<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 20/10/2023
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="./navbar/error.jsp"/>

<section class="section">
    <div class="box px-6">
        <div class="notification is-danger">
            <p class="title is-1">Erreur cdoe <b>Error code</b>:</p>
            <p class="text is-size-3">
                Error Message
            </p>

        </div>
    </div>
</section>
</body>
</html>
