package com.mamating.bean;

import java.util.ArrayList;

public class Course extends BaseBean {

	private Integer course_id;// 课程id
	private String name;
	private String cover;
	private String author;// 作者名字
	private Integer is_study;
	private Integer is_select;

	private ArrayList<CourseContent> content;

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getIs_study() {
		return is_study;
	}

	public void setIs_study(Integer is_study) {
		this.is_study = is_study;
	}

	public Integer getIs_select() {
		return is_select;
	}

	public void setIs_select(Integer is_select) {
		this.is_select = is_select;
	}

	public ArrayList<CourseContent> getContent() {
		return content;
	}

	public void setContent(ArrayList<CourseContent> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", name=" + name + ", cover="
				+ cover + ", is_study=" + is_study + ", is_select=" + is_select
				+ ", content=" + content + "]";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
