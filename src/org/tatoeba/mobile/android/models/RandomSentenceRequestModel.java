package org.tatoeba.mobile.android.models;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 22.09.13
 * Time: 17:13
 */


public class RandomSentenceRequestModel
{
    private String _srcLanguage;
    private int _numberOfResults;
    private String _targetLanguage;

    /**
     * What is the language to fetch the random results in.
     * @param language
     */
    public void set_sourceLanguage(String language)
    {
        _srcLanguage = language;
    }


    /**
     * What is the language to translate the random results into.
     * @param language
     */
    public void set_targetLanguage(String language)
    {
        _targetLanguage = language;
    }



    /**
     * How many random results to fetch.
     * @param numberOfResults
     */
    public void set_numberOfResults(int numberOfResults)
    {
        _numberOfResults = numberOfResults;
    }

    /**
     * How many random results to fetch.
     * @return
     */
    public int get_numberOfResults()
    {
        return _numberOfResults;
    }

    /**
     * What is the language to fetch the random results in.
     * @return
     */
    public String get_sourceLanguage()
    {
        return _srcLanguage;
    }

    /**
     * What is the language to translate the random results into.
     * @return
     */
    @SuppressWarnings("SpellCheckingInspection")
    public String get_targetLanguage()
    {
        return _targetLanguage;
    }


    @Override
    public String toString()
    {
        return "RandomSentenceRequestModel{" +
                "_srcLanguage='" + _srcLanguage + '\'' +
                ", _numberOfResults=" + _numberOfResults +
                '}';
    }
}
