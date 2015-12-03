-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: localhost    Database: Detran
-- ------------------------------------------------------
-- Server version	5.5.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Automovel`
--

DROP IF EXISTS Detran;

create database Detran;

DROP TABLE IF EXISTS `Automovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Automovel` (
  `idAutomovel` int(11) NOT NULL AUTO_INCREMENT,
  `renavam` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `placa` varchar(45) NOT NULL,
  `chassi` varchar(45) NOT NULL,
  `ano` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `idPessoa` int(11) NOT NULL,
  PRIMARY KEY (`idAutomovel`),
  UNIQUE KEY `chassi_UNIQUE` (`chassi`),
  KEY `Proprietario_Automovel_idx` (`idPessoa`),
  CONSTRAINT `Proprietario_Automovel` FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Automovel`
--

LOCK TABLES `Automovel` WRITE;
/*!40000 ALTER TABLE `Automovel` DISABLE KEYS */;
INSERT INTO `Automovel` VALUES (1,'12345678901','Marca3','2134','3242314','HHH-7865','aaaaaaaaaaaaaaaaa','2010',1,21),(24,'3','Marca2','','','AAA-6666','','',1,6),(25,'12341234555','Carro3','a','a','FFF-2112','asdfghjkllkjhgfds','2000',0,10),(26,'11111111111','Marcar','Modelo','Cor','BBB-2222','ChassiChassiChass','1999',1,9),(27,'2','Marca1','Modelo','Cor','FDF-2232','12345123451234512','1991',1,5);
/*!40000 ALTER TABLE `Automovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Autuacao`
--

DROP TABLE IF EXISTS `Autuacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Autuacao` (
  `idAutuacao` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `descricao` varchar(250) NOT NULL,
  `pontuacao` int(11) NOT NULL,
  `custo` double NOT NULL,
  `prazo` int(11) NOT NULL,
  PRIMARY KEY (`idAutuacao`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Autuacao`
--

LOCK TABLES `Autuacao` WRITE;
/*!40000 ALTER TABLE `Autuacao` DISABLE KEYS */;
INSERT INTO `Autuacao` VALUES (1,'Velocidade','2',1,3,60),(2,'Não dar a seta','Descricap',7,917.54,7);
/*!40000 ALTER TABLE `Autuacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Carteira`
--

DROP TABLE IF EXISTS `Carteira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Carteira` (
  `idCarteira` int(11) NOT NULL AUTO_INCREMENT,
  `dataVencimento` datetime NOT NULL,
  `dataEmissao` datetime NOT NULL,
  `nRegistro` varchar(45) NOT NULL,
  `permissao` tinyint(1) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `idPessoa` int(11) NOT NULL,
  PRIMARY KEY (`idCarteira`),
  KEY `Carteira_Pessoa_idx` (`idPessoa`),
  CONSTRAINT `Carteira_Pessoa` FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Carteira`
--

LOCK TABLES `Carteira` WRITE;
/*!40000 ALTER TABLE `Carteira` DISABLE KEYS */;
INSERT INTO `Carteira` VALUES (9,'2015-12-17 00:00:00','2015-12-02 00:00:00','Olaf',0,'A',1,5),(10,'2015-10-23 18:58:07','2015-10-10 00:00:00','facil',0,'A',1,10),(11,'2015-10-15 00:00:00','2015-10-15 00:00:00','12345543210',1,'A',1,20),(12,'2015-10-31 16:38:04','2015-10-01 16:38:02','nregistro',1,'A',0,5),(13,'2015-10-31 16:38:04','2015-10-01 16:38:02','12345678901',0,'B',1,20),(14,'2015-10-31 16:38:04','2015-10-01 16:38:02','12345',1,'AB',1,10);
/*!40000 ALTER TABLE `Carteira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Multa`
--

DROP TABLE IF EXISTS `Multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Multa` (
  `idMulta` int(11) NOT NULL AUTO_INCREMENT,
  `dataEmissao` date NOT NULL,
  `taxaAcrescimo` float NOT NULL,
  `dataPagamento` date DEFAULT NULL,
  `idPessoa` int(11) DEFAULT NULL,
  `idAutuacao` int(11) NOT NULL,
  `idAutomovel` int(11) NOT NULL,
  `idCarteira` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMulta`),
  KEY `Multa_Proprietario_idx` (`idPessoa`),
  KEY `Multa_Autuacao_idx` (`idAutuacao`),
  KEY `Multa_Carteira_idx` (`idCarteira`),
  KEY `Multa_Automovel_idx` (`idAutomovel`),
  CONSTRAINT `Multa_Automovel` FOREIGN KEY (`idAutomovel`) REFERENCES `Automovel` (`idAutomovel`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Multa_Autuacao` FOREIGN KEY (`idAutuacao`) REFERENCES `Autuacao` (`idAutuacao`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Multa_Carteira` FOREIGN KEY (`idCarteira`) REFERENCES `Carteira` (`idCarteira`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Multa_Proprietario` FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Multa`
--

LOCK TABLES `Multa` WRITE;
/*!40000 ALTER TABLE `Multa` DISABLE KEYS */;
INSERT INTO `Multa` VALUES (4,'2015-11-06',50,'2015-12-03',NULL,1,1,10),(6,'2015-01-06',3,'0000-00-00',NULL,1,24,9),(7,'2015-11-06',12,'2015-12-03',18,1,27,NULL),(8,'2015-09-12',33,'0000-00-00',NULL,1,26,9),(9,'2015-11-14',7,'0000-00-00',9,2,1,NULL),(10,'2015-11-10',19,'0000-00-00',21,2,26,NULL),(11,'2015-11-06',50,'0000-00-00',NULL,1,1,10),(12,'2015-11-05',50,'2015-12-03',19,1,1,NULL);
/*!40000 ALTER TABLE `Multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pessoa` (
  `idPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `rg` varchar(15) NOT NULL,
  `orgaoEmissor` varchar(10) NOT NULL,
  `rgEstado` varchar(5) NOT NULL,
  `dataNascimento` datetime NOT NULL,
  `logradouro` varchar(80) NOT NULL,
  `numeroLogradouro` varchar(10) NOT NULL,
  `complementoLogradouro` varchar(30) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `estado` varchar(25) NOT NULL,
  `cep` varchar(15) NOT NULL,
  `nomeMae` varchar(200) NOT NULL,
  `nomePai` varchar(200) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES (1,'Lucas Ramos','377.894.178-00','22.223.123-1','CRAS','RR','1993-10-09 00:00:00','Rua Doutor Colares','22','232','Centro','Ponta Grossa','PR','84.010-010','Nome','P',1),(3,'João','377.894.178-00','22.223.123-1','SSP','PR','1993-10-13 00:00:00','Rua Alexandrino de Moraes','2','23','Jardim Maringá','Itapeva','SP','18.407-130','Nome','J',1),(4,'Maria','965.156.018-59','12.345.678-X','SSP','PR','2015-10-23 16:25:55','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','k',1),(5,'José','965.156.018-59','12.345.678-X','SSP','PR','1955-10-15 00:00:00','Rua Doutor Colares','23213','','Centro','Ponta Grossa','PR','84.010-010','Nome','asdasd',1),(6,'Daniel','377.894.178-00','12.345.678-X','SSP','PR','2015-10-22 00:00:00','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','s',1),(7,'Cristian','965.156.018-59','12.345.678-X','SSP','PR','2015-10-22 16:54:13','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','k',1),(8,'Nomessss','377.894.178-00','12.345.678-X','SSP','PR','2015-10-02 00:00:00','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','d',1),(9,'Smeagol','377.894.178-00','12.345.678-X','SSP','PR','2015-10-22 00:00:00','Rua Doutor Colares','2','','Centro','Ponta Grossa','PR','84.010-010','Nome','sd',1),(10,'Frodo','377.894.178-00','12.345.678-X','SSP','PR','2015-10-02 00:00:00','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','Pai',1),(11,'Sam','377.894.178-00','12.345.678-X','SSP','PR','2015-10-09 14:50:43','Rua Doutor Colares','12','','Centro','Ponta Grossa','PR','84.010-010','Nome','1',1),(12,'Merry','965.156.018-59','12.345.678-X','SSP','PR','2015-10-17 14:54:00','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','1',1),(18,'Pippin','965.156.018-59','12.345.678-X','SSP','PR','2015-10-03 16:38:00','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Nome','w',1),(19,'Gandalf','965.156.018-59','12.345.678-X','SSP','PR','2015-10-09 21:34:15','Rua Doutor Colares','1','','Centro','Ponta Grossa','PR','84.010-010','Mãe','Nome',1),(20,'Sauron','965.156.018-59','12.345.678-X','SSP','PR','1997-10-21 00:00:00','Rua Doutor Colares','9','32','Centro','Ponta Grossa','PR','84.010-010','Mãe','Pai',1),(21,'Bilbo Bagginssessss','965.156.018-59','12.345.678-X','SSP','PR','2015-10-03 10:53:32','Rua Doutor Colares','1','1','Centro','Ponta Grossa','PR','84.010-010','Mãe','Pai',1),(22,'Legolas','377.894.178-00','48.791.924-0','COREN','PR','1990-10-18 00:00:00','Rua Doutor Paula Xavier','1','1','Centro','Ponta Grossa','PR','84.010-270','Mae','Pai',1);
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-03 21:13:01
