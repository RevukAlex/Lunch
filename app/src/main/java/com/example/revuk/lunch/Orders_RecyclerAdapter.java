package com.example.revuk.lunch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Revuk on 18-Dec-16.
 */

public class Orders_RecyclerAdapter extends RecyclerView.Adapter<Orders_ViewHolder> {

    private Context context;
    private ArrayList<Order_dish> Orders;

    public Orders_RecyclerAdapter(Context context, ArrayList<Order_dish> orders) {
        this.context = context;
        this.Orders = orders;
        }



    @Override
    public Orders_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_customer, null);
        Orders_ViewHolder ordersHolder = new Orders_ViewHolder(view);

        return ordersHolder;



    }



    @Override
    public void onBindViewHolder(final Orders_ViewHolder viewHolder, final int position) {

        final Order_dish order = Orders.get(position);

        viewHolder.orders_name.setText(order.Name);
        viewHolder.orders_device_id.setText(order.Device_ID);
        viewHolder.orders_first.setText(order.First);
        viewHolder.orders_second.setText(order.Second);
        viewHolder.orders_third.setText(order.Third);


    }



    @Override
    public int getItemCount() {
        return Orders.size();
}
}
