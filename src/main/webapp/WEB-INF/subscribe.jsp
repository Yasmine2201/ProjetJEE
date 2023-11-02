<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>

<!DOCTYPE html>
<meta charset="UTF-8">
<head>
    <title>TeachFinder</title>
    <link rel="stylesheet" href="/WEB-INF/css/style.scss">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"/>
</head>
<body>
<section class="section">

    <div class="columns is-centered">
        <div class="column is-10 ">

            <div class="title is-1 has-text-dark">
                Inscription
            </div>

            <div class="px-6">
                <form class="box has-text-centered px-3" method="post" action="controller">
                    <div class="field">

                        <div class="control pb-3">
                            <div class="label has-text-left">Identifiant<sup>*</sup></div>
                            <label for=login>
                                <input id="login" name="login" type="text" class="input is-medium is-rounded"
                                       placeholder="Identifiant" value="${login}"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <div class="label has-text-left">Mot de passe<sup>*</sup></div>
                            <label for="password">
                                <input id="password" name="password" class="input is-medium is-rounded"
                                       placeholder="********"
                                       type="password"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <div class="label has-text-left">Confirmez votre mot de passe<sup>*</sup></div>
                            <label for="passwordVerification">
                                <input id="passwordVerification" name="passwordVerification" class="input is-medium is-rounded"
                                       placeholder="********"
                                       type="password"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <div class="label has-text-left">Prenom<sup>*</sup></div>
                            <label for=firstName>
                                <input id="firstname" name="firstname" type="text" class="input is-medium is-rounded"
                                       placeholder="Prenom" value="${firstname}"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <div class="label has-text-left">Nom<sup>*</sup></div>
                            <label for=lastName>
                                <input id="lastname" name="lastname" type="text" class="input is-medium is-rounded"
                                       placeholder="Nom" value="${lastname}"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <div class="label has-text-left">Email<sup>*</sup></div>
                            <label for=email>
                                <input id="email" name="email" type="email" class="input is-medium is-rounded"
                                       placeholder="Email" value="${email}"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <div class="label has-text-left">Numero de telephone<sup>*</sup></div>
                            <label for=phone>
                                <input id="phone" name="phone" type="tel" class="input is-medium is-rounded"
                                       inputmode="numeric" maxlength="30"
                                       onkeypress="return (event.charCode !==8 && event.charCode ===0 || event.charCode ===43 || event.charCode ===32 || event.charCode ===127 || (event.charCode >= 48 && event.charCode <= 57))"
                                       placeholder="XX-XX-XX-XX-XX" value="${phone}"/>
                            </label>
                        </div>

                        <div class="control pb-3">
                            <label class="radio">
                                <input type="radio" name="role" value="Teacher" checked onclick="setSchoolDropdownVisible(false)">
                                Enseignant
                            </label>
                            <label class="radio">
                                <input type="radio" name="role" value="Recruiter" onclick="setSchoolDropdownVisible(true)">
                                Recruteur
                            </label>
                            <label class="radio">
                                <input type="radio" name="role" value="Admin" onclick="setSchoolDropdownVisible(false)">
                                Admin
                            </label>
                        </div>

                        <div id="schoolDropdown" style="display: none" class="pb-3">
                            <label>
                                <div class="label has-text-left">École<sup>*</sup></div>
                                <select class="select is-fullwidth" name="schoolName">
                                    <c:forEach items="${schools}" var="school">
                                        <option value="${school}">${school}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>

                        <div class="has-text-left">
                            <div class="title is-6">
                                * : obligatoire
                            </div>
                        </div>

                        <div class="has-text-danger error-div">${errorMessage}</div>
                        <div class="has-text-success error-div">${successMessage}</div>

                        <button type="submit" name="action" value="register" class="button is-success">
                            Inscription
                        </button>

                        <div class="pt-3">
                            <p>Déjà un compte ?</p>
                            <button type="submit" name="action" value="goToLogin"
                                    class="button is-transparent has-text-info"
                                    style="border: none"> Connectez-vous
                            </button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>

<script>
    function setSchoolDropdownVisible(isVisible) {
        let dropSchool = document.getElementById("schoolDropdown");
        if (isVisible) {
            dropSchool.style.display = "block";
        } else {
            dropSchool.style.display = "none";
        }
    }
</script>
