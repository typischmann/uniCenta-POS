/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  bjyustb
 * Created: Dec 11, 2015
 */

create table CLIENTS (
    ID VARCHAR NOT NULL,
    CODE INTEGER NOT NULL,
    NAME VARCHAR NOT NULL,
    NOTE VARCHAR,
    PRIMARY KEY (ID)
);

INSERT INTO CLIENTS(ID, CODE, NAME, NOTE)
VALUES('100', 100, 'System', 'System Client');

INSERT INTO CLIENTS(ID, CODE, NAME, NOTE)
VALUES('200', 200, 'Partner', 'Partner Client');


