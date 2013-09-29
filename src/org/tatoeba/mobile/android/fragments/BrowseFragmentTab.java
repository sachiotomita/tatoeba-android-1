package org.tatoeba.mobile.android.fragments;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 3.08.13
 * Time: 23:15
 */


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.models.RandomSentenceRequestModel;

public class BrowseFragmentTab extends TatoebaMainFragment implements ActionBar.TabListener
{

    private EditText _sentenceIdBox;
    protected boolean _firstTouch = true;
    private Button _searchButton;
    private Button _randomFetchButton;
    private Spinner _randomResultsNumberSpinner;
    private Spinner _randomSrcLangSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Get the view from browse_fragmentagment.xml
        _activity.setContentView(R.layout.browse_fragment);

        initialize();
    }

    private void initialize()
    {

        // SENTENCE BY ID

        _sentenceIdBox = (EditText) _activity.findViewById(R.id.sentenceIdInputBox);
        _searchButton = (Button) _activity.findViewById(R.id.searchButton);
        _searchButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // nothing to do if the sentence ID is empty
                if (_sentenceIdBox.length() == 0)
                {
                    _sentenceIdBox.setFocusableInTouchMode(true);
                    _sentenceIdBox.requestFocus();
                    return;
                }
                fetchById();
            }
        });

        // RANDOM SENTENCE

        _randomSrcLangSpinner = (Spinner) _activity.findViewById(R.id.randomSrcLangSpinner);
        _randomResultsNumberSpinner = (Spinner) _activity.findViewById(R.id.randomResultsNumberSpinner);

        _randomFetchButton = (Button) _activity.findViewById(R.id.randomFetchButton);
        _randomFetchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fetchRandomSentence();
            }
        });
    }

    private void fetchRandomSentence()
    {
        RandomSentenceRequestModel request = new RandomSentenceRequestModel();

        String selectedLanguage = _randomSrcLangSpinner.getSelectedItem().toString();
        int selectedAmount = Integer.parseInt( _randomResultsNumberSpinner.getSelectedItem().toString() );

        request.set_sourceLanguage(selectedLanguage);

        // TODO: #makeDestinationLanguageWork: read the destination language from the dest. spinner/
        request.set_targetLanguage("eng");

        request.set_numberOfResults(selectedAmount);

        Log.d("###", "Fetch random sentence, request="+request);

        _activity.fetchRandomSentence(request);
    }



    private void fetchById()
    {
        //Add a FetchByIdRequestModel instance request here
        _activity.fetchSentenceById();
    }

    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {
        // TODO Auto-generated method stub
        mFragment = new BrowseFragmentTab();
        // Attach browse_fragmentagment.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft)
    {
        // TODO Auto-generated method stub
        // Remove browse_fragment.xmlnt.xml layout
        ft.remove(mFragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft)
    {
        // TODO Auto-generated method stub

    }

}