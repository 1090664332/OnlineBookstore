<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@include file="../../community/header.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>

<script type="text/javascript">
	$(function () {
		//获取提示错误信息的span标签
		var $errorMsg=$("span.errorMsg");

		//获取用户名的jQuery对象
		var $username=$("#username");
		//用户名验证
		$username.blur(function () {
			// 1、获取用户名
			var username=$username.val();
			// 2、创建验证用户名的正则表达式
			var userrep=/^\w{5,12}$/;
			// 3、用正则表达式验证输入的用户名是否合法
			if (!userrep.test(username)){
				// 4、如果不合法提醒用户
				$errorMsg.text("用户名不合法！");
				$errorMsg.attr("nameError","nameError");
			}else {
				//如果username合法，则继续判断username是否可用
				$.get("${baseHref}userServlet","action=userNameExist&username="+username,
						function (msg) {
							$errorMsg.text(msg);
						},
						"Text"
				);

				$errorMsg.attr("nameError","nameError");
			}

			$username.removeAttr("nameError");
		});




		//密码验证
		var $password=$("#password");
		$password.blur(function () {
			// 1、获取密码值
			var psw=$password.val();
			// 2、创建验证密码的正则表达式
			var pswrep=/^[a-z0-9_-]{6,18}$/;
			// 3、用正则表达式验证输入的密码是否合法
			if (!pswrep.test(psw)){
				// 4、如果不合法提醒用户
				$errorMsg.text("密码不合法！");
				$errorMsg.attr("pswError","pswError");
			}
			$password.removeAttr("pswError");
		});


		//确认密码验证
		//input不输入东西，值是""
		var $repwd=$("#repwd");
		$repwd.blur(function () {
			var repwd=$repwd.val();
			var psw=$password.val();
			if (repwd!=psw || repwd==""){
				$errorMsg.text("两次密码输入不一致！");
				$errorMsg.attr("repwdError","repwdError");
			}
			$repwd.removeAttr("repwdError");
		});



		//验证邮箱
		var $email=$("#email");
		$email.blur(function () {
			var email=$email.val();
			var emailrep=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			if(!emailrep.test(email)){
				$errorMsg.text("邮箱不合法！");
				$errorMsg.attr("emailError","emailError");
			}
			$email.removeAttr("emailError");
		});

		//验证验证码是否为空
		var $code=$("#code");
		$code.blur(function () {
			var code=$code.val();
			code=$.trim(code);
			if (code==null||code==""){
				$errorMsg.text("验证码为空！");
				$errorMsg.attr("codeError","codeError");
			}
			$code.removeAttr("codeError");
		});

        var serviceFun=function (errorType,thisObj) {
			var attr = $errorMsg.attr(errorType);
			if (attr===undefined){
				attr="";
			}
			if (attr!=""){
				delErrorMsg();
				thisObj.value="";
				$errorMsg.attr(errorType,"");
			}

		};

		var determineType=function(typeName,obj){
			if(typeName==="username"){
				var errorType="nameError";
				serviceFun(errorType,obj);
			}else if (typeName==="password"){
				var errorType="pswError";
				serviceFun(errorType,obj);
			}else if (typeName==="repwd"){
				var errorType="repwdError";
				serviceFun(errorType,obj);
			}else if (typeName==="email"){
				var errorType="emailError";
				serviceFun(errorType,obj);
			}else if (typeName==="code"){
				if($errorMsg.text()==="验证码错误！"){
					delErrorMsg();
					obj.value="";
				}else{
					var errorType="codeError";
					serviceFun(errorType,obj);
				}
			}

		};
		//给输入栏注册获得焦点事件
		$("input:not('#sub_btn')").focus(function () {
			determineType(this.name,this);

		});

		//消除错误信息函数
		var delErrorMsg=function () {
				$errorMsg.text("");
		}


		//给提交按钮注册点击事件
		$("#sub_btn").click(function () {
			//提交时，触发所有文本框的blur，查看是否还有不符合的规则的选项
			$username.blur();
			$password.blur();
			$repwd.blur();
			$email.blur();
			$code.blur();
			if ($errorMsg.text()!=""){
				return false;
			}
		});

		//给验证码图片加上单击事件
		$(".form img").click(function () {
			this.src="${baseHref}kaptcha.jpg?id="+Math.random();
		});

		//Ajax验证用户名是否存在



	});
</script>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class ="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>

								<span class="errorMsg">${empty requestScope.registmsg?"":requestScope.registmsg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${requestScope.map.name}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" value="${requestScope.map.password}" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" value="${requestScope.map.password}" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${requestScope.map.email}" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code" id="code" value="${requestScope.map.userCode}"/>
									<img alt="" src="kaptcha.jpg"  style="float: right; margin-right: 40px;width: 80px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="../../community/footer.jsp"%>
</body>
</html>