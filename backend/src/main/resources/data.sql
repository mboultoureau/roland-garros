DELETE FROM users WHERE email = "admin@email.com";

INSERT INTO users (email, password, username) VALUES
    ("admin@email.com", "$2a$10$iSa6.Wh9ZAghYsiL/xfR6u4mnw0UJ.Tc6nDSwo8OxxKZU8r/PQ27m", "admin");

DELETE FROM players;

INSERT INTO players (firstname, lastname, gender, birth_date,
    birth_place, nationality, weight, height) VALUES
    ("Iga", "Swiatek", 0, "20010531", "Varsosie, Pologne", "POL", null, 175),
    ("Carlos", "Alcaraz", 1, "20030505", "El Palmar, Murcia, Espagne", "ESP", 74, 182),
    ("Ons", "Jabeur", 0, "19940828", "Ksar Hellal, Tunisie", "TUN", 66, 167),
    ("Rafael", "Nadal", 1, "19860603", "Manacor, Espagne", "ESP", 84, 185);