/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 3.08.13
 * Time: 22:10
 */

package org.tatoeba.mobile.android;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.tatoeba.mobile.android.fragments.BrowseFragmentTab;
import org.tatoeba.mobile.android.fragments.ResultsFragmentTab;
import org.tatoeba.mobile.android.fragments.SearchFragmentTab;
import org.tatoeba.mobile.android.service.LocalDataBaseAsyncTask;

public class WelcomeActivity extends Activity
{
    // Declare Tab Variable
    private Tab tab;

    /**
     * Contains the latest search string
     */
    public String currentSearchString;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        boolean isDBInitialized = checkDBState();

        if (isDBInitialized)
        {
            showMainView();
        }
        else
        {
            showSplash();
        }
    }

    /** Show splashscreen and initialize the DB in the background */
    private void showSplash()
    {
        setContentView(R.layout.splash_screen);
        TextView splashText =  (TextView) findViewById(R.id.splashScreenTextView);
        ProgressBar splashProgressBar =  (ProgressBar) findViewById(R.id.splashScreenProgressBar);


        LocalDataBaseAsyncTask localDB =  new LocalDataBaseAsyncTask( getBaseContext());
        localDB.setVisualAssets(splashText,splashProgressBar, this);
        localDB.execute("blala");
    }

    public void onDataBaseCreated()
    {
        // TODO: Dispose the splash screen and its resources properly!

        showMainView();
    }

    /** Checks if the DB was initialized already or not. */
    private boolean checkDBState()
    {
        return false;
    }

    /** Display the main view */
    private void showMainView()
    {
        // Create the actionbar
        ActionBar actionBar = getActionBar();

        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);

        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);

        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create "Search" Tab
        tab = actionBar.newTab().setTabListener(new SearchFragmentTab());
        // Create your own custom icon
        //tab.setIcon(R.drawable.tab1);
        tab.setText(getString(R.string.searchTabCaption));
        actionBar.addTab(tab);

        // Create "Browse"  Tab
        tab = actionBar.newTab().setTabListener(new BrowseFragmentTab());
        // Set Tab Title
        tab.setText(getString(R.string.browseTabCaption));
        actionBar.addTab(tab);


        // Create "Results" Tab
        tab = actionBar.newTab().setTabListener(new ResultsFragmentTab());
        // Set Tab Title
        tab.setText(getString(R.string.resultsTabCaption));
        actionBar.addTab(tab);
    }


}