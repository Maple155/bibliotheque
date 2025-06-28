-- Étape 1 : Supprimer les tables existantes dans l'ordre correct

DROP TABLE IF EXISTS Prolongement_pret;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Penalite;
DROP TABLE IF EXISTS Pret;
DROP TABLE IF EXISTS Condition_pret;
DROP TABLE IF EXISTS Inscription;
DROP TABLE IF EXISTS Adherant;
DROP TABLE IF EXISTS Type_adherant;
DROP TABLE IF EXISTS Exemplaire;
DROP TABLE IF EXISTS Livre;
DROP TABLE IF EXISTS Type_pret;

-- Étape 2 : Recréer les tables

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

CREATE TABLE Adherant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    FOREIGN KEY (id_type_adherant) REFERENCES Type_adherant(id)
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

CREATE TABLE Condition_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherant INT NOT NULL,
    id_type_pret INT NOT NULL,
    exemplaire_max INT,
    duree_max INT,
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
