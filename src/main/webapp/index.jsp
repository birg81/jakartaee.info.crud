<%@page import="java.util.Date"%>
<%@page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="it">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,shrink-to-fit=no,user-scalable=yes,initial-scale=1.0,maximum-scale=4.0,minimum-scale=0.5" />
<link type="image/png" rel="icon" href="https://em-content.zobj.net/source/samsung/349/laptop_1f4bb.png" />
<title>Tomcat Test</title>
</head>
<body>
<h5><%= new Date() %></h5>
<ul>
<li><a href="./api/info/subpath?param0=value0&param1=value0">info</a></li>
<li><a href="./api/people/">CRUD</a></li>
<li><a href="./peoplelist.html">people list</a></li>
</ul>
</body>
</html>