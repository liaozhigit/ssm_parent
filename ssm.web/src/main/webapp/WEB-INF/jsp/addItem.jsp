<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>

</head>
<body> 
	<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
	<form id="itemForm" action="${pageContext.request.contextPath }/items/save" 
	method="post" enctype="multipart/form-data">
<%-- 	<form id="itemForm"	action="${pageContext.request.contextPath }/items/updateitem.action" method="post"> --%>
		 修改商品信息：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name"  /></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price"  /></td>
			</tr>
			
			<tr>
				<td>商品生产日期</td>
				<td><input type="text" name="createtime"/></td>
			</tr>
			
			<tr>
				<td>商品图片</td>
				<td>
					<input type="file"  name="pictureFile"/> 
				</td>
			</tr>
			 
			<tr>
				<td>商品简介</td>
				<td><textarea rows="3" cols="30" name="detail"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" />
				</td>
			</tr>
		</table>

	</form>
</body>

</html>