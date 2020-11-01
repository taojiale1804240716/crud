<%@ page contentType="text/html;charset=UTF-8" language="java"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE htm	l PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style>
#customers
{
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	width:100%;
	border-collapse:collapse;
}
#customers td, #customers th 
{
	font-size:1em;
	border:1px solid #98bf21;
	padding:3px 7px 2px 7px;
}
#customers th 
{
	font-size:1.1em;
	text-align:left;
	padding-top:5px;
	padding-bottom:4px;
	background-color:#A7C942;
	color:#ffffff;
}
#customers tr.alt td 
{
	color:#000000;
	background-color:#EAF2D3;
}
</style>
</head>
<body>
       <h1>下载文件资源</h1>
       <form action="DownloadServlet.do" method="get">
       <table  width="300" border="1" id="customers" >
       </tr>
       <th>id</th>
       <th>name</th>
       <th>path</th>
       <th>description</th>
       <th>size</th>
       <th>star</th>
       <th>image</th>
        <th>点击下载</th>
       
       </tr>
       <c:forEach items="${downloadlist}" var="d">
        <tr>
       <td class="alt">${d.id}</td>
       <td>${d.name}</td>
       <td>${d.path}</td>
       <td>${d.description}</td>
       <td>${d.size}</td>
       <td>${d.star}</td>
       <td>${d.image}</td>
      
       <td><button type="submit" name="id" value="${d.id}">立即下载</button></td>
       </tr>
       </c:forEach>
       </table>
       </form>
      

</body>
</html>