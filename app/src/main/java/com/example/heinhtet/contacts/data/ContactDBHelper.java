package com.example.heinhtet.contacts.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.heinhtet.contacts.Contact;
import com.example.heinhtet.contacts.ContactFav;

import java.util.ArrayList;

/**
 * Created by heinhtet on 3/13/17.
 */

public class ContactDBHelper extends SQLiteOpenHelper {
    Context mContext;

    android.text.format.Time today = new android.text.format.Time(android.text.format.Time.getCurrentTimezone());

    SQLiteDatabase sqLiteDatabase;

    public static final String DB_NAME = "contact.db";
    public static final int DB_VERSION = 1;

    public ContactDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CONTACT_CREATE = "CREATE TABLE " + ContactContract.TABLE_NAME + "("
                + ContactContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ContactContract.NAME + " TEXT NOT NULL, "
                + ContactContract.PHONE + " TEXT, "
                + ContactContract.EMAIL + " TEXT, "
                + ContactContract.NOTE + " TEXT ,"
                + ContactContract.TIME + " TEXT ,"
                + ContactContract.IMAGE + " BLOB );";

        db.execSQL(SQL_CONTACT_CREATE);

        String SQL_CONTACT_CREATE_FAV = "CREATE TABLE " + ContactContract.TABLE_NAME_FAV + "("
                + ContactContract.ID_FAV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ContactContract.NAME_FAV + " TEXT NOT NULL, "
                + ContactContract.PHONE_FAV + " TEXT, "
                + ContactContract.EMAIL_FAV + " TEXT, "
                + ContactContract.NOTE_FAV + " TEXT ,"
                + ContactContract.TIME_FAV + " TEXT );";
        db.execSQL(SQL_CONTACT_CREATE_FAV);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS ");
    }

    public ArrayList<Contact> Get_ALL()



    {
        Cursor cursor = null;
        ArrayList<Contact> list = new ArrayList<>();

        String Select_Query = " SELECT * FROM " + ContactContract.TABLE_NAME;

        sqLiteDatabase = this.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(Select_Query, null);

        int COLUMN_ID = cursor.getColumnIndex(ContactContract._ID);
        int COLUMN_NAME = cursor.getColumnIndex(ContactContract.NAME);
        int COLUMN_PHONE = cursor.getColumnIndex(ContactContract.PHONE);
        int COLUMN_EMAIL = cursor.getColumnIndex(ContactContract.EMAIL);
        int COLUMN_NOTE = cursor.getColumnIndex(ContactContract.NOTE);
        int COLUMN_TIME = cursor.getColumnIndex(ContactContract.TIME);
        int COLUMN_IMAGE = cursor.getColumnIndex(ContactContract.IMAGE);
//
//        try {
//            while (cursor.moveToNext()) {
//                Contact contact = new Contact();
//                contact.setId(cursor.getInt(COLUMN_ID));
//                contact.setName(cursor.getString(COLUMN_NAME));
//                contact.setPhone(cursor.getString(COLUMN_PHONE));
//                contact.setEmail(cursor.getString(COLUMN_EMAIL));
//                contact.setNote(cursor.getString(COLUMN_NOTE));
//                contact.setTime(cursor.getString(COLUMN_TIME));
//                contact.setIamge(cursor.getBlob(COLUMN_IMAGE));
//                list.add(contact);
//
//            }
//        } finally {
//            cursor.close();
//        }

        if (cursor.moveToFirst())
        {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(COLUMN_ID));
                contact.setName(cursor.getString(COLUMN_NAME));
                contact.setPhone(cursor.getString(COLUMN_PHONE));
                contact.setEmail(cursor.getString(COLUMN_EMAIL));
                contact.setNote(cursor.getString(COLUMN_NOTE));
                contact.setTime(cursor.getString(COLUMN_TIME));
                contact.setIamge(cursor.getBlob(COLUMN_IMAGE));
                list.add(contact);
            }while (cursor.moveToNext());
        }

        return list;

    }

    public void insert(String name, String phone, String email, String note,byte image[])

    {
        today.setToNow();

//        String delete_image = " SELECT * FROM " + ContactContract.TABLE_NAME + "where" + ContactContract.IMAGE;
//        sqLiteDatabase.execSQL(delete_image);

        sqLiteDatabase = this.getWritableDatabase();
        String timeStamp = today.format("%Y-%m-%d %H:%M:%S");
        ContentValues values = new ContentValues();
        values.put(ContactContract.NAME, name);
        values.put(ContactContract.PHONE, phone);
        values.put(ContactContract.EMAIL, email);
        values.put(ContactContract.NOTE, note);
        values.put(ContactContract.TIME, timeStamp);
        values.put(ContactContract.IMAGE,image);

        long row_ID = sqLiteDatabase.insert(ContactContract.TABLE_NAME, null, values);

        if (row_ID == -1) {
            displayTost("Can't Inserting Contact");
        } else {
            displayTost("Success Adding Contact" );
        }
        sqLiteDatabase.close();


    }


    public void displayTost(String message)

    {
        Toast.makeText(mContext.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void update_contact(int id, String name, String phone, String email, String note) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactContract.NAME, name);
        values.put(ContactContract.PHONE, phone);
        values.put(ContactContract.EMAIL, email);
        values.put(ContactContract.NOTE, note);

        sqLiteDatabase.update(ContactContract.TABLE_NAME, values, ContactContract._ID + "=" + id, null);
    }

    public void delete_contact(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(ContactContract.TABLE_NAME, ContactContract._ID + "=" + id, null);
    }

    public void delete_all_row ()
    {
        sqLiteDatabase = this.getWritableDatabase();
        String SELECT = " SELECT * FROM " + ContactContract.TABLE_NAME;
        Cursor c=  sqLiteDatabase.rawQuery(SELECT,null);

        int COL_ID = c.getColumnIndexOrThrow(ContactContract._ID);

        try {
            while (c.moveToNext())
            {
                delete_contact(c.getInt(COL_ID));
            }
        }
        finally {
            c.close();
        }

    }


    public void insert_fav( String name, String phone, String email, String note,String time) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactContract.NAME_FAV, name);
        values.put(ContactContract.PHONE_FAV, phone);
        values.put(ContactContract.EMAIL_FAV, email);
        values.put(ContactContract.NOTE_FAV, note);
        values.put(ContactContract.TIME_FAV,time);

        long row_ID = sqLiteDatabase.insert(ContactContract.TABLE_NAME_FAV, null, values);

        if (row_ID == -1) {
            displayTost("Can't Inserting Contact");
        } else {
            displayTost("Success Adding Favorites Contact " );
        }


    }


    public ArrayList<ContactFav> get_all_list() {
        ArrayList<ContactFav> list = new ArrayList<>();
        String get_SQL = "SELECT * FROM " + ContactContract.TABLE_NAME_FAV;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(get_SQL, null);

        int COLUMN_ID = cursor.getColumnIndex(ContactContract.ID_FAV);
        int COLUMN_NAME = cursor.getColumnIndex(ContactContract.NAME_FAV);
        int COLUMN_PHONE = cursor.getColumnIndex(ContactContract.PHONE_FAV);
        int COLUMN_EMAIL = cursor.getColumnIndex(ContactContract.EMAIL_FAV);
        int COLUMN_Note = cursor.getColumnIndex(ContactContract.NOTE_FAV);
        int COLUMN_TIME = cursor.getColumnIndex(ContactContract.TIME_FAV);


        try {

            while (cursor.moveToNext()) {

                ContactFav contactFav = new ContactFav();
                contactFav.setId(cursor.getInt(COLUMN_ID));
                contactFav.setName(cursor.getString(COLUMN_NAME));
                contactFav.setPhone(cursor.getString(COLUMN_PHONE));
                contactFav.setEmail(cursor.getString(COLUMN_EMAIL));
                contactFav.setNote(cursor.getString(COLUMN_Note));
                contactFav.setTime(cursor.getString(COLUMN_TIME));
                list.add(contactFav);

            }


        } finally
        {
            cursor.close();
        }
        return list;
    }





    public void delete_contact_fav(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(ContactContract.TABLE_NAME_FAV,ContactContract.ID_FAV+"="+id,null);


    }
}

