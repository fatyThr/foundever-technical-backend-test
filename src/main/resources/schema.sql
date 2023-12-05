CREATE TABLE IF NOT EXISTS message (
       id UUID PRIMARY KEY,
       author VARCHAR(255),
       content VARCHAR(1000)
);