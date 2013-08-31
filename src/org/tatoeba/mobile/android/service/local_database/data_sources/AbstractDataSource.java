package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.tatoeba.mobile.android.service.local_database.TatoebaDBHelper;
import org.tatoeba.mobile.android.service.local_database.tables.TableLinks;
import org.tatoeba.mobile.android.service.local_database.tables.TableUsers;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 16:08
 */


public class AbstractDataSource
{

    private static SQLiteDatabase static_db;
    private static TatoebaDBHelper static_db_helper;

    protected SQLiteDatabase database;
    protected TatoebaDBHelper dbHelper;
    protected String[] allColumns;

    public AbstractDataSource(Context context)
    {
        //This is kind of a quick and dirty solution.
        // In order to save resources we use one static access to the DB here.
        // This shall probably be refactored.
        if (static_db_helper == null)
        {
            static_db_helper = new TatoebaDBHelper(context);
            dbHelper = static_db_helper;
        }
    }

    /** Open  the DB session */
    public void open() throws SQLException
    {
        // static DB already opened. Nothing to do.
        if (static_db != null)
        {
            Log.w("###", "DB is already opened!");
            return;
        }
        static_db = dbHelper.getWritableDatabase();
        database = static_db;
    }

    /** Close the DB session */
    public void close()
    {
        // static DB already closed. Nothing to do.
        if (static_db == null)
        {
            Log.w("###", "DB is already closed!");
            return;
        }
        dbHelper.close();
        static_db = null;
    }

    /** Check if the DB has been opened. Thrown an error if not. */
    protected void checkDataBase()
    {
        if (static_db == null)
            throw new Error("Database has not been opened for writing!");

        else if (database == null)
            database = static_db;
   }
}
