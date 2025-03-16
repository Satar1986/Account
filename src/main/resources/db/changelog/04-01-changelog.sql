-- liquibase formatted sql

-- changeset Oleg:1741110375525-1
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

-- changeset Oleg:1741110375525-2
CREATE TABLE account
(
    id               UUID             NOT NULL,
    external_id      VARCHAR(50)      NOT NULL,
    client_id        VARCHAR(20)      NOT NULL,
    name_account     VARCHAR(20)      NOT NULL,
    sum              DOUBLE PRECISION,
    currency         VARCHAR(20)      NOT NULL,
    interest_rate    DOUBLE PRECISION NOT NULL,
    interest_is_paid VARCHAR(20)      NOT NULL,
    min_remainder    DOUBLE PRECISION NOT NULL,
    created_time     TIMESTAMP WITHOUT TIME ZONE,
    updated_time     TIMESTAMP WITHOUT TIME ZONE,
    state_id         INTEGER          NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

-- changeset Oleg:1741110375525-3
CREATE TABLE state
(
    id         INTEGER     NOT NULL,
    name_state VARCHAR(20) NOT NULL,
    CONSTRAINT pk_state PRIMARY KEY (id)
);

-- changeset Oleg:1741110375525-4
ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_STATE FOREIGN KEY (state_id) REFERENCES state (id);

