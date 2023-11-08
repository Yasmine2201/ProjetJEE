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
    <c:if test="${not empty school}">
        <title>Edition de ${school.schoolName}</title>
    </c:if>
    <c:if test="${empty school}">
        <title>Création d'une école</title>
    </c:if>


</head>
<body>
<jsp:include page="/WEB-INF/restricted/navbar/admin.jsp"/>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <form class="box has-text-centered px-3" method="post" action="controller">

                <c:if test="${not empty school}">
                    <h3 class="title is-2 has-text-left">Edition de ${school.schoolName}</h3>
                </c:if>
                <c:if test="${empty school}">
                    <h3 class="title is-2 has-text-left">Création d'une école</h3>
                </c:if>
                <div class="columns">
                    <div class="column is-half">

                        <h3 class="subtitle is-5 has-text-left"> Nom de l'école<sup class="has-text-danger">*</sup></h3>
                        <c:if test="${empty school}">
                            <input class="input is-rounded" name="schoolName" placeholder="Nom de l'école" value="${schoolName}">
                        </c:if>
                        <c:if test="${not empty school}">
                            <input class="input is-rounded " name="schoolName" placeholder="Nom de l'école"
                                   value="${school.schoolName}" readonly>
                        </c:if>
                    </div>

                    <div class="column is-half">
                        <h3 class="subtitle is-5 has-text-left"> Spécialisation de l'école<sup class="has-text-danger">*</sup></h3>
                        <c:if test="${empty school}">
                            <input class="input is-rounded" name="specializations" placeholder="Spécialisation" value="${specializations}">
                        </c:if>
                        <c:if test="${not empty school}">
                            <input class="input is-rounded" name="specializations" placeholder="Spécialisation" value="${school.specializations}">
                        </c:if>
                    </div>
                </div>

                <h3 class="subtitle is-5 has-text-left"> Adresse de l'école<sup class="has-text-danger">*</sup></h3>
                <c:if test="${empty school}">
                    <input class="input is-rounded" name="address" placeholder="Adresse" value="${address}">
                </c:if>
                <c:if test="${not empty school}">
                    <input class="input is-rounded" name="address" placeholder="Adresse" value="${school.address}">
                </c:if>

                <div class="pt-6">
                    <c:if test="${empty school}">
                        <button class="button is-success" name="action" value="createSchool"> Valider</button>
                    </c:if>
                    <c:if test="${not empty school}">
                        <button class="button is-success" name="action" value="updateSchool"> Valider</button>
                    </c:if>

                    <button class="button is-danger" name="action" value="back"> Annuler</button>
                </div>
                <div class="has-text-danger error-div">${errorMessage}</div>
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
