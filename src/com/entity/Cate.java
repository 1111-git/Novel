package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Cate {
	private String cateid;
	private String catename;
	private List<Novel> novelList = new ArrayList<Novel>();

	public List<Novel> getNovelList() {
		return novelList;
	}

	public void setNovelList(List<Novel> novelList) {
		this.novelList = novelList;
	}

	public String getCateid() {
		return cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}
}
