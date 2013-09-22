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
import org.tatoeba.mobile.android.fragments.TatoebaMainFragment;
import org.tatoeba.mobile.android.fragments.enums.MAIN_TABS;
import org.tatoeba.mobile.android.fragments.enums.SEARCH_ACTIONS;
import org.tatoeba.mobile.android.models.RandomSentenceRequestModel;
import org.tatoeba.mobile.android.service.local_database.InitLocalDataBaseAsyncTask;
import org.tatoeba.mobile.android.service.local_database.TatoebaDBHelper;

public class MainActivity extends Activity
{
    // Declare Tab Variable
    private Tab tab;

    /**
     * Contains the latest random request.
     */
    private RandomSentenceRequestModel _randomSentenceRequest;

    //TODO: add model for fetching by id
    // private ... _fetchByIdRequest;

    //TODO: add model for searching by keywords
    // private ... _seachRequest;

   private SEARCH_ACTIONS _searchAction;

    private Context _context;
    private TextView _splashText;
    private ProgressBar _splashProgressBar;
    private ActionBar _actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _context = this.getBaseContext();

        // Delete the database each time the app is started!
        //_context.deleteDatabase(TatoebaDBHelper.DATABASE_NAME);

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
        _actionBar = getActionBar();

        // Hide Actionbar Icon
        _actionBar.setDisplayShowHomeEnabled(false);

        // Hide Actionbar Title
        _actionBar.setDisplayShowTitleEnabled(false);

        // Create Actionbar Tabs
        _actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create "Search" Tab
        tab = _actionBar.newTab().setTabListener(new SearchFragmentTab());
        // Create your own custom icon
        //tab.setIcon(R.drawable.tab1);
        tab.setText(getString(R.string.searchTabCaption));
        _actionBar.addTab(tab);

        // Create "Browse"  Tab
        tab = _actionBar.newTab().setTabListener(new BrowseFragmentTab());
        // Set Tab Title
        tab.setText(getString(R.string.browseTabCaption));
        _actionBar.addTab(tab);

        // Create "Results" Tab
        tab = _actionBar.newTab().setTabListener(new ResultsFragmentTab());
        // Set Tab Title
        tab.setText(getString(R.string.resultsTabCaption));
        _actionBar.addTab(tab);
    }


    public void fetchRandomSentence(RandomSentenceRequestModel request)
    {
        _randomSentenceRequest = request;
        _searchAction = SEARCH_ACTIONS.FETCH_RANDOM;
        _actionBar.setSelectedNavigationItem( MAIN_TABS.RESULTS.ordinal() );
    }

    public void fetchSentenceById()
    {
        // arrange the request
        _searchAction = SEARCH_ACTIONS.FETCH_BY_ID;
        _actionBar.setSelectedNavigationItem( MAIN_TABS.RESULTS.ordinal() );
    }

    public void searchForSentences()
    {
        // arrange the request
        _searchAction = SEARCH_ACTIONS.SEARCH;
        _actionBar.setSelectedNavigationItem( MAIN_TABS.RESULTS.ordinal() );
    }

    /**
     * Returns the latest search action requested.
     * The value is also erased after one access.
     * @return
     */
    public SEARCH_ACTIONS get_searchAction()
    {
        SEARCH_ACTIONS searchAction = _searchAction;
        _searchAction = null;
        return searchAction;
    }

    /**
     * Returns a model with the data for a random sentence request.
     * @return
     */
    public RandomSentenceRequestModel get_randomSentenceRequest()
    {
        return _randomSentenceRequest;
    }



}