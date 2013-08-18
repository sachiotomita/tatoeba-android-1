package org.tatoeba.mobile.android.models.tables;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 18.08.13
 * Time: 22:33
 */


public class Sentences extends SQLiteOpenHelper
{
    public Sentences(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler)
    {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
