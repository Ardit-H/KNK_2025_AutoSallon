/*
CREATE TABLE Klientet(
    id SERIAL PRIMARY KEY,
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
    id SERIAL PRIMARY KEY,
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
    id SERIAL PRIMARY KEY,
    kid INTEGER NOT NULL,
    veturaId INTEGER NOT NULL,
    cmimiOfruar NUMERIC(10, 2) CHECK (cmimi_ofruar > 0),
    statusiPorosise VARCHAR(20) CHECK (statusi_porosise IN ('Ne pritje', 'Ne proces', 'E kompletuar', 'E refuzuar')),
    FOREIGN KEY (kid) REFERENCES Klientet(id),
    FOREIGN KEY (veturaId) REFERENCES Veturat(id)
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
*/


CREATE TABLE Shitjet (
       shitje_id SERIAL PRIMARY KEY,
	   kid INTEGER NOT NULL,
	   vetura_id INTEGER NOT NULL ,
	   punetor_id INTEGER NOT NULL,
	   data_shitjes DATE NOT NULL,
	   cmimi_final DECIMAL(10,2) CHECK (cmimi_final > 0),
	   FOREIGN KEY (kid) REFERENCES Klientet(kid),
	   FOREIGN KEY (vetura_id) REFERENCES Veturat(vetura_id),
	   FOREIGN KEY (punetor_id) REFERENCES Punetoret(punetor_id)
);

CREATE TABLE faturat(
    id SERIAL PRIMARY KEY,
    shitje_id INTEGER  NOT NULL,
    dataFatures DATE NOT NULL,
    shumaTotale NUMERIC(10,2) NOT NULL CHECK (shumaTotale >= 0);
    llojiPageses TEXT NOT NULL CHECK (llojiPageses IN('CASH', 'KARTE', 'BANK', 'TJETER')),
    FOREIGN KEY (shitje_id) REFERENCES Shitjet(shitje_id) ON DELETE CASCADE
);
CREATE TABLE Vleresimet(
    vleresimi_id SERIAL PRIMARY KEY,
    klienti_id INT NOT NULL,
    vetura_id INT NOT NULL,
    vleresimi INT CHECK (vleresimi BETWEEN 1 AND 5),
    komenti TEXT,
    data_vleresimit DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY(klienti_id) REFERENCES Klientet(id) ON DELETE CASCADE,
    FOREIGN KEY(vetura_id) REFERENCES Veturat(vetura_id) ON DELETE CASCADE
);
*/

/*
CREATE TABLE testDrives (
    id SERIAL PRIMARY KEY,
    kid INT NOT NULL,
    vetura_id INT NOT NULL,
    status VARCHAR(50),
    feedback TEXT,
    duration INT,
    location VARCHAR(255),
    CONSTRAINT fk_klient FOREIGN KEY (kid)
        REFERENCES Klientet(kid) ON DELETE CASCADE,
    CONSTRAINT fk_vetura FOREIGN KEY (vetura_id)
        REFERENCES Veturat(vetura_id) ON DELETE CASCADE
);
*/
/*
CREATE TABLE rezervimet (
    rezervimi_id SERIAL PRIMARY KEY,
    klienti_id INTEGER REFERENCES Klientet(kid),
    vetura_id INTEGER REFERENCES Veturat(vetura_id),
    data_rezervimit DATE NOT NULL,
    statusi VARCHAR(20) CHECK (statusi IN ('aktiv', 'anuluar', 'etj.'))
);*/

CREATE TABLE Riparimet (
    id SERIAL PRIMARY KEY,
    veturaId INTEGER NOT NULL,
    pershkrimi TEXT NOT NULL,
    statusi VARCHAR(20) CHECK (statusi IN ('Në përparim', 'Nuk mund të riparohet', 'E rregulluar')) NOT NULL,
    kostoRiparimit NUMERIC(10,2) NOT NULL,
    FOREIGN KEY (veturaId) REFERENCES veturat(id)
);

CREATE TABLE Pagesat (
    id SERIAL PRIMARY KEY,
    porosiaId INTEGER NOT NULL,
    metodaPageses VARCHAR(20) CHECK (metodaPageses IN ('KARTELE', 'CASH', 'KREDI', 'TJETER')),
    shuma NUMERIC(10, 2) NOT NULL CHECK (shuma >= 0),
    dataPageses DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (porosiaId) REFERENCES Porosite(id) ON DELETE CASCADE
);

CREATE TABLE Ofertat (
    id SERIAL PRIMARY KEY,
    veturaId INT NOT NULL,
    zbritja DECIMAL(5,2) CHECK (Zbritja >= 0),
    cmimiFinal DECIMAL(10,2) CHECK (CmimiFinal >= 0),
    dataFillimit DATE NOT NULL,
    dataMbarimit DATE NOT NULL,
    FOREIGN KEY (veturaId) REFERENCES Veturat(id) ON DELETE CASCADE
);