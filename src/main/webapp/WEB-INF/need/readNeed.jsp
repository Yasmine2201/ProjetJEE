<c:set var="need"
       value="${{'schoolName' : 'HEC','contractType' : 'Temporary','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'}}"/>


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
                                    <p class="title is-3 has-text-left">${need.schoolName.schoolName} </p>
                                </div>
                                <div class="level-item">
                                    <form method="post" action="controller">
                                        <input class="is-hidden" name="needId" value="${need.schoolName.schoolName}">
                                        <button class="button has-text-info" type="submit" name="action" value="goToSchool">
                                            <span class="material-symbols-outlined">
                                                read_more
                                            </span>
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
                    <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                        <thead>
                        <tr>
                            <th> Type de contrat</th>

                            <th> Matière</th>

                            <th> Période</th>

                            <c:if test="${sessionUser.role =='Teacher'}">
                                <th>
                                    Candidater
                                </th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${need.contractType eq 'Any'}">
                                        Non précisé
                                    </c:when>
                                    <c:when test="${need.contractType eq 'Continous'}">
                                        Durée indéterminée
                                    </c:when>
                                    <c:when test="${need.contractType eq 'Temporary'}">
                                        Prestation
                                    </c:when>
                                </c:choose>
                            </td>

                            <td>${need.subject}</td>

                            <td> ${need.timePeriod} </td>

                            <c:if test="${sessionUser.role  == 'Teacher'}">
                                <td>
                                    <form method="post" action="controller">
                                        <label>
                                            <input class="is-hidden" name="needId"
                                                   value="${need.id}">
                                        </label>
                                        <button type="submit" name="action" value="goToCandidature"
                                                class="button is-success"> Candidater
                                        </button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>
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