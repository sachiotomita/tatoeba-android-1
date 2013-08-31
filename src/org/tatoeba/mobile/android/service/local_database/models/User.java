package org.tatoeba.mobile.android.service.local_database.models;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 14:41
 */

/**
 * The class represents one user.
 */
public class User
{
    private long _id;
    private int _userId;
    private String _name;
    private String _email;

    public long getId()
    {
        return _id;
    }

    public void setId(long id)
    {
        _id = id;
    }

    public int getUserId()
    {
        return _userId;
    }

    public void setUserId(int id)
    {
        _userId = id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail(String email)
    {
        _email = email;
    }


    @Override
    public String toString()
    {
        return "User{" +
                "_id=" + _id +
                ", _userId=" + _userId +
                ", _name='" + _name + '\'' +
                ", _email='" + _email + '\'' +
                '}';
    }
}
