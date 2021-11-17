CREATE TABLE IF NOT EXISTS Country(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    citizenship_name VARCHAR(50) NOT NULL,
    citizenship_code INTEGER(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Organization(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    short_name VARCHAR(50) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    inn VARCHAR(12) NOT NULL,
    kpp VARCHAR(9) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(30),
    is_active BIT(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS Office(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    office_name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(30),
    is_active BIT(1) NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY(organization_id) REFERENCES Organization(id)
);

CREATE TABLE IF NOT EXISTS Worker_position(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    position_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Usr(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    second_name VARCHAR(50),
    middle_name VARCHAR(50),
    phone VARCHAR(30),
    is_identified BIT(1) NOT NULL,
    document_id INTEGER,
    citizenship_id INTEGER,
    position_id INTEGER NOT NULL,
    office_id INTEGER NOT NULL,
    FOREIGN KEY(citizenship_id) REFERENCES Country(id),
    FOREIGN KEY(office_id) REFERENCES Office(id),
    FOREIGN KEY(position_id) REFERENCES Worker_position(id)
);

CREATE TABLE IF NOT EXISTS DocName(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    doc_code INTEGER(10) NOT NULL,
    doc_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Document(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL,
    doc_number VARCHAR(20) NOT NULL,
    doc_date DATE NOT NULL,
    doc_name_id INTEGER NOT NULL,
    usr_id INTEGER NOT NULL,
    FOREIGN KEY(doc_name_id) REFERENCES DocName(id)
);

ALTER TABLE Usr ADD FOREIGN KEY(document_id) REFERENCES Document(id);
ALTER TABLE Document ADD FOREIGN KEY(usr_id) REFERENCES Usr(id);