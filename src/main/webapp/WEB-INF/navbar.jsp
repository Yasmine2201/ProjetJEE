<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 20/10/2023
  Time: 09:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="controller">
    <section class="section">
        <nav class="navbar is-fixed-top">
            <div class="navbar-start">
                <div class="navbar-item">
                    Bonjour {Prenom}, {Nom}.
                </div>
                <div class="navbar-item">
                    <button type="submit" name="action" value="goToHome"
                            class="button is-primary">
                    <span class="material-symbols-outlined">
                        home
                    </span>
                    </button>
                </div>
                <div class="navbar-item">
                    <div class="control has-icons-right">
                        <label for="research">
                            <input id="research" name="reasearch" type="text"
                                   class="input is-medium is-rounded"
                                   placeholder="Recherche"/>
                            <button class="button is-small is-left" name="action"
                                    type="submit" value="research">
                                <span class="material-symbols-outlined">
                                    search
                                </span>
                            </button>
                        </label>
                    </div>
                </div>
            </div>
            <div class="navbar-end">
                <div class="navbar-item">
                    <button class="button" type="submit" name="action" value="goToDisponibilities">
                        Mes dispos
                    </button>
                </div>
                <div class="navbar-item">
                    <button class="button is-danger" type="submit" name="action" value="logout">
                        Deconnexion
                    </button>
                </div>
            </div>
        </nav>

    </section>
</form>
<style>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
