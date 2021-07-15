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
<div id="app">
	<admin-table ref="theme_type">
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
	<admin-table ref="theme">
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
<script type="module">
import AdminTable from './js/admin.js';

const vm = Vue.createApp({})
			.component('admin-table', AdminTable)
			.mount('#app');

document.getElementById('submit').onclick = e=>{
	e.preventDefault();
	const xhr = new XMLHttpRequest();
	xhr.open("POST", location.href);
	xhr.send(JSON.stringify({
		theme_type: vm.$refs.theme_type.getContents(),
		theme: vm.$refs.theme.getContents()
	}));
	//xhr.onload = ()=>location.href="";
}
document.getElementById('cancel').onclick = e=>{
	e.preventDefault();
	location.href="";
}
</script>
</body>
</html>