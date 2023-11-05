<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tableau de bord</title>
</head>
<body>

<jsp:include page="../navbar/recruiter.jsp"/>
<section class="section">

    <div class="columns">
        <div class="column">
            <h3 class="title is-3 has-text-left">Besoins de recrutement</h3>
            <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth ">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Matière</th>
                        <th>Type de contrat</th>
                        <th>Période</th>
                        <th>Ouvrir</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${runningNeeds}" var="need">
                        <tr id=${need.needId}>
                            <td class="is-narrow">${need.id}</td>
                            <td>${need.subject}</td>
                            <td class="is-narrow">
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
                            <td>
                                <c:if test="${not empty need.timePeriod}">${need.timePeriod}</c:if>
                                <c:if test="${empty need.timePeriod}">${need.timePeriod}</c:if>
                            </td>
                            <td class="is-narrow">
                                <form method="post" action="controller">
                                    <input class="is-hidden" name="needId" value="${need.needId}">
                                    <button class="button is-info is-small" type="submit" name="action" value="goToNeed">
                                        <span class="material-symbols-outlined is-size-6">read_more</span>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="column">
            <h3 class="title is-3 has-text-left">Candidatures en cours</h3>
            <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
                <thead>
                    <tr>
                        <th>Id du besoin</th>
                        <th>Enseignant</th>
                        <th>Validations</th>
                        <th>Ouvrir</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${pendingCandidatures}" var="candidature">
                        <tr id=${candidature.need.needId}>
                            <td class="is-narrow">${candidature.need.id}</td>
                            <td>
                                ${candidature.teacher.applicationuser.firstname}
                                ${fn:toUpperCase(candidature.teacher.applicationuser.lastname)}
                            </td>
                            <td class="is-narrow">
                                <c:if test="${candidature.isValidatedByTeacher}">
                                    <p class="tag is-danger"> Enseignant </p></c:if>
                                <c:if test="${!candidature.isValidatedByTeacher}">
                                    <p class="tag is-success"> Enseignant </p></c:if>

                                <c:if test="${candidature.isValidatedByRecruiter}">
                                    <p class="tag is-danger"> Recruteur </p></c:if>
                                <c:if test="${!candidature.isValidatedByRecruiter}">
                                    <p class="tag is-success"> Recruteur </p></c:if>
                            </td>
                            <td class="is-narrow">
                                <form method="post" action="controller">
                                    <input class="is-hidden" name="needId" value="${candidature.need.needId}">
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
        </div>
    </div>
</section>
</body>
<jsp:include page="/WEB-INF/footer.jsp"/>
</html>
