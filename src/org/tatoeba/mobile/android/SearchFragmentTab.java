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
import android.widget.Button;
import android.widget.EditText;

public class SearchFragmentTab extends Fragment implements ActionBar.TabListener {

    private Fragment mFragment;

    private EditText _searchBox;
    private Button _searchButton;
    private ActionBar _ActionBar;

    private boolean _firstTouch = true;
    private Activity _activity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from search_fragment.xml
        _activity = getActivity();
        _activity.setContentView(R.layout.search_fragment);
        _searchBox = (EditText) _activity.findViewById(R.id.editText);
        _searchButton = (Button) _activity.findViewById(R.id.searchButton_searchFragment);

        _ActionBar = this.getActivity().getActionBar();

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


        _searchButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String searchString = SearchFragmentTab.this._searchBox.getText().toString();
                ((WelcomeActivity) SearchFragmentTab.this._activity).currentSearchString = searchString;
                SearchFragmentTab.this._ActionBar.setSelectedNavigationItem(2);
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