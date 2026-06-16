<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>云借阅-图书管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/_all-skins.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <style id="themeStyle"></style>
    <style>
        .theme-circle{display:inline-block;width:50px;height:50px;border-radius:50%;
            margin:10px 12px;cursor:pointer;border:3px solid transparent;transition:all .2s;}
        .theme-circle:hover{transform:scale(1.15);border-color:#333;}
    </style>
    <script type="text/javascript">
        function SetIFrameHeight() {
            var iframeid = document.getElementById("iframe");
            if (document.getElementById) {
                iframeid.height = document.documentElement.clientHeight;
            }
        }
    </script>
</head>

<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">
    <!-- 页面头部 -->
    <header class="main-header">
        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/admin/main.jsp" class="logo">
            <span class="logo-lg"><b>云借阅-图书管理系统</b></span>
        </a>
        <!-- 头部导航 -->
        <nav class="navbar navbar-static-top">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${pageContext.request.contextPath}/img/user.jpg" class="user-image"
                                 alt="User Image">
                            <span class="hidden-xs">${USER_SESSION.name}</span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" style="box-shadow: 0 6px 24px rgba(0,0,0,0.25);border-radius:6px;min-width:200px;padding:8px 0;border:none;">
                            <c:if test="${USER_SESSION.canBorrow == 1}">
                            <li><a href="#" data-toggle="modal" data-target="#unbanRequestModal"
                                   style="color:#d9534f;padding:10px 20px;font-size:14px;">
                                <i class="fa fa-ban" style="margin-right:8px;"></i> 账号不可借书，联系管理员</a></li>
                            <li class="divider" style="margin:4px 0;"></li>
                            </c:if>
                            <li><a href="${pageContext.request.contextPath}/user/profile" target="iframe"
                                   style="padding:10px 20px;font-size:14px;">
                                <i class="fa fa-user" style="margin-right:8px;width:16px;"></i> 个人资料</a></li>
                            <li class="divider" style="margin:4px 0;"></li>
                            <li><a href="#" data-toggle="modal" data-target="#themeModal"
                                   style="padding:10px 20px;font-size:14px;">
                                <i class="fa fa-paint-brush" style="margin-right:8px;width:16px;"></i> 设置主题</a></li>
                        </ul>
                    </li>
                    <li class="dropdown user user-menu">
                        <a href="${pageContext.request.contextPath}/user/logout">
                            <span class="hidden-xs">注销</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li id="admin-index">
                    <a href="main.jsp">
                        <i class="fa fa-dashboard"></i> <span>首页</span>
                    </a>
                </li>
                <!-- 人员管理 -->
                <li id="admin-login">
                    <a href="${pageContext.request.contextPath}/user/search" target="iframe">
                        <i class="fa fa-circle-o"></i>人员管理
                    </a>
                </li>
                <!-- 消息管理 -->

                <!-- 图书管理 -->
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-folder"></i>
                        <span>图书管理</span>
                        <span class="pull-right-container">
				       			<i class="fa fa-angle-left pull-right"></i>
				   		 	</span>
                    </a>
                    <ul class="treeview-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/book/search" target="iframe">
                                <i class="fa fa-circle-o"></i>图书借阅
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/searchBorrowed" target="iframe">
                                <i class="fa fa-circle-o"></i>当前借阅
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/record/searchRecords" target="iframe">
                                <i class="fa fa-circle-o"></i>借阅记录
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/searchReservable" target="iframe">
                                <i class="fa fa-circle-o"></i>预约图书
                            </a>
                        </li>
                        <c:if test="${USER_SESSION.role =='ADMIN'}">
                        <li>
                            <a href="${pageContext.request.contextPath}/book/searchOverdue" target="iframe">
                                <i class="fa fa-circle-o"></i>超时管理
                            </a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                <c:if test="${USER_SESSION.role =='ADMIN'}">
                <li id="admin-blacklist">
                    <a href="${pageContext.request.contextPath}/book/blacklistCandidates" target="iframe">
                        <i class="fa fa-circle-o"></i>设置用户黑名单
                    </a>
                </li>
                </c:if>
                <li id="admin-message">
                    <a href="${pageContext.request.contextPath}/message/myMessages" target="iframe">
                        <i class="fa fa-circle-o"></i>消息管理
                    </a>
                </li>
            </ul>
        </section>

        <!-- /.sidebar -->
    </aside>
    <!-- 导航侧栏 /-->
    <!-- 内容展示区域 -->
    <div class="content-wrapper">
        <iframe width="100%" id="iframe" name="iframe" onload="SetIFrameHeight()"
                frameborder="0" src="${pageContext.request.contextPath}/book/selectNewbooks"></iframe>
    </div>
</div>

<!-- 主题设置模态框 -->
<div class="modal fade" id="themeModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4>选择主题颜色</h4>
            </div>
            <div class="modal-body text-center">
                <span class="theme-circle" onclick="setTheme('skin-green')" style="background:#00a65a;" title="默认绿"></span>
                <span class="theme-circle" onclick="setTheme('skin-blue')" style="background:#3c8dbc;" title="天空蓝"></span>
                <span class="theme-circle" onclick="setTheme('skin-red')" style="background:#dd4b39;" title="中国红"></span>
                <span class="theme-circle" onclick="setTheme('skin-purple')" style="background:#605ca8;" title="优雅紫"></span>
                <span class="theme-circle" onclick="setTheme('skin-yellow')" style="background:#f39c12;" title="活力黄"></span>
                <span class="theme-circle" onclick="setTheme('skin-black')" style="background:#222d32;" title="深色黑"></span>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 申请解除黑名单模态框 -->
<div class="modal fade" id="unbanRequestModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>申请解除黑名单</h4>
            </div>
            <div class="modal-body">
                <form id="unbanRequestForm">
                    <table class="table table-bordered table-striped" width="800px">
                        <tr>
                            <td>消息接收人</td>
                            <td><select class="form-control" id="requestReceiver"></select></td>
                            <td>发送人</td>
                            <td><input class="form-control" readonly value="${USER_SESSION.name}"></td>
                        </tr>
                        <tr>
                            <td>消息内容</td>
                            <td colspan="3"><textarea class="form-control" id="requestContent" rows="3" placeholder="请填写申请解除黑名单的原因"></textarea></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" onclick="sendUnbanRequest()">发送</button>
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script>
var themeSkins = {
    'skin-green':  { sidebar: '#00a65a', header: '#00a65a', logo: '#008d4c' },
    'skin-blue':   { sidebar: '#3c8dbc', header: '#3c8dbc', logo: '#367fa9' },
    'skin-red':    { sidebar: '#dd4b39', header: '#dd4b39', logo: '#d33724' },
    'skin-purple': { sidebar: '#605ca8', header: '#605ca8', logo: '#555299' },
    'skin-yellow': { sidebar: '#f39c12', header: '#f39c12', logo: '#db8b0b' },
    'skin-black':  { sidebar: '#222d32', header: '#222d32', logo: '#1a2226' }
};

function setTheme(skin) {
    localStorage.setItem('cloudLibraryTheme', skin);
    $('#themeModal').modal('hide');
    applyTheme(skin);
}

function applyTheme(skin) {
    var colors = themeSkins[skin] || themeSkins['skin-green'];
    var css = '.main-header,.main-sidebar{background:' + colors.sidebar + '!important}'
            + '.logo{background:' + colors.logo + '!important}'
            + '.main-header .navbar{background:' + colors.header + '!important}'
            + '.sidebar-menu>li.active>a,.sidebar-menu>li:hover>a{background:' + colors.logo + '!important}';
    $('#themeStyle').html(css);
    $('body').removeClass(Object.keys(themeSkins).join(' ')).addClass(skin);
}

$(function() {
    var saved = localStorage.getItem('cloudLibraryTheme');
    if (saved) applyTheme(saved);
});

$("#unbanRequestModal").on("show.bs.modal", function() {
    $.get("${pageContext.request.contextPath}/message/getAdmins", function(res) {
        if (res.success && res.data) {
            var sel = $("#requestReceiver").empty();
            $.each(res.data, function(i, admin) {
                sel.append('<option value="' + admin.name + '">' + admin.name + '</option>');
            });
        }
    });
});

function sendUnbanRequest() {
    var jieShouRen = $("#requestReceiver").val();
    var content = $("#requestContent").val();
    if (!jieShouRen) { alert("请选择管理员"); return; }
    if (!content) { alert("请填写申请内容"); return; }
    $.post("${pageContext.request.contextPath}/message/sendUnbanRequest", {jieShouRen: jieShouRen, content: content}, function(res) {
        alert(res.message);
        if (res.success) { $("#unbanRequestModal").modal("hide"); }
    });
}
</script>
</body>
</html>