<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Disponibilités</title>
    <style>
        #content body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        #content h2, h3 {
            color: #4a54f1;
            text-align: center;
        }

        #content form {
            background: white;
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
        }

        #content label {
            margin-top: 10px;
            display: block;
            color: #666;
        }

        #content input[type="datetime-local"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        #content button {
            background-color: #4a54f1;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        #content button:hover {
            background-color: #3d46d6;
        }

        #content table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        #content th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        #content thead {
            background-color: #4a54f1;
            color: white;
        }

        #content tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/restricted/navbar/teacher.jsp"/>
<div id="content">
<h2>Veuillez indiquer votre disponibilité</h2>
<c:choose>
    <c:when test="${not empty teacher}">
        <h3>Ajouter une disponibilité ${teacher.name}</h3>
        <form method="post" action="controller">
            <input type="hidden" name="teacherId" value="${teacher.id}" />
            <input type="hidden" name="teacherId" value="${disponibility.id}" />
            <label for="startDate">Date de début:</label>
            <input type="datetime-local" id="startDate" name="startDate" value="${disponibility.startDate}"><br>
            <label for="endDate">Date et heure de fin:</label>
            <input type="datetime-local" id="endDate" name="endDate" value="${disponibility.endDate}"><br>
            <c:if test="${empty disponibility}">
                <button type="submit" name="action" value="createDisponibility">
                    Enregistrer
                </button>
                <button type="submit" name="action" value="cancelDisponibilityCreation">
                    Annuler
                </button>
            </c:if>
            <c:if test="${not empty disponibility}">
                <button type="submit" name="action" value="editDisponibility">
                    Enregistrer
                </button>
                <button type="submit" name="action" value="cancelDisponibilityEdition">
                    Annuler
                </button>
            </c:if>
        </form>
        <c:if test="${not empty disponibilities}">
        <h3>Disponibilités existantes:</h3>
            <table>
                <thead>
                <tr>
                    <th>Date et heure de début</th>
                    <th>Date et heure de fin</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="disponibility" items="${disponibilities}">
                    <tr>
                        <td><fmt:formatDate value="${disponibility.startDate}" pattern="dd/MM/yyyy" /></td>
                        <td><fmt:formatDate value="${disponibility.endDate}" pattern="dd/MM/yyyy" /></td>
                        <td>
                            <form action="controller" method="post" style="display: inline;">
                                <input type="hidden" name="disponibilityId" value="${disponibility.id}" />
                                <button type="submit" name="action" value="deleteDisponibility">Supprimer</button>
                            </form>
                            <form action="controller" method="post" style="display: inline;">
                                <input type="hidden" name="disponibilityId" value="${disponibility.id}" />
                                <button type="submit" name="action" value="updateDisponibility">Modifier</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:when>
    <c:otherwise>
        <p>Enseignant non identifié. Veuillez vous connecter.</p>
    </c:otherwise>
</c:choose>
</div>
<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>

<style>
    <%@include file="/WEB-INF/css/bulma/css/bulma.min.css" %>
    <%@include file="/WEB-INF/css/style.scss" %>
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0");
</style>