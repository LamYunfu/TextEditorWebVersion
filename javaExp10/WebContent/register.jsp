<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<title>用户注册程序</title>
</head>
<body>
<!-- form标签的作用是封装提交信息
当点击提交的时候，交互的信息--
用户名和密码封装到form里面 
action标签表示我们要送到的位置，即信息提交给谁处理
method属性表示我们发送信息的方式
只有两个值可以选择 get和 post-->
<!-- 下面这段代码的功能是获取项目路径 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

<center>
<form action="<%=path%>/RegistServlet" method = "post">
	<h1>用户注册程序</h1>
	<table boder = "1" width = "400" class="table table-bordered">
	<tr>
		<td>用户名：</td>
		<td><input class="form-control" name = "username" type = "text"/></td>
	</tr>
	<tr>
		<td width = "100" >密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
		<td><input class="form-control" name = "pass" type = "password"/></td>
	</tr>
	<tr>
		<td width = "100">电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
		<td><input class="form-control" name = "tel" type = "text"/></td>
	</tr>
	<tr>
		<td width = "100">年&nbsp;&nbsp;&nbsp;&nbsp;龄</td>
		<td><input class="form-control" name = "age" type = "text"/></td>
	</tr>
	</table>
	<center>
	<input class="btn btn-primary" style = "margin:20px;" type = "submit" value="提交"/><input type = "reset" class="btn btn-primary"
		value = "重置"/>
	</center>
	</form>
	</center>
</body>
</html>