package com.example.revuk.lunch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Revuk on 09-Dec-16.
 */

public class Order_dish implements Parcelable{
    public String First;
    public String Second;
    public String Third;
    public  String Name;
    public String Device_ID;


    public Order_dish(String name, String device_id, String first, String second, String third){
        this.Name = name;
        this.Device_ID = device_id;
        this.First = first;
        this.Second = second;
        this.Third = third;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.First);
        dest.writeString(this.Second);
        dest.writeString(this.Third);
        dest.writeString(this.Name);
        dest.writeString(this.Device_ID);

    }

    protected Order_dish(Parcel in) {
        this.First = in.readString();
        this.Second = in.readString();
        this.Third = in.readString();
        this.Name = in.readString();
        this.Device_ID = in.readString();
    }

    public static final Parcelable.Creator<Order_dish> CREATOR = new Parcelable.Creator<Order_dish>() {
        public Order_dish createFromParcel(Parcel source) {
            return new Order_dish(source);
        }
        public Order_dish[] newArray(int size) {
            return new Order_dish[size];
        }
    };
}
