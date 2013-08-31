package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import org.tatoeba.mobile.android.service.local_database.models.Sentence;
import org.tatoeba.mobile.android.service.local_database.models.SentenceLink;
import org.tatoeba.mobile.android.service.local_database.tables.TableSentences;
import org.tatoeba.mobile.android.service.local_database.tables.TableSentences;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 13:31
 */


public class SentenceDataSource extends AbstractDataSource
{

    public SentenceDataSource(Context context)
    {
        super(context);
        allColumns = TableSentences.ALL_COLUMNS;
    }

    /***
     * Adds a new sentence link  to the DB, reads it and returns it (re-check!)
     */
    public Sentence createSentence(int sentenceId, String language, String text, int ownerId)
    {
        checkDataBase();

        ContentValues values = new ContentValues();
        //5966	nld	Ik moet gaan slapen.	17
        
        values.put(TableSentences.COLUMN_SENTENCE_ID, sentenceId);
        values.put(TableSentences.COLUMN_LANGUAGE, language);
        values.put(TableSentences.COLUMN_TEXT, text);
        values.put(TableSentences.COLUMN_OWNER_ID, ownerId);

        long insertId = database.insert(TableSentences.TABLE_NAME, null, values);

        Cursor cursor = database.query(TableSentences.TABLE_NAME,
                allColumns, TableSentences.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Sentence sentence = cursorToSentence(cursor);
        cursor.close();
        return sentence;
    }

    private Sentence cursorToSentence(Cursor cursor)
    {
        Sentence sentence = new Sentence();
        sentence.setSentenceId(cursor.getInt(0));
        sentence.setLanguage(cursor.getString(1));
        sentence.setText(cursor.getString(2));
        sentence.setOwnerId(cursor.getInt(3));
        return sentence;
    }

}
