<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 20/10/2023
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page admin</title>
</head>
<body>
<jsp:include page="../navbar/admin.jsp"/>
<c:set var="candidatures" scope="session" value="${'registrationId' :10}">

</c:set>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <form class="box has-text-centered px-3" method="post" action="controller">
                <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                    <thead>
                    <tr>
                        <th colspan="8">
                            En attend de validation
                        </th>
                    </tr>
                    <tr>
                        <th>
                            Nom
                        </th>
                        <th>
                            Prenom
                        </th>
                        <th>
                            Email
                        </th>
                        <th>
                            N° de telephone
                        </th>
                        <th>
                            Role
                        </th>
                        <th>
                            Ecole
                        </th>
                        <th>
                            Validation
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${candidatures}" var="candidature">
                        <tr id= ${candidature.registrationId}>

                            <td>${candidature.lastname}</td>

                            <td>${candidature.firstname}</td>

                            <td>${candidature.phone}</td>

                            <td>${candidature.role}</td>

                            <td>${candidature.schoolName}</td>
                            <td>
                                <button class="button is-success" type="submit" name="action" value="approveRegistration">
                                    <span class="material-symbols-outlined">
                                        done
                                    </span>
                                </button>
                                <button class="button is-danger" type="submit" name="action" value="denyRegistration">
                                    <span class="material-symbols-outlined">
                                        close
                                    </span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</section>
</body>
</html>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
