package org.tatoeba.mobile.android;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 3.08.13
 * Time: 23:14
 */

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.view.View;
import android.widget.EditText;

public class SearchFragmentTab extends Fragment implements ActionBar.TabListener {

    private Fragment mFragment;

    private EditText _searchBox;
    private boolean _firstTouch = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from search_fragment.xml

        Activity activity = getActivity();
        activity.setContentView(R.layout.search_fragment);
        _searchBox = (EditText) activity.findViewById(R.id.editText);

        // remove the greeting text from the search box
        _searchBox.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(hasFocus && _firstTouch)
                {
                    _searchBox.setText("");
                    _firstTouch = false;
                }
            }
        });
    }




    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new SearchFragmentTab();
        // Attach search_fragment.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove search_fragment.xml layout
        ft.remove(mFragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }

}