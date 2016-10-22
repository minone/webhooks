--# Webhooks Database PostgreSQL Script #

CREATE TABLE destination (
  dest_id_destination VARCHAR(36)   NOT NULL,
  dest_tx_url         VARCHAR(2000) NOT NULL,
  dest_tx_secret      VARCHAR(36)   NOT NULL
);

ALTER TABLE destination ADD CONSTRAINT pk_dest PRIMARY KEY (dest_id_destination);
ALTER TABLE destination ADD CONSTRAINT uk_dest_tx_url UNIQUE (dest_tx_url);
ALTER TABLE destination ADD CONSTRAINT uk_dest_tx_secret UNIQUE (dest_tx_secret);