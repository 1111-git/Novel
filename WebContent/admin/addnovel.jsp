<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<script type="text/javascript" src="js/novel.js" charset="utf-8"></script>
<script type="text/javascript" src="js/date.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="css/four.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function selimage(){
	window.open(
		"<%=basePath%>saveimage.jsp",
		"",
		"toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,copyhistory=no,scrollbars=yes,width=400,height=240,top="+(screen.availHeight-240)/2+",left="+(screen.availWidth-400)/2+""
	);
}
</script>
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
%>
<style>
td{
	text-align: center;
}
</style>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF">
				<form action="novel/addNovel.action" method="post" name="myform" onsubmit="return check()">
					<table width="60%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
						<tr align="center" bgcolor="#F2FDFF">
							<td align="left" colspan="2" class="optiontitle">添加小说</td>
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
							<td width="20%" align="right">小说名称</td>
							<td align="left"><input type="text" placeholder="请输入小说名称"
								name="novelname" style="width: 160px" id="novelname" /></td>
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
							<td width="20%" align="right">封面</td>
							<td align="left"><input type="text" placeholder="请选择图片"
								name="image" style="width: 160px" id="image"
								onclick="selimage();" readonly="readonly" /></td>
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
							<td width="20%" align="right">小说类型</td>
							<td align="left"><select name="cateid" style="width: 160px"
								id="cateid"><c:forEach items="${cateList}" var="cate">
										<option value="${cate.cateid}">${cate.catename }</option>
									</c:forEach></select></td>
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
						<td width="20%" align="right">小说作者</td>
						<c:if test="${adminname.equals('admin')}">
							<td align="left"><input type="text" placeholder="请输入小说作者"
								name="author" style="width: 160px" id="author" />
								</td>
						</c:if>
						<c:if test="${!adminname.equals('admin')}">
							<td align="left">
							<input type="text" value="${adminname }" readonly="readonly" name="author" style="width: 160px" id="author" />
							</td>
						</c:if>
						
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
							<td width="20%" align="right">小说介绍</td>
							<td align="left"><script type="text/javascript"
									src="ckeditor/ckeditor.js"></script>
								<textarea cols="80" name="contents" id="contents" rows="10">在此添加内容 </textarea>
								<script type="text/javascript">
									CKEDITOR.replace('contents', {
										language : 'zh-cn'
									});
								</script></td>
						</tr>
						<tr align="center" bgcolor="#ebf0f7">
							<td colspan="2">
							<input type="submit" name="Submit" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" name="Submit2" value="重置" /></td>
						</tr>
					</table>
				</form>
				</td>
		</tr>
	</table>
</body>
</html>