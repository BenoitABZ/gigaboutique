
CREATE SEQUENCE public.utilisateur_id_utilisateur_seq;

CREATE TABLE public.Utilisateur (
                id_utilisateur INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_utilisateur_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                adresse_mail VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                CONSTRAINT id_utilisateur PRIMARY KEY (id_utilisateur)
);


ALTER SEQUENCE public.utilisateur_id_utilisateur_seq OWNED BY public.Utilisateur.id_utilisateur;

CREATE SEQUENCE public.utilisateur_produit_id_produit_external_seq;

CREATE TABLE public.Utilisateur_Produit (
                id_produit_external INTEGER NOT NULL DEFAULT nextval('public.utilisateur_produit_id_produit_external_seq'),
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT utilisateur_produit_pk PRIMARY KEY (id_produit_external, id_utilisateur)
);


ALTER SEQUENCE public.utilisateur_produit_id_produit_external_seq OWNED BY public.Utilisateur_Produit.id_produit_external;

ALTER TABLE public.Utilisateur_Produit ADD CONSTRAINT utilisateur_utilisateur_produit_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.Utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
