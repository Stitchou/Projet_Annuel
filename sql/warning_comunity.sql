-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 22 Mai 2014 à 18:46
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `warning_comunity`
--
CREATE DATABASE IF NOT EXISTS `warning_comunity` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `warning_comunity`;

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `longitute` varchar(40) NOT NULL,
  `latitude` varchar(40) NOT NULL,
  `date_debut` datetime NOT NULL,
  `date_fin` datetime NOT NULL,
  `users_id` int(11) NOT NULL,
  `type_event` int(11) NOT NULL,
  PRIMARY KEY (`event_id`),
  KEY `users_id` (`users_id`,`type_event`),
  KEY `type_event` (`type_event`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `events`
--

INSERT INTO `events` (`event_id`, `longitute`, `latitude`, `date_debut`, `date_fin`, `users_id`, `type_event`) VALUES
(1, '111244.2154', '1231.234', '2014-05-22 00:00:00', '2014-05-22 10:21:12', 1, 2),
(2, '15.254', '13.6578', '2014-05-24 07:24:14', '2014-05-24 09:13:12', 3, 3),
(3, '145.326', '185.2169', '2014-05-27 09:21:00', '2014-05-27 18:42:14', 4, 2),
(4, '198.2546', '12.364820', '2014-05-09 06:15:12', '2014-05-09 08:39:40', 1, 4),
(5, '542..3545', '012452.23421', '2014-05-10 10:22:00', '2014-05-10 19:24:24', 2, 4),
(6, '5452.3.5', '224.324', '2014-05-24 08:14:25', '2014-05-22 10:34:43', 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `type_event`
--

CREATE TABLE IF NOT EXISTS `type_event` (
  `type_event` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `duree` int(11) NOT NULL,
  `distance_prevention` int(11) NOT NULL,
  PRIMARY KEY (`type_event`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `type_event`
--

INSERT INTO `type_event` (`type_event`, `nom`, `duree`, `distance_prevention`) VALUES
(1, 'radar', 2147483647, 800),
(2, 'bouchon', 2, 1000),
(3, 'accident', 3, 1500),
(4, 'controle', 2, 1000);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `users_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `pseudo` varchar(30) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` varchar(30) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`users_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`users_id`, `nom`, `prenom`, `pseudo`, `mail`, `mdp`, `level`) VALUES
(1, 'nadire', 'reda', 'red', 're@gg.com', 'md5', 1),
(2, 'arnauve', 'yehouda', 'pedbear', 'yye@gg.com', 'md6', 1),
(3, 'imbart', 'emerich', '626', 'sti@gg.com', 'sha256', 0),
(4, 'toto', 'titi', 'riri', 'fifi@gg.com', 'lol', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `fk_type_event` FOREIGN KEY (`type_event`) REFERENCES `type_event` (`type_event`),
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
