CREATE TABLE usuari (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    rol VARCHAR(255)
);

CREATE TABLE lliga (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    data_creacio TIMESTAMP,
    estat VARCHAR(255),
    admin BIGINT,
    FOREIGN KEY (admin) REFERENCES usuari(id)
);

CREATE TABLE bot (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    data_registre TIMESTAMP,
    model_ia VARCHAR(255),
    propietari BIGINT,
    FOREIGN KEY (propietari) REFERENCES usuari(id)
);

CREATE TABLE classificacio (
    id_lliga BIGINT,
    id_bot BIGINT,
    punts INT,
    enfrontaments INT,
    guanyats INT,
    empatats INT,
    perduts INT,
    data TIMESTAMP,
    FOREIGN KEY (id_lliga) REFERENCES lliga(id),
    FOREIGN KEY (id_bot) REFERENCES bot(id)
);

CREATE TABLE enfrontament (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_lliga BIGINT,
    id_local BIGINT,
    id_visitant BIGINT,
    data TIMESTAMP,
    resultat VARCHAR(255),
    FOREIGN KEY (id_lliga) REFERENCES lliga(id),
    FOREIGN KEY (id_local) REFERENCES bot(id),
    FOREIGN KEY (id_visitant) REFERENCES bot(id)
);

CREATE TABLE resposta (
    id_autor BIGINT,
    id_enfrontament BIGINT,
    temps TIMESTAMP,
    text VARCHAR(255),
    FOREIGN KEY (id_autor) REFERENCES bot(id),
    FOREIGN KEY (id_enfrontament) REFERENCES enfrontament(id)
);

INSERT INTO usuari (nom, email, rol)
VALUES ('admin', 'admin@jaumesitos.com', 'admin');
