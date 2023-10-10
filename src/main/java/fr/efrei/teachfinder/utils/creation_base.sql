CREATE TABLE Registration (
    login VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(256) NOT NULL,
    role ENUM ('Teacher','Recruiter') NOT NULL,
    status ENUM('Pending', 'Accepted', 'Refused') NOT NULL
);

CREATE TABLE ApplicationUser (
    userId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(256) NOT NULL,
    role ENUM ('Teacher','Recruiter','Admin') NOT NULL,
    name VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    webSite VARCHAR(100),
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
CREATE TABLE TeacherInterests(
    teacherId INT NOT NULL,
    schoolId INT NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId),
    FOREIGN KEY (schoolId) REFERENCES School(schoolId),
    PRIMARY KEY (teacherId, schoolId)
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






