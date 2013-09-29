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
public class SentenceLinkModel
{
    private long _id;
    private int _leftSentenceID;
    private int _rightSentenceID;

    public long getId()
    {
        return _id;
    }

    public void setId(long id)
    {
        _id = id;
    }

    /**
     * Set the ID of the left sentence (the original)
     * @param sentenceID
     */
    public void setLeftId(int sentenceID)
    {
        _leftSentenceID = sentenceID;
    }

    /**
     * Set the ID of the right sentence (the translation)
     * @param sentenceID
     */
    public void setRightId(int sentenceID)
    {
        _rightSentenceID = sentenceID;
    }

    /**
     * ID of the left sentence (the original)
     * @return
     */
    public int getLeftId()
    {
        return _leftSentenceID;
    }

    /**
     * ID of the right sentence (the translation)
     * @return
     */
    public int getRightId()
    {
        return _rightSentenceID;
    }

    @Override
    public String toString()
    {
        return "SentenceLink{" +
                "_id=" + _id +
                ", _leftSentenceID=" + _leftSentenceID +
                ", _rightSentenceID=" + _rightSentenceID +
                '}';
    }
}
