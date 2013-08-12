package org.tatoeba.mobile.android;

import android.os.AsyncTask;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 12.08.13
 * Time: 22:44
 */


public class QueryTatoebaTask extends AsyncTask<URL, Integer, Long>
{

    protected Long doInBackground(URL... urls)
    {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
           // totalSize += Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress)
    {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result)
    {
        //showDialog("Downloaded " + result + " bytes");
    }

}
