CREATE TABLE IF NOT EXISTS Organization (
    id          integer PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    name        varchar (50) NOT NULL,
    full_name   varchar (100) NOT NULL,
    inn         varchar (10) NOT NULL,
    kpp         varchar (9) NOT NULL,
    address     varchar (100) NOT NULL,
    phone       varchar (15),
    is_active        boolean
);

CREATE TABLE IF NOT EXISTS Office (
    id          integer PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    org_id  	INTEGER NOT NULL,
    name        varchar (50) NOT NULL,
    address     varchar (100) NOT NULL,
    phone       varchar (15),
    is_active   boolean
);

CREATE TABLE IF NOT EXISTS User (
    id                  integer PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER NOT NULL,
    office_id 			INTEGER NOT NULL,
    first_name          varchar (50) NOT NULL,
    second_name         varchar (50),
    middle_name         varchar (50),
    position            varchar (30) NOT NULL,
    phone               varchar (15),
    doc_Code            integer,
    doc_name            varchar(50),
    doc_number          varchar(15),
    doc_date            date ,
    citizenship_code    varchar (10),
    is_identified       boolean
);

CREATE TABLE IF NOT EXISTS doc (
    id         integer PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    code       integer ,
    name       varchar (50)
);

CREATE TABLE IF NOT EXISTS COUNTRY (
    id         integer PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    code       integer ,
    name       varchar (50)
);

ALTER TABLE OFFICE ADD FOREIGN KEY (org_id) REFERENCES Organization(id);
ALTER TABLE USER ADD FOREIGN KEY (OFFICE_ID) REFERENCES OFFICE (ID);
ALTER TABLE USER ADD FOREIGN KEY (DOC_CODE) REFERENCES DOC(code);
ALTER TABLE USER ADD FOREIGN KEY (CITIZENSHIP_CODE) REFERENCES COUNTRY(ID);



