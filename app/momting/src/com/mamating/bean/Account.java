package com.mamating.bean;

import android.database.Cursor;
import android.provider.BaseColumns;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.mamating.constants.AccountTable;

@Table(name = AccountTable.TABLE_NAME, id = BaseColumns._ID)
public class Account extends User {
	@Column(name = AccountTable.COLUMN_SINA_UID)
	private String sina_uid;
	@Column(name = AccountTable.COLUMN_SINA_OAUTH_TOKEN)
	private String sina_oauth_token;
	@Column(name = AccountTable.COLUMN_QQ_UID)
	private String qq_uid;
	@Column(name = AccountTable.COLUMN_QQ_OAUTH_TOKEN)
	private String qq_oauth_token;

	public String getSina_uid() {
		return sina_uid;
	}

	public void setSina_uid(String sina_uid) {
		this.sina_uid = sina_uid;
	}

	public String getSina_oauth_token() {
		return sina_oauth_token;
	}

	public void setSina_oauth_token(String sina_oauth_token) {
		this.sina_oauth_token = sina_oauth_token;
	}

	public String getQq_uid() {
		return qq_uid;
	}

	public void setQq_uid(String qq_uid) {
		this.qq_uid = qq_uid;
	}

	public String getQq_oauth_token() {
		return qq_oauth_token;
	}

	public void setQq_oauth_token(String qq_oauth_token) {
		this.qq_oauth_token = qq_oauth_token;
	}

	public static Account getAccountFromCursor(Cursor cursor) {
		Account account = new Account();
		if (cursor.moveToNext()) {
			account.setUid(cursor.getInt(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_UID)));
			account.setAvatar(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_AVATAR)));
			account.setUname(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_UNAME)));
			account.setSessionid(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_SESSIONID)));
			account.setIs_new(cursor.getInt(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_IS_NEW)));
			account.setSina_oauth_token(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_SINA_OAUTH_TOKEN)));
			account.setSina_uid(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_SINA_UID)));
			account.setQq_oauth_token(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_QQ_OAUTH_TOKEN)));
			account.setQq_uid(cursor.getString(cursor
					.getColumnIndexOrThrow(AccountTable.COLUMN_UID)));
		}
		return account;
	}
}
