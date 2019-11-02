<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日一抢商品</title>
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
				top.$.jBox.open("iframe:${ctx}/product/promote/form?code="+code+"&isView="+true,"每日一抢商品详情", 680, 360,{//宽高
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
		<li class="active"><a href="${ctx}/product/promote/">每日一抢商品列表</a></li>
		<shiro:hasPermission name="product:promote:edit"><li><a href="${ctx}/product/promote/form" >每日一抢商品添加</a></li></shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/product/promote/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>款号：</label>
		    	<input type="text" name="param.productDto.modelNum" value="${reqParam.param.productDto.modelNum}" class="input-medium" maxlength="100" placeholder="商品款号">
			</li>
			<li><label>商品名称：</label>
		    	<input type="text" name="param.productDto.name" value="${reqParam.param.productDto.name}" class="input-medium" maxlength="100" placeholder="商品名称">
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
				<th>款号</th>
				<th>商品名称</th>
				<th>ICON</th>
				<th>商品类目</th>
				<th>库存</th>
				<th>已售</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr id="${item.code}">
					<td title="${item.productDto.modelNum}"><a>${item.productDto.modelNum}</a></td>
					<td title="${item.productDto.name}"><a>${item.productDto.name}</a></td>
					<td>
						<c:if test="${!empty item.productDto.productIcon }">
							<div class="lafen-group">
							<img class="img-small" src="${fns:getUploadUrl()}${item.productDto.productIcon }" alt="">
					    	<%-- <div class="img-big">
					    		<img src="${fns:getUploadUrl()}${item.productDto.productIcon }" alt="">
					    	</div> --%>
					    	</div>
						</c:if>
					</td>
					<td>  
						 ${item.productDto.catalog.parentCatalogName }-
						 ${item.productDto.catalog.catalogName }
					</td>
					<td>  
						 ${item.productDto.cnt}
					</td>
					<td>  
						 ${item.productDto.saleCnt}
					</td>
					<td> <fmt:formatDate value="${item.openDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					<td> <fmt:formatDate value="${item.closeDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
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
					<shiro:hasPermission name="product:promote:edit"><td nowrap>
						<a href="${ctx}/product/promote/form?code=${item.code}&productCode=${item.productCode}">修改</a>
						<c:if test="${item.status=='0'}">
								<a href="${ctx}/product/promote/status?code=${item.code}&status=1" onclick="return confirmx('确定停用该商品吗？', this.href)">停用</a>
						</c:if>
						<c:if test="${item.status=='1'}">
								<a href="${ctx}/product/promote/status?code=${item.code}&status=0" onclick="return confirmx('确定取消冻结商品吗？', this.href)">启用</a>
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