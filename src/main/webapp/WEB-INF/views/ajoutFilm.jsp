<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="repo.models.*" %>
<%@ page import="java.sql.Date" %>


<% 
    List<Categorie> allCategorie = (List<Categorie>) request.getAttribute("allCategorie"); 
    List<Type> allTypes = (List<Type>) request.getAttribute("allType");

    int idFilm = -1;
    String titre = "";
    int idType = 0;
    String type = "";
    Film film = null;
    Date dateStr = null;
    List <Categorie> categorieFilms = new ArrayList <> ();

    if (request.getAttribute("film") != null) {
        Type tp = (Type) request.getAttribute("type");
        film = (Film) request.getAttribute("film");
        idFilm = film.getId();
        titre = film.getName();
        idType = film.getType().getId();
        type = tp.getName();
        dateStr = film.getDateSortie();
        categorieFilms = film.getCategories();
    }
%>

<html>
<head>
    <title>Ajout d'un film</title>
    <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/ajout.css"> --%>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h2 {
            text-align: center;
            color: #444;
            margin-bottom: 30px;
        }

        form {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 25px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .select-group {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 10px;
        }

        select.categorie-select {
            flex: 1;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 15px;
            background-color: #fff;
        }

        button {
            padding: 8px 14px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            font-size: 14px;
        }

        button[type="button"] {
            background-color: #ff6b6b;
            color: white;
        }

        button[type="button"]:hover {
            background-color: #e05656;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            width: 100%;
            padding: 12px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

    </style>
</head>
<body>
<h2>Ajouter un film</h2>

<form action="enregistrerFilm" method="post">
    <label for="titre">Titre :</label>

    <input type="hidden" name="idFilm" value="<%= idFilm %>">

    <% if (titre != "") { %>
        <input type="text" name="titre" id="titre" value="<%= titre %>" required>
    <% } else { %>
        <input type="text" name="titre" id="titre"  required>
    <% } %>

    <div id="categories-container">
        <% 
        if (film != null && !categorieFilms.isEmpty()) {
            for (Categorie catSelected : categorieFilms) {
        %>
            <div class="select-group">
                <select name="categories" class="categorie-select" onchange="updateDisabledOptions()" required>
                    <option value="" disabled>-- Choisir une catégorie --</option>
                    <% for (Categorie cat : allCategorie) { %>
                        <option value="<%= cat.getId() %>" <%= (cat.getId() == catSelected.getId()) ? "selected" : "" %>>
                            <%= cat.getName() %>
                        </option>
                    <% } %>
                </select>
                <button type="button" onclick="removeCategorieSelect(this)">Supprimer</button>
            </div>
        <% 
            }
        } else { %>
            <div class="select-group">
                <select name="categories" class="categorie-select" onchange="updateDisabledOptions()" required>
                    <option value="" disabled selected>-- Choisir une catégorie --</option>
                    <% for (Categorie cat : allCategorie) { %>
                        <option value="<%= cat.getId() %>"><%= cat.getName() %></option>
                    <% } %>
                </select>
                <button type="button" onclick="removeCategorieSelect(this)">Supprimer</button>
            </div>
        <% } %>
    </div>
    <br>
    <button type="button" onclick="addCategorieSelect()">Ajouter une catégorie</button>
    <br><br>
        <% if (film != null) { %>
        <div>
            <label for="type">Type :</label>
            <select name="type" id="type" required>
                <option value="<%=  idType %>"><%= type %></option>
                <% for (Type t : allTypes) { %>
                    <option value="<%= t.getId() %>" <%= (t.getId() == idType) ? "selected" : "" %>>
                        <%= t.getName() %>
                    </option>
                <% } %>
            </select>
        </div>
        <br><br>
        <div>
            <label for="date_sortie">Date de sortie :</label>
            <input type="date" name="date_sortie" id="date_sortie" value="<%= dateStr %>" required>
        </div>
    <% } else { %>
        <div>
            <label for="type">Type :</label>
            <select name="type" id="type" required>
                <option value="">-- Choisir un type --</option>
                <% for (Type t : allTypes) { %>
                    <option value="<%= t.getId() %>" <%= (t.getId() == idType) ? "selected" : "" %>>
                        <%= t.getName() %>
                    </option>
                <% } %>
            </select>
        </div>
        <br><br>
        <div>
            <label for="date_sortie">Date de sortie :</label>
            <input type="date" name="date_sortie" id="date_sortie" required>
        </div>
    <% } %>
    <br><br>
    <input type="submit" value="Enregistrer le film">
</form>

<%-- <script src="${pageContext.request.contextPath}/static/js/ajout.js"> 
</script> --%>

<script>
    function updateDisabledOptions() {
        let selects = document.querySelectorAll('.categorie-select');
        let selectedValues = Array.from(selects).map(s => s.value);

        selects.forEach(select => {
            let currentValue = select.value;
            Array.from(select.options).forEach(option => {
                option.disabled = selectedValues.includes(option.value) && option.value !== currentValue;
            });
        });
    }

    function addCategorieSelect() {
        let container = document.getElementById("categories-container");
        let firstSelect = container.querySelector(".categorie-select");

        let newSelectDiv = document.createElement("div");
        newSelectDiv.classList.add("select-group");

        newSelectDiv.innerHTML = firstSelect.parentElement.innerHTML;

        let newSelect = newSelectDiv.querySelector("select");
        newSelect.value = "";

        container.appendChild(newSelectDiv);
        updateDisabledOptions();
    }

    function removeCategorieSelect(button) {
        let container = document.getElementById("categories-container");
        let groups = container.querySelectorAll(".select-group");

        if (groups.length > 1) {
            button.parentElement.remove();
            updateDisabledOptions();
        } else {
            alert("Au moins une catégorie est requise.");
        }
    }

    window.onload = function() {
        updateDisabledOptions();
    };
</script>

</body>
</html>
