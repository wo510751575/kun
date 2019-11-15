<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
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
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/member/">人员列表</a></li>
	</ul><br/>
	 	<div class="store-details">
			<table class="table-form" style="width: 100%;">
				<tbody>
					<tr>
						<td style="padding: 10px;font-weight: bold;width: 90px;text-align:right;">客户名称 ：</td>
						<td  >${data.name}</td>
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">支付宝点位：</td>
						<td> 
						 	${data.grade}
						</td
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">设备号 ：</td>
						<td>${data.wxNo}</td>
					</tr>
					<tr>
						<td  style="padding: 10px;font-weight: bold;text-align:right;">接单总数量 ：</td>
						<td>${totalCount}</td>
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">接单总金额 ：</td>
						<td> 
						 	 ${totalAmt}
						</td>
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">接单成功率：</td>
						<td>
							${successCalc}
						</td>
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">补单率 ：</td>
						<td> 
						 	 ${bdCalc}
						</td>
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">收入金额：</td>
						<td>
								${incomeAmt}
						</td>
					</tr>
					<tr>
						<td style="padding: 10px;font-weight: bold;text-align:right;">收款码列表：</td>
						<td>
							<c:forEach items="${accList}" var="item" >
				    				${item.name}：<img  src="${fns:getUploadUrl()}${item.pid}" width="100px" height="100px" alt=""><br/>   
					    	</c:forEach>
						</td>
					</tr>
				</tbody>
			</table>
	</div>
	</div>
	
</body>
</html>