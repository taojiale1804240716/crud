<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
<script>
			function countDown(){
				//获取初始时间
				var time = document.getElementById("Time");
				
				//获取到id为time标签中的数字时间
				if(time.innerHTML == 0){				
					window.location.href="login.html";
				}else{
					time.innerHTML = time.innerHTML-1;
				}
			}
			//1000毫秒调用一次
			window.setInterval("countDown()",1000);
		</script>
		<style>
			#Time,#p{
				font-size: 150px;
				text-align: center;
			}
			#Font,#p{
				font-size: 100px;
				text-align: center;
			}
			.img {
			      background-image: url(images/timg.jpg);  		      
			      background-size:100% 100%;
			      background-repeat:no-repeat;		     	      
			}
		</style>
</head>
<body>
   <div class="img">
      <p>${errorInfo}</p>
      <p>${info}</p> 
      <font color="blue" ><p id="Time" >10</p></font>
	  <p id="Font">将跳转页面</p>
	</div>
</body>
</html>