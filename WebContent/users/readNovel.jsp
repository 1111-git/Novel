<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
<style>
.boxCenterList{
    font-size: 24px; 
}
</style>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="<%=basePath%>">首页</a>
			<code> &gt; </code>
			${sections.title }
		</div>
	</div>
	<div class="blank"></div>
	<div class="block">
		<div class="box">
			<div class="box_1">
				<p style="font-size:24px;text-align:center;font-weight:bold;">${sections.title}</p>
			<div class="boxCenterList">${sections.contents }</div>
				<h3>
					<span >
						<center>
							<c:if test="${sections.thepre != '-' }">
								<a href="index/readNovel.action?id=${sections.thepre }"
									target="_self"> <img height=20 alt=上一页 src="images/pre.gif"
									width=70 align=absmiddle border=0 />
								</a>
							</c:if>
							&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							<c:if test="${sections.thenext != '-' }">
								<a href="index/readNovel.action?id=${sections.thenext }"
									target="_self"> <img height=20 alt=下一页
									src="images/next.gif" width=70 align=absmiddle border=0 />
								</a>
							</c:if>
						</center>
					</span>
				</h3>
			</div>
		</div>
		<div class="blank5"></div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
