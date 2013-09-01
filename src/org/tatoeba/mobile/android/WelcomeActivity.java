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
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.tatoeba.mobile.android.fragments.BrowseFragmentTab;
import org.tatoeba.mobile.android.fragments.ResultsFragmentTab;
import org.tatoeba.mobile.android.fragments.SearchFragmentTab;
import org.tatoeba.mobile.android.service.local_database.InitLocalDataBaseAsyncTask;
import org.tatoeba.mobile.android.service.local_database.TatoebaDBHelper;

public class WelcomeActivity extends Activity
{
    // Declare Tab Variable
    private Tab tab;

    /**
     * Contains the latest search string
     */
    public String currentSearchString;
    private Context _context;
    private TextView _splashText;
    private ProgressBar _splashProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _context = this.getBaseContext();
        boolean isDBInitialized = TatoebaDBHelper.databaseExists(_context);

        if (isDBInitialized)
            showMainView();
        else
            showSplash();
    }

    /** Show splashscreen and initialize the DB in the background */
    private void showSplash()
    {
        setContentView(R.layout.splash_screen);

        _splashText = (TextView) findViewById(R.id.splashScreenTextView);
        _splashProgressBar = (ProgressBar) findViewById(R.id.splashScreenProgressBar);

        InitLocalDataBaseAsyncTask localDB = new InitLocalDataBaseAsyncTask( _context );
        localDB.setVisualSplashAssets(_splashText, _splashProgressBar, this);
        localDB.execute("Test string");
    }

    public void onDataBaseCreated()
    {
        _splashText.setText("");
        _splashText = null;
        _splashProgressBar = (ProgressBar) findViewById(R.id.splashScreenProgressBar);
        _splashProgressBar = null;
        showMainView();
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