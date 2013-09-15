package org.tatoeba.mobile.android.fragments;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 3.08.13
 * Time: 23:14
 */

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.MainActivity;
import org.tatoeba.mobile.android.fragments.enums.MAIN_TABS;

public class SearchFragmentTab extends TatoebaMainFragment implements ActionBar.TabListener {

    private EditText _searchBox;
    private Button _searchButton;
    protected boolean _firstTouch = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Get the view from search_fragment.xml
        _activity.setContentView(R.layout.search_fragment);

        initialize();

        // TEMPORARY SWITCH to facilitate a quicker debugging of the ListView on the "results" tab.
        //switchTab(MAIN_TABS.RESULTS);
    }

    private void initialize()
    {
        _searchBox = (EditText) _activity.findViewById(R.id.editText);
        _searchButton = (Button) _activity.findViewById(R.id.searchButton_searchFragment);

        _searchButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String searchString = SearchFragmentTab.this._searchBox.getText().toString();

                // nothing to do if the string is empty
                if(searchString.length() == 0 )
                {
                    _searchBox.setFocusableInTouchMode(true);
                    _searchBox.requestFocus();
                    return;
                }

                ((MainActivity) SearchFragmentTab.this._activity).currentSearchString = searchString;
                //SearchFragmentTab.this._actionBar.setSelectedNavigationItem(2);
                switchTab(MAIN_TABS.RESULTS);
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