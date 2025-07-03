Table admin {
  id int [pk, increment]
  nom varchar(100)
  prenom varchar(100)
}

Table bibliothecaire {
  id int [pk, increment]
  nom varchar(100)
  prenom varchar(100)
}

Table Rarete {
  id int [pk, increment]
  type varchar(100)
}

Table Livre {
  id int [pk, increment]
  titre varchar(255) [not null]
  nbPage int
  auteur varchar(255)
  datePublication date
  nbChapitre int
  langue varchar(50)
  editeur varchar(100)
  genre varchar(100)
  id_rarete int
}

Table Exemplaire {
  id int [pk, increment]
  id_livre int [not null]
  numero_exemplaire int
}

Table Type_adherant {
  id int [pk, increment]
  type varchar(100) [not null]
}

Table Adherant {
  id int [pk, increment]
  id_type_adherant int [not null]
  nom varchar(100)
  prenom varchar(100)
  naissance date
}

Table BlacklistAge {
  id int [pk, increment]
  age_min int [not null]
  id_livre int [not null]
}

Table BlacklistLivres {
  id int [pk, increment]
  id_type_adherant int [not null]
  id_livre int [not null]
}

Table Inscription {
  id int [pk, increment]
  id_adherant int [not null]
  date_inscription date
}

Table type_status_inscription {
  id int [pk, increment]
  type varchar(55)
}

Table status_inscription {
  id int [pk, increment]
  id_status int
  id_inscription int
}

Table duree_inscription {
  id int [pk, increment]
  id_type_adherant int
  duree int
}

Table Type_pret {
  id int [pk, increment]
  type varchar(100) [not null]
}

Table Condition_pret {
  id int [pk, increment]
  id_type_adherant int [not null]
  id_type_pret int [not null]
  exemplaire_max int
  duree_max int
}

Table Pret {
  id int [pk, increment]
  id_adherant int [not null]
  id_exemplaire int [not null]
  type_pret int [not null]
  date_debut date
}

Table type_status_pret {
  id int [pk, increment]
  type varchar(50)
}

Table status_pret {
  id int [pk, increment]
  id_pret int
  id_status int
}

Table retour_pret {
  id int [pk, increment]
  id_pret int
  date_retour date
}

Table Penalite {
  id int [pk, increment]
  id_pret int [not null]
  date date
}

Table Reservation {
  id int [pk, increment]
  id_adherant int [not null]
  id_exemplaire int [not null]
  date_reservation date [not null]
}

Table status_reservation {
  id int [pk, increment]
  id_reservation int
  id_status int
}

Table Prolongement_pret {
  id int [pk, increment]
  id_pret int [not null]
  date_prolongement date [not null]
}

Table status_prolongement {
  id int [pk, increment]
  id_prolongement int
  id_status int
}

// --- Relations ---
Ref: Livre.id_rarete > Rarete.id
Ref: Exemplaire.id_livre > Livre.id
Ref: Adherant.id_type_adherant > Type_adherant.id
Ref: BlacklistAge.id_livre > Livre.id
Ref: BlacklistLivres.id_type_adherant > Type_adherant.id
Ref: BlacklistLivres.id_livre > Livre.id
Ref: Inscription.id_adherant > Adherant.id
Ref: status_inscription.id_status > type_status_inscription.id
Ref: status_inscription.id_inscription > Inscription.id
Ref: duree_inscription.id_type_adherant > Type_adherant.id
Ref: Condition_pret.id_type_adherant > Type_adherant.id
Ref: Condition_pret.id_type_pret > Type_pret.id
Ref: Pret.id_adherant > Adherant.id
Ref: Pret.id_exemplaire > Exemplaire.id
Ref: Pret.type_pret > Type_pret.id
Ref: status_pret.id_pret > Pret.id
Ref: status_pret.id_status > type_status_pret.id
Ref: retour_pret.id_pret > Pret.id
Ref: Penalite.id_pret > Pret.id
Ref: Reservation.id_adherant > Adherant.id
Ref: Reservation.id_exemplaire > Exemplaire.id
Ref: status_reservation.id_reservation > Reservation.id
Ref: status_reservation.id_status > type_status_pret.id
Ref: Prolongement_pret.id_pret > Pret.id
Ref: status_prolongement.id_prolongement > Prolongement_pret.id
Ref: status_prolongement.id_status > type_status_pret.id