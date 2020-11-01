  <%@ page contentType="text/html;charset=UTF-8" language="java"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/login.js"></script>
<title>Insert title here</title>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}
</style>
</head>
<body>
<p style="text-align:right">欢迎光临:${currentUser.chrName} <a href="login.html">退出登录</a></p>
<ul>
        
        <li><a class="active" href="login.html">返回登录界面</a></li>
        <li><a href="GetDownloadListController.do">下载</a></li>
        <li><a href="administratorPage.html">人员管理</a></li>
        <li><a href="#contact">联系</a></li>
        <li><a href="#about">关于</a></li>
    </ul>
   

</body>
</html>