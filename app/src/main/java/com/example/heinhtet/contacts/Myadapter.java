package com.example.heinhtet.contacts;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heinhtet.contacts.data.ContactDBHelper;

import java.util.ArrayList;

/**
 * Created by heinhtet on 3/13/17.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    ContactDBHelper mdb;
    Context mContext;
    ArrayList<Contact> list = new ArrayList<>();
    String encode = Uri.encode("#");
    Contact contact;


    public Myadapter(Context context, ArrayList<Contact> list1) {

        mContext = context;
        list = list1;
    }

    @Override
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.contact_rv_row, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Myadapter.ViewHolder holder, int position) {
        mdb = new ContactDBHelper(mContext.getApplicationContext());
        contact = list.get(position);
        holder.name.setText(contact.getName());

        if (contact.getName().contains("h"))
        {
            holder.keyword.setText("H");
        }
        else if (contact.getName().contains("a"))
        {
            holder.keyword.setText("A");
        }
        else if (contact.getName().contains("b"))
        {
            holder.keyword.setText("B");
        }
        else if (contact.getName().contains("c"))
        {
            holder.keyword.setText("C");
        }
        else if (contact.getName().contains("d"))
        {
            holder.keyword.setText("D");
        }
        else if (contact.getName().contains("e"))
        {
            holder.keyword.setText("E");
        }
        else if (contact.getName().contains("f"))
        {
            holder.keyword.setText("F");
        }
        else if (contact.getName().contains("g"))
        {
            holder.keyword.setText("G");
        }
        else if (contact.getName().contains("h"))
        {
            holder.keyword.setText("H");
        }
        else if (contact.getName().contains("i"))
        {
            holder.keyword.setText("I");
        }
        else if (contact.getName().contains("j"))
        {
            holder.keyword.setText("J");
        }
        else if (contact.getName().contains("k"))
        {
            holder.keyword.setText("K");
        }else if (contact.getName().contains("l"))
        {
            holder.keyword.setText("L");
        }
        else if (contact.getName().contains("m"))
        {
            holder.keyword.setText("M");
        }else if (contact.getName().contains("N"))
        {
            holder.keyword.setText("n");
        }else if (contact.getName().contains("o"))
        {
            holder.keyword.setText("O");
        }else if (contact.getName().contains("p"))
        {
            holder.keyword.setText("P");
        }
        else if (contact.getName().contains("r"))
        {
            holder.keyword.setText("R");
        }
        else if (contact.getName().contains("s"))
        {
            holder.keyword.setText("S");
        }
        else if (contact.getName().contains("t"))
        {
            holder.keyword.setText("T");
        }
        else if (contact.getName().contains("u"))
        {
            holder.keyword.setText("U");
        }
        else if (contact.getName().contains("w"))
        {
            holder.keyword.setText("W");
        }
        else if (contact.getName().contains("x"))
        {
            holder.keyword.setText("X");
        }
        else if (contact.getName().contains("y"))
        {
            holder.keyword.setText("Y");
        }
        else if (contact.getName().contains("z"))
        {
            holder.keyword.setText("Z");
        }
        else
        {
            holder.keyword.setText("No");
        }






        holder.phone.setText(contact.getPhone());
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call_Phone();
            }
        });

        holder.call_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call_Phone();
            }
        });
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdb.insert_fav(holder.name.getText().toString(),holder.phone.getText().toString(),contact.getNote(),contact.getEmail(),contact.getTime());
            }
        });

        GradientDrawable drawable = (GradientDrawable)holder.keyword.getBackground();
        int bg_color = getBackgroundColor(contact.getName());
        drawable.setColor(bg_color);



    }

    private int getBackgroundColor(String name) {
        int bg_color ;
        if (name.contains("h"))
        {
            bg_color = R.color.bg_screen1;
        }


        else if (name.contains("h"))
        {
            bg_color = R.color.magnitude1;
        }
        else if (name.contains("a"))
        {
            bg_color = R.color.magnitude2;

        }
        else if (name.contains("b"))
        {
            bg_color = R.color.magnitude3;

        }
        else if (contact.getName().contains("c"))
        {

            bg_color = R.color.magnitude4;

        }
        else if (contact.getName().contains("d"))
        {

            bg_color = R.color.magnitude5;

        }
        else if (contact.getName().contains("e"))
        {
            bg_color = R.color.magnitude6;

        }
        else if (contact.getName().contains("f"))
        {
            bg_color = R.color.magnitude7;
        }
        else if (contact.getName().contains("g"))
        {
            bg_color = R.color.magnitude8;

        }
        else if (contact.getName().contains("h"))
        {
            bg_color = R.color.magnitude9;

        }
        else if (contact.getName().contains("i"))
        {
            bg_color = R.color.magnitude10plus;
        }
        else if (contact.getName().contains("j"))
        {
            bg_color = R.color.bg_screen2;
        }
        else if (contact.getName().contains("k"))
        {
            bg_color = R.color.dot_light_screen1;
        }else if (contact.getName().contains("l"))
        {
            bg_color = R.color.cardview_dark_background;
        }
        else if (contact.getName().contains("m"))
        {
            bg_color = R.color.dot_dark_screen2;

        }else if (contact.getName().contains("N"))
        {
            bg_color = R.color.bg_screen4;

        }else if (contact.getName().contains("o"))
        {
            bg_color = R.color.bg_screen3;
        }
        else if (contact.getName().contains("p"))
        {
            bg_color = R.color.bg_screen1;
        }
        else if (contact.getName().contains("r"))
        {
            bg_color = R.color.bg_screen2;
        }
        else if (contact.getName().contains("s"))
        {
            bg_color = R.color.dot_light_screen2;
        }
        else if (contact.getName().contains("t"))
        {
            bg_color = R.color.dot_light_screen3;
        }
        else if (contact.getName().contains("u"))
        {
            bg_color = R.color.colorAccent;
        }
        else if (contact.getName().contains("w"))
        {
            bg_color = R.color.dot_dark_screen3;
        }
        else if (contact.getName().contains("x"))
        {
            bg_color = R.color.bg_screen4;
        }
        else if (contact.getName().contains("y"))
        {
            bg_color = R.color.bg_screen2;
        }
        else if (contact.getName().contains("z"))
        {
            bg_color = R.color.bg_screen1;
        }
        else
        {
            bg_color = R.color.cardview_dark_background;
        }

        return ContextCompat.getColor(mContext.getApplicationContext(),bg_color);
    }

    public void Call_Phone() {
        Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mContext.startActivity(call);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name ,phone,keyword;
        ImageView call_img ,fav;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_con_name);
            phone = (TextView) itemView.findViewById(R.id.rv_con_phone);
           // delete = (TextView) itemView.findViewById(R.id.delete);
            call_img = (ImageView) itemView.findViewById(R.id.rv_con_call);
            fav = (ImageView) itemView.findViewById(R.id.rv_con_fav);
            keyword = (TextView) itemView.findViewById(R.id.rv_con_key);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext.getApplicationContext(),DetailActivity.class);
                    i.putExtra("contact",list.get(getAdapterPosition()));
                    mContext.startActivity(i);
                }
            });

        }
    }
}
