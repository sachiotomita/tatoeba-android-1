package org.tatoeba.mobile.android.service.local_database;

import android.content.Context;
import android.os.AsyncTask;
import org.tatoeba.mobile.android.models.RandomSentenceRequestModel;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;
import org.tatoeba.mobile.android.service.ITatoebaDBCallbackAPI;
import org.tatoeba.mobile.android.service.local_database.data_sources.RandomSentenceDataSource;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 12.08.13
 * Time: 22:44
 */


/**
 * This class is intended to query the local
 */

public class FetchRandomDataBaseAsyncTask extends AsyncTask<RandomSentenceRequestModel, Integer, Long>
{


    private Context _context;
    private ITatoebaDBCallbackAPI _callbackAPI;
    private ArrayList<TranslatedSentenceModel> _translations;

    public FetchRandomDataBaseAsyncTask(Context context)
    {
        _context = context;
    }

    protected Long doInBackground(RandomSentenceRequestModel... params)
    {

        RandomSentenceDataSource dataSource = new RandomSentenceDataSource(_context);
        dataSource.open();
        RandomSentenceRequestModel requestModel = params[0];

        _translations = dataSource.fetchRandomSentences(requestModel);

        return Long.valueOf(0);
    }



    protected void onProgressUpdate(Integer... progress)
    {

    }

    protected void onPreExecute(String data)
    {
    }



    protected void onPostExecute(Long result)
    {
        _callbackAPI.setRandomFetchResult(_translations);
    }


    public void setCallbackAPI(ITatoebaDBCallbackAPI callbackAPI)
    {
        _callbackAPI = callbackAPI;
    }
}
