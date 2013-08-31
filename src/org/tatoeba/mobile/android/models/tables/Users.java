package org.tatoeba.mobile.android.models.tables;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 18.08.13
 * Time: 22:33
 */

public class Users extends AbstractTable
{

    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_EMAIL = "email";

    protected Users()
    {
        super("users");
    }

    @Override
    protected void prepareSqlCreateTable()
    {
        _SQLCreateTable = "create table "
                + _tableName + "( " + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_USER_ID + " integer not null, "
                + COLUMN_USER_NAME + " text not null, "
                + COLUMN_EMAIL + " text not null);";
    }

    @Override
    protected void prepareSqlUpgradeTable()
    {
        throw new Error("Not implemented yet!");
    }
}
