package org.tatoeba.mobile.android;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 12.08.13
 * Time: 22:35
 */


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultsFragmentTab extends Fragment implements ActionBar.TabListener {

    private Fragment mFragment;

    private TextView _tempText;
    private ProgressBar _progressBar;


    private boolean _firstTouch = true;
    private WelcomeActivity _activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from browse_fragmentagment.xml

        _activity = (WelcomeActivity) getActivity();
        _activity.setContentView(R.layout.results_fragment);
        _tempText = (TextView) _activity.findViewById(R.id.tempTextField);
        _tempText.setText("General purpose temp results log here...");

        _progressBar = (ProgressBar) _activity.findViewById(R.id.resultsProgressBar);
        //_progressBar.setVisibility(View.INVISIBLE);

    }


    public void onTabSelected(Tab tab, FragmentTransaction ft) {
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


        /*
        // try to access the search string
        String searchStr = _activity.currentSearchString;

            if(searchStr != null && !searchStr.isEmpty())
            {
                _tempText.setText(searchStr);
            }

          */
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove browse_fragment.xmlnt.xml layout
        ft.remove(mFragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }

}