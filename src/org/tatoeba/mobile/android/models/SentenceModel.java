package org.tatoeba.mobile.android.models;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 14:41
 */

/**
 * The class represents one user.
 */
public class SentenceModel
{
    private long _id;
    private int _sentenceID;
    private String _language;
    private String _text;
    private int _ownerID;

    public long getId()
    {
        return _id;
    }

    public void setId(long id)
    {
        _id = id;
    }

    /**
     * Set the ID of the sentence.
     * @param sentenceID
     */
    public void setSentenceId(int sentenceID)
    {
        _sentenceID = sentenceID;
    }

    /**
     * Get the ID of the sentence.
     * @return
     */
    public int getSentenceId()
    {
        return _sentenceID;
    }

    /**
     * Set the ID of the user, who owns the sentence.
     * @param userID
     */
    public void setOwnerId(int userID)
    {
        _ownerID = userID;
    }

    /**
     * Get the ID of the user, who owns the sentence.
     * @return
     */
    public int getOwnerId()
    {
        return _ownerID;
    }


    /**
     * Set the language of the sentence.
     * @param lang
     */
    public void setLanguage(String lang)
    {
        _language = lang;
    }

    /**
     * Get the language of the sentence.
     * @return
     */
    public String getLanguage()
    {
        return _language;
    }

    /**
     * Set the text of the sentence.
     * @param text
     */
    public void setText(String text)
    {
        _text = text;
    }

    /**
     * Get the text of the sentence.
     * @return
     */
    public String getText()
    {
        return _text;
    }

    @Override
    public String toString()
    {
        return "Sentence{" +
                "_id=" + _id +
                ", _sentenceID=" + _sentenceID +
                ", _language='" + _language + '\'' +
                ", _text='" + _text + '\'' +
                ", _ownerID=" + _ownerID +
                '}';
    }
}
