package com.example.revuk.lunch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Revuk on 22-Feb-16.
 */
public class Tab_RecyclerAdapter extends RecyclerView.Adapter<Tab_ViewHolder> {

    private Context context;
    private ArrayList<HashMap<String,String>> Menus;
    private OnItemClick onClickListener;
    private String CHECK_MENU = "check";
    private  String name;



    public Tab_RecyclerAdapter(Context context,ArrayList<HashMap<String,String>> menus) {
        this.context = context;
        this.Menus = menus;
    }

    private Bitmap base64ToBitmap(String b64) {



        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        return bitmap;
    }


    @Override
    public Tab_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_title, parent,false);
        Tab_ViewHolder viewHolder = new Tab_ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Tab_ViewHolder holder, final int position) {

        final HashMap<String,String> menu = Menus.get(position);
        holder.nameDish.setText(menu.get(Config.TAG_LUNCH_NAME_DISH));
        holder.description.setText(menu.get(Config.TAG_LUNCH_DESCRIPTION));
        holder.weight.setText(menu.get(Config.TAG_LUNCH_WEIGHT));
        holder.image.setImageBitmap(base64ToBitmap(menu.get(Config.TAG_LUNCH_IMAGE)));


        //реагування на кліки


        holder.RootView.setOnLongClickListener(new View.OnLongClickListener() {
            //довгі кліки
            @Override
            public boolean onLongClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onItemClick(position);
                }
                return false;
            }
//короткі кліки
           /* @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onItemClick(i);
                }
            }*/
        });

        holder.RootView.setOnClickListener(new View.OnClickListener() {
            //довгі кліки
            @Override
            public void onClick(View v) {



                if (onClickListener != null) {
                    onClickListener.onItemClick(position);

                }










            }

        });

    }

    public interface OnItemClick{
        public void onItemClick(int position);
    }
    //кліки
    public void setOnItemClickListener(OnItemClick listener){
        onClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return Menus.size();
    }
}
