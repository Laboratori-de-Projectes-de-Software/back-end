CREATE TABLE usuari
(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    rol VARCHAR(255)
);

CREATE TABLE lliga
(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    data_creacio TIMESTAMP,
    estat VARCHAR(255),
    admin INT,
    FOREIGN KEY (admin) REFERENCES usuari(id)
);

CREATE TABLE bot
(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    data_registre TIMESTAMP,
    model_ia VARCHAR(255),
    propietari INT,
    FOREIGN KEY (propietari) REFERENCES usuari(id)

);

CREATE TABLE classificacio
(
    id_lliga INT,
    id_bot INT,
    punts INT,
    enfrontaments INT,
    guanyats INT,
    empatats INT,
    perduts INT,
    data TIMESTAMP,
    FOREIGN KEY (id_lliga) REFERENCES lliga(id),
    FOREIGN KEY (id_bot) REFERENCES bot(id)

);

CREATE TABLE enfrontament(
    id SERIAL,
    id_lliga INT,
    id_local INT,
    id_visitant INT,
    data TIMESTAMP,
    resultat VARCHAR(255),
    FOREIGN KEY (id_lliga) REFERENCES lliga(id),
    FOREIGN KEY (id_local) REFERENCES bot(id),
    FOREIGN KEY (id_visitant) REFERENCES bot(id)
);

CREATE TABLE resposta(
    id_autor INT,
    id_enfrontament INT,
    temps TIMESTAMP,
    text VARCHAR(255),
    FOREIGN KEY (id_autor) REFERENCES bot(id),
    FOREIGN KEY (id_enfrontament) REFERENCES enfrontament(id)
);


INSERT INTO usuari (nom,email,rol)
VALUES ('admin', 'admin@jaumesitos.com','admin');

