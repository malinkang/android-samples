package com.mamating.bean;

import android.provider.BaseColumns;

import com.activeandroid.annotation.Table;
import com.mamating.constants.FriendTable;

//关注人
@Table(name = FriendTable.FOLLOWING_TABLE_NAME, id = BaseColumns._ID)
public class Following extends Friend {

}
