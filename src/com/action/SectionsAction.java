package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.NovelDAO;
import com.dao.SectionsDAO;
import com.entity.Novel;
import com.entity.Sections;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/sections")
public class SectionsAction {
	// 注入AdminDAO 并getter setter
	private SectionsDAO sectionsDAO;
	private NovelDAO novelDAO;

	// 准备添加数据
	@RequestMapping("createSections.action")
	public String createSections(Map<String, Object> map) {
		List<Novel> novelList = this.novelDAO.getAllNovel();
		map.put("novelList", novelList);
		return "admin/addsections";
	}

	// 添加数据
	@RequestMapping("addSections.action")
	public String addSections(Sections sections) {
		sections.setSectionsid(VeDate.getStringDatex());
		sections.setAddtime(VeDate.getStringDateShort());
		sections.setHits("0");
		Sections s = new Sections();
		s.setNovelid(sections.getNovelid());
		List<Sections> sList = this.sectionsDAO.getSectionsByCond(s);
		if (sList.size() == 0) {
			sections.setThepre("-");
			sections.setThenext("-");
		} else {
			s = new Sections();
			s.setNovelid(sections.getNovelid());
			s.setThenext("-");
			List<Sections> list = this.sectionsDAO.getSectionsByCond(s);
			Sections se = list.get(0);// 上一章
			se.setThenext(sections.getSectionsid());
			this.sectionsDAO.updateSections(se);
			sections.setThepre(se.getSectionsid());
			sections.setThenext("-");
		}
		this.sectionsDAO.insertSections(sections);
		return "redirect:/sections/createSections.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteSections.action")
	public String deleteSections(String id) {
		this.sectionsDAO.deleteSections(id);
		return "redirect:/sections/getAllSections.action";
	}

	// 更新数据
	@RequestMapping("updateSections.action")
	public String updateSections(Sections sections) {
		this.sectionsDAO.updateSections(sections);
		return "redirect:/sections/getAllSections.action";
	}

	// 显示全部数据
	@RequestMapping("getAllSections.action")
	public String getAllSections(String number, Map<String, Object> map) {
		List<Sections> sectionsList = new ArrayList<Sections>();
		List<Sections> tempList = new ArrayList<Sections>();
		tempList = this.sectionsDAO.getAllSections();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Sections sections = tempList.get(i);
			sectionsList.add(sections);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"sections/getAllSections.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append(
					"<a href=\"sections/getAllSections.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append(
					"<a href=\"sections/getAllSections.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"sections/getAllSections.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("sectionsList", sectionsList);
		return "admin/listsections";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("querySectionsByCond.action")
	public String querySectionsByCond(String cond, String name, Map<String, Object> map) {
		List<Sections> sectionsList = new ArrayList<Sections>();
		Sections sections = new Sections();
		if (cond != null) {
			if ("title".equals(cond)) {
				sections.setTitle(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
			if ("novelid".equals(cond)) {
				sections.setNovelid(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
			if ("contents".equals(cond)) {
				sections.setContents(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
			if ("addtime".equals(cond)) {
				sections.setAddtime(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
			if ("hits".equals(cond)) {
				sections.setHits(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
			if ("thepre".equals(cond)) {
				sections.setThepre(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
			if ("thenext".equals(cond)) {
				sections.setThenext(name);
				sectionsList = this.sectionsDAO.getSectionsByLike(sections);
			}
		}
		map.put("sectionsList", sectionsList);
		return "admin/querysections";
	}

	// 按主键查询数据
	@RequestMapping("getSectionsById.action")
	public String getSectionsById(String id, Map<String, Object> map) {
		Sections sections = this.sectionsDAO.getSectionsById(id);
		map.put("sections", sections);
		List<Novel> novelList = this.novelDAO.getAllNovel();
		map.put("novelList", novelList);
		return "admin/editsections";
	}

	public SectionsDAO getSectionsDAO() {
		return sectionsDAO;
	}

	public void setSectionsDAO(SectionsDAO sectionsDAO) {
		this.sectionsDAO = sectionsDAO;
	}

	public NovelDAO getNovelDAO() {
		return novelDAO;
	}

	public void setNovelDAO(NovelDAO novelDAO) {
		this.novelDAO = novelDAO;
	}
}
