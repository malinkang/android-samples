package com.mamating.bean;

import java.util.ArrayList;

/**
 * 
 * @author malinkang 2014年4月10日
 * 
 */

public class FollowingInfo {

	private ArrayList<Following> respMsg;

	public ArrayList<Following> getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(ArrayList<Following> respMsg) {
		this.respMsg = respMsg;
	}

	@Override
	public String toString() {
		return "FollowingInfo [respMsg=" + respMsg + "]";
	}
}
