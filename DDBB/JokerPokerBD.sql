CREATE DATABASE JokerPokerDB;
USE JokerPokerDB;

CREATE TABLE Usuarios (
    userName varchar(25),
    passwd varchar (25),
    name varchar(25),
    balance double,
    esAdmin boolean,
    PRIMARY KEY (userName)
    
    );

CREATE TABLE Partidas (
    id varchar(25),
    userWins boolean,
    fecha date,
    userName varchar(25),
    PRIMARY KEY (id),
    FOREIGN KEY (userName) REFERENCES Usuarios (userName)
     
    );