package com.example.revuk.lunch;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Revuk on 29-Jan-16.
 */
public class TabFragment2 extends Fragment {

    int TAB2_GROUP_ID = 2;



    private String JSON_STRING;
    private HashMap<String,String> menu;
    private ArrayList<HashMap<String,String>> list;

    private RecyclerView recyclerView2;
    private String id;
   private int i;
    private String nameMenu = "Main";
    private   String name;
    View tab2;


    public TabFragment2() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        getJSON();




        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         tab2 = inflater.inflate(R.layout.tab_fragment_2, container, false);
        recyclerView2 = (RecyclerView) tab2.findViewById(R.id.recyclerview_tab_2);
        final GridLayoutManager gridLayout2 = new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView2.setLayoutManager(gridLayout2);
        registerForContextMenu(recyclerView2);



        return tab2;
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

                String nameDish = jo.getString(Config.TAG_LUNCH_NAME_DISH);
                String description = jo.getString(Config.TAG_LUNCH_DESCRIPTION);
                String weight = jo.getString(Config.TAG_LUNCH_WEIGHT);
                String image = jo.getString(Config.TAG_LUNCH_IMAGE);

                menu = new HashMap<>();
                menu.put(Config.TAG_ID,id);

                menu.put(Config.TAG_LUNCH_NAME_DISH, nameDish);
                menu.put(Config.TAG_LUNCH_DESCRIPTION, description);
                menu.put(Config.TAG_LUNCH_WEIGHT, weight);
                menu.put(Config.TAG_LUNCH_IMAGE, image);
                list.add(menu);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




        Tab_RecyclerAdapter adapter2 = new Tab_RecyclerAdapter(getActivity().getApplicationContext(), list);
        recyclerView2.setAdapter(adapter2);

        //реагуавння на кліки
        adapter2.setOnItemClickListener(new Tab_RecyclerAdapter.OnItemClick() {

            @Override
            public void onItemClick(int pos) {
                i = pos;
                confirmorder();

            }
        });


    }

    private void confirmorder(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Would you like order this?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HashMap<String, String> choice1 = list.get(i);
                name = choice1.get(Config.TAG_LUNCH_NAME_DISH);

                Activity_Admin_Menu a = (Activity_Admin_Menu)getActivity();
                a.Order2(name);

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getActivity(), "no", Toast.LENGTH_LONG).show();


            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {

           // ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(Activity_View_All_Menu.this,nameMenu, Toast.LENGTH_LONG).show();
              //  loading = ProgressDialog.show(getActivity(),"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  loading.dismiss();
                JSON_STRING = s;
                //Toast.makeText(Activity_View_All_Menu.this,s, Toast.LENGTH_LONG).show();
                showMenu();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_DAY_MENU, nameMenu);
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
                loading = ProgressDialog.show(getActivity(), "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getJSON();
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();

                String s = rh.sendGetRequestParam(Config.URL_DELETE_DAY_MENU, id);
                return s;
            }
        }
        DeleteMenu de = new DeleteMenu();
        de.execute();


    }

    private void confirmDelete(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
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
                Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Select The Action");

        menu.add(TAB2_GROUP_ID, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getGroupId() == TAB2_GROUP_ID){
        if (item.getTitle().equals("Delete")) {
            HashMap<String, String> fo = list.get(i);
            id = fo.get(Config.TAG_ID);
            confirmDelete();
        }

        }


        return super.onContextItemSelected(item);
    }



}
