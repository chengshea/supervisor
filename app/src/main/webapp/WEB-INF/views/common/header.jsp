<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
    <div class="navbar navbar-fixed-top" >
        <div class="container-fluid cl">
            <a class="logo navbar-logo f-l mr-10 hidden-xs" href="">后台通用管理系统</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs"></span>
            <a  class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
           
                <ul class="cl">
                    <li >超级管理员</li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A"><i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a  href="javascript:;" onclick="'javascript:edit_password(\'修改密码\',\'/admin/password\',\'\',\'280\');'">修改密码</a></li>
                            <li><a href="#" href="">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;"  title="默认（黑色）">默认（黑色）</a></li>
                        </ul>
                    </li>
                </ul>
        </div>
    </div>
</body>
</html>