package org.tatoeba.mobile.android.fragments;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 3.08.13
 * Time: 23:15
 */


import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import org.tatoeba.mobile.android.R;

public class BrowseFragmentTab extends TatoebaMainFragment implements ActionBar.TabListener {

    private EditText _sentenceIdBox;
    protected boolean _firstTouch = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from browse_fragmentagment.xml


        _activity.setContentView(R.layout.browse_fragment);
        _sentenceIdBox = (EditText) _activity.findViewById(R.id.sentenceIdInputBox);

        // remove the greeting text from the sentence ID box
        _sentenceIdBox.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(hasFocus && _firstTouch)
                {
                    _sentenceIdBox.setText("");
                    _firstTouch = false;
                }
            }
        });

    }



    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new BrowseFragmentTab();
        // Attach browse_fragmentagment.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
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