<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>超时管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script>var contextPath = "${pageContext.request.contextPath}";</script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<div class="box-header with-border">
    <h3 class="box-title">超时管理</h3>
</div>
<div class="box-body">
    <div class="table-box">
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th>图书名称</th>
                <th>图书作者</th>
                <th>出版社</th>
                <th>标准ISBN</th>
                <th>借阅人</th>
                <th>借阅时间</th>
                <th>应归还时间</th>
                <th>超时天数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.rows}" var="book">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.press}</td>
                    <td>${book.isbn}</td>
                    <td>${book.borrower}</td>
                    <td>${book.borrowTime}</td>
                    <td style="color:red;">${book.returnTime}</td>
                    <td style="color:red;">${book.overdueDays}天</td>
                    <td>
                        <c:if test="${USER_SESSION.role =='ADMIN'}">
                            <button class="btn btn-warning btn-xs" onclick="sendRemind('${book.borrower}','${book.name}')">超时提醒</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="pagination" class="pagination"></div>
    </div>
</div>
</body>
<script>
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize);
    pageargs.cur = ${pageNum};
    pageargs.gourl = "${gourl}";
    pagination(pageargs);

    function getProjectPath() { return contextPath || ""; }

    function sendRemind(borrower, bookName) {
        if (!confirm("确定向 " + borrower + " 发送超时归还提醒？")) return;
        $.post(getProjectPath() + "/message/sendRemind", {jieShouRen: borrower, bookName: bookName}, function(res) {
            alert(res.message);
        });
    }

</script>
</html>
