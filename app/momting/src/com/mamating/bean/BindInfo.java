package com.mamating.bean;

import java.util.ArrayList;

public class BindInfo {
	private ArrayList<Bind> respMsg;
	private Integer respCode;

	public ArrayList<Bind> getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(ArrayList<Bind> respMsg) {
		this.respMsg = respMsg;
	}

	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}
}
