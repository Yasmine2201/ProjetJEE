CREATE DATABASE IF NOT EXISTS teach_finder_db;

START TRANSACTION;

USE teach_finder_db;

DROP TABLE IF EXISTS Candidature;
DROP TABLE IF EXISTS Need;
DROP TABLE IF EXISTS Evaluation;
DROP TABLE IF EXISTS Disponibility;
DROP TABLE IF EXISTS Registration;
DROP TABLE IF EXISTS Recruiter;
DROP TABLE IF EXISTS School;
DROP TABLE IF EXISTS Teacher;
DROP TABLE IF EXISTS ApplicationUser;
DROP TABLE IF EXISTS Refs;
DROP TABLE IF EXISTS Experience;
DROP TABLE IF EXISTS Disponibilities;
DROP TABLE IF EXISTS Application;

CREATE TABLE School
(
    schoolName      VARCHAR(128) NOT NULL PRIMARY KEY,
    address         VARCHAR(256),
    specializations VARCHAR(256)
) ENGINE = InnoDB;

CREATE TABLE Registration
(
    registrationId INT                                     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login          VARCHAR(32)                             NOT NULL,
    password       VARCHAR(64)                             NOT NULL,
    firstname      VARCHAR(32)                             NOT NULL,
    lastname       VARCHAR(32)                             NOT NULL,
    email          VARCHAR(64)                             NOT NULL,
    phone          VARCHAR(16),
    role           ENUM ('Admin', 'Teacher', 'Recruiter')  NOT NULL,
    status         ENUM ('Accepted', 'Refused', 'Pending') NOT NULL DEFAULT 'PENDING',
    schoolName     VARCHAR(128),
    FOREIGN KEY (schoolName) REFERENCES School (schoolName)
) ENGINE = InnoDB;

CREATE TABLE ApplicationUser
(
    userId    INT                                    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login     VARCHAR(32)                            NOT NULL UNIQUE,
    password  VARCHAR(64)                            NOT NULL,
    firstname VARCHAR(32)                            NOT NULL,
    lastname  VARCHAR(32)                            NOT NULL,
    email     VARCHAR(64)                            NOT NULL,
    phone     VARCHAR(16),
    role      ENUM ('Admin', 'Teacher', 'Recruiter') NOT NULL
) ENGINE = InnoDB;

CREATE TABLE Teacher
(
    teacherId              INT                                    NOT NULL PRIMARY KEY,
    experiences            TEXT,
    skills                 VARCHAR(256),
    personnalInterests     VARCHAR(256),
    schoolInterests        VARCHAR(256),
    desiredLevels          VARCHAR(64),
    contractType           ENUM ('Temporary', 'Continous', 'Any') NOT NULL DEFAULT 'Any',
    academicCertifications VARCHAR(256),
    otherInformations      TEXT,
    recommendations        VARCHAR(256),
    FOREIGN KEY (teacherId) REFERENCES ApplicationUser (userId)
) ENGINE = InnoDB;

CREATE TABLE Recruiter
(
    recruiterId INT          NOT NULL PRIMARY KEY,
    schoolName  VARCHAR(128) NOT NULL,
    FOREIGN KEY (recruiterId) REFERENCES ApplicationUser (userId),
    FOREIGN KEY (schoolName) REFERENCES School (schoolName)
) ENGINE = InnoDB;

CREATE TABLE Disponibility
(
    disponibilityId INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teacherId       INT      NOT NULL,
    startDate       DATETIME NOT NULL,
    endDate         DATETIME NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES Teacher (teacherId)
) ENGINE = InnoDB;

CREATE TABLE Evaluation
(
    teacherId  INT          NOT NULL,
    schoolName VARCHAR(128) NOT NULL,
    rating     TINYINT      NOT NULL,
    comment    TEXT,
    PRIMARY KEY (teacherId, schoolName),
    FOREIGN KEY (teacherId) REFERENCES Teacher (teacherId),
    FOREIGN KEY (schoolName) REFERENCES School (schoolName)
) ENGINE = InnoDB;

CREATE TABLE Need
(
    needId       INT                                    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    recruiterId  INT                                    NOT NULL,
    schoolName   VARCHAR(128)                           NOT NULL,
    contractType ENUM ('Temporary', 'Continous', 'Any') NOT NULL DEFAULT 'Temporary',
    subject      VARCHAR(64)                            NOT NULL,
    requirements TEXT,
    timePeriod   VARCHAR(32),
    notes        TEXT,
    FOREIGN KEY (schoolName) REFERENCES School (schoolName),
    FOREIGN KEY (recruiterId) REFERENCES Recruiter (recruiterId)
) ENGINE = InnoDB;

CREATE TABLE Candidature
(
    teacherId              INT                                     NOT NULL,
    needId                 INT                                     NOT NULL,
    schoolName             VARCHAR(128)                            NOT NULL,
    createdOn              DATETIME                                NOT NULL,
    isInitiatedByTeacher   BOOLEAN                                 NOT NULL DEFAULT TRUE,
    isValidatedByTeacher   BOOLEAN                                 NOT NULL DEFAULT FALSE,
    isValidatedByRecruiter BOOLEAN                                 NOT NULL DEFAULT FALSE,
    status                 ENUM ('Pending', 'Accepted', 'Refused') NOT NULL DEFAULT 'Pending',
    PRIMARY KEY (teacherId, needId),
    FOREIGN KEY (teacherId) REFERENCES Teacher (teacherId),
    FOREIGN KEY (needId) REFERENCES Need (needId),
    FOREIGN KEY (schoolName) REFERENCES School (schoolName)
) ENGINE = InnoDB;


INSERT INTO school (schoolName, address, specializations)
VALUES ('EFREI', '30-32 Av. de la République, 94800 Villejuif', 'Informatique'),
       ('MINE', '123 Rue Saint-Jacques, 75005 Paris', 'Littérature, Sciences'),
       ('Polytech', '1 Rue Clovis, 75005 Paris', 'Mathématiques, Histoire'),
       ('ESME', '106 Rue de la Pompe, 75116 Paris', 'Sciences, Économie'),
       ('HEC', '44 Boulevard Saint-Michel, 75006 Paris', 'Commerce, Langues');

INSERT INTO registration(login, password, firstname, lastname, email, phone, role,status, schoolName)
VALUES
    #MDP : John.Doe
    ( 'John.Doe','73372335bd12eb0633284c1b68419e0f7ad16d3d777b13e5d2f8c41549a618ec' , 'John', 'Doe', 'john.doe@example.com', '+33604070146', 'Teacher', 'Pending', NULL),

    #MDP : emily.smith
    ( 'emily.smith','924009a2b43366e66a8040de2a78cfa6c58272a8508ec498da02555c0eb584f2' , 'emily', 'smith', 'emily.smith@example.com', '+33613802856', 'Teacher', 'Pending', NULL),

    #MDP : david.miller
    ( 'david.miller','3b2d78a462ad9cce711bfc24a920a7a3cefc506ada270909efb7c76144103ee0' , 'david', 'miller', 'david.miller@example.com', '+33604070199', 'Recruiter', 'Pending', 'MINE'),

    #MDP : sophia.williams
    ( 'sophia.williams','08b706a236c0da44d3fa0500f3746cfafd72739b147b42fceeb673667ad5995f' , 'John', 'williams', 'sophia.williams@example.com', '+33675670146', 'Recruiter', 'Pending', 'Polytech'),

    #MDP : ethan.martin
    ( 'ethan.martin','f0b38b2a5589fd2a68ff88864567630088d0868e5e8b60e676a6edf3e3aa9ede' , 'ethan', 'martin', 'ethan.martin@example.com', '+33605670146', 'Recruiter', 'Pending', 'ESME'),

    #MDP : alex.johnson
    ( 'alex.johnson','19ed73555df05da1c7fc6cff315cb562a13f7ff67a5ea361c6446dfe6d7eea12' , 'alex', 'johnson', 'alex.johnson@example.com', '+33604070146', 'Recruiter', 'Pending', 'HEC');

    INSERT INTO applicationuser (userId, login, password, firstname, lastname, email, phone, role)
VALUES
    (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Admin', 'Test', 'admin@test.com', '', 'Admin'),
    (2, 'recruiter', '92d46204d8e9aeb3b37873c794348b3132ab9c9f33a89f2063c46180914c0104', 'Recruteur1', 'Test',
     'recruteur@test.com', '', 'Recruiter'),
    (3, 'teacher', '1057a9604e04b274da5a4de0c8f4b4868d9b230989f8c8c6a28221143cc5a755', 'Enseignant', 'Test',
     'teacher@test.com', '', 'Teacher'),

    (4, 'recruiter2', '92d46204d8e9aeb3b37873c794348b3132ab9c9f33a89f2063c46180914c0104', 'Recruteur2', 'Test',
     'recruteur@test.com', '', 'Recruiter'),
    (5, 'recruiter3', '92d46204d8e9aeb3b37873c794348b3132ab9c9f33a89f2063c46180914c0104', 'Recruteur3', 'Test',
     'recruteur@test.com', '', 'Recruiter'),
    (6, 'recruiter4', '92d46204d8e9aeb3b37873c794348b3132ab9c9f33a89f2063c46180914c0104', 'Recruteur4', 'Test',
     'recruteur@test.com', '', 'Recruiter'),
    (7, 'recruiter5', '92d46204d8e9aeb3b37873c794348b3132ab9c9f33a89f2063c46180914c0104', 'Recruteur5', 'Test',
     'recruteur@test.com', '', 'Recruiter');



INSERT INTO recruiter (recruiterId, schoolName)
VALUES
    (2, 'EFREI'),
    (4, 'MINE'),
    (5, 'Polytech'),
    (6, 'ESME'),
    (7, 'HEC');

INSERT INTO teacher (teacherId)
VALUES (3);

INSERT INTO need(needId, recruiterId, schoolName, contractType, subject, requirements, timePeriod, notes) VALUES
-- Besoins pour École primaire A
(1, 2,'EFREI', 'Temporary', 'Mathématiques', 'Licence en mathématiques requise', '2023-01-01 to 2023-06-30', 'Besoin urgent'),
(2, 2,'EFREI', 'Temporary', 'Français', 'Expérience dans l\'enseignement du français', '2023-02-15 to 2023-05-15', 'Besoin ponctuel'),
(3, 2,'EFREI', 'Continous', 'Sciences', 'Master en sciences requis', '', 'Poste permanent'),
(4, 2,'EFREI', 'Any', 'Éducation physique', 'Expérience dans l\'enseignement de l\'éducation physique', '', 'Flexibilité requise'),
(5, 2,'EFREI', 'Temporary', 'Arts', 'Expérience dans l\'enseignement des arts visuels', '2023-05-20 to 2023-08-20', 'Besoin temporaire'),

-- Besoins pour Collège B
(6, 4,'MINE', 'Temporary', 'Histoire-Géographie', 'Licence en histoire-géographie requise', '2023-02-01 to 2023-06-30', 'Besoin ponctuel'),
(7, 4,'MINE', 'Continous', 'Mathématiques', 'Master en mathématiques requis', '', 'Poste permanent'),
(8, 4,'MINE', 'Any', 'Anglais', 'Certification d\'enseignement de l\'anglais', '', 'Poste flexible'),
(9, 4,'MINE', 'Temporary', 'Sciences', 'Expérience dans l\'enseignement des sciences', '2023-05-20 to 2023-08-20', 'Besoin temporaire'),
(10,4,'MINE', 'Temporary', 'Musique', 'Diplôme en éducation musicale requis', '2023-06-01 to 2023-06-30', 'Besoin ponctuel'),


-- Besoins pour Lycée C
(11,5, 'Polytech', 'Temporary', 'Informatique', 'Expérience dans l\'enseignement de l\'informatique', '2023-01-10 to 2023-06-10', 'Besoin ponctuel'),
(12,5, 'Polytech', 'Continous', 'Langues étrangères', 'Certification d\'enseignement des langues étrangères', '', 'Poste permanent'),
(13,5, 'Polytech', 'Any', 'Philosophie', 'Master en philosophie requis', '', 'Poste flexible'),
(14,5, 'Polytech', 'Temporary', 'Arts plastiques', 'Diplôme en arts plastiques requis', '2023-04-10 to 2023-07-10', 'Besoin temporaire'),
(15,5, 'Polytech', 'Continous', 'Économie', 'Licence en économie requise', '', 'Besoin urgent'),

-- Besoins pour Lycée C
(16, 6,'ESME', 'Temporary', 'Informatique', 'Expérience dans l\'enseignement de l\'informatique', '2023-01-10 to 2023-06-10', 'Besoin ponctuel'),
(17, 6,'ESME', 'Continous', 'Langues étrangères', 'Certification d\'enseignement des langues étrangères', '', 'Poste permanent'),
(18, 6,'ESME', 'Any', 'Philosophie', 'Master en philosophie requis', '2023-03-15 to 2023-09-15', 'Poste flexible'),
(19, 6,'ESME', 'Temporary', 'Arts plastiques', 'Diplôme en arts plastiques requis', '2023-04-10 to 2023-07-10', 'Besoin temporaire'),
(20, 6,'ESME', 'Temporary', 'Économie', 'Licence en économie requise', '2023-05-20 to 2023-08-20', 'Besoin urgent'),

-- Besoins pour Lycée C
(21, 7,'HEC', 'Temporary', 'Informatique', 'Expérience dans l\'enseignement de l\'informatique', '2023-01-10 to 2023-06-10', 'Besoin ponctuel'),
(22, 7,'HEC', 'Continous', 'Langues étrangères', 'Certification d\'enseignement des langues étrangères', '', 'Poste permanent'),
(23, 7,'HEC', 'Any', 'Philosophie', 'Master en philosophie requis', '2023-03-15 to 2023-09-15', 'Poste flexible'),
(24, 7,'HEC', 'Temporary', 'Arts plastiques', 'Diplôme en arts plastiques requis', '2023-04-10 to 2023-07-10', 'Besoin temporaire'),
(25, 7,'HEC', 'Temporary', 'Économie', 'Licence en économie requise', '2023-05-20 to 2023-08-20', 'Besoin urgent');


COMMIT;