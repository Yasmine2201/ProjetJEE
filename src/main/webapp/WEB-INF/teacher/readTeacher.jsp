<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enseignant - ${teacher.applicationuser.firstname} ${fn:toUpperCase(teacher.applicationuser.lastname)}</title>
</head>

<body>

<c:if test="${sessionUser.role eq 'Admin'}">
    <jsp:include page="../restricted/navbar/admin.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq 'Teacher'}">
    <jsp:include page="../restricted/navbar/teacher.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq 'Recruiter'}">
    <jsp:include page="../restricted/navbar/recruiter.jsp"/>
</c:if>

<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <div class="columns">
                    <div class="column">
                        <h3 class="title is-2 has-text-left">
                            ${teacher.applicationuser.firstname} ${teacher.applicationuser.lastname}
                        </h3>

                        <h3 class="title is-5 has-text-left"> Certifications </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${teacher.academicCertifications}</p>

                        <h3 class="title is-5 has-text-left"> Experiences </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${teacher.experiences}</p>

                        <h3 class="title is-5 has-text-left"> Ecoles interresées </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${teacher.schoolInterests}</p>

                        <h3 class="title is-5 has-text-left"> Autre informations </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${teacher.otherInformations}</p>

                        <h3 class="title is-5 has-text-left"> Contract recherché </h3>
                        <c:choose>
                            <c:when test="${teacher.contractType eq 'Any'}">
                                <p class="subtitle is-5 has-text-left ml-3 pb-3">
                                    CDI et/ou CDD
                                </p>
                            </c:when>
                            <c:when test="${teacher.contractType eq 'Continous'}">
                                <p class="subtitle is-5 has-text-left ml-3 pb-3">
                                    CDI
                                </p>
                            </c:when>
                            <c:when test="${teacher.contractType eq 'Temporary'}">
                                <p class="subtitle is-5 has-text-left ml-3 pb-3">
                                    CDD
                                </p>
                            </c:when>
                        </c:choose>

                        <h3 class="title is-5 has-text-left"> Recommendations </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${teacher.recommendations}</p>

                    </div>
                    <div class="column">
                        <c:if test="${sessionUser.userId == teacher.id}">
                            <form method="post" action="controller" class="has-text-right">
                                <input class="is-hidden" name="teacherId"
                                       value="${teacher.id}">
                                <button class="button is-info" type="submit" name="action"
                                        value="goToTeacherEdition">Modifier
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${sessionUser.role eq 'Recruiter' }">
                            <form method="post" action="controller" class="has-text-right">
                                <input class="is-hidden" name="teacherId" value="${teacher.id}">
                                <input class="is-hidden" name="schoolName"
                                       value="${sessionUser.recruiter.SchoolName.schoolName}">

                                <button class="button is-info" type="submit" name="action"
                                        value="goToEvaluation">Noter le Professeur
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <c:if test="${sessionuser.teacher.id == teacher.id}">
                    <form method="post" action="controller" class="has-text-left">
                        <button class="button is-success" type="submit" name="action"
                                value="goToDisponibilityCreation">Ajouter
                        </button>
                    </form>
                </c:if>
                <h3 class="title is-5 has-text-left"> Disponibilité </h3>
                <c:if test="${not empty futureDisponibilities}">
                    <table class="table is-bordered is-striped is-half is-hoverable">

                        <thead>
                        <tr>
                            <th>
                                Date de début
                            </th>
                            <th>
                                Date de fin
                            </th>
                            <c:if test="${sessionUser.userId == teacher.id}">
                                <th>
                                    Modifier
                                </th>
                                <%--                            <th>--%>
                                <%--                                Supprimer--%>
                                <%--                            </th>--%>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${futureDisponibilities}" var="disponibility">
                            <tr>
                                <td>
                                        ${disponibility.startDate}
                                </td>
                                <td>
                                        ${disponibility.endDate}
                                </td>
                                <td>
                                    <c:if test="${sessionuser.teacher.id == teacher.id}">
                                        <form method="post" action="controller">
                                            <input class="is-hidden" name="disponibilityId" value="${disponibility.id}">

                                            <button class="button is-info" type="submit" name="action"
                                                    value="goToDisponibilityEdition">Modifier
                                            </button>
                                        </form>
                                    </c:if>
                                </td>
                                    <%--                                <td>--%>
                                    <%--                                    <form method="post" action="controller">--%>
                                    <%--                                        <input class="is-hidden" name="disponibilityId" value="${disponibility.id}">--%>

                                    <%--                                        <button class="button is-danger" type="submit" name="action"--%>
                                    <%--                                                value="disponibilityDelete">Supprimer--%>
                                    <%--                                        </button>--%>
                                    <%--                                    </form>--%>
                                    <%--                                </td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:if>
                <c:if test="${empty futureDisponibilities}">
                    <p class="subtitle is-5 has-text-left ml-3 pb-3 is-wrapped"> Le professeur est occupé pour le
                        moment</p>
                </c:if>
                <h3 class="title is-5 has-text-left"> Evaluations </h3>
                <c:if test="${not empty evaluations}">
                    <table class="table is-bordered is-striped is-narrow is-hoverable">
                        <thead>
                        <tr>
                            <th>
                                Ecole
                            </th>
                            <th>
                                Notes
                            </th>
                            <th>
                                Commentaire
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${evaluations}" var="evaluation">
                            <tr>
                                <td>
                                        ${evaluation.schoolName.schoolName}
                                </td>
                                <td>
                                        ${evaluation.rating}
                                </td>
                                <td>
                                        ${evaluation.comment}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>


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
<jsp:include page="/WEB-INF/footer.jsp"/>
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
