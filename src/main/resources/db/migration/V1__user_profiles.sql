CREATE TABLE user_profiles (
    id          UUID         PRIMARY KEY,
    display_name VARCHAR(255),
    email       VARCHAR(255) NOT NULL,
    phone       VARCHAR(20),
    city        VARCHAR(100),
    bio         TEXT,
    photo_url   VARCHAR(500),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL
);
