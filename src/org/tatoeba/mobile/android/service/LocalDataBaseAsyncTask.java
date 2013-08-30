package org.tatoeba.mobile.android.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import au.com.bytecode.opencsv.CSVParser;
import org.tatoeba.mobile.android.WelcomeActivity;
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
public class LocalDataBaseAsyncTask extends AsyncTask<String, Integer, Long>
{


    private ProgressBar _progressBar;
    private WelcomeActivity _welcomeActivity;
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


    public LocalDataBaseAsyncTask(Context context)
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

        String[] record = new String[10];
        _csvParser = new CSVParser('\t');
        String[] csvFiles = new String[]{USERS_CSV, LINKS_CSV, SENTENCES_CSV, };

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
        String trash = "";
        try
        {
            if (type.equals(SENTENCES_CSV))
            {
                //5966	nld	Ik moet gaan slapen.	17
                trash += record[0]; // sentence id
                trash += record[1]; // sentence language
                trash += record[2]; // sentence text
                trash += record[3]; // user id
            }
            else if (type.equals(LINKS_CSV))
            {
                //1289	5980
                trash += record[0]; // left sentence id
                trash += record[1]; // right sentence id

            }
            else if (type.equals(USERS_CSV))
            {
                //7	Arraroak	Arraroak@tatoeba.org
                trash += record[0]; // user id
                trash += record[1]; // user name
                trash += record[2]; // email
            }
        } catch (Exception e)
        {
            Log.w("###", "This record failed: " + record.toString());
        }

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
        _welcomeActivity.onDataBaseCreated();
    }

    public void setVisualAssets(TextView textView, ProgressBar progressBar, WelcomeActivity welcomeActivity)
    {
        _splashText = textView;
        _progressBar = progressBar;
        _welcomeActivity = welcomeActivity;
    }
}
