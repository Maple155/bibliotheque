CREATE TABLE Livre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    nbPage INT,
    auteur VARCHAR(255),
    datePublication DATE,
    nbChapitre INT,
    langue VARCHAR(50),
    editeur VARCHAR(100),
    genre VARCHAR(100)
);

CREATE TABLE Exemplaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    numero_exemplaire INT,
    FOREIGN KEY (id_livre) REFERENCES Livre(id)
);

CREATE TABLE Type_adherant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);
/*
Exemples :
    Etudiant
    Professionnel 
    Professeur
    Anonyme
*/

CREATE TABLE Adherant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id)
);

CREATE TABLE BlacklistLivres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    id_livre INT NOT NULL,
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id),
    FOREIGN KEY (id_livre) REFERENCES Livre(id)
);

CREATE TABLE Inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    date_inscription DATE,
    status ENUM('actif', 'inactif') DEFAULT 'actif',
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id)
);

CREATE TABLE duree_inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT,
    duree INT,
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id)
);

CREATE TABLE Type_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);
/*
Exemples :
    a domicile
    sur place
*/

CREATE TABLE Condition_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    id_type_pret INT NOT NULL,
    exemplaire_max INT,
    duree_max INT, -- durée maximale en jours
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id),
    FOREIGN KEY (id_type_pret) REFERENCES Type_pret(id)
);

CREATE TABLE Pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    id_exemplaire INT NOT NULL,
    type_pret INT NOT NULL,
    date_debut DATE,
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id),
    FOREIGN KEY (type_pret) REFERENCES Type_pret(id)
);


CREATE TABLE Historique_Pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    id_exemplaire INT NOT NULL,
    type_pret INT NOT NULL,
    date_debut DATE,
    date_retour DATE,
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id),
    FOREIGN KEY (type_pret) REFERENCES Type_pret(id)
);

CREATE TABLE Penalite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    date DATE,
    FOREIGN KEY (id_pret) REFERENCES Pret(id)
);

CREATE TABLE Reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_reservation DATE NOT NULL,
    statut ENUM('en attente', 'annulée', 'effectuée') DEFAULT 'en attente',
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id)
);

CREATE TABLE Prolongement_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    date_prolongement DATE NOT NULL,
    nouvelle_date_retour DATE NOT NULL,
    FOREIGN KEY (id_pret) REFERENCES Pret(id)
);

CREATE OR REPLACE VIEW v_exemplaires_restants AS
SELECT 
    l.id AS id_livre,
    l.titre,
    l.nbPage,
    l.auteur,
    l.datePublication,
    l.nbChapitre,
    l.langue,
    l.editeur,
    l.genre,
    e.id AS id_exemplaire,
    e.numero_exemplaire
FROM 
    Livre l
JOIN 
    Exemplaire e ON l.id = e.id_livre
LEFT JOIN 
    Pret p ON e.id = p.id_exemplaire
WHERE 
    p.id IS NULL;

CREATE OR REPLACE VIEW v_prets_avec_date_retour AS
SELECT
    p.id AS id_pret,
    p.date_debut,
    p.id_adherant,
    a.nom AS adherant_nom,
    a.prenom AS adherant_prenom,
    a.id_type_adherant,
    ta.type AS type_adherant,
    
    p.id_exemplaire,
    e.numero_exemplaire,
    e.id_livre,
    l.titre AS livre_titre,
    l.nbPage AS livre_nbPage,
    l.auteur AS livre_auteur,
    l.datePublication AS livre_datePublication,
    l.nbChapitre AS livre_nbChapitre,
    l.langue AS livre_langue,
    l.editeur AS livre_editeur,
    l.genre AS livre_genre,
    
    p.type_pret AS id_type_pret,
    tp.type AS type_pret,
    
    c.exemplaire_max,
    c.duree_max,
    DATE_ADD(p.date_debut, INTERVAL c.duree_max DAY) AS date_retour_prevue

FROM
    Pret p
    JOIN Adherant a ON p.id_adherant = a.id
    JOIN Type_adherant ta ON a.id_type_adherant = ta.id
    JOIN Condition_pret c 
        ON c.id_type_adherant = a.id_type_adherant 
        AND c.id_type_pret = p.type_pret
    JOIN Type_pret tp ON p.type_pret = tp.id
    JOIN Exemplaire e ON p.id_exemplaire = e.id
    JOIN Livre l ON e.id_livre = l.id;


--------------------------------------------
-- CREATE OR REPLACE VIEW v_exemplaires_restants AS
-- SELECT 
--     l.id AS id_livre,
--     l.titre,
--     l.nbPage,
--     l.auteur,
--     l.datePublication,
--     l.nbChapitre,
--     l.langue,
--     l.editeur,
--     l.genre,
--     COUNT(e.id) AS nb_exemplaires_totaux,
--     IFNULL(SUM(CASE WHEN p.id IS NOT NULL THEN 1 ELSE 0 END), 0) AS nb_exemplaires_pretes,
--     COUNT(e.id) - IFNULL(SUM(CASE WHEN p.id IS NOT NULL THEN 1 ELSE 0 END), 0) AS nb_exemplaires_restants
-- FROM 
--     Livre l
-- LEFT JOIN 
--     Exemplaire e ON l.id = e.id_livre
-- LEFT JOIN 
--     Pret p ON e.id = p.id_exemplaire
-- GROUP BY 
--     l.id, l.titre, l.nbPage, l.auteur, l.datePublication, l.nbChapitre, l.langue, l.editeur, l.genre;
