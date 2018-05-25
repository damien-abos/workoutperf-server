-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql
-- Généré le :  mer. 23 mai 2018 à 21:02
-- Version du serveur :  10.2.14-MariaDB-10.2.14+maria~jessie
-- Version de PHP :  7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de données :  `workoutperf`
--

-- --------------------------------------------------------

--
-- Structure de la table `acl`
--

DROP TABLE IF EXISTS `acl`;
CREATE TABLE `acl` (
  `id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `acl`
--

INSERT INTO `acl` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8);

-- --------------------------------------------------------

--
-- Structure de la table `acl_acl_entries`
--

DROP TABLE IF EXISTS `acl_acl_entries`;
CREATE TABLE `acl_acl_entries` (
  `acl_id` int(11) NOT NULL,
  `acl_entries_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE `acl_class` (
  `collection` varchar(255) NOT NULL,
  `acl_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE `acl_entry` (
  `id` int(11) NOT NULL,
  `permission` int(11) NOT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest`
--

DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contest`
--

INSERT INTO `contest` (`id`, `description`, `name`, `begin`, `end`, `acl_id`) VALUES
('c1', '', 'contest1', '2000-01-01 00:00:00', '2100-12-31 23:59:59', 1);

-- --------------------------------------------------------

--
-- Structure de la table `contestposition_workoutposition`
--

DROP TABLE IF EXISTS `contestposition_workoutposition`;
CREATE TABLE `contestposition_workoutposition` (
  `workoutposition_id` varchar(255) NOT NULL,
  `contestposition_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_divisions`
--

DROP TABLE IF EXISTS `contest_divisions`;
CREATE TABLE `contest_divisions` (
  `contest_id` varchar(255) NOT NULL,
  `division_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contest_divisions`
--

INSERT INTO `contest_divisions` (`contest_id`, `division_id`) VALUES
('c1', 'b7fe6b39-0c70-4a19-9c90-97a7edc7d46a'),
('c1', 'f8d89519-596a-4f47-a13d-3fe67f11621d');

-- --------------------------------------------------------

--
-- Structure de la table `contest_judges`
--

DROP TABLE IF EXISTS `contest_judges`;
CREATE TABLE `contest_judges` (
  `contest_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_leaderboard`
--

DROP TABLE IF EXISTS `contest_leaderboard`;
CREATE TABLE `contest_leaderboard` (
  `id` varchar(255) NOT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `contest_id` varchar(255) DEFAULT NULL,
  `division_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_members`
--

DROP TABLE IF EXISTS `contest_members`;
CREATE TABLE `contest_members` (
  `contest_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_organizers`
--

DROP TABLE IF EXISTS `contest_organizers`;
CREATE TABLE `contest_organizers` (
  `contest_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_position`
--

DROP TABLE IF EXISTS `contest_position`;
CREATE TABLE `contest_position` (
  `id` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `leaderboard_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_workouts`
--

DROP TABLE IF EXISTS `contest_workouts`;
CREATE TABLE `contest_workouts` (
  `contest_id` varchar(255) NOT NULL,
  `workouts_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contest_workouts`
--

INSERT INTO `contest_workouts` (`contest_id`, `workouts_id`) VALUES
('c1', 'w1');

-- --------------------------------------------------------

--
-- Structure de la table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
CREATE TABLE `group_members` (
  `group_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(9),
(9);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `people_group`
--

DROP TABLE IF EXISTS `people_group`;
CREATE TABLE `people_group` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `people_group`
--

INSERT INTO `people_group` (`id`, `name`, `acl_id`) VALUES
('b7fe6b39-0c70-4a19-9c90-97a7edc7d46a', 'Avengers', 5),
('f8d89519-596a-4f47-a13d-3fe67f11621d', 'X-men', 6);

-- --------------------------------------------------------

--
-- Structure de la table `performance`
--

DROP TABLE IF EXISTS `performance`;
CREATE TABLE `performance` (
  `id` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `athlete_id` varchar(255) DEFAULT NULL,
  `judge_id` varchar(255) DEFAULT NULL,
  `workout_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `performance`
--

INSERT INTO `performance` (`id`, `comment`, `date`, `duration`, `score`, `acl_id`, `athlete_id`, `judge_id`, `workout_id`) VALUES
('a68f6f7e-e277-49d3-8042-f64b68907a02', '', '2018-05-23 22:57:00', 0, 10, 7, 'dams@example.com', NULL, 'w1'),
('e2e60f53-47c9-46c3-a8b8-dbedad4bd0a1', '', '2018-05-23 22:57:00', 0, 20, 8, 'ln@example.com', NULL, 'w1');

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `birthday`, `gender`, `name`, `acl_id`) VALUES
('dams@example.com', '1981-02-10', 'MALE', 'damien', 3),
('ln@example.com', '1980-07-19', 'FEMALE', 'Hélène', 4);

-- --------------------------------------------------------

--
-- Structure de la table `workout`
--

DROP TABLE IF EXISTS `workout`;
CREATE TABLE `workout` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `contest_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `workout`
--

INSERT INTO `workout` (`id`, `description`, `name`, `begin`, `end`, `type`, `acl_id`, `contest_id`) VALUES
('w1', '', 'wod1', '2000-01-01 00:00:00', '2100-12-31 23:59:59', 'SCORE_ASC_TIME_ASC', 2, 'c1');

-- --------------------------------------------------------

--
-- Structure de la table `workout_leaderboard`
--

DROP TABLE IF EXISTS `workout_leaderboard`;
CREATE TABLE `workout_leaderboard` (
  `id` varchar(255) NOT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `division_id` varchar(255) DEFAULT NULL,
  `workout_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `workout_position`
--

DROP TABLE IF EXISTS `workout_position`;
CREATE TABLE `workout_position` (
  `id` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `leaderboard_id` varchar(255) DEFAULT NULL,
  `performance_id` varchar(255) DEFAULT NULL,
  `workout_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `acl`
--
ALTER TABLE `acl`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `acl_acl_entries`
--
ALTER TABLE `acl_acl_entries`
  ADD PRIMARY KEY (`acl_id`,`acl_entries_id`),
  ADD UNIQUE KEY `UK_dchbahj0xrgyajoq0vg15qjcp` (`acl_entries_id`);

--
-- Index pour la table `acl_class`
--
ALTER TABLE `acl_class`
  ADD PRIMARY KEY (`collection`),
  ADD KEY `FK3ui760y1fft80h0hcp377hyi3` (`acl_id`);

--
-- Index pour la table `acl_entry`
--
ALTER TABLE `acl_entry`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf6ccd2xcklql32hhdj0n7w7ti` (`acl_id`);

--
-- Index pour la table `contest`
--
ALTER TABLE `contest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKowxhfoyhe6t57ev0r1ff1cpx4` (`acl_id`);

--
-- Index pour la table `contestposition_workoutposition`
--
ALTER TABLE `contestposition_workoutposition`
  ADD PRIMARY KEY (`workoutposition_id`,`contestposition_id`),
  ADD KEY `FKpouv5v0t46jm5qcijly8ej5u7` (`contestposition_id`);

--
-- Index pour la table `contest_divisions`
--
ALTER TABLE `contest_divisions`
  ADD PRIMARY KEY (`contest_id`,`division_id`),
  ADD KEY `FKk1220b3fpxy4h14ejnvd5bbmf` (`division_id`);

--
-- Index pour la table `contest_judges`
--
ALTER TABLE `contest_judges`
  ADD PRIMARY KEY (`contest_id`,`person_id`),
  ADD KEY `FK17lha7u56ix5dhy3u79xh2tev` (`person_id`);

--
-- Index pour la table `contest_leaderboard`
--
ALTER TABLE `contest_leaderboard`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp9pai1q0xcycfy6fqnskoecwv` (`acl_id`),
  ADD KEY `FKek1finho3cvqeuv4g4tgfhxvg` (`contest_id`),
  ADD KEY `FK2wlesnnwfjls1yohumoctatsr` (`division_id`);

--
-- Index pour la table `contest_members`
--
ALTER TABLE `contest_members`
  ADD PRIMARY KEY (`contest_id`,`person_id`),
  ADD KEY `FKfr8p8mqt35hfndx9uwimw4g1g` (`person_id`);

--
-- Index pour la table `contest_organizers`
--
ALTER TABLE `contest_organizers`
  ADD PRIMARY KEY (`contest_id`,`person_id`),
  ADD KEY `FK1442jdih1fl9e3tyu909x32qk` (`person_id`);

--
-- Index pour la table `contest_position`
--
ALTER TABLE `contest_position`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa1q92yfc02x3v8vbu0kojqla` (`acl_id`),
  ADD KEY `FKjki559f873wr8sjldmt12qh7d` (`leaderboard_id`);

--
-- Index pour la table `contest_workouts`
--
ALTER TABLE `contest_workouts`
  ADD PRIMARY KEY (`contest_id`,`workouts_id`),
  ADD UNIQUE KEY `UK_kmtr9mt9sl09sa2q1pwg5tgbq` (`workouts_id`);

--
-- Index pour la table `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Index pour la table `group_members`
--
ALTER TABLE `group_members`
  ADD PRIMARY KEY (`group_id`,`person_id`),
  ADD KEY `FKsp30j3hgcgao226agar5ckvdt` (`person_id`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `people_group`
--
ALTER TABLE `people_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdn0omjvgoitym2j6y0eve30nk` (`acl_id`);

--
-- Index pour la table `performance`
--
ALTER TABLE `performance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKajvhssml7sa0i608srpnpnmos` (`acl_id`),
  ADD KEY `FK7ofsjqyyevd8orbqnvh3lf3j` (`athlete_id`),
  ADD KEY `FK41fbgs617lcngms01d52fia6x` (`judge_id`),
  ADD KEY `FKfa697vtnl0pt6nk0jyhko2tdl` (`workout_id`);

--
-- Index pour la table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsxbo9jh4r6st2scmsqrnlq6qd` (`acl_id`);

--
-- Index pour la table `workout`
--
ALTER TABLE `workout`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqpudy603jwy9igflsnmb382di` (`acl_id`),
  ADD KEY `FKpxybu4i0hnw6hlrvdheg74ybx` (`contest_id`);

--
-- Index pour la table `workout_leaderboard`
--
ALTER TABLE `workout_leaderboard`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq1wpc6263vwc5ayjp3raqblml` (`acl_id`),
  ADD KEY `FKmrki01c1wgfixi5ww4kiy8sd5` (`division_id`),
  ADD KEY `FK5hd8p9o7g2y76tokta605jxik` (`workout_id`);

--
-- Index pour la table `workout_position`
--
ALTER TABLE `workout_position`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn8eok42qiq5a702w6ysblyns9` (`leaderboard_id`),
  ADD KEY `FK5g6oe1dxr2j9amlf43n46qvfu` (`performance_id`),
  ADD KEY `FK4b5b4wgli9r1dutlthdj6681p` (`workout_id`);
COMMIT;
