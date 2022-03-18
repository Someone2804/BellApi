CREATE TABLE IF NOT EXISTS Citizenship
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL,
    citizenship_name VARCHAR(50) NOT NULL,
    citizenship_code VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Organization
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER     NOT NULL,
    short_name VARCHAR(50) NOT NULL,
    full_name  VARCHAR(50) NOT NULL,
    inn        VARCHAR(12) NOT NULL,
    kpp        VARCHAR(9)  NOT NULL,
    address    VARCHAR(50) NOT NULL,
    phone      VARCHAR(30),
    is_active  BIT(1)      NOT NULL
);

CREATE TABLE IF NOT EXISTS Office
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL,
    office_name     VARCHAR(50) NOT NULL,
    address         VARCHAR(50) NOT NULL,
    phone           VARCHAR(30),
    is_active       BIT(1)      NOT NULL,
    organization_id INTEGER     NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES Organization (id)
);

CREATE TABLE IF NOT EXISTS Position
(
    id            INTEGER PRIMARY KEY AUTO_INCREMENT,
    version       INTEGER     NOT NULL,
    position_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Usr
(
    id             INTEGER PRIMARY KEY AUTO_INCREMENT,
    version        INTEGER     NOT NULL,
    username       VARCHAR(30) NOT NULL UNIQUE,
    password       VARCHAR(72) NOT NULL,
    role_id        INTEGER     NOT NULL,
    first_name     VARCHAR(50) NOT NULL,
    second_name    VARCHAR(50),
    middle_name    VARCHAR(50),
    phone          VARCHAR(30),
    is_identified  BIT(1)      NOT NULL,
    citizenship_id INTEGER,
    office_id      INTEGER     NOT NULL,
    FOREIGN KEY (citizenship_id) REFERENCES Citizenship (id),
    FOREIGN KEY (office_id) REFERENCES Office (id)
);

CREATE TABLE IF NOT EXISTS role
(
    id   INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(50)         NOT NULL
);

CREATE TABLE IF NOT EXISTS Document_name
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    version  INTEGER     NOT NULL,
    doc_code VARCHAR(10) NOT NULL,
    doc_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Document
(
    id          INTEGER PRIMARY KEY,
    version     INTEGER     NOT NULL,
    doc_number  VARCHAR(20) NOT NULL,
    doc_date    DATE        NOT NULL,
    doc_name_id INTEGER     NOT NULL,
    FOREIGN KEY (doc_name_id) REFERENCES Document_name (id)
);

CREATE TABLE IF NOT EXISTS Usr_Position
(
    usr_id      INTEGER NOT NULL,
    position_id INTEGER NOT NULL,

    PRIMARY KEY (usr_id, position_id)
);

CREATE INDEX IF NOT EXISTS IX_Usr_Position_Id ON Usr_Position (position_id);
ALTER TABLE Usr_Position
    ADD FOREIGN KEY (usr_id) REFERENCES Usr (id);

CREATE INDEX IF NOT EXISTS IX_Position_Usr_Id ON Usr_Position (usr_id);
ALTER TABLE Usr_Position
    ADD FOREIGN KEY (position_id) REFERENCES Position (id);