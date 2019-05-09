<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--jstl提供的专门用来格式化的工具--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <title>织物测试| 用户详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body">
    <table id="dataTable" class="table">
        <tbody>
        <tr>
            <td>
                邮箱:
            </td>
            <td>
                ${tbUser.email}
            </td>
        </tr> <tr>
            <td>
                姓名:
            </td>
            <td>
                ${tbUser.username}
            </td>
        </tr> <tr>
            <td>
                手机:
            </td>
            <td>
                ${tbUser.phone}
            </td>
        </tr> <tr>
            <td>
                创建时间:
            </td>
            <td>
                <ftm:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr><tr>
            <td>
                更新时间:
            </td>
            <td>
                <ftm:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>

</div>
</body>
<jsp:include page="../includes/footer.jsp"/>


</html>
