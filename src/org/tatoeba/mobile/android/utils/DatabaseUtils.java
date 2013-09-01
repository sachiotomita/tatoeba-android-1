package org.tatoeba.mobile.android.utils;

import android.content.Context;
import org.tatoeba.mobile.android.service.local_database.TatoebaDBHelper;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 1.09.13
 * Time: 20:30
 */


public class DatabaseUtils
{

    /**
     * Checks if a database with a given name exists.
     * @param context
     * @param databaseName
     * @return true if the database exists, false otherwise.
     */
    public static boolean databaseExists(Context context, String databaseName)
    {
        File databaseFile = context.getDatabasePath(TatoebaDBHelper.DATABASE_NAME);
        return databaseFile.exists();
    }
}
