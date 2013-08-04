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

public class WelcomeActivity extends Activity {
    // Declare Tab Variable
    Tab tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the actionbar
        ActionBar actionBar = getActionBar();

        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);

        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);

        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create first Tab
        tab = actionBar.newTab().setTabListener(new SearchFragmentTab());
        // Create your own custom icon
        //tab.setIcon(R.drawable.tab1);
        tab.setText(getString(R.string.searchTabCaption));
        actionBar.addTab(tab);

        // Create Second Tab
        tab = actionBar.newTab().setTabListener(new BrowseFragmentTab());
        // Set Tab Title
        tab.setText(getString(R.string.browseTabCaption));
        actionBar.addTab(tab);


    }
}