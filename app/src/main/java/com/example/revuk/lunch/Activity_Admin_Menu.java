package com.example.revuk.lunch;




import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Admin_Menu extends AppCompatActivity{

    String First = "First";
    String Main = "Main";
    String Salad = "Salad";
    String Order = "Order";
    int tabItem;
    PagerAdapter adapter0;
   Order_dish order_dish;
    String first;
    String second;
    String third;
    String name;
    String device_id;
    User user;


    String b;




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin__menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            order_dish = new Order_dish(first,second,third,name,device_id);
            Intent intent = getIntent();
            user = intent.getParcelableExtra("User");
            order_dish.Name = user.Name;
            order_dish.Device_ID = user.Device_id;


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(First));
        tabLayout.addTab(tabLayout.newTab().setText(Main));
        tabLayout.addTab(tabLayout.newTab().setText(Salad));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        adapter0 = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

//перелистування
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());
               tabItem = tab.getPosition();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
    // Inflate the menu; this adds items to the action bar if it is present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem setting = menu.findItem(R.id.action_settings);
        MenuItem add = menu.findItem(R.id.action_add);

        if(user.Name.equals("Admin")){


        } else {
        setting.setVisible(false);
        add.setVisible(false);
        }
        return true;



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        String tabName = null;
        if (tabItem == 0){
            tabName = First;

        }else if (tabItem == 1){
            tabName = Main;

        }else if (tabItem == 2){
            tabName = Salad;

        }else {
            tabName = null;

        }

        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, Activity_Create_Dish.class);
                intent.putExtra(Config.KEY_TAB, tabName);
                startActivity(intent);

                break;
            case R.id.action_add:
                Intent intent1 = new Intent(this, Activity_View_All_Menu.class);
                intent1.putExtra(Config.KEY_TAB, tabName);
                startActivity(intent1);
                break;
            case R.id.action_list:

                if(user.Name.equals("Admin")){

                    Intent intent3 = new Intent(this, Orders.class);
                    startActivity(intent3);


                } else {


                Intent intent2 = new Intent(this, Order.class);
                intent2.putExtra("Order", order_dish);
                startActivity(intent2);}
                break;
        }

        return super.onOptionsItemSelected(item);

    }


    public void Order1(String a){
        order_dish.First = a;
    }
    public void Order2(String b){
        order_dish.Second = b;
    }
    public void Order3(String c){
        order_dish.Third = c;
    }



}
