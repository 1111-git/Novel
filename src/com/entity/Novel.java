package com.entity;
public class Novel {
private String novelid;
private String novelname;
private String image;
private String cateid;
private String author;
private String addtime;
private String hits;
private String contents;
private String catename;
public String getNovelid() { return novelid; }
public void setNovelid(String novelid) { this.novelid = novelid; }
public String getNovelname() { return this.novelname; }
public void setNovelname(String novelname) { this.novelname = novelname; }
public String getImage() { return this.image; }
public void setImage(String image) { this.image = image; }
public String getCateid() { return this.cateid; }
public void setCateid(String cateid) { this.cateid = cateid; }
public String getAuthor() { return this.author; }
public void setAuthor(String author) { this.author = author; }
public String getAddtime() { return this.addtime; }
public void setAddtime(String addtime) { this.addtime = addtime; }
public String getHits() { return this.hits; }
public void setHits(String hits) { this.hits = hits; }
public String getContents() { return this.contents; }
public void setContents(String contents) { this.contents = contents; }
public String getCatename() { return catename; }
public void setCatename(String catename) { this.catename = catename; }
}
