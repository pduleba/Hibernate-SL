DROP TABLE HIBERNATE.T_LARGE_CAR;
DROP TABLE HIBERNATE.T_SMALL_CAR;
DROP TABLE HIBERNATE.T_SPORT_CAR;
DROP TABLE HIBERNATE.T_CAR;
DROP SEQUENCE HIBERNATE.CAR_SEQ;

CREATE TABLE HIBERNATE.T_CAR (
  ID								    NUMBER(19),
  CLASS_NAME_DISCRIMINATOR			    VARCHAR2(100 CHAR),
  NAME								    VARCHAR2(100 CHAR),
  DATE_ID							    VARCHAR2(100 CHAR),
  CONSTRAINT PK_CAR PRIMARY KEY (ID)
);

CREATE TABLE HIBERNATE.T_LARGE_CAR (
  ID								    NUMBER(19),
  PART_SIZE								NUMBER(19),
  PART_WEIGHT							NUMBER(19),
  CONSTRAINT PK_LARGE_CAR PRIMARY KEY (ID),
  CONSTRAINT FK_LARGE_CAR FOREIGN KEY (ID) REFERENCES T_CAR(ID) 
);

CREATE TABLE HIBERNATE.T_SMALL_CAR (
  ID								    NUMBER(19),
  PART_NAME					    VARCHAR2(100 CHAR),
  CONSTRAINT PK_SMALL_CAR PRIMARY KEY (ID),
  CONSTRAINT FK_SMALL_CAR FOREIGN KEY (ID) REFERENCES T_CAR(ID) 
);

CREATE TABLE HIBERNATE.T_SPORT_CAR (
  ID								    NUMBER(19),
  CONSTRAINT PK_SPORT_CAR PRIMARY KEY (ID),
  CONSTRAINT FK_SPORT_CAR FOREIGN KEY (ID) REFERENCES T_CAR(ID) 
);

CREATE SEQUENCE HIBERNATE.CAR_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
