<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改题目信息</title>

</head>
<body> 
	<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
	<form id="itemForm" action="${pageContext.request.contextPath }/items/updateitem" 
	method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${id }" /> 修改商品信息：
		<table width="100%" border=1>
			<tr>
				<td>题目名称</td>
				<td><input type="text" name="name" value="${name }" /></td>
			</tr>
			<tr>
				<td>题目序号</td>
				<td><input type="text" name="price" value="${price }" /></td>
				
			</tr>
			<%-- <tr>
				<td>题目路径</td>
				<td><input type="text" name="pic" value="${pic }" /></td>
				
			</tr> --%>
			<input name="pic" value="${pic}" type="hidden"/>
			<%--<tr>--%>
				<%--<td>商品生产日期</td>--%>
				<%--<td><input type="text" name="createtime"--%>
					<%--value="${createtime}"</td>--%>
			<%--</tr>--%>
			
			<tr>
				<td>商品图片</td>
				<td>
					<c:if test="${img_name !=null}">
						<img src="${img_name}" width=100 height=100/>
						<br/>
					</c:if>
					<input type="file"  name="pictureFile"/> 
				</td>
			</tr>
			 
			<tr>
				<td>题目描述</td>
				<td>
					<textarea name="detail" style="overflow-x:hidden; overflow-y:auto; background:#fff; border:0;width:100%" cols="50" rows="30" >${detail}
					</textarea>
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