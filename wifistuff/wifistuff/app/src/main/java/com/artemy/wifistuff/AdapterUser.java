package com.artemy.wifistuff;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by natal on 11/03/2018.
 */

public class AdapterUser extends ArrayAdapter<User>
{
    Activity activity;
    ArrayList<User> all_users;
    LayoutInflater layoutInflater;

    public AdapterUser(Activity activity, ArrayList<User> all_users)
    {
        super(activity, 0, all_users);
        this.activity=activity;
        this.all_users=all_users;
        this.layoutInflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        User  userItem = getItem(position);
        View row = layoutInflater.inflate(R.layout.item_user,null);

        TextView text_view_nickname = (TextView)row.findViewById(R.id.text_view_nickname);
        TextView text_view_password = (TextView)row.findViewById(R.id.text_view_password);
        TextView text_view_first_name = (TextView)row.findViewById(R.id.text_view_first_name);
        TextView text_view_last_name = (TextView)row.findViewById(R.id.text_view_last_name);
        TextView text_view_phone = (TextView)row.findViewById(R.id.text_view_phone);

        text_view_nickname.setText(userItem.getNickname());
        text_view_password.setText(userItem.getPassword());
        text_view_first_name.setText(userItem.getFirst_name());
        text_view_last_name.setText(userItem.getLast_name());
        text_view_phone.setText(userItem.getPhone());


        return row;
    }


}
