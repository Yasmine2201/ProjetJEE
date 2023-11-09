<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${not empty need}">
        <title>Edition du besoin N°${need.id}</title>
    </c:if>
    <c:if test="${empty need}">
        <title>Création d'un besoin</title>
    </c:if>

</head>
<body>
<c:if test="${sessionUser.role eq 'Admin'}">
    <jsp:include page="/WEB-INF/restricted/navbar/admin.jsp"/>
</c:if>
<c:if test="${sessionUser.role eq 'Recruiter'}">
    <jsp:include page="/WEB-INF/restricted/navbar/recruiter.jsp"/>
</c:if>


<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <form method="post" action="controller">
                    <input class="is-hidden" name="needId" value="${need.id}">
                    <input class="is-hidden" name="schoolName" value="${need.schoolName.schoolName}">

                    <c:if test="${not empty need}">
                        <h3 class="title is-2 has-text-left">Édition du besoin N<sup>o</sup>${need.id}</h3>
                    </c:if>
                    <c:if test="${empty need}">
                        <h3 class="title is-2 has-text-left">Création d'un besoin</h3>
                    </c:if>
                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-4 has-text-left"> Matière <sup class="has-text-danger">*</sup>
                            </h3>
                            <input class="input is-rounded " name="subject" placeholder="Matière à enseigner"
                                   value="${empty need ? subject : need.subject}">
                        </div>
                        <div class="column">
                            <h3 class="title is-4 has-text-left"> Type de contrat <sup
                                    class="has-text-danger">*</sup></h3>
                            <div class="select is-rounded is-fullwidth has-text-left">
                                <select name="contractType" id="constractTypeSelect" onchange="showHideTimePeriod(this.value)">
                                    <option value="Any" ${(empty need ? contractType : need.contractType) == 'Any' ? 'selected="selected"' : ''}>
                                        CDD et/ou CDI
                                    </option>
                                    <option value="Continous" ${(empty need ? contractType : need.contractType) == 'Continous' ? 'selected="selected"' : ''}>
                                        CDI
                                    </option>
                                    <option value="Temporary" ${(empty need ? contractType : need.contractType) == 'Temporary' ? 'selected="selected"' : ''}>
                                        CDD
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="min-height is-2">
                            <div id="timePeriodDiv">
                                <h3 class="title is-4 has-text-left">
                                    Période du contrat
                                </h3>
                                <input id="timePeriodInput" class="input is-rounded " name="timePeriod" placeholder="XX/XX/XXXX - XX/XX/XXXX"
                                       value="${empty need ? timePeriod : need.timePeriod}">
                            </div>
                        </div>
                    </div>
                    <h3 class="title is-4 has-text-left"> Prérecquis </h3>
                    <textarea class="textarea is-rounded has-fixed-size mb-3" name="requirements">${empty need ? requirements : need.requirements}</textarea>

                    <h3 class="title is-4 has-text-left"> Description </h3>

                    <textarea class="textarea is-rounded has-fixed-size" name="notes">${empty nned ? notes : need.notes}</textarea>

                    <div class="pt-6">
                        <c:if test="${empty need}">
                            <button class="button is-success mr-3" name="action" value="createNeed"> Créer</button>
                            <button class="button is-danger" name="action" value="cancelNeedCreation"> Annuler</button>
                        </c:if>
                        <c:if test="${not empty need}">
                            <button class="button is-success mr-3" name="action" value="updateNeed"> Modifier</button>
                            <button class="button is-danger" name="action" value="cancelNeedEdition"> Annuler</button>
                        </c:if>
                    </div>
                    <div class="has-text-danger error-div">${errorMessage}</div>
                </form>
            </div>
        </div>
    </div>
</section>


<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const contractTypeSelect = document.getElementById("constractTypeSelect");
        showHideTimePeriod(contractTypeSelect.value)
    })

    function showHideTimePeriod(value) {
        let isVisible = (value != 'Continous');

        const timePeriodDiv = document.getElementById("timePeriodDiv");
        timePeriodDiv.hidden = !isVisible;

        if (!isVisible) {
            const timePeriodInput = document.getElementById("timePeriodInput");
            timePeriodInput.value = "";
        }
    }
</script>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
