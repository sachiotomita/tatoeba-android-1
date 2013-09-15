package org.tatoeba.mobile.android;

import android.app.Application;
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

}
