package org.tatoeba.mobile.android.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import org.tatoeba.mobile.android.MainActivity;
import org.tatoeba.mobile.android.TatoebaApp;
import org.tatoeba.mobile.android.fragments.enums.MAIN_TABS;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 18.08.13
 * Time: 10:48
 */

/**
 * The class is intended to abstract the common properties and methods of all the main menu tab fragments.
 */
public abstract class TatoebaMainFragment extends Fragment
{
    protected Fragment mFragment;
    protected ActionBar _actionBar;
    protected MainActivity _activity;
    protected Context _context;

    protected TatoebaApp _appState;

    /**
     * Selects and opens one of the main menu tabs
     */
    public void switchTab(MAIN_TABS tab)
    {
        //Log.d("###","tab.ordinal()="+tab.ordinal());
        this._actionBar.setSelectedNavigationItem(tab.ordinal());
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        _activity = (MainActivity)activity; //_activity = (MainActivity)getActivity();
        _context = _activity.getBaseContext();
        _actionBar = _activity.getActionBar();
        _appState = ((TatoebaApp) _activity.getApplicationContext());
    }



}
