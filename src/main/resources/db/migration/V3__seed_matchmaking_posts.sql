-- Seed data: demo announcements for each intent
INSERT INTO matchmaking_posts (id, creator_user_id, creator_name, intent, sport, city, match_date, match_time, level, spots, note, status, created_at, updated_at)
VALUES
  ('a1000000-0000-0000-0000-000000000001','b0000000-0000-0000-0000-000000000001',
   'Karim B.','ORGANIZE','Football','Tunis',
   CURRENT_DATE + 5,'18:00','Intermediaire',4,
   'Match 5v5 sur terrain synthetique a Ariana. Ambiance decontractee, venez bien chausses !',
   'OPEN',NOW(),NOW()),

  ('a1000000-0000-0000-0000-000000000002','b0000000-0000-0000-0000-000000000002',
   'Sami T.','ORGANIZE','Football','Sfax',
   CURRENT_DATE + 3,'19:30','Debutant',6,
   'Match amical a Sfax, ideal pour debutants. Terrain reserve, besoin de completer l''equipe.',
   'OPEN',NOW(),NOW()),

  ('a1000000-0000-0000-0000-000000000003','b0000000-0000-0000-0000-000000000003',
   'Yasmine H.','ORGANIZE','Padel','Tunis',
   CURRENT_DATE + 7,'10:00','Intermediaire',2,
   'Cherche 2 joueurs pour completer notre partie de Padel au PlayZone Lac. Courts indoor.',
   'OPEN',NOW(),NOW()),

  ('a1000000-0000-0000-0000-000000000004','b0000000-0000-0000-0000-000000000004',
   'Ines M.','ORGANIZE','Tennis','Sousse',
   CURRENT_DATE + 10,'09:00','Avance',1,
   'Recherche un partenaire de double pour completer notre equipe en tournoi regional a Sousse.',
   'OPEN',NOW(),NOW()),

  ('a1000000-0000-0000-0000-000000000005','b0000000-0000-0000-0000-000000000005',
   'Nabil K.','ORGANIZE','Football','Tunis',
   CURRENT_DATE + 2,'20:00','Avance',3,
   'Match 7v7 organise a El Menzah. Niveau exige : avance. Arbitre present.',
   'OPEN',NOW(),NOW()),

  ('a1000000-0000-0000-0000-000000000006','b0000000-0000-0000-0000-000000000006',
   'Mariem S.','ORGANIZE','Padel','Monastir',
   CURRENT_DATE + 4,'17:00','Debutant',2,
   'Premiere organisation, ambiance sympa garantie ! Padel debutant a Monastir.',
   'OPEN',NOW(),NOW()),

  ('a2000000-0000-0000-0000-000000000001','b0000000-0000-0000-0000-000000000007',
   'Rami F.','TEAM','Football','Tunis',
   CURRENT_DATE,'00:00','Intermediaire',1,
   '[Milieu] Joueur regulier niveau intermediaire, cherche equipe serieuse pour championnat. Disponible soirs semaine.',
   'OPEN',NOW(),NOW()),

  ('a2000000-0000-0000-0000-000000000002','b0000000-0000-0000-0000-000000000008',
   'Tarek L.','TEAM','Football','Sfax',
   CURRENT_DATE,'00:00','Avance',1,
   '[Attaquant] Ex-joueur de club regional, cherche equipe competitive a Sfax.',
   'OPEN',NOW(),NOW()),

  ('a2000000-0000-0000-0000-000000000003','b0000000-0000-0000-0000-000000000009',
   'Houssem A.','TEAM','Padel','Tunis',
   CURRENT_DATE,'00:00','Intermediaire',1,
   '[Cote droit] Joueur padel 2 ans d''experience, cherche equipe pour tournoi mensuel PlayZone.',
   'OPEN',NOW(),NOW()),

  ('a2000000-0000-0000-0000-000000000004','b0000000-0000-0000-0000-000000000010',
   'Rim B.','TEAM','Tennis','Tunis',
   CURRENT_DATE,'00:00','Debutant',1,
   '[Double] Joueuse tennis debutante, cherche equipe pour doubles mixtes a Tunis.',
   'OPEN',NOW(),NOW()),

  ('a2000000-0000-0000-0000-000000000005','b0000000-0000-0000-0000-000000000011',
   'Oussama G.','TEAM','Football','Tunis',
   CURRENT_DATE,'00:00','Avance',1,
   '[Gardien] Gardien experimente, cherche equipe de niveau avance a Tunis pour saison complete.',
   'OPEN',NOW(),NOW()),

  ('a3000000-0000-0000-0000-000000000001','b0000000-0000-0000-0000-000000000012',
   'Leila M.','PARTNER','Padel','Tunis',
   CURRENT_DATE,'00:00','Intermediaire',1,
   '[Week-end - 2x/semaine] Joueuse padel niveau intermediaire, cherche partenaire feminine reguliere au Lac.',
   'OPEN',NOW(),NOW()),

  ('a3000000-0000-0000-0000-000000000002','b0000000-0000-0000-0000-000000000013',
   'Mehdi C.','PARTNER','Padel','Tunis',
   CURRENT_DATE,'00:00','Avance',1,
   '[Semaine soir - 2x/semaine] Joueur padel competitif, cherche binome serieux pour tournoi mensuel.',
   'OPEN',NOW(),NOW()),

  ('a3000000-0000-0000-0000-000000000003','b0000000-0000-0000-0000-000000000014',
   'Sara B.','PARTNER','Tennis','Sousse',
   CURRENT_DATE,'00:00','Debutant',1,
   '[Week-end - 1x/semaine] Debutante en tennis, cherche partenaire pour doubles detente a Sousse.',
   'OPEN',NOW(),NOW()),

  ('a3000000-0000-0000-0000-000000000004','b0000000-0000-0000-0000-000000000015',
   'Amine D.','PARTNER','Tennis','Tunis',
   CURRENT_DATE,'00:00','Intermediaire',1,
   '[Flexible - 1x/semaine] Joueur tennis 3 ans, cherche partenaire pour doubles reguliers a Tunis.',
   'OPEN',NOW(),NOW()),

  ('a3000000-0000-0000-0000-000000000005','b0000000-0000-0000-0000-000000000016',
   'Fatma T.','PARTNER','Padel','Monastir',
   CURRENT_DATE,'00:00','Intermediaire',1,
   '[Semaine matin - Occasionnel] Cherche binome padel a Monastir, disponible en matinee.',
   'OPEN',NOW(),NOW());
