package com.example.revuk.lunch;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    EditText editText_user_name;
    Button button_logIn;
    private User user;
    String deviceId;
    HashMap<String, String> params;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_user_name = (EditText) findViewById(R.id.editText_user_name);
        button_logIn = (Button) findViewById(R.id.button_LogIn);
       getDevice_id();


    }



    public void onClick(View v){
        user = new User(Name,deviceId);
        Name = editText_user_name.getText().toString();
        user.Name = Name;
        user.Device_id = deviceId;

        Intent intent = new Intent(this, Activity_Admin_Menu.class);
        intent.putExtra("User", user);
        startActivity(intent);





    }



    private void getDevice_id (){

        deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


    }


}
