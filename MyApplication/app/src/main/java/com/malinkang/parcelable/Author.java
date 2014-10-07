package com.malinkang.parcelable;


import android.os.Parcelable;

import org.parceler.Parcel;

/**
 * Created by malinkang on 2014/9/15.
 */
@Parcel
public class Author implements Parcelable {
    int id;

    String name;

    //setter & getter...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public Author() {
    }

    private Author(android.os.Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        public Author createFromParcel(android.os.Parcel source) {
            return new Author(source);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
