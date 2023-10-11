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
                <div class="button is-large is-primary">Connexion</div>
            </div>
            <div class="navbar-item">
                <div class="button is-large is-info">Inscription</div>
            </div>
        </div>
    </div>
</nav>
<div>
    <div class="columns is-full is-centered">
        <div class="column is-11 has-text-centered pt-6">
            <div class="title is-1 has-text-left pt-6">Bienvenue,</div>

            <div class=" pb-4">
                <label class="column is-10 is-offset-1">
                    <input class="input is-rounded is-large" type="text" placeholder="Nom d'utilisateur">
                </label>
            </div>
            <div class=" pb-4">
                <label class="column is-10 is-offset-1 pb-3">
                    <input class="input is-rounded is-large" type="password" placeholder="**************">
                </label>
            </div>
            <div class="button is-success is-rounded is-medium"> Connexion</div>
        </div>
    </div>
</div>
</body>
</html>