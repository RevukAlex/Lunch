package com.example.revuk.lunch;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Revuk on 22-Feb-16.
 */
public class Tab_ViewHolder extends RecyclerView.ViewHolder{


    TextView nameMenu;
    public TextView nameDish;
    public TextView description;
    public TextView weight;
    ImageView image, check;
    public View RootView;




    public Tab_ViewHolder(final View itemView) {
        super(itemView);

        nameDish = (TextView) itemView.findViewById(R.id.item_nameDish);
        description = (TextView) itemView.findViewById(R.id.item_description);
        weight = (TextView) itemView.findViewById(R.id.item_weight);
        image = (ImageView) itemView.findViewById(R.id.item_image_dish);
        RootView = itemView.findViewById(R.id.rootView);



    }



}
