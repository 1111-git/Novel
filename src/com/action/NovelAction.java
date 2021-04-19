package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.CateDAO;
import com.dao.NovelDAO;
import com.entity.Cate;
import com.entity.Novel;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/novel")
public class NovelAction {
	// 注入AdminDAO 并getter setter
	private NovelDAO novelDAO;
	private CateDAO cateDAO;

	// 准备添加数据
	@RequestMapping("createNovel.action")
	public String createNovel(Map<String, Object> map) {
		List<Cate> cateList = this.cateDAO.getAllCate();
		map.put("cateList", cateList);
		return "admin/addnovel";
	}

	// 添加数据
	@RequestMapping("addNovel.action")
	public String addNovel(Novel novel) {
		novel.setNovelid(VeDate.getStringDatex());
		novel.setAddtime(VeDate.getStringDateShort());
		novel.setHits("0");
		this.novelDAO.insertNovel(novel);
		return "redirect:/novel/createNovel.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteNovel.action")
	public String deleteNovel(String id) {
		this.novelDAO.deleteNovel(id);
		return "redirect:/novel/getAllNovel.action";
	}

	// 更新数据
	@RequestMapping("updateNovel.action")
	public String updateNovel(Novel novel) {
		this.novelDAO.updateNovel(novel);
		return "redirect:/novel/getAllNovel.action";
	}

	// 显示全部数据
	@RequestMapping("getAllNovel.action")
	public String getAllNovel(String number, Map<String, Object> map) {
		List<Novel> novelList = new ArrayList<Novel>();
		List<Novel> tempList = new ArrayList<Novel>();
		tempList = this.novelDAO.getAllNovel();
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
			Novel novel = tempList.get(i);
			novelList.add(novel);
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
			buffer.append("<a href=\"novel/getAllNovel.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"novel/getAllNovel.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"novel/getAllNovel.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"novel/getAllNovel.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("novelList", novelList);
		return "admin/listnovel";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryNovelByCond.action")
	public String queryNovelByCond(String cond, String name, Map<String, Object> map) {
		List<Novel> novelList = new ArrayList<Novel>();
		Novel novel = new Novel();
		if (cond != null) {
			if ("novelname".equals(cond)) {
				novel.setNovelname(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
			if ("image".equals(cond)) {
				novel.setImage(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
			if ("cateid".equals(cond)) {
				novel.setCateid(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
			if ("author".equals(cond)) {
				novel.setAuthor(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
			if ("addtime".equals(cond)) {
				novel.setAddtime(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
			if ("hits".equals(cond)) {
				novel.setHits(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
			if ("contents".equals(cond)) {
				novel.setContents(name);
				novelList = this.novelDAO.getNovelByLike(novel);
			}
		}
		map.put("novelList", novelList);
		return "admin/querynovel";
	}

	// 按主键查询数据
	@RequestMapping("getNovelById.action")
	public String getNovelById(String id, Map<String, Object> map) {
		Novel novel = this.novelDAO.getNovelById(id);
		map.put("novel", novel);
		List<Cate> cateList = this.cateDAO.getAllCate();
		map.put("cateList", cateList);
		return "admin/editnovel";
	}

	public NovelDAO getNovelDAO() {
		return novelDAO;
	}

	public void setNovelDAO(NovelDAO novelDAO) {
		this.novelDAO = novelDAO;
	}

	public CateDAO getCateDAO() {
		return cateDAO;
	}

	public void setCateDAO(CateDAO cateDAO) {
		this.cateDAO = cateDAO;
	}
}
