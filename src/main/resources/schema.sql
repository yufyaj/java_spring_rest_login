CREATE TABLE IF NOT EXISTS users(
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS sessionTb(
    session_id VARCHAR(20) PRIMARY KEY,
    user_id VARCHAR(50),
    start_date DATE,
    limit_date DATE
);
