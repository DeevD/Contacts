package com.example.heinhtet.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.heinhtet.contacts.data.ContactDBHelper;

import java.io.ByteArrayOutputStream;

/**
 * Created by heinhtet on 3/13/17.
 */
public class InsertActivity extends AppCompatActivity {
    ContactDBHelper mDB;
    ImageView blob_img;
    EditText name, email, phone, note;
    android.text.format.Time today = new android.text.format.Time(android.text.format.Time.getCurrentTimezone());
    Bitmap bitmapImage;
    byte imageByte[];
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);

        name = (EditText) findViewById(R.id.name_edit);
        phone = (EditText) findViewById(R.id.ph);
        email = (EditText) findViewById(R.id.edit_email);
        note = (EditText) findViewById(R.id.edit_note);
        blob_img = (ImageView) findViewById(R.id.blob_image);
          /*
            nothing getting form storage file putting have resources
             */
        bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.email);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        imageByte = outputStream.toByteArray();
        blob_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInternal();
            }
        });

    }

    private void getInternal() {

//        getting from memory card pic
        Intent getImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(getImage, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {

            Uri selectedImg = data.getData();

            String[] FILE_COLUNM = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImg, FILE_COLUNM, null, null, null);

            cursor.moveToFirst();



            int column_index = cursor.getColumnIndex(FILE_COLUNM[0]);

            String picture_PATH = cursor.getString(column_index);

            /*
            converting imagepath into bitmap_image
             */
            bitmapImage = BitmapFactory.decodeFile(picture_PATH);
           // Bitmap bitmap = Bitmap.createBitmap(bitmapImage,)
            /*
            setting bitmap to image_view
             */

            blob_img.setImageBitmap(bitmapImage);


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.insert_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())

        {
            case R.id.done: {

                mDB = new ContactDBHelper(this);
                SQLiteDatabase db = mDB.getWritableDatabase();
                String Insert_name = name.getText().toString().trim();

                if (Insert_name.length() == 0) {
                    Insert_name = "No Name Enter Name";
                }

                String Insert_phone = phone.getText().toString().trim();

                if (Insert_phone.length() == 0) {
                    Insert_phone = "No Phone Enter Ph No:";
                }

                String Insert_email = email.getText().toString().trim();

                if (Insert_email.length() == 0) {
                    Insert_email = "No email Enter Email No:";
                }

                String Insert_note = note.getText().toString().trim();

                if (Insert_note.length() == 0) {
                    Insert_note = "No Note for this Contact";
                }




//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//
//                imageByte = outputStream.toByteArray();


                /*
                inserting to database with image_byte_array
                 */
                mDB.insert(Insert_name, Insert_phone, Insert_email, Insert_note,imageViewToByte(blob_img));
                blob_img.setImageResource(R.drawable.email);

                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private byte[] imageViewToByte(ImageView img) {
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        /*
        converting to byte array image
         */
        byte[] bytesArry = outputStream.toByteArray();
        return bytesArry;
    }
}
