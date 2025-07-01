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
('Le Petit Prince', 96, 'Antoine de Saint-Exupéry', '1943-04-06', 27, 'Français', 'Gallimard', 'Conte', 1),
('1984', 328, 'George Orwell', '1949-06-08', 24, 'Anglais', 'Secker & Warburg', 'Dystopie', 2),
('Le Rouge et le Noir', 576, 'Stendhal', '1830-11-01', 45, 'Français', 'Levavasseur', 'Roman', 1);

-- Exemplaires
INSERT INTO Exemplaire (id_livre, numero_exemplaire) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(3, 2);

-- Type d'adhérant
INSERT INTO Type_adherant (type) VALUES
('Etudiant'),
('Professeur'),
('Professionnel'),
('Anonyme');

-- Adhérants
INSERT INTO Adherant (id_type_adherant, nom, prenom, naissance) VALUES
(1, 'Dupont', 'Alice', '1990-05-12'),
(2, 'Muller', 'Jean', '1985-08-23'),
(3, 'Smith', 'Robert', '1978-11-02'),
(4, 'ITU', 'ITU', '1978-11-02');

-- BlacklistLivres
INSERT INTO BlacklistLivres (id_type_adherant, id_livre) VALUES
(4, 2), -- Anonyme ne peut pas emprunter '1984'
(1, 3); -- Etudiant ne peut pas emprunter 'Le Rouge et le Noir'

-- Inscription
INSERT INTO Inscription (id_adherant, date_inscription) VALUES
(1, '2025-01-10'),
(2, '2025-02-15'),
(3, '2025-03-20'),
(4, '2025-04-05');

-- Type_status_inscription
INSERT INTO type_status_inscription (type) VALUES
('active'),
('expirée'),
('en attente');

-- Status_inscription
INSERT INTO status_inscription (id_status, id_inscription) VALUES
(1, 1),
(3, 2),
(1, 3),
(2, 4);

-- Duree_inscription
INSERT INTO duree_inscription (id_type_adherant, duree) VALUES
(1, 365),
(2, 730),
(3, 180),
(4, 30);

-- Type_pret
INSERT INTO Type_pret (type) VALUES
('a domicile'),
('sur place');

-- Condition_pret
INSERT INTO Condition_pret (id_type_adherant, id_type_pret, exemplaire_max, duree_max) VALUES
(1, 1, 3, 30),
(2, 1, 5, 60),
(3, 2, 1, 1),
(4, 2, 0, 0);

-- Pret
INSERT INTO Pret (id_adherant, id_exemplaire, type_pret, date_debut) VALUES
(1, 1, 1, '2025-06-01'),
(2, 3, 1, '2025-06-10'),
(3, 4, 2, '2025-06-15');

-- Type_status_pret
INSERT INTO type_status_pret (type) VALUES
('en attente'),
('en cours'),
('terminé'),
('refusé');

-- Status_pret
INSERT INTO status_pret (id_pret, id_status) VALUES
(1, 2), -- en cours
(2, 1), -- en attente
(3, 3); -- terminé

-- Retour_pret
INSERT INTO retour_pret (id_pret, date_retour) VALUES
(3, '2025-06-17');

-- Penalite
INSERT INTO Penalite (id_pret, date) VALUES
(1, '2025-07-01');

-- Reservation
INSERT INTO Reservation (id_adherant, id_exemplaire, date_reservation) VALUES
(2, 2, '2025-06-25'),
(1, 5, '2025-06-28');

-- Status_reservation
INSERT INTO status_reservation (id_reservation, id_status) VALUES
(1, 1), -- en attente
(2, 2); -- en cours

-- Prolongement_pret
INSERT INTO Prolongement_pret (id_pret, date_prolongement) VALUES
(1, '2025-06-28');

-- Status_prolongement
INSERT INTO status_prolongement (id_prolongement, id_status) VALUES
(1, 1); -- en attente