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

CREATE TABLE School (
    schoolName VARCHAR(128) NOT NULL PRIMARY KEY,
    address VARCHAR(256),
    specializations VARCHAR(256)
) ENGINE=InnoDB;

CREATE TABLE Registration (
    registrationId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    firstname VARCHAR(32) NOT NULL,
    lastname VARCHAR(32) NOT NULL,
    email VARCHAR(64) NOT NULL,
    phone VARCHAR(16),
    role ENUM('Admin', 'Teacher', 'Recruiter') NOT NULL ,
    status ENUM('Accepted', 'Refused', 'Pending') NOT NULL DEFAULT 'PENDING',
    schoolName VARCHAR(128),
    FOREIGN KEY (schoolName) REFERENCES School(schoolName)
) ENGINE=InnoDB;

CREATE TABLE ApplicationUser (
    userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    firstname VARCHAR(32) NOT NULL,
    lastname VARCHAR(32) NOT NULL,
    email VARCHAR(64) NOT NULL,
    phone VARCHAR(16),
    role ENUM('Admin', 'Teacher', 'Recruiter') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE Teacher(
    teacherId INT NOT NULL PRIMARY KEY,
    experiences TEXT,
    skills VARCHAR(256),
    personnalInterests VARCHAR(256),
    schoolInterests VARCHAR(256),
    desiredLevels VARCHAR(64),
    contractType ENUM('Temporary', 'Continous', 'Any') NOT NULL DEFAULT 'Any',
    academicCertifications VARCHAR(256),
    otherInformations TEXT,
    recommendations VARCHAR(256),
    FOREIGN KEY (teacherId) REFERENCES ApplicationUser(userId)
) ENGINE=InnoDB;

CREATE TABLE Recruiter (
    recruiterId INT NOT NULL PRIMARY KEY,
    schoolName VARCHAR(128) NOT NULL,
    FOREIGN KEY (recruiterId) REFERENCES ApplicationUser(userId),
    FOREIGN KEY (schoolName) REFERENCES School(schoolName)
) ENGINE=InnoDB;

CREATE TABLE Disponibility (
    disponibilityId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teacherId INT NOT NULL,
    startDate DATETIME NOT NULL,
    endDate DATETIME NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId)
) ENGINE=InnoDB;

CREATE TABLE Evaluation (
    teacherId INT NOT NULL,
    schoolName VARCHAR(128) NOT NULL,
    rating TINYINT NOT NULL,
    comment TEXT,
    PRIMARY KEY (teacherId, schoolName),
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (schoolName) REFERENCES School(schoolName)
) ENGINE=InnoDB;

CREATE TABLE Need (
    needId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    recruiterId INT NOT NULL,
    schoolName VARCHAR(128) NOT NULL,
    contractType ENUM('Temporary', 'Continous', 'Any') NOT NULL DEFAULT 'Temporary',
    subject VARCHAR(64) NOT NULL,
    requirements TEXT,
    timePeriod VARCHAR(32),
    notes TEXT,
    FOREIGN KEY (schoolName) REFERENCES School(schoolName),
    FOREIGN KEY (recruiterId) REFERENCES Recruiter(recruiterId)
) ENGINE=InnoDB;

CREATE TABLE Candidature (
    teacherId INT NOT NULL,
    needId INT NOT NULL,
    schoolName VARCHAR(128) NOT NULL,
    createdOn DATETIME NOT NULL,
    isInitiatedByTeacher BOOLEAN NOT NULL DEFAULT TRUE,
    isValidatedByTeacher BOOLEAN NOT NULL DEFAULT FALSE,
    isValidatedByRecruiter BOOLEAN NOT NULL DEFAULT FALSE,
    status ENUM('Pending', 'Accepted', 'Refused') NOT NULL DEFAULT 'Pending',
    PRIMARY KEY (teacherId, needId),
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (needId) REFERENCES Need(needId),
    FOREIGN KEY (schoolName) REFERENCES School(schoolName)
) ENGINE=InnoDB;


INSERT INTO school (schoolName, address, specializations) VALUES
    ('EFREI', '30-32 Av. de la République, 94800 Villejuif', 'Informatique');

INSERT INTO applicationuser (userId, login, password, firstname, lastname, email, phone, role) VALUES
    (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Admin', 'Test', 'admin@test.com', '', 'Admin'),
    (2, 'recruiter', '92d46204d8e9aeb3b37873c794348b3132ab9c9f33a89f2063c46180914c0104', 'Recruteur', 'Test', 'recruteur@test.com', '', 'Recruiter'),
    (3, 'teacher', '1057a9604e04b274da5a4de0c8f4b4868d9b230989f8c8c6a28221143cc5a755', 'Enseignant', 'Test', 'teacher@test.com', '', 'Teacher');

INSERT INTO recruiter (recruiterId, schoolName) VALUES (2, 'EFREI');
INSERT INTO teacher (teacherId) VALUES (3);


COMMIT;