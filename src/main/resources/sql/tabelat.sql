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
CREATE TABLE sherbimet(
id SERIAL PRIMARY KEY,
emri VARCHAR(100) NOT NULL,
pershkrimi VARCHAR(500),
çmimi DECIMAL(10,2) DEFAULT 0 CHECK (çmimi>=0)
)

/*
CREATE TABLE Veturat (
    vetura_id SERIAL PRIMARY KEY,
    prodhuesi VARCHAR(50) NOT NULL,
    modeli VARCHAR(50) NOT NULL,
    viti_prodhimit INT CHECK (viti_prodhimit >= 1900 AND viti_prodhimit <= EXTRACT(YEAR FROM CURRENT_DATE)),
    ngjyra VARCHAR(30),
    cmimi DECIMAL(10,2) CHECK (cmimi >= 0),
    gjendja VARCHAR(20) CHECK (gjendja IN ('E re', 'E përdorur')),
    kilometrazha INT CHECK (kilometrazha >= 0),
    tipi_karburant VARCHAR(20) CHECK (tipi_karburant IN ('Benzinë', 'Naftë', 'Elektrik', 'Hibrid'))
);
*/

/*
CREATE TABLE Porosite (
    porosi_id SERIAL PRIMARY KEY,
    kid INTEGER NOT NULL,
    vetura_id INTEGER NOT NULL,
    cmimi_ofruar NUMERIC(10, 2) CHECK (cmimi_ofruar > 0),
    statusi_porosise VARCHAR(20) CHECK (statusi_porosise IN ('Ne pritje', 'Ne proces', 'E kompletuar', 'E refuzuar')),
    FOREIGN KEY (kid) REFERENCES Klientet(kid),
    FOREIGN KEY (vetura_id) REFERENCES Veturat(vetura_id)
);
*/


CREATE TABLE Punetoret (
    punetor_id SERIAL PRIMARY KEY,
    emri VARCHAR(50) NOT NULL,
    mbiemri VARCHAR(50) NOT NULL,
    pozita VARCHAR(50) NOT NULL,
    telefoni VARCHAR(15) UNIQUE,
    email VARCHAR(100) UNIQUE,
    paga DECIMAL(10,2) CHECK (paga >= 0),
    data_punesimit DATE NOT NULL
);

/*
CREATE TABLE Garancite (
      id SERIAL PRIMARY KEY,
      v_id INT REFERENCES Veturat(vetura_id),
      klienti_id INT REFERENCES Klientet(kid),
      lloji_garancise VARCHAR(10) CHECK (lloji_garancise IN ('6 muaj', '1 vit', '2 vite')),
      data_fillimit DATE,
      data_mbarimit DATE
  );
*/