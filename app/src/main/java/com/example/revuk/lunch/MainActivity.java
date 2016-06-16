package com.example.revuk.lunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    EditText editText_user_name;
    Button button_logIn;
    private User user;
    String deviceId;
    HashMap<String, String> params;
    String Admin = "Admin";
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_user_name = (EditText) findViewById(R.id.editText_user_name);
        button_logIn = (Button) findViewById(R.id.button_LogIn);
//        getDevice_id();

        Intent intent = new Intent(this, Activity_Admin_Menu.class);
        // intent.putExtra(EXTRA_MENU, arr.get(0));
        startActivity(intent);


    }



    public void onClick(View v){
        Name = editText_user_name.getText().toString();
        user = new User(Name,deviceId);
       // checkUser();
        //addUser();

    }

    private void checkUser(){

        if (Name.equals(Admin)){
            Intent intent = new Intent(this, Activity_Admin_Menu.class);
           // intent.putExtra(EXTRA_MENU, arr.get(0));
            startActivity(intent);
        }else{
            //Toast.makeText(MainActivity.this, "I"+checkUser+"I", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Activity_Admin_Menu.class);
            // intent.putExtra(EXTRA_MENU, arr.get(0));
            startActivity(intent);
        }
    }


    private void getDevice_id (){
        TelephonyManager tMgr =(TelephonyManager)this.getSystemService(TELEPHONY_SERVICE);
//        deviceId = tMgr.getDeviceId();

    }

    /*private void addUser() {
        class addUser extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {

                params = new HashMap<>();
                params.put(Config.KEY_LUNCH_NAME, user.Name );
                params.put(Config.KEY_LUNCH_DEVICE_ID, user.Device_id);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_USER, params);
                return res;
            }
        }
        addUser addU = new addUser();
        addU.execute();
    }

    private void checkUser (){
       // Toast.makeText(MainActivity.this, deviceId, Toast.LENGTH_LONG).show();
        class CheckUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                checkUser = s;
                super.onPostExecute(checkUser);
                loading.dismiss();
                Toast.makeText(MainActivity.this, checkUser, Toast.LENGTH_LONG).show();
                check();


            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();

                String s = rh.sendGetRequestParam(Config.URL_CHECK_USER, deviceId);
                return s;
            }
        }
        CheckUser de = new CheckUser();
        de.execute();


    }*/


}
