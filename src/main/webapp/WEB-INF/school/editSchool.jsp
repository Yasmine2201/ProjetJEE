<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07/11/2023
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edition de ${school.schoolName}</title>
</head>
<body>
<jsp:include page="/WEB-INF/restricted/navbar/admin.jsp"/>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <form class="box has-text-centered px-3" method="post" action="controller">
                <h3 class="title is-2 has-text-left">Edition de ${school.schoolName}</h3>
                <div class="columns">
                    <div class="column is-half">
                        <h3 class="subtitle is-5 has-text-left"> Nom de l'ecole </h3>
                        <input class="input is-rounded" name="shoolName" placeholder="Nom de l'ecole"
                               value="${school.schoolName}">
                    </div>

                    <div class="column is-half">
                        <h3 class="subtitle is-5 has-text-left"> Specialisation de l'ecole </h3>
                        <input class="input is-rounded" name="shoolName" placeholder="spécialisation"
                               value="${school.specializations}">
                    </div>
                </div>
                <h3 class="subtitle is-5 has-text-left"> Adresse de l'ecole </h3>
                <input class="input is-rounded" name="shoolName" placeholder="addresse" value="${school.address}">
                <div class="pt-6">
                    <button class="button is-success"> Valider</button>
                    <button class="button is-danger"> Annuler</button>
                </div>
            </form>
        </div>
    </div>
</section>


<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
