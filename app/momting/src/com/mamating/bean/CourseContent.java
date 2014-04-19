package com.mamating.bean;

public class CourseContent extends BaseBean {
	private String record;
	private String pic;
	private String user_record;

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUser_record() {
		return user_record;
	}

	public void setUser_record(String user_record) {
		this.user_record = user_record;
	}

	@Override
	public String toString() {
		return "CourseContent [record=" + record + ", pic=" + pic
				+ ", user_record=" + user_record + "]";
	}
}
