package com.app.wedonate2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListAdapterFindDonar extends ArrayAdapter<Find_Blood_Model_Class> {
    public ListAdapterFindDonar(@NonNull Context context, int resource, ArrayList<Find_Blood_Model_Class> userList) {
        super(context, resource,userList);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Find_Blood_Model_Class user2 = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_info,parent,false);
        }
        TextView tv = convertView.findViewById(R.id.address);
        TextView tv2 = convertView.findViewById(R.id.userInfo);
        TextView tv3 = convertView.findViewById(R.id.city);

        tv.setText(user2.getAddress());
        tv2.setText(user2.getBlood());
        tv3.setText(user2.getCity());
        return convertView;
    }
}
