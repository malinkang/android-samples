package com.mamating.bean;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.mamating.constants.UserTable;

@Table(name = UserTable.TABLE_NAME)
public class User extends BaseBean {
	@Column(name = UserTable.COLUMN_UID, unique = true)
	protected Integer uid;
	@Column(name = UserTable.COLUMN_AVATAR)
	protected String avatar;
	@Column(name = UserTable.COLUMN_UNAME)
	protected String uname;
	@Column(name = UserTable.COLUMN_SESSIONID)
	protected String sessionid;
	@Column(name = UserTable.COLUMN_IS_NEW)
	protected Integer is_new;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Integer getIs_new() {
		return is_new;
	}

	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", avatar=" + avatar + ", uname=" + uname
				+ ", sessionid=" + sessionid + ", is_new=" + is_new + "]";
	}

}
