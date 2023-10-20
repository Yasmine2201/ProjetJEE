<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 20/10/2023
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<form method="post" action="controller">
    <div class="level">
        <div class="level-left">
            <div class="level-item">
                <button type="submit" name="action" value="goToHome"
                        class="button is-primary">
                    <span class="material-symbols-outlined">
                        home
                    </span>
                </button>
            </div>
            <div class="level-item">
            <button type="submit" name="action" value="back"
                    class="button">
                    Revenir en arriere
            </button>
        </div>
        </div>
        <div class="level-left">
            <div class="level-item">
                <button type="submit" name="action" value="goToHome"
                        class="button is-danger">
                    Deconnexion
                </button>
            </div>
        </div>
    </div>

    <div class="box">
        <div class="notification is-danger">
           <p class="title is-1">Erreur cdoe <b>{Error code}</b>:</p>
            <p>
                {Error Message}
            </p>

        </div>
        </div>
</form>
</body>
</html>
