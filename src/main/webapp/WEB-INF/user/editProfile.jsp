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
            <form method="post" action="controller" onsubmit="validateForm()">
                <div class="box has-text-centered px-3">
                    <h3 class="title is-2 has-text-left">
                        Edition du profil
                    </h3>
                    <h3 class="title is-5 has-text-left"> Nom d'utilisateur </h3>
                    <input class="input is-rounded " name="login" placeholder="Nom d'utilisateur"
                           value="${user.login}">

                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left mt-3"> Nom </h3>
                            <input class="input is-rounded " name="lastname" placeholder="Nom"
                                   value="${user.lastname}">
                        </div>
                        <div class="column">
                            <h3 class="title is-5 has-text-left mt-3"> Prénom </h3>
                            <input class="input is-rounded " name="firstname" placeholder="Prénom"
                                   value="${user.firstname}">
                        </div>
                    </div>

                    <h3 class="title is-5 has-text-left"> Changer le mot de passe </h3>
                    <input class="input is-rounded" type="password" name="password" placeholder="*********">

                    <h3 class="title is-5 has-text-left mt-3"> Confirmez votre mot de passe </h3>
                    <input class="input is-rounded" type="password" name="passwordVerification" placeholder="*********">

                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left mt-3"> Email </h3>
                            <input class="input is-rounded " name="email" placeholder="Email"
                                   value="${user.email}">

                        </div>
                        <div class="column">
                            <h3 class="title is-5 has-text-left mt-3"> Téléphone </h3>
                            <input name="phone" value="${user.phone}" type="tel" class="input is-rounded"
                                   inputmode="numeric" maxlength="30"
                                   onkeypress="return (event.charCode !==8 && event.charCode ===0 || event.charCode ===43 || event.charCode ===32 || event.charCode ===127 || (event.charCode >= 48 && event.charCode <= 57))"
                                   placeholder="+XX X-XX-XX-XX-XX"/>
                        </div>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="notification" id="message">
                                ${message}
                        </div>
                    </c:if>

                    <div class="pt-6">
                        <button class="button is-success mr-3" name="action" value="updateUserProfile"> Valider</button>
                        <button class="button is-danger" name="action" value="cancelProfileEdition"> Annuler</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
<jsp:include page="/WEB-INF/footer.jsp"/>
</html>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        showNotification(null);
    })

    function validateForm() {
        const password = document.getElementById("password").value;
        const passwordVerification = document.getElementById("passwordVerification").value;

        if (password !== passwordVerification) {
            showNotification("Mot de passe de confirmation non conforme.");
            return false;
        }

        return true;
    }

    function showNotification(message) {
        const notification = document.getElementById("message");

        if (notification === null) return;
        notification.hidden = false;

        if (message != null) {
            notification.textContent = message;
        }

        setTimeout(hideNotification, 5000)
    }

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
