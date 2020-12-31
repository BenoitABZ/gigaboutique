CREATE SEQUENCE vendeur_id_vendeur_seq;

CREATE TABLE Vendeur (
                id_vendeur INTEGER NOT NULL DEFAULT nextval('vendeur_id_vendeur_seq'),
                adresse_web VARCHAR NOT NULL,
                frais_port INTEGER NOT NULL,
                logo VARCHAR NOT NULL,
                CONSTRAINT vendeur_pk PRIMARY KEY (id_vendeur)
);


ALTER SEQUENCE vendeur_id_vendeur_seq OWNED BY Vendeur.id_vendeur;

CREATE SEQUENCE commentaire_id_commentaire_seq;

CREATE TABLE Commentaire (
                id_commentaire VARCHAR NOT NULL DEFAULT nextval('commentaire_id_commentaire_seq'),
                date_commentaire DATE NOT NULL,
                description VARCHAR NOT NULL,
                auteur VARCHAR NOT NULL,
                id_vendeur INTEGER NOT NULL,
                note INTEGER NOT NULL,
                CONSTRAINT commentaire PRIMARY KEY (id_commentaire)
);


ALTER SEQUENCE commentaire_id_commentaire_seq OWNED BY Commentaire.id_commentaire;

ALTER TABLE Commentaire ADD CONSTRAINT vendeur_commentaire_fk
FOREIGN KEY (id_vendeur)
REFERENCES Vendeur (id_vendeur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
