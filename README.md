# KNK_2025_AutoSallon
 Zhvillimi i sistemit interaktiv për menaxhimin e shitblejes së veturave në auto-sallone.

## Përshkrimi i Projektit
 - Ky projekt përfaqëson një aplikacion desktop për menaxhimin e një autosalloni, i zhvilluar në gjuhën programuese Java duke përdorur JavaFX për ndërfaqen grafike dhe PostgreSQL si sistemin e bazës së të dhënave. Projekti u realizua si pjesë e kursit "Komunikimi Njeri-Kompjuter" dhe përfshin të gjitha funksionalitetet e nevojshme për menaxhimin dhe operimin e plotë të një autosalloni, duke përfshirë: shitjet, rezervimet, riparimet, garancitë, pagesat, testimet, përdoruesit, adminët dhe shumë më tepër.

## Teknologjitë e përdorura
1. Java (JavaFX) – për zhvillimin e ndërfaqes grafike
2. PostgreSQL – për menaxhimin e të dhënave
3. FXML – për strukturimin vizual të pamjeve
4. Maven – për menaxhimin e varësive

## Struktura e Projektit
KNK_2025_AutoSallon/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── App/
│       │   ├── com.example.knk_2025_autosallon/
│       │   ├── controllers/
│       │   ├── CustomExceptions/
│       │   ├── Database/
│       │   ├── models.dto/           → 18 modelet për tabelat në DB
│       │   ├── repository/           → Repository për secilën tabelë
│       │   ├── services/             → Servicet për logjikën e biznesit
│       │   ├── Test/                 → Teste për repository & service
│       │   └── utils/                → Funksione ndihmëse (p.sh. enums, helpers)
│       │
│       └── resources/
│           ├── com.example.knk_2025_autosallon/
│           ├── Images/
│           ├── languages/            → Skedarët për përkthim (anglisht & shqip)
│           ├── sql/                  → Skripte për krijimin e tabelave
│           └── Views/                → FXML për çdo komponent UI
│
└── README.md 

## Modelet (DTO)
 - Janë implementuar 18 klasa model, secila duke përfaqësuar një tabelë në bazën e të dhënave:
1. Faturat
2. Garancia
3. Klientët
4. Lokacionet
5. Ofertat
6. Pagesat
7. Partnerët
8. Përdoruesit
9. Porositë
10. Punëtorët
11. Rezervimet
12. Riparimet
13. Shërbimet
14. Shitjet
15. Statistikë e shitjeve
16. Test Drives
17. Veturat
18. Vlerësimet
 - Secila prej këtyre klasave ka implementuar:
     - Create klasë – për input-in e të dhënave të reja
     - Update klasë – për përditësimin e të dhënave ekzistuese

## Logjika e Aplikacionit
# Repository Pattern
 - Për çdo model është krijuar një klasë repository me operacionet:
1. create
2. update
3. delete
4. findAll
5. findById
6. search (ne disa ku ishte e nevojshme)

# Service Layer
 - Secila repository ka përkatësinë e saj në paketën services/, ku bëhet logjika e ndërmjetme dhe menaxhimi i transaksioneve.

##  Controller-at dhe Pamjet
 - Për çdo komponent është ndërtuar një FXML i dedikuar dhe një kontroller Java:
1. OverallDashboardController – Paneli kryesor i faqes
2. AdminDashboardController – Paneli kryesor për administratorin
3. UserDashboardController – Paneli kryesor për përdoruesin
4. LoginController – Autentifikimi i përdoruesit
5. SignupController – Regjistrimi i përdoruesve të rinj
6. HelpUserController – Ndihmë për përdoruesit
7. HelpAdminController – Ndihmë për administratorët
8. AdminProfileController - Profili i administratorit
9. UserProfileController - Profili i user-it
10. ChangePasswordController - Ndryshim te password-it
 - Dhe 18 kontrollera për secilën njësi (klasë) të domain-it

## Testimet
 - Në paketën Test/ janë realizuar:
1. Teste për çdo repository (duke përdorur një DB të testimit)
2. Teste për çdo service
 - Testet sigurojnë që logjika e biznesit dhe qasja me DB të funksionojnë siç duhet.

## Multilingualiteti
 - Aplikacioni mbështet dy gjuhë:
1. Shqip
2. Anglisht
 - Me përdorimin e ResourceBundle, të gjitha FXML dhe kontrollet dinamike përkthehen automatikisht në gjuhën e përzgjedhur nga përdoruesi.

## Si ta ekzekutoni
 - Importoni projektin si Maven në IntelliJ IDEA
 - Sigurohuni që baza e të dhënave PostgreSQL është aktive dhe të dhënat janë krijuar
 - Ekzekutoni klasën App.java ose Main.java si aplikacion JavaFX

## Autorët
- Elsa Krasniqi
- Fahrije Gjokiqi
- Ardit Hyseni
- Dua Gashi
- Erzana Beqaj
- Albison Bekaj

