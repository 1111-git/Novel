package com.entity;

public class Sections {
	private String sectionsid;
	private String title;
	private String novelid;
	private String contents;
	private String addtime;
	private String hits;
	private String thepre;
	private String thenext;
	private String novelname;

	public String getSectionsid() {
		return sectionsid;
	}

	public void setSectionsid(String sectionsid) {
		this.sectionsid = sectionsid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNovelid() {
		return this.novelid;
	}

	public void setNovelid(String novelid) {
		this.novelid = novelid;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getHits() {
		return this.hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getThepre() {
		return this.thepre;
	}

	public void setThepre(String thepre) {
		this.thepre = thepre;
	}

	public String getThenext() {
		return this.thenext;
	}

	public void setThenext(String thenext) {
		this.thenext = thenext;
	}

	public String getNovelname() {
		return novelname;
	}

	public void setNovelname(String novelname) {
		this.novelname = novelname;
	}
}
