CREATE TABLE USERS
(
	username VARCHAR(50),
	password VARCHAR(50),
	PRIMARY KEY (username)
);

CREATE TABLE PATIENT
(
	username VARCHAR(50),
	name VARCHAR(100),
	gender VARCHAR(10),
	dob DATE,
	address VARCHAR(100),
	phoneNum VARCHAR(12),
	PRIMARY KEY (username),
	CONSTRAINT FK_user 
	   FOREIGN KEY (username) REFERENCES USERS (username)
);

CREATE TABLE DIAGNOSES
(
	diagnosesID INTEGER,
	name VARCHAR(100),
	PRIMARY KEY (diagnosesID),
	CONSTRAINT UK_name UNIQUE (name)
);

CREATE TABLE SICKPATIENT
(
	diagnosesID INTEGER,
	username VARCHAR(50),
	diagnosesDate DATE,
	PRIMARY KEY (diagnosesID, username),
	FOREIGN KEY (diagnosesID) REFERENCES DIAGNOSES (diagnosesID),
	FOREIGN KEY (username) REFERENCES USERS (username)
);

CREATE TABLE SUPPORTSPATIENT
(
	pUsername VARCHAR(50),
	sUsername VARCHAR(50),
	authorizationDate DATE,
	type varchar(10)
	PRIMARY KEY (pUsername, sUsername),
	FOREIGN KEY (pUsername) REFERENCES USERS (username),
	FOREIGN KEY (sUsername) REFERENCES USERS (username)
);


CREATE TABLE INSTRUCTION
(
	instructionID INTEGER,
	description VARCHAR(4000),
	isMandatory VARCHAR(5),
	PRIMARY KEY (instructionID)
);

CREATE TABLE PATIENTINSTRUCTION
(
	username VARCHAR(50),
	instructionID INTEGER,
	PRIMARY KEY (username, instructionID),
	FOREIGN KEY (username) REFERENCES USERS (username),
	FOREIGN KEY (instructionID) REFERENCES INSTRUCTION (instructionID)
);

CREATE TABLE HEALTHINDICATOR
(
	indicatorID INTEGER,
	diagnosesID INTEGER,
	name VARCHAR(100),
	lowerLimit NUMBER(6,2),
	upperLimit NUMBER(6,2),
	visitFrequency INT,
	description VARCHAR(1000),
	PRIMARY KEY (indicatorID),
	CONSTRAINT UK_healthIndicator UNIQUE (diagnosesID, name),
	FOREIGN KEY (diagnosesID) REFERENCES DIAGNOSES (diagnosesID)
);

CREATE TABLE HEALTHOBSERVATION
( 
	observationID INTEGER,
	username VARCHAR(50),
	name VARCHAR(100),
	observationTime DATE,
	recordingTime DATE,
	value NUMBER(6,2),
	PRIMARY KEY (observationID)
);

CREATE TABLE ALERT
(
	alertID INTEGER,
	username VARCHAR(50),
	heathIndicator INTEGER,
	healthObservation INTEGER,
	issueDate DATE,
	message VARCHAR(4000),
	PRIMARY KEY (alertID),
	FOREIGN KEY (healthIndicator) REFERENCES HEALTHINDICATOR (indicatorID),
	FOREIGN KEY (healthObservation) REFERENCES HEALTHOBSERVATION (observationID),
	FOREIGN KEY (username) REFERENCES USERS (username)
);