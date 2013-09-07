package org.tatoeba.mobile.android.fragments;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 12.08.13
 * Time: 22:35
 */


import android.app.*;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.widget.*;
import au.com.bytecode.opencsv.CSVParser;

import java.util.ArrayList;

import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.WelcomeActivity;
import org.tatoeba.mobile.android.fragments.enums.MAIN_TABS;
import org.tatoeba.mobile.android.models.SentenceModel;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;
import org.tatoeba.mobile.android.service.local_database.QueryTatoebaTask;
import org.tatoeba.mobile.android.views.search_result.SentenceAdapter;

public class ResultsFragmentTab extends ListFragment implements ActionBar.TabListener
{

    private TextView _tempText;
    private ProgressBar _progressBar;


    private boolean _firstTouch = true;
    private WelcomeActivity _welcomeActivity;

    private QueryTatoebaTask _service;
    private CSVParser _csvParser;


//////////////////////////////////////////////////////////////
//      The list-related members

    ListView list;
    SentenceAdapter adapter;

//////////////////////////////////////////////////////////////
//      This part is to make the fragment compatible with TatoebaMainFragment
//      (Hopefully, this is a temporary solution

    protected Fragment mFragment;
    protected ActionBar _actionBar;
    protected Activity _activity;
    private ArrayList<TranslatedSentenceModel> _translations;

    /**
     * Selects and opens one of the main menu tabs
     */
    protected void switchTab(MAIN_TABS tab)
    {
        //Log.d("###","tab.ordinal()="+tab.ordinal());
        this._actionBar.setSelectedNavigationItem(tab.ordinal());
    }


    private void onCreateTatoebaMainFragment(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _activity = getActivity();
        _actionBar = this.getActivity().getActionBar();
    }
////////////////////////////////////////////////////////////


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        onCreateTatoebaMainFragment(savedInstanceState);
        super.onCreate(savedInstanceState);
        initialize();
        //handleSearchString();

        list = (ListView) _activity.findViewById(R.id.resultList);

        // Getting adapter by passing xml data ArrayList
        adapter = new SentenceAdapter(_activity, _translations);
        //list.setAdapter(adapter);
        setListAdapter(adapter);

        /*
        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Log.d("###", "view id: " + id);
            }
        });
          */


    }

    private void handleSearchString()
    {
        // try to access the search string
        String searchStr = _welcomeActivity.currentSearchString;

        // nothing to do if no search string was set.
        if (searchStr == null || searchStr.isEmpty())
            return;


        _tempText.setText(searchStr);

        /*
        if (_service == null) _service = new QueryTatoebaTask();
        _service.setVisualAssets(_tempText, _progressBar);
        _service.execute(searchStr);


        //////////////////////////////////////////////////////////////////////////////
        // Below is an experimental part, here I try to parse CSV from a file.
        // CSV files are planned to be used to populate the DB with the initial values.
        // This code should eventually be moved to the DB related classes.
        //


        ArrayList<String> tempList =  new ArrayList<String>();
        tempList.add("temp");
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



        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(_activity.getAssets().open("db_init_sentences.csv"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine = reader.readLine();
            while (mLine != null) {
                //process line

                try
                {
                    record = _csvParser.parseLine(mLine);
                    _tempText.append("\n" + record[0]);
                    _tempText.append("\n" + record[1]);
                    _tempText.append("\n" + record[2] + "\n");

                } catch (IOException e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


                mLine = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            //log the exception
        }
                   */
    }


    private void initialize()
    {
        _welcomeActivity = (WelcomeActivity) _activity;
        _welcomeActivity.setContentView(R.layout.results_fragment);

        _translations = new ArrayList<TranslatedSentenceModel>();

        SentenceModel tempMainSentence;
        SentenceModel tempSingleTranslation;
        ArrayList<SentenceModel> tempTranslationCollection;

        TranslatedSentenceModel translatedSentence;

        for (int i = 0; i < 15; i++)
        {
            // fake the main sentence
            tempMainSentence = new SentenceModel();
            tempMainSentence.setText("This is a sample main sentence. Index: [" + i + "]");
            tempTranslationCollection = new ArrayList<SentenceModel>();

            // fake the translations to the main sentence
            for (int j = 0; j < 5; j++)
            {
                tempSingleTranslation = new SentenceModel();
                tempSingleTranslation.setText("And here is a sample translation. Index: [" + i + ", " + j + "]");
                tempTranslationCollection.add(tempSingleTranslation);
            }

            translatedSentence = new TranslatedSentenceModel(tempMainSentence, tempTranslationCollection);
            _translations.add(translatedSentence);

        }





        /*
        _tempText = (TextView) _welcomeActivity.findViewById(R.id.tempTextField);
        _tempText.setText("General purpose temp results log here...");
        _progressBar = (ProgressBar) _welcomeActivity.findViewById(R.id.resultsProgressBar);
        //_progressBar.setVisibility(View.INVISIBLE);
        */
    }


    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {


        // TODO Auto-generated method stub
        mFragment = new ResultsFragmentTab();
        // Attach browse_fragmentagment.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);

        /*
        if (_firstTouch)
        {
            _firstTouch = false;
            return;
        }
        _progressBar.setVisibility(ProgressBar.SYSTEM_UI_FLAG_VISIBLE);
        return;
        */

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
}


/*
    String[] languages = new String[]{
            "Dutch",
            "English",
            "Russian",
            "Swedish",
            "Tamil",
            "French",
            "Estonian",
            "Finnish",
            "Ukrainian",
            "Japanese",
            "German",
            "Chinese",
            "Norwegian"
    };
*/

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Creating an array adapter to store the list of languages
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(inflater.getContext(), R.layout.result_list_item_1, languages);

        //android.R.layout.simple_list_item_1
        // Setting the list adapter for the ListFragment
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    */


/*

public class
ArrayAdapter
extends BaseAdapter implements Filterable

A concrete BaseAdapter that is backed by an array of arbitrary objects. By default this class expects that the provided resource id references a single TextView. If you want to use a more complex layout, use the constructors that also takes a field id. That field id should reference a TextView in the larger layout resource.
However the TextView is referenced, it will be filled with the toString() of each object in the array. You can add lists or arrays of custom objects. Override the toString() method of your objects to determine what text will be displayed for the item in the list.
To use something other than TextViews for the array display, for instance, ImageViews, or to have some of data besides toString() results fill the views, override getView(int, View, ViewGroup) to return the type of view you want.
 */
