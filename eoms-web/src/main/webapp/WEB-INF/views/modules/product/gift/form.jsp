<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员礼品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#giftName").val($("#giftSec option:selected").text());
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
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
</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/gift/">会员礼品列表</a></li>
		<li class="active"><a href="${ctx}/product/gift/form?code=${data.code}">会员礼品<shiro:hasPermission name="product:gift:edit">${not empty data.code?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="product:gift:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> 
	<form id="inputForm"  action="${ctx}/product/gift/${not empty data.code?'edit':'save'}" method="post" class="form-horizontal">
		<input type="hidden" name="code" value="${data.code}">
		<input id="giftName" type="hidden" name="giftName" value="${data.giftName}">
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">所属会员礼包:</label>
			<div class="controls">
				<select id="giftSec" name="giftCode" class="required">
						<c:forEach items="${memberRankGifts}" var="p">
								<option value="${p.value}" <c:if test="${p.value eq data.giftCode}">selected="selected"</c:if> >${p.name}</option>
						</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">礼品名称:</label>
			<div class="controls">
				<input type="text" name="name" value="${data.name}" maxlength="32" class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<select name="status" class="required">
					<option></option>
						<c:forEach items="${statuss}" var="p">
								<c:choose >
									<c:when test="${p.value eq data.status}">
										<option value="${p.value }" selected="selected">${p.chName}</option>
									</c:when>
									<c:otherwise><option value="${p.value }">${p.chName}</option></c:otherwise>
								</c:choose>
							</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="member:member:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	</div>
</body>
</html>