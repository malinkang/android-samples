package com.mamating.bean;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.mamating.constants.CountInfoTable;

@Table(name = CountInfoTable.TABLE_NAME, id = BaseColumns._ID)
public class CountInfo extends Model {
	@Column(name = CountInfoTable.COLUMN_UID)
	private String uid;
	private Count count;
	@Column(name = CountInfoTable.COLUMN_COUNT_JSON)
	private String count_json;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Count getCount() {
		return count;
	}

	public void setCount(Count count) {
		this.count = count;
	}

	public String getCount_json() {
		return count_json;
	}

	public void setCount_json(String count_json) {
		this.count_json = count_json;
	}

	@Override
	public String toString() {
		return "CountInfo [uid=" + uid + ", count=" + count + "]";
	}
}
