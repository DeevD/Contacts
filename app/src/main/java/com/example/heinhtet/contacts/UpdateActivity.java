package com.example.heinhtet.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.heinhtet.contacts.data.ContactDBHelper;

/**
 * Created by heinhtet on 3/13/17.
 */

public class UpdateActivity extends AppCompatActivity {
    EditText name , phone, email,note;
    ContactDBHelper mdb ;
    Contact contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Intent i = getIntent();
        contact = (Contact) i.getSerializableExtra("contact");

        name = (EditText) findViewById(R.id.edit_name_detail);
        phone = (EditText) findViewById(R.id.edit_detail_phone);


        email = (EditText) findViewById(R.id.edit_email_detail);

        note = (EditText) findViewById(R.id.edit_note_detail);

        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
        note .setText(contact.getNote());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.update:
            {

                String name_up = name.getText().toString();
                String phone_up = phone.getText().toString();
                String email_up = email.getText().toString();
                String note_up = note.getText().toString();
                mdb = new ContactDBHelper(this);
                mdb.update_contact((int) contact.getId(),name_up,phone_up,email_up,note_up);
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
                finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }
}
