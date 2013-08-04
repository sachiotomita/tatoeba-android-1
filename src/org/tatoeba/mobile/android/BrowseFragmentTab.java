package org.tatoeba.mobile.android;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: leo
 * Date: 3.08.13
 * Time: 23:15
 */


import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.view.View;
import android.widget.EditText;

public class BrowseFragmentTab extends Fragment implements ActionBar.TabListener {

    private Fragment mFragment;

    private EditText _sentenceIdBox;
    private boolean _firstTouch = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from browse_fragmentagment.xml

        Activity activity = getActivity();
        activity.setContentView(R.layout.browse_fragment);
        _sentenceIdBox = (EditText) activity.findViewById(R.id.sentenceIdInputBox);

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