<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--jstl提供的专门用来格式化的工具--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>


<!DOCTYPE html>
<head>
    <title>织物测试| 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <small>内容管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult !=  null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-ban"></i> Alert!</h4>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">分类列表</h3>


                            <div class="box-body">
                                <a href="/content/category/form" type="button" class="btn btn-sm  btn-default"><i
                                        class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm  btn-default"><i class="fa fa-download"></i>导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm  btn-default"><i class="fa fa-upload"></i>导出</a>&nbsp;&nbsp;&nbsp;
                            </div>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                <th>ID</th>
                                <th>名称</th>
                                <th>排序</th>
                                <th>操作</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbContentCategories}" var="tbContentCategory">
                                    <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parent.id}">
                                        <td>${tbContentCategory.id}</td>
                                        <td>${tbContentCategory.name}</td>
                                        <td>${tbContentCategory.sortOrder}</td>
                                        <td>
                                            <a href="/content/category/form?id=${tbContentCategory.id}" type="button"  class="btn btn-sm  btn-success"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;
                                            <button type="button"  class="btn btn-sm  btn-danger" onclick="App.deleteSingle('/content/category/delete','${tbContentCategory.id}','警告：该删除操作会将包括选中类目的全部子类目及属于类目的内容一并删除，请谨慎操作！您还确定要删除吗？')"><i class="fa fa-trash-o"></i>删除</button>&nbsp;&nbsp;&nbsp;
                                            <%--//把列表页里的值带回后台，再传回到表单页----传到了form方法，因为这里显示的和编辑里的不同，所以用了一点小技巧，覆盖了modelattribute里的内容--%>
                                            <a href="/content/category/form?parent.id=${tbContentCategory.id}&parent.name=${tbContentCategory.name}" type="button"  class="btn btn-sm  btn-default"><i class="fa fa-plus"></i>新增下级菜单</a>&nbsp;&nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<%--自定义模态框--%>
<sys:modal/>
<script>
    $(function () {
        $('#treeTable').treeTable({
            column: 1,
            expandLevel: 2
        })
    });
</script>
</body>
</html>
