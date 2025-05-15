CREATE DATABASE JokerPokerDB;
USE JokerPokerDB;

CREATE TABLE Usuarios (
    userName varchar(25) NOT NULL,
    passwd varchar (25),
    name varchar(25),
    balance double,
    esAdmin boolean,
    PRIMARY KEY (userName)
    
    );

CREATE TABLE Partidas (
    id varchar(25) NOT NULL,
    userWins boolean,
    fecha date,
    userName varchar(25) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userName) REFERENCES Usuarios (userName)
     
    );

INSERT INTO usuarios (userName, passwd, name, balance, esAdmin) VALUE ('admin', 'admin', 'administrador', 99999.99, true);