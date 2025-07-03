<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Log in Bibliothecaire</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            min-width: 100vw;
            display: block;
        }
        .sidebar {
            width: 220px;
            background: #233554;
            color: #fff;
            height: 100vh;
            position: fixed;
            left: 0;
            top: 0;
            padding-top: 30px;
            box-shadow: 2px 0 8px rgba(0,0,0,0.08);
            z-index: 100;
        }
        .sidebar h2 {
            margin: 0 0 30px 20px;
            font-size: 1.3em;
            letter-spacing: 1px;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0 20px;
            margin: 0;
        }
        .sidebar ul li {
            margin-bottom: 20px;
        }
        .sidebar ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 1.08em;
            transition: color 0.2s;
            display: block;
            padding: 8px 12px;
            border-radius: 4px;
        }
        .sidebar ul li a:hover, .sidebar ul li.active a {
            background-color: #2d466b;
            color: #d7e3ff;
        }
        .form-container {
            margin-left: 240px;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        form {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <jsp:include page="sideBarLogin.jsp" />
    <div class="form-container">
        <form action="checkbiblio" method="post">
            <h2>Log in Bibliothecaire</h2>
            <% 
                String error = request.getAttribute("error"); 
                if (error != null) { 
            %>
                <div style="color:red;"><%= error %></div>
            <% 
                } 
            %>
            <br>
            <label for="nom">Nom :</label>
            <input type="text" name="nom" id="nom" value="ranto" required>
            <label for="prenom">Prenom :</label>
            <input type="text" name="prenom" id="prenom" value="ranto" required>
            <input type="submit" value="Valider">
        </form>
    </div>
</body> 
</html>