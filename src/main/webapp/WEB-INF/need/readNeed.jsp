<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 05/11/2023
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>

<c:set var="need"
       value="${{'contractType' : 'Temporary','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'}}"/>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionUser.role eq 'Admin'}">
    <jsp:include page="../restricted/navbar/admin.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq'Teacher'}">
    <jsp:include page="../restricted/navbar/teacher.jsp"/>
</c:if>

<c:if test="${sessionUser.role eq 'Recruiter'}">
    <jsp:include page="../restricted/navbar/recruiter.jsp"/>
</c:if>

<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <div class="title is-3 has-text-left"> ${need.schoolName}
                    <form method="post" action="controller">
                        <input class="is-hidden" name="needId" value="${need.schoolName}">
                        <button class="button is-info" type="submit" name="action" value="goToSchool">
                        <span class="material-symbols-outlined">
                            read_more
                        </span>
                        </button>
                    </form>
                </div>
                <p class="subtitle is-3 has-text-left">besoin N° ${need.needId} </p>
                <div class="column">
                    <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                        <thead>
                        <tr>
                            <th> Type de contract</th>

                            <th> Sujet</th>

                            <th> Nécessaire</th>

                            <th> Temps</th>

                            <th> Notes</th>

                            <c:if test="${sessionUser.role =='Teacher' }">
                                <th>
                                    Candidater
                                </th>
                            </c:if>

                            <c:if test="${sessionUser.role =='Recruiter'}">
                                <th>
                                    Modifer
                                </th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <c:if test="${need.contractType  == 'Temporary'}">
                                    CDD
                                </c:if>
                                <c:if test="${need.contractType  == 'Continous'}">
                                    CDI
                                </c:if>
                                <c:if test="${need.contractType  == 'Any'}">
                                    CDI et/ou CDD
                                </c:if>
                            </td>

                            <td>${need.subject}</td>

                            <td>${need.requirements}</td>

                            <td> ${need.timePeriod} </td>

                            <td> ${need.notes} </td>

                            <c:if test="${sessionUser.role  == 'Teacher'}">
                                <td>
                                    <form method="post" action="controller">
                                        <label>
                                            <input class="is-hidden" name="needId"
                                                   value="${need.needId}">
                                        </label>
                                        <button type="submit" name="action" value="goToCandidature"
                                                class="button is-primary"> Candidater
                                        </button>
                                    </form>
                                </td>
                            </c:if>
                            <c:if test="${sessionUser.role eq 'Recruiter' and fn:contains( recuitersId, sessionUser.id)}">

                                <td>
                                    <form method="post" action="controller">
                                        <label>
                                            <input class="is-hidden" name="needId"
                                                   value="${need.needId}">
                                        </label>
                                        <button type="submit" name="action" value="goToNeedEdition"
                                                class="button is-primary"> Modifier
                                        </button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
    </div>
</section>
</body>
</html>
