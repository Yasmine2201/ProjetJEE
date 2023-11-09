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
        <div class="column is-10">
            <div class="box has-text-centered px-3">
                <div class="columns">
                    <div class="column">
                        <h3 class="title is-2 has-text-left">
                            Profil de ${user.firstname} ${fn: toUpperCase(user.lastname)}
                        </h3>
                    </div>
                    <div class="column">
                        <c:if test="${sessionUser.userId == user.id}">
                            <form method="post" action="controller" class="has-text-right">
                                <button class="button is-info mr-3" name="action" value="goToUserProfileEdition">
                                    Modifier
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>


                <div class="columns">
                    <div class="column">
                        <h3 class="title is-5 has-text-left"> Nom </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${user.lastname}</p>
                    </div>
                    <div class="column">
                        <h3 class="title is-5 has-text-left"> Prénom </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${user.firstname}</p>
                    </div>
                </div>

                <div class="columns">
                    <div class="column">
                        <h3 class="title is-5 has-text-left"> Email </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${user.email}</p>

                    </div>
                    <div class="column">
                        <h3 class="title is-5 has-text-left"> Téléphone </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${user.phone}</p>

                    </div>
                </div>
                <div class="columns">
                    <div class="column">
                        <h3 class="title is-5 has-text-left"> Role </h3>
                        <p class="subtitle is-5 has-text-left ml-3 pb-3">${user.role}</p>

                    </div>
                    <div class="column">
                        <c:if test="${user.role eq 'Recruiter'}">

                            <h3 class="title is-5 has-text-left"> Ecole </h3>
                            <p class="subtitle is-5 has-text-left ml-3 pb-3">${recruiter.schoolName.schoolName}</p>
                        </c:if>

                    </div>
                </div>


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
