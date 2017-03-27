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
 * Created by heinhtet on 3/14/17.
 */
public class FavMyadapter extends RecyclerView.Adapter<FavMyadapter.ViewHoleder> {
    ContactDBHelper mdb;
    ContactFav c;
    Context mContext;
    ArrayList<ContactFav> list = new ArrayList<>();

    public FavMyadapter(Context context, ArrayList<ContactFav> list1)

    {
        mContext = context;
        list = list1;
    }

    @Override
    public FavMyadapter.ViewHoleder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.fav_contact_row, parent, false);
        ViewHoleder holder = new ViewHoleder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(FavMyadapter.ViewHoleder holder, int position) {
        c = list.get(position);
        holder.name.setText(c.getName());
        holder.phone.setText(c.getPhone());

        if (c.getName().contains("h"))

        {
            holder.keyword.setText("h");
        }
        else {
            holder.keyword.setText("K");
        }
        GradientDrawable draw = (GradientDrawable) holder.keyword.getBackground();

        int bg_keyword = SetBackground(holder.name);

        draw.setColor(bg_keyword);

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call_Phone();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdb = new ContactDBHelper(mContext);
                mdb.delete_contact_fav(c.getId());
                mContext.startActivity(new Intent(mContext.getApplicationContext(),ContactFavActivity.class));


            }
        });
    }
    public void Call_Phone() {
        Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + c.getPhone()));
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

    private int SetBackground(TextView keyword)
    {
        int bg_iD;
        if (keyword.getText().toString().contains("h"))
        {
            bg_iD = R.color.bg_screen1;
        }
        else
        {
            bg_iD = R.color.bg_screen4;
        }
        return ContextCompat.getColor(mContext, bg_iD);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder {
        TextView keyword, name, phone;
        ImageView call,delete;

        public ViewHoleder(View itemView) {
            super(itemView);

            keyword = (TextView) itemView.findViewById(R.id.fav_keyword);
            name = (TextView) itemView.findViewById(R.id.fav_rv_name);
            phone = (TextView) itemView.findViewById(R.id.fav_rv_phone);
            call = (ImageView) itemView.findViewById(R.id.favcall);
            delete = (ImageView) itemView.findViewById(R.id.fav_rv_delete);


        }
    }
}
