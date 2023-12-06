CREATE TABLE IF NOT EXISTS message (
       id UUID PRIMARY KEY,
       author VARCHAR(255),
       content VARCHAR(1000)
       );
CREATE TABLE IF NOT EXISTS client (
        id UUID PRIMARY KEY,
        client_name VARCHAR(255),
        client_reference VARCHAR(255)
);

ALTER TABLE message ADD COLUMN client_id UUID;

ALTER TABLE message ADD CONSTRAINT fk_message_client FOREIGN KEY (client_id) REFERENCES client(id);