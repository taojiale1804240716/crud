//除了表头（第一行）以外所有的行添加click事件.
$("tr").first().nextAll().click(function() {
    //如果没有某个样式则加上，否则去除
    $(this).children().toggleClass("bgRed");
    if ($(this).children().hasClass("bgRed")) { //如果有某个样式则表明，这一行已经被选中
        $(this).children().first().children().attr("checked", true);
    } else { //如果没有被选中
        $(this).children().first().children().attr("checked", false);
    }
});
//********************8 */


//跳转到最后一页
var lastpage = function() {
    var tdnum = $("table td").size();
    console.log(tdnum)
    if (tdnum > 0) { //如果table存在数据
        //得到现在页码数   	  
        var q = parseInt($("#pageNumber").text()); //当前页码
        var w = parseInt($("#pageCount").text()); //得到总共的页码
        console.log(q);
        console.log(w);
        if (q != w) { //判断是否在最后一页
            var form = $("#searchForm").serialize();
            //dataCollect = decodeURIComponent(dataCollect, true); //防止中文乱码
            var queryParams = DataDeal.formToJson(form); //转化为json
            console.log(queryParams);
            //var s = 1;
            var a = w + ""; //将页码更新到最后一页
            console.log(a);
            $("#pageNumber").text(a); //改变页面
            //console.log("页数为" + parseInt($("#pageNumber").text()) + 1);
            //得到pageParams的值
            var pageParam = {
                pageSize: $("#pageSize").val(),
                pageNumber: a,
                sort: "userName",
                sortOrder: "desc"
            };
            var pageParams = JSON.stringify(pageParam);
            console.log(pageParams);

            $.ajax({
                type: "POST",
                url: "ajaxQuery",
                //contentType: 'application/json;charset=UTF-8',
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data: { "queryParams": queryParams, "pageParams": pageParams },
                dataType: "json",
                success: function(response) {
                    var total = response.total;
                    var rows = response.rows;
                    var pageCount = Math.ceil(total / pageParam.pageSize);
                    $("#total").text(total);
                    $("#pageCount").text(pageCount);
                    $("tbody").empty();
                    //然后将表格动态生成
                    $.each(rows, function(index, row) {
                        //首先将Json转化成对象
                        var r = JSON.stringify(row);
                        var str = "<tr data='" + r + "'>";
                        str = str + '<td><input type="checkbox" value=' + row.userName + '></td>';
                        str = str + '<td>' + row.userName + '</td>';
                        str = str + '<td>' + row.chrName + '</td>';
                        str = str + '<td>' + row.email + '</td>';
                        str = str + '<td>' + row.provincial + '</td>';
                        str = str + '<td>' + row.city + '</td>';
                        str = str + '<td><a href="#" id="btnUpdate">修改</a>';
                        str = str + '<a href="# id="btnDel">删除</a></td>';
                        str + str + '</tr>'
                        console.log(str);
                        $("tbody").append(str);
                    })
                },
                error: function(data) {
                    alert("ccc");
                    console.log(data);
                }
            });


        } else {
            window.alert("已经是最后一页");
        }
    } else {
        window.alert("请先查询数据");
    }
}



//转到第一页
var firstpage = function() {
        var tdnum = $("table td").size(); //看表格中是否有数据
        console.log(tdnum);
        if (tdnum > 0) { //如果table存在数据
            if (parseInt($("#pageNumber").text()) > 1) { //如果不是第一页，那么可以跳转到第一页
                var form = $("#searchForm").serialize();
                //dataCollect = decodeURIComponent(dataCollect, true); //防止中文乱码
                var queryParams = DataDeal.formToJson(form); //转化为json
                console.log(queryParams);
                //var s = 1;
                var a = 1 + ""; //将值赋为1跳转到首页
                $("#pageNumber").text(a);
                console.log(a);
                //console.log("页数为" + parseInt($("#pageNumber").text()) + 1);
                //得到pageParams的值
                var pageParam = {
                    pageSize: $("#pageSize").val(),
                    pageNumber: a,
                    sort: "userName",
                    sortOrder: "desc"
                };
                var pageParams = JSON.stringify(pageParam);
                console.log(pageParams);

                $.ajax({
                    type: "POST",
                    url: "ajaxQuery",
                    //contentType: 'application/json;charset=UTF-8',
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    data: { "queryParams": queryParams, "pageParams": pageParams },
                    dataType: "json",
                    success: function(response) {
                        var total = response.total;
                        var rows = response.rows;
                        var pageCount = Math.ceil(total / pageParam.pageSize);
                        $("#total").text(total);
                        $("#pageCount").text(pageCount);
                        $("tbody").empty();
                        //然后将表格动态生成
                        $.each(rows, function(index, row) {
                            //首先将Json转化成对象
                            var r = JSON.stringify(row);
                            var str = "<tr data='" + r + "'>";
                            str = str + '<td><input type="checkbox" value=' + row.userName + '></td>';
                            str = str + '<td>' + row.userName + '</td>';
                            str = str + '<td>' + row.chrName + '</td>';
                            str = str + '<td>' + row.email + '</td>';
                            str = str + '<td>' + row.provincial + '</td>';
                            str = str + '<td>' + row.city + '</td>';
                            str = str + '<td><a href="#" id="btnUpdate">修改</a>';
                            str = str + '<a href="# id="btnDel">删除</a></td>';
                            str + str + '</tr>'
                            console.log(str);
                            $("tbody").append(str);
                        })
                    },
                    error: function(data) {
                        alert("ccc");
                        console.log(data);
                    }
                });

            } else { //如果等于1的话
                window.alert("您当前就在第一页");
            }

        } else {
            window.alert("请先查询数据");
        }
    }
    //清空模态框里面的数据
var clearModel = function() {
        $("#userName").val("");
        $("#trueName").val("");
        $("#e-mail").val("");
        $("#province").empty();
        $("#city").empty();
        $("#password1").val("");
        $("#password2").val("");
        $("#action").val(false);
        fillProvince();
    }
    //表格里面的删除
$("table").on("click", "#btnDel", function() {
    console.log("***********");
    var userName = $(this).attr("value"); //拿到userName数值
    console.log(userName);
    $.ajax({
        type: "POST",
        url: "ajaxDelete",
        data: { ids: userName }, //使用分割
        dataType: "json",
        success: function(response) {

            window.alert(response.info);
            if (response.code == 0) {
                query(); //调用查找函数
            }
        }
    });
});
var UpdateDate = function() {
    //怎么拿到userName的数据
    var len = $('tbody tr input:checkbox:checked').length;
    //console.log("*********************");
    console.log(len);
    if (len == 1) { //当表格中没有数据时
        var userName = $('tbody tr input:checkbox:checked').val();

        //var form = $("#searchForm").serialize();
        var s = "userName=" + userName + "&chrName=" + "" +
            "&email=" + "" + "&provinceName=" + "";
        console.log(s);
        //dataCollect = decodeURIComponent(dataCollect, true); //防止中文乱码
        var queryParams = DataDeal.formToJson(s); //转化为json
        console.log(queryParams);
        //var s = 1;

        //得到pageParams的值
        var pageParam = {
            pageSize: $("#pageSize").val(),
            pageNumber: $("#pageNumber").text(),
            sort: "userName",
            sortOrder: "desc"
        };
        var pageParams = JSON.stringify(pageParam);
        console.log(pageParams);

        $.ajax({
            type: "POST",
            url: "ajaxQuery",
            //contentType: 'application/json;charset=UTF-8',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: { "queryParams": queryParams, "pageParams": pageParams },
            dataType: "json",
            success: function(response) {
                var total = response.total;
                var rows = response.rows;
                var user;
                var pageCount = Math.ceil(total / pageParam.pageSize);
                $("#total").text(total);
                $("#pageCount").text(pageCount);
                //将表格清空
                $("tbody").empty();
                //然后将表格动态生成
                $.each(rows, function(index, row) {
                    //拿到user对象
                    if (row.userName == userName) {
                        user = row; //将对象赋给user
                        return false;
                    }
                })

                console.log(user.provincial);
                console.log(user);
                clearModel();
                //然后将值传给页面   
                $("#userName").val(user.userName);
                $("#trueName").val(user.chrName);
                $("#e-mail").val(user.email);
                // fillProvince();
                //cityElement.add(new Option("请添加城市"));
                $("#xxx").html(user.provincial);
                //$("#city").val(user.city);
                console.log("xxxxxxxxxxxxxxxxx");
                console.log($("#province").length);
                //console.log($("#province option:last").attr("index"));
                //console.log($("#province option").length);
                // $("#province option").each(function() {


                //     if ($(this).text() == user.provincial) {

                //         $(this).attr("selected", true);
                //         return false;
                //     }
                // })
                $("#password1").val(user.password);
                $("#password2").val(user.password);
                $("#action").val(true);
                query();
            },
            error: function(data) {
                alert("ccc");
                console.log(data);
            }
        });
        //然后查询 
    } else {
        clearModel();
        alert("只能选择一个");
        return;
    }
}


//更新信息
var Update = function() {
    //拿到数据
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

    var queryParams = DataDeal.formToJson(date); //转化从成json格式的
    console.log(queryParams);
    //首先调用删除的ajax

    //删除后再进行插入
    $.ajax({
        type: "POST",
        url: "ajaxUpdate", //先删除再修改
        data: { "queryParams": queryParams }, //使用分割
        dataType: "json",
        success: function(response) {
            window.alert(response.info);
            if (response.code == 0) {
                query(); //调用查找函数
            }
        }
    });
}


window.onload = function() {
    query();
}


//删除按件
var Delete = function() {
    var len = $('tbody tr input:checkbox:checked').length;
    if (len == 0) { //当表格中没有数据时
        alert("至少选择一个");
        return;
    }
    var vals = []; //定义一个存username的数组
    $('tbody tr input:checkbox:checked').each(function(index, item) {
        vals.push($(this).val()); //循环的将value值放入
    });
    console.log(vals.join(","));
    $.ajax({
        type: "POST",
        url: "ajaxDelete",
        data: { ids: vals.join(",") }, //使用分割
        dataType: "json",
        success: function(response) {

            window.alert(response.info);
            if (response.code == 0) {
                query(); //调用查找函数

            }
        }
    });
}

//清空函数
var clear = function() {
    $("tbody").empty();

}

//上一页
var backpage = function() {
    var tdnum = $("table td").size();
    console.log(tdnum);
    if (tdnum > 0) { //如果table存在数据
        if (parseInt($("#pageNumber").text()) > 1) {
            var form = $("#searchForm").serialize();
            //dataCollect = decodeURIComponent(dataCollect, true); //防止中文乱码
            var queryParams = DataDeal.formToJson(form); //转化为json
            console.log(queryParams);
            //var s = 1;
            var a = parseInt($("#pageNumber").text()) - 1 + "";
            $("#pageNumber").text(a);
            console.log(a);
            //console.log("页数为" + parseInt($("#pageNumber").text()) + 1);
            //得到pageParams的值
            var pageParam = {
                pageSize: $("#pageSize").val(),
                pageNumber: a,
                sort: "userName",
                sortOrder: "desc"
            };
            var pageParams = JSON.stringify(pageParam);
            console.log(pageParams);

            $.ajax({
                type: "POST",
                url: "ajaxQuery",
                //contentType: 'application/json;charset=UTF-8',
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data: { "queryParams": queryParams, "pageParams": pageParams },
                dataType: "json",
                success: function(response) {
                    var total = response.total;
                    var rows = response.rows;
                    var pageCount = Math.ceil(total / pageParam.pageSize);
                    $("#total").text(total);
                    $("#pageCount").text(pageCount);
                    $("tbody").empty();
                    //然后将表格动态生成
                    $.each(rows, function(index, row) {
                        //首先将Json转化成对象
                        var r = JSON.stringify(row);
                        var str = "<tr data='" + r + "'>";
                        str = str + '<td><input type="checkbox" value=' + row.userName + '></td>';
                        str = str + '<td>' + row.userName + '</td>';
                        str = str + '<td>' + row.chrName + '</td>';
                        str = str + '<td>' + row.email + '</td>';
                        str = str + '<td>' + row.provincial + '</td>';
                        str = str + '<td>' + row.city + '</td>';
                        str = str + '<td><a href="#" id="btnUpdate">修改</a>';
                        str = str + '<a href="# id="btnDel">删除</a></td>';
                        str + str + '</tr>'
                        console.log(str);
                        $("tbody").append(str);
                    })
                },
                error: function(data) {
                    alert("ccc");
                    console.log(data);
                }
            });

        } else { //如果等于1的话
            window.alert("您当前就在第一页");
        }

    } else {
        window.alert("请先查询数据");
    }
}



//下一页函数
var nextpage = function() {
    var tdnum = $("table td").size();
    console.log(tdnum)
    if (tdnum > 0) { //如果table存在数据
        //得到现在页码数   	  
        var q = parseInt($("#pageNumber").text()); //当前页码
        var w = parseInt($("#pageCount").text()); //得到总共的页码
        console.log(q);
        console.log(w);
        if (q != w) {
            var form = $("#searchForm").serialize();
            //dataCollect = decodeURIComponent(dataCollect, true); //防止中文乱码
            var queryParams = DataDeal.formToJson(form); //转化为json
            console.log(queryParams);
            //var s = 1;
            var a = parseInt($("#pageNumber").text()) + 1 + "";
            console.log(a);
            $("#pageNumber").text(a); //改变页面
            //console.log("页数为" + parseInt($("#pageNumber").text()) + 1);
            //得到pageParams的值
            var pageParam = {
                pageSize: $("#pageSize").val(),
                pageNumber: a,
                sort: "userName",
                sortOrder: "desc"
            };
            var pageParams = JSON.stringify(pageParam);
            console.log(pageParams);

            $.ajax({
                type: "POST",
                url: "ajaxQuery",
                //contentType: 'application/json;charset=UTF-8',
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data: { "queryParams": queryParams, "pageParams": pageParams },
                dataType: "json",
                success: function(response) {
                    var total = response.total;
                    var rows = response.rows;
                    var pageCount = Math.ceil(total / pageParam.pageSize);
                    $("#total").text(total);
                    $("#pageCount").text(pageCount);
                    $("tbody").empty();
                    //然后将表格动态生成
                    $.each(rows, function(index, row) {
                        //首先将Json转化成对象
                        var r = JSON.stringify(row);
                        var str = "<tr data='" + r + "'>";
                        str = str + '<td><input type="checkbox" value=' + row.userName + '></td>';
                        str = str + '<td>' + row.userName + '</td>';
                        str = str + '<td>' + row.chrName + '</td>';
                        str = str + '<td>' + row.email + '</td>';
                        str = str + '<td>' + row.provincial + '</td>';
                        str = str + '<td>' + row.city + '</td>';
                        str = str + '<td><a href="#" id="btnUpdate">修改</a>';
                        str = str + '<a href="# id="btnDel">删除</a></td>';
                        str + str + '</tr>'
                        console.log(str);
                        $("tbody").append(str);
                    })
                },
                error: function(data) {
                    alert("ccc");
                    console.log(data);
                }
            });


        } else {
            window.alert("已经是最后一页");
        }
    } else {
        window.alert("请先查询数据");
    }

}


//查找函数
var query = function() {
    var form = $("#searchForm").serialize(); //
    data_params = decodeURIComponent(form, true);
    console.log(form);
    //dataCollect = decodeURIComponent(dataCollect, true); //防止中文乱码
    var queryParams = DataDeal.formToJson(data_params); //转化为json
    console.log(queryParams);
    //var s = 1;

    //得到pageParams的值
    var pageParam = {
        pageSize: $("#pageSize").val(),
        pageNumber: $("#pageNumber").text(),
        sort: "userName",
        sortOrder: "desc"
    };
    var pageParams = JSON.stringify(pageParam);
    console.log(pageParams);

    $.ajax({
        type: "POST",
        url: "ajaxQuery",
        //contentType: 'application/json;charset=UTF-8',
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data: { "queryParams": queryParams, "pageParams": pageParams },
        dataType: "json",
        success: function(response) {
            var total = response.total;
            var rows = response.rows;
            var pageCount = Math.ceil(total / pageParam.pageSize);
            $("#total").text(total);
            $("#pageCount").text(pageCount);
            //将表格清空
            $("tbody").empty();
            //然后将表格动态生成
            $.each(rows, function(index, row) {
                //首先将Json转化成对象
                var r = JSON.stringify(row);
                var str = "<tr data='" + r + "'>";
                str = str + '<td><input type="checkbox" value=' + row.userName + '></td>';
                str = str + '<td>' + row.userName + '</td>';
                str = str + '<td>' + row.chrName + '</td>';
                str = str + '<td>' + row.email + '</td>';
                str = str + '<td>' + row.provincial + '</td>';
                str = str + '<td>' + row.city + '</td>';
                str = str + '<td><a href="#" id="btnUpdate">修改</a>';
                str = str + '<a href="# id="btnDel">删除</a></td>';
                str + str + '</tr>'
                console.log(str);
                $("tbody").append(str);
            })

        },
        error: function(data) {
            alert("ccc");
            console.log(data);
        }
    });
}

$(document).ready(function() {
    $("#next").click(nextpage); //下一页函数
    $("#btSearch").click(query); //查找函数
    $("#back").click(backpage); //上一页函数
    $("#btClear").click(clear); //清空函数
    $("#btDelete").click(Delete); //删除函数
    $("#btAdd").click(clearModel); //清空函数
    //需要将数据修改
    //$("#btUpdate").click(updateDate);
    //$("#btUpdate").click(Update);
    $("#btUpdate").click(UpdateDate); //更新函数
    $("#first").click(firstpage); //跳转到首页
    $("#last").click(lastpage); //跳转到最后一页
    query();
});

var DataDeal = {
    //将从form中通过$('#refer').serialize()获取的值转成json
    formToJson: function(data) {
        data = data.replace(/&/g, "\",\"");
        data = data.replace(/=/g, "\":\"");
        data = "{\"" + data + "\"}";
        return data;
    }
};