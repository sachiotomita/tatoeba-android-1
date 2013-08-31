package org.tatoeba.mobile.android.service.local_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.tatoeba.mobile.android.service.local_database.tables.TableLinks;
import org.tatoeba.mobile.android.service.local_database.tables.TableSentences;
import org.tatoeba.mobile.android.service.local_database.tables.TableUsers;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 18.08.13
 * Time: 22:33
 */

public class TatoebaDBHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "tatoeba.db";
    protected static final int DATABASE_VERSION = 1;

    //public Users(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler)
    public TatoebaDBHelper(Context context)
    {
        //super(context, name, factory, version, errorHandler);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        Log.d("###", "Users/onCreate() called!");
        database.execSQL( TableUsers.onCreate() );
        database.execSQL( TableLinks.onCreate() );
        database.execSQL(TableSentences.onCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {

        Log.d("###", "Users/onUpgrade() called!");
        database.execSQL( TableUsers.onUpgrade() );
        /*
        Log.w(TatoebaDBHelper.class.getName(),
                "Upgrading db " + DATABASE_NAME + " from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
        */
    }
}
