<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link href="css/login.css" rel="stylesheet">
<script src="js/login.js"></script>

</head>
<body>
	<div class="color">

		<form>
			<p>
			    
				<label for="userName">用户名</label>
				 <input id="userName" class="sign-text" type="text" name="userName" value="tjl" onblur="valuName()"/>
				 <span id="userName-err" class="red"></span>
			</p>
			<p>

				<label for="password">密码</label> 
				<input id="password" type="password" name="password" value="123"  onblur="valtpassword()"/>
				<span id="password1-err"></span>
			</p>
			<h3>验证码</h3>
			<input type="text" name="vcode" id="vcode" placeholder="验证码"  onblur="valtcode()" /></br> 
			<span id="vcodeerr"></span>
			<img
				src="CreateVcodeImageController.do" id="verifyCode" title="看不清楚换一张"
				onclick="changeCode()" style="cursor: pointer">
			<p>
				<input type="button" value="开始登录" onclick="ajaxCheckLogin()" />
				
			</p>
			<p>		
				<input  id="check" type="checkbox" name="login" value="login">一周内免登录<br>			
			</p>
			<a href="register.html">注册</a> 
		</form>

	</div>
</body>
</html>