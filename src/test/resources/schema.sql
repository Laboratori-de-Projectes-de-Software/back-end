CREATE TABLE administrador (
                               id_usuario INTEGER PRIMARY KEY
);
CREATE TABLE usuario (
                         id_usuario INTEGER PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         contraseña VARCHAR(255) NOT NULL,
                         imagen VARCHAR(255)
);
CREATE TABLE liga (
                      id_liga INTEGER PRIMARY KEY,
                      id_usuario INTEGER NOT NULL,
                      nombre VARCHAR(255) NOT NULL,
                      FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE bot (
                     id_bot INTEGER PRIMARY KEY,
                     id_usuario INTEGER NOT NULL,
                     cualidad VARCHAR(255),
                     url VARCHAR(255),
                     imagen VARCHAR(255),
                     prompt VARCHAR(255) NOT NULL,
                     nombre VARCHAR(255) NOT NULL,
                     FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE partida (
                         id_partida INTEGER PRIMARY KEY,
                         id_liga INTEGER NOT NULL,
                         duracion_total INTEGER,
                         estado ENUM('EnProgreso','Finalizada'),
                         fecha DATE NOT NULL,
                         FOREIGN KEY (id_liga) REFERENCES liga(id_liga)
);
CREATE TABLE mensaje (
                         id_mensaje INTEGER PRIMARY KEY,
                         id_bot INTEGER NOT NULL,
                         id_partida INTEGER NOT NULL,
                         texto TEXT NOT NULL,
                         hora TIMESTAMP NOT NULL,
                         FOREIGN KEY (id_bot) REFERENCES bot(id_bot),
                         FOREIGN KEY (id_partida) REFERENCES partida(id_partida)
);

CREATE TABLE resultado (
                           id_bot INTEGER NOT NULL,
                           id_partida INTEGER NOT NULL,
                           puntuacion INTEGER NOT NULL,
                           PRIMARY KEY (id_bot, id_partida),
                           FOREIGN KEY (id_bot) REFERENCES bot(id_bot),
                           FOREIGN KEY (id_partida) REFERENCES partida(id_partida)
);
CREATE TABLE bot_liga(
                         id_bot INTEGER NOT NULL,
                         id_liga INTEGER NOT NULL,
                         PRIMARY KEY (id_bot, id_liga),
                         FOREIGN KEY (id_bot) REFERENCES bot(id_bot),
                         FOREIGN KEY (id_liga) REFERENCES liga(id_liga)
);