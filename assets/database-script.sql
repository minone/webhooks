--# Webhooks Database PostgreSQL Script #

CREATE TABLE destination (
  dest_id_destination VARCHAR(36)   NOT NULL,
  dest_tx_url         VARCHAR(2000) NOT NULL,
  dest_tx_secret      VARCHAR(36)   NOT NULL
);