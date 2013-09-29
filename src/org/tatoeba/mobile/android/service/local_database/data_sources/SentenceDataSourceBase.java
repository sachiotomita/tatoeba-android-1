package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.Context;
import android.database.Cursor;
import org.tatoeba.mobile.android.models.SentenceLinkModel;
import org.tatoeba.mobile.android.models.SentenceModel;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;
import org.tatoeba.mobile.android.service.local_database.tables.TableLinks;
import org.tatoeba.mobile.android.service.local_database.tables.TableSentences;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 29.09.13
 * Time: 21:07
 */


public abstract class SentenceDataSourceBase extends AbstractDataSource
{
    public SentenceDataSourceBase(Context context)
    {
        super(context);
    }

    /**
     * Identifies the original with the translations using links.
     * @param originals the original sentences
     * @param translations the translations of the original sentences
     * @param links the links between the originals and the translations
     * @return a collection of the translated senteces
     */
    protected ArrayList<TranslatedSentenceModel> link(SentenceModel[] originals, SentenceModel[] translations, SentenceLinkModel[] links)
    {

        /* Map translation IDs to the translations themselves: [translationID] => translation */
        HashMap<Integer,SentenceModel> translationID_translation = new HashMap<Integer,SentenceModel>();

        for (SentenceModel translation : translations)
        {
            translationID_translation.put(translation.getSentenceId(), translation);
        }

        // final collection of the translated sentences
        ArrayList<TranslatedSentenceModel> translatedSentences = new ArrayList<TranslatedSentenceModel>();

        // a single translated sentence (to be added to translatedSentences)
        TranslatedSentenceModel singleTranslatedSentence;

        // a list of translation IDs for a single original
        ArrayList<Integer> translationIDs;

        // a single translation
        SentenceModel singleTranslation;

        for (SentenceModel original : originals)
        {
            // save the original to the translated sentence
            singleTranslatedSentence = new TranslatedSentenceModel();
            singleTranslatedSentence.set_mainSentence(original);

            // get the list of the translation IDs, corresponding to the original
            translationIDs = getTranslationIDs(original.getSentenceId(), links);

            // get the translations according to the IDs. Use a previously prepared hash map.
            for (Integer translationID : translationIDs)
            {
                singleTranslation = translationID_translation.get(translationID);
                singleTranslatedSentence.addTranslation(singleTranslation);
            }

            // add the freshly populated translated sentence to the final collection
            translatedSentences.add(singleTranslatedSentence);
        }

        return translatedSentences;
    }

    /**
     * Fetches all the translation IDs from the povided links according to a provided original ID.
     * @param originalID id of an original sentence
     * @param links  collection of the links
     * @return
     */
    private ArrayList<Integer> getTranslationIDs(int originalID, SentenceLinkModel[] links)
    {

        ArrayList<Integer> results = new ArrayList<Integer>();

        for (SentenceLinkModel link : links)
        {
            if (link.getLeftId() == originalID)
                results.add( link.getRightId() );
        }

        return results;
    }


    protected SentenceModel cursorToSentence(Cursor cursor)
    {
        // find the column indexes
        int sentenceIdIndex =  cursor.getColumnIndex(TableSentences.COLUMN_SENTENCE_ID);
        int langIndex = cursor.getColumnIndex(TableSentences.COLUMN_LANGUAGE);
        int textIndex = cursor.getColumnIndex(TableSentences.COLUMN_TEXT);
        int ownerIdIndex = cursor.getColumnIndex(TableSentences.COLUMN_OWNER_ID);

        if (sentenceIdIndex == -1 || langIndex == -1 || textIndex == -1 || ownerIdIndex == -1)
            throw new Error("Columnt index not found!");

        SentenceModel sentence = new SentenceModel();
        sentence.setSentenceId(cursor.getInt( sentenceIdIndex ));
        sentence.setLanguage(cursor.getString( langIndex ));
        sentence.setText(cursor.getString(textIndex));
        sentence.setOwnerId(cursor.getInt(ownerIdIndex));
        return sentence;
    }

    /**
     * Extracts a link model from a given cursor.
     * @param cursor
     * @return
     */
    protected SentenceLinkModel cursorToLink(Cursor cursor)
    {
        // find the column indexes
        int leftLinkIndex =  cursor.getColumnIndex(TableLinks.COLUMN_LEFT_ID);
        int rightLinkIndex = cursor.getColumnIndex(TableLinks.COLUMN_RIGHT_ID);

        if (leftLinkIndex == -1 || rightLinkIndex == -1 )
            throw new Error("Columnt index not found!");

        SentenceLinkModel link = new SentenceLinkModel();
        link.setLeftId( cursor.getInt( leftLinkIndex ) );
        link.setRightId( cursor.getInt( rightLinkIndex) );

        return link;
    }


    /**
     * Converts the sentenceIDs into a string, suitable for an SQL " ... where ... in (id1, id2, ...)" request.
     * @param sentenceIDs
     * @return A string like "(id1, id2, id3, ...)"
     */
    protected String makeSQL_in_list(int[] sentenceIDs)
    {
        String sentenceIDsList = "(";
        for (int sentenceID : sentenceIDs)
        {
            sentenceIDsList += Integer.toString(sentenceID) + ",";
        }
        sentenceIDsList = sentenceIDsList.substring(0, sentenceIDsList.length() - 1) + ")";
        return sentenceIDsList;
    }

}
