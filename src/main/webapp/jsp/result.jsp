<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String winner = (String) request.getAttribute("winner");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ワードウルフ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alignCenter.css">
<style>
body {
    animation: fadeIn 3.5s ease 0.5s 1 normal;
    -webkit-animation: fadeIn 2s ease 0s 1 normal;
}

@keyframes fadeIn {
    0% {opacity: 0}
    100% {opacity: 1}
}

@-webkit-keyframes fadeIn {
    0% {opacity: 0}
    100% {opacity: 1}
}
</style>
<body>
<h1>結果</h1>
<p>投票結果は・・・</p>
<% if(winner.equals("person")){ %>
<p><font size="6">村人の勝ち！</font></p>
<% } else if(winner.equals("wolf")){ %>
<p><font size="6">ウルフの勝ち！</font></p>
<% } else { %>
<p>エラー</p>
<% } %>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">終了</a><br><br>
<img src="${pageContext.request.contextPath}/images/wolf-icon.png">
<script>
window.onload = ()=>{
	setTimeout(()=>location.href="${pageContext.request.contextPath}/index.jsp", 30000);
}
</script>
</body>
</html>
