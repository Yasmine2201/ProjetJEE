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
                        <div class="level">
                            <div class="level-left">
                                <div class="level-item">
                                    <form method="post" action="controller">
                                        <input class="is-hidden" name="schoolName"
                                               value="${need.schoolName.schoolName}">
                                        <button class="title-button is-2" type="submit" name="action"
                                                value="goToSchool"><sup>⇱</sup>${need.schoolName.schoolName}
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <c:if test="${sessionUser.role eq 'Recruiter' or sessionUser.role eq 'Admin'}">
                            <form class="has-text-right" method="post" action="controller">
                                <input class="is-hidden" name="needId" value="${need.id}">
                                <button class="button is-info" type="submit" name="action" value="goToNeedEdition">
                                    Modifier
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <p class="subtitle is-3 has-text-left">Besoin N° ${need.id} </p>
                <div class="column">
                    <h3 class="title is-5 has-text-left"> Reruteur </h3>
                    <form class="has-text-left" method="post" action="controller">
                        <input class="is-hidden" name="recruiterId" value="${need.recruiter.id}">
                        <button class="title-button is-5 pb-3 ml-3" type="submit" name="action"
                                value="goToRecruiterProfile">
                            <sup>⇱</sup>${fn: toUpperCase(need.recruiter.applicationuser.lastname)} ${need.recruiter.applicationuser.firstname}
                        </button>
                    </form>
                    <h3 class="title is-5 has-text-left"> Type de Contract </h3>
                    <c:if test="${need.contractType =='Continous'}">
                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                            CDI
                        </p>
                    </c:if>
                    <c:if test="${need.contractType == 'Temporary'}">

                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                            CDD
                        </p>
                        <h3 class="title is-5 has-text-left"> Durée </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${need.timePeriod}</p>
                    </c:if>
                    <c:if test="${need.contractType  == 'Any'}">

                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                            CDI et/ou CDD ${need.timePeriod}
                        </p>
                        <h3 class="title is-5 has-text-left"> Durée </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${need.timePeriod}</p>
                    </c:if>

                    <h3 class="title is-5 has-text-left"> Sujet </h3>
                    <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${need.subject}</p>

                    <h3 class="title is-5 has-text-left"> Requis </h3>
                    <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${need.requirements}</p>
                    <h3 class="title is-5 has-text-left"> Description </h3>
                    <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${need.notes}</p>

                    <c:if test="${sessionUser.role eq 'Recruiter' and not empty candidatures}">
                    <h3 class="title is-3 has-text-left">Candudature </h3>
                    <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth ">
                        <thead>
                        <tr>
                            <th> Nom</th>
                            <th> Prénom</th>
                            <th> Qualification</th>
                            <th> Disponibilité</th>
                            <th> Vérifier la candidature</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${candidatures}" var="candidature">
                            <tr>
                                <td> ${candidature.teacher.applicationuser.lastname} </td>

                                <td> ${candidature.teacher.applicationuser.lastname} </td>

                                <td> ${candidature.teacher.academicCertifications} </td>

                                <td> ${candidature.teacher.disponibilities} </td>

                                <td>
                                    <form class="has-text-right" method="post" action="controller">
                                        <input class="is-hidden" name="teacherId" value="${candidature.teahcer.id}">
                                        <input class="is-hidden" name="needId" value="${need.id}">
                                        <button class="button is-info" type="submit" name="action" value="goToCandidature">
                                            Vérifier la candidature
                                        </button>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
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