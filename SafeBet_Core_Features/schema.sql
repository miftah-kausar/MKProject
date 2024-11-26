
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    totalWinnings REAL DEFAULT 0,
    totalLosses REAL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS challenges (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT NOT NULL,
    betAmount REAL NOT NULL,
    status TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS challenge_participants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    challenge_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(challenge_id) REFERENCES challenges(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS driving_sessions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    startTime TEXT NOT NULL,
    endTime TEXT,
    performanceScore REAL DEFAULT 0.0,
    FOREIGN KEY(user_id) REFERENCES users(id)
);
