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
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.WelcomeActivity;
import org.tatoeba.mobile.android.service.QueryTatoebaTask;

public class ResultsFragmentTab extends TatoebaMainFragment implements ActionBar.TabListener
{

    private TextView _tempText;
    private ProgressBar _progressBar;


    private boolean _firstTouch = true;
    private WelcomeActivity _welcomeActivity;

    private QueryTatoebaTask _service;
    private CSVParser _csvParser;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initialize();
        handleSearchString();

    }

    private void handleSearchString()
    {
        // try to access the search string
        String searchStr = _welcomeActivity.currentSearchString;

        // nothing to do if no search string was set.
        if(searchStr == null || searchStr.isEmpty())
            return;


        _tempText.setText(searchStr);


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







    }

    private void initialize()
    {

        _welcomeActivity = (WelcomeActivity) _activity;
        _welcomeActivity.setContentView(R.layout.results_fragment);
        _tempText = (TextView) _welcomeActivity.findViewById(R.id.tempTextField);
        _tempText.setText("General purpose temp results log here...");

        _progressBar = (ProgressBar) _welcomeActivity.findViewById(R.id.resultsProgressBar);
        //_progressBar.setVisibility(View.INVISIBLE);

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