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
<body>

<jsp:include page="../navbar/teacher.jsp"/>

<form method="post" action="controller">
    <section class="section">
        <div class="level">

            <div class="level-item pr-3">
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
                            identifiant du bensoin
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
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="level-item pl-3">
                <table class="table is-bordered is-striped
                                  is-narrow is-hoverable is-fullwidth">
                    <thead>
                    <tr>
                        <th colspan="6">
                            Offre postule
                        </th>
                    </tr>
                    <tr>
                        <th>
                            identifiant du bensoin
                        </th>
                        <th>
                            Ecole
                        </th>
                        <th>
                            Matiere
                        </th>
                        <th>
                            Accepter?
                        </th>
                        <th>
                            Status
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </section>
</form>
</body>
</html>
