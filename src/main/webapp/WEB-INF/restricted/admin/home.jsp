<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tableau de bord</title>
</head>

<body>
<jsp:include page="../navbar/admin.jsp"/>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <h3 class="title is-3 has-text-left">En attente de validation</h3>

                <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                    <thead>
                    <tr>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Courrier électronique</th>
                        <th>N° de téléphone</th>
                        <th>Rôle</th>
                        <th>École</th>
                        <th>Valider / Refuser</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pendingRegistrations}" var="registration">
                        <tr id= ${registration.registrationId}>
                            <td>${registration.firstname}</td>
                            <td>${registration.lastname}</td>
                            <td>${registration.email}</td>
                            <td>${registration.phone}</td>
                            <td>${registration.role}</td>
                            <td>
                                <form method="post" action="controller">
                                    <label>
                                        <input class="is-hidden" name="schoolName"
                                               value="${registration.schoolName.schoolName}">
                                    </label>
                                    <button type="submit" name="action" value="goToSchool"
                                            class="has-text-info">

                                            ${registration.schoolName.schoolName}
                                    </button>
                                </form>
                            </td>

                            <td>
                                <form method="post" action="controller">
                                    <input class="is-hidden" name="registrationId" value="${registration.registrationId}">

                                    <button class="button is-success is-small" type="submit" name="action"
                                            value="approveRegistration">
                                    <span class="material-symbols-outlined">done</span>
                                    </button>

                                    <button class="button is-danger is-small" type="submit" name="action"
                                            value="denyRegistration">
                                    <span class="material-symbols-outlined">close</span>
                                    </button>
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty message}">
                <div class="notification" id="message">
                    ${message}
                </div>
            </c:if>
        </div>
    </div>
</section>
</body>
<%--<jsp:include page="/WEB-INF/footer.jsp"/>--%>
</html>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        setTimeout(hideNotification, 5000);
    })

    function hideNotification() {
        const notification = document.getElementById("message");

        if (notification === null) return;
        notification.hidden = true;
    }
</script>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
