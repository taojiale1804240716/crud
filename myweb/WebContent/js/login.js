//XMLHttpRequest对象
//XMLHttpRequest对象
var xmlHttp;

function CreateXmlHttp() {
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest;

    } else {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    // var userName=document.getElementById("userName").nodeValue;
    // var password=document.getElementById("passWord").nodeValue;

}

function ajaxCheckLogin() {

    // 1.创建对象
    CreateXmlHttp();
    // 2.发送请求
    // 2.1
    xmlHttp.open("post", "ajaxCheckLogin.do", true);

    // 2.2设置内容类型编码类型为url的编码格式
    xmlHttp.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    // 2.3设置请求头
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var vcode = document.getElementById("vcode").value;
    var date = "userName=" + userName + "&password=" + password + "&vcode=" +
        vcode;
    // console.log(date);
    // 2.4发送请求头
    xmlHttp.send(date);

    // 3. //判断是否初始化完成
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var info = xmlHttp.responseText;
            var obj = JSON.parse(info);
            console.log(info);
            if (obj.code == 0) {
                window.location.href = "main.jsp";
            } else {
                console.log(2);
                alert(obj.info);
                document.getElementById("").innerText = obj.info;
            }

        } else {
            console.log(11);
        }
    }
}


function ajaxCheckRegister() {

    // 1.创建对象
    CreateXmlHttp();
    // 2.发送请求
    // 2.1
    xmlHttp.open("post", "registerController", true);

    // 2.2设置内容类型编码类型为url的编码格式
    xmlHttp.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    // 2.3设置请求头
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password2").value;
    var trueName = document.getElementById("trueName").value;
    var email = document.getElementById("e-mail").value;
    var p = document.getElementById("province"); //拿到省份被选中的数值
    var index = p.selectedIndex;
    var province = p.options[index].text;
    var city = document.getElementById("city").value;
    console.log("省份信息是" + province);
    var date = "userName=" + userName + "&password=" + password + "&trueName=" +
        trueName + "&email=" + email + "&province=" + province + "&city=" + city;
    // console.log(date);
    // 2.4发送请求头
    console.log(date);
    xmlHttp.send(date);

    // 3. //判断是否初始化完成
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var info = xmlHttp.responseText;
            var obj = JSON.parse(info);
            console.log(info);
            if (obj.code == 0) { //注册成功
                window.alert("注册成功");
            } else if (obj.code == 1) {
                window.alert("用户名重复");
                //document.getElementById("").innerText = obj.info; //将错误的信息返回到页面
            } else {
                window.alert("注册失败");
            }

        } else {
            console.log(11);
        }
    }

}
//将数据填入省份中
function fillProvince() {

    // 1.创建对象
    CreateXmlHttp();
    // 2.发送请求
    // 2.1
    xmlHttp.open("post", "queryProvinceCity", true);

    // 2.2设置内容类型编码类型为url的编码格式
    xmlHttp.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    // 2.3设置请求头
    //将select标签获取
    var provinceElement = document.getElementById("province");
    var date = "provincecode=" + provinceElement.value; //获取数据

    // console.log(date);
    // 2.4发送请求头
    xmlHttp.send();


    // 3. //判断是否初始化完成
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var info = xmlHttp.responseText;
            console.log(info);
            var obj = JSON.parse(info);
            //console(info.index);
            console.log(info.length);
            //console.log(obj[1].Provincial);
            //清楚select中所有的数据
            provinceElement.options.length = 0;
            //增加一个option
            provinceElement.add(new Option("请选择省份", ""));
            //console.log(info[2].pid);
            for (var index = 0; index < info.length; index++) {
                provinceElement.add(new Option(obj[index].Provincial, obj[index].pid));
            }
            console.log("rwggggggggggggggggg")
            console.log(provinceElement.length);

        } else {
            console.log(xmlHttp.status);
            console.log(xmlHttp.readyState);
        }
    }

}
//填充省份的方法
function fillcity() {
    var provinceElement = document.getElementById("province");
    var cityElement = document.getElementById("city");

    // 1.创建对象
    CreateXmlHttp();
    // 2.发送请求
    // 2.1
    xmlHttp.open("post", "queryProvinceCity", true);

    // 2.2设置内容类型编码类型为url的编码格式
    xmlHttp.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    // 2.3设置请求头

    if (provinceElement.value == "") //如果没有选中省份
    {
        var sp = document.getElementById("provinceerr");
        var x = "你没有选择省份";
        sp.innerHTML = x;
        //添加错误提示信息
        return;
    }

    var provinceCode = provinceElement.value; //得到省份的代码
    console.log(provinceCode);
    //然后进行city的ajax
    var date = "provinceCode=" + provinceCode;
    // console.log(date);
    // 2.4发送请求头
    xmlHttp.send(date);

    // 3. //判断是否初始化完成
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var info = xmlHttp.responseText;
            console
            var obj = JSON.parse(info);
            console.log(info);

            //将城市清空
            cityElement.options.length = 0;
            cityElement.add(new Option("请添加城市"));
            //添加城市
            for (var index = 0; index < info.length; index++) {
                cityElement.add(new Option(obj[index].city));
            }

        } else {
            console.log(11);
        }
    }
}

//页面刷新自动调用这个函数
window.onload = function() {
    //首先调用函数,填充下拉框	
    fillProvince();
}

function changeCode() {
    var codeImg = document.getElementById("verifyCode").src = "CreateVcodeImageController.do?t=" +
        math.random();
}

function getCookie(c_name) {
    //判断document.cookie对象里面是否存有cookie
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        //如果document.cookie对象里面有cookie则查找是否有指定的cookie，如果有则返回指定的cookie值，如果没有则返回空字符串
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        } //可能得到的不是正确的值
    }
    return "";
}

function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + "";
    expires = " + exdate.toGMTString() + ";
    path = "/";
}

function myfunction() {


    console.log(getCookie("userName") && getCookie("password"));


    //   
    //	if (getCookie("userName")!="" && getCookie("password")!="") {//如果为true
    //		//需要创建一个user
    //		//直接调用cookielogin
    //		
    //		 document.getElementById("form1").action="LoginController.do";
    //		 console.log(document.getElementById("form1").action);
    //         //模拟一次点击登录按键
    //         //然后直接调用点击登录
    //          var oBtn = document.getElementById('tj');
    //         // oBtn.click();
    //     }// 判断多选框是否选中
    //	else{
    //		 document.getElementById("form1").action="LoginController.do";
    //	}


}

function clearcookie() {
    var  date = new  Date();     
    date.setTime(date.getTime() - 10000);    
    setCookie("userName", "", -1);
    setCookie("password", "", -1);


}
//正则表达式
function valtName() {
    var tname = document.getElementById("trueName");
    var tspan = document.getElementById("trueName-err");
    var x = "";
    var pattern = /^[\u4e00-\u9fa5]{2,4}$/;
    var value = tname.value;

    if (value == "") //没有输入用户名
    {
        x = "请输入真实姓名";
    }
    if (!pattern.test(value)) //如果验证成功
    {
        x = "请输入2到四位的汉字";
    }
    tspan.innerHTML = x; //向span中插入提示信息
}

function valuName() {
    var uname = document.getElementById("userName").value; //拿到输入框里面的值\
    var uspan = document.getElementById("userName-err"); //拿到span标签
    var pattern = /^[a-zA-Z][a-zA-Z0-9]{3,14}$/; //正则表达式验证
    var x = "";
    //首先判断是否为空
    if (uname == "") {
        x = "请输入用户名";
    }
    if (!pattern.test(uname)) {
        x = "请输入4到14位字母或者数字(开头必须用字母)组成的用户名";
    }
    uspan.innerHTML = x;

}

function valEmail() {
    var email = document.getElementById("e-mail").value; //拿到e-mail的值
    var espan = document.getElementById("e-mail-err"); //拿到span标签
    var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //定义正则表达式
    var x = ""; //需要添加的值
    if (email == "") //邮件不能为空
    {
        x = "请输入电子邮箱";
    }
    if (!pattern.test(email)) {
        x = "请输入格式正确的e-mail的格式";
    }
    espan.innerHTML = x;
}
//验证密码
function valPassword() {
    var password = document.getElementById("password1").value;
    var pspan = document.getElementById("password1-err");
    var pattern = /^.{4,15}$/;
    var x = ""; //需要添加的值
    if (password == "") //邮件不能为空
    {
        x = "请输入密码";
    }
    if (!pattern.test(password)) {
        x = "请输入4-15位的密码";
    }
    pspan.innerHTML = x;
}

//判断两次密码输入的值是否相同
function passwordcheck() {
    //首先得到第一个框的密码
    //1.如果为空 提示先输入第一个密码 2.否则 检查和第二个框输入的密码是否相同
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    var pspan = document.getElementById("password2-err");
    var x = "";
    if (password1 == "") //如果第一个密码为空
    {
        x = "请输入密码框";
        pspan.innerHTML = x;
        return;
    }
    //如果不为空那么和第二个密码框进行验证
    if (password1 == password2) //如果两个密码相同
    {
        x = "";
        pspan.innerHTML = x;
        return;
    } else {
        x = "请再次确认密码两次输入的不同";
        pspan.innerHTML = x;
        return;
    }


}

function clickPssword() {
    //首先调第二个密码框
    //1.首先判断是否为空 2.如果不为空判断二则密码是否相等，如果不等，在下面的密码框提示
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    var pspan = document.getElementById("password2-err");
    var x = "";
    if (password2 == "") {
        return;
    }
    if (password1 != password2) {
        x = "两次输入的密码不一样";
        pspan.innerHTML = x;
        return;
    } else {
        x = "";
        pspan.innerHTML = x;
        return;
    }
}

function checklogin() {
    var listspan = document.getElementsByTagName("span");
    var listinput = document.getElementsByTagName("input");
    var selectlist = document.getElementsByTagName("select");
    //console.log("checklogin -> selectlist", selectlist[0].value);
    var count1 = 0;
    var count2 = 0;
    //console.log(list.length);
    for (var i = 0; i < listspan.length; i++) {
        console.log(listspan[i].innerHTML);
        if (listspan[i].innerHTML == "") //当为空的时候
        {

            count1++;
        }
    }
    for (var i = 0; i < listinput.length; i++) {
        //console.log(list[i].innerHTML);
        if (listinput[i].value != "") //当为空的时候
        {
            count2++;
        }
    }
    // console.log(count1);
    //console.log(count2);
    if (count1 >= 5 && count2 >= 5) //符合提交
    {
        //下面是判断是否注册成功的
        //首先拿到那一行判断是否是想修改信息
        var action = $("#action").val(); //
        console.log(action);
        if (action == "true") {
            $("#userName").attr("disabled", "disabled");
            console.log("我进来了");
            Update(); //更新的ajax
        } else {
            $("#userName").removeAttr("disabled"); //将Input设置为可编辑
            ajaxCheckRegister(); //判断注册的
            console.log(123);
        }

    } else {
        console.log(count1);
        console.log(count2);
        alert("请输入数据");
    }


}

function valtcode() {
    var tcity = document.getElementById("vcode");
    var tspan = document.getElementById("vcodeerr");
    var x = "";

    var value = tname.value;

    if (value == "") //没有输入用户名
    {
        x = "填写验证码";
    }

    tspan.innerHTML = x; //向span中插入提示信息
}

function valtpassword() {
    var tcity = document.getElementById("password");
    var tspan = document.getElementById("password1-err");
    var x = "";

    var value = tname.value;

    if (value == "") //没有输入用户名
    {
        x = "请填写密码";
    }

    tspan.innerHTML = x; //向span中插入提示信息
}


// function valprovince() {
//     var tprovince = document.getElementById("province");
//     var tspan = document.getElementById("provinceerr");
//     var x = "";

//     var value = tname.value;

//     if (value == "请选择省份") //没有输入用户名
//     {
//         x = "填写身份";
//     }

//     tspan.innerHTML = x; //向span中插入提示信息
// }