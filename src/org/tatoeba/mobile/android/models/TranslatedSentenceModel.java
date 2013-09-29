package org.tatoeba.mobile.android.models;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 7.09.13
 * Time: 17:11
 */


public class TranslatedSentenceModel
{

    private SentenceModel _mainSentence;
    private ArrayList<SentenceModel> _translations;

    public TranslatedSentenceModel()
    {
    }

    public TranslatedSentenceModel(SentenceModel mainSentence, ArrayList<SentenceModel> translations)
    {
        _mainSentence = mainSentence;
        _translations = translations;
    }


    public void set_mainSentence(SentenceModel _mainSentence)
    {
        this._mainSentence = _mainSentence;
    }

    public void set_translations(ArrayList<SentenceModel> _translations)
    {
        this._translations = _translations;
    }

    public void addTranslation(SentenceModel translation)
    {
        if (_translations == null) _translations = new ArrayList<SentenceModel>();
        _translations.add(translation);
    }

    /**
     * The original sentence, which was translated.
     * @return
     */
    public SentenceModel get_mainSentence()
    {
        return _mainSentence;
    }

    public ArrayList<SentenceModel> get_translations()
    {
        return _translations;
    }

    @Override
    public String toString()
    {
        return "TranslatedSentenceModel{" +
                "_mainSentence=" + _mainSentence +
                ", _translations=" + _translations +
                '}';
    }
}
