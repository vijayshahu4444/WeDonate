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

public class blood_package_adapter extends ArrayAdapter<Blood_Pakcage_Helper> {
    public blood_package_adapter(@NonNull Context context, int resource, ArrayList<Blood_Pakcage_Helper> userList) {
        super(context, resource, userList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Blood_Pakcage_Helper user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_info, parent, false);
        }
        TextView tv = convertView.findViewById(R.id.address);
        TextView tv2 = convertView.findViewById(R.id.userInfo);
        TextView tv3 = convertView.findViewById(R.id.city);
        TextView tv4 = convertView.findViewById(R.id.name2);
        TextView name = convertView.findViewById(R.id.name);
        TextView address = convertView.findViewById(R.id.textname);
        TextView tv5 = convertView.findViewById(R.id.name3);
        LinearLayout l1 = convertView.findViewById(R.id.layer3);

        LinearLayout l2 = convertView.findViewById(R.id.layer4);

        name.setText("Blood Group");
        address.setText("Packets");

        tv.setText(user.getPackets());

        l1.setVisibility(View.GONE);
        l2.setVisibility(View.GONE);
        tv4.setText(user.getBlood_Group());
        return convertView;
    }
}
