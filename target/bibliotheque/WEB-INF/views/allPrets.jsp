<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.V_pretsAvecDateRetour" %>
<%
    List<V_pretsAvecDateRetour> allPrets = (List<V_pretsAvecDateRetour>) request.getAttribute("allPrets");
    String error = (String) request.getAttribute("error"); 
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste de tous les prêts</title>
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
        <h1>Liste de tous les prêts</h1>

        <div class="filter-bar">
            <input type="text" id="filter-dateDebut" placeholder="Filtrer par date début" onkeyup="filterTable()">
            <input type="text" id="filter-adherantNom" placeholder="Filtrer par nom adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-adherantPrenom" placeholder="Filtrer par prénom adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-typeAdherant" placeholder="Filtrer par type adhérant" onkeyup="filterTable()">
            <input type="text" id="filter-numeroExemplaire" placeholder="Filtrer par numéro exemplaire" onkeyup="filterTable()">
            <input type="text" id="filter-livreTitre" placeholder="Filtrer par titre livre" onkeyup="filterTable()">
            <input type="text" id="filter-typePret" placeholder="Filtrer par type prêt" onkeyup="filterTable()">
            <input type="text" id="filter-exemplaireMax" placeholder="Filtrer par exemplaire max" onkeyup="filterTable()">
            <input type="text" id="filter-dureeMax" placeholder="Filtrer par durée max" onkeyup="filterTable()">
            <input type="text" id="filter-dateRetourPrevue" placeholder="Filtrer par date retour prévue" onkeyup="filterTable()">
            <input type="text" id="filter-statusCourant" placeholder="Filtrer par statut courant" onkeyup="filterTable()">
        </div>

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
        <br><br>
        <table id="prets-table" border="1">
            <thead>
                <tr>
                    <th>Date Début</th>
                    <th>Nom Adhérant</th>
                    <th>Prénom Adhérant</th>
                    <th>Type Adhérant</th>
                    <th>Numéro Exemplaire</th>
                    <th>Titre Livre</th>
                    <th>Type Prêt</th>
                    <th>Exemplaire Max</th>
                    <th>Durée Max (jours)</th>
                    <th>Date Retour Prévue</th>
                    <th>Statut</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (allPrets != null && !allPrets.isEmpty()) {
                        for (V_pretsAvecDateRetour pret : allPrets) {
                %>
                <tr>
                    <td><%= pret.getDateDebut() != null ? pret.getDateDebut() : "-" %></td>
                    <td><%= pret.getAdherantNom() != null ? pret.getAdherantNom() : "-" %></td>
                    <td><%= pret.getAdherantPrenom() != null ? pret.getAdherantPrenom() : "-" %></td>
                    <td><%= pret.getTypeAdherant() != null ? pret.getTypeAdherant() : "-" %></td>
                    <td><%= pret.getNumeroExemplaire() != null ? pret.getNumeroExemplaire() : "-" %></td>
                    <td><%= pret.getLivreTitre() != null ? pret.getLivreTitre() : "-" %></td>
                    <td><%= pret.getTypePret() != null ? pret.getTypePret() : "-" %></td>
                    <td><%= pret.getExemplaireMax() != null ? pret.getExemplaireMax() : "-" %></td>
                    <td><%= pret.getDureeMax() != null ? pret.getDureeMax() : "-" %></td>
                    <td><%= pret.getDateRetourPrevue() != null ? pret.getDateRetourPrevue() : "-" %></td>
                    <td><%= pret.getStatusCourant() != null ? pret.getStatusCourant() : "-" %></td>
                    <td>
                        <% if ("en cours".equals(pret.getStatusCourant())) { %>
                            <form action="rendreExemplaire" method="post">
                                <input type="hidden" name="id_pret" value="<%= pret.getIdPret() %>">
                                <input type="hidden" name="id_adherant" value="<%= pret.getIdAdherant()%>">
                                <br>
                                <label for="date_retour">Date retour :</label>
                                <input type="date" name="date_retour" required>
                                <br><br>
                                <input type="submit" value="Rendre" class="btn-submit">
                            </form>
                        <% } %>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="11">Aucun prêt enregistré</td></tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <script>
        function filterTable() {
            const filters = {
                dateDebut: document.getElementById('filter-dateDebut').value.toLowerCase(),
                adherantNom: document.getElementById('filter-adherantNom').value.toLowerCase(),
                adherantPrenom: document.getElementById('filter-adherantPrenom').value.toLowerCase(),
                typeAdherant: document.getElementById('filter-typeAdherant').value.toLowerCase(),
                numeroExemplaire: document.getElementById('filter-numeroExemplaire').value.toLowerCase(),
                livreTitre: document.getElementById('filter-livreTitre').value.toLowerCase(),
                typePret: document.getElementById('filter-typePret').value.toLowerCase(),
                exemplaireMax: document.getElementById('filter-exemplaireMax').value.toLowerCase(),
                dureeMax: document.getElementById('filter-dureeMax').value.toLowerCase(),
                dateRetourPrevue: document.getElementById('filter-dateRetourPrevue').value.toLowerCase(),
                statusCourant: document.getElementById('filter-statusCourant').value.toLowerCase()
            };

            const rows = document.querySelectorAll('#prets-table tbody tr');
            rows.forEach(row => {
                const cells = row.children;
                const show =
                    (filters.dateDebut === '' || cells[0].textContent.toLowerCase().includes(filters.dateDebut)) &&
                    (filters.adherantNom === '' || cells[1].textContent.toLowerCase().includes(filters.adherantNom)) &&
                    (filters.adherantPrenom === '' || cells[2].textContent.toLowerCase().includes(filters.adherantPrenom)) &&
                    (filters.typeAdherant === '' || cells[3].textContent.toLowerCase().includes(filters.typeAdherant)) &&
                    (filters.numeroExemplaire === '' || cells[4].textContent.toLowerCase().includes(filters.numeroExemplaire)) &&
                    (filters.livreTitre === '' || cells[5].textContent.toLowerCase().includes(filters.livreTitre)) &&
                    (filters.typePret === '' || cells[6].textContent.toLowerCase().includes(filters.typePret)) &&
                    (filters.exemplaireMax === '' || cells[7].textContent.toLowerCase().includes(filters.exemplaireMax)) &&
                    (filters.dureeMax === '' || cells[8].textContent.toLowerCase().includes(filters.dureeMax)) &&
                    (filters.dateRetourPrevue === '' || cells[9].textContent.toLowerCase().includes(filters.dateRetourPrevue)) &&
                    (filters.statusCourant === '' || cells[10].textContent.toLowerCase().includes(filters.statusCourant));

                row.style.display = show ? '' : 'none';
            });
        }
    </script>
</body>
</html>