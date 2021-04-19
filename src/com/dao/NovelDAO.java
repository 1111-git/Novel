package com.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import com.entity.Novel;

public class NovelDAO {
	// sqlSessionTemplate 注入 在applicationContext.xml里定义
	private SqlSessionTemplate sqlSessionTemplate;

	// 插入数据 调用entity包novel.xml里的insertNovel配置
	public void insertNovel(Novel novel) {
		this.sqlSessionTemplate.insert("insertNovel", novel);
	}

	// 更新数据 调用entity包novel.xml里的updateNovel配置
	public void updateNovel(Novel novel) {
		this.sqlSessionTemplate.update("updateNovel", novel);
	}

	// 删除数据 调用entity包novel.xml里的deleteNovel配置
	public void deleteNovel(String novelid) {
		this.sqlSessionTemplate.delete("deleteNovel", novelid);
	}

	// 查询全部数据 调用entity包novel.xml里的getAllNovel配置
	public List<Novel> getAllNovel() {
		return this.sqlSessionTemplate.selectList("getAllNovel");
	}

	public List<Novel> getHotNovel() {
		return this.sqlSessionTemplate.selectList("getHotNovel");
	}

	public List<Novel> getNewsNovel() {
		return this.sqlSessionTemplate.selectList("getNewsNovel");
	}

	public List<Novel> getFrontNovel(String cateid) {
		return this.sqlSessionTemplate.selectList("getFrontNovel", cateid);
	}

	// 按照Admin类里面的值精确查询 调用entity包novel.xml里的getNovelByCond配置
	public List<Novel> getNovelByCond(Novel novel) {
		return this.sqlSessionTemplate.selectList("getNovelByCond", novel);
	}

	// 按照Novel类里面的值模糊查询 调用entity包novel.xml里的getNovelByLike配置
	public List<Novel> getNovelByLike(Novel novel) {
		return this.sqlSessionTemplate.selectList("getNovelByLike", novel);
	}

	// 按主键查询表返回单一的Novel实例 调用entity包novel.xml里的getNovelById配置
	public Novel getNovelById(String novelid) {
		return this.sqlSessionTemplate.selectOne("getNovelById", novelid);
	}

	// IOC注入所需要的getter和setter
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
