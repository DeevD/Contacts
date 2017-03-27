package com.example.heinhtet.contacts.data;

import android.provider.BaseColumns;

/**
 * Created by heinhtet on 3/13/17.
 */

public class ContactContract implements BaseColumns
{
    public static final String TABLE_NAME = "contact";
    public static final String ID = BaseColumns._ID ;
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String NOTE = "note";
    public static final String TIME = "time";
    public static final String IMAGE = "image";



    public static final String TABLE_NAME_FAV = "fav_contact";
    public static final String ID_FAV = BaseColumns._ID ;
    public static final String NAME_FAV = "name";
    public static final String PHONE_FAV = "phone";
    public static final String EMAIL_FAV = "email";
    public static final String NOTE_FAV = "note";
    public static final String TIME_FAV = "time";




    public ContactContract()
    {

    }
}
