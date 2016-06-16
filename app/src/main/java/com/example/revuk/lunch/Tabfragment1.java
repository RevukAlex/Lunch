package com.example.revuk.lunch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
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
public class Tabfragment1 extends Fragment {

    int TAB1_GROUP_ID = 1;


    private String JSON_STRING;
    private HashMap<String,String> menu;
    private ArrayList<HashMap<String,String>> list;
    private Tab_RecyclerAdapter adapter;
    Tab_ViewHolder holder;
    private RecyclerView recyclerView;
    private String id;
    int i;
    private String nameMenu = "First";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        getJSON();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Toast.makeText(getContext(),"tag1",Toast.LENGTH_SHORT).show();
       View tab1 = inflater.inflate(R.layout.tab_fragment_1, container, false);
//        ((TextView)tab1.findViewById(R.id.textView)).setText("abra-cadabra");


    recyclerView = (RecyclerView) tab1.findViewById(R.id.recyclerview_tab_1);
    final GridLayoutManager gridLayout = new GridLayoutManager(getActivity(),2);
    recyclerView.setLayoutManager(gridLayout);
        registerForContextMenu(recyclerView);




        return tab1;
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
                menu.put(Config.TAG_LUNCH_NAME_MENU, nameMenu);
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
        adapter = new Tab_RecyclerAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);

        //реагуавння на кліки
        adapter.setOnItemClickListener(new Tab_RecyclerAdapter.OnItemClick() {

            @Override
            public void onItemClick(int position) {

                i = position;


                /*TabFragment4 t = new TabFragment4();
                Bundle bundle = new Bundle();
                bundle.putString(FRAGMENT_BUNDLE, "dfdg");
                t.setArguments(bundle);*/

                Activity_Admin_Menu a = (Activity_Admin_Menu)getActivity();
                a.test(5);
                //test();

                /*TabFragment4 fragmentB = (TabFragment4)getActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag("Order");

                fragmentB.fragmentCommunication("textPassToB");

                Toast.makeText(getActivity(),
                        "text sent to Fragment B:\n ",
                        Toast.LENGTH_LONG).show();*/


               /* HashMap<String, String> tt = list.get(i);

                Bundle args = new Bundle();

                args.putSerializable("hashmap",tt);*/


               /* args.putString("ID",menu.get(Config.TAG_ID));
                args.putString("NAMEMENU",menu.get(Config.TAG_LUNCH_NAME_MENU));
                args.putString("NAMEDISH",menu.get(Config.TAG_LUNCH_NAME_DISH));
                args.putString("DESCRIPTION", menu.get(Config.TAG_LUNCH_DESCRIPTION));
                args.putString("WEIGHT", menu.get(Config.TAG_LUNCH_WEIGHT));
                args.putString("IMAGE", menu.get(Config.TAG_LUNCH_IMAGE));*/







            }
        });



    }


    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {

            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(Activity_View_All_Menu.this,nameMenu, Toast.LENGTH_LONG).show();
                //loading = ProgressDialog.show(getActivity(),"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // loading.dismiss();
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
                // Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();


            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


   /* @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Delete") {

            HashMap<String, String> fo = list.get(i);
            id = fo.get(Config.TAG_ID);

            confirmDelete();


        }
        return super.onContextItemSelected(item);
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //getActivity().getMenuInflater().inflate(R.menu.context,menu);

        menu.setHeaderTitle("Select The Action");
        // menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
        menu.add(TAB1_GROUP_ID, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getGroupId()==TAB1_GROUP_ID){
            if (item.getTitle().equals("Delete")) {
                HashMap<String, String> fo = list.get(i);
                id = fo.get(Config.TAG_ID);
                confirmDelete();
            }
            //  Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();
        }


        return super.onContextItemSelected(item);
    }

    public HashMap<String, String> test(){

        HashMap<String,String> t4 = list.get(i);
        id = t4.get(Config.TAG_ID);
        Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();


        return t4;

    }

}
