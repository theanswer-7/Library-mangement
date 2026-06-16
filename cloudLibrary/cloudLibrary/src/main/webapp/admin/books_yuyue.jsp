<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>预约图书</title>
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
    <h3 class="box-title">预约图书</h3>
</div>
<div class="box-body">
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/book/searchReservable" method="post">
                图书名称：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp
                图书作者：<input name="author" value="${search.author}">&nbsp&nbsp&nbsp&nbsp
                出版社：<input name="press" value="${search.press}">&nbsp&nbsp&nbsp&nbsp
                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
    <div class="table-box">
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th>图书名称</th><th>作者</th><th>出版社</th><th>ISBN</th><th>状态</th>
                <th>借阅人</th><th>借阅时间</th><th>预计归还</th><th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.rows}" var="book">
                <tr>
                    <td>${book.name}</td><td>${book.author}</td><td>${book.press}</td><td>${book.isbn}</td>
                    <td><c:if test="${book.status==1}">借阅中</c:if><c:if test="${book.status==2}">归还中</c:if></td>
                    <td>${book.borrower}</td><td>${book.borrowTime}</td><td>${book.returnTime}</td>
                    <td><button class="btn btn-success btn-xs" onclick="reserveBook(${book.id})">预约借阅</button></td>
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
    bookVO.name = "${search.name}";
    bookVO.author = "${search.author}";
    bookVO.press = "${search.press}";
    pagination(pageargs);
</script>
</html>
