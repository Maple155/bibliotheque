INSERT INTO Livre (titre, nbPage, auteur, datePublication, nbChapitre, langue, editeur, genre) VALUES
("Le Petit Prince", 96, "Antoine de Saint-Exupéry", "1943-04-06", 27, "Français", "Gallimard", "Conte"),
("1984", 328, "George Orwell", "1949-06-08", 24, "Anglais", "Secker & Warburg", "Science-fiction"),
("Les Misérables", 1463, "Victor Hugo", "1862-04-03", 48, "Français", "Pagnerre", "Roman historique"),
("Harry Potter à l\'école des sorciers", 309, "J.K. Rowling", "1997-06-26", 17, "Français", "Gallimard", "Fantastique");

INSERT INTO Exemplaire (id_livre, nbExemplaire) VALUES
(1, 5),
(2, 3),
(3, 2),
(4, 7);

INSERT INTO Type_adherant (type) VALUES 
('Etudiant'),
('Professionnel'),
('Professeur'),
('Anonyme');

INSERT INTO Adherant (id_type_adherant, nom, prenom) VALUES
(1, 'Durand', 'Alice'),
(2, 'Martin', 'Paul'),
(3, 'Dupont', 'Sophie'),
(1, 'Nguyen', 'Minh'),
(4, 'ITU', 'ITU');

INSERT INTO Inscription (id_adherant, date_inscription, status) VALUES
(1, '2025-01-15', 'actif'),
(2, '2024-10-01', 'actif'),
(3, '2024-09-12', 'inactif'),
(4, '2025-02-20', 'actif'),
(5, '2025-06-10', 'actif');

INSERT INTO Type_pret (type) VALUES 
('a domicile'),
('sur place');

INSERT INTO Condition_pret (id_type_adherant, id_type_pret, exemplaire_max, duree_max) VALUES
(1, 1, 3, 21),  -- Etudiant, domicile
(1, 2, 1, 1),   -- Etudiant, sur place
(2, 1, 5, 30),  -- Professionnel, domicile
(2, 2, 2, 1),   -- Professionnel, sur place
(3, 1, 4, 28),  -- Professeur, domicile
(3, 2, 2, 1),   -- Professeur, sur place
(4, 2, 1, 1);   -- Anonyme, sur place seulement

INSERT INTO Pret (id_adherant, id_exemplaire, nb_exemplaire, type_pret, date_debut) VALUES
(1, 1, 1, 1, '2025-06-01'),
(2, 2, 1, 1, '2025-06-05'),
(3, 3, 1, 2, '2025-05-20'),
(4, 4, 2, 1, '2025-06-10');

INSERT INTO Penalite (id_pret, date) VALUES
(3, '2025-06-15'),
(2, '2025-06-20');

INSERT INTO Reservation (id_adherant, id_exemplaire, date_reservation, statut) VALUES
(1, 2, '2025-06-15', 'en attente'),
(2, 3, '2025-06-18', 'en attente'),
(4, 1, '2025-06-19', 'en attente');

INSERT INTO Prolongement_pret (id_pret, date_prolongement, nouvelle_date_retour) VALUES
(1, '2025-06-20', '2025-07-05'),
(2, '2025-06-25', '2025-07-10');