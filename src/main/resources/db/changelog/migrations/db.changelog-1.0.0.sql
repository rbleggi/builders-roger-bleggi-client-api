--liquibase formatted sql
--changeset roger.bleggi:1
CREATE TABLE client
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(100) NOT NULL,
    birth_date DATE         NOT NULL,
    PRIMARY KEY (id)
);