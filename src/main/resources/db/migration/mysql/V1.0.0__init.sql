-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql
-- Généré le :  mar. 22 mai 2018 à 23:03
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
CREATE TABLE IF NOT EXISTS `acl` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
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
(7);

-- --------------------------------------------------------

--
-- Structure de la table `acl_acl_entries`
--

DROP TABLE IF EXISTS `acl_acl_entries`;
CREATE TABLE IF NOT EXISTS `acl_acl_entries` (
  `acl_id` int(11) NOT NULL,
  `acl_entries_id` int(11) NOT NULL,
  PRIMARY KEY (`acl_id`,`acl_entries_id`),
  UNIQUE KEY `UK_dchbahj0xrgyajoq0vg15qjcp` (`acl_entries_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE IF NOT EXISTS `acl_class` (
  `collection` varchar(255) NOT NULL,
  `acl_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`collection`),
  KEY `FK3ui760y1fft80h0hcp377hyi3` (`acl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE IF NOT EXISTS `acl_entry` (
  `id` int(11) NOT NULL,
  `permission` int(11) NOT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf6ccd2xcklql32hhdj0n7w7ti` (`acl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest`
--

DROP TABLE IF EXISTS `contest`;
CREATE TABLE IF NOT EXISTS `contest` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowxhfoyhe6t57ev0r1ff1cpx4` (`acl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contest`
--

INSERT INTO `contest` (`id`, `description`, `name`, `begin`, `end`, `acl_id`) VALUES
('c1', '', 'contest-1', '2000-01-01 00:00:00', '2100-12-31 23:59:59', 1);

-- --------------------------------------------------------

--
-- Structure de la table `contestposition_workoutposition`
--

DROP TABLE IF EXISTS `contestposition_workoutposition`;
CREATE TABLE IF NOT EXISTS `contestposition_workoutposition` (
  `workoutposition_id` varchar(255) NOT NULL,
  `contestposition_id` varchar(255) NOT NULL,
  PRIMARY KEY (`workoutposition_id`,`contestposition_id`),
  KEY `FKpouv5v0t46jm5qcijly8ej5u7` (`contestposition_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_divisions`
--

DROP TABLE IF EXISTS `contest_divisions`;
CREATE TABLE IF NOT EXISTS `contest_divisions` (
  `contest_id` varchar(255) NOT NULL,
  `division_id` varchar(255) NOT NULL,
  PRIMARY KEY (`contest_id`,`division_id`),
  KEY `FKk1220b3fpxy4h14ejnvd5bbmf` (`division_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contest_divisions`
--

INSERT INTO `contest_divisions` (`contest_id`, `division_id`) VALUES
('c1', '50924f63-0e8c-4f89-9480-1353f7bbe0c6');

-- --------------------------------------------------------

--
-- Structure de la table `contest_judges`
--

DROP TABLE IF EXISTS `contest_judges`;
CREATE TABLE IF NOT EXISTS `contest_judges` (
  `contest_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  PRIMARY KEY (`contest_id`,`person_id`),
  KEY `FK17lha7u56ix5dhy3u79xh2tev` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_leaderboard`
--

DROP TABLE IF EXISTS `contest_leaderboard`;
CREATE TABLE IF NOT EXISTS `contest_leaderboard` (
  `id` varchar(255) NOT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `contest_id` varchar(255) DEFAULT NULL,
  `division_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp9pai1q0xcycfy6fqnskoecwv` (`acl_id`),
  KEY `FKek1finho3cvqeuv4g4tgfhxvg` (`contest_id`),
  KEY `FK2wlesnnwfjls1yohumoctatsr` (`division_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_members`
--

DROP TABLE IF EXISTS `contest_members`;
CREATE TABLE IF NOT EXISTS `contest_members` (
  `contest_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  PRIMARY KEY (`contest_id`,`person_id`),
  KEY `FKfr8p8mqt35hfndx9uwimw4g1g` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_organizers`
--

DROP TABLE IF EXISTS `contest_organizers`;
CREATE TABLE IF NOT EXISTS `contest_organizers` (
  `contest_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  PRIMARY KEY (`contest_id`,`person_id`),
  KEY `FK1442jdih1fl9e3tyu909x32qk` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_position`
--

DROP TABLE IF EXISTS `contest_position`;
CREATE TABLE IF NOT EXISTS `contest_position` (
  `id` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `leaderboard_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa1q92yfc02x3v8vbu0kojqla` (`acl_id`),
  KEY `FKjki559f873wr8sjldmt12qh7d` (`leaderboard_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contest_workouts`
--

DROP TABLE IF EXISTS `contest_workouts`;
CREATE TABLE IF NOT EXISTS `contest_workouts` (
  `contest_id` varchar(255) NOT NULL,
  `workouts_id` varchar(255) NOT NULL,
  PRIMARY KEY (`contest_id`,`workouts_id`),
  UNIQUE KEY `UK_kmtr9mt9sl09sa2q1pwg5tgbq` (`workouts_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contest_workouts`
--

INSERT INTO `contest_workouts` (`contest_id`, `workouts_id`) VALUES
('c1', 'w1');

-- --------------------------------------------------------

--
-- Structure de la table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
CREATE TABLE IF NOT EXISTS `group_members` (
  `group_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`,`person_id`),
  KEY `FKsp30j3hgcgao226agar5ckvdt` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `group_members`
--

INSERT INTO `group_members` (`group_id`, `person_id`) VALUES
('50924f63-0e8c-4f89-9480-1353f7bbe0c6', 'dams@example.com'),
('50924f63-0e8c-4f89-9480-1353f7bbe0c6', 'ln@example.com');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(8),
(8);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `people_group`
--

DROP TABLE IF EXISTS `people_group`;
CREATE TABLE IF NOT EXISTS `people_group` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdn0omjvgoitym2j6y0eve30nk` (`acl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `people_group`
--

INSERT INTO `people_group` (`id`, `name`, `acl_id`) VALUES
('50924f63-0e8c-4f89-9480-1353f7bbe0c6', 'all', 2);

-- --------------------------------------------------------

--
-- Structure de la table `performance`
--

DROP TABLE IF EXISTS `performance`;
CREATE TABLE IF NOT EXISTS `performance` (
  `id` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `athlete_id` varchar(255) DEFAULT NULL,
  `judge_id` varchar(255) DEFAULT NULL,
  `workout_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKajvhssml7sa0i608srpnpnmos` (`acl_id`),
  KEY `FK7ofsjqyyevd8orbqnvh3lf3j` (`athlete_id`),
  KEY `FK41fbgs617lcngms01d52fia6x` (`judge_id`),
  KEY `FKfa697vtnl0pt6nk0jyhko2tdl` (`workout_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `performance`
--

INSERT INTO `performance` (`id`, `comment`, `date`, `duration`, `score`, `acl_id`, `athlete_id`, `judge_id`, `workout_id`) VALUES
('113464ae-1c36-443b-b8d6-7190ed6be1f8', 'test', '2018-05-22 22:11:00', 120000000000, 10, 6, 'ln@example.com', NULL, 'w1'),
('85392901-ab10-4975-93fa-0b8aa3c938f5', 'test', '2018-05-22 22:11:00', 125000000000, 10, 7, 'dams@example.com', NULL, 'w1');

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsxbo9jh4r6st2scmsqrnlq6qd` (`acl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `birthday`, `gender`, `name`, `acl_id`) VALUES
('ln@example.com', '1980-07-19', 'FEMALE', 'Hélène Abos', 3),
('dams@example.com', '1981-02-10', 'MALE', 'Damien Abos', 4);

-- --------------------------------------------------------

--
-- Structure de la table `workout`
--

DROP TABLE IF EXISTS `workout`;
CREATE TABLE IF NOT EXISTS `workout` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `contest_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqpudy603jwy9igflsnmb382di` (`acl_id`),
  KEY `FKpxybu4i0hnw6hlrvdheg74ybx` (`contest_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `workout`
--

INSERT INTO `workout` (`id`, `description`, `name`, `begin`, `end`, `acl_id`, `contest_id`) VALUES
('w1', '', 'wod_1', '2000-01-01 00:00:00', '2100-12-31 23:59:59', 5, 'c1');

-- --------------------------------------------------------

--
-- Structure de la table `workout_leaderboard`
--

DROP TABLE IF EXISTS `workout_leaderboard`;
CREATE TABLE IF NOT EXISTS `workout_leaderboard` (
  `id` varchar(255) NOT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `division_id` varchar(255) DEFAULT NULL,
  `workout_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq1wpc6263vwc5ayjp3raqblml` (`acl_id`),
  KEY `FKmrki01c1wgfixi5ww4kiy8sd5` (`division_id`),
  KEY `FK5hd8p9o7g2y76tokta605jxik` (`workout_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `workout_position`
--

DROP TABLE IF EXISTS `workout_position`;
CREATE TABLE IF NOT EXISTS `workout_position` (
  `id` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `leaderboard_id` varchar(255) DEFAULT NULL,
  `performance_id` varchar(255) DEFAULT NULL,
  `workout_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn8eok42qiq5a702w6ysblyns9` (`leaderboard_id`),
  KEY `FK5g6oe1dxr2j9amlf43n46qvfu` (`performance_id`),
  KEY `FK4b5b4wgli9r1dutlthdj6681p` (`workout_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;
