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
            <form method="post" action="controller">
                <div class="box has-text-centered px-3">
                    <h3 class="title is-2 has-text-left">
                        Modification du profile enseignant
                    </h3>
                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left"> Intéressé par les écoles </h3>
                            <input class="input is-rounded " name="schoolInterests"
                                   placeholder="Intéressé par les écoles"
                                   value="${empty teacher.schoolInterests ? '' : teacher.schoolInterests}">
                        </div>
                        <div class="column">
                            <h3 class="title is-5 has-text-left"> Certifiactions académiques </h3>
                            <input class="input is-rounded " name="acidemicCertifications"
                                   placeholder="Certifiactions académiques"
                                   value="${empty teacher.acidemicCertifications ? '' : teacher.acidemicCertifications}">
                        </div>
                    </div>
                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left"> Type de contrat intéressé </h3>
                            <div class="select is-left has-text-left">
                                <select name="contractType" id="constractTypeSelect">
                                    <option value="Any" ${teacher.contractType == 'Any' ? 'selected="selected"' : ''}>
                                        CDD et/ou CDI
                                    </option>
                                    <option value="Continous" ${teacher.contractType == 'Continous' ? 'selected="selected"' : ''}>
                                        CDI
                                    </option>
                                    <option value="Temporary" ${teacher.contractType == 'Temporary' ? 'selected="selected"' : ''}>
                                        CDD
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="column">
                            <h3 class="title is-5 has-text-left"> Site internet </h3>
                            <input class="input is-rounded " name="website" placeholder="Site internet"
                                   value="${teacher.website}">
                        </div>
                    </div>


                    <h3 class="title is-5 has-text-left"> Loisirs </h3>
                    <input class="input is-rounded " name="personnalInterests" placeholder="Loisirs"
                           value="${teacher.personnalInterests}">

                    <h3 class="title is-5 has-text-left"> Qualitées </h3>
                    <input class="input is-rounded " name="skills" placeholder="Qualitées"
                           value="${teacher.skills}">

                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-5 has-text-left"> Classe voulue </h3>
                            <input class="input is-rounded " name="desiredLevels" placeholder="Classe voulue"
                                   value="${teacher.desiredLevels}">
                        </div>
                    </div>
                    <h3 class="title is-5 has-text-left"> Recommendations </h3>
                    <input class="input is-rounded " name="recommendations" placeholder="Recommendations"
                           value="${teacher.recommendations}">

                    <h3 class="title is-5 has-text-left"> Experiences </h3>
                    <textarea class="textarea is-rounded has-fixed-size mb-3" name="experiences"
                              placeholder="Experiences professionnels">${teacher.experiences}</textarea>

                    <h3 class="title is-5 has-text-left"> Autres informations </h3>
                    <textarea class="textarea is-rounded has-fixed-size mb-3" name="otherInformations"
                              placeholder="Autre informations">${teacher.otherInformations}</textarea>


                    <c:if test="${not empty message}">
                        <div class="notification" id="message">
                                ${message}
                        </div>
                    </c:if>
                    <div class="pt-6">
                        <button class="button is-success mr-3" name="action" value="updateTeacher"> Modifier</button>
                        <button class="button is-danger" name="action" value="cancelTeacherEdition"> Annuler</button>
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
