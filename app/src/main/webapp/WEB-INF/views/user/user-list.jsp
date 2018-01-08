<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/js.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/assets/layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
 
<table  id="test"></table>
<!-- 设置操作按钮 --> 
<script type="text/html" id="barDemo">
 {{#  if(d.openId == 1){ }}
       <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="status">删除</a>     
  {{#  } else { }}
          <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="status">修改</a>         
  {{#  } }}
 
</script>              
          
<script src="/assets/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script src="/assets/user/user.js"></script>

</body>
</html>