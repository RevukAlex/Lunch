package com.example.revuk.lunch;

/**
 * Created by Revuk on 25-Jan-16.
 */
public class Config {
    //Address of our scripts of the CRUD

    public static final String URL_ADD_DISH="http://176.38.167.24/lunch_add_dish.php";
    public static final String URL_ADD_DAY_MENU="http://176.38.167.24/lunch_add_dayMenu.php?id=";
    public static final String URL_GET_MENU="http://176.38.167.24/luncy_getMenu.php?id=";
    public static final String URL_DELETE_MENU="http://176.38.167.24/lunch_deleteMenu.php?id=";
    public static final String URL_GET_DAY_MENU="http://176.38.167.24/lunch_getdayMenu.php?id=";
    public static final String URL_DELETE_DAY_MENU="http://176.38.167.24/lunch_deletedayMenu.php?id=";
    public static final String URL_ADD_ORDER="http://176.38.167.24/lunch_add_order.php";
    public static final String URL_GET_ORDER="http://176.38.167.24/lunch_get_orders.php?id=";
    public static final String URL_DELETE_TABL="http://176.38.167.24/lunch_deleteTabl.php?id=";

    //Keys that will be used to send the request to php scripts

    public static final String KEY_LUNCH_NAME_DISH = "nameDish";
    public static final String KEY_LUNCH_NAME_MENU = "nameMenu";
    public static final String KEY_LUNCH_DESCRIPTION = "description";
    public static final String KEY_LUNCH_WEIGHT = "weight";
    public static final String KEY_LUNCH_IMAGE = "image";
    public static final String KEY_TAB = "tab";

    public static final String KEY_LUNCH_ORDER_NAME = "name";
    public static final String KEY_LUNCH_ORDER_DEVICE_ID= "deviceId";
    public static final String KEY_LUNCH_ORDER_FIRST = "first";
    public static final String KEY_LUNCH_ORDER_SECOND = "second";
    public static final String KEY_LUNCH_ORDER_THIRD = "third";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_LUNCH_NAME_DISH = "nameDish";
    public static final String TAG_LUNCH_NAME_MENU = "nameMenu";
    public static final String TAG_LUNCH_DESCRIPTION = "description";
    public static final String TAG_LUNCH_WEIGHT = "weight";
    public static final String TAG_LUNCH_IMAGE = "image";


    public static final String TAG_ORDER_NAME = "name";
    public static final String TAG_ORDER_DEVICE_ID = "deviceId";
    public static final String TAG_ORDER_FIRST = "first";
    public static final String TAG_ORDER_SECOND = "second";
    public static final String TAG_ORDER_THIRD = "third";


}




