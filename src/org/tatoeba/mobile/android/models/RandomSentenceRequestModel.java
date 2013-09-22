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
    private String _language;
    private int _numberOfResults;

    /**
     * What is the language to fetch the random results in.
     * @param language
     */
    public void set_language(String language)
    {
        _language = language;
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
    public String get_language()
    {
        return _language;
    }

    @Override
    public String toString()
    {
        return "RandomSentenceRequestModel{" +
                "_language='" + _language + '\'' +
                ", _numberOfResults=" + _numberOfResults +
                '}';
    }
}
