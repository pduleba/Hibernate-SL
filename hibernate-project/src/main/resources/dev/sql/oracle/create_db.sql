DROP TABLE HIBERNATE.T_LARGE_CAR;
DROP TABLE HIBERNATE.T_SMALL_CAR;
DROP TABLE HIBERNATE.T_SPORT_CAR;
DROP SEQUENCE HIBERNATE.CAR_SEQ;

CREATE TABLE HIBERNATE.T_LARGE_CAR (
  ID								    NUMBER(19),
  PART_SIZE								NUMBER(19),
  PART_WEIGHT							NUMBER(19),
  LARGE_NAME						    VARCHAR2(100 CHAR),
  DATE_ID							    VARCHAR2(100 CHAR),
  CONSTRAINT PK_LARGE_CAR PRIMARY KEY (ID)
);

CREATE TABLE HIBERNATE.T_SMALL_CAR (
  ID								    NUMBER(19),
  PART_NAME							    VARCHAR2(100 CHAR),
  SMALL_NAME						    VARCHAR2(100 CHAR),
  DATE_ID							    VARCHAR2(100 CHAR),
  CONSTRAINT PK_SMALL_CAR PRIMARY KEY (ID)
);

CREATE TABLE HIBERNATE.T_SPORT_CAR (
  ID								    NUMBER(19),
  SPORT_NAME						    VARCHAR2(100 CHAR),
  DATE_ID							    VARCHAR2(100 CHAR),
  CONSTRAINT PK_SPORT_CAR PRIMARY KEY (ID)
);

CREATE SEQUENCE HIBERNATE.CAR_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
