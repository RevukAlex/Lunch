package com.example.revuk.lunch;



import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;


import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Revuk on 29-Jan-16.
 */
public class TabFragment4 extends Fragment {

    String JSON_STRING;
    public static final String FRAGMENT_BUNDLE = "key";


    HashMap<String,String> tt;
    ArrayList<HashMap<String,String>> list;
    Tab_RecyclerAdapter adapter;
    RecyclerView recyclerView;
    private static String CHECK_MENU = "check";

    private static int id = 2;

    public Activity_Admin_Menu act;

    String h;






    public static TabFragment4 newInstance(int page) {
    // public static void newInstance(int page) {
        id = page;
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_BUNDLE, id);
        TabFragment4 fragment = new TabFragment4();
        fragment.setArguments(args);
      // FragmentTransaction transaction = getFragmentManager();
     //   transaction.commit();
    return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  newInstance(4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View tab4 = inflater.inflate(R.layout.tab_fragment_4, container, false);
      /*  recyclerView = (RecyclerView) tab4.findViewById(R.id.recyclerview_tab_4);
        final GridLayoutManager gridLayout = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayout);
        registerForContextMenu(recyclerView);
        list = new ArrayList<HashMap<String, String>>();
        tt = new HashMap<String,String>();*/

       // int id = this.getArguments().getInt(CHECK_MENU);
       // Toast.makeText(getActivity(),idi,Toast.LENGTH_SHORT).show();

       // String s = act.b;

       // Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();

        /*String id = bundle.getString("ID");
        String nameMenu = bundle.getString("NAMEMENU");
        String nameDish = bundle.getString("NAMEDISH");
        String description = bundle.getString("DESCRIPTION");
        String weight = bundle.getString("WEIGHT");
        String image = bundle.getString("IMAGE");*/
        Bundle args = getArguments();

        int mPage = args.getInt(FRAGMENT_BUNDLE);

       /* adapter = new Tab_RecyclerAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);*/

       // list.add(menu);

      /*  Activity_Admin_Menu a = (Activity_Admin_Menu)getActivity();
        String b = a.test();*/

       /* Tabfragment1 a = new Tabfragment1();
        menu = a.test();
        list.add(menu);
*/
        //Toast.makeText(getActivity(),b,Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity(),mPage,Toast.LENGTH_SHORT).show();

        return tab4;
    }




}
