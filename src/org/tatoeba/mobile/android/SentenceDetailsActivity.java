package org.tatoeba.mobile.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import org.tatoeba.mobile.android.enums.INTENT_EXTRAS;
import org.tatoeba.mobile.android.models.SentenceModel;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;
import org.tatoeba.mobile.android.views.search_result.TranslationAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 15.09.13
 * Time: 15:24
 */


public class SentenceDetailsActivity extends Activity
{

    private TextView _mainSentenceNumberTV;
    private TextView _mainSentenceTV;
    private ListView _translatedSentencesLV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentence_details);

        _mainSentenceNumberTV = (TextView)findViewById(R.id.main_sentence_number);
        _mainSentenceTV = (TextView)findViewById(R.id.main_sentence);
        _translatedSentencesLV = (ListView)findViewById(R.id.translationList);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        TatoebaApp appState = ((TatoebaApp) getApplicationContext());
        ArrayList<TranslatedSentenceModel> currentTranslations = appState.getCurrentTranslations();

        Intent details = getIntent();
        int translationPosition = details.getIntExtra( INTENT_EXTRAS.CURRENT_TRANSLATION_POSITION.name(), -1 );

        // Display main sentence
        TranslatedSentenceModel translatedSentence = currentTranslations.get(translationPosition);
        SentenceModel mainSentence = translatedSentence.get_mainSentence();

        String sentenceNumber = Integer.toString(mainSentence.getSentenceId());
        _mainSentenceNumberTV.setText(_mainSentenceNumberTV.getText() + sentenceNumber);

        _mainSentenceTV.setText( mainSentence.getText() );

        // Display translations
        ArrayList<SentenceModel> translations = translatedSentence.get_translations();
        TranslationAdapter adapter = new TranslationAdapter(this, translations);
        _translatedSentencesLV.setAdapter(adapter);

    }
}
