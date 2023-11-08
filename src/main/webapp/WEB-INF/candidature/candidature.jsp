<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07/11/2023
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidature</title>
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
                <form method="post" action="controller" class="has-text-left">
                    <input class="is-hidden" name="schoolName"
                           value="${candidature.schoolName.schoolName}">
                    <button class="title-button is-2" type="submit" name="action"
                            value="goToSchool"><sup>⇱</sup>${candidature.schoolName.schoolName}
                    </button>
                </form>
                <form method="post" action="controller" class="has-text-left">
                    <input class="is-hidden" name="needId"
                           value="${candidature.need.id}">
                    <button class="title-button is-2" type="submit" name="action"
                            value="goToNeed"><sup>⇱</sup>Besoin N°${candidature.need.id}
                    </button>
                </form>

                <div class="columns">
                    <div class="column">
                        <h3 class="title is-5 has-text-left"> Requis </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.requirements}</p>

                        <h3 class="title is-5 has-text-left"> Type de contract </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.contractType}</p>

                        <c:if test="${candidature.need.contractType == 'Temporary'}">
                            <h3 class="title is-5 has-text-left"> Temps du contract </h3>
                            <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.timePeriod}</p>
                        </c:if>

                        <c:if test="${candidature.need.contractType == 'Any'}">
                            <h3 class="title is-5 has-text-left"> Temps du contract </h3>
                            <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.timePeriod}</p>
                        </c:if>

                        <h3 class="title is-5 has-text-left"> Sujet </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.subject}</p>


                        <h3 class="title is-5 has-text-left"> Description </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.notes}</p>

                        <h3 class="title is-5 has-text-left">Status</h3>
                        <c:if test="${candidature.status eq 'Accepting'}">
                            <p class="tag is-success"> Accepter</p>
                        </c:if>
                        <c:if test="${candidature.status eq 'Pending'}">
                            <p class="tag is-warning"> En cours</p>
                        </c:if>
                        <c:if test="${candidature.status eq 'Refused'}">
                            <p class="tag is-danger"> Reffuser</p>
                        </c:if>

                        <h3 class="title is-5 has-text-left">Valider par></h3>

                        <c:if test="${candidature.isValidatedByTeacher}">
                            <p class="tag is-danger"> Enseignant </p></c:if>
                        <c:if test="${!candidature.isValidatedByTeacher}">
                            <p class="tag is-success"> Enseignant </p></c:if>

                        <c:if test="${candidature.isValidatedByRecruiter}">
                            <p class="tag is-danger"> Recruteur </p></c:if>
                        <c:if test="${!candidature.isValidatedByRecruiter}">
                            <p class="tag is-success"> Recruteur </p></c:if>
                    </div>
                    <div class="column">
                        <form method="post" action="controller" class="has-text-left">
                            <input class="is-hidden" name="teacherId"
                                   value="${candidature.need.id}">
                            <button class="title-button is-2" type="submit" name="action"
                                    value="goToNeed"><sup>⇱</sup>Professeur
                            </button>
                        </form>

                        <h3 class="title is-5 has-text-left"> Nom </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.applicaitonuser.lastname}</p>

                        <h3 class="title is-5 has-text-left"> Prénom </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.applicaitonuser.firstName}</p>

                        <h3 class="title is-5 has-text-left"> Email </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.applicaitonuser.email}</p>

                        <h3 class="title is-5 has-text-left"> Téléphone </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.applicaitonuser.phone}</p>


                    </div>
                </div>
            </div>

            <form method="post" action="controller">
                <input class="is-hidden" name="schoolName" value="${candidature.schoolName.schoolName}">
                <input class="is-hidden" name="needId" value="${candidature.need.id}">
                <input class="is-hidden" name="teacherId" value="${candidature.teacher.id}">

                <button class="button is-success" type="submit" name="action"
                        value="validateCandidature" ${ isValidateEnable == 0 ? '"disabled"' : ''} ${ isValidateVisible == 0 ? 'class="is-hidden"' : ''}>
                    Valider
                </button>
            </form>

            <form method="post" action="controller">
                <input class="is-hidden" name="schoolName" value="${candidature.schoolName.schoolName}">
                <input class="is-hidden" name="needId" value="${candidature.need.id}">
                <input class="is-hidden" name="teacherId" value="${candidature.teacher.id}">

                <button class="button is-danger" type="submit" name="action"
                        value="denyCandidature" ${ isRefuseEnable  == 0 ? '"disabled"' : ''} ${ isRefuseVisible == 0 ? 'class="is-hidden"' : ''}>
                    Refuser
                </button>
            </form>
        </div>
    </div>
    <c:if test="${not empty message}">
        <div class="notification" id="message">
                ${message}
        </div>
    </c:if>
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
