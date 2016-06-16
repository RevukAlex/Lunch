package com.example.revuk.lunch;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class Activity_View_All_Menu extends AppCompatActivity {

    String JSON_STRING;
    HashMap<String,String> menu;
    ArrayList<HashMap<String,String>> list;
    AllMenu_RecyclerAdapter adapter;
    RecyclerView recyclerView;
    private String id;
    int i;
    String nameMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__view__all__menu);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayout);

        //отримуємо від головного актівіті дані в якому типу меню ми знаходимось
        Intent intent = getIntent();
        nameMenu = intent.getStringExtra(Config.KEY_TAB);


        getJSON();
    }

    public void onClick(View v){
     this.finish();

    }


    private void showMenu() {
        JSONObject jsonObject = null;
        list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
         // String nameMenu = jo.getString(Config.TAG_LUNCH_NAME_MENU);
                String nameDish = jo.getString(Config.TAG_LUNCH_NAME_DISH);
                String description = jo.getString(Config.TAG_LUNCH_DESCRIPTION);
                String weight = jo.getString(Config.TAG_LUNCH_WEIGHT);
                String image = jo.getString(Config.TAG_LUNCH_IMAGE);

                menu = new HashMap<>();
                menu.put(Config.TAG_ID,id);
               // menu.put(Config.TAG_LUNCH_NAME_MENU, nameMenu);
                menu.put(Config.TAG_LUNCH_NAME_DISH, nameDish);
                menu.put(Config.TAG_LUNCH_DESCRIPTION, description);
                menu.put(Config.TAG_LUNCH_WEIGHT, weight);
                menu.put(Config.TAG_LUNCH_IMAGE, image);
                list.add(menu);
               // Toast.makeText(this,image,Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new AllMenu_RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);

        //реагуавння на кліки
        adapter.setOnItemClickListener(new AllMenu_RecyclerAdapter.OnItemClick() {

            @Override
            public void onItemClick(int position) {
                i = position;
            }
        });


    }


    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
              // Toast.makeText(Activity_View_All_Menu.this,nameMenu, Toast.LENGTH_LONG).show();
                loading = ProgressDialog.show(Activity_View_All_Menu.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
              //Toast.makeText(Activity_View_All_Menu.this,s, Toast.LENGTH_LONG).show();
                showMenu();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_MENU, nameMenu);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void deleteMenu (){
        class DeleteMenu extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_View_All_Menu.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getJSON();
                Toast.makeText(Activity_View_All_Menu.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();

                String s = rh.sendGetRequestParam(Config.URL_DELETE_MENU, id);
                return s;
            }
        }
        DeleteMenu de = new DeleteMenu();
        de.execute();


    }

    private void confirmDelete(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMenu();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Activity_View_All_Menu.this, id, Toast.LENGTH_LONG).show();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void addDayMenu (){
        class addDayMenu extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_View_All_Menu.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Activity_View_All_Menu.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_ADD_DAY_MENU, id);
                return s;
            }
        }

        addDayMenu day = new addDayMenu();
        day.execute();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Add to lunch menu") {
            HashMap<String,String> fo = list.get(i);
            id = fo.get(Config.TAG_ID);

            addDayMenu ();

        } else if (item.getTitle() == "Delete") {

            HashMap<String,String> fo = list.get(i);
            id = fo.get(Config.TAG_ID);

            confirmDelete();

        }  else {
            return false;
        }

        return true;
    }



}
