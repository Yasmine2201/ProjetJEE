<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur 403</title>
</head>
<body>
<jsp:include page="./restricted/navbar/error.jsp"/>

<section class="section">
    <div class="box px-6">
        <div class="notification is-danger">
            <p class="title is-1">Erreur <b>403</b>:</p>
            <p class="text is-size-3">
                Non autorisé
            </p>
        </div>
    </div>
</section>
</body>
<jsp:include page="/WEB-INF/footer.jsp"/>
</html>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>