CREATE TABLE usuari (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE lliga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    data_creacio TIMESTAMP,
    estat VARCHAR(255),
    admin INT,
    FOREIGN KEY (admin) REFERENCES usuari(id)
);

CREATE TABLE bot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    url_image VARCHAR(255),
    endpoint VARCHAR(255),
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    draws INT DEFAULT 0,
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES usuari(id)
);

CREATE TABLE classificacio (
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

CREATE TABLE enfrontament (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_lliga INT,
    id_local INT,
    id_visitant INT,
    data TIMESTAMP,
    resultat VARCHAR(255),
    FOREIGN KEY (id_lliga) REFERENCES lliga(id),
    FOREIGN KEY (id_local) REFERENCES bot(id),
    FOREIGN KEY (id_visitant) REFERENCES bot(id)
);

CREATE TABLE resposta (
    id_autor INT,
    id_enfrontament INT,
    temps TIMESTAMP,
    text VARCHAR(255),
    FOREIGN KEY (id_autor) REFERENCES bot(id),
    FOREIGN KEY (id_enfrontament) REFERENCES enfrontament(id)
);

-- Usuario por defecto
INSERT INTO usuari (name, email, password, role)
VALUES ('admin', 'admin@jaumesitos.com', '$2a$10$u0eP/qrwvru7fPDwRBWWn.H4dTkldNDDxOyndWKdz2RKv74KMA0.i', 'admin');

INSERT INTO usuari (name, email, password, role)
VALUES ('xisco', 'xisco@jaumesitos.com', '$2a$10$jL0p6tJwrnTwmy/l35W/8u464axyPE5bn/2RmwazKtkRt/tTUdrTS', 'admin');
