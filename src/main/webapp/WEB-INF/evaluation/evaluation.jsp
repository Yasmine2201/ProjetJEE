<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10/11/2023
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        <form method="post" action="controller">
            <div class="column is-10 ">
                <div class="box has-text-centered px-3">
                    <h3 class="title is-2 has-text-left">
                        Evaluation de ${teacher.applicationuser.firstname} ${teacher.applicationuser.lastname}
                    </h3>
                    <h3 class="title is-4 has-text-left"> Note </h3>
                    <div class="select is-left has-text-left">
                        <select name="" id="constractTypeSelect">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </div>

                    <h3 class="title is-4 has-text-left"> Commentaire </h3>
                    <textarea class="textarea is-rounded has-fixed-size"
                              name="comment">${empty evaluation ? '' : evaluation.comment}</textarea>
                    <div class="pt-6">
                        <button class="button is-success mr-3" name="action" value="upsertEvaluation"> Evaluer</button>
                        <button class="button is-danger" name="action" value="cancelTeacherEdition"> Annuler</button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</section>


<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
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

