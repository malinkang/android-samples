package com.malinkang.parcelable;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by malinkang on 2014/9/15.
 */
public class Book implements Parcelable{
    String title;
    Author author;
    //setter & getter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.author, flags);
    }

    public Book() {
    }

    private Book(Parcel in) {
        this.title = in.readString();
        this.author = in.readParcelable(Author.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
