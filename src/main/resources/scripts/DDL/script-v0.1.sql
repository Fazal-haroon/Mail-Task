--start fazal - 17/07/2023

CREATE DATABASE `maildb`;

CREATE TABLE RECIPIENT
(
    id      INT          NOT NULL AUTO_INCREMENT,
    email   VARCHAR(256) NOT NULL,
    subject VARCHAR(256) NOT NULL,
    body    VARCHAR(256) NOT NULL,
    sent    VARCHAR(256) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE recipient;

--end fazal - 17/07/2023

