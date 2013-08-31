package org.tatoeba.mobile.android.models.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 12:35
 */

/**
 * The class is intended to be a parent class for all the table classes.
 */
public abstract class AbstractTable
{
    private final static String UNDEFINED = "Unspecified! Must be set or overridden!";

    /** The system row id */
    protected final String COLUMN_ID = "_id";

    /** The table name */
    protected String _tableName = UNDEFINED;

    /** The table creation sql statement */
    protected String _SQLCreateTable = UNDEFINED;

    /** The table Upgrade sql statement */
    protected String _SQLUpgradeTable = UNDEFINED;

    /** Prepares the SQL statement (_SQLCreateTable) for creating the table */
    protected abstract void prepareSqlCreateTable();

    /** Prepares the SQL statement (_SQLUpgradeTable) for upgrading the table */
    protected abstract void prepareSqlUpgradeTable();

    protected AbstractTable(String tableName)
    {
        _tableName = tableName;
    }

    /** Returns an SQL statement for creating the table. */
    public String onCreate()
    {
        checkTableName();
        prepareSqlCreateTable();
        return _SQLCreateTable;
    }

    /** Returns an SQL statement for upgrading the table. */
    public String onUpgrade()
    {
        checkTableName();
        prepareSqlUpgradeTable();
        return _SQLUpgradeTable;
    }

    private void checkTableName()
    {
        if (_tableName.equals(UNDEFINED))
            throw new AbstractMethodError("Table name not specified!");
    }
    /** Returns the table name */
    public String get_tableName()
    {
        return _tableName;
    }

}