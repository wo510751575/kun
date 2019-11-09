<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支付页面</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
	$(document).ready(function() {
		//生成模拟单
			$("#btnSubmit").click(function(){
				$.ajax({
                    type:"POST",
                    url:"${ctx}/order/order/create",
                    data:{mbrCode :$("#mbrCode").val()},
                    dataType:'JSON',
                    success:function(result){
                    	showTip("生成成功！");
                    }
                });
			  });
	});
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
.img-small {
	width: 40px;
	height: 40px;
}
.lafen-group {
    position: relative;
}
.lafen-group .img-big {
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -75px;
    margin-left: 35px;
    opacity: 0;
    transform: scale(.2, .2);
    transition: all .2s ease-in-out;
    width: 130px;
    height: 130px;
}
.lafen-group .img-small:hover + .img-big {
    transform: scale(1, 1);
    opacity: 1;
}
.lafen-group .img-big img {
    width: 130px;
    height: 130px;
}
</style>
</head>
<body>
<div class="container">
	<form id="inputForm"  action="#" method="post" class="form-horizontal">
		<input type="hidden" id="mbrCode" value="${mbrCode}">
		<input id="btnSubmit" class="btn btn-primary" type="button" value="生成模拟单"/>&nbsp;
	</form>
	
		<table id="treeTable" class="table table-striped table-bordered table-condensed ">
			<thead>
			<tr>
			<th>姓名</th>
			<th>账户</th>
			<th>二维码</th>
			<th>类型</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="item">
				<tr id="${item.code}">
					<td title="${item.name}">${item.name}</td>
					<td >${item.account}</td>
					<td >
						<c:if test="${!empty item.pid }">
							<div class="lafen-group">
							<c:choose>
				    		<c:when test="${fn:startsWith(item.pid, '/')}">
	   							<img class="img-small" src="${fns:getUploadUrl()}${item.pid}" alt="">
				    			<div class="img-big">
				    				<img  src="${fns:getUploadUrl()}${item.pid}" alt="">
				    			</div>
							</c:when>
							<c:otherwise>
								${item.pid}
							</c:otherwise>
				    		</c:choose>
				    		</div>
						</c:if>
					</td>
					<td> 
						<c:forEach items="${types}" var="p">
							<c:if test="${p.value eq item.type}">${p.chName }</c:if>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	 </div>
</body>
</html>