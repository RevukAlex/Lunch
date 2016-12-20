package com.example.revuk.lunch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Order extends AppCompatActivity {


    Order_dish order_dish;
    TextView First;
    TextView Second;
    TextView Third;

    String name;
    String deviceId;
    String first;
    String second;
    String third;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        First = (TextView) findViewById(R.id.textView2);
        Second = (TextView) findViewById(R.id.textView4);
        Third = (TextView) findViewById(R.id.textView6);


        Intent intent = getIntent();
        order_dish = intent.getParcelableExtra("Order");

        First.setText(order_dish.First);
        Second.setText(order_dish.Second);
        Third.setText(order_dish.Third);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:

                this.finish();
                break;
            case R.id.button2:
                name = order_dish.Name;
                deviceId = order_dish.Device_ID;
                first = order_dish.First;
                second = order_dish.Second;
                third = order_dish.Third;


                addMenu();

                break;


        }
    }

    private void addMenu() {

        class addMenu extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Order.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Order.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... param) {

                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_LUNCH_ORDER_NAME, name);
                params.put(Config.KEY_LUNCH_ORDER_DEVICE_ID, deviceId);
                params.put(Config.KEY_LUNCH_ORDER_FIRST, first);
                params.put(Config.KEY_LUNCH_ORDER_SECOND, second);
                params.put(Config.KEY_LUNCH_ORDER_THIRD, third);


                String res = rh.sendPostRequest(Config.URL_ADD_ORDER, params);
                return res;

                //return null;
            }
        }
        addMenu addM = new addMenu();
        addM.execute();

    }
}
