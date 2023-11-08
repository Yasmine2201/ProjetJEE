<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Besoin N°${need.id}</title>
</head>
<body>
<c:if test="${sessionUser.role eq 'Admin'}">
    <jsp:include page="../restricted/navbar/admin.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq'Teacher'}">
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
                        <p class="title is-2 has-text-left ml-2">Besoin N<sup>o</sup>${need.id} </p>
                        <p class="subtitle has-text-left ml-3 pb-3"><u>Matière:</u> ${need.subject}</p>
                    </div>
                    <c:if test="${sessionUser.role eq 'Recruiter'}">
                        <div class="column">
                            <form class="has-text-right" method="post" action="controller">
                                <input class="is-hidden" name="needId" value="${need.id}">
                                <button class="button is-info" type="submit" name="action" value="goToNeedEdition">
                                    Modifier
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${sessionUser.role eq 'Teacher'}">
                        <div class="column">
                            <form class="has-text-right" method="post" action="controller">
                                <input class="is-hidden" name="needId" value="${need.id}">
                                <button class="button is-info" type="submit" name="action" value="applyToNeed">
                                    Candidater
                                </button>
                                <div class="has-text-danger error-div">${errorMessage}</div>
                            </form>
                        </div>
                    </c:if>
                </div>
                <div class="column ml-2">
                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left mb-0"> École </h3>
                            <form class="has-text-left" method="post" action="controller">
                                <input class="is-hidden" name="schoolName" value="${need.schoolName.schoolName}">
                                <button class="title-button is-5 pb-3" type="submit" name="action"
                                        value="goToSchool">
                                    <sup>⇱</sup>${need.schoolName.schoolName}
                                </button>
                            </form>
                        </div>
                        <div class="column">
                            <h3 class="title is-5 has-text-left mb-0"> Recruteur </h3>
                            <form class="has-text-left" method="post" action="controller">
                                <input class="is-hidden" name="recruiterId" value="${need.recruiter.id}">
                                <button class="title-button is-5 pb-3" type="submit" name="action"
                                        value="goToRecruiterProfile">
                                    <sup>⇱</sup>
                                    ${fn: toUpperCase(need.recruiter.applicationuser.lastname)}
                                    ${need.recruiter.applicationuser.firstname}
                                </button>
                            </form>
                        </div>
                    </div>

                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left"> Type de Contrat </h3>
                            <c:if test="${need.contractType =='Continous'}">
                                <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                    CDI
                                </p>
                            </c:if>
                            <c:if test="${need.contractType == 'Temporary'}">

                                <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                    CDD
                                </p>
                            </c:if>
                            <c:if test="${need.contractType == 'Any'}">

                                <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                    CDI et/ou CDD ${need.timePeriod}
                                </p>
                            </c:if>
                        </div>

                        <div class="column">
                            <c:if test="${need.contractType != 'Continous'}">
                                <h3 class="title is-5 has-text-left"> Durée </h3>
                                <p class="subtitle is-5 has-text-left ml-3 pb-3">${need.timePeriod}</p>
                            </c:if>
                        </div>
                    </div>

                    <h3 class="title is-5 has-text-left"> Prérecquis </h3>
                    <div class="min-height is-2">
                        <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${need.requirements}</p>
                    </div>

                    <h3 class="title is-5 has-text-left"> Description </h3>
                    <div class="min-height is-1">
                        <p class="subtitle is-5 has-text-left ml-3 pb-3 is-wrapped"> ${need.notes}</p>
                    </div>


                    <c:if test="${sessionUser.role eq 'Recruiter' and not empty candidatures}">
                    <h3 class="title is-3 has-text-left mt-6">Candidatures </h3>
                    <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
                        <thead>
                        <tr>
                            <th>Id du besoin</th>
                            <th>Enseignant</th>
                            <th>Matière</th>
                            <th>Validations</th>
                            <th>Statut</th>
                            <th>Ouvrir</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${candidatures}" var="candidature">
                            <tr id=${candidature.need.id}>
                                <td class="is-narrow">${candidature.need.id}</td>
                                <td>
                                    <form method="post" action="controller">
                                        <label>
                                            <input class="is-hidden" name="teacherId"
                                                   value="${candidature.teacher.id}">
                                        </label>
                                        <button type="submit" name="action" value="goToTeacher"
                                                class="has-text-info">
                                                ${candidature.teacher.applicationuser.firstname}
                                                ${fn:toUpperCase(candidature.teacher.applicationuser.lastname)}
                                        </button>
                                    </form>
                                </td>
                                <td>${candidature.need.subject}</td>
                                <td class="is-narrow">
                                    <c:if test="${!candidature.isValidatedByTeacher}">
                                        <p class="tag is-danger"> Enseignant </p></c:if>
                                    <c:if test="${candidature.isValidatedByTeacher}">
                                        <p class="tag is-success"> Enseignant </p></c:if>

                                    <c:if test="${!candidature.isValidatedByRecruiter}">
                                        <p class="tag is-danger"> Recruteur </p></c:if>
                                    <c:if test="${candidature.isValidatedByRecruiter}">
                                        <p class="tag is-success"> Recruteur </p></c:if>
                                </td>
                                <td class="is-narrow">
                                    <c:if test="${candidature.status == 'Pending'}">
                                        <p class="tag is-warning"> En cours </p>
                                    </c:if>

                                    <c:if test="${candidature.status == 'Accepted'}">
                                        <p class="tag is-success"> Accepter </p>
                                    </c:if>

                                    <c:if test="${candidature.status == 'Refused'}">
                                        <p class="tag is-danger"> Refuser </p>
                                    </c:if>
                                </td>
                                <td class="is-narrow">
                                    <form method="post" action="controller">

                                        <input class="is-hidden" name="needId" value="${candidature.need.id}">
                                        <input class="is-hidden" name="teacherId" value="${candidature.teacher.id}">
                                        <button class="button is-info is-small" type="submit" name="action" value="goToCandidature">
                                            <span class="material-symbols-outlined is-size-6">read_more</span>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/footer.jsp"/>
</section>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>