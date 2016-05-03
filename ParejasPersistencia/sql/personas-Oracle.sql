CREATE TABLE personas(
id integer PRIMARY KEY,
nombre varchar(45),
edad integer,
altura float,
sexo char
);

CREATE SEQUENCE mi_sequence
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;