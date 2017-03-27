package com.example.heinhtet.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by heinhtet on 3/13/17.
 */

public class ListViewAdapter extends ArrayAdapter<ContactFav> {
    ArrayList<ContactFav> list;
    Context on;

    public ListViewAdapter(Context context, ArrayList<ContactFav> list1) {
        super(context,0, list1);
        on= context;
        list = list1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.eg,parent,false);

        }
        ContactFav contact = list.get(position);
        TextView  name = (TextView)convertView.findViewById(R.id.name);
       // TextView phone = (TextView)convertView.findViewById(R.id.rv_phone);
        name.setText(contact.getName());
        //phone.setText(contact.getPhone());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
