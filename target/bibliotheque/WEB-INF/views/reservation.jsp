<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.*" %>
<%
    List<Exemplaire> exemplaires = (List<Exemplaire>) request.getAttribute("exemplaires");
    String error = (String) request.getAttribute("error"); 
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Réserver un Exemplaire</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f4f6fb;
            margin: 0;
            padding: 0;
        }
        .main-content {
            margin-left: 240px;
            padding: 40px 30px;
            width: calc(100% - 300px);
        }
        h1 {
            color: #233554;
            margin-bottom: 30px;
        }
        .filter-bar {
            margin-bottom: 20px;
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }
        .filter-bar input {
            padding: 8px 12px;
            border: 1px solid #cfd8dc;
            border-radius: 4px;
            background: #fff;
            font-size: 1em;
            min-width: 150px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }
        table {
            background: #fff;
            border-collapse: collapse;
            width: 100%;
            box-shadow: 0 2px 10px rgba(0,0,0,0.03);
            margin-top: 20px;
        }
        th, td {
            padding: 10px 14px;
            text-align: left;
        }
        thead {
            background: #233554;
            color: #fff;
        }
        tbody tr:nth-child(odd) {
            background: #f7faff;
        }
        tbody tr:hover {
            background: #e3e9f6;
        }
        td:empty::before {
            content: "-";
            color: #999;
        }
        form {
            margin: 0;
        }
        .btn-submit {
            background: #233554;
            color: #fff;
            border: none;
            padding: 7px 13px;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-submit:hover {
            background: #304a74;
        }
    </style>
</head>
<body>
    <jsp:include page="sideBarBiblio.jsp" />
    <div class="main-content">
        <h1>Réservation d'un Exemplaire</h1>

        <div class="filter-bar">
            <input type="text" id="filter-titre" placeholder="Filtrer par titre" onkeyup="filterTable()">
            <input type="text" id="filter-auteur" placeholder="Filtrer par auteur" onkeyup="filterTable()">
            <input type="text" id="filter-datePub" placeholder="Filtrer par date publication" onkeyup="filterTable()">
            <input type="text" id="filter-genre" placeholder="Filtrer par genre" onkeyup="filterTable()">
            <input type="text" id="filter-numero" placeholder="Filtrer par numéro exemplaire" onkeyup="filterTable()">
        </div>
        <br>
        <%  
            if (error != null) { 
        %>
            <div style="color:red;"><%= error %></div>
        <% 
            } else if (success != null){
        %>
            <div style="color:green;"><%= success %></div>
        <% 
            }
        %>
        <br>
        <table id="exemplaires-table" border="1">
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Date Publication</th>
                    <th>Genre</th>
                    <th>Numéro exemplaire</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (exemplaires != null && !exemplaires.isEmpty()) {
                        for (Exemplaire exemplaire : exemplaires) {
                            if (exemplaire != null) { 
                                Livre livre = exemplaire.getLivre();
                %>
                    <tr>
                        <td><%= (livre != null && livre.getTitre()!=null) ? livre.getTitre() : "-" %></td>
                        <td><%= (livre != null && livre.getAuteur()!=null) ? livre.getAuteur() : "-" %></td>
                        <td><%= (livre != null && livre.getDatePublication()!=null) ? livre.getDatePublication() : "-" %></td>
                        <td><%= (livre != null && livre.getGenre()!=null) ? livre.getGenre() : "-" %></td>
                        <td><%= exemplaire.getNumeroExemplaire() != null ? exemplaire.getNumeroExemplaire() : "-" %></td>
                        <td>
                            <form action="reserverExemplaire" method="post">
                                <input type="hidden" name="id_exemplaire" value="<%= exemplaire.getId() %>">
                                <label for="date_res">Date de réservation</label>
                                <input type="date" name="date_res" id="date_res" required>
                                <br><br>
                                <input type="submit" value="Réserver" class="btn-submit">
                            </form>
                        </td>
                    </tr>
                <%
                            }
                        }
                    } else {
                %>
                    <tr><td colspan="6">Aucun exemplaire disponible</td></tr>
                <%
                    }
                %>
            </tbody>

        </table>
    </div>
    <script>
        function filterTable() {
            const filters = {
                titre: document.getElementById('filter-titre').value.toLowerCase(),
                auteur: document.getElementById('filter-auteur').value.toLowerCase(),
                datePub: document.getElementById('filter-datePub').value.toLowerCase(),
                genre: document.getElementById('filter-genre').value.toLowerCase(),
                numero: document.getElementById('filter-numero').value.toLowerCase()
            };
            const rows = document.querySelectorAll('#exemplaires-table tbody tr');
            rows.forEach(row => {
                const cells = row.children;
                const show = 
                    (filters.titre === '' || cells[0].textContent.toLowerCase().includes(filters.titre)) &&
                    (filters.auteur === '' || cells[1].textContent.toLowerCase().includes(filters.auteur)) &&
                    (filters.datePub === '' || cells[2].textContent.toLowerCase().includes(filters.datePub)) &&
                    (filters.genre === '' || cells[3].textContent.toLowerCase().includes(filters.genre)) &&
                    (filters.numero === '' || cells[4].textContent.toLowerCase().includes(filters.numero));
                row.style.display = show ? '' : 'none';
            });
        }
    </script>
</body>
</html>