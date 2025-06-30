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
    nbExemplaire INT,
    FOREIGN KEY (id_livre) REFERENCES Livre(id)
);

CREATE TABLE Type_adherant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);
/*
Type adherant 
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
    id SERIAL PRIMARY KEY,
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

CREATE TABLE Type_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);
/*
Type pret 
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
    nb_exemplaire INT,
    type_pret INT NOT NULL,
    date_debut DATE,
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id),
    FOREIGN KEY (type_pret) REFERENCES Type_pret(id)
);

CREATE TABLE Penalite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    -- montant DECIMAL(10,2),
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
    l.id,
    l.titre,
    l.nbPage,
    l.auteur,
    l.datePublication,
    l.nbChapitre,
    l.langue,
    l.editeur,
    l.genre,
    e.id AS id_exemplaire,
    e.nbExemplaire AS nb_exemplaires_totaux,
    IFNULL(SUM(p.nb_exemplaire), 0) AS nb_exemplaires_pretes,
    e.nbExemplaire - IFNULL(SUM(p.nb_exemplaire), 0) AS nb_exemplaires_restants
FROM 
    Livre l
JOIN 
    Exemplaire e ON l.id = e.id_livre
LEFT JOIN 
    Pret p ON e.id = p.id_exemplaire
GROUP BY 
    e.id;

CREATE OR REPLACE VIEW v_prets_avec_date_retour AS
SELECT
    p.id AS id_pret,
    p.id_adherant,
    p.id_exemplaire,
    p.nb_exemplaire,
    p.type_pret,
    p.date_debut,
    c.duree_max,
    DATE_ADD(p.date_debut, INTERVAL c.duree_max DAY) AS date_retour_prevue
FROM
    Pret p
    JOIN Adherant a ON p.id_adherant = a.id
    JOIN Condition_pret c ON c.id_type_adherant = a.id_type_adherant AND c.id_type_pret = p.type_pret;