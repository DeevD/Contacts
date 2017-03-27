package com.example.heinhtet.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heinhtet.contacts.data.ContactDBHelper;

/**
 * Created by heinhtet on 3/13/17.
 */
public class DetailActivity extends AppCompatActivity {
    TextView phone, email,name;
    ContactDBHelper mdb;
    ImageView call,email_send,bolb;

    Contact c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_contact);
        Intent i = getIntent();
        android.support.v7.app.ActionBar a = getSupportActionBar();
        a.setDisplayShowHomeEnabled(true);
        a.setDisplayHomeAsUpEnabled(true);
        a.setHomeButtonEnabled(true);


        c = (Contact) i.getSerializableExtra("contact");
        name = (TextView) findViewById(R.id.detail_name);
        call = (ImageView) findViewById(R.id.detail_call);
        bolb = (ImageView)findViewById(R.id.detail_Imge);
        phone = (TextView) findViewById(R.id.detail_phone);
        email = (TextView) findViewById(R.id.d_email);
        email_send = (ImageView)findViewById(R.id.email_detail);

        name.setText(c.getName());
        phone.setText(c.getPhone());
        email.setText(c.getEmail());

        byte[] outImage=c.getIamge();

        /*
        getiing byte_array_image from database and that converting to Bit_map
         */
        //ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);

        Bitmap theImage = BitmapFactory.decodeByteArray(outImage,0,outImage.length);

        bolb.setImageBitmap(theImage);


        email_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageMail(email.getText().toString(),name.getText().toString());
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call_phone();
            }
        });




    }

    private void SendMessageMail(String email_name, String name) {
        Intent mail = new Intent(Intent.ACTION_SENDTO);
        mail.setData(Uri.parse("mailto:"+ email_name));
        mail.putExtra(Intent.EXTRA_SUBJECT,"" +name);
        startActivity(mail);
    }

    private void Call_phone() {

        Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + c.getPhone()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(call);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.detail_update:
            {

                Intent update = new Intent(DetailActivity.this,UpdateActivity.class);
                update .putExtra("contact", c);
                startActivity(update);
                break;
            }
            case R.id.detail_delete:
            {
               mdb = new ContactDBHelper(this);
                mdb.delete_contact((int) c.getId());
                finish();
                break;
            }
            case android.R.id.home:
            {
                startActivity(new Intent(DetailActivity.this,MainActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
