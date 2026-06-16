<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>黑名单管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<div class="box-header with-border">
    <h3 class="box-title">黑名单管理</h3>
</div>
<div class="box-body">
    <div class="table-box">
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th>借阅人</th>
                <th>提醒次数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${candidates}" var="c">
                <tr>
                    <td>${c.borrower}</td>
                    <td style="color:red;">${c.msgCount}</td>
                    <td>
                        <c:if test="${c.canBorrow == 1}">
                            <button class="btn btn-default btn-xs" disabled>已在黑名单</button>
                            <button class="btn btn-success btn-xs" onclick="unbanUser('${c.borrower}')">移出黑名单</button>
                        </c:if>
                        <c:if test="${c.canBorrow != 1}">
                            <button class="btn btn-danger btn-xs" onclick="banUser('${c.borrower}')">加入黑名单</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty candidates}">
            <p class="text-center">暂无黑名单候选人</p>
        </c:if>
    </div>
</div>
</body>
<script>
function getProjectPath() { return contextPath || ""; }

function banUser(name) {
    if (!confirm("确认将 " + name + " 加入黑名单？加入后该用户将无法借书。")) return;
    $.post(getProjectPath() + "/user/banUser", {name: name}, function(res) {
        alert(res.message);
        if (res.success) location.reload();
    });
}

function unbanUser(name) {
    if (!confirm("确认将 " + name + " 移出黑名单？")) return;
    $.post(getProjectPath() + "/user/unbanUser", {name: name}, function(res) {
        alert(res.message);
        if (res.success) location.reload();
    });
}
</script>
</html>
