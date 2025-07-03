DROP DATABASE bibliotheque;

CREATE DATABASE bibliotheque;

USE bibliotheque;

CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100) 
);

CREATE TABLE bibliothecaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100)
);

CREATE TABLE Rarete (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100)
);

-- Livres
CREATE TABLE Livre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    nbPage INT,
    auteur VARCHAR(255),
    datePublication DATE,
    nbChapitre INT,
    langue VARCHAR(50),
    editeur VARCHAR(100),
    genre VARCHAR(100),
    id_rarete INT,
    FOREIGN KEY (id_rarete) REFERENCES Rarete(id)
);

-- Exemplaires
CREATE TABLE Exemplaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    numero_exemplaire INT,
    FOREIGN KEY (id_livre) REFERENCES Livre(id)
);

-- Type d'adhérant
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

-- Adhérants
CREATE TABLE Adherant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    naissance DATE,
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id)
);

CREATE TABLE BlacklistAge (
    id INT AUTO_INCREMENT PRIMARY KEY,
    age_min INT NOT NULL,
    id_livre INT NOT NULL,
    FOREIGN KEY (id_livre) REFERENCES Livre(id)
);

-- Blacklist pour certains types d'adhérants
CREATE TABLE BlacklistLivres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    id_livre INT NOT NULL,
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id),
    FOREIGN KEY (id_livre) REFERENCES Livre(id)
);

-- Inscription
CREATE TABLE Inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    date_debut DATE,
    date_fin Date,
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id)
);

-- Type de statut pour inscription
CREATE TABLE type_status_inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(55)
);

-- Statut d'inscription
CREATE TABLE status_inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_status INT, 
    id_inscription INT,
    FOREIGN KEY (id_status) REFERENCES type_status_inscription(id),
    FOREIGN KEY (id_inscription) REFERENCES Inscription(id)
);

-- Durée de l'inscription par type d'adhérant
CREATE TABLE duree_inscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT,
    duree INT,
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id)
);

-- Type de prêt
CREATE TABLE Type_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);
/*
Exemples :
    a domicile
    sur place
*/

-- Condition de prêt par type d'adhérant et type de prêt
CREATE TABLE Condition_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    id_type_pret INT NOT NULL,
    exemplaire_max INT,
    duree_max INT, -- durée maximale en jours
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id),
    FOREIGN KEY (id_type_pret) REFERENCES Type_pret(id)
);

-- Prêt
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

-- Type de statut de prêt
CREATE TABLE type_status_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50)
);
/*
    en attente
    en cours
    terminé
    refusé
*/

-- Statut du prêt
CREATE TABLE status_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT,
    id_status INT,
    FOREIGN KEY (id_pret) REFERENCES Pret(id),
    FOREIGN KEY (id_status) REFERENCES type_status_pret(id)
);

-- Retour du prêt
CREATE TABLE retour_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT,
    date_retour DATE,
    FOREIGN KEY (id_pret) REFERENCES Pret(id)
);

-- Pénalité
CREATE TABLE Penalite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    date DATE,
    nbJour INT,
    FOREIGN KEY (id_pret) REFERENCES Pret(id)
);

-- Réservation
CREATE TABLE Reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherant INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_reservation DATE NOT NULL,
    FOREIGN KEY (id_adherant) REFERENCES Adherant(id),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id)
);

-- Statut de réservation
CREATE TABLE status_reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_reservation INT,
    id_status INT,
    FOREIGN KEY (id_reservation) REFERENCES Reservation(id),
    FOREIGN KEY (id_status) REFERENCES type_status_pret(id)
);

-- Prolongement de prêt
CREATE TABLE Prolongement_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    date_prolongement DATE NOT NULL,
    FOREIGN KEY (id_pret) REFERENCES Pret(id)
);

-- Statut de prolongement
CREATE TABLE status_prolongement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_prolongement INT,
    id_status INT,
    FOREIGN KEY (id_prolongement) REFERENCES Prolongement_pret(id),
    FOREIGN KEY (id_status) REFERENCES type_status_pret(id)
);

--------------------------------------------------------- 
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
LEFT JOIN (
    -- Récupère le dernier status pour chaque prêt
    SELECT sp.id_pret, tsp.type
    FROM status_pret sp
    JOIN type_status_pret tsp ON sp.id_status = tsp.id
    WHERE sp.id IN (
        SELECT MAX(id) FROM status_pret GROUP BY id_pret
    )
) statut_actuel ON statut_actuel.id_pret = p.id
WHERE 
    p.id IS NULL
    OR (statut_actuel.type NOT IN ('en attente', 'en cours'));

------------------------------------------------------------- 

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

    p.type_pret AS id_type_pret,
    tp.type AS type_pret,

    c.exemplaire_max,
    c.duree_max,
    DATE_ADD(p.date_debut, INTERVAL c.duree_max DAY) AS date_retour_prevue,

    sp_courant.id_status AS id_status_courant,
    ts.type AS status_courant

FROM
    Pret p
    JOIN Adherant a ON p.id_adherant = a.id
    JOIN Type_adherant ta ON a.id_type_adherant = ta.id
    JOIN Condition_pret c 
        ON c.id_type_adherant = a.id_type_adherant 
       AND c.id_type_pret = p.type_pret
    JOIN Type_pret tp ON p.type_pret = tp.id
    JOIN Exemplaire e ON p.id_exemplaire = e.id
    JOIN Livre l ON e.id_livre = l.id

    LEFT JOIN status_pret sp_courant
      ON sp_courant.id = (
           SELECT MAX(sp2.id)
           FROM status_pret sp2
           WHERE sp2.id_pret = p.id
       )
    LEFT JOIN type_status_pret ts ON sp_courant.id_status = ts.id;

----------------------------------------------------------------

CREATE OR REPLACE VIEW v_prets_avec_status_actuel AS
SELECT
    p.id AS id_pret,
    p.id_adherant,
    p.id_exemplaire,
    p.type_pret,
    p.date_debut,
    tsp.type AS statut_actuel
FROM
    Pret p
    JOIN status_pret sp ON sp.id_pret = p.id
    JOIN type_status_pret tsp ON tsp.id = sp.id_status
    JOIN (
        SELECT
            id_pret,
            MAX(id) AS id_status_pret
        FROM
            status_pret
        GROUP BY
            id_pret
    ) dernier_status ON dernier_status.id_pret = sp.id_pret AND dernier_status.id_status_pret = sp.id;

-----------------------------------------------------------------------------

CREATE OR REPLACE VIEW penalite_adherant AS
SELECT 
    a.id AS id_adherant,
    a.nom,
    a.prenom,
    p.id AS id_pret,
    p.date_debut,
    penalite.date AS date_penalite,
    penalite.nbJour
FROM 
    Penalite penalite
JOIN 
    Pret p ON penalite.id_pret = p.id
JOIN 
    Adherant a ON p.id_adherant = a.id;

------------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_reservations_avec_status_actuel AS
SELECT
    r.id AS id_reservation,
    r.id_adherant,
    r.id_exemplaire,
    r.date_reservation,
    tsp.type AS statut_actuel
FROM
    Reservation r
    JOIN status_reservation sr ON sr.id_reservation = r.id
    JOIN type_status_pret tsp ON tsp.id = sr.id_status
    JOIN (
        SELECT
            id_reservation,
            MAX(id) AS id_status_reservation
        FROM
            status_reservation
        GROUP BY
            id_reservation
    ) dernier_status
    ON dernier_status.id_reservation = sr.id_reservation
    AND dernier_status.id_status_reservation = sr.id;

--------------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_prolongements_avec_status_actuel AS
SELECT
    pp.id AS id_prolongement,
    pp.id_pret,
    pp.date_prolongement,
    tsp.type AS statut_actuel,
    a.nom AS nom_adherant,
    a.prenom AS prenom_adherant,
    l.titre AS titre_livre,
    e.numero_exemplaire
FROM
    Prolongement_pret pp
    JOIN status_prolongement sp ON sp.id_prolongement = pp.id
    JOIN type_status_pret tsp ON tsp.id = sp.id_status
    JOIN (
        SELECT
            id_prolongement,
            MAX(id) AS id_status_prolongement
        FROM
            status_prolongement
        GROUP BY
            id_prolongement
    ) dernier_status
      ON dernier_status.id_prolongement = sp.id_prolongement
     AND dernier_status.id_status_prolongement = sp.id
    JOIN Pret p            ON pp.id_pret = p.id
    JOIN Adherant a        ON p.id_adherant = a.id
    JOIN Exemplaire e      ON p.id_exemplaire = e.id
    JOIN Livre l           ON e.id_livre = l.id;

----------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_inscriptions_avec_status_actuel AS
SELECT
    i.id AS id_inscription,
    i.id_adherant,
    i.date_debut,
    i.date_fin,
    tsi.type AS statut_actuel
FROM
    Inscription i
    JOIN status_inscription si ON si.id_inscription = i.id
    JOIN type_status_inscription tsi ON tsi.id = si.id_status
    JOIN (
        SELECT
            id_inscription,
            MAX(id) AS id_status_inscription
        FROM
            status_inscription
        GROUP BY
            id_inscription
    ) dernier_status
    ON dernier_status.id_inscription = si.id_inscription
    AND dernier_status.id_status_inscription = si.id;