<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10/10/2023
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>TeachFinder</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
</head>
<body>
<nav class="navbar is-transparent px-3 py-3">
    <div id="BasicNavbar" class="navbar-menu">
        <div class="navbar-start">
            <div class="navbar-item">
                <div class="button is-medium is-primary">Connexion</div>
            </div>
            <div class="navbar-item">
                <div class="button is-medium is-info">Inscription</div>
            </div>
        </div>
    </div>
</nav>
<section class="section">
    <div class="columns is-centered">
        <div class="column is-10 ">
            <div class="title is-1 has-text-dark">
                Connexion
            </div>
            <div class="px-6">
                <form class="box has-text-centered px-3">
                    <div class="field">


                        <div class="label has-text-left">Nom d'utilisateur</div>
                        <div class="control">
                            <!--envoyer vers la servlet -->
                            <label for=Username>
                                <input id=Username class="input is-medium is-rounded" placeholder="Nom d'utilisateur"
                                       type="password"/>
                            </label>
                        </div>
                    </div>
                    <div class="field pt-3 pb-4">
                        <div class="label has-text-left">Mot de passe</div>
                        <div class="control">
                            <!--envoyer vers la servlet -->
                            <label for=Password>
                                <input id=Password class="input is-medium is-rounded" placeholder="********"
                                       type="password"/>
                            </label>
                        </div>
                    </div>
                    <!-- declanche l'envoye -->

                    <button class="button is-success" type="submit" value="Connexion"> Connexion </button>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
</body>
</html>
