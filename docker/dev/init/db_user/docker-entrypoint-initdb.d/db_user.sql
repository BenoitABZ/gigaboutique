
CREATE SEQUENCE role_id_role_seq_1;

CREATE TABLE Role (
                id_role INTEGER NOT NULL DEFAULT nextval('role_id_role_seq_1'),
                role VARCHAR NOT NULL,
                CONSTRAINT id_role PRIMARY KEY (id_role)
);


ALTER SEQUENCE role_id_role_seq_1 OWNED BY Role.id_role;

CREATE TABLE Produitpanier (
                id_produitpanier INTEGER NOT NULL,
                CONSTRAINT id_produitpanier PRIMARY KEY (id_produitpanier)
);


CREATE SEQUENCE utilisateur_id_utilisateur_seq;

CREATE TABLE Utilisateur (
                id_utilisateur serial NOT NULL DEFAULT nextval('utilisateur_id_utilisateur_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                adresse_mail VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                id_role INTEGER NOT NULL,
                CONSTRAINT id_utilisateur PRIMARY KEY (id_utilisateur)
);


ALTER SEQUENCE utilisateur_id_utilisateur_seq OWNED BY Utilisateur.id_utilisateur;

CREATE SEQUENCE utilisateur_produitpanier_id_utilisateur_produitpanier_seq;

CREATE TABLE Utilisateur_Produitpanier (
                id_utilisateur_produitpanier INTEGER NOT NULL DEFAULT nextval('utilisateur_produitpanier_id_utilisateur_produitpanier_seq'),
                id_produitpanier INTEGER NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT utilisateur_produitpanier_pk PRIMARY KEY (id_utilisateur_produitpanier)
);


ALTER SEQUENCE utilisateur_produitpanier_id_utilisateur_produitpanier_seq OWNED BY Utilisateur_Produitpanier.id_utilisateur_produitpanier;

ALTER TABLE Utilisateur ADD CONSTRAINT role_utilisateur_fk
FOREIGN KEY (id_role)
REFERENCES Role (id_role)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Utilisateur_Produitpanier ADD CONSTRAINT produitpanier_utilisateur_produitpanier_fk
FOREIGN KEY (id_produitpanier)
REFERENCES Produitpanier (id_produitpanier)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Utilisateur_Produitpanier ADD CONSTRAINT utilisateur_utilisateur_produit_fk
FOREIGN KEY (id_utilisateur)
REFERENCES Utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;











