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

CREATE TABLE IF NOT EXISTS user (
    id                  integer PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER NOT NULL,
    office_id 			INTEGER NOT NULL,
    office_id1 			INTEGER ,
    first_name          varchar (50) NOT NULL,
    second_name         varchar (50),
    middle_name         varchar (50),
    position            varchar (30) NOT NULL,
    phone               varchar (15),
    doc_code            integer not null,
    doc_name            varchar(50),
    doc_number          varchar(15),
    doc_date            date ,
    citizenship_code    integer not null,
    is_identified       boolean
);

CREATE TABLE IF NOT EXISTS doc (
    version    INTEGER NOT NULL,
    code       integer PRIMARY KEY,
    name       varchar (50)

);

CREATE TABLE IF NOT EXISTS COUNTRY (
    version    INTEGER NOT NULL,
    code       integer  PRIMARY KEY,
    name       varchar (50),
);

CREATE INDEX IX_USER_DOC_CODE ON USER (DOC_CODE) ;
CREATE INDEX IX_USER_citizenship_code ON USER (citizenship_code) ;

ALTER TABLE OFFICE ADD FOREIGN KEY (org_id) REFERENCES Organization(id);
ALTER TABLE USER ADD FOREIGN KEY (OFFICE_ID) REFERENCES OFFICE (ID);
ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES doc(code);
ALTER TABLE USER ADD FOREIGN KEY (citizenship_code) REFERENCES COUNTRY(code);



