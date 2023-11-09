<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil - ${user.firstname} ${fn:toUpperCase(user.lastname)}</title>
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
            <form method="post" action="controller">
                <div class="box has-text-centered px-3">
                    <h3 class="title is-2 has-text-left">Édition du profile</h3>
                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-2 has-text-left">Nom<sup class="has-text-danger">*</sup></h3>
                            <input class="input is-rounded " name="subject" placeholder="Nom"
                                   value="${user.lastName}">
                        </div>
                        <div class="column">
                            <h3 class="title is-2 has-text-left">Prénom<sup class="has-text-danger">*</sup></h3>

                        </div>
                    </div>

                </div>
            </form>
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
