<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="repo.models.VReservationsAvecStatusActuel" %>
<%
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Liste des réservations</title>
    <meta charset="UTF-8">
    <meta name="_csrf" th:content="${_csrf.token}"/>
    <meta name="_csrf_header" th:content="${_csrf.headerName}"/>
    <style>
        .debug { background: #f0f0f0; padding: 10px; margin: 10px 0; border: 1px solid #ccc; }
        .error { color: red; }
        .success { color: green; }
        table { border-collapse: collapse; width: 100%; margin: 10px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .container {margin-left : 230px}
    </style>
</head>
<body>
    <jsp:include page="sideBarBiblio.jsp" />

    <div class="container">
        <h2>Sélectionner une livre</h2>
        <form id="id_livre">
            <div class="livre">
                <select id="livre" name="livre">
                    <option value="">Chargement...</option>
                </select>
            </div>
            <br />
            <input type="submit" value="Valider">
        </form>
        <br /><br />    
        <div id="livreInfo"></div>
        <br /><br />    
        <div id="exemplaireTable"></div>
        <br /><br />
        <div id="resteExemplaireTable"></div>
        <br /><br />
    </div>  
    <script>
    const apiBase = "${pageContext.request.contextPath}";

    const csrfTokenElement = document.querySelector('meta[name="_csrf"]');
    const csrfHeaderElement = document.querySelector('meta[name="_csrf_header"]');
    
    if (!csrfTokenElement || !csrfHeaderElement) {
    } else {
        const csrfToken = csrfTokenElement.getAttribute('content');
        const csrfHeader = csrfHeaderElement.getAttribute('content');
    }

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
            if (xhr.readyState === 4) {
                
                if (xhr.status === 200) {
                    try {
                        const response = JSON.parse(xhr.responseText);
                        console.log("Réponse complète:", response);
                        callback(response);
                    } catch (e) {
                        console.error("Réponse brute:", xhr.responseText);
                    }
                } else {
                    console.error("Réponse d'erreur:", xhr.responseText);
                }
            }
        };
        
        xhr.onerror = () => {
        };
        
        xhr.send(data);
    }

    function formatDate(timestamp) {
        if (!timestamp) return "Non spécifiée";
        const date = new Date(timestamp);
        return date.toLocaleDateString('fr-FR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    }

    function chargerLivre() {
        
        ajax("GET", "/getLivre", null, (data) => {
            console.log("Données livres:", data);
            
            if (!data) {
                return;
            }
            
            if (!data.livres) {
                console.log("Structure de la réponse:", Object.keys(data));
                return;
            }
            
            const livres = data.livres;
            
            const select = document.getElementById("livre");
            select.innerHTML = "";

            const optionDefault = document.createElement("option");
            optionDefault.value = "";
            optionDefault.textContent = "-- Choisir un livre --";
            select.appendChild(optionDefault);

            livres.forEach((item, index) => {
                const option = document.createElement("option");
                option.value = item.id;
                option.textContent = item.titre;
                select.appendChild(option);
            });
            
        });
    }

    document.addEventListener("DOMContentLoaded", () => {
        chargerLivre();

        document.getElementById("id_livre").addEventListener("submit", function(e) {
            e.preventDefault();
            const idLivre = document.getElementById("livre").value;
            
            if (!idLivre) {
                alert("Veuillez choisir un livre.");
                return; 
            }

            document.getElementById("livreInfo").innerHTML = "";
            document.getElementById("exemplaireTable").innerHTML = "";
            document.getElementById("resteExemplaireTable").innerHTML = "";

            ajax("GET", "/livre?livre=" + idLivre, null, (data) => {
                console.log("Données livre complètes:", data);
                
                if (!data || !data.livre) {
                    return;
                }
                
                const livre = data.livre;
                
                const html = `
                    <h3>Livre</h3>
                    <table border="1">
                        <tr><th>Titre</th><td>\${livre.titre}</td></tr>
                        <tr><th>Nb Pages</th><td>\${livre.nbPage}</td></tr>
                        <tr><th>Auteur</th><td>\${livre.auteur}</td></tr>
                        <tr><th>Date Publication</th><td>\${formatDate(livre.datePublication)}</td></tr>
                        <tr><th>Nb Chapitre</th><td>\${livre.nbChapitre}</td></tr>
                        <tr><th>Langue</th><td>\${livre.langue}</td></tr>
                        <tr><th>Editeur</th><td>\${livre.editeur}</td></tr>
                        <tr><th>Genre</th><td>\${livre.genre}</td></tr>
                    </table>
                `;
                document.getElementById("livreInfo").innerHTML = html;
            });

            ajax("GET", "/getExemplaire?livre=" + idLivre, null, (data) => {
                console.log("Données exemplaires:", data);
                
                if (!data || !data.exemplaires) {
                    return;
                }
                
                const exemplaires = data.exemplaires;
                
                let html = `<h3>Exemplaires</h3><table border="1">
                    <tr><th>Numéro Exemplaire</th></tr>`;
                
                exemplaires.forEach(e => {
                    html += `<tr><td>\${e.numeroExemplaire}</td></tr>`;
                });
                html += `</table>`;
                document.getElementById("exemplaireTable").innerHTML = html;
            });

            ajax("GET", "/getExemplaireRestante?livre=" + idLivre, null, (data) => {
                console.log("Données exemplaires restants:", data);
                
                if (!data || !data.reste_exemplaire) {
                    return;
                }
                
                const restes = data.reste_exemplaire;
                
                let html = `<h3>Exemplaires Restants</h3><table border="1">
                    <tr>
                        <th>Numéro Exemplaire</th>
                        <th>Titre</th>
                        <th>Nb Page</th>
                        <th>Auteur</th>
                        <th>Date Publication</th>
                        <th>Nb Chapitre</th>
                        <th>Langue</th>
                        <th>Editeur</th>
                        <th>Genre</th>
                    </tr>`;
                
                restes.forEach(r => {
                    html += `<tr>
                        <td>\${r.numeroExemplaire}</td>
                        <td>\${r.titre}</td>
                        <td>\${r.nbPage}</td>
                        <td>\${r.auteur}</td>
                        <td>\${formatDate(r.datePublication)}</td>
                        <td>\${r.nbChapitre}</td>
                        <td>\${r.langue}</td>
                        <td>\${r.editeur}</td>
                        <td>\${r.genre}</td>
                    </tr>`;
                });
                html += `</table>`;
                document.getElementById("resteExemplaireTable").innerHTML = html;
            });
        });
    });
    </script>
</body>
</html>