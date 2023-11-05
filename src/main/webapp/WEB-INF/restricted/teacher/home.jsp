<%--
  Created by IntelliJ IDEA.
  User: ThibautColnot
  Date: 12/10/2023
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<c:set var="candidaturesById" scope="session"
       value="${[{'needId' : 10012, 'schoolName': 'HEC', 'isValidatedByTeacher' : 0, 'isValidatedByRecruiter' : 0, 'status' : 'Pending'},
       {'needId' : 100115212121212, 'schoolName': 'HEC', 'isValidatedByTeacher' : 1, 'isValidatedByRecruiter' : 0, 'status' : 'Accepted'},
       {'needId' : 10012, 'schoolName': 'HEC', 'isValidatedByTeacher' : 0, 'isValidatedByRecruiter' : 1, 'status' : 'Refused'},
       {'needId' : 10012, 'schoolName': 'HEC', 'isValidatedByTeacher' : 1, 'isValidatedByRecruiter' : 1, 'status' : 'Accepted'}]}"/>
<c:set var="needsBySubject" scope="session"
       value="${[{'needId' : 10012, 'schoolName': 'HEC', 'subject' : 'JAVA', 'contractType' : 'temp'},
       {'needId' : 1021222121112012, 'schoolName': 'HEC', 'subject' : 'JAVA', 'contractType' : 'temp'},
       {'needId' : 10012, 'schoolName': 'HEC', 'subject' : 'JAVA', 'contractType' : 'temp'},
       {'needId' : 10012, 'schoolName': 'HEC', 'subject' : 'JAVA', 'contractType' : 'temp'},
       {'needId' : 10012, 'schoolName': 'HEC', 'subject' : 'JAVA', 'contractType' : 'temp'},
       {'needId' : 10012, 'schoolName': 'HEC', 'subject' : 'JAVA', 'contractType' : 'temp'}]}"/>
<body>

<jsp:include page="../navbar/teacher.jsp"/>
<section class="section">

    <div class="columns">
        <div class="column">
            <table class="table is-bordered is-striped
                                  is-narrow is-hoverable is-fullwidth ">
                <thead>
                <tr>
                    <th colspan="6">
                        Offres interessantes
                    </th>
                </tr>
                <tr>
                    <th>
                        Id
                    </th>
                    <th>
                        Ecole
                    </th>
                    <th>
                        Matiere
                    </th>
                    <th>
                        Enseignement
                    </th>
                    <th>
                        Ouvrir
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${needsBySubject}" var="need">
                    <tr id=${need.needId}>
                        <td class="is-narrow">${need.needId}</td>

                        <td>${need.schoolName}</td>

                        <td>${need.subject}</td>

                        <td class="is-narrow">${need.contractType}</td>

                        <td class="is-narrow">
                            <form method="post" action="controller">
                                <input class="is-hidden" name="needId" value="${need.needId}">
                                <button class="button is-info" type="submit" name="action" value="goToNeed">
                                    <span class="material-symbols-outlined">
                                        read_more
                                    </span>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="column">
            <table class="table is-bordered is-striped
                                  is-narrow is-hoverable is-fullwidth">
                <thead>
                <tr>
                    <th colspan="6">
                        Mes candidatures
                    </th>
                </tr>
                <tr>
                    <th>
                        Id
                    </th>
                    <th>
                        Ecole
                    </th>

                    <th>
                        Validations
                    </th>
                    <th>
                        Statut
                    </th>
                    <th>
                        Ouvrir
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${candidaturesById}" var="candidature">
                    <tr id=${candidature.needId}>

                        <td class="is-narrow">${candidature.needId}</td>

                        <td>${candidature.schoolName}</td>
                        <td class="is-narrow">
                            <c:if test="${candidature.isValidatedByTeacher == 0}">
                                <p class="tag is-danger"> Enseignant </p>
                            </c:if>

                            <c:if test="${candidature.isValidatedByTeacher == 1}">
                                <p class="tag is-success"> Enseignant </p>
                            </c:if>

                            <c:if test="${candidature.isValidatedByRecruiter == 0}">
                                <p class="tag is-danger"> Recruteur </p>
                            </c:if>

                            <c:if test="${candidature.isValidatedByRecruiter == 1}">
                                <p class="tag is-success"> Recruteur </p>
                            </c:if>

                        </td>
                        <td class="is-narrow">
                            <c:if test="${candidature.status == 'Pending'}">
                                <p class="tag is-warning"> En cours </p>
                            </c:if>

                            <c:if test="${candidature.status == 'Accepted'}">
                                <p class="tag is-success"> Accepter </p>
                            </c:if>

                            <c:if test="${candidature.status == 'Refused'}">
                                <p class="tag is-danger"> Refuser </p>
                            </c:if>
                        </td>
                        <td class="is-narrow">
                            <form method="post" action="controller">
                                <input class="is-hidden" name="needId" value="${candidature.needId}">
                                <button class="button is-info" type="submit" name="action" value="goToNeed">
                                    <span class="material-symbols-outlined">
                                        read_more
                                    </span>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>
</body>
<jsp:include page="/WEB-INF/footer.jsp"/>
</html>
