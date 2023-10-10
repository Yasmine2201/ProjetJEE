<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>TeachFinder</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
</head>
<body>
<nav class="level py-3 px-6">
    <div class="level-left has-text-centered">
        <div class="level-item">
            <div class="button is-medium is-danger">Deconnexion</div>
        </div>
        <div class="level-item">
            <div class="button is-medium is-info">Inscription</div>
        </div>
        <div class="level-item has-text-centered">
            <input class="input is-rounded" type="text" placeholder="Rounded input">
        </div>
    </div>
    <div class="level-right has-text-centered">
        <div class="level-item">
            <div class="dropdown is-active is-right is-narrow">
                <div class="dropdown-trigger is-narrow">
                    <button class="button" aria-haspopup="true" aria-controls="dropdown-menu3">
                        <span> N </span>
                    </button>
                </div>
                <div class="dropdown-menu is-narrow" id="dropdown-menu3" role="menu">
                    <div class="dropdown-content">
                        <a href="#" class="dropdown-item">
                            Profile
                        </a>
                        <a href="#" class="dropdown-item">
                            ????
                        </a>
                        <a href="#" class="dropdown-item">
                            Recherche
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>


<section class="section">
    <div class="px-6 py-3">
        <div class="title">Bienvenu,</div>
        <div class="subtitle">Nom, Prenom</div>
    </div>
    <div class="columns is-centered is-full">
        <div class="columns is-10 ">
            <div class="column is-half">
                <form class="box has-text-centered px-3">
                </form>
            </div>
            <div class="column is-half">
                <form class="box has-text-centered px-3">
                </form>
            </div>

        </div>
    </div>
</section>
</body>
</html>