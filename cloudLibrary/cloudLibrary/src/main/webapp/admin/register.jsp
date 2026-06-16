<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册 - 云借阅</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/webbase.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pages-login-manage.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<div class="loginmanage">
    <div class="py-container">
        <h4 class="manage-title">云借阅-图书管理系统</h4>
        <div class="loginform">
            <ul class="sui-nav nav-tabs tab-wraped">
                <li class="active">
                    <h3>用户注册</h3>
                </li>
            </ul>
            <div class="tab-content tab-wraped">
                <span style="color: red">${msg}</span>
                <div id="profile" class="tab-pane active">
                    <form id="regform" class="sui-form" action="${pageContext.request.contextPath}/user/register" method="post">
                        <div class="input-prepend"><span class="add-on loginname">姓名</span>
                            <input type="text" name="name" placeholder="请输入姓名" class="span2 input-xfat">
                        </div>
                        <div class="input-prepend"><span class="add-on loginname">邮箱</span>
                            <input type="text" name="email" placeholder="请输入企业邮箱" class="span2 input-xfat">
                        </div>
                        <div class="input-prepend"><span class="add-on loginpwd">密码</span>
                            <input type="password" name="password" placeholder="请输入密码" class="span2 input-xfat">
                        </div>
                        <div class="logined">
                            <a class="sui-btn btn-block btn-xlarge btn-danger"
                               href='javascript:document:regform.submit();' target="_self">注&nbsp;&nbsp;册</a>
                        </div>
                    </form>
                    <div style="text-align:center;margin-top:10px;">
                        <a href="${pageContext.request.contextPath}/admin/login.jsp">已有账号？返回登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
