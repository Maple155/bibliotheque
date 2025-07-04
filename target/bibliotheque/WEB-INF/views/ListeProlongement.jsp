<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.VProlongementsAvecStatusActuel" %>
<%
    List<VProlongementsAvecStatusActuel> prolongements = (List<VProlongementsAvecStatusActuel>) request.getAttribute("prolongements");
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des prolongements</title>
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
    </style>
</head>
<body>
    <jsp:include page="sideBarBiblio.jsp" />

    <div class="main-content">
        <h1>Liste des prolongements</h1>

        <div class="filter-bar">
            <input type="text" id="filter-nomAdherant" placeholder="Filtrer par nom adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-prenomAdherant" placeholder="Filtrer par prénom adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-titreLivre" placeholder="Filtrer par titre livre" onkeyup="filterTable()">
            <input type="text" id="filter-numeroExemplaire" placeholder="Filtrer par numéro exemplaire" onkeyup="filterTable()">
            <input type="text" id="filter-dateProlongement" placeholder="Filtrer par date prolongement" onkeyup="filterTable()">
            <input type="text" id="filter-statutActuel" placeholder="Filtrer par statut" onkeyup="filterTable()">
        </div>

        <% if (error != null) { %>
            <div style="color:red;"><%= error %></div>
        <% } else if (success != null) { %>
            <div style="color:green;"><%= success %></div>
        <% } %>
        <br>
        <table id="prolongements-table" border="1">
            <thead>
                <tr>
                    <th>ID Prolongement</th>
                    <th>Nom Adhérant</th>
                    <th>Prénom Adhérant</th>
                    <th>Titre Livre</th>
                    <th>Numéro Exemplaire</th>
                    <th>Date Prolongement</th>
                    <th>Statut Actuel</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                if (prolongements != null && !prolongements.isEmpty()) {
                    for (VProlongementsAvecStatusActuel p : prolongements) {
                %>
                <tr>
                    <td><%= p.getIdProlongement() != null ? p.getIdProlongement() : "-" %></td>
                    <td><%= p.getNomAdherant() != null ? p.getNomAdherant() : "-" %></td>
                    <td><%= p.getPrenomAdherant() != null ? p.getPrenomAdherant() : "-" %></td>
                    <td><%= p.getTitreLivre() != null ? p.getTitreLivre() : "-" %></td>
                    <td><%= p.getNumeroExemplaire() != null ? p.getNumeroExemplaire() : "-" %></td>
                    <td><%= p.getDateProlongement() != null ? p.getDateProlongement() : "-" %></td>
                    <td><%= p.getStatutActuel() != null ? p.getStatutActuel() : "-" %></td>
                    <td>
                        <% if ("en attene".equals(p.getStatutActuel())) {  %>
                                <form method="post" action="validerPro">
                                <input type="hidden" name="id_prolongement" value="<%= p.getIdProlongement() %>">
                                <input type="hidden" name="action" value="Accepter">
                                <input type="submit" value="Accepter">
                            </form>
                        <% } %>
                    </td>
                    <td>
                        <% if ("en attente".equals(p.getStatutActuel())) {  %>
                            <form method="post" action="validerPro">
                                <input type="hidden" name="id_prolongement" value="<%= p.getIdProlongement() %>">
                                <input type="hidden" name="action" value="refuser">
                                <input type="submit" value="Refuser">
                            </form>
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="7">Aucun prolongement enregistré</td></tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>
    <script>
        function filterTable() {
            const filters = {
                nomAdherant: document.getElementById('filter-nomAdherant').value.toLowerCase(),
                prenomAdherant: document.getElementById('filter-prenomAdherant').value.toLowerCase(),
                titreLivre: document.getElementById('filter-titreLivre').value.toLowerCase(),
                numeroExemplaire: document.getElementById('filter-numeroExemplaire').value.toLowerCase(),
                dateProlongement: document.getElementById('filter-dateProlongement').value.toLowerCase(),
                statutActuel: document.getElementById('filter-statutActuel').value.toLowerCase()
            };

            const rows = document.querySelectorAll('#prolongements-table tbody tr');
            rows.forEach(row => {
                const cells = row.children;
                const show =
                    (filters.nomAdherant === '' || cells[1].textContent.toLowerCase().includes(filters.nomAdherant)) &&
                    (filters.prenomAdherant === '' || cells[2].textContent.toLowerCase().includes(filters.prenomAdherant)) &&
                    (filters.titreLivre === '' || cells[3].textContent.toLowerCase().includes(filters.titreLivre)) &&
                    (filters.numeroExemplaire === '' || cells[4].textContent.toLowerCase().includes(filters.numeroExemplaire)) &&
                    (filters.dateProlongement === '' || cells[5].textContent.toLowerCase().includes(filters.dateProlongement)) &&
                    (filters.statutActuel === '' || cells[6].textContent.toLowerCase().includes(filters.statutActuel));
                row.style.display = show ? '' : 'none';
            });
        }
    </script>
</body>
</html>