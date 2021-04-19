package com.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.ArticleDAO;
import com.dao.CateDAO;
import com.dao.FavDAO;
import com.dao.NovelDAO;
import com.dao.SectionsDAO;
import com.dao.TopicDAO;
import com.dao.UsersDAO;
import com.entity.Article;
import com.entity.Cate;
import com.entity.Fav;
import com.entity.Novel;
import com.entity.Sections;
import com.entity.Topic;
import com.entity.Users;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/index")
public class IndexAction extends BaseAction {

	private ArticleDAO articleDAO;
	private UsersDAO usersDAO;
	private CateDAO cateDAO;
	private FavDAO favDAO;
	private NovelDAO novelDAO;
	private SectionsDAO sectionsDAO;
	private TopicDAO topicDAO;

	// 公共方法 提供公共查询数据
	private void front() {
		getRequest().setAttribute("title", "小说阅读网站");
		List<Cate> cateList = this.cateDAO.getAllCate();
		this.getRequest().setAttribute("cateList", cateList);
	}

	// 首页显示
	@RequestMapping("index.action")
	public String index() {
		this.front();
		List<Article> articleList = this.articleDAO.getFrontArticle();
		this.getRequest().setAttribute("articleList", articleList);
		List<Cate> cList = this.cateDAO.getFrontCate();
		List<Cate> frontList = new ArrayList<Cate>();
		for (Cate cate : cList) {
			List<Novel> novelList = this.novelDAO.getFrontNovel(cate.getCateid());
			cate.setNovelList(novelList);
			frontList.add(cate);
		}
		this.getRequest().setAttribute("frontList", frontList);
		List<Novel> newsList = this.novelDAO.getNewsNovel();
		this.getRequest().setAttribute("newsList", newsList);
		return "users/index";
	}

	// 分类查询
	@RequestMapping("cate.action")
	public String cate(String id) {
		this.front();
		Novel novel = new Novel();
		novel.setCateid(id);
		List<Novel> novelList = this.novelDAO.getNovelByCond(novel);
		this.getRequest().setAttribute("novelList", novelList);
		return "users/list";
	}

	// 模糊查询
	@RequestMapping("query.action")
	public String query() {
		this.front();
		String name = this.getRequest().getParameter("name");
		Novel novel = new Novel();
		novel.setNovelname(name);
		novel.setAuthor(name);
		List<Novel> novelList = this.novelDAO.getNovelByLike(novel);
		this.getRequest().setAttribute("novelList", novelList);
		return "users/list";
	}

	// 全部查询
	@RequestMapping("all.action")
	public String all() {
		this.front();
		List<Novel> novelList = this.novelDAO.getAllNovel();
		this.getRequest().setAttribute("novelList", novelList);
		return "users/list";
	}

	// 小说详细
	@RequestMapping("detail.action")
	public String detail(String id) {
		this.front();
		Novel novel = this.novelDAO.getNovelById(id);
		novel.setHits("" + (Integer.parseInt(novel.getHits()) + 1));
		this.novelDAO.updateNovel(novel);
		Sections section = new Sections();
		section.setNovelid(id);
		List<Sections> sectionsList = this.sectionsDAO.getSectionsByCond(section);
		Topic topic = new Topic();
		topic.setNovelid(id);
		List<Topic> topicList = this.topicDAO.getTopicByCond(topic);
		this.getRequest().setAttribute("novel", novel);
		this.getRequest().setAttribute("topicList", topicList);
		this.getRequest().setAttribute("tnum", topicList.size());
		this.getRequest().setAttribute("sectionsList", sectionsList);
		return "users/detail";
	}

	@RequestMapping("readNovel.action")
	public String readNovel(String id) {
		this.front();
		Sections sections = this.sectionsDAO.getSectionsById(id);
		sections.setHits("" + (Integer.parseInt(sections.getHits()) + 1));
		this.sectionsDAO.updateSections(sections);
		this.getRequest().setAttribute("sections", sections);
		return "users/readNovel";
	}

	@RequestMapping("addTopic.action")
	public String addTopic() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Topic topic = new Topic();
		topic.setTopicid(VeDate.getStringDatex());
		topic.setAddtime(VeDate.getStringDateShort());
		topic.setContents(this.getRequest().getParameter("contents"));
		topic.setNovelid(this.getRequest().getParameter("novelid"));
		topic.setNum(this.getRequest().getParameter("num"));
		topic.setUsersid(userid);
		this.topicDAO.insertTopic(topic);
		return "redirect:/index/detail.action?id=" + topic.getNovelid();
	}

	@RequestMapping("addFav.action")
	public String addFav() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setUsersid(userid);
		fav.setAddtime(VeDate.getStringDateShort());
		fav.setFavid(VeDate.getStringDatex());
		fav.setNovelid(this.getRequest().getParameter("id"));
		this.favDAO.insertFav(fav);
		return "redirect:/index/myFav.action";
	}

	@RequestMapping("myFav.action")
	public String myFav() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setUsersid(userid);
		List<Fav> favList = this.favDAO.getFavByCond(fav);
		this.getRequest().setAttribute("favList", favList);
		return "users/myFav";
	}

	@RequestMapping("deleteFav.action")
	public String deleteFav() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.favDAO.deleteFav(this.getRequest().getParameter("id"));
		return "redirect:/index/myFav.action";
	}

	// 新闻公告
	@RequestMapping("article.action")
	public String article(String id) {
		this.front();
		List<Article> articleList = this.articleDAO.getAllArticle();
		this.getRequest().setAttribute("articleList", articleList);
		return "users/article";
	}

	// 阅读公告
	@RequestMapping("read.action")
	public String read(String id) {
		this.front();
		Article article = this.articleDAO.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleDAO.updateArticle(article);
		this.getRequest().setAttribute("article", article);
		return "users/read";
	}

	// 准备登录
	@RequestMapping("preLogin.action")
	public String prelogin() {
		this.front();
		return "users/login";
	}

	// 用户登录
	@RequestMapping("login.action")
	public String login() {
		this.front();
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersDAO.getUsersByCond(u);
		if (usersList.size() == 0) {
			this.getSession().setAttribute("msg", "用户名不存在");
			return "redirect:/index/preLogin.action";
		} else {
			Users users = usersList.get(0);
			if (password.equals(users.getPassword())) {
				this.getSession().setAttribute("userid", users.getUsersid());
				this.getSession().setAttribute("username", users.getUsername());
				this.getSession().setAttribute("users", users);
				return "redirect:/index/usercenter.action";
			} else {
				this.getSession().setAttribute("msg", "密码错误");
				return "redirect:/index/preLogin.action";
			}
		}
	}

	// 准备注册
	@RequestMapping("preReg.action")
	public String preReg() {
		this.front();
		return "users/register";
	}

	// 用户注册
	@RequestMapping("register.action")
	public String register(Users users) {
		this.front();
		Users u = new Users();
		u.setUsername(users.getUsername());
		List<Users> usersList = this.usersDAO.getUsersByCond(u);
		if (usersList.size() == 0) {
			users.setUsersid("" + VeDate.getStringDatex());
			users.setRegdate(VeDate.getStringDateShort());
			this.usersDAO.insertUsers(users);
		} else {
			this.getSession().setAttribute("msg", "用户名已存在");
			return "redirect:/index/preReg.action";
		}
		return "redirect:/index/preLogin.action";
	}

	// 退出登录
	@RequestMapping("exit.action")
	public String exit() {
		this.front();
		this.getSession().removeAttribute("userid");
		this.getSession().removeAttribute("username");
		return "index";
	}

	// 用户中心
	@RequestMapping("usercenter.action")
	public String usercenter() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Users users = this.usersDAO.getUsersById(userid);
		this.getRequest().setAttribute("user", users);
		return "users/usercenter";
	}

	// 用户中心
	@RequestMapping("personal.action")
	public String personal() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Users users = this.usersDAO.getUsersById(userid);
		users.setBirthday(this.getRequest().getParameter("birthday"));
		users.setContact(this.getRequest().getParameter("contact"));
		users.setRealname(this.getRequest().getParameter("realname"));
		users.setSex(this.getRequest().getParameter("sex"));
		this.usersDAO.updateUsers(users);
		this.getSession().setAttribute("users", users);
		return "redirect:/index/usercenter.action";
	}

	// 准备修改密码
	@RequestMapping("prePwd.action")
	public String prePwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/editpwd";
	}

	// 修改密码
	@RequestMapping("editpwd.action")
	public String editpwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		String password = this.getRequest().getParameter("password");
		String repassword = this.getRequest().getParameter("repassword");
		Users users = this.usersDAO.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			this.usersDAO.updateUsers(users);
		} else {
			this.getSession().setAttribute("msg", "旧密码错误");
			return "redirect:/index/prePwd.action";
		}
		return "redirect:/index/prePwd.action";
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public CateDAO getCateDAO() {
		return cateDAO;
	}

	public void setCateDAO(CateDAO cateDAO) {
		this.cateDAO = cateDAO;
	}

	public FavDAO getFavDAO() {
		return favDAO;
	}

	public void setFavDAO(FavDAO favDAO) {
		this.favDAO = favDAO;
	}

	public NovelDAO getNovelDAO() {
		return novelDAO;
	}

	public void setNovelDAO(NovelDAO novelDAO) {
		this.novelDAO = novelDAO;
	}

	public SectionsDAO getSectionsDAO() {
		return sectionsDAO;
	}

	public void setSectionsDAO(SectionsDAO sectionsDAO) {
		this.sectionsDAO = sectionsDAO;
	}

	public TopicDAO getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

}
