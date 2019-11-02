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
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
    .photo-file{
	 	position: absolute;
	    top: 350px;
	    left: 190px;
	    opacity: 0;
	    filter: alpha(opacity:0);
	    cursor:pointer;
	}
	img {
	    border: 0 none;
	    height: 80px;
	    max-width: 100%;
	    vertical-align: middle;
	}
	.pre-container {
    float: right;
    width: 354px;
    height: 630px;
    background: url(${ctxStatic}/images/iphone.png) no-repeat;
    background-size: 100% auto;
	}
	
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	#product_img_bt img{
	float: left;
	}
	.close-Icon{
	position: absolute;
    background: url(${ctxStatic}/images/closeImg.png) no-repeat;
    z-index: 100;
    width: 20px;
    height: 20px;
    background-size: 100%;
    right: -7px;
    top: -7px;
       cursor: pointer;
	}
	
    </style>
	<script type="text/javascript">
		String productcode = null;
		String modelnum = null;
		$(document).ready(function() {
			$("#inputForm").validate({
			    ignore: [],
				submitHandler: function(form){

					loading('正在提交，请稍等...');
	                form.submit();
	                return false;
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
<div class="container" style="height:950px;overflow-x:visible;overflow-y:scroll;">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/promote/">每周一抢列表</a></li>
		<li class="active"><a href="${ctx}/product/promote/form?code=${data.code}">每周一抢<shiro:hasPermission name="product:promote:edit">${not empty data.code?'修改':'新增'}</shiro:hasPermission><shiro:lacksPermission name="product:product:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> 
	<form id="inputForm"  action="${ctx}/product/promote/${not empty data.code?'edit':'save'}" method="post" class="form-horizontal" οnsubmit="saveCheck()">
		<input type="hidden" name="code" value="${data.code}">
		<input class="control-pcode" type="hidden" name="productCode" value="${data.productCode}">
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">款号:</label>
			<div class="controls">
				<input class="required" id="modelnumInput" type="button" style="width:300px;height:30px;" value="${data.productDto.modelNum}" name="productDto.modelNum" onclick="showTable()" οnblur="checkModelNum(this)"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${empty sku}">
			<div class="control-group">
				<label class="control-label">价格:</label>
				<div class="controls">
			       <table id="priceTable" class="table-form" style="width:70%;" >
			       </table>
				</div>
			</div>
		</c:if>
		
		<c:if test="${!empty sku}">
			<div class="control-group">
				<label class="control-label">价格:</label>
					<div class="controls">
				       <table id="priceTable" class="table-form" style="width:70%;" >
					       	<tr style="font-weight: bold;">
 					       		<td>克重</td>
					       		<td>零售价(￥)</td>
					       		<td>一级佣金(￥)</td> 
								<td>二级佣金(￥)</td>
					       		<td>库存</td>
					       		<td>折扣价(￥)</td>
					       		<td>是否默认</td>
					       		<!-- <td>操作</td> -->
					       	</tr>
					       	<c:forEach items="${sku}" var="p" varStatus="status">
				       		<tr>
				       			<td><input id="sku_${p.code }" name="skucode" value="${data.productDto.weight}" maxlength="10" class="required number" style="width: 80px;" readonly="readonly"/><input type="hidden" id="sku_code_${p.code }" name="skus[${status.index}].code" value="${p.code}" maxlength="10" class="required number"/></td> 
					       		<td><input id="sku_salePrice_${p.code }" name="skus[${status.index}].salePrice" value="${p.salePrice}" maxlength="10" class="required number"  style="width: 80px;" readonly="readonly"/></td>
								<td><input id="sku_oneprice_${p.code }" name="skus[${status.index}].onePrice" value="${p.onePrice}" maxlength="10" class="required number"  style="width: 80px;" readonly="readonly"/></td> 
					       		<td><input id="sku_twoPrice_${p.code }" name="skus[${status.index}].twoPrice" value="${p.twoPrice}" maxlength="10" class="required number" style="width: 80px;" readonly="readonly"/></td>
					       		<td><input id="sku_cnt_${p.code }" name="skus[${status.index}].cnt" value="${p.cnt}" maxlength="10" class="required digits" style="width: 80px;" readonly="readonly"/></td>
					       		<td><input id="sku_orgPrice_${p.code }" name="skus[${status.index}].orgPrice" value="${p.orgPrice}" maxlength="10" class="required number" style="width: 80px;"/></td>
					       		<td>
					       			<c:if test="${p.isDefault eq '0'}">
					       				<input type="radio" id="sku_isDefault_${p.code }" name="skuIsDefault" checked="checked">
					       			</c:if>
					       			<c:if test="${!(p.isDefault eq '0')}">
					       				<input type="radio" id="sku_isDefault_${p.code }" name="skuIsDefault" >
					       			</c:if>
					       		</td>
				       		</tr>
				       		</c:forEach>
				       </table>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">活动时间:</label>
			<div class="controls">
			<input id="billStart" style="width:200px;" name="openDate" type="text"  maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${data.openDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({minDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,autoShowQS:false});" autocomplete="off"/>
				---
			<input id="billStart" style="width:200px;" name="closeDate" type="text"  maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${data.closeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({minDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,autoShowQS:false});" autocomplete="off"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<select name="status" class="required">
					<option></option>
						<c:forEach items="${prodcutStatuss}" var="p">
								<c:choose >
									<c:when test="${p.value eq data.status}">
										<option value="${p.value }" selected="selected">${p.chName}</option>
									</c:when>
									<c:otherwise><option  value="${p.value }">${p.chName}</option></c:otherwise>
								</c:choose>
							</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="member:member:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="save()" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>

</div>

<script>

	function showTable(){
		$.jBox.open("iframe:${ctx}/product/promote/view","商品列表", 820, 680,{//宽高
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
				/* 信息关闭后执行的函数 */
				if(productcode != null){
				   $("#modelnumInput").attr("value",modelnum);
				   $(".control-pcode").attr("value",productcode);
				   reloadTable();
				}
			},
		});
	}
	
	function reloadTable(){
	 	var param = new Object();
		param.productCode = productcode;
		param.modelNum = modelnum; 
		$.ajax({
			  type: 'POST',
			  url: "${ctx}/product/promote/sku/list",
			  data: param,
			  success: function(data){ 
				  $("#priceTable").html('');
				  var htmls = '<tr style="font-weight: bold;">';
				 	  htmls+= '<td>克重</td>';
			          htmls+= '<td>零售价(￥)</td>';
			          htmls+= '<td>一级佣金(￥)</td>';
			          htmls+= '<td>二级佣金(￥)</td>';
			          htmls+= '<td>库存</td>';
			          htmls+= '<td>折扣价(￥)</td>';
			          htmls+= '<td>是否默认</td></tr>';
			          htmls+= '</tr>';
				  for (var i = 0; i < data.data.skus.length; i++) {
					    var e2= data.data.skus[i];
					    htmls+='<tr>';
						htmls+='<td><input id="sku_code_${p.code }" name="skus['+i+'].code" value="'+e2.code+'" maxlength="50" class="required" style="width: 80px;" readonly="readonly"/></td>';
						htmls+='<td><input id="sku_salePrice_${p.code }" name="skus['+i+'].salePrice" value="'+e2.salePrice+'" maxlength="10" class="required number"  style="width: 80px;" readonly="readonly"/></td>';
						htmls+='<td><input id="sku_oneprice_${p.code }" name="skus['+i+'].oneprice" value="'+e2.onePrice+'" maxlength="10" class="required number"  style="width: 80px;" readonly="readonly"/></td>';
						htmls+='<td><input id="sku_twoPrice_${p.code }" name="skus['+i+'].twoPrice" value="'+e2.twoPrice+'" maxlength="10" class="required number" style="width: 80px;" readonly="readonly"/></td>';
						htmls+='<td><input id="sku_cnt_${p.code }" name="skus['+i+'].cnt" value="'+e2.cnt+'" maxlength="10" class="required digits" style="width: 80px;" readonly="readonly"/></td>';
						htmls+='<td><input id="sku_orgPrice_${p.code }" name="skus['+i+'].orgPrice" value="${e2.orgPrice}" maxlength="10" class="required number" style="width: 80px;"/></td>';
						if(i==0){
							htmls+='<td><input type="radio" checked="checked" onclick="setIsDefault(this)" class="skuIsDefault" value="0" name="skus['+i+'].isDefault" /></td>';
						}else{
							htmls+='<td><input type="radio" value="0" onclick="setIsDefault(this)" class="skuIsDefault" name="skus['+i+'].isDefault" /></td>';	
						}
						 htmls+='</tr>';
					}
					$("#priceTable").append(htmls);
			  },
			  error:function(){
				  alert('获取信息失败');
			  },
			  dataType: "json"//xml、json、script 或 html
		});
	}

</script>
<script type="text/javascript">
	$(document).ready(function() { 
		 $("input[name='pFlags']").each(function(i){ //勾选已经选中的标签
			 var id=$(this).val()+"_id";
			 $("#"+id).attr("checked","true"); 
		 });

	 });
	//跳页
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit(); 
		return false;
	};
	
	function checkModelNum(obj){
		var modelNum = $(obj).val();
		return false;
	};
	
	function saveCheck() {
		alert('1111');
		checkModelNum();
		 /* var userName = obj.userName.value;
		 var password = obj.password.value;
         var code = obj.code.value;
		 if (code == null || code == "") { */
		 	alert("请输入验证码！");
		    return false;
		/*  }
		 return true; */
	};
</script>
</body>
</html>