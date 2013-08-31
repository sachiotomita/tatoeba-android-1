package org.tatoeba.mobile.android.service.local_database.tables;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 18.08.13
 * Time: 22:33
 */

public class TableLinks
{
    /** system row id. */
    public static final String COLUMN_ID = "_id";

    public static final String TABLE_NAME = "links";
    public static final String COLUMN_LEFT_ID = "leftLink";
    public static final String COLUMN_RIGHT_ID = "rightLink";

    public static final String[] ALL_COLUMNS = {COLUMN_LEFT_ID, COLUMN_RIGHT_ID};


    // Database creation sql statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LEFT_ID + " integer not null, "
            + COLUMN_RIGHT_ID + " integer not null);";

    private static final String TABLE_UPGRADE = "";

    /** Returns an SQL statement for creating the table. */
    public static String onCreate()
    {
        return TABLE_CREATE;
    }

    /** Returns an SQL statement for upgrading the table. */
    public static String onUpgrade()
    {
        throw new Error("Not implemented!");
        //return TABLE_UPGRADE;
    }
}
