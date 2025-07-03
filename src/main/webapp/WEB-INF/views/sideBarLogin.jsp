<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <style>
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
    </style>
</head>
<body>
    <nav class="sidebar">
        <h2>Log in</h2>
        <ul>
            <li class="active"><a href="bibliotheque">Adherant</a></li>
            <li><a href="bibliothecaire">Bibliothecaire</a></li>
            <li><a href="admin">Administrateur</a></li>
        </ul>
    </nav>
</body>
</html>