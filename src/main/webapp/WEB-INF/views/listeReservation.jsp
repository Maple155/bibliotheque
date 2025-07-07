<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.VReservationsAvecStatusActuel" %>
<%
    List<VReservationsAvecStatusActuel> reservations = (List<VReservationsAvecStatusActuel>) request.getAttribute("reservations");
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des réservations</title>
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
        <h1>Liste des réservations</h1>

        <div class="filter-bar">
            <input type="text" id="filter-nomAdherant" placeholder="Filtrer par nom adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-prenomAdherant" placeholder="Filtrer par prénom adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-idExemplaire" placeholder="Filtrer par ID exemplaire" onkeyup="filterTable()">
            <input type="text" id="filter-numeroExemplaire" placeholder="Filtrer par numéro exemplaire" onkeyup="filterTable()">
            <input type="text" id="filter-dateReservation" placeholder="Filtrer par date réservation" onkeyup="filterTable()">
            <input type="text" id="filter-statutActuel" placeholder="Filtrer par statut" onkeyup="filterTable()">
        </div>

        <% if (error != null) { %>
            <div style="color:red;"><%= error %></div>
        <% } else if (success != null) { %>
            <div style="color:green;"><%= success %></div>
        <% } %>
        <br>
        <table id="reservations-table" border="1">
            <thead>
                <tr>
                    <th>ID Réservation</th>
                    <th>Nom Adhérant</th>
                    <th>Prénom Adhérant</th>
                    <th>ID Exemplaire</th>
                    <th>Numéro Exemplaire</th>
                    <th>Date Réservation</th>
                    <th>Statut Actuel</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                if (reservations != null && !reservations.isEmpty()) {
                    for (VReservationsAvecStatusActuel res : reservations) {
                %>
                <tr>
                    <td><%= res.getIdReservation() != null ? res.getIdReservation() : "-" %></td>
                    <td><%= res.getNomAdherant() != null ? res.getNomAdherant() : "-" %></td>
                    <td><%= res.getPrenomAdherant() != null ? res.getPrenomAdherant() : "-" %></td>
                    <td><%= res.getIdExemplaire() != null ? res.getIdExemplaire() : "-" %></td>
                    <td><%= res.getNumeroExemplaire() != null ? res.getNumeroExemplaire() : "-" %></td>
                    <td><%= res.getDateReservation() != null ? res.getDateReservation() : "-" %></td>
                    <td><%= res.getStatutActuel() != null ? res.getStatutActuel() : "-" %></td>
                    <% if ("en attente".equals(res.getStatutActuel())) { %>
                        <td>
                                <form method="post" action="validerRes">
                                    <input type="hidden" name="id_reservation" value="<%= res.getIdReservation() %>">
                                    <input type="hidden" name="action" value="valider">
                                    <input type="submit" value="Accepter">
                                </form>
                        </td>
                        <td>
                                <form method="post" action="validerRes">
                                    <input type="hidden" name="id_reservation" value="<%= res.getIdReservation() %>">
                                    <input type="hidden" name="action" value="refuser">
                                    <input type="submit" value="Refuser">
                                </form>
                        </td>
                    <% } %>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="9">Aucune réservation enregistrée</td></tr>
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
                idExemplaire: document.getElementById('filter-idExemplaire').value.toLowerCase(),
                numeroExemplaire: document.getElementById('filter-numeroExemplaire').value.toLowerCase(),
                dateReservation: document.getElementById('filter-dateReservation').value.toLowerCase(),
                statutActuel: document.getElementById('filter-statutActuel').value.toLowerCase()
            };

            const rows = document.querySelectorAll('#reservations-table tbody tr');
            rows.forEach(row => {
                const cells = row.children;
                const show =
                    (filters.nomAdherant === '' || cells[1].textContent.toLowerCase().includes(filters.nomAdherant)) &&
                    (filters.prenomAdherant === '' || cells[2].textContent.toLowerCase().includes(filters.prenomAdherant)) &&
                    (filters.idExemplaire === '' || cells[3].textContent.toLowerCase().includes(filters.idExemplaire)) &&
                    (filters.numeroExemplaire === '' || cells[4].textContent.toLowerCase().includes(filters.numeroExemplaire)) &&
                    (filters.dateReservation === '' || cells[5].textContent.toLowerCase().includes(filters.dateReservation)) &&
                    (filters.statutActuel === '' || cells[6].textContent.toLowerCase().includes(filters.statutActuel));
                row.style.display = show ? '' : 'none';
            });
        }
    </script>
</body>
</html>