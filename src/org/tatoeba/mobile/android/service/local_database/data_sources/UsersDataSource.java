package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import org.tatoeba.mobile.android.models.UserModel;
import org.tatoeba.mobile.android.service.local_database.tables.TableUsers;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 13:31
 */

public class UsersDataSource extends AbstractDataSource
{
    public UsersDataSource(Context context)
    {
        super(context);
        allColumns = TableUsers.ALL_COLUMNS;
    }

    /***
     * Adds a new user to the DB, reads it and returns it (re-check!)
     */
    public UserModel createUser(int userId, String userName, String email)
    {
        checkDataBase();

        ContentValues values = new ContentValues();

        values.put(TableUsers.COLUMN_USER_ID, userId);
        values.put(TableUsers.COLUMN_USER_NAME, userName);
        values.put(TableUsers.COLUMN_EMAIL, email);

        long insertId = database.insert(TableUsers.TABLE_NAME, null, values);

        Cursor cursor = database.query(TableUsers.TABLE_NAME,
                allColumns, TableUsers.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        UserModel newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    private UserModel cursorToUser(Cursor cursor)
    {
        UserModel user = new UserModel();
        int userId = cursor.getInt(0);
        user.setUserId(userId);
        user.setName(cursor.getString(1));
        user.setEmail(cursor.getString(2));

        return user;

    }

}
