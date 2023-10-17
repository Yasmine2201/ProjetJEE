<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>TeachFinder</title>
</head>
<body>
<div style=" position: relative;
  min-height: 100vh;">
    <div style="padding-bottom: 2.5rem;">
        <section class="section">
            <div class="columns is-centered">
                <div class="column is-10 ">
                    <div class="title is-1 has-text-dark">
                        Connexion
                    </div>
                    <div class="px-6">
                        <form class="box has-text-centered px-3" method="post" action="controller">
                            <div class="field">
                                <div class="label has-text-left">Nom d'utilisateur</div>
                                <div class="control">
                                    <label for="login">
                                        <input id="login" name="login" type="text"
                                               class="input is-medium is-rounded"
                                               placeholder="Nom d'utilisateur"/>
                                    </label>

                                </div>
                            </div>
                            <div class="field pt-3 ">
                                <div class="label has-text-left">Mot de passe</div>
                                <div class="control">
                                    <label for="Password">
                                        <input id="Password" name="password" type="password"
                                               class="input is-medium is-rounded"
                                               placeholder="********"/>
                                    </label>
                                </div>
                            </div>
                            <div class="notification is-danger">
                                <div class="delete"></div>
                                <b>Identifiant</b> ou <b>mot de passe </b> incorect(s)
                            </div>
                            <button type="submit" name="action" value="login" class="button is-success"> Connexion
                            </button>
                            <div class="pt-3">
                                <p>Pas de compte ?</p>
                                <button type="submit" name="action" value="goToRegistration"
                                        class="button is-transparent has-text-info"
                                        style="border: none"> Inscrivez-vous
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <footer class="footer" style="  position: absolute;
  bottom: 0;
  width: 100%;
  height: 2.5rem;   ">
            <div class="content has-text-centered">
                <p>
                    <strong>Bulma</strong> by <a href="https://jgthms.com">Jeremy Thomas</a>. The source code is
                    licensed
                    <a href="http://opensource.org/licenses/mit-license.php">MIT</a>. The website content
                    is licensed <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY NC SA 4.0</a>.
                </p>
            </div>
        </footer>
    </div>
</div>
</body>
</html>