package com.example.revuk.lunch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Revuk on 18-Dec-16.
 */

public class Orders_ViewHolder extends RecyclerView.ViewHolder {

    public TextView orders_name;
    public TextView orders_device_id;
    public TextView orders_first;
    public TextView orders_second;
    public TextView orders_third;


    public Orders_ViewHolder(View itemView) {
        super(itemView);

        orders_name = (TextView) itemView.findViewById(R.id.textView10);
        orders_device_id = (TextView) itemView.findViewById(R.id.textView11);
        orders_first = (TextView) itemView.findViewById(R.id.textView12);
        orders_second = (TextView) itemView.findViewById(R.id.textView13);
        orders_third = (TextView) itemView.findViewById(R.id.textView14);
    }
}
