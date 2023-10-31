<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 20/10/2023
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../navbar/admin.jsp"/>

<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <form class="box has-text-centered px-3" method="post" action="controller">
                <table class="table is-bordered is-striped
                              is-narrow is-hoverable is-fullwidth">
                    <thead>
                    <tr>
                        <th colspan="7">
                            En attend de validation
                        </th>
                    </tr>
                    <tr>
                        <th>
                            Nom
                        </th>
                        <th>
                            Email
                        </th>
                        <th>
                            N° de telephone
                        </th>
                        <th>
                            Role
                        </th>
                        <th>
                            Ecole
                        </th>
                        <th>
                            Validation
                        </th>
                    </tr>
                    </thead>
                </table>
            </form>
        </div>
    </div>
</section>
</body>
</html>

<script>


</script>
