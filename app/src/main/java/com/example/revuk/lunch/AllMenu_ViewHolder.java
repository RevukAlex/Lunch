package com.example.revuk.lunch;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Revuk on 04-Feb-16.
 */
public class AllMenu_ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

   TextView nameMenu;
   public TextView nameDish;
   public TextView description;
   public TextView weight;
   ImageView image;
   public View RootView;



    public AllMenu_ViewHolder(View itemView) {
        super(itemView);

        nameDish = (TextView) itemView.findViewById(R.id.item_nameDish);
        description = (TextView) itemView.findViewById(R.id.item_description);
        weight = (TextView) itemView.findViewById(R.id.item_weight);
        image = (ImageView) itemView.findViewById(R.id.item_image_dish);
        RootView = itemView.findViewById(R.id.rootView);

        itemView.setOnCreateContextMenuListener(this);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Change Student");
        menu.add(0, v.getId(), 0, "Add to lunch menu"); //groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Delete");


    }
}
