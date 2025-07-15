<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.Livre" %>
<%
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des livres</title>
    <meta charset="UTF-8">
</head>
<body>
    <jsp:include page="sideBarBiblio.jsp" />

    <form method="POST" action="showAll">
        <div class="livre">
            <select id="livre" name="livre">
                <option value="">-- Choisir un livre --</option>
                <%
                    if (livres != null) {
                        for (Livre l : livres) {
                %>
                    <option value="<%= l.getId() %>">
                        <%= l.getId() %> - <%= l.getTitre() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <br /><br />
        <input type="submit" value="Valider">
    </form>
</body>
</html>
