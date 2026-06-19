CREATE TABLE matchmaking_posts (
    id               UUID PRIMARY KEY,
    creator_user_id  UUID         NOT NULL,
    creator_name     VARCHAR(120) NOT NULL,
    intent           VARCHAR(20)  NOT NULL,
    sport            VARCHAR(20)  NOT NULL,
    city             VARCHAR(120) NOT NULL,
    match_date       DATE         NOT NULL,
    match_time       TIME         NOT NULL,
    level            VARCHAR(40)  NOT NULL,
    spots            INTEGER      NOT NULL,
    note             TEXT         NOT NULL,
    status           VARCHAR(20)  NOT NULL DEFAULT 'OPEN',
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_matchmaking_posts_status ON matchmaking_posts(status);
CREATE INDEX idx_matchmaking_posts_sport ON matchmaking_posts(sport);
CREATE INDEX idx_matchmaking_posts_intent ON matchmaking_posts(intent);
CREATE INDEX idx_matchmaking_posts_match_date ON matchmaking_posts(match_date);
