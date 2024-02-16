CREATE TABLE IF NOT EXISTS listings(
    listing_id UUID PRIMARY KEY NOT NULL,
    title VARCHAR(100) NOT NULL ,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(20) NOT NULL ,
    county VARCHAR(30)NOT NULL ,
    city VARCHAR(30) NOT NULL,
    media VARCHAR(255)NOT NULL,
    user_id UUID REFERENCES users(user_id)
)