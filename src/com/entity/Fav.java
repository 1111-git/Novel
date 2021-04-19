package com.entity;

public class Fav {
	private String favid;
	private String usersid;
	private String novelid;
	private String addtime;
	private String username;
	private String novelname;

	public String getFavid() {
		return favid;
	}

	public void setFavid(String favid) {
		this.favid = favid;
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
