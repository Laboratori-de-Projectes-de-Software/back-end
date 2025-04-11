CREATE TABLE IF NOT EXISTS usuari (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS lliga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    match_time INT,
    name VARCHAR(255),
    rounds INT,
    url_image VARCHAR(255),
    state VARCHAR(255),
    bots VARCHAR(255),
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES usuari(id)
);

CREATE TABLE IF NOT EXISTS bot (
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

CREATE TABLE IF NOT EXISTS classificacio (
    leagueid INT,
    botid INT,
    points INT,
    matches INT,
    wins INT,
    draws INT,
    losses INT,
    inscription TIMESTAMP,
    FOREIGN KEY (leagueid) REFERENCES lliga(id),
    FOREIGN KEY (botid) REFERENCES bot(id)
);

CREATE TABLE IF NOT EXISTS enfrontament (
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

CREATE TABLE IF NOT EXISTS resposta (
    id_autor INT,
    id_enfrontament INT,
    temps TIMESTAMP,
    text VARCHAR(255),
    FOREIGN KEY (id_autor) REFERENCES bot(id),
    FOREIGN KEY (id_enfrontament) REFERENCES enfrontament(id)
);

-- Usuario por defecto
