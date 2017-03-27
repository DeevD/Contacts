package com.example.heinhtet.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heinhtet.contacts.data.ContactDBHelper;

import java.util.ArrayList;

/**
 * Created by heinhtet on 3/13/17.
 */

public class ContactFavActivity extends AppCompatActivity {
    ArrayList<ContactFav> list;
    ListView lv;
    ContactDBHelper mdb;
    ListViewAdapter adapter;
    TextView empty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_contact_rv);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        empty  = (TextView) findViewById(R.id.empty_view_fav);


    }

    @Override
    protected void onStart() {
        mdb = new ContactDBHelper(this);
        super.onStart();
        list = new ArrayList<>();


        list = mdb.get_all_list();

        if (list.isEmpty())
        {
            empty.setText("No Favorites Contact");
        }
        FavMyadapter favMyadapter;
        RecyclerView rv;
        favMyadapter = new FavMyadapter(this, list);

        rv = (RecyclerView) findViewById(R.id.fav_rv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(favMyadapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                startActivity(new Intent(ContactFavActivity.this, MainActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
