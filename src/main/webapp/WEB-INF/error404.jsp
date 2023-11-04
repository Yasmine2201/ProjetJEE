<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur 404</title>
</head>
<body>
<jsp:include page="./restricted/navbar/error.jsp"/>

<section class="section">
    <div class="box px-6">
        <div class="notification is-danger">
            <p class="title is-1">Erreur code : <b>404</b>:</p>
            <p class="text is-size-3">
                Page ou methode introuvable
            </p>

        </div>
    </div>
</section>
</body>
</html>
