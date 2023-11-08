<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07/11/2023
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${not empty need}">
        <title>Edition du besoins N°${need.id}</title>
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
                <form method="post" action="controller" class="has-text-left">
                    <c:if test="${not empty need}">
                        <input class="is-hidden" name="schoolName"
                               value="${sessionUser.recruiter.schoolName.schoolName}">
                        <button class="title-button is-2" type="submit" name="action"
                                value="goToSchool"><sup>⇱</sup>${need.schoolName.schoolName}
                        </button>
                    </c:if>
                    <c:if test="${empty need}">
                        <input class="is-hidden" name="schoolName"
                               value="${school.schoolName}">
                        <button class="title-button is-2" type="submit" name="action"
                                value="goToSchool"><sup>⇱</sup>${school.schoolName}
                        </button>
                    </c:if>
                </form>
                <form method="post" action="controller">
                    <input class="is-hidden" name="needId" value="${need.id}">
                    <input class="is-hidden" name="schoolName" value="${need.schoolName.schoolName}">

                    <c:if test="${not empty nedd}">
                        <h3 class="title is-2 has-text-left">Édition du besoins N° ${need.id}</h3>
                    </c:if>
                    <c:if test="${empty need}">
                        <h3 class="title is-2 has-text-left">Création d'un besoin</h3>
                    </c:if>
                    <div class="columns">
                        <div class="column">
                            <h3 class="title is-4 has-text-left"> Sujet à enseigner<sup class="has-text-danger">*</sup>
                            </h3>
                            <input class="input is-rounded " name="subject" placeholder="Sujet à enseigner"
                                   value="${need.subsect}">
                        </div>
                        <div class="column">
                            <div class="column">
                                <h3 class="title is-4 has-text-left"> Type de contract <sup
                                        class="has-text-danger">*</sup></h3>
                                <div class="select is-rounded is-fullwidth has-text-left">
                                    <select name="contractType">
                                        <option value="Any" ${need.contractType == 'Any' ? 'selected="selected"' : ''}>
                                            CDD et/ou CDI
                                        </option>
                                        <option value="Continous" ${need.contractType == 'Continous' ? 'selected="selected"' : ''}>
                                            CDI
                                        </option>
                                        <option value="Temporary" ${need.contractType == 'Temporary' ? 'selected="selected"' : ''}>
                                            CDD
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="column">
                                <h3 class="title is-4 has-text-left"> Temp du contract <sup
                                        class="has-text-danger">*</sup>
                                </h3>
                                <input class="input is-rounded " name="timePeriod" placeholder="XX/XX/XXXX - XX/XX/XXXX"
                                       value="${need.timePeriod}">
                            </div>
                        </div>
                    </div>
                    <h3 class="title is-4 has-text-left"> Expérience requis <sup class="has-text-danger">*</sup></h3>
                    <input class="input is-rounded " name="requirements" placeholder="Diplôme requis"
                           value="${need.requirements}">

                    <h3 class="title is-4 has-text-left"> Description <sup class="has-text-danger">*</sup></h3>
                    <input class="input is-rounded" name="notes" value="${need.notes}" placeholder="Description">
                    <div class="pt-3">
                        <button name="action" value="saveNeed" type="submit" class="button is-success"> Valider</button>
                        <button name="action" value="back" type="submit" class="button is-danger"> Anuller</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
