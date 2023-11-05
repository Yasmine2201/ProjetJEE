<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page admin</title>
</head>
<body>
<jsp:include page="../navbar/admin.jsp"/>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                    <thead>
                    <tr>
                        <th colspan="8">
                            En attente de validation
                        </th>
                    </tr>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>N° de telephone</th>
                        <th>Role</th>
                        <th>Ecole</th>
                        <th>Valider ?</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pendingRegistrations}" var="registration">
                        <tr id= ${registration.registrationId}>
                            <td>${registration.lastname}</td>
                            <td>${registration.firstname}</td>
                            <td>${registration.email}</td>
                            <td>${registration.phone}</td>
                            <td>${registration.role}</td>
                            <td>${registration.schoolName.schoolName}</td>

                            <td>
                                <form method="post" action="controller">
                                    <input class="is-hidden" name="registrationId" value="${registration.registrationId}">

                                    <button class="button is-success" type="submit" name="action"
                                            value="approveRegistration">
                                    <span class="material-symbols-outlined">done</span>
                                    </button>

                                    <button class="button is-danger" type="submit" name="action"
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
        </div>
    </div>
</section>
</body>
</html>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
