START TRANSACTION;

DROP TABLE Registration;
DROP TABLE ApplicationUser;
DROP TABLE School;
DROP TABLE Teacher;
DROP TABLE Refs;
DROP TABLE Recruiter;
DROP TABLE Experience;
DROP TABLE Disponibilities;
DROP TABLE Need;
DROP TABLE Application;

CREATE TABLE Registration (
    login VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(256) NOT NULL,
    name VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    schoolId INT,
    role ENUM ('Teacher','Recruiter') NOT NULL,
    status ENUM('Pending', 'Accepted', 'Refused') NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (schoolId) REFERENCES School(schoolId)
);

CREATE TABLE ApplicationUser (
    userId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    role ENUM ('Teacher','Recruiter','Admin') NOT NULL,
    name VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    phone VARCHAR(20) 
);
CREATE TABLE School (
    schoolId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    officialName VARCHAR(100)
);
CREATE TABLE Teacher(
    teacherId INT PRIMARY KEY NOT NULL,
    subjectExpertise VARCHAR(100),
    skills VARCHAR(100),
    academicCertification VARCHAR(100),
    otherInformations TEXT,
    interestedInSchool INT,
    FOREIGN KEY (teacherId) REFERENCES ApplicationUser(userId),
    FOREIGN KEY (interestedInSchool) REFERENCES School(schoolId)
);

CREATE TABLE Refs(
    teacherId INT NOT NULL,
    schoolId INT NOT NULL,
    PRIMARY KEY(teacherId,schoolId),
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (schoolId) REFERENCES School(schoolId)
);

CREATE TABLE Recruiter(
    recruiterId INT PRIMARY KEY NOT NULL,
    recruitingMethod VARCHAR(100),
    recruitingTools VARCHAR(100),
    schoolId INT,
    FOREIGN KEY (recruiterId) REFERENCES ApplicationUser(userId),
    FOREIGN KEY (schoolId) REFERENCES School(schoolId)
);


CREATE TABLE Experience (
    startingDate DATE NOT NULL,
    endingDate DATE NOT NULL,
    teacherId INT NOT NULL,
    schoolId INT NOT NULL,
    PRIMARY KEY (startingDate, endingDate,teacherId, schoolId),
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (schoolId) REFERENCES School(schoolId)
);


CREATE TABLE Disponibilities (
    teacherId INT NOT NULL,
    startDateTime DATETIME NOT NULL,
    endDateTime DATETIME NOT NULL,
    PRIMARY KEY (teacherId, startDateTime, endDateTime), 
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId)
    
);

CREATE TABLE Need (
    needId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    contractType VARCHAR(50),
    subject VARCHAR(100),
    teachingMethod VARCHAR(100),
    schoolId INT NOT NULL,
    recruiterId INT,
    preferedCandidate INT,
    workingTime INT,
    description TEXT,
    FOREIGN KEY (recruiterId) REFERENCES Recruiter(recruiterId),
    FOREIGN KEY (schoolId) REFERENCES School(schoolId),
    FOREIGN KEY (preferedCandidate) REFERENCES Teacher(teacherId)
    
);
CREATE TABLE Application (
    teacherId INT NOT NULL,
    needId INT NOT NULL,
    desiredLevel VARCHAR(100),
    status ENUM('In progress', 'Accepted', 'Refused') NOT NULL,
    isValidatedByTeacher BOOLEAN,
    isValidatedByRecruiter BOOLEAN,
    PRIMARY KEY (teacherId,needId),
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (needId) REFERENCES Need(needId)
);

INSERT INTO `applicationuser` (`login`, `password`, `role`, `name`, `mail`, `phone`) VALUES
    # Mdp : ImASuperAdmin
    ('adminTest', '49a02abc531d047c7596bcdd3657e213db6d5d2972ca44d7699ea4accc1827c2', 'Admin', 'MYTZ', 'example@efrei.net', NULL),
    # Mdp : Teacher1234
    ('teacherTest', '2c1714cf95b64a23d5a0d9720fb078d3af6acc0cdf23afdf9797dc022d77bdc4', 'Teacher', 'Jacques Augustin', 'example@efrei.net', NULL);

COMMIT;