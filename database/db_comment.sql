
CREATE SEQUENCE commentaire_id_commentaire_seq;

CREATE TABLE Commentaire (
                id_commentaire VARCHAR NOT NULL DEFAULT nextval('commentaire_id_commentaire_seq'),
                date_commentaire DATE NOT NULL,
                description VARCHAR NOT NULL,
                auteur VARCHAR NOT NULL,
                nom_vendeur_external VARCHAR NOT NULL,
                CONSTRAINT commentaire PRIMARY KEY (id_commentaire)
);


ALTER SEQUENCE commentaire_id_commentaire_seq OWNED BY Commentaire.id_commentaire;
