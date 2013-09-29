package org.tatoeba.mobile.android.service.local_database.tables;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 29.09.13
 * Time: 14:30
 */

public abstract class TableHelper
{
    /**
     * Returns {tableName.col1, tableName.col2, ... }
     * @return
     */
    public static  String[] getFullyQualifiedColumnNames(String tableName, String[] allColumns)
    {
        String result[] = new String[allColumns.length];
        for (int i = 0; i < allColumns.length; i++)
        {
            result[i] = tableName + "." + allColumns[i];
        }

        return result;
    }

}
