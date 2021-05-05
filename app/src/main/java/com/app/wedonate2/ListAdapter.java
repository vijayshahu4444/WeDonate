package com.app.wedonate2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<User> {
    public ListAdapter(@NonNull Context context, int resource, ArrayList<User> userList) {
        super(context, resource,userList);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_info,parent,false);
        }
        TextView tv = convertView.findViewById(R.id.address);
        TextView tv2 = convertView.findViewById(R.id.userInfo);
        TextView tv3 = convertView.findViewById(R.id.city);
        TextView tv4 = convertView.findViewById(R.id.name2);
        TextView tv5 = convertView.findViewById(R.id.name3);



        tv.setText(user.getAddress());
        tv2.setText(user.getBlood_Group());
        tv3.setText(user.getCity());
        tv4.setText(user.getFname());
        tv5.setText(user.getLname());
        return convertView;
    }

}
