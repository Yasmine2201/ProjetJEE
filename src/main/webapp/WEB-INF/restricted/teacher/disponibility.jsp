<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Disponibility</title>

</head>
<body>
<h2>Veuillez indiquer votre disponibilité</h2>
<c:choose>
    <c:when test="${not empty teacher}">
        <h3>Ajouter une disponibilité ${teacher.name}</h3>
        <form method="post" action="controller">
            <input type="hidden" name="teacherId" value="${teacher.id}" />

            <label for="startDate">Date de début:</label>
            <input type="datetime-local" id="startDate" name="startDate" required><br>

            <label for="endDate">Date et heure de fin:</label>
            <input type="datetime-local" id="endDate" name="endDate" required><br>

            <button type="submit" name="action" value="createDisponibility">
                Enregistrer
            </button>
        </form>
        <h3>Disponibilités existantes:</h3>
        <c:if test="${not empty disponibilities}">
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
                            <form action="DeleteDisponibilityServlet" method="post" style="display: inline;">
                                <input type="hidden" name="disponibilityId" value="${disponibility.id}" />
                                <input type="submit" name="action" value="Annuler">
                            </form>
                            <form action="EditDisponibilityServlet" method="get" style="display: inline;">
                                <input type="hidden" name="disponibilityId" value="${disponibility.id}" />
                                <input type="submit" name="action" value="updateDisponibility">
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
</body>
</html>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
        color: #333;
    }

    h2, h3 {
        color: #4a54f1;
        text-align: center;
    }

    form {
        background: white;
        max-width: 400px;
        margin: 20px auto;
        padding: 20px;
        box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
    }

    label {
        margin-top: 10px;
        display: block;
        color: #666;
    }

    input[type="datetime-local"], input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-sizing: border-box;
    }

    button {
        background-color: #4a54f1;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
    }

    button:hover {
        background-color: #3d46d6;
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
    }

    th, td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: left;
    }

    thead {
        background-color: #4a54f1;
        color: white;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
</style>