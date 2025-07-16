<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Adhérant - Détails</title>
    <meta charset="UTF-8">
    <meta name="_csrf" th:content="${_csrf.token}" />
    <meta name="_csrf_header" th:content="${_csrf.headerName}" />

    <style>
        body { font-family: Arial, sans-serif; }
        .container { margin-left: 230px; padding: 20px; }
        h3 { margin-top: 30px; }
        table { border-collapse: collapse; width: 100%; margin: 10px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        select { padding: 5px; }
        input[type="submit"] { padding: 5px 10px; }
    </style>
</head>
<body>

    <jsp:include page="sideBarBiblio.jsp" />

    <div class="container">
        <h2>Sélectionner un adhérant</h2>
        <form id="adherantForm">
            <select id="adherant" name="adherant">
                <option value="">-- Choisir un adhérant --</option>
            </select>
            <input type="submit" value="Valider">
        </form>

        <div id="adherantAbonnement"></div>
        <div id="adherantPrets"></div>
        <div id="adherantPenalite"></div>
        <div id="adherantCota"></div>
    </div>

    <script>
    const apiBase = "${pageContext.request.contextPath}";

    const csrfTokenElement = document.querySelector('meta[name="_csrf"]');
    const csrfHeaderElement = document.querySelector('meta[name="_csrf_header"]');

    const csrfToken = csrfTokenElement ? csrfTokenElement.getAttribute('content') : null;
    const csrfHeader = csrfHeaderElement ? csrfHeaderElement.getAttribute('content') : null;

    function ajax(method, url, data, callback) {
        const xhr = new XMLHttpRequest();
        xhr.open(method, apiBase + url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        if (csrfToken && csrfHeader) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                try {
                    callback(JSON.parse(xhr.responseText));
                } catch (e) {
                    console.error("Erreur de parsing:", xhr.responseText);
                }
            }
        };
        xhr.send(data);
    }

    function formatDate(dateStr) {
        if (!dateStr) return "Non spécifiée";
        const date = new Date(dateStr);
        return date.toLocaleDateString('fr-FR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    }

    function chargerAdherants() {
        ajax("GET", "/getAdherant", null, (data) => {
            const select = document.getElementById("adherant");
            select.innerHTML = '<option value="">-- Choisir un adhérant --</option>';
            if (data.adherant) {
                data.adherant.forEach(a => {
                    const opt = document.createElement("option");
                    opt.value = a.id;
                    opt.textContent = `\${a.nom} \${a.prenom}`;
                    select.appendChild(opt);
                });
            }
        });
    }

    document.addEventListener("DOMContentLoaded", () => {
        chargerAdherants();

        document.getElementById("adherantForm").addEventListener("submit", function(e) {
            e.preventDefault();
            const idAdherant = document.getElementById("adherant").value;
            if (!idAdherant) { alert("Veuillez choisir un adhérant."); return; }

            document.getElementById("adherantAbonnement").innerHTML = "";
            document.getElementById("adherantPrets").innerHTML = "";
            document.getElementById("adherantPenalite").innerHTML = "";
            document.getElementById("adherantCota").innerHTML = "";

            // Abonnement
            ajax("GET", "/getAbonnement?adherant=" + idAdherant, null, (data) => {
                if (!data.inscriptions) return;
                let html = "<h3>Abonnements</h3><table><tr><th>Date début</th><th>Date fin</th></tr>";
                data.inscriptions.forEach(i => {
                html += `<tr>
                    <td>\${formatDate(i.dateDebut)}</td>
                    <td>\${formatDate(i.datefin)}</td>
                </tr>`;
                });
                html += "</table>";
                document.getElementById("adherantAbonnement").innerHTML = html;
            });

            // Prêts
            ajax("GET", "/getPretAdherant?adherant=" + idAdherant, null, (data) => {
                if (!data.prets) return;
                let html = "<h3>Prêts en cours</h3><table><tr><th>Livre</th><th>Numéro Exemplaire</th><th>Type Adherant</th><th>Type Pret</th><th>Date Prêt</th><th>Date Retour Prévue</th></tr>";
                data.prets.forEach(p => {
                    html += `<tr>
                        <td>\${p.livreTitre}</td>
                        <td>\${p.numeroExemplaire}</td>
                        <td>\${p.typeAdherant}</td>
                        <td>\${p.typePret}</td>
                        <td>\${formatDate(p.dateDebut)}</td>
                        <td>\${formatDate(p.dateRetourPrevue)}</td>
                    </tr>`;
                });
                html += "</table>";
                document.getElementById("adherantPrets").innerHTML = html;
            });

            // Pénalités
            ajax("GET", "/getPenalite?adherant=" + idAdherant, null, (data) => {
                if (!data.penalite) return;
                let html = "<h3>Pénalités</h3><table><tr><th>ID Prêt</th><th>Nom</th><th>Prénom</th><th>Date Début</th><th>Date Pénalité</th><th>Nb Jours</th></tr>";
                data.penalite.forEach(p => {
                    html += `<tr>
                        <td>\${p.idPret}</td>
                        <td>\${p.nom}</td>
                        <td>\${p.prenom}</td>
                        <td>\${formatDate(p.dateDebut)}</td>
                        <td>\${formatDate(p.datePenalite)}</td>
                        <td>\${p.nbJour}</td>
                    </tr>`;
                });
                html += "</table>";
                document.getElementById("adherantPenalite").innerHTML = html;
            });

            // Cota
            ajax("GET", "/getCota?adherant=" + idAdherant, null, (data) => {
                if (!data.cota) return;
                let html = "<h3>Conditions de prêt</h3><table><tr><th>Type adhérant</th><th>Type prêt</th><th>Exemplaire max</th><th>Durée max</th><th>Prolongement max</th><th>Reservation max</th><th>Penalite max</th></tr>";
                data.cota.forEach(p => {
                    html += `<tr>
                        <td>\${p.typeAdherant.type}</td>
                        <td>\${p.typePret.type}</td>
                        <td>\${p.exemplaireMax}</td>
                        <td>\${p.dureeMax}</td>
                        <td>\${p.prolongementMax}</td>
                        <td>\${p.reservationMax}</td>
                        <td>\${p.penalite}</td>
                    </tr>`;
                });
                html += "</table>";
                document.getElementById("adherantCota").innerHTML = html;
            });

        });
    });
    </script>
</body>
</html>
