-- ==============================
-- Données complètes pour BIBLIOTHEQUE
-- ==============================

-- Livres
INSERT INTO Livre (titre, nbPage, auteur, datePublication, nbChapitre, langue, editeur, genre) VALUES
('La Peste', 320, 'Albert Camus', '1947-06-10', 25, 'Français', 'Gallimard', 'Roman'),
('Fondation', 255, 'Isaac Asimov', '1951-05-01', 20, 'Anglais', 'Gnome Press', 'Science-fiction'),
('Les Fleurs du mal', 180, 'Charles Baudelaire', '1857-06-25', 15, 'Français', 'Poulet-Malassis', 'Poésie'),
('Le Seigneur des Anneaux', 1178, 'J.R.R. Tolkien', '1954-07-29', 22, 'Anglais', 'Allen & Unwin', 'Fantasy');

-- Exemplaires
INSERT INTO Exemplaire (id_livre, numero_exemplaire) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(4, 2),
(4, 3);

-- Types d'adhérents
INSERT INTO Type_adherant (type) VALUES 
('Etudiant'),
('Professionnel'),
('Professeur'),
('Anonyme');

-- Adhérents
INSERT INTO Adherant (id_type_adherant, nom, prenom) VALUES
(1, 'Martin', 'Alice'),
(2, 'Durand', 'Paul'),
(3, 'Nguyen', 'Sophie'),
(1, 'Bernard', 'Lucas'),
(4, 'Anonyme', 'Utilisateur');

-- BlacklistLivres
INSERT INTO BlacklistLivres (id_type_adherant, id_livre) VALUES
(4, 2),
(4, 4);

-- Inscriptions
INSERT INTO Inscription (id_adherant, date_inscription, status) VALUES
(1, '2025-02-15', 'actif'),
(2, '2025-03-01', 'actif'),
(3, '2024-11-20', 'inactif'),
(4, '2025-04-10', 'actif'),
(5, '2025-05-05', 'actif');

-- Durée d'inscription
INSERT INTO duree_inscripiton (id_type_adherant, duree) VALUES
(1, 365),
(2, 730),
(3, 1095),
(4, 30);

-- Types de prêt
INSERT INTO Type_pret (type) VALUES
('a domicile'),
('sur place');

-- Conditions de prêt
INSERT INTO Condition_pret (id_type_adherant, id_type_pret, exemplaire_max, duree_max) VALUES
(1, 1, 3, 21),
(1, 2, 1, 1),
(2, 1, 5, 30),
(2, 2, 2, 1),
(3, 1, 4, 28),
(3, 2, 2, 1),
(4, 2, 1, 1);

-- Prêts
INSERT INTO Pret (id_adherant, id_exemplaire, type_pret, date_debut) VALUES
(1, 1, 1, '2025-06-15'),
(2, 2, 1, '2025-06-17'),
(3, 3, 2, '2025-06-10'),
(4, 5, 1, '2025-06-20');

-- Pénalités
INSERT INTO Penalite (id_pret, date) VALUES
(3, '2025-06-30'),
(2, '2025-06-25');

-- Réservations
INSERT INTO Reservation (id_adherant, id_exemplaire, date_reservation, statut) VALUES
(1, 4, '2025-06-22', 'en attente'),
(2, 5, '2025-06-24', 'effectuée'),
(4, 2, '2025-06-25', 'en attente');
