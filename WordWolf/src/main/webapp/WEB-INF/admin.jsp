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
</style>
</head>
<body>
<form action="" method="POST">
	<table>
		<thead>
			<tr><th>種類</th></tr>
		</thead>
		<tbody>
			<%-- themeTypeListからfor文ですべての値を表示 --%>
			<% for(String themeType: themeTypeList){ %>
			<tr>
				<td><%= themeType %></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<table>
		<thead>
			<tr>
				<th>種類</th>
				<th>お題</th>
			</tr>
		</thead>
		<tbody>
			<%-- themeListからfor文ですべての値を表示 --%>
			<% for(Map<String, String> elem: themeList) { %>
			<tr>
				<td><%= elem.get("type") %></td>
				<td><%= elem.get("theme") %></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<button id="submit">適用</button>
	<button id="cancel">キャンセル</button>
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
</body>
</html>