package org.tatoeba.mobile.android.service.local_database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import au.com.bytecode.opencsv.CSVParser;
import org.tatoeba.mobile.android.MainActivity;
import org.tatoeba.mobile.android.models.SentenceLinkModel;
import org.tatoeba.mobile.android.models.SentenceModel;
import org.tatoeba.mobile.android.models.UserModel;
import org.tatoeba.mobile.android.service.local_database.data_sources.SentenceDataSource;
import org.tatoeba.mobile.android.service.local_database.data_sources.SentenceLinksDataSource;
import org.tatoeba.mobile.android.service.local_database.data_sources.UsersDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 12.08.13
 * Time: 22:44
 */


/**
 * This class is intended to query the local and the remote tatoeba databases in the background
 * and update the UI correspondingly.
 */
//public class QueryTatoebaTask extends AsyncTask<URL, Integer, Long>
public class InitLocalDataBaseAsyncTask extends AsyncTask<String, Integer, Long>
{


    private ProgressBar _progressBar;
    private MainActivity _mainActivity;
    private ScrollView _scrollView;
    private Context _context;
    private TextView _splashText;
    private String _tempCurrentLine;

    /** Total length of all the CSV-assets to parse */
    private long _totalDataLength = 0;

    /** Length of the CSV-asset data processed so far */
    private long _processedDataLength = 0;

    private final String SIZE_INDEX_FILE = "db_init/total_size.txt";
    private final String USERS_CSV = "db_init/users.csv";
    private final String LINKS_CSV = "db_init/links.csv";
    private final String SENTENCES_CSV = "db_init/sentences.csv";

    private String _currentFile = "none";
    private String _currentlyDisplayedFile = "none";

    /** CSV parser */
    private CSVParser _csvParser;

    // db-related variables
    private UsersDataSource _userDataSource;
    private SentenceLinksDataSource _sentenceLinksDataSource;
    private SentenceDataSource _sentenceDataSource;




    public InitLocalDataBaseAsyncTask(Context context)
    {
        _context = context;
        _csvParser = new CSVParser('\t');

        // get the total CSV assets file size.
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(_context.getAssets().open(SIZE_INDEX_FILE), "UTF-8"));

            // do reading, usually loop until end of file reading
            String sizeStr = reader.readLine();
            Log.d("###", "sizeStr="+sizeStr);
            _totalDataLength = Long.parseLong(sizeStr);
            reader.close();
        }
        catch (IOException e)
        {
            Log.e("###", "Could not read file: " + SIZE_INDEX_FILE );
        }
    }

    protected Long doInBackground(String... searchText)
    {
//-------------------------------------------------------------------------------------------------------------

        if (_userDataSource == null) _userDataSource = new UsersDataSource(_context);
        if (_sentenceLinksDataSource == null) _sentenceLinksDataSource = new SentenceLinksDataSource(_context);
        if (_sentenceDataSource == null) _sentenceDataSource = new SentenceDataSource(_context);

        String[] record;
        _csvParser = new CSVParser('\t');
        String[] csvFiles = new String[]{LINKS_CSV, USERS_CSV, SENTENCES_CSV, };

        // this call opens the DB for all the 3:
        // _userDataSource, _sentenceLinksDataSource and _sentenceDataSource.
        // They all share the same DB instance and  the open() and close() implementation.
        _userDataSource.open();

        for (int i=0; i<csvFiles.length; i++)
        {
            _currentFile = csvFiles[i];

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(_context.getAssets().open(csvFiles[i]), "UTF-8"));

                // do reading, usually loop until end of file reading
                String mLine = reader.readLine();
                while (mLine != null) {
                    //process line
                    try
                    {
                        _processedDataLength += mLine.getBytes().length;
                        record = _csvParser.parseLine(mLine);

                        // parse the CSV record according to which file it belongs to.
                        parseRecord(record, csvFiles[i]);

                        publishProgress((int) ((_processedDataLength / (float) _totalDataLength) * 100));
                    } catch (IOException e)
                    {
                        Log.d("###", "Failed here: " +  mLine);
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    mLine = reader.readLine();
                }

                reader.close();
            } catch (IOException e)
            {
                Log.e("###", "Could not open file: " + _currentFile);
            }
        }
        _userDataSource.close();
//-------------------------------------------------------------------------------------------------------------
        return _totalDataLength;
    }

    /**
     * Parses a record according to its type and saves the result to a corresponding property
     * @param record
     * @param type
     */
    private void parseRecord(String[] record, String type)
    {

        // TEMPORARY! Remove the whole old DB before we begin.
        //_context.deleteDatabase(TatoebaDBHelper.DATABASE_NAME);

        //try
        //{
            if (type.equals(SENTENCES_CSV))
            {
                //5966	nld	Ik moet gaan slapen.	17
                int sentenceID = Integer.parseInt(record[0]); // sentence id
                String language = record[1]; // sentence language
                String text = record[2]; // sentence text
                int ownerId  = Integer.parseInt(record[3]); // owner id
                SentenceModel sentence = _sentenceDataSource.createSentence(sentenceID,language, text, ownerId);
            }
            else if (type.equals(LINKS_CSV))
            {
                //1289	5980
                int leftSentenceID = Integer.parseInt(record[0]); // left sentence id
                int rightSentenceID = Integer.parseInt(record[1]); // right sentence id
                SentenceLinkModel link = _sentenceLinksDataSource.createLink(leftSentenceID, rightSentenceID);
                //Log.d("##", link.toString());
            }
            else if (type.equals(USERS_CSV))
            {
                //7	Arraroak	Arraroak@tatoeba.org
                int userId = Integer.parseInt(record[0]); // user id
                String userName = record[1]; // user name
                String email  = record[2]; // email
                UserModel user = _userDataSource.createUser(userId, userName,email);
                //Log.d("##", user.toString());

            }
        /*} catch (Exception e)
        {
            Log.w("###", "This record failed: " + record.toString());
        }
              */
    }


    protected void onProgressUpdate(Integer... progress)
    {
        if (_progressBar == null) throw new Error("No progress bar was set!");
        _progressBar.setProgress(progress[0]);

        if (!_currentlyDisplayedFile.equals(_currentFile))
        {
            _currentlyDisplayedFile = _currentFile;
            _splashText.setText("Processing: " + _currentFile);
        }
    }

    protected void onPreExecute(String data)
    {
    }

    protected void onPostExecute(Long result)
    {
        //showDialog("Downloaded " + result + " bytes");
        _mainActivity.onDataBaseCreated();
    }

    public void setVisualSplashAssets(TextView textView, ProgressBar progressBar, MainActivity mainActivity)
    {
        _splashText = textView;
        _progressBar = progressBar;
        _mainActivity = mainActivity;
    }
}
