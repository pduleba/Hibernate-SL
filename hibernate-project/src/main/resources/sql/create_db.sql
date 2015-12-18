DROP TABLE HIBERNATE.T_USER2ORDERS;
DROP TABLE HIBERNATE.T_ORDERS;
DROP TABLE HIBERNATE.T_USERS;
DROP SEQUENCE HIBERNATE.USERS_SEQ;
DROP SEQUENCE HIBERNATE.ORDERS_SEQ;

CREATE TABLE HIBERNATE.T_USERS (
  ID								NUMBER(15),
  NAME								VARCHAR2(100 CHAR),
  CONSTRAINT PK_USER PRIMARY KEY (ID)
);

CREATE TABLE HIBERNATE.T_ORDERS (
  ID								NUMBER(15),
  ORDER_DETAILS						VARCHAR2(100 CHAR) NOT NULL,
  CONSTRAINT PK_UDETAILS PRIMARY KEY (ID)
);

CREATE TABLE HIBERNATE.T_USER2ORDERS (
  ID_USER							NUMBER(15) NOT NULL,
  ID_ORDER							NUMBER(15) NOT NULL,
  CONSTRAINT UK_USER2ORDER UNIQUE (ID_ORDER),
  CONSTRAINT FK_USER2ORDER_USER FOREIGN KEY (ID_USER) REFERENCES T_USERS(ID) ENABLE,
  CONSTRAINT FK_USER2ORDER_ORDER FOREIGN KEY (ID_ORDER) REFERENCES T_ORDERS(ID) ENABLE
);

CREATE SEQUENCE HIBERNATE.USERS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE HIBERNATE.ORDERS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
