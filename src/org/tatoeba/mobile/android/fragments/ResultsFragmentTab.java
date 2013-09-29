package org.tatoeba.mobile.android.fragments;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 12.08.13
 * Time: 22:35
 */


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import au.com.bytecode.opencsv.CSVParser;
import org.tatoeba.mobile.android.MainActivity;
import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.SentenceDetailsActivity;
import org.tatoeba.mobile.android.enums.INTENT_EXTRAS;
import org.tatoeba.mobile.android.fragments.enums.MAIN_TABS;
import org.tatoeba.mobile.android.fragments.enums.SEARCH_ACTIONS;
import org.tatoeba.mobile.android.fragments.enums.TOAST_MESSAGES;
import org.tatoeba.mobile.android.models.RandomSentenceRequestModel;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;
import org.tatoeba.mobile.android.service.ITatoebaDBCallbackAPI;
import org.tatoeba.mobile.android.service.local_database.FetchRandomDataBaseAsyncTask;
import org.tatoeba.mobile.android.service.local_database.QueryTatoebaTask;
import org.tatoeba.mobile.android.views.search_result.SentenceAdapter;

import java.util.ArrayList;

public class ResultsFragmentTab extends TatoebaMainFragment implements ActionBar.TabListener, ITatoebaDBCallbackAPI
{

    private MainActivity _mainActivity;

    private QueryTatoebaTask _service;
    private CSVParser _csvParser;
    private ListView _resultsListView;
    SentenceAdapter adapter;


    private ArrayList<TranslatedSentenceModel> _translations;


////////////////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initialize();

        fetchResults();
    }

    private void initializeResultItemClickListener()
    {
        // Click event for single list row
        _resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Log.d("###", "view id: " + id + ", position: " + position);

//                // get the clicked sentence's model
//                TranslatedSentenceModel translatedSentence =
//                        (TranslatedSentenceModel)parent.getAdapter().getItem(position);


                Intent details = new Intent(_activity, SentenceDetailsActivity.class);
                details.putExtra(INTENT_EXTRAS.CURRENT_TRANSLATION_POSITION.name(), position);
                startActivity(details);

            }
        });
    }

    /**
     * Checks if there's any sor tof sentence fetching requested.
     */
    private void fetchResults()
    {
        MainActivity mainActivity = (MainActivity) getActivity();
        SEARCH_ACTIONS searchAction = mainActivity.get_searchAction();

        if (searchAction == null) return;

        //showProgressIndication();
        updateResultsView();

        switch (searchAction)
        {
            case FETCH_RANDOM : fetchRandom(); break;
            case FETCH_BY_ID: fetchById(); break;
            case SEARCH: search(); break;
        }

        ///////////////////////////////////////////////////////////////////

        return ;
    }


    private void initialize()
    {
        _mainActivity =  _activity;
        _mainActivity.setContentView(R.layout.results_fragment);

        /*
        _translations = new ArrayList<TranslatedSentenceModel>();

      //  initializePagination();

        SentenceModel tempMainSentence;
        SentenceModel tempSingleTranslation;
        ArrayList<SentenceModel> tempTranslationCollection;

        TranslatedSentenceModel translatedSentence;

        //The code below generates fake sentences and fake translations
        // TODO: This fake-content generator should be moved to a service or model (think it over!), it should not be here.

        String languages[] = {"eng", "ndl", "fra", "rus", "est", "fin", "ger", "spa"};
        for (int i = 0; i < 15; i++)
        {
            // fake the main sentence
            tempMainSentence = new SentenceModel();
            tempMainSentence.setText("This is a sample main sentence. A longer one, actually. Index: [" + i + "]");
            tempMainSentence.setLanguage("eng");
            tempMainSentence.setSentenceId( (int)(Math.random()*5000) );
            tempTranslationCollection = new ArrayList<SentenceModel>();

            // fake the translations to the main sentence
            for (int j = 0; j < (int)(Math.random()*14); j++)
            {
                tempSingleTranslation = new SentenceModel();
                tempSingleTranslation.setText("And here is a sample translation. "+
                        "A bit longer this time. And the index: [" + i + ", " + j + "]");

                int tempLanguageIndex = j % 5;

                tempSingleTranslation.setLanguage( languages[tempLanguageIndex] );
                tempTranslationCollection.add(tempSingleTranslation);
            }

            translatedSentence = new TranslatedSentenceModel(tempMainSentence, tempTranslationCollection);
            _translations.add(translatedSentence);

            TatoebaApp appState = ((TatoebaApp)_activity.getApplicationContext());
            appState.setCurrentTranslations(_translations);
        }
          */
    }

    /*private void initializePagination()
    {

        List<String> spinnerArray = new ArrayList<String>();

        for (int i = 0; i < 8; i++)
        {
            spinnerArray.add(i + "");
        }


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_item, spinnerArray);

    }*/

    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {
        // TODO Auto-generated method stub
        mFragment = new ResultsFragmentTab();
        // Attach browse_fragmentagment.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }


    private void fetchRandom()
    {
        Log.d("###", "Fetching a random sentence");
        RandomSentenceRequestModel request = _activity.get_randomSentenceRequest();
        FetchRandomDataBaseAsyncTask asyncTask = new FetchRandomDataBaseAsyncTask( _context );
        asyncTask.setCallbackAPI(this);
        asyncTask.execute(request);
    }

    private void fetchById()
    {
        Log.d("###", "Fetching a sentence by id");
    }

    private void search()
    {
        Log.d("###", "Searching for sentences");
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft)
    {
        // TODO Auto-generated method stub
        // Remove browse_fragment.xmlnt.xml layout
        ft.remove(mFragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void setRandomFetchResult(TranslatedSentenceModel[] sentences)
    {

        if (sentences == null)
        {
            _appState.showToast(TOAST_MESSAGES.RANDOM_SENTENCES_NOT_FOUND);
            switchTab(MAIN_TABS.BROWSE);
            return;
        }

        _translations = new ArrayList<TranslatedSentenceModel>();
        for (TranslatedSentenceModel sentence : sentences)
        {
            Log.d("###", sentence.get_mainSentence().toString());
            _translations.add(sentence);
        }

        _appState.setCurrentTranslations(_translations);
        updateResultsView();

    }


    private void updateResultsView()
    {
        _translations = _appState.getCurrentTranslations();
        // nothing to display, but the progress indicator.
        if (_translations == null)
        {
            _activity.setContentView(R.layout.results_fragment_progress);
            return;
        }

        _activity.setContentView(R.layout.results_fragment);

        adapter = new SentenceAdapter(_activity, _translations);

        //_resultsListView = new ListView(_context);

        _resultsListView = (ListView) _activity.findViewById(R.id.resultList);

        _resultsListView.setAdapter(adapter);
        initializeResultItemClickListener();

    }

}