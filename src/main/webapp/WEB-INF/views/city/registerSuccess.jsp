<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   <!-- 에러 처리 할때 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  <!-- 다국어 처리 할때 -->
 
<!DOCTYPE html >
<html>
<head>
<meta  charset="UTF-8">
<title>registerSuccess.jsp</title>
<style type="text/css">
	.box{
		border-top: 1px double red;
		border-bottom: 1px double blue;
		border-left: 1px double lime;
		border-right: 1px double black;
		width: 500px;
		box-shadow: 20px 10px 10px pink; 
		text-shadow: 5px 5px 5px gray;
	}
</style>
</head>
<body>
<h1><spring:message code="city.register.success" /></h1>

<pre class="box">
id	 		= ${city.id }<br/>
name 		= ${city.name }<br/>
countryCode = ${city.country.code } <br/>
district 	= ${city.district } <br/>
population 	= ${city.population } <br/>
</pre>

<a href="register">도시 입력 화면으로....</a>


</body>
</html>