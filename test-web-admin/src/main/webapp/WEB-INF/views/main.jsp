<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--jstl提供的专门用来格式化的工具--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <title>织物测试| 控制面板</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <small>控制面板</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>
</body>
<jsp:include page="../includes/footer.jsp"/>
</html>
