<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidature</title>
</head>
<body>
<c:if test="${sessionUser.role eq 'Admin'}">
    <jsp:include page="/WEB-INF/restricted/navbar/admin.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq 'Teacher'}">
    <jsp:include page="/WEB-INF/restricted/navbar/teacher.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq 'Recruiter'}">
    <jsp:include page="/WEB-INF/restricted/navbar/recruiter.jsp"/>
</c:if>

<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered p-6">
                <form method="post" action="controller" class="has-text-left mb-3">
                    <input class="is-hidden" name="needId"
                           value="${candidature.need.id}">
                    <input class="is-hidden" name="schoolName"
                           value="${candidature.teacher.id}">
                    <div class="title is-2">
                        Candidature de
                        <button class="title-button is-2 is-inline dotted" type="submit" name="action"
                                value="goToTeacher">
                            ${candidature.teacher.applicationuser.firstname}
                            ${fn:toUpperCase(candidature.teacher.applicationuser.lastname)}
                        </button>
                        - Offre
                        <button class="title-button is-2 is-inline dotted" type="submit" name="action"
                                value="goToNeed">
                            N<sup>o</sup>${candidature.need.id}
                        </button>
                    </div>
                </form>

                <div class="columns">
                    <div class="column has-text-left">
                        <div class="mb-5">
                            <c:if test="${candidature.status eq 'Accepted'}">
                                <p class="tag is-success"> Acceptée</p>
                            </c:if>
                            <c:if test="${candidature.status eq 'Pending'}">
                                <p class="tag is-warning"> En cours</p>
                            </c:if>
                            <c:if test="${candidature.status eq 'Refused'}">
                                <p class="tag is-danger"> Refusée</p>
                            </c:if>
                            <p class="tag ${candidature.isValidatedByTeacher ? "is-success" : "is-danger"}"> Enseignant </p>
                            <p class="tag ${candidature.isValidatedByRecruiter ? "is-success" : "is-danger"}"> Recruteur </p>
                        </div>
                        <p class="title is-3 has-text-left mt-6">École :
                            <input class="is-hidden" name="schoolName"
                                   value="${candidature.schoolName.schoolName}">
                            <button class="title-button is-3 dotted" type="submit" name="action"
                                    value="goToSchool">
                                ${candidature.schoolName.schoolName}
                            </button>
                        </p>

                        <h3 class="title is-5 has-text-left"> Matière </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.subject}</p>

                        <h3 class="title is-5 has-text-left"> Type de contrat </h3>
                        <c:if test="${candidature.need.contractType == 'Continous'}">
                            <p class="subtitle is-5 has-text-left ml-3 pb-3">
                                CDI
                            </p>
                        </c:if>
                        <c:if test="${candidature.need.contractType == 'Temporary'}">
                            <p class="subtitle is-5 has-text-left ml-3 pb-3">
                                CDD
                            </p>
                        </c:if>
                        <c:if test="${candidature.need.contractType == 'Any'}">
                            <p class="subtitle is-5 has-text-left ml-3 pb-3">
                                CDI et/ou CDD
                            </p>
                        </c:if>

                        <div class="min-height is-3">
                            <c:if test="${candidature.need.contractType != 'Continous'}">
                                <h3 class="title is-5 has-text-left"> Période du contrat </h3>
                                <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.timePeriod}</p>
                            </c:if>

                        </div>
                    </div>
                    <div class="column p-6 mt-6 mb-3 card">

                        <p class="title is-3 has-text-left">Professeur</p>

                        <div class="columns">
                            <div class="column">
                                <h3 class="title is-5 has-text-left"> Nom </h3>
                            </div>
                            <div class="column">
                                <p class="subtitle is-5 has-text-right mr-3 pb-3">
                                    ${fn:toUpperCase(candidature.teacher.applicationuser.lastname)}
                                </p>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <h3 class="title is-5 has-text-left"> Prénom </h3>
                            </div>
                            <div class="column">
                                <p class="subtitle is-5 has-text-right ml-3 pb-3">
                                    ${candidature.teacher.applicationuser.firstname}
                                </p>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <h3 class="title is-5 has-text-left"> Email </h3>
                            </div>
                            <div class="column">
                                <p class="subtitle is-5 has-text-right ml-3 pb-3">
                                    ${candidature.teacher.applicationuser.email}
                                </p>
                            </div>
                        </div>
                        <div class="columns">
                            <div class="column">
                                <h3 class="title is-5 has-text-left"> Téléphone </h3>
                            </div>
                            <div class="column">
                                <p class="subtitle is-5 has-text-right ml-3 pb-3">
                                    ${candidature.teacher.applicationuser.phone}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <h3 class="title is-5 has-text-left"> Prérecquis </h3>
                <div class="min-height is-5">
                    <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.need.requirements}</p>
                </div>

                <h3 class="title is-5 has-text-left"> Description </h3>
                <div class="min-height is-1">
                    <p class="subtitle is-5 has-text-left ml-3 pb-3 is-wrapped"> ${candidature.need.notes}</p>
                </div>

                <c:if test="${canChoose}">
                    <form method="post" action="controller" class="pt-6">
                        <input class="is-hidden" name="needId" value="${candidature.need.id}">
                        <input class="is-hidden" name="teacherId" value="${candidature.teacher.id}">

                        <button class="button is-success mr-3" name="action" value="validateCandidature">
                            Valider
                        </button>
                        <button class="button is-danger" name="action" value="denyCandidature">
                            Refuser
                        </button>
                    </form>
                </c:if>

                <div class="has-text-danger error-div mt-2">${errorMessage}</div>
                <div class="has-text-sucess error-div mt-2">${successMessage}</div>
                <c:if test="${not empty message}">
                    <div class="notification" id="message">
                            ${message}
                    </div>
                </c:if>
            </div>
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