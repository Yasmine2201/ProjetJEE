<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="controller">
    <nav class="is-navbar is-fixed-top">
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
                Bonjour ${sessionUser.firstname} ${fn:toUpperCase(sessionUser.lastname)} !
            </div>
            <div class="navbar-item">
                <div class="control">
                    <button class="button  is-medium is-rounded" name="action"
                            type="submit" value="goToResearch">
                            <span class="material-symbols-outlined">
                                search
                            </span>
                    </button>
                </div>
            </div>
        </div>
        <div class="navbar-end">
            <div class="navbar-item">
                <button class="button" type="submit" name="action" value="goToDisponibilities">
                    Mes Dispos
                </button>
            </div>
            <div class="navbar-item">
                <button class="button" type="submit" name="action" value="goToTeacher">
                    Mes infos
                </button>
            </div>
            <div class="navbar-item">
                <input class="is-hidden" name="teacherId" value="${sessionuser.userId}">
                <button class="button is-info" type="submit" name="action" value="goToUserProfile">
                    Mon Profil
                </button>
            </div>
            <div class="navbar-item">
                <button class="button is-danger" type="submit" name="action" value="logout">
                    Déconnexion
                </button>
            </div>
        </div>
    </nav>
</form>
<style>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
