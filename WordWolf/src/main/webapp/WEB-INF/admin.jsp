<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%
List<String> themeTypeList = (List<String>) request.getAttribute("themeTypeList");
List<Map<String, String>> themeList = (List<Map<String, String>>) request.getAttribute("themeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>
<style>
body{
	text-align: center;
}
table{
	margin: 10px auto;
}
td:hover{
	cursor: pointer;
}
</style>
<script src="https://unpkg.com/vue@next"></script>
</head>
<body>
<form action="" method="POST">
<div id="app">
	<admin-table>
		<template v-slot:head>
			<tr><th>種類</th></tr>
		</template>
		<template v-slot:body>
			<% for(String themeType: themeTypeList){ %>
			<tr>
				<td><%= themeType %></td>
			</tr>
			<% } %>
		</template>
	</admin-table>
	<admin-table>
		<template v-slot:head>
			<tr>
				<th>種類</th>
				<th>お題</th>
			</tr>
		</template>
		<template v-slot:body>
			<% for(Map<String, String> elem: themeList) { %>
			<tr>
				<td><%= elem.get("type") %></td>
				<td><%= elem.get("theme") %></td>
			</tr>
			<% } %>
		</template>
	</admin-table>
	<button id="submit">適用</button>
	<button id="cancel">キャンセル</button>
</div>
</form>
<script>
document.getElementById('submit').onclick = e=>{
	e.preventDefault();
}
document.getElementById('cancel').onclick = e=>{
	e.preventDefault();
	location.href="";
}
</script>
<script src="./js/admin.js"></script>
</body>
</html>