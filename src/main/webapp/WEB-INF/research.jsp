<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recherche</title>
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
            <div class="title is-1 has-text-dark">
                Recherche
            </div>
            <form method="post" action="controller">
                <div class="columns">
                    <div class="column is-2">
                        <select class="select is-fullwidth pr-2" name="reseach">
                            <option value="Teachers">Enseignant</option>
                            <option value="Schools">École</option>
                            <option value="Needs">Besoins</option>
                        </select>
                    </div>
                    <div class="column">
                        <button class="button is-success mr-3" name="action" value="sendResearch">
                            Rechercher
                        </button>
                    </div>
                </div>
            </form>
            <div class="box px-3 has-text-centered">
                <c:if test="${not empty teachers}">
                    <h3 class="title is-2 has-text-left">Enseignants</h3>

                    <c:forEach items="${teachers}" var="teacher">
                        <div class="box px-6 pb-3">
                            <form method="post" action="controller" class="has-text-left">
                                <input class="is-hidden" name="teacherId" value="${teacher.id}">
                                <button class="title-button is-5 " name="action" value="goToTeacher">
                                    <sup>⇱</sup>${teacher.aplicationuser.firstname} ${teacher.aplicationuser.lastname}
                                </button>
                            </form>
                            <div class="level">
                                <div class="level-left">
                                    <div class="level-item">
                                        <c:if test="${teacher.contractType =='Continous'}">
                                            <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                                CDI
                                            </p>
                                        </c:if>
                                        <c:if test="${teacher.contractType == 'Temporary'}">

                                            <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                                CDD
                                            </p>
                                        </c:if>
                                        <c:if test="${teacher.contractType == 'Any'}">

                                            <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                                CDI et/ou CDD
                                            </p>
                                        </c:if>
                                    </div>
                                    <div class="level-item">
                                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                            ${teacher.schoolInterests}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="needs">
                    <h3 class="title is-2 has-text-left">Besoins</h3>

                    <h3 class="title is-2 has-text-left">Enseignants</h3>

                    <c:forEach items="${needs}" var="need">
                        <div class="box px-6 pb-3">
                            <form method="post" action="controller" class="has-text-left">
                                <input class="is-hidden" name="needId" value="${need.id}">
                                <button class="title-button is-5 " name="action" value="goToNeed">
                                    <sup>⇱</sup>N<sup>o</sup> ${need.id}
                                </button>
                            </form>
                            <div class="level">
                                <div class="level-left">
                                    <div class="level-item">
                                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                            ${need.schoolName.schoolName}
                                        </p>
                                    </div>
                                    <div class="level-item">
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
                                                CDI et/ou CDD
                                            </p>
                                        </c:if>
                                    </div>
                                    <div class="level-item">
                                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                            ${need.subject}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </c:if>
                <c:if test="schools">
                    <h3 class="title is-2 has-text-left">Écoles</h3>

                    <c:forEach items="${schools}" var="school">
                        <div class="box px-6 pb-3">
                            <form method="post" action="controller" class="has-text-left">
                                <input class="is-hidden" name="schoolName" value="${school.schoolName}">
                                <button class="title-button is-5 " name="action" value="goToNeed">
                                    <sup>⇱</sup>${school.schoolName}
                                </button>
                            </form>
                            <div class="level">
                                <div class="level-left">
                                    <div class="level-item">
                                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                                ${fn:length(school.needs)} Besoins
                                        </p>
                                    </div>
                                    <div class="level-item">
                                        <p class="subtitle is-5 has-text-left ml-2 pb-3">
                                                ${school.specialisation}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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
