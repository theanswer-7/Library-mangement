<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>消息管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<div class="box-header with-border">
    <h3 class="box-title">消息管理</h3>
</div>
<div class="box-body">
    <div class="table-box">
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th>发送人</th>
                <th>消息内容</th>
                <th>发送时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messages}" var="msg">
                <tr>
                    <td>${msg.faShongRen}</td>
                    <td style="max-width:300px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">${msg.content}</td>
                    <td>${msg.sendTime}</td>
                    <td>
                        <c:if test="${msg.status == 0}"><span style="color:red;">未读</span></c:if>
                        <c:if test="${msg.status == 1}"><span style="color:green;">已读</span></c:if>
                    </td>
                    <td>
                        <button class="btn btn-info btn-xs" onclick="viewDetail(${msg.id})">查看详情</button>
                        <c:if test="${msg.status == 0}">
                            <button class="btn btn-success btn-xs" onclick="markRead(${msg.id})">确认已读</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- 消息详情模态窗口，默认隐藏 -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">发送详情</h3>
            </div>
            <div class="modal-body">
                <form id="detailForm">
                    <table class="table table-bordered table-striped" width="800px">
                        <tr>
                            <td>发送人</td>
                            <td><input class="form-control" readonly id="dFaShongRen"></td>
                            <td>接收人</td>
                            <td><input class="form-control" readonly id="dJieShouRen"></td>
                        </tr>
                        <tr>
                            <td>发送时间</td>
                            <td><input class="form-control" readonly id="dSendTime"></td>
                            <td>消息状态</td>
                            <td><input class="form-control" readonly id="dStatus"></td>
                        </tr>
                        <tr>
                            <td>发送内容</td>
                            <td colspan="3"><input class="form-control" readonly id="dContent"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <span id="approveBtn" style="display:none;">
                    <button class="btn btn-success" onclick="approveUnban()">同意解除黑名单</button>
                </span>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
function getProjectPath() { return contextPath || ""; }
var currentMsgId, currentSender;

function viewDetail(id) {
    $.get(getProjectPath() + "/message/findById", {id: id}, function(res) {
        if (res.success) {
            var m = res.data;
            currentMsgId = m.id;
            currentSender = m.faShongRen;
            $("#dFaShongRen").val(m.faShongRen);
            $("#dJieShouRen").val(m.jieShouRen);
            $("#dSendTime").val(m.sendTime);
            $("#dStatus").val(m.status == 0 ? "未读" : "已读");
            $("#dContent").val(m.content);
            // 管理员且内容是黑名单解除申请时，显示同意按钮
            var isAdmin = "${USER_SESSION.role}" === "ADMIN";
            var isUnbanReq = m.content.indexOf("黑名单解除申请") >= 0;
            $("#approveBtn").toggle(isAdmin && isUnbanReq);
            $("#detailModal").modal("show");
        }
    });
}

function approveUnban() {
    if (!confirm("确认同意 " + currentSender + " 的解除黑名单申请？")) return;
    $.post(getProjectPath() + "/user/unbanUser", {name: currentSender}, function(res) {
        if (res.success) {
            $.post(getProjectPath() + "/message/markRead", {id: currentMsgId});
        }
        alert(res.message);
        if (res.success) location.reload();
    });
}

function markRead(id) {
    $.post(getProjectPath() + "/message/markRead", {id: id}, function(res) {
        alert(res.message);
        if (res.success) location.reload();
    });
}
</script>
</html>
