<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enseignant - ${teacher.applicationuser.firstname} ${fn:toUpperCase(teacher.applicationuser.lastname)}</title>
</head>

<body>

<jsp:include page="../restricted/navbar/teacher.jsp"/>

<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">



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
