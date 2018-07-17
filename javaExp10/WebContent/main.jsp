<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
	<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js">
	</script>
	<title></title>
	
	<style type="text/css">
	#tbody tr:hover{
	background-color: #e0e1e4;}
	#returnOneLevel:disabled {
		background-color: #eee;
	}
	</style>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand">在线文件管理器</a>
  <form class="form-inline">
  	<b id = "userID">用户：${userId}</b>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style = "margin:20px">登出</button>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">显示用户信息</button>
  </form>
</nav>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-left">
				文件管理系统
			</h3>

	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="panel panel-default" style = "border-style: solid; border-color: #4169E1">
				<div class="panel-heading">
					<h3 class="panel-title" style="background-color: #4169E1; color : #FFFFFF">
						目录操作
					</h3>
				</div>
				<div class="panel-body">
					<div class="row clearfix">
				<div class="col-md-12 column" >
					<div class="input-group mb-3 col-md-5 column" style = "float: left; margin-right : 100px">
  						<input id="directory" type="text" class="form-control" placeholder="文件系统路径" aria-label="Recipient's username" aria-describedby="basic-addon2" style = "margin-right : 10px">
  					<div class="input-group-append">
    					<button id = "upOneLevel" class="btn btn-outline-secondary" type="button"><i class="fas fa-chevron-circle-left"></i></button>
    					<button id = "returnOneLevel" disabled = "disabled" class="btn btn-outline-secondary" type="button"><i class="fas fa-chevron-circle-right"></i></button>
  					</div>
					</div>
					<div class="input-group mb-3 col-md-5 column" style = "float: left">
  						<input id="newFolder" type="text" class="form-control" placeholder="输入要在当前路径下要创建的子目录或者文件名" aria-label="Recipient's username" aria-describedby="basic-addon2" style = "margin-right : 10px">
  					<div class="input-group-append">
    					<button id="createFolder" class="btn btn-outline-secondary" type="button"><i class="fas fa-folder-open"></i></button>
    					<button id="createFile" class="btn btn-outline-secondary" type="button"><i class="fas fa-file"></i></button>
  					</div>
					</div>
				</div>
			</div>
				</div>
			</div>
		</div>
	</div>

			
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table class="table" id = "table">
						<thead>
							<tr>
								<th>
									文件大小
								</th>
								<th>
									创建日期
								</th>
								<th>
									修改日期
								</th>
								<th>
									文件名
								</th>
								<th>
									类型
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody id="tbody"></tbody>
						
					</table>
				</div>
			</div>

			<div class="row clearfix">
				<div class="col-md-12 column">
				<div class="panel panel-default" style = "border-style: solid; border-color: #87CEFA; border-width: thin;">
					<div class="panel-heading">
					<h3 class="panel-title" style="background-color: #87CEFA; color : #4169E1">
						文件编辑器
					</h3>
					</div>
					<div class="panel-body clearfix col-md-12 column" style="background-color: #F0FFFF">
						<textarea id = "textarea" style="width: 100%; height: 350px;"></textarea>
						
						<div class="input-group mb-3 col-md-5 column float-right">
  							<input readonly id = "fileName" type="text" class="form-control" placeholder="文本文件名.txt/csv/json" aria-label="Recipient's username" aria-describedby="basic-addon2" style = "margin-right : 20px">
  						<div class="input-group-append">
    					<button id = "saveFile" class="btn btn-outline-secondary" type="button"><i class="fas fa-save"></i></button>
  						</div>
					</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var currentPath = "";			//表示当前路径
var pathStack = [];				//记录上一次访问的路径，当点击右箭头按钮的时候就可以返回上次访问路径

//刷新面板
function flushBoard(){
	$("#textarea").val("");
	$("#fileName").val("");
	$("#newFolder").val("");
	
	setTimeout(function(){
		listTable(currentPath);
	}, 50);
}
function listTable(path) {
	//alert(path);
	$.ajax({
		  type: 'POST',
		  url: "/javaExp10/TableServlet",
		  data: {'userId' : path},
		  success:  function(data){
			  if(data == false){
				  alert("路径不存在！");
			  }
			  var str="";
			     //alert(data); 
			     for(i in data) {
			    	 //alert(data[i].name);
			    	 str += "<tr>";
			    	 str +=  "<td>"+data[i].size+"</td>";
			    	 str += "<td>"+data[i].createDate+"</td>";
			    	 str += "<td>"+data[i].modifiedDate+"</td>";
			    	 str += "<td>"+data[i].name+"</td>";
			    	 //<i class="fas fa-file">
			    	 if(data[i].type == "1"){		//1表示文件
			    		 str += "<td><i class='fas fa-file'><span style = 'visibility: hidden;'>1</span></i></td>";
			    	 }
			    	 else{			//2表示文件夹
			    		 str += "<td><i class='fas fa-folder'><span style = 'visibility: hidden;'>2</span></i></td>";
			    	 }
			    	// str += "<td>"+data[i].type+"</td>";
			    	 str += "<td><button class='btn btn-outline-warning btnDeleteFile'><i class='fas fa-trash-alt'></i></button></td>";
			    	 str += "</tr>";
			     }
			     $("#tbody").html(str);
			     currentPath = path;
			   },
			error: function(){
				alert("出错了");
			},
		  dataType: "json"
		});
	//把当前文件路径显示到路径导航的位置
	$("#directory").val(path);
}

function deleteDirectory(path){
	alert("删除成功！");
	$.ajax({
		  type: 'POST',
		  url: "/javaExp10/DeleteFolderServlet",
		  data: {'path' : path},
		  success: function(){
			  flushBoard();
		  },
		  dataType: "json"
		});
}

function createDirectory(path){
	$.ajax({
		  type: 'POST',
		  url: "/javaExp10/CreateFolderServlet",
		  data: {'path' : path},
		  success:  function(){
			  flushBoard();
		  },
		  dataType: "json"
		});
}

function readFile(path){
	//alert("进发生的");
	$.ajax({
		  type: 'POST',
		  url: "/javaExp10/ReadFileServlet",
		  data: {'path' : path},
		  success:   function(data){
			 // alert(data);
			  $("#textarea").val(data);
		  },
		  error: function(data) {
			  alert(data);
		  },
		  dataType: "text"
		});
}
$(document).ready(function(){
	listTable(${userId});
	
	$("#directory").bind('keypress', function(event){
		if(event.keyCode == '13'){
			listTable($("#directory").val());
		}
	});
	
	$("tbody").on("click", ".btnDeleteFile", function(event){
		confirm("确认删除？");
		//alert(event.toElement.parentNode.parentNode.childNodes[3].innerHTML);
		var fileName = event.toElement.parentNode.parentNode.childNodes[3].innerHTML;
		//event.toElement.parentNode.parentNode.remove();
		//调用后端的删除文件夹函数
		//alert(currentPath + "/" + fileName);
		deleteDirectory(currentPath + "/" + fileName);
	});
	
	//创建新的文件夹
	$("#createFolder").click(function(){
		//alert($("#newFolder").val());
		var folderName = $("#newFolder").val();
		var path = currentPath + "/" + folderName;
		createDirectory(path);
		//刷新表格
		flushBoard();
	});
	
	//创建新的文件夹
	$("#createFile").click(function(){
		//alert($("#newFolder").val());
		var fileName = $("#newFolder").val();
		var path = currentPath + "/" + fileName;
		$.ajax({
			  type: 'POST',
			  url: "/javaExp10/CreateFileServlet",
			  data: {'path' : path},
			  success:  function(){
				  flushBoard();
			  },
			  error: function(){
				  alert("创建失败");
			  },
			  dataType: "text"
			});
		//刷新表格
	});
	
	//双击文件，打开文件
	$("tbody").on("dblclick", function(event){
		//alert(event.toElement.parentNode.childNodes[3].innerHTML);
		//首先获取选中数据的类型
		var fName = event.toElement.parentNode.childNodes[3].innerHTML;
		var type = event.toElement.parentNode.childNodes[4].childNodes[0].childNodes[0].innerHTML;
		//alert(type);
		//1：文件类型，直接打开文件将文件内容显示到文本框内， 2：文件夹类型，直接打开文件夹
		//打开文件或者文件夹会导致之前访问的栈清空
		stackPath = [];
		$("#returnOneLevel").attr("disabled", "disabled");
		
		if(type == 1){
			//如果是文件类型,直接打开文件，并且把文件名显示到相应的位置
			//将文件名显示到相应位置
			$("#fileName").val(fName);
			readFile(currentPath + "/" + fName);
		}
		else{
			//如果是文件夹类型，列出当前文件夹下的所有文件
			listTable(currentPath + "/" + fName);
		}
		
	});
	
	$("#saveFile").click(function(){
		alert("保存成功");
		//获取textArea内容和文件名
		path = currentPath + "/" + $("#fileName").val();
		var text = $("#textarea").val();
		$.ajax({
			  type: 'POST',
			  url: "/javaExp10/SaveFileServlet",
			  data: {'path' : path, "content" : text},
			  success:   function(){
				  flushBoard();
			  },
			  error: function(data) {
				  alert(data);
			  },
			  dataType: "text"
			});
	});
	
	$("#upOneLevel").click(function(){
		//返回上一级，直接拿到当前路径，去掉尾部的的路径
		if(currentPath.search("/") == -1 || currentPath == ""){
			alert("已经是最上面的界面了！");
		}
		else{
			//将之前访问的路径放到访问过的路径的栈中
			pathStack.push(currentPath);
			$("#returnOneLevel").removeAttr("disabled");
			currentPath = currentPath.substring(0, currentPath.lastIndexOf('/'));
			//listTable(currentPath);
		}
		flushBoard();
	});
	
	$("#returnOneLevel").click(function(){
		//出栈显示
		currentPath = pathStack.pop();
		flushBoard();
		if(pathStack == false){
			$("#returnOneLevel").attr("disabled", "disabled");
		}
	});
	
	});
	
	//存完文件马上访问，会被拒绝
</script>
</body>
</html>