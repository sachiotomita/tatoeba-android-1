package org.tatoeba.mobile.android.service;


import org.tatoeba.mobile.android.models.TranslatedSentenceModel;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 28.09.13
 * Time: 14:38
 */


public interface ITatoebaDBCallbackAPI
{
    /**
     * Set the result of fetching a random sentence.
     * @param sentences
     */
    //void setRandomFetchResult(TranslatedSentenceModel sentence);
    void setRandomFetchResult(ArrayList<TranslatedSentenceModel> sentences);

}
