package com.mamating.bean;

public class UserInfo {
	private Account respMsg;
	private int respCode;

	public Account getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(Account respMsg) {
		this.respMsg = respMsg;
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	@Override
	public String toString() {
		return "UserInfo [respMsg=" + respMsg + ", respCode=" + respCode + "]";
	}

}
