<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07/11/2023
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidature</title>
</head>
<body>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <div class="columns">
                    <div class="column">
                        <div class="level">
                            <div class="level-right">
                                <div class="level-item">
                                    <h3 class="title is-3 has-text-right"> ${candidature.needId.schoolName.schoolName}</h3>
                                </div>
                                <div class="level-item">
                                    <form method="post" action="controller">
                                        <input class="is-hidden" name="needId"
                                               value="${candidature.needId.schoolName.schoolName}">
                                        <button class="button has-text-info" type="submit" name="action"
                                                value="goToSchool">
                                            <span class="material-symbols-outlined">
                                                read_more
                                            </span>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="level">
                            <div class="level-right">
                                <div class="level-item">
                                    <h3 class="title is-3 has-text-right">Besoin N° ${candidature.needId.id}</h3>
                                </div>
                                <div class="level-item">
                                    <form method="post" action="controller">
                                        <input class="is-hidden" name="needId" value="${candidature.needId.id}">
                                        <button class="button has-text-info" type="submit" name="action"
                                                value="goToNeed">
                                            <span class="material-symbols-outlined">
                                                read_more
                                            </span>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="column">
                        <h3 class="title is-3 has-text-left">Besoin N° ${candidature.needId.id}</h3>
                        <form method="post" action="controller">
                            <input class="is-hidden" name="needId" value="${candidature.needId.id}">
                            <button class="button has-text-info" type="submit" name="action" value="goToNeed">
                                <span class="material-symbols-outlined">
                                    read_more
                                </span>
                            </button>
                        </form>
                    </div>
                </div>
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
