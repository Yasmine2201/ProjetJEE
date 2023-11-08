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
                    </div>
                    <div class="column">
                        <c:if test="${sessionUser.role eq 'Recruiter'}">
                            <form method="post" action="controller">
                                <input class="is-hidden" name="teacherId" value="${teacher.id}">

                                <button class="button is-info" type="submit" name="action"
                                        value="graduateTeacher"> noter le prof
                                </button>
                            </form>
                        </c:if>

                        <c:if test="${sessionUser.role eq 'Recruiter'}">
                            <form method="post" action="controller">
                                <input class="is-hidden" name="teacherId" value="${teacher.id}">

                                <button class="button is-info" type="submit" name="action"
                                        value="graduateTeacher"> noter le prof
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>


                <h3 class="title is-5 has-text-left"> Certifications </h3>
                <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.academicCertifications}</p>

                <h3 class="title is-5 has-text-left"> Experiences </h3>
                <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.experiences}</p>

                <h3 class="title is-5 has-text-left"> Recommendations </h3>
                <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.recommendations}</p>

                <h3 class="title is-5 has-text-left"></h3>
                <p class="subtitle is-5 has-text-left ml-3 pb-3"> ${candidature.teacher.applicaitonuser.lastname}</p>


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
