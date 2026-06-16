<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<div class="box-header with-border">
    <h3 class="box-title">个人资料</h3>
</div>
<div class="box-body">
    <div class="row">
        <!-- 个人信息 -->
        <div class="col-md-6">
            <div class="box box-primary">
                <div class="box-header"><h4>基本信息</h4></div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr><td width="120">工号</td><td>${profileUser.id}</td></tr>
                        <tr><td>姓名</td><td>${profileUser.name}</td></tr>
                        <tr><td>邮箱</td><td>${profileUser.email}</td></tr>
                        <tr><td>角色</td><td>${profileUser.role == 'ADMIN' ? '管理员' : '普通用户'}</td></tr>
                        <tr><td>入职日期</td><td>${profileUser.hiredate}</td></tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- 修改密码 -->
        <div class="col-md-6">
            <div class="box box-danger">
                <div class="box-header"><h4>修改密码</h4></div>
                <div class="box-body">
                    <div class="form-group">
                        <label>原密码</label>
                        <input type="password" class="form-control" id="oldPwd" placeholder="请输入原密码">
                    </div>
                    <div class="form-group">
                        <label>新密码</label>
                        <input type="password" class="form-control" id="newPwd" placeholder="请输入新密码">
                    </div>
                    <div class="form-group">
                        <label>确认新密码</label>
                        <input type="password" class="form-control" id="confirmPwd" placeholder="请再次输入新密码">
                    </div>
                    <button class="btn btn-primary" onclick="updatePwd()">修改密码</button>
                    <span id="pwdMsg" style="margin-left:10px;"></span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
function updatePwd() {
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    var confirmPwd = $("#confirmPwd").val();
    if (!oldPwd || !newPwd) { $("#pwdMsg").css("color","red").text("请填写完整"); return; }
    if (newPwd !== confirmPwd) { $("#pwdMsg").css("color","red").text("两次密码不一致"); return; }
    $.post(contextPath + "/user/updatePassword", {oldPwd: oldPwd, newPwd: newPwd}, function(res) {
        if (res.success) {
            $("#pwdMsg").css("color","green").text(res.message);
        } else {
            $("#pwdMsg").css("color","red").text(res.message);
        }
    });
}
</script>
</body>
</html>
