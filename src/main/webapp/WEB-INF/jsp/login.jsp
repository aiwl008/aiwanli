<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>

<base href="<%=basePath%>">
<meta charset="utf-8">
<title>礼物网</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="login-full-page">
        <div class="login-content-box">
            <div class="login-content">
                <form id="login" method="post" action="login"  role="form">
                    <input type="hidden" name="isLogin" id="isLogin" value="true"/>
                    <div class="login-group">
                        <label>用户名：</label>
                        <input type="text" name="username" class="input-txt"  maxlength="20"  value="${user.username}" id="username"  >
                    </div>
                    <div class="login-group">
                        <label>密&nbsp;&nbsp;&nbsp;码：</label>
                        <input type="password" name="password" class="input-txt"   value="${user.password}" maxlength="20">
                    </div>
                    <div class="rem-count">
                       <c:if test="${message ne null}">
							 ${message}
						</c:if>
                    </div>
                    <div class="btn-group ">
                        <button type="submit" class="btn btn-commit">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

	
</body>
</html>