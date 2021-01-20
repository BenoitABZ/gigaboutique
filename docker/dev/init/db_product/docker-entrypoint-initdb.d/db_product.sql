
CREATE TABLE Vendeur (
                id_vendeur INTEGER NOT NULL,
                CONSTRAINT vendeur_pk PRIMARY KEY (id_vendeur)
);


CREATE SEQUENCE categorie_id_categorie_seq_1;

CREATE TABLE Categorie (
                id_categorie INTEGER NOT NULL DEFAULT nextval('categorie_id_categorie_seq_1'),
                nom_categorie VARCHAR NOT NULL,
                CONSTRAINT id_categorie PRIMARY KEY (id_categorie)
);


ALTER SEQUENCE categorie_id_categorie_seq_1 OWNED BY Categorie.id_categorie;

CREATE SEQUENCE taille_id_taille_seq;

CREATE TABLE Taille (
                id_taille INTEGER NOT NULL DEFAULT nextval('taille_id_taille_seq'),
                taille VARCHAR NOT NULL,
                CONSTRAINT id_taille PRIMARY KEY (id_taille)
);


ALTER SEQUENCE taille_id_taille_seq OWNED BY Taille.id_taille;

CREATE SEQUENCE produit_id_produit_seq;

CREATE TABLE Produit (
                id_produit INTEGER NOT NULL DEFAULT nextval('produit_id_produit_seq'),
                nom VARCHAR NOT NULL,
                prix DOUBLE PRECISION NOT NULL,
                promotion INTEGER NOT NULL,
                id_categorie INTEGER NOT NULL,
                id_vendeur INTEGER NOT NULL,
                marque VARCHAR NOT NULL,
                adresse_web VARCHAR NOT NULL,
                CONSTRAINT id_produit PRIMARY KEY (id_produit)
);


ALTER SEQUENCE produit_id_produit_seq OWNED BY Produit.id_produit;

CREATE SEQUENCE imageproduit_id_image_produit_seq;

CREATE TABLE ImageProduit (
                id_image_produit INTEGER NOT NULL DEFAULT nextval('imageproduit_id_image_produit_seq'),
                adresse_web VARCHAR NOT NULL,
                id_produit INTEGER NOT NULL,
                CONSTRAINT id_image_produit PRIMARY KEY (id_image_produit)
);


ALTER SEQUENCE imageproduit_id_image_produit_seq OWNED BY ImageProduit.id_image_produit;

CREATE TABLE Taille_Produit (
                id_produit INTEGER NOT NULL,
                id_taille INTEGER NOT NULL,
                disponibilite BOOLEAN NOT NULL,
                CONSTRAINT taille_produit_pk PRIMARY KEY (id_produit, id_taille)
);


ALTER TABLE Produit ADD CONSTRAINT vendeur_produit_fk
FOREIGN KEY (id_vendeur)
REFERENCES Vendeur (id_vendeur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Produit ADD CONSTRAINT categorie_produit_fk
FOREIGN KEY (id_categorie)
REFERENCES Categorie (id_categorie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Taille_Produit ADD CONSTRAINT taille_taille_produit_fk
FOREIGN KEY (id_taille)
REFERENCES Taille (id_taille)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Taille_Produit ADD CONSTRAINT produit_taille_produit_fk
FOREIGN KEY (id_produit)
REFERENCES Produit (id_produit)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ImageProduit ADD CONSTRAINT produit_image_produit_fk
FOREIGN KEY (id_produit)
REFERENCES Produit (id_produit)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
