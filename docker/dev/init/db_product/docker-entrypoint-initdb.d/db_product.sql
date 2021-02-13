
CREATE SEQUENCE public.genre_id_genre_seq_1;

CREATE TABLE public.Genre (
                id_genre INTEGER NOT NULL DEFAULT nextval('public.genre_id_genre_seq_1'),
                nom_genre VARCHAR NOT NULL,
                CONSTRAINT genre_pk PRIMARY KEY (id_genre)
);


ALTER SEQUENCE public.genre_id_genre_seq_1 OWNED BY public.Genre.id_genre;

CREATE TABLE public.Vendeur (
                id_vendeur INTEGER NOT NULL,
                CONSTRAINT vendeur_pk PRIMARY KEY (id_vendeur)
);


CREATE SEQUENCE public.categorie_id_categorie_seq_1;

CREATE TABLE public.Categorie (
                id_categorie INTEGER NOT NULL DEFAULT nextval('public.categorie_id_categorie_seq_1'),
                nom_categorie VARCHAR NOT NULL,
                CONSTRAINT id_categorie PRIMARY KEY (id_categorie)
);


ALTER SEQUENCE public.categorie_id_categorie_seq_1 OWNED BY public.Categorie.id_categorie;

CREATE SEQUENCE public.taille_id_taille_seq;

CREATE TABLE public.Taille (
                id_taille INTEGER NOT NULL DEFAULT nextval('public.taille_id_taille_seq'),
                taille VARCHAR NOT NULL,
                CONSTRAINT id_taille PRIMARY KEY (id_taille)
);


ALTER SEQUENCE public.taille_id_taille_seq OWNED BY public.Taille.id_taille;

CREATE SEQUENCE public.produit_id_produit_seq;

CREATE TABLE public.Produit (
                id_produit INTEGER NOT NULL DEFAULT nextval('public.produit_id_produit_seq'),
                nom VARCHAR NOT NULL,
                prix DOUBLE PRECISION NOT NULL,
                promotion INTEGER NOT NULL,
                id_categorie INTEGER NOT NULL,
                id_vendeur INTEGER NOT NULL,
                marque VARCHAR NOT NULL,
                adresse_web VARCHAR NOT NULL,
                id_genre INTEGER NOT NULL,
                maj BOOLEAN NOT NULL,
                CONSTRAINT id_produit PRIMARY KEY (id_produit)
);


ALTER SEQUENCE public.produit_id_produit_seq OWNED BY public.Produit.id_produit;

CREATE SEQUENCE public.imageproduit_id_image_produit_seq;

CREATE TABLE public.ImageProduit (
                id_image_produit INTEGER NOT NULL DEFAULT nextval('public.imageproduit_id_image_produit_seq'),
                adresse_web VARCHAR NOT NULL,
                id_produit INTEGER NOT NULL,
                CONSTRAINT id_image_produit PRIMARY KEY (id_image_produit)
);


ALTER SEQUENCE public.imageproduit_id_image_produit_seq OWNED BY public.ImageProduit.id_image_produit;

CREATE TABLE public.Taille_Produit (
                id_taille_produit INTEGER NOT NULL,
                id_taille INTEGER NOT NULL,
                id_produit INTEGER NOT NULL,
                disponibilite BOOLEAN NOT NULL,
                CONSTRAINT taille_produit_pk PRIMARY KEY (id_taille_produit)
);


ALTER TABLE public.Produit ADD CONSTRAINT genre_produit_fk
FOREIGN KEY (id_genre)
REFERENCES public.Genre (id_genre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Produit ADD CONSTRAINT vendeur_produit_fk
FOREIGN KEY (id_vendeur)
REFERENCES public.Vendeur (id_vendeur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Produit ADD CONSTRAINT categorie_produit_fk
FOREIGN KEY (id_categorie)
REFERENCES public.Categorie (id_categorie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Taille_Produit ADD CONSTRAINT taille_taille_produit_fk
FOREIGN KEY (id_taille)
REFERENCES public.Taille (id_taille)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Taille_Produit ADD CONSTRAINT produit_taille_produit_fk
FOREIGN KEY (id_produit)
REFERENCES public.Produit (id_produit)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ImageProduit ADD CONSTRAINT produit_image_produit_fk
FOREIGN KEY (id_produit)
REFERENCES public.Produit (id_produit)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
CREATE SEQUENCE public.genre_id_genre_seq_1;

CREATE TABLE public.Genre (
                id_genre INTEGER NOT NULL DEFAULT nextval('public.genre_id_genre_seq_1'),
                nom_genre VARCHAR NOT NULL,
                CONSTRAINT genre_pk PRIMARY KEY (id_genre)
);


ALTER SEQUENCE public.genre_id_genre_seq_1 OWNED BY public.Genre.id_genre;

CREATE TABLE public.Vendeur (
                id_vendeur INTEGER NOT NULL,
                CONSTRAINT vendeur_pk PRIMARY KEY (id_vendeur)
);


CREATE SEQUENCE public.categorie_id_categorie_seq_1;

CREATE TABLE public.Categorie (
                id_categorie INTEGER NOT NULL DEFAULT nextval('public.categorie_id_categorie_seq_1'),
                nom_categorie VARCHAR NOT NULL,
                CONSTRAINT id_categorie PRIMARY KEY (id_categorie)
);


ALTER SEQUENCE public.categorie_id_categorie_seq_1 OWNED BY public.Categorie.id_categorie;

CREATE SEQUENCE public.taille_id_taille_seq;

CREATE TABLE public.Taille (
                id_taille INTEGER NOT NULL DEFAULT nextval('public.taille_id_taille_seq'),
                taille VARCHAR NOT NULL,
                CONSTRAINT id_taille PRIMARY KEY (id_taille)
);


ALTER SEQUENCE public.taille_id_taille_seq OWNED BY public.Taille.id_taille;

CREATE SEQUENCE public.produit_id_produit_seq;


CREATE SEQUENCE public.genre_id_genre_seq_1;

CREATE TABLE public.Genre (
                id_genre INTEGER NOT NULL DEFAULT nextval('public.genre_id_genre_seq_1'),
                nom_genre VARCHAR NOT NULL,
                CONSTRAINT genre_pk PRIMARY KEY (id_genre)
);


ALTER SEQUENCE public.genre_id_genre_seq_1 OWNED BY public.Genre.id_genre;

CREATE TABLE public.Vendeur (
                id_vendeur INTEGER NOT NULL,
                CONSTRAINT vendeur_pk PRIMARY KEY (id_vendeur)
);


CREATE SEQUENCE public.categorie_id_categorie_seq_1;

CREATE TABLE public.Categorie (
                id_categorie INTEGER NOT NULL DEFAULT nextval('public.categorie_id_categorie_seq_1'),
                nom_categorie VARCHAR NOT NULL,
                CONSTRAINT id_categorie PRIMARY KEY (id_categorie)
);


ALTER SEQUENCE public.categorie_id_categorie_seq_1 OWNED BY public.Categorie.id_categorie;

CREATE SEQUENCE public.taille_id_taille_seq;

CREATE TABLE public.Taille (
                id_taille INTEGER NOT NULL DEFAULT nextval('public.taille_id_taille_seq'),
                taille VARCHAR NOT NULL,
                CONSTRAINT id_taille PRIMARY KEY (id_taille)
);


ALTER SEQUENCE public.taille_id_taille_seq OWNED BY public.Taille.id_taille;

CREATE SEQUENCE public.produit_id_produit_seq;

CREATE TABLE public.Produit (
                id_produit INTEGER NOT NULL DEFAULT nextval('public.produit_id_produit_seq'),
                nom VARCHAR NOT NULL,
                prix DOUBLE PRECISION NOT NULL,
                promotion INTEGER NOT NULL,
                id_categorie INTEGER NOT NULL,
                id_vendeur INTEGER NOT NULL,
                marque VARCHAR NOT NULL,
                adresse_web VARCHAR NOT NULL,
                id_genre INTEGER NOT NULL,
                maj BOOLEAN NOT NULL,
                CONSTRAINT id_produit PRIMARY KEY (id_produit)
);


ALTER SEQUENCE public.produit_id_produit_seq OWNED BY public.Produit.id_produit;

CREATE SEQUENCE public.imageproduit_id_image_produit_seq;

CREATE TABLE public.ImageProduit (
                id_image_produit INTEGER NOT NULL DEFAULT nextval('public.imageproduit_id_image_produit_seq'),
                adresse_web VARCHAR NOT NULL,
                id_produit INTEGER NOT NULL,
                CONSTRAINT id_image_produit PRIMARY KEY (id_image_produit)
);


ALTER SEQUENCE public.imageproduit_id_image_produit_seq OWNED BY public.ImageProduit.id_image_produit;

CREATE SEQUENCE public.taille_produit_id_taille_produit_seq;

CREATE TABLE public.Taille_Produit (
                id_taille_produit INTEGER NOT NULL DEFAULT nextval('public.taille_produit_id_taille_produit_seq'),
                id_taille INTEGER NOT NULL,
                id_produit INTEGER NOT NULL,
                disponibilite BOOLEAN NOT NULL,
                CONSTRAINT taille_produit_pk PRIMARY KEY (id_taille_produit),
                UNIQUE (id_taille, id_produit)
);


ALTER SEQUENCE public.taille_produit_id_taille_produit_seq OWNED BY public.Taille_Produit.id_taille_produit;

ALTER TABLE public.Produit ADD CONSTRAINT genre_produit_fk
FOREIGN KEY (id_genre)
REFERENCES public.Genre (id_genre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Produit ADD CONSTRAINT vendeur_produit_fk
FOREIGN KEY (id_vendeur)
REFERENCES public.Vendeur (id_vendeur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Produit ADD CONSTRAINT categorie_produit_fk
FOREIGN KEY (id_categorie)
REFERENCES public.Categorie (id_categorie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Taille_Produit ADD CONSTRAINT taille_taille_produit_fk
FOREIGN KEY (id_taille)
REFERENCES public.Taille (id_taille)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Taille_Produit ADD CONSTRAINT produit_taille_produit_fk
FOREIGN KEY (id_produit)
REFERENCES public.Produit (id_produit)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ImageProduit ADD CONSTRAINT produit_image_produit_fk
FOREIGN KEY (id_produit)
REFERENCES public.Produit (id_produit)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
