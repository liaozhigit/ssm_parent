<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<title>查询商品列表</title>
	<style>
		.ctl{
			table-layout:fixed
		}
		.ctl td{
			word-break:break-all
		}
		.over{
			position: fixed; left:0; top:0; width:100%; z-index:100;
		}
		.tempContainer{
			position:fixed; width:100%; margin-right:0px; margin-left:0px; text-align:center; z-index:101;
		}
	</style>
</head>
<body>

	<script type="text/javascript">
		$(function() {
			//prop和attr方法都是设置或者修改被选元素的属性，
			// prop方法用于HTML元素本身就带有的固有属性，
			// attr方法用于HTML元素自己定义的dom属性，

			$("#checkedAll").click(function() {

				if (this.checked) {

					$("[name=check]:checkbox").attr("checked", true);
				} else {

					$("[name=check]:checkbox").attr("checked", false);
				}
			});
			///////////////全选
			$("#checkboxAll").click(function() {

				$("[name=check]:checkbox").attr("checked", true);
			});
			///////////////全不选
			$("#checkedNo").click(function() {
				debugger;
				$("[name=check]:checkbox").attr("checked", false);
			});
			//////////////////反选
			$("#checkedRev").click(function() {

				$("[name=check]:checkbox").each(function() {
					console.log(this.checked);
					this.checked = !this.checked//this指当前的html对象
				});
			});
			/**
			$("input:button").click(
					function() {
						var ids = "";
						$("input:checkbox[name='check']:checked").each(
								function() {
									ids += $(this).val();
								});
						alert("已选择：" + ids);

					});
			
			 */

			$("#senJson")
					.click(
							function() {
								var checkBoxs = "";
								$("input:checkbox[name='check']:checked").each(
										function() {
											checkBoxs = $(this).val();
										});
								var items = {
									name : checkBoxs
								};
								//请求json响应json
								$
										.ajax({
											type : "post",
											url : "${pageContext.request.contextPath }/items/sendJson.action",
											contentType : "application/json;charset=utf-8",
											//	data:'{"name":ids,"price":99.9}',
											//	dataType:'text',
											dataType : "json",
											//	data : items,
											data : '{"name":"测试商品","price":99.9}',
											success : function(data) {
												alert(data.name);
											}
										});
							});

		})

        $(document).ready(function () {
            var imgsObj = $('.amplifyImg img');//需要放大的图像
            if(imgsObj){
                $.each(imgsObj,function(){
                    $(this).click(function(){
                        var currImg = $(this);
                        coverLayer(1);
                        var tempContainer = $('<div class=tempContainer></div>');//图片容器
                        with(tempContainer){//width方法等同于$(this)
                            appendTo("body");
                            var windowWidth=$(window).width();
                            var windowHeight=$(window).height();
                            //获取图片原始宽度、高度
                            var orignImg = new Image();
                            orignImg.src =currImg.attr("src") ;
                            var currImgWidth= orignImg.width;
                            var currImgHeight = orignImg.height;
                            if(currImgWidth<windowWidth){//为了让图片不失真，当图片宽度较小的时候，保留原图
                                if(currImgHeight<windowHeight){
                                    var topHeight=(windowHeight-currImgHeight)/2;
                                    if(topHeight>35){/*此处为了使图片高度上居中显示在整个手机屏幕中：因为在android,ios的微信中会有一个title导航，35为title导航的高度*/
                                        topHeight=topHeight-35;
                                        css('top',topHeight);
                                    }else{
                                        css('top',0);
                                    }
                                    html('<img border=0 src=' + currImg.attr('src') + '>');
                                }else{
                                    css('top',0);
                                    html('<img border=0 src=' + currImg.attr('src') + ' height='+windowHeight+'>');
                                }
                            }else{
                                var currImgChangeHeight=(currImgHeight*windowWidth)/currImgWidth;
                                if(currImgChangeHeight<windowHeight){
                                    var topHeight=(windowHeight-currImgChangeHeight)/2;
                                    if(topHeight>35){
                                        topHeight=topHeight-35;
                                        css('top',topHeight);
                                    }else{
                                        css('top',0);
                                    }
                                    html('<img border=0 src=' + currImg.attr('src') + ' width='+windowWidth+';>');
                                }else{
                                    css('top',0);
                                    html('<img border=0 src=' + currImg.attr('src') + ' width='+windowWidth+'; height='+windowHeight+'>');
                                }
                            }
                        }
                        tempContainer.click(function(){
                            $(this).remove();
                            coverLayer(0);
                        });
                    });
                });
            }
            else{
                return false;
            }
            //使用禁用蒙层效果
            function coverLayer(tag){
                with($('.over')){
                    if(tag==1){
                        css('height',$(document).height());
                        css('display','block');
                        css('opacity',1);
                        css("background-color","#FFFFFF");
                        css("background-color","rgba(0,0,0,0.7)" );  //蒙层透明度
                    }
                    else{
                        css('display','none');
                    }
                }
            }
        });
	</script>

	<p>
		<a href="${pageContext.request.contextPath }/items/add">增加</a>
	</p>
	<input type="button" value="senJson" id="senJson" />
	<form action="${pageContext.request.contextPath }/items/search"
		method="post">
		查询条件：
		<table width="100%" border=1>
			<tr width="100%">
				<!-- 如果Controller中接收的是Vo,那么页面上input框的name属性值要等于vo的属性.属性.属性..... -->
				<td>题目名称:<input type="text" name="name" /></td>
				<td>题目序号:<input type="text" name="price" /></td>
				<td><input type="submit" value="查询" /></td>
			</tr>
		</table>
	</form>
	商品列表：
	<table cellSpacing="0" cellpadding="1" width="100%" class="ctl" border=1>
		<tr>
			<td width="10%"><input type="button" name="all" id="checkboxAll" value="全 选">
				<input type="button" name="no" id="checkedNo" value="全不选"> <input
				type="button" name="reverse" id="checkedRev" value="反 选"> <input
				type="button" name="checkedAll" id="checkedAll" value="checkedAll"></td>
			<td width="10%">题目名称</td>
			<%--<td>题目路径</td>--%>
			<%--<td>题目序号</td>--%>
			<td width="40%">题目图片</td>
			<%--<td>生产日期</td>--%>
			<td width="30%">题目描述</td>
			<td width="10%">操作</td>
		</tr>
		<c:forEach items="${itemList }" var="item" varStatus="status">
			<tr>
				<!-- name属性名称要等于vo中的接收的属性名 -->
				<!-- 如果批量删除,可以用List<pojo>来接收,页面上input框的name属性值= vo中接收的集合属性名称+[list的下标]+.+list泛型的属性名称 -->
				<td width="10%"><input type="checkbox" name="check" value="${item.name }" />
					<input type="hidden" name="itemsList[${status.index }].id"
					value="${item.id }" /></td>
				<td width="10%">
					<textarea name="itemsList[${status.index }].name" style="overflow-x:hidden; overflow-y:auto; background:#fff; border:0;width:100%" cols="20" rows="15" >
							${item.name}
					</textarea>
				</td>
				<td width="40%">
					<c:if test="${item.pic !=null}">
					<div class="over"></div><!--背景层-->
					<div class="logoImg amplifyImg"><!--注意：此处的amlifyImg不可少-->
						<!-- 此处是引入图片的路径 -->
						<img name="itemsList[${status.index }].pic" src="/pic/${item.pic}"
							 width=100% height=100% />
					</div>
					</c:if>
				</td>
				<%--<td><input type="text"--%>
					<%--name="itemsList[${status.index }].createtime"--%>
					<%--value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></td>--%>
				<td width="30%">
					<textarea name="itemsList[${status.index }].detail" style="overflow-x:hidden; overflow-y:auto; background:#fff; border:0;width:100%" cols="50" rows="30" >${item.detail}
					</textarea>
				</td>
				<td width="10%">
					<a href="${pageContext.request.contextPath }/items/itemEdit/${item.id}">修改</a>
					<br/>
					<a href="${pageContext.request.contextPath }/items/delete/${item.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
</body>

</html>