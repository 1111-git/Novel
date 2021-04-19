package com.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import com.entity.Sections;

public class SectionsDAO {
	// sqlSessionTemplate 注入 在applicationContext.xml里定义
	private SqlSessionTemplate sqlSessionTemplate;

	// 插入数据 调用entity包sections.xml里的insertSections配置
	public void insertSections(Sections sections) {
		this.sqlSessionTemplate.insert("insertSections", sections);
	}

	// 更新数据 调用entity包sections.xml里的updateSections配置
	public void updateSections(Sections sections) {
		this.sqlSessionTemplate.update("updateSections", sections);
	}

	// 删除数据 调用entity包sections.xml里的deleteSections配置
	public void deleteSections(String sectionsid) {
		this.sqlSessionTemplate.delete("deleteSections", sectionsid);
	}

	// 查询全部数据 调用entity包sections.xml里的getAllSections配置
	public List<Sections> getAllSections() {
		return this.sqlSessionTemplate.selectList("getAllSections");
	}

	// 按照Admin类里面的值精确查询 调用entity包sections.xml里的getSectionsByCond配置
	public List<Sections> getSectionsByCond(Sections sections) {
		return this.sqlSessionTemplate.selectList("getSectionsByCond", sections);
	}

	// 按照Sections类里面的值模糊查询 调用entity包sections.xml里的getSectionsByLike配置
	public List<Sections> getSectionsByLike(Sections sections) {
		return this.sqlSessionTemplate.selectList("getSectionsByLike", sections);
	}

	// 按主键查询表返回单一的Sections实例 调用entity包sections.xml里的getSectionsById配置
	public Sections getSectionsById(String sectionsid) {
		return this.sqlSessionTemplate.selectOne("getSectionsById", sectionsid);
	}

	// IOC注入所需要的getter和setter
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
