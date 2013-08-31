package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import org.tatoeba.mobile.android.service.local_database.models.SentenceLink;
import org.tatoeba.mobile.android.service.local_database.models.User;
import org.tatoeba.mobile.android.service.local_database.tables.TableLinks;
import org.tatoeba.mobile.android.service.local_database.tables.TableUsers;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 13:31
 */


public class SentenceLinksDataSource extends AbstractDataSource
{

    public SentenceLinksDataSource(Context context)
    {
        super(context);
        allColumns = TableLinks.ALL_COLUMNS;
    }

    /***
     * Adds a new sentence link  to the DB, reads it and returns it (re-check!)
     */
    public SentenceLink createLink(int leftID, int rightID)
    {
        checkDataBase();

        ContentValues values = new ContentValues();

        values.put(TableLinks.COLUMN_LEFT_ID, leftID);
        values.put(TableLinks.COLUMN_RIGHT_ID, rightID);

        long insertId = database.insert(TableLinks.TABLE_NAME, null, values);

        Cursor cursor = database.query(TableLinks.TABLE_NAME,
                allColumns, TableLinks.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        SentenceLink newLink = cursorToLink(cursor);
        cursor.close();
        return newLink;


    }

    private SentenceLink cursorToLink(Cursor cursor)
    {
        SentenceLink link = new SentenceLink();
        link.setLeftId(cursor.getInt(0));
        link.setRightId(cursor.getInt(1));
        return link;

    }

}
