DROP TABLE HIBERNATE.T_CAR;

CREATE TABLE HIBERNATE.T_CAR (
  ID								    NUMBER(19),
  NAME								    VARCHAR2(100 CHAR),
  DATE_ID							    VARCHAR2(100 CHAR),
  CONSTRAINT PK_CAR PRIMARY KEY (ID)
);