package com.entity;

public class Topic {
	private String topicid;
	private String usersid;
	private String novelid;
	private String num;
	private String contents;
	private String addtime;
	private String username;
	private String novelname;

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getNovelid() {
		return this.novelid;
	}

	public void setNovelid(String novelid) {
		this.novelid = novelid;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNovelname() {
		return novelname;
	}

	public void setNovelname(String novelname) {
		this.novelname = novelname;
	}
}
