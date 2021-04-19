<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<div class="footer">
	<div class="foot_con">
		<div class="blank"></div>

		<div class="blank"></div>
		<div id="bottomNav" class="rolling"></div>

		<div class="text" style="height: 1px; width: 1px; overflow: hidden;">
			<br />
		</div>

		<div class="record">
			&copy; 2021 ${title } 版权所有，并保留所有权利。
			<p style="color: #999;">
				江西省宜春市 Tel: 1111 111 111
			</p>
			<a href="admin/index.jsp" target="_blank">管理员入口</a>
		</div>
	</div>
	<div class="blank"></div>
</div>