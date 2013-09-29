package org.tatoeba.mobile.android;

import android.app.Application;
import android.widget.Toast;
import org.tatoeba.mobile.android.fragments.enums.TOAST_MESSAGES;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 15.09.13
 * Time: 21:52
 */


public class TatoebaApp extends Application
{


    private ArrayList<TranslatedSentenceModel> _currentTranslations;

    /**
     * Get the currently available translations
     * @return array of translated sentences
     */
    public ArrayList<TranslatedSentenceModel> getCurrentTranslations()
    {
        return _currentTranslations;
    }

    /**
     * Set the currently available translations
     * @param translations
     */
    public void setCurrentTranslations(ArrayList<TranslatedSentenceModel> translations)
    {
        _currentTranslations = translations;
    }

    /**
     * Shows a quick pre-defined toast (Toast.LENGTH_SHORT)
     * @param message Message type
     * @param message
     */
    public void showToast(TOAST_MESSAGES message)
    {
        showToast(message, Toast.LENGTH_LONG);
    }

    /**
     * Shows a pre-defined toast.
     * @param message Message type
     * @param duration Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    public void showToast(TOAST_MESSAGES message, int duration)
    {
        if (duration> 1 || duration<0) throw new Error("Wront toast duration given!");

        String msgText;
        switch (message)
        {
            case SENTENCE_NOT_FOUND :  msgText = getString(R.string.toast_sentenceNotFound); break;
            case SENTENCES_NOT_FOUND : msgText = getString(R.string.toast_sentencesNotFound); break;
            case RANDOM_SENTENCES_NOT_FOUND : msgText = getString(R.string.toast_randomSentencesNotFound); break;

            default: throw new Error("Invalid message given!");
        }
        Toast toast = Toast.makeText(getBaseContext(), msgText, duration);
        toast.show();
    }

}
