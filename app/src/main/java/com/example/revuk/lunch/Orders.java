package com.example.revuk.lunch;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Orders extends AppCompatActivity {

    Orders_RecyclerAdapter adapterb;
    private ArrayList<Order_dish> arr;
    String JSON_STRING;
    HashMap<String,String> menu;
    ArrayList<HashMap<String,String>> list;
    RecyclerView recyclerViewb;
    String nameMenu;
    private String id;

    String deviceId;
    String first;
    String second;
    String third;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        getJSON();

        recyclerViewb = (RecyclerView)findViewById(R.id.recyclerview_orders);
        LinearLayoutManager layoutManagerb = new LinearLayoutManager(this);
        recyclerViewb.setLayoutManager(layoutManagerb);



        }

    private void showMenu() {
        JSONObject jsonObject = null;
        arr = new ArrayList<Order_dish>();


        try {

            jsonObject = new JSONObject(JSON_STRING);

            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String name = jo.getString(Config.TAG_ORDER_NAME);
                deviceId = jo.getString(Config.TAG_ORDER_DEVICE_ID);
                first = jo.getString(Config.TAG_ORDER_FIRST);
                second = jo.getString(Config.TAG_ORDER_SECOND);
                third = jo.getString(Config.TAG_ORDER_THIRD);

                Order_dish order_dish = new Order_dish(name,deviceId,first,second,third);
                arr.add(order_dish);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapterb = new Orders_RecyclerAdapter(this,arr);
        recyclerViewb.setAdapter(adapterb);

        //реагуавння на кліки


    }


    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(Orders.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;

                showMenu();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ORDER, nameMenu);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button4:
            this.finish();
                break;

            case R.id.button:
                deleteTabl();
                break;



        }
    }

    private void deleteTabl (){
        class DeleteMenu extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Orders.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getJSON();
                Toast.makeText(Orders.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();

                String s = rh.sendGetRequestParam(Config.URL_DELETE_TABL, id);
                return s;
            }
        }
        DeleteMenu de = new DeleteMenu();
        de.execute();


    }

}
