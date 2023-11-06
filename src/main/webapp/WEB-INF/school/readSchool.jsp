<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 05/11/2023
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>

<c:set var="school" value="${{'schoolName' : 'HEC', 'address' : '1 rue de la libération, 78350 Jouy-en-Josas', 'specializations' : 'commerce'}}"/>

<c:set var="recruiters"
       value="${[{'firstName' : 'Yesdg', 'lastName' : 'Tesst'},{'firstName' : 'Yesdg', 'lastName' : 'Tesst'},{'firstName' : 'Yesdg', 'lastName' : 'Tesst'},{'firstName' : 'Yesdg', 'lastName' : 'Tesst'}]}"/>

<c:set var="needs" value="${[{'contractType' : 'Temporary','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'},
                            {'contractType' : 'Temporary','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'},
                            {'contractType' : 'Continous','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'},
                            {'contractType' : 'Any','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'},
                            {'contractType' : 'Temporary','needId' : 1024055, 'subject' : 'JAVA', 'requirements': 'je sasi pas quoi mettre', 'timePeriod' : '10/10/2023 - 10/10/2026', 'notes' : 'aucune notes'}]}"/>

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
        <div class="column is-10 ">
            <div class="box has-text-centered px-3">
                <div class="columns">
                    <div class="column">
                        <h3 class="title has-text-left">${school.schoolName}
                        </h3>
                        <p class="subtitle is-6 has-text-left">
                            ${school.specializations}
                        </p>
                        <p class="subtitle has-text-left">${school.address} </p>

                    </div>
                    <div class="column">

                        <c:if test="${sessionUser.role eq 'Admin'}">
                            <form method="post" action="controller">
                                <input class="is-hidden" name="needId"
                                       value="${school.schoolName}">
                                <button type="submit" name="action" value="goToSchoolEdition"
                                        class="button is-info"> Modifier l'ecole
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>

                <div class="columns">
                    <div class="column is-10">
                        <h3 class="title is-3 has-text-left">Besoins</h3>
                        <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                            <thead>
                            <tr>
                                <th> Id</th>
                                <th> Type de contract</th>
                                <th> Sujet</th>
                                <th> Requis</th>
                                <th> Temps</th>
                                <th> Notes</th>
                                <c:if test="${sessionUser.role eq 'Recruiter'}">
                                    <th> Modifier</th>
                                </c:if>
                                <c:if test="${sessionUser.role eq 'Teacher'}">
                                    <th> Candidater</th>
                                </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${needs}" var="need">
                                <tr>
                                    <td>
                                        <form method="post" action="controller">
                                            <label>
                                                <input class="is-hidden" name="needId"
                                                       value="${need.needId}">
                                            </label>
                                            <button type="submit" name="action" value="goToNeed"
                                                    class="button fantom has-text-info"
                                                    style="border: none"> ${need.needId}
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <c:if test="${need.contractType =='Continous' }">
                                            CDI
                                        </c:if>
                                        <c:if test="${need.contractType == 'Temporary' }">
                                            CDD
                                        </c:if>
                                        <c:if test="${need.contractType  == 'Any'}">
                                            CDI et/ou CDD
                                        </c:if>
                                    </td>
                                    <td> ${need.subject} </td>

                                    <td> ${need.requirements} </td>

                                    <td> ${need.timePeriod} </td>

                                    <td> ${need.notes} </td>


                                    <c:if test="${sessionUser.role == 'Recruiter'}">
                                        <td>
                                            <form method="post" action="controller">
                                                <label>
                                                    <input class="is-hidden" name="needId"
                                                           value="${need.needId}">
                                                </label>
                                                <button type="submit" name="action" value="goToNeedEdition"
                                                        class="button  is-info"
                                                        style="border: none">
                                                <span class="material-symbols-outlined">
                                                    settings
                                                </span>
                                                </button>
                                            </form>
                                        </td>
                                    </c:if>

                                    <c:if test="${sessionUser.role == 'Teacher'}">
                                        <td>
                                            <form method="post" action="controller">
                                                <label>
                                                    <input class="is-hidden" name="needId"
                                                           value="${need.needId}">
                                                </label>
                                                <button type="submit" name="action" value="goToCandidature"
                                                        class="button is-success"
                                                        style="border: none"> Candidater
                                                </button>
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="column">
                        <h3 class="title is-3 has-text-left">Recruteur</h3>
                        <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth is-vcentered">
                            <thead>
                            </thead>
                            <tbody>
                            <c:forEach items="${recruiters}" var="recruiter">
                                <tr>
                                    <td>
                                            ${recruiter.firstName}, ${recruiter.lastName}
                                    </td>
                                </tr>
                            </c:forEach>
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