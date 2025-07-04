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

-- BlacklistAge : interdictions d'âge pour certains livres

INSERT INTO BlacklistAge (age_min, id_livre) VALUES
(16, 2), -- '1984' interdit aux moins de 16 ans
(18, 3); -- 'Le Rouge et le Noir' interdit aux moins de 18 ans

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
INSERT INTO Condition_pret (id_type_adherant, id_type_pret, exemplaire_max, duree_max) VALUES
(1, 1, 3, 30),
(2, 1, 5, 60),
(3, 2, 1, 1),
(4, 2, 0, 0);

-- Type_status_pret
INSERT INTO type_status_pret (type) VALUES
('en attente'),
('en cours'),
('termine'),
('refusé');