package com.example.revuk.lunch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Revuk on 25-Jan-16.
 */
//public class User implements Parcelable {
public class User  {

    String Name;
    String Device_id;


    public User(String name, String device_id) {
        this.Name = name;
        this.Device_id = device_id;
    }

   /* protected User(Parcel in) {
        Name = in.readString();
        Device_id = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Device_id);
    }*/
}
