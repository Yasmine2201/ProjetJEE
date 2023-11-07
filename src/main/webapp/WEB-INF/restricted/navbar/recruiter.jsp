<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="is-navbar is-fixed-top">
    <form method="post" action="controller">
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
                            type="submit" value="research">
                            <span class="material-symbols-outlined">
                                search
                            </span>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <div class="navbar-end">
        <div class="navbar-item">
            <form method="post" action="controller">
                <label>
                    <input class="is-hidden" name="schoolName"
                           value="${recruiter.schoolName.schoolName}">
                </label>
                <button class="button" type="submit" name="action" value="goToSchool">
                    Mon école
                </button>
            </form>
        </div>
        <div class="navbar-item">
            <form method="post" action="controller">
                <button class="button" type="submit" name="action" value="goToNeedCreation">
                    + Besoin
                </button>
            </form>
        </div>
        <div class="navbar-item">
            <form method="post" action="controller">
                <button class="button is-info" type="submit" name="action" value="goToUserProfile">
                    Mon Profil
                </button>
            </form>
        </div>
        <div class="navbar-item">
            <form method="post" action="controller">
                <button class="button is-danger" type="submit" name="action" value="logout">
                    Déconnexion
                </button>
            </form>
        </div>
    </div>
</nav>
<style>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>
