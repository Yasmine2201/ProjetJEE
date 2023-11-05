<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur 400</title>
</head>
<body>
<jsp:include page="./restricted/navbar/error.jsp"/>

<section class="section">
    <div class="box px-6">
        <div class="notification is-danger">
            <p class="title is-1">Erreur code : <b>400</b>:</p>
            <p class="text is-size-3">
                Requête invalide
            </p>

        </div>
    </div>
</section>
</body>
<jsp:include page="/WEB-INF/footer.jsp"/>
</html>
