<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 23/10/2023
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<form method="post" action="controller">
    <section class="section">
        <nav class="navbar is-fixed-top">
            <div class="navbar-start">
                <div class="navbar-item">
                    <button type="submit" name="action" value="goToHome"
                            class="button is-medium is-primary">
                    <span class="material-symbols-outlined">
                        home
                    </span>
                    </button>
                </div>
                <div class="navbar-item">
                    Bonjour Prenom, Nom.
                </div>
                <div class="navbar-item">
                    <label for="research">
                        <div class="field has-addons">
                            <div class="control">
                                <input id="research" name="reasearch" type="text"
                                       class="input is-medium is-rounded"
                                       placeholder="Recherche"/>
                            </div>
                            <div class="control">
                                <button class="button  is-medium is-rounded" name="action"
                                        type="submit" value="research">
                                <span class="material-symbols-outlined">
                                    search
                                </span>
                                </button>
                            </div>
                        </div>
                    </label>
                </div>
            </div>
            <div class="navbar-end">
                <div class="navbar-item">
                    <button class="button" type="submit" name="action" value="goToNeedForm">
                        + Besoin
                    </button>
                </div>
                <div class="navbar-item">
                    <button class="button is-info" type="submit" name="action" value="goToCandidature">
                        Candidatures
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
</style>
