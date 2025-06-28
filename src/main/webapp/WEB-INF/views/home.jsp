<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.*" %>

<html>
<head>
    <title>Liste des Films</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/liste.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
            color: #333;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }

        thead {
            background-color: #3498db;
            color: white;
        }

        th, td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            vertical-align: top;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        ul {
            padding-left: 18px;
            margin: 0;
        }

        a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        p {
            margin-top: 20px;
            text-align: center;
        }

        p a {
            padding: 10px 16px;
            background-color: #2ecc71;
            color: white;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        p a:hover {
            background-color: #27ae60;
        }

    </style>
</head>
<body>
<h2 style="text-align: center;">Liste des Films avec leurs Catégories</h2>

<table>
    <thead>
        <tr>
            <th> ID </th>
            <th> Titre </th>
            <th> Catégories </th>
            <th>Type</th>
            <th>Date de sortie</th>
            <th> Modifier </th>
            <th> Supprimer </th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Film> films = (List<Film>) request.getAttribute("allFilms");
            if (films != null) {
                for (Film film : films) {
        %>
        <tr>
            <td><%= film.getId() %></td>
            <td><%= film.getName() %></td>
            <td>
                <ul>
                    <%
                        List<Categorie> categories = film.getCategories();
                        if (categories != null) {
                            for (Categorie cat : categories) {
                    %>
                        <li><%= cat.getName() %></li>
                    <%
                            }
                        }
                    %>
                </ul>
            </td>
            <td>
                <%
                    List<Type> allType = (List<Type>) request.getAttribute("allTypes");
                    if (allType != null) {
                        for (Type tp : allType) {
                            if (tp.getId() == film.getType().getId()) {
                %>      
                <%= tp.getName()%>
                <% break; } } }%>
                <%-- <%= film.getType().getName() %> --%>
            </td>
            <td><%= film.getDateSortie() %></td>
            <td> <a href="modifFilm?id=<%= film.getId() %>"> Modifier </a> </td>
            <td> <a href="supprimerFilm?id=<%= film.getId() %>"> Supprimer </a> </td>
        </tr>
        <% } } else {
        %>
        <tr><td colspan="3">Aucun film trouvé.</td></tr>
        <%
            }
        %>
    </tbody>
</table>

<p> <a href="ajoutFilm"> Ajouter un film </a> </p>

</body>
</html>
