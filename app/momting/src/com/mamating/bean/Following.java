package com.mamating.bean;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.mamating.constants.FollowingTable;

/**
 * 关注人
 * 
 * @author malinkang 2014年4月10日
 * 
 */
@Table(name = FollowingTable.TABLE_NAME)
public class Following extends BaseBean {
	@Column(name = FollowingTable.COLUMN_FOLLOW_ID)
	private String follow_id;
	@Column(name = FollowingTable.COLUMN_UID)
	private String uid;
	@Column(name = FollowingTable.COLUMN_UNAME)
	private String uname;
	@Column(name = FollowingTable.COLUMN_AVATAR)
	private String avatar;
	private FollowState follow_state;
	@Column(name = FollowingTable.COLUMN_FOLLOW_STATE_JSON)
	private String follow_state_json;

	public String getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(String follow_id) {
		this.follow_id = follow_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public FollowState getFollow_state() {
		return follow_state;
	}

	public void setFollow_state(FollowState follow_state) {
		this.follow_state = follow_state;

	}

	public String getFollow_state_json() {
		return follow_state_json;
	}

	public void setFollow_state_json(String follow_state_json) {
		this.follow_state_json = follow_state_json;
	}

	@Override
	public String toString() {
		return "Following [follow_id=" + follow_id + ", uid=" + uid
				+ ", uname=" + uname + ", avatar=" + avatar + ", follow_state="
				+ follow_state + "]";
	}

	public static FollowState getFollowStateFromJson(String json) {
		return mGson.fromJson(json, FollowState.class);
	}
}
