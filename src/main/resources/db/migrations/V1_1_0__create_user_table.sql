CREATE TABLE IF NOT EXISTS users(
    user_id UUID PRIMARY KEY NOT NULL ,
    email VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL ,
    account_type VARCHAR(50) NOT NULL ,
    phone VARCHAR(10)NOT NULL ,
    media VARCHAR(255) NOT NULL ,
    rating integer NOT NULL

)