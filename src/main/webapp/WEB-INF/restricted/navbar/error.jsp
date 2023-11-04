<form method="post" action="controller">
    <section class="section">
        <nav class="navbar is-fixed-top">

            <div class="navbar-start">
                <div class="navbar-item">
                    <button type="submit" name="action" value="back"
                            class="button is-medium">
                       <span class="material-symbols-outlined">
                            arrow_back
                       </span>
                    </button>
                </div>
                <div class="navbar-item">
                    <button type="submit" name="action" value="goToHome"
                            class="button is-medium is-primary">
                    <span class="material-symbols-outlined">
                        home
                    </span>
                    </button>
                </div>
            </div>

            <div class="navbar-end">
                <div class="navbar-item">
                    <button class="button is-info" type="submit" name="action" value="goToMyProfile">
                        Mon Profil
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
