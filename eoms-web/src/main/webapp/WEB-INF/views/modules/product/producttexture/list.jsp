<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品材质管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3}).show();
		});
		$(document).ready(function() {
			//详情
			$('.view_btn').click(function() {
				var code = $(this).attr("data-code");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/product/producttexture/form?code="+code+"&isView="+true,"商品材质详情", 680, 360,{//宽高
					id:9527,
					draggable: true,
					showClose: true,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
						top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
						top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
					},
					closed: function () { 
					} /* 信息关闭后执行的函数 */
				});
		    });
		});
		//跳页
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
	
	<style type="text/css">
.container {
	padding: 20px 30px;
	width: 100%;
	min-height: 800px;
	background: #fff;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.page_header {
	font-size: 32px;
	font-weight: normal;
	line-height: 1;
	padding-bottom: 40px;
	color: #666;
}
.nav-tabs > li > a {
    padding-top: 0px;
}
</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/producttexture/">商品材质列表</a></li>
		<shiro:hasPermission name="product:producttexture:edit"><li><a href="${ctx}/product/producttexture/form" >商品材质添加</a></li></shiro:hasPermission>
	</ul>
		<form id="searchForm" action="${ctx}/product/producttexture/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>材质名称：</label>
		    	<input type="text" name="param.textureName" value="${reqParam.param.textureName}" class="input-medium" maxlength="100" placeholder="商品材质名称">
			</li>
			<li><label>状态：</label>
				<select style="width: 177px;" name="param.status">
                    <option value="">全部</option>
                    <c:forEach items="${statuss}" var="item">
						<option value="${item.value}"
							<c:if test="${item.value eq reqParam.param.status}">selected="selected"</c:if>>${item.chName}</option>
					</c:forEach>
                </select>
			</li>
		 	<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide">
			<thead><tr>
				<th>材质名称</th>
				<th>状态</th>
				<th>录入时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr id="${item.code}">
					<td title="${item.textureName}"><a>${item.textureName}</a></td>
<%-- 					<td>${item.textureName}</td> --%>
					<td>  
						<c:forEach items="${statuss}" var="p">
							<c:choose> 
								<c:when test="${p.value == item.status}">
									<span class="label label-${item.status eq '0'?'success':'danger'}">${p.chName}</span>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
					<td> <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
					<shiro:hasPermission name="product:producttexture:edit"><td nowrap>
						<a href="${ctx}/product/producttexture/form?code=${item.code}">修改</a>
						<c:if test="${item.status=='0'}">
								<a href="${ctx}/product/producttexture/status?code=${item.code}&status=1" onclick="return confirmx('确定停用商品材质吗？', this.href)">停用</a>
						</c:if>
						<c:if test="${item.status=='1'}">
								<a href="${ctx}/product/producttexture/status?code=${item.code}&status=0" onclick="return confirmx('确定取消冻结商品材质吗？', this.href)">启用</a>
						</c:if>
					</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach></tbody>
		</table>
		<div class="pagination">${page}</div>
	
	 </div>
</body>
</html>