<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每周一抢</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript">
   
    </script>
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.container #tableDiv{
		position:absolute;
		min-height:500px;
		overflow-x:visible;
		width:60%;  
		background-color:white;
		/* opacity:0.5; */
		top:60px;
		left:100px;
	 	display: none;
	 	border: 0.5px solid black;
	}
    </style>
	<script type="text/javascript">	
		$(document).ready(function() {
		});
		//跳页
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
<div class="container" id="tableDiv";overflow-x:visible;overflow-y:scroll;">
		<div class="control-group" >
		<form id="searchForm" action="${ctx}/product/promote/view" method="post" class="breadcrumb form-search" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form" style="display:flex;justify-content:space-between; width:100%; align-items:center;">
		    <li><label>款号：&nbsp;&nbsp;</label>
		    	<input type="text" name="param.modelNum" value="${reqParam.param.modelNum}" class="input-medium" maxlength="100" placeholder="商品款号">
		    	<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="submitClick()"/>
			</li>
		</ul>
		</form>
		<tags:message content="${message}"/>
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
				<thead><tr>
						<th>选择</th>
						<th>款号</th>
						<th>商品名称</th>
						<th>ICON</th>
						<th>商品类名</th>
						<th>库存</th>
						</tr>
				</thead>
				<tbody id="infolist">
					<c:forEach items="${page.list}" var="item">
					<tr id="${item.code}">
					<td><input type="radio" name="selectbox" value="${item.code}===${item.modelNum}" /></td>
					<td title="${item.modelNum}"><a>${item.modelNum}</a></td>
					<td>  
						 ${item.name}
					</td>
					<td>
						<c:if test="${!empty item.productIcon }">
							<div class="lafen-group">
							<img class="img-small" src="${fns:getUploadUrl()}${item.productIcon}" alt="">
					    	</div>
						</c:if>
					</td>
					<td>  
						 ${item.catalog.parentCatalogName }-
						 ${item.catalog.catalogName }
					</td>
					<td>  
						 ${item.saleCnt}
					</td>
					</tr>
					</c:forEach></tbody>
			</table>
		<div class="pagination">${page}</div>
		<div class="form-actions" style="text-align:center; width:100%;">
			<shiro:hasPermission name="member:member:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="marksure()" value="确定"/>&nbsp;&nbsp;
			</shiro:hasPermission>
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/> -->
		</div>
</div>

<script>
	function marksure(){
		//获取productCode 和 modelNum 
		var valArr = new Array;
		var mnArr  = new Array;
		$("input[name='selectbox']:radio:checked").each(function(i){ 
			/* alert($(this).val()); */
		 	var object = $(this).val().split("==="); 
			valArr[i] = object[0];
			mnArr[i] = object[1];
	    });  
		
		var code = valArr.join(',');//转换为逗号隔开的字符串
		if(!code){
			alertx("请选择商品!");
			return false;
		}
		window.parent.window.productcode = code; 
		window.parent.window.modelnum = mnArr.join(',');
		window.parent.window.jBox.close();  
	}
</script>
<script type="text/javascript">

</script>
</body>
</html>