package com.app.wedonate2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class blood_bank_adapter extends ArrayAdapter<Find_Blood_Model_Class> {
    public blood_bank_adapter(@NonNull Context context, int resource, ArrayList<Find_Blood_Model_Class> userList) {
        super(context, resource, userList);
    }
        public View getView(int position, View convertView, ViewGroup parent){
            Find_Blood_Model_Class user = getItem(position);

            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_info,parent,false);
            }
            TextView tv = convertView.findViewById(R.id.address);
            TextView tv2 = convertView.findViewById(R.id.userInfo);
            TextView tv3 = convertView.findViewById(R.id.city);
            TextView tv4 = convertView.findViewById(R.id.name2);
            TextView tv5 = convertView.findViewById(R.id.name3);
            LinearLayout l1= convertView.findViewById(R.id.layer3);

            LinearLayout l2= convertView.findViewById(R.id.layer4);



            tv.setText(user.getAddress());
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
            tv4.setText(user.getName());
            return convertView;
        }

}
