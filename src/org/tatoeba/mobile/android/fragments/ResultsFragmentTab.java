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