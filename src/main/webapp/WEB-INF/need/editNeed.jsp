<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07/11/2023
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edition du besoins ${need.needId}</title>
</head>
<body>
<c:choose>

    <c:when test="${sessionUser.Role eq 'Admin'}">
        <jsp:include page="/WEB-INF/restricted/navbar/admin.jsp"/>
    </c:when>

    <c:when test="${sessionUser.Role eq 'Recruiter'}">
        <jsp:include page="/WEB-INF/restricted/navbar/recruiter.jsp"/>
    </c:when>

</c:choose>



<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
