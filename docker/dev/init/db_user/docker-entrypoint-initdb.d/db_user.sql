
CREATE SEQUENCE utilisateur_id_utilisateur_seq;

CREATE TABLE Utilisateur (
                id_utilisateur INTEGER NOT NULL DEFAULT nextval('utilisateur_id_utilisateur_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                adresse_mail VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                CONSTRAINT id_utilisateur PRIMARY KEY (id_utilisateur)
);


ALTER SEQUENCE utilisateur_id_utilisateur_seq OWNED BY Utilisateur.id_utilisateur;

CREATE TABLE Utilisateur_Produit (
                id_produit_external INTEGER NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT utilisateur_produit_pk PRIMARY KEY (id_produit_external, id_utilisateur)
);


ALTER TABLE Utilisateur_Produit ADD CONSTRAINT utilisateur_utilisateur_produit_fk
FOREIGN KEY (id_utilisateur)
REFERENCES Utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
