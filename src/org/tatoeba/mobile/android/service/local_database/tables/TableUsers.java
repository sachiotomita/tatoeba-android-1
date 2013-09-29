package org.tatoeba.mobile.android.service.local_database.tables;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 18.08.13
 * Time: 22:33
 */

public class TableUsers
{
    /** system row id. */
    public static final String COLUMN_ID = "_id";

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_EMAIL = "email";

    public static final String[] ALL_COLUMNS = {COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_EMAIL};

    // Database creation sql statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USER_ID + " integer not null unique, "
            + COLUMN_USER_NAME + " text not null, "
            + COLUMN_EMAIL + " text not null);";

    private static final String TABLE_UPGRADE = "";

    /** Returns an SQL statement for creating the table. */
    public static String onCreate()
    {
        return TABLE_CREATE;
    }

    /**
     * Returns {tableName.col1, tableName.col2, ... }
     * @return
     */
    public static  String[] getFullyQualifiedColumnNames()
    {
        return  TableHelper.getFullyQualifiedColumnNames(TABLE_NAME,ALL_COLUMNS);
    }

    /** Returns an SQL statement for upgrading the table. */
    public static String onUpgrade()
    {
        throw new Error("Not implemented!");
        //return TABLE_UPGRADE;
    }

}
