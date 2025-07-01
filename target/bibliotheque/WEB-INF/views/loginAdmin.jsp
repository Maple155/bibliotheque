<html>
<head>
    <title>Liste des Films</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/liste.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
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

        input[type="text"],
        input[type="password"] {
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
    <form action="checkAdmin" method="post">
        <h2>Log in Administrateur</h2>
        <% 
            String error = request.getParameter("error"); 
            if (error != null) { 
        %>
            <div style="color:red;"><%= error %></div>
        <% 
            } 
        %>
        <br>
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" value="ranto" required>

        <label for="pwd">Prenom :</label>
        <input type="text" name="prenom" id="prenom" value="ranto" required>

        <input type="submit" value="Valider">
    </form>
</body> 
</html>
