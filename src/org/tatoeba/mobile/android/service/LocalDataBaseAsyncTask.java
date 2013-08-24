package org.tatoeba.mobile.android.service;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import au.com.bytecode.opencsv.CSVParser;
import android.content.res.AssetFileDescriptor;
import org.tatoeba.mobile.android.WelcomeActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    /** CSV parser */
    private CSVParser _csvParser;


//    TODO:
//    SIZE_INDEX_FILE, USERS_CSV and other csv fileNames should be placed into an array. This array then has to be
//    processed element by element. There should be a unified method to parse the CSV and

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

        ArrayList<String> tempList =  new ArrayList<String>();
        tempList.add("temp");
        tempList.add("temp 1");
        tempList.add("temp 2");


        String[] record = tempList.toArray(new String[tempList.size()]);


        _csvParser = new CSVParser('\t');
        try
        {
            record = _csvParser.parseLine("12312\teng\tThis is a text!");
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        _splashText.setText("Processing: " + SENTENCES_CSV);

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(_context.getAssets().open(SENTENCES_CSV), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine = reader.readLine();
            while (mLine != null) {
                //process line
                try
                {
                    _processedDataLength += mLine.getBytes().length;
                    record = _csvParser.parseLine(mLine);
                    _tempCurrentLine = "\n" + record[0] + "|" +record[1] + "|" + record[2];
                    publishProgress((int) ((_processedDataLength / (float)_totalDataLength ) * 100));
                }
                catch (IOException e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


                mLine = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            //log the exception
        }

//-------------------------------------------------------------------------------------------------------------



        return _totalDataLength;
    }

    protected void onProgressUpdate(Integer... progress)
    {
        //setProgressPercent(progress[0]);
        //if (_splashText == null) throw new Error("No text view was set!");

        if (_progressBar == null) throw new Error("No progress bar was set!");
        _progressBar.setProgress(progress[0]);
        //_splashText.setText(_tempCurrentLine);

        //_textView.append(progress[0].toString() + "% ");
        //_scrollView.scrollTo(0, _scrollView.getBottom() );
        //_textView.scrollTo(0, _textView.getBottom());

    }

    protected void onPreExecute(String data)
    {
    }

    protected void onPostExecute(Long result)
    {
        //showDialog("Downloaded " + result + " bytes");
        _welcomeActivity.onDataBaseCreated();
    }

    //public void setVisualAssets(TextView textView, ScrollView scrollView)
    public void setVisualAssets(TextView textView, ProgressBar progressBar, WelcomeActivity welcomeActivity)
    {

        _splashText = textView;
        //_scrollView = scrollView;
        _progressBar = progressBar;
        _welcomeActivity = welcomeActivity;
    }
}
