<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/four.css" rel="stylesheet" type="text/css" />
</head>
<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "";
	}
	if (!message.trim().equals("")) {
		out.println("<script language='javascript'>");
		out.println("alert('" + message + "');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%><body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><table width="96%" border="0"
					align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="20" class="optiontitle">小说信息列表</td>
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">小说名称</td>
						<td align="center" bgcolor="#ebf0f7">小说类型</td>
						<td align="center" bgcolor="#ebf0f7">小说作者</td>
						<td align="center" bgcolor="#ebf0f7">发布日期</td>
						<td align="center" bgcolor="#ebf0f7">点击数</td>
						<td align="center" bgcolor="#ebf0f7" width="10%">操作</td>
					</tr>
					<c:if test="${adminname.equals('admin') }">
					<c:forEach items="${novelList}" var="novel">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${novel.novelname}</td>
							<td align="center">${novel.catename}</td>
							<td align="center">${novel.author}</td>
							<td align="center">${novel.addtime}</td>
							<td align="center">${novel.hits}</td>
							<td align="center"><a
								href="novel/getNovelById.action?id=${novel.novelid}">编辑</a>&nbsp;&nbsp;<a
								href="novel/deleteNovel.action?id=${novel.novelid}"
								onclick="{if(confirm('确定要删除吗?')){return true;}return false;}">删除</a></td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${!adminname.equals('admin') }">
					<c:forEach items="${novelList}" var="novel">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${novel.novelname}</td>
							<td align="center">${novel.catename}</td>
							<td align="center">${novel.author}</td>
							<td align="center">${novel.addtime}</td>
							<td align="center">${novel.hits}</td>
							<td align="center"><a
								href="novel/getNovelById.action?id=${novel.novelid}">编辑</a>&nbsp;&nbsp;<a
								href="novel/deleteNovel.action?id=${novel.novelid}"
								onclick="{if(confirm('确定要删除吗?')){return true;}return false;}">删除</a></td>
						</tr>
					</c:forEach>
					</c:if>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="20"><span style="float: left; color: red">${map.msg }</span>&nbsp;<span
							style="float: right;">${html}</span></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>