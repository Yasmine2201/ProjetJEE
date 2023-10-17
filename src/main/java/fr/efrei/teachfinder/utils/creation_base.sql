START TRANSACTION;

CREATE DATABASE IF NOT EXISTS teach_finder_db;
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
    login VARCHAR(32) NOT NULL UNIQUE,
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
    role ENUM('ADMIN', 'Teacher', 'Recruiter') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE Teacher(
    teacherId INT NOT NULL PRIMARY KEY,
    experiences TEXT,
    skills VARCHAR(256),
    personnalInterests VARCHAR(256),
    schoolInterests VARCHAR(256),
    desiredLevels VARCHAR(64),
    contractType ENUM('Temporary', 'Continous') NOT NULL DEFAULT 'Temporary',
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
    schoolName VARCHAR(128) NOT NULL,
    contractType ENUM('Temporary', 'Continous') NOT NULL DEFAULT 'Temporary',
    subject VARCHAR(64) NOT NULL,
    requirements TEXT,
    timePeriod VARCHAR(32) NOT NULL,
    notes TEXT,
    FOREIGN KEY (schoolName) REFERENCES School(schoolName)
) ENGINE=InnoDB;

CREATE TABLE Candidature (
    teacherId INT NOT NULL,
    needId INT NOT NULL,
    recruiterId INT NOT NULL,
    schoolName VARCHAR(128) NOT NULL,
    createdOn DATETIME NOT NULL,
    isInitiatedByTeacher BOOLEAN NOT NULL DEFAULT TRUE,
    isValidatedByTeacher BOOLEAN NOT NULL DEFAULT FALSE,
    isValidatedByRecruiter BOOLEAN NOT NULL DEFAULT FALSE,
    status ENUM('Pending', 'Accepted', 'Refused') NOT NULL DEFAULT 'Pending',
    PRIMARY KEY (teacherId, needId),
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (needId) REFERENCES Need(needId),
    FOREIGN KEY (recruiterId) REFERENCES Recruiter(recruiterId),
    FOREIGN KEY (schoolName) REFERENCES School(schoolName)
) ENGINE=InnoDB;


INSERT INTO `applicationuser` (`login`, `password`, `role`, `firstname`, `lastname`, `email`) VALUES
    # Mdp : ImASuperAdmin
    ('adminTest', '49a02abc531d047c7596bcdd3657e213db6d5d2972ca44d7699ea4accc1827c2', 'Admin', 'MYTZ', 'BACB', 'example@efrei.net'),
    # Mdp : Teacher1234
    ('teacherTest', '2c1714cf95b64a23d5a0d9720fb078d3af6acc0cdf23afdf9797dc022d77bdc4', 'Teacher', 'Jacques', 'Augustin', 'example@efrei.net');

COMMIT;