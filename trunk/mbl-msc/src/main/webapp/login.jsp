<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录</title>
<link rel="icon" href="images/logo.png">
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/dist/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        font-weigth: 900em;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
      .form-signin .errorSpan {
   		color: red;
      }

</style>
<script type="text/javascript">

	$(document).keydown(function(event) {
		if (event.keyCode == 13) { 
			submitForm();
		}
	});
	function submitForm(){
		var name = $("#name").val();
		var password = $("#password").val();
		var validateCode = $("#validateCode").val();
		if($.trim(name)==""||$.trim(password)==""){
			$("#errorDiv").html("帐号或密码不能为空");
			return false;
		}
		if($.trim(validateCode)==""){
			$("#errorDiv").html("验证码不能为空");
			return false;
		}
		$.ajax({
		    type:"post",
		    url:"<%=basePath%>/auth/login/loginPost",
		    async: false,
		    data:{
		    	name:name,
		    	password:password,
		    	validateCode:validateCode
		    },
		    dataType: 'json',
		    success : function(obj){
		    	console.log(obj);
		    	if("success" == obj.errorCode){
	    			var url = "<%=basePath%>/index.html";
					window.location.href = url;
				} else {
					alert(obj.errorMsg);
					return;
				}
			},
			error : function(e) {
				alert("系统错误!");
			}
		});
	}
	function updatePwdForm(){
		var name = $("#upName").val();
		var password = $("#upPassword").val();
		if($.trim(name)==""||$.trim(password)==""){
			$("#errorDiv").html("帐号或密码不能为空");
			return false;
		}
		
		$.ajax({
		    type:"post",
		    url:"<%=basePath%>/auth/login/loginPost",
		    async: false,
		    data:{
		    	name:name,
		    	password:password,
		    	validateCode:validateCode
		    },
		    dataType: 'json',
		    success : function(obj){
		    	console.log(obj);
		    	if("success" == obj.errorCode){
	    			var url = "<%=basePath%>/index.html";
					window.location.href = url;
				} else {
					alert(obj.errorMsg);
					return;
				}
			},
			error : function(e) {
				alert("系统错误!");
			}
		});
	}
	function reloadValidateCode(){
		$("#validateCodeImg").attr("src","<%=basePath%>/auth/login/validateCode?data=" + new Date() + Math.floor(Math.random()*24));
	}
</script>
</head>
<body>
	<div class="container">

      <form class="form-signin" id="loginForm">
        <h3><img src="<%=basePath%>/images/logo.png" alt="" style="">免不了后台登录</h3>
        
        <span id="errorDiv" class="errorSpan input-block-level">
        <% String errorMsg = (String)request.getAttribute("errorMsg");
			if(null != errorMsg) {%>			             
		 	<%=errorMsg%>
	    <%}%>	
        </span>
        <input type="text" name="name" class="form-control input-block-level" placeholder="用户名" id="name">
        <input type="password" name="password" class="form-control input-block-level" placeholder="密码" id="password">
        <div class="form-group" id = "validateCodeDiv">
        	<img id="validateCodeImg" src="<%=basePath%>/auth/login/validateCode" />&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:reloadValidateCode();">看不清？</a>
			<div class="input-icon">
				<i class="icon-lock login-pwd"></i>
				<input class="form-control placeholder-no-fix" type="text" name="validateCode" id="validateCode" placeholder="验证码"/>
				<div  class="errorMessage" id="valicodesErrorMessage"></div> 
			</div>
		</div>
        <button class="btn btn-large btn-primary" style="margin-right:10px;" type="button" onclick="submitForm(); return false;">登录</button>
        <a style="float:right;cursor:pointer" data-toggle="modal" data-target="#modifyPwdModal">修改密码</a>
      </form>

    </div>
	
</body>
</html>

<!-- modal begin -->
 <!--  Modal content for the above example -->
<div class="modal fade modal-scroll" id="modifyPwdModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-replace="true">
    <div class="modal-dialog modal-sm">
      	<div class="modal-content">

	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">
	          <span aria-hidden="true">&times;</span>
	          <span class="sr-only">Close</span>
	          </button>
	          <h4 class="modal-title" id="myLargeModalLabel">修改密码</h4>
	        </div>
	        <div class="modal-body" style="overflow-y:auto;">
	        	 
 				<form class="form-horizontal">
				  <div class="form-group">
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="upName" placeholder="用户名">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="upPwd" placeholder="原密码">
				    </div>
				  </div>
				   <div class="form-group">
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="upNewPwd" placeholder="新密码">
				    </div>
				  </div>
				   <div class="form-group">
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="upNewPwdConfirm" placeholder="确认密码">
				    </div>
				  </div>
				</form>
	        </div>
	        <div class="modal-footer"> 
				<div class="row">
					<center class="submit col-sm-12" ><!--col-xs-offset-10 col-xs-2 -->
						<button class="btn btn-primary btn-r-default  btn-radius">确 定</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="btn btn-default btn-r-default  btn-radius" data-dismiss="modal">关 闭</button>
					</center>
				</div> 
			</div>
      	</div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- modal end -->