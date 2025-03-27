/*
CREATE TABLE Klientet(
kid SERIAL PRIMARY KEY,
emri VARCHAR(50) NOT NULL CHECK(char_length(emri)>=3),
mbiemri VARCHAR(50) NOT NULL CHECK(char_length(mbiemri)>=3),
email VARCHAR(100) UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9._]+\.[A-Za-z]{2,}$'),
nrtelefonit VARCHAR(15) CHECK(nrtelefonit ~ '^\+?[0-9]{7,15}$'),
adresa VARCHAR(200) CHECK(char_length(adresa)>=5),
data_regjistrimit TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
*/