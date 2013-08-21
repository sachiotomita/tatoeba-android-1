package org.tatoeba.mobile.android.service;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.net.URL;

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
public class QueryTatoebaTask extends AsyncTask<String, Integer, Long>
{

    private TextView _textView;
    private ProgressBar _progressBar;
    private ScrollView _scrollView;

    protected Long doInBackground(String... searchText)
    {
        int count = searchText.length;

        int MAX = 1000; // Integer.MAX_VALUE

        for (int i = 0; i < MAX; i++) {

            publishProgress((int) ((i / (float) MAX) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }


        long totalSize = 0;
        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress)
    {
        //setProgressPercent(progress[0]);
        if (_textView == null) throw new Error("No text view was set!");
        if (_progressBar == null) throw new Error("No progress bar was set!");

        _progressBar.setProgress(progress[0]);

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
    }

    //public void setVisualAssets(TextView textView, ScrollView scrollView)
    public void setVisualAssets(TextView textView, ProgressBar progressBar)
    {

        _textView = textView;
        //_scrollView = scrollView;
        _progressBar = progressBar;
    }
}
