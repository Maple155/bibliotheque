-- Admins
INSERT INTO admin (nom, prenom) VALUES
('Martin', 'Durand'),
('Lefevre', 'Claire');

-- Bibliothecaires
INSERT INTO bibliothecaire (nom, prenom) VALUES
('Petit', 'Sophie'),
('Bernard', 'Julien');

-- Raretes
INSERT INTO Rarete (type) VALUES
('Commun'),
('Rare'),
('Ancien');

-- Livres
INSERT INTO Livre (titre, nbPage, auteur, datePublication, nbChapitre, langue, editeur, genre, id_rarete) VALUES
('Les Miserables', 96, 'Victor Hugo', '1943-04-06', 27, 'Francais', 'Gallimard', 'Litterature classique', 1),
('L Etranger', 328, 'Albert Camus', '1949-06-08', 24, 'Francais', 'Secker & Warburg', 'Philosophie', 2),
('Harry Potter a l ecole des sorciers', 576, 'J.K. Rowling', '1830-11-01', 45, 'Francais', 'Levavasseur', 'Jeunesse / Fantastique', 1);

-- Exemplaires
INSERT INTO Exemplaire (id_livre, numero_exemplaire) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1);

-- Type d'adhérant
INSERT INTO Type_adherant (type) VALUES
('Etudiant'),
('Professeur'),
('Professionnel'),
('Anonyme');

-- Adhérants
INSERT INTO Adherant (id_type_adherant, nom, prenom, naissance) VALUES
(1, 'Amine Bensaïd', 'Alice', '1990-05-12'),
(1, 'Sarah El Khattabi', 'Jean', '1985-08-23'),
(1, 'Youssef Moujahid', 'Robert', '1978-11-02'),
(2, 'Nadia Benali', 'ITU', '1978-11-02'),
(2, 'Karim Haddadi', 'ITU', '1978-11-02'),
(2, 'Salima Touhami', 'ITU', '1978-11-02'),
(3, 'Rachid El Mansouri', 'ITU', '1978-11-02'),
(3, 'Amina Zerouali', 'ITU', '1978-11-02');

-- Type_status_inscription
INSERT INTO type_status_inscription (type) VALUES
('active'),
('expiree'),
('en attente');

-- Type_pret
INSERT INTO Type_pret (type) VALUES
('a domicile'),
('sur place');

-- Condition_pret
-- Condition_pret : Chaque adhérent a une règle pour chaque type de prêt
INSERT INTO Condition_pret (id_type_adherant, id_type_pret, exemplaire_max, duree_max, prolongement_max, reservation_max, penalite) VALUES
-- Etudiant
(1, 1, 2, 7, 3, 1, 10), -- domicile

-- Professeur
(2, 1, 3, 9, 5, 2, 9), -- domicile

-- Professionnel
(3, 1, 4, 12, 7, 3, 8); -- domicile


-- Type_status_pret
INSERT INTO type_status_pret (type) VALUES
('en attente'),
('en cours'),
('termine'),
('refusé');

INSERT INTO inscription (id_adherant, date_debut, date_fin) VALUES
(1, '2025-02-01', '2025-07-24'),
(3, '2025-04-01', '2025-12-01'),
(4, '2025-07-01', '2026-07-01'),
(6, '2025-07-01', '2026-06-01'),
(7, '2025-06-01', '2025-12-01');
