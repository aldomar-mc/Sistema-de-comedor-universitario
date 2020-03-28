-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.1-m2-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema comedorbd
--

CREATE DATABASE IF NOT EXISTS comedorbd;
USE comedorbd;

--
-- Temporary table structure for view `comensale`
--
DROP TABLE IF EXISTS `comensale`;
DROP VIEW IF EXISTS `comensale`;
CREATE TABLE `comensale` (
  `idpago` int(11),
  `descritipcom` varchar(60),
  `codfacu` varchar(11),
  `concat(categor,' - ',ta.monto)` varbinary(18),
  `dni` varchar(8),
  `nombres` varchar(100),
  `fechapag` date,
  `descricomproba` varchar(60),
  `numcomp` varchar(12),
  `monto` decimal(9,2),
  `idtipocomensal` int(11)
);

--
-- Temporary table structure for view `crono_comensale`
--
DROP TABLE IF EXISTS `crono_comensale`;
DROP VIEW IF EXISTS `crono_comensale`;
CREATE TABLE `crono_comensale` (
  `idconsumidores` int(11),
  `idpago` int(11),
  `dni` varchar(8),
  `nombres` varchar(100),
  `fechaconsumo` date,
  `estadocrono` int(11),
  `idcronogramaconsumo` int(11),
  `descritipcom` varchar(60),
  `idtipocomensal` int(11),
  `codfacu` varchar(11),
  `cat` varbinary(18)
);

--
-- Temporary table structure for view `inscritos`
--
DROP TABLE IF EXISTS `inscritos`;
DROP VIEW IF EXISTS `inscritos`;
CREATE TABLE `inscritos` (
  `idconsumidores` int(11),
  `dni` varchar(8),
  `nombres` varchar(100),
  `gene` varchar(9),
  `est` varchar(8),
  `descrifacu` varchar(100),
  `monto` decimal(9,2),
  `categor` varchar(4),
  `descrisemestre` varchar(60),
  `descritipcom` varchar(60),
  `codfacu` varchar(11)
);

--
-- Temporary table structure for view `vat_asistencia`
--
DROP TABLE IF EXISTS `vat_asistencia`;
DROP VIEW IF EXISTS `vat_asistencia`;
CREATE TABLE `vat_asistencia` (
  `idasistencia` int(11),
  `descritipcom` varchar(60),
  `codfacu` varchar(11),
  `dni` varchar(8),
  `nombres` varchar(100),
  `hora` time,
  `fecha` varchar(45),
  `descripturno` varchar(60),
  `idcronogramaconsumo` int(11)
);

--
-- Temporary table structure for view `vta_licencias`
--
DROP TABLE IF EXISTS `vta_licencias`;
DROP VIEW IF EXISTS `vta_licencias`;
CREATE TABLE `vta_licencias` (
  `idlicencias` int(11),
  `descritipcom` varchar(60),
  `codfacu` varchar(11),
  `dni` varchar(8),
  `nombres` varchar(100),
  `fechaconsumo` date,
  `descricomproba` varchar(60),
  `numdoc` varchar(45),
  `motivo` varchar(150),
  `nufecha` date,
  `idtipocomensal` int(11)
);

--
-- Temporary table structure for view `vtapermisosusuarios`
--
DROP TABLE IF EXISTS `vtapermisosusuarios`;
DROP VIEW IF EXISTS `vtapermisosusuarios`;
CREATE TABLE `vtapermisosusuarios` (
  `idpermisos` int(11),
  `descripcion` varchar(65),
  `idPermisosUsuario` int(11),
  `idusuario` int(11)
);

--
-- Definition of table `administrativos`
--

DROP TABLE IF EXISTS `administrativos`;
CREATE TABLE `administrativos` (
  `idadministrativos` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `genero` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idadministrativos`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrativos`
--

/*!40000 ALTER TABLE `administrativos` DISABLE KEYS */;
INSERT INTO `administrativos` (`idadministrativos`,`nombres`,`dni`,`genero`,`foto`) VALUES 
 (1,'Hernan Hermosa','99877623',0,''),
 (2,'mirian hern','54535643',0,'sinImagen'),
 (3,'ester garcia','32345456',0,'sinImagen');
/*!40000 ALTER TABLE `administrativos` ENABLE KEYS */;


--
-- Definition of table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
CREATE TABLE `alumnos` (
  `idalumnos` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `genero` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  `idfacultad` int(11) NOT NULL,
  PRIMARY KEY (`idalumnos`),
  KEY `fk_alumnos_facultad1` (`idfacultad`),
  CONSTRAINT `fk_alumnos_facultad1` FOREIGN KEY (`idfacultad`) REFERENCES `facultad` (`idfacultad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alumnos`
--

/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` (`idalumnos`,`nombres`,`dni`,`genero`,`foto`,`idfacultad`) VALUES 
 (1,'Jhon Jamanca','87654321',0,'87654321.jpg',2),
 (3,'miguel silva','23456567',0,'sinImagen',2),
 (4,'Omar Barreto','54353453',0,'sinImagen',2),
 (5,'Aldo Omar','72080489',0,'72080489.jpg',2);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;


--
-- Definition of table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
CREATE TABLE `asistencia` (
  `idasistencia` int(11) NOT NULL AUTO_INCREMENT,
  `hora` time NOT NULL,
  `idcronogramaconsumo` int(11) NOT NULL,
  `idturno` int(11) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  PRIMARY KEY (`idasistencia`),
  KEY `fk_asistencia_cronogramaconsumo1` (`idcronogramaconsumo`),
  KEY `fk_asistencia_turno1` (`idturno`),
  CONSTRAINT `fk_asistencia_cronogramaconsumo1` FOREIGN KEY (`idcronogramaconsumo`) REFERENCES `cronogramaconsumo` (`idcronogramaconsumo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_asistencia_turno1` FOREIGN KEY (`idturno`) REFERENCES `turno` (`idturno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `asistencia`
--

/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` (`idasistencia`,`hora`,`idcronogramaconsumo`,`idturno`,`fecha`) VALUES 
 (3,'06:35:46',15,1,'2015-05-28'),
 (4,'12:37:22',15,2,'2015-05-28'),
 (6,'19:18:19',15,3,'2015-05-28'),
 (7,'16:42:06',16,3,'2015-05-29'),
 (8,'17:46:32',8,3,'2015-05-29'),
 (9,'17:46:40',12,3,'2015-05-29'),
 (10,'11:25:07',17,2,'2015-06-01'),
 (11,'11:25:14',9,2,'2015-06-01'),
 (12,'17:04:23',17,3,'2015-06-01'),
 (13,'17:04:30',9,3,'2015-06-01');
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;


--
-- Definition of table `consumidores`
--

DROP TABLE IF EXISTS `consumidores`;
CREATE TABLE `consumidores` (
  `idconsumidores` int(11) NOT NULL AUTO_INCREMENT,
  `idtarifas` int(11) NOT NULL,
  `idsemestre` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `idcomensales` int(11) NOT NULL,
  `idtipocomensal` int(11) NOT NULL,
  PRIMARY KEY (`idconsumidores`),
  KEY `fk_consumidores_tarifas1` (`idtarifas`),
  KEY `fk_consumidores_semestre1` (`idsemestre`),
  KEY `fk_consumidores_tipocomensal1` (`idtipocomensal`),
  CONSTRAINT `fk_consumidores_semestre1` FOREIGN KEY (`idsemestre`) REFERENCES `semestre` (`idsemestre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumidores_tarifas1` FOREIGN KEY (`idtarifas`) REFERENCES `tarifas` (`idtarifas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumidores_tipocomensal1` FOREIGN KEY (`idtipocomensal`) REFERENCES `tipocomensal` (`idtipocomensal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consumidores`
--

/*!40000 ALTER TABLE `consumidores` DISABLE KEYS */;
INSERT INTO `consumidores` (`idconsumidores`,`idtarifas`,`idsemestre`,`estado`,`idcomensales`,`idtipocomensal`) VALUES 
 (1,1,2,0,1,1),
 (3,1,2,0,3,1),
 (4,1,2,0,4,1),
 (5,3,2,0,1,2),
 (6,4,2,0,2,2),
 (7,1,3,0,1,1),
 (8,3,3,0,1,2),
 (9,4,3,0,3,2),
 (10,4,3,0,4,2),
 (11,6,3,0,1,3),
 (12,5,3,0,2,3),
 (13,6,3,0,3,3),
 (14,1,3,0,5,1);
/*!40000 ALTER TABLE `consumidores` ENABLE KEYS */;


--
-- Definition of table `cronogramaconsumo`
--

DROP TABLE IF EXISTS `cronogramaconsumo`;
CREATE TABLE `cronogramaconsumo` (
  `idcronogramaconsumo` int(11) NOT NULL AUTO_INCREMENT,
  `idpago` int(11) NOT NULL,
  `fechaconsumo` date NOT NULL,
  `estadocrono` int(11) NOT NULL,
  PRIMARY KEY (`idcronogramaconsumo`),
  KEY `fk_cronogramaconsumo_pago1` (`idpago`),
  CONSTRAINT `fk_cronogramaconsumo_pago1` FOREIGN KEY (`idpago`) REFERENCES `pago` (`idpago`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cronogramaconsumo`
--

/*!40000 ALTER TABLE `cronogramaconsumo` DISABLE KEYS */;
INSERT INTO `cronogramaconsumo` (`idcronogramaconsumo`,`idpago`,`fechaconsumo`,`estadocrono`) VALUES 
 (1,3,'2015-05-20',1),
 (2,3,'2015-05-21',1),
 (3,3,'2015-05-22',1),
 (4,3,'2015-05-23',1),
 (5,3,'2015-05-24',1),
 (6,3,'2015-05-25',1),
 (7,4,'2015-05-27',1),
 (8,4,'2015-05-29',1),
 (9,4,'2015-06-01',1),
 (10,4,'2015-06-02',2),
 (11,4,'2015-06-03',1),
 (12,5,'2015-05-29',1),
 (13,5,'2015-06-01',1),
 (14,5,'2015-06-02',1),
 (15,6,'2015-05-28',1),
 (16,6,'2015-05-29',1),
 (17,6,'2015-06-01',1),
 (18,6,'2015-06-02',2),
 (19,6,'2015-06-04',1),
 (21,6,'2015-06-03',1),
 (22,7,'2015-06-03',1),
 (23,7,'2015-06-04',1),
 (24,7,'2015-06-05',1),
 (25,4,'2015-06-04',1);
/*!40000 ALTER TABLE `cronogramaconsumo` ENABLE KEYS */;


--
-- Definition of table `datosusuarios`
--

DROP TABLE IF EXISTS `datosusuarios`;
CREATE TABLE `datosusuarios` (
  `idDatosUsuarios` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `ape` varchar(45) DEFAULT NULL,
  `dire` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idDatosUsuarios`),
  KEY `fk_DatosUsuarios_Usuario1` (`idusuario`),
  CONSTRAINT `fk_DatosUsuarios_Usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datosusuarios`
--

/*!40000 ALTER TABLE `datosusuarios` DISABLE KEYS */;
INSERT INTO `datosusuarios` (`idDatosUsuarios`,`nom`,`ape`,`dire`,`tel`,`dni`,`idusuario`) VALUES 
 (4,'JHON JAMANCA','JAMANCA','HUARAZ','2324','12345678',1),
 (6,'MIGUEL SILVA',NULL,'HUARAZ','9237842','09009999',3);
/*!40000 ALTER TABLE `datosusuarios` ENABLE KEYS */;


--
-- Definition of table `docentes`
--

DROP TABLE IF EXISTS `docentes`;
CREATE TABLE `docentes` (
  `iddocentes` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `genero` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddocentes`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `docentes`
--

/*!40000 ALTER TABLE `docentes` DISABLE KEYS */;
INSERT INTO `docentes` (`iddocentes`,`nombres`,`dni`,`genero`,`foto`) VALUES 
 (1,'Miguel Silva','09876543',0,'09876543.jpg'),
 (2,'Luis Alvardo','99998987',0,''),
 (3,'Puelles Manuel','90082321',0,'sinImagen'),
 (4,'Medina Rafaile','12343231',0,'sinImagen');
/*!40000 ALTER TABLE `docentes` ENABLE KEYS */;


--
-- Definition of table `estadistica`
--

DROP TABLE IF EXISTS `estadistica`;
CREATE TABLE `estadistica` (
  `idestadistica` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) DEFAULT NULL,
  `turno` varchar(20) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idestadistica`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estadistica`
--

/*!40000 ALTER TABLE `estadistica` DISABLE KEYS */;
INSERT INTO `estadistica` (`idestadistica`,`tipo`,`turno`,`cantidad`) VALUES 
 (1,'Asistencia','Desayuno',0),
 (2,'InAsistencia','Desayuno',1),
 (3,'Asistencia','Almuerzo',0),
 (4,'InAsistencia','Almuerzo',1),
 (5,'Asistencia','Cena',0),
 (6,'InAsistencia','Cena',1);
/*!40000 ALTER TABLE `estadistica` ENABLE KEYS */;


--
-- Definition of table `facultad`
--

DROP TABLE IF EXISTS `facultad`;
CREATE TABLE `facultad` (
  `idfacultad` int(11) NOT NULL AUTO_INCREMENT,
  `descrifacu` varchar(100) NOT NULL,
  `codfacu` varchar(10) NOT NULL,
  PRIMARY KEY (`idfacultad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facultad`
--

/*!40000 ALTER TABLE `facultad` DISABLE KEYS */;
INSERT INTO `facultad` (`idfacultad`,`descrifacu`,`codfacu`) VALUES 
 (2,'Facultad de Ciencias','FC');
/*!40000 ALTER TABLE `facultad` ENABLE KEYS */;


--
-- Definition of table `licencias`
--

DROP TABLE IF EXISTS `licencias`;
CREATE TABLE `licencias` (
  `idlicencias` int(11) NOT NULL AUTO_INCREMENT,
  `motivo` varchar(150) DEFAULT NULL,
  `numdoc` varchar(45) DEFAULT NULL,
  `nufecha` date NOT NULL,
  `idcronogramaconsumo` int(11) NOT NULL,
  `idtipocomprobante` int(11) NOT NULL,
  PRIMARY KEY (`idlicencias`),
  KEY `fk_licencias_cronogramaconsumo1` (`idcronogramaconsumo`),
  KEY `fk_licencias_tipocomprobante1` (`idtipocomprobante`),
  CONSTRAINT `fk_licencias_cronogramaconsumo1` FOREIGN KEY (`idcronogramaconsumo`) REFERENCES `cronogramaconsumo` (`idcronogramaconsumo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_licencias_tipocomprobante1` FOREIGN KEY (`idtipocomprobante`) REFERENCES `tipocomprobante` (`idtipocomprobante`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `licencias`
--

/*!40000 ALTER TABLE `licencias` DISABLE KEYS */;
INSERT INTO `licencias` (`idlicencias`,`motivo`,`numdoc`,`nufecha`,`idcronogramaconsumo`,`idtipocomprobante`) VALUES 
 (1,'SALDRE DE VIAJE','09044','2015-06-03',18,1),
 (2,'No se Atendera en el comedor por Feriado','001-002415','2015-06-04',10,1);
/*!40000 ALTER TABLE `licencias` ENABLE KEYS */;


--
-- Definition of table `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE `pago` (
  `idpago` int(11) NOT NULL AUTO_INCREMENT,
  `idconsumidores` int(11) NOT NULL,
  `monto` decimal(9,2) NOT NULL,
  `fechapag` date NOT NULL,
  `idtipocomprobante` int(11) NOT NULL,
  `diainicio` varchar(25) NOT NULL,
  `numcomp` varchar(12) NOT NULL,
  PRIMARY KEY (`idpago`),
  KEY `fk_pago_consumidores1` (`idconsumidores`),
  KEY `fk_pago_tipocomprobante1` (`idtipocomprobante`),
  CONSTRAINT `fk_pago_consumidores1` FOREIGN KEY (`idconsumidores`) REFERENCES `consumidores` (`idconsumidores`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pago_tipocomprobante1` FOREIGN KEY (`idtipocomprobante`) REFERENCES `tipocomprobante` (`idtipocomprobante`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pago`
--

/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` (`idpago`,`idconsumidores`,`monto`,`fechapag`,`idtipocomprobante`,`diainicio`,`numcomp`) VALUES 
 (3,7,'9.00','2015-05-26',1,'null','001-004521'),
 (4,8,'50.00','2015-05-26',1,'null','002-012445'),
 (5,12,'27.00','2015-05-27',1,'null','001-001244'),
 (6,7,'7.50','2015-05-27',1,'null','001-002151'),
 (7,9,'36.00','2015-05-28',1,'null','001-002452');
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;


--
-- Definition of table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
CREATE TABLE `permisos` (
  `idPermisos` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(65) NOT NULL,
  PRIMARY KEY (`idPermisos`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permisos`
--

/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` (`idPermisos`,`Descripcion`) VALUES 
 (1,'Crear y actualizar usuarios'),
 (2,'Cambiar Password'),
 (3,'Backup de la Base de Datos'),
 (4,'Restaurar la Base de Datos'),
 (5,'Agregar Permisos'),
 (6,'Asignar Permisos'),
 (7,'Facultad'),
 (8,'Tipo Comensal'),
 (9,'Tipo Comprobante'),
 (10,'Semestre Academico'),
 (11,'Tarifas'),
 (12,'Alumnos'),
 (13,'Docentes'),
 (14,'Administrativos'),
 (15,'Graba Huella Digital'),
 (16,'Registrar Pagos'),
 (17,'Ver Pagos Realizados'),
 (18,'Registro de Asistencia'),
 (19,'Registro Asistencia Manual'),
 (20,'Horarios'),
 (21,'Ver Comensales'),
 (22,'Ver Pagos'),
 (23,'Ver Asistencias'),
 (24,'Ver Balance General'),
 (25,'Ver Inscritos del Semestre'),
 (26,'Ver InAsistencias'),
 (27,'Ver Licencias'),
 (28,'Ver Estadisticas');
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;


--
-- Definition of table `permisosusuario`
--

DROP TABLE IF EXISTS `permisosusuario`;
CREATE TABLE `permisosusuario` (
  `idPermisosUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  `idPermisos` int(11) NOT NULL,
  PRIMARY KEY (`idPermisosUsuario`),
  KEY `fk_PermisosUsuario_Usuario1` (`idusuario`),
  KEY `fk_PermisosUsuario_Permisos1` (`idPermisos`),
  CONSTRAINT `fk_PermisosUsuario_Permisos1` FOREIGN KEY (`idPermisos`) REFERENCES `permisos` (`idPermisos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PermisosUsuario_Usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permisosusuario`
--

/*!40000 ALTER TABLE `permisosusuario` DISABLE KEYS */;
INSERT INTO `permisosusuario` (`idPermisosUsuario`,`idusuario`,`idPermisos`) VALUES 
 (67,1,1),
 (68,1,2),
 (69,1,3),
 (70,1,4),
 (71,1,5),
 (72,1,6),
 (74,3,2),
 (75,3,3),
 (76,3,1),
 (77,1,7),
 (78,1,8),
 (79,1,9),
 (80,1,11),
 (81,1,10),
 (82,1,12),
 (83,1,13),
 (84,1,14),
 (85,1,15),
 (86,1,16),
 (87,1,17),
 (88,1,18),
 (89,1,19),
 (90,1,20),
 (91,1,21),
 (92,1,22),
 (93,1,23),
 (94,1,24),
 (95,1,25),
 (96,1,26),
 (97,1,27),
 (98,1,28);
/*!40000 ALTER TABLE `permisosusuario` ENABLE KEYS */;


--
-- Definition of table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
CREATE TABLE `semestre` (
  `idsemestre` int(11) NOT NULL AUTO_INCREMENT,
  `descrisemestre` varchar(60) NOT NULL,
  `estadosem` int(11) NOT NULL,
  `fecini` date NOT NULL,
  `fecfin` date NOT NULL,
  PRIMARY KEY (`idsemestre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semestre`
--

/*!40000 ALTER TABLE `semestre` DISABLE KEYS */;
INSERT INTO `semestre` (`idsemestre`,`descrisemestre`,`estadosem`,`fecini`,`fecfin`) VALUES 
 (1,'Semestre Academico 2014-II',1,'2013-01-05','2013-06-13'),
 (2,'Semestre Academico 2015-I',1,'2014-01-05','2014-06-05'),
 (3,'Semestre Academico 2015-II',0,'2015-05-05','2015-08-05');
/*!40000 ALTER TABLE `semestre` ENABLE KEYS */;


--
-- Definition of table `somhue`
--

DROP TABLE IF EXISTS `somhue`;
CREATE TABLE `somhue` (
  `id` int(11) DEFAULT NULL,
  `huenombre` varchar(100) DEFAULT NULL,
  `huehuella` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `somhue`
--

/*!40000 ALTER TABLE `somhue` DISABLE KEYS */;
INSERT INTO `somhue` (`id`,`huenombre`,`huehuella`) VALUES 
 (1,'87654321',0x00F87E01C82AE3735CC0413709AB71708314559ACD55A970EF717E7602C3D5790AE6B90AABF2CF8DCD7FA4159640513C70981AAB283A2861284B58A796C6A5DACFAEF5E7916873C91B36CB482E496FEDF850796AE9245AEF089B04653BB081661D758D92FB1DC4AA76E037773B0A0E3FEE7941BE293A7E83A3E8F921BAE630C4832FF35A7001955B919669465385CC106FC0DBD1C6D97DD99C9719797818550C2BC650C7CD556812DFE5C478B9C67A358E531BAB1F0A8AEA05DCE5D040CD8B6B6EB0A08711B4812CF58709DB5B4DBA572800B1C665E713BBCB1519AFE0A68B0369DC89F4EA39A90A18F820CBB5827EFF9249B89EB7CE0DF245312EAD4CC1BB68D56FC05182F5E277CCEFF66DBA50B5955077C8667F21CF5F49F9D6F77407EAFFB3C9A3B54D379EB370640AE66E7E60643087F639E087293FC88EBEE7C576162EC4BB27A001698E30989EBE6CA7C7369EBDF4566B4C8DAEE21DDED17CEA343FAB78A2C7D20AB089783F86ED2119FE366C070D9354215EC2C0624B8C6C0F0EE063CD046F00F88001C82AE3735CC0413709AB71B08314559A14E1F8F8593CE9B83C119A09222C6ED713B52D9C66085CCBF0485CFE7246189A8F2F12232F125E3D0F2B439FA8A9072C9C93AD78F1BDC9DB3492B0D0523641573AAC8E5FC9E46A444F6BDED401F331E89F907C28981A06AA312A287FF804AEED1CBC5447DD12510F6E5C9446E06B85A4AFD29367695D3D704B615AB74F9D4D8BCA73BBA74F4F808091191B013EC7EB9374FE57F7476738102BDAF07C36336F3B88D24E68CEE79EFDD1AB4C53573F20B8301D942FC3F94F3EBBB187BB8787F0FEB364C3639D849B460772708A4551C9E6113B9FB9B94A7608CE0BD739EAA6BA008D7310106B2CCDA814F341077F797F241F05493ECF3AE282A985F98F809154C1DCE86FC6D492B517DF59AB4C494BD52C6CB66D5CD482285DB5BCC370AC39988884CC0854B20428704C18C31E643A50E85C8C26043366F9DC37AA03530E9EBB9ECF124F308A9A22BC4CFDA8B81957A1682EF169287DFAEA71EAF5F67E3ED2CCA7E50AE21EB30B886CFD795C092969F8646F00F87E01C82AE3735CC0413709AB71F08114559A9276D47DFD70CC0C9E8C25EE25E2F6F5624E5026B63BD9743127535C2D8CF676CA6643BFC43353CB1019968D2E204BC23B9C7711B5A93D3535C69ACB46B2C4FCC06FC781ADB6C1E0FC96BD1CF3A9E610B2C5DD064B7029621C46C2AAA45F88FFE56C21B7B070E70A563E36105856900B15C7F0E0678DE72B7642AE3C7D265812A3DDE147E7D797B998876981B8959B8A1122E8DB0A15F2280B14327A201FF9EC04F811092E5EA914F3B9896DDFAAF727865E193532DEC08533E4966F04108D2705877A9CE697E628581A3D13B1A43264119BE3414F40F04C4B4BEE3856A09BBADB0CA1428B7172BB3A4B4AC8DE80EBE0CE96C4760615560112F64E508BD9E760072BD73098A8A1A38EDBAB10BA97DCB751DC7CBD293C7C95478F0C42D3BEE5774102489E631D3DA019D996044D361FF027727148E098EA97F449AD8B6A98701323977B620EB3B364CBAD2D6902D4A41805848988B9DEEA1EC5C5CBEF4E7BB64DB72D924A0816D543EC106DD53C2F6F00E88001C82AE3735CC0413709AB71F0B814559A1417FC45AC50D54FB1BAD2EBDA5AB8F6188F13BFDDFC90485C1E6C365E1309300224B95441FB668DEA93B616530C30EEB6A4929F3373EF643EF15682CBBA7F00D87681832D2D94CF78F8E79DB928335BB58DAF9CC4A579CDBD614AF7964C3689F8D62E00F3F71F83F5F521BD7CC437047AD0D9A1E8074850A52B48688BCB3C7CF229EC22D3A54990CA7D37219FC0758443EDF114B9E2F403CC7579BB172A4AF7BC15A98C46AAA8495133F1E37D03A30AEB1A23B4F0590E438F2EE9967A36DB751FC0726EDAF007E64408FB3F05996E6E4CFD45A79477D2D63352D666FD65D9BF16F6584AB85D4988397C77C2FE10EBFF601540D4E46F373070CAA57F9AC83B17278FAB2753DF51A27AD10EA79E01193F0B45BF6E1501332F68D072B4C24F1B2DB5F629A5FB93F521B990508AA45EC979DDC4A997763C2D782EF7954E79F743B4D1053A7C9BC21DD6389FAB925C1095AA1EEB9719311EE2D27AB3A06F2144679FD94F746ED304BF7F2E5E6E8B9BB5FC3D6F0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000);
/*!40000 ALTER TABLE `somhue` ENABLE KEYS */;


--
-- Definition of table `tarifas`
--

DROP TABLE IF EXISTS `tarifas`;
CREATE TABLE `tarifas` (
  `idtarifas` int(11) NOT NULL AUTO_INCREMENT,
  `monto` decimal(9,2) NOT NULL,
  `estadotar` int(11) NOT NULL,
  `categor` varchar(4) NOT NULL,
  `idtipocomensal` int(11) NOT NULL,
  PRIMARY KEY (`idtarifas`),
  KEY `FK_tarifas_1` (`idtipocomensal`),
  CONSTRAINT `FK_tarifas_1` FOREIGN KEY (`idtipocomensal`) REFERENCES `tipocomensal` (`idtipocomensal`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tarifas`
--

/*!40000 ALTER TABLE `tarifas` DISABLE KEYS */;
INSERT INTO `tarifas` (`idtarifas`,`monto`,`estadotar`,`categor`,`idtipocomensal`) VALUES 
 (1,'1.50',0,'A',1),
 (2,'3.00',0,'B',1),
 (3,'10.00',0,'A',2),
 (4,'12.00',0,'B',2),
 (5,'9.00',0,'A',3),
 (6,'10.00',0,'B',3);
/*!40000 ALTER TABLE `tarifas` ENABLE KEYS */;


--
-- Definition of table `tipocomensal`
--

DROP TABLE IF EXISTS `tipocomensal`;
CREATE TABLE `tipocomensal` (
  `idtipocomensal` int(11) NOT NULL AUTO_INCREMENT,
  `descritipcom` varchar(60) NOT NULL,
  PRIMARY KEY (`idtipocomensal`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipocomensal`
--

/*!40000 ALTER TABLE `tipocomensal` DISABLE KEYS */;
INSERT INTO `tipocomensal` (`idtipocomensal`,`descritipcom`) VALUES 
 (1,'Estudiantes'),
 (2,'Docentes'),
 (3,'Administrativos');
/*!40000 ALTER TABLE `tipocomensal` ENABLE KEYS */;


--
-- Definition of table `tipocomprobante`
--

DROP TABLE IF EXISTS `tipocomprobante`;
CREATE TABLE `tipocomprobante` (
  `idtipocomprobante` int(11) NOT NULL AUTO_INCREMENT,
  `descricomproba` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idtipocomprobante`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipocomprobante`
--

/*!40000 ALTER TABLE `tipocomprobante` DISABLE KEYS */;
INSERT INTO `tipocomprobante` (`idtipocomprobante`,`descricomproba`) VALUES 
 (1,'Boleta'),
 (2,'Solicitud');
/*!40000 ALTER TABLE `tipocomprobante` ENABLE KEYS */;


--
-- Definition of table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
CREATE TABLE `tipousuario` (
  `idTipousuario` int(11) NOT NULL AUTO_INCREMENT,
  `nomtpus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipousuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipousuario`
--

/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` (`idTipousuario`,`nomtpus`) VALUES 
 (1,'Administrador');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;


--
-- Definition of table `tolerancia`
--

DROP TABLE IF EXISTS `tolerancia`;
CREATE TABLE `tolerancia` (
  `idtolerancia` int(11) NOT NULL,
  `minutos` int(11) NOT NULL,
  PRIMARY KEY (`idtolerancia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tolerancia`
--

/*!40000 ALTER TABLE `tolerancia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tolerancia` ENABLE KEYS */;


--
-- Definition of table `turno`
--

DROP TABLE IF EXISTS `turno`;
CREATE TABLE `turno` (
  `idturno` int(11) NOT NULL AUTO_INCREMENT,
  `descripturno` varchar(60) NOT NULL,
  `horamin` time NOT NULL,
  `horamax` time NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idturno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `turno`
--

/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` (`idturno`,`descripturno`,`horamin`,`horamax`,`estado`) VALUES 
 (1,'Desayuno','06:00:00','10:00:00',0),
 (2,'Almuerzo','11:00:00','14:00:00',0),
 (3,'Cena','16:00:00','20:00:00',0);
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nomusr` varchar(45) DEFAULT NULL,
  `psw` varchar(8) DEFAULT NULL,
  `idTipousuario` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_Usuario_Tipousuario1` (`idTipousuario`) USING BTREE,
  CONSTRAINT `fk_Usuario_Tipousuario1` FOREIGN KEY (`idTipousuario`) REFERENCES `tipousuario` (`idTipousuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idusuario`,`nomusr`,`psw`,`idTipousuario`) VALUES 
 (1,'admin','1234',1),
 (3,'miki','123',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of function `InsertaVendedor`
--

DROP FUNCTION IF EXISTS `InsertaVendedor`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=``@`` FUNCTION `InsertaVendedor`(nomusu varchar(45), pas varchar(45), tipo varchar(45)) RETURNS int(11)
BEGIN
declare idtipoous int;
declare codus int;
set idtipoous=(select  idTipousuario from tipousuario where nomtpus=tipo);

   insert into usuario  values (null,nomusu, pas, idtipoous);

   set codus=(select idusuario from usuario order by idusuario desc limit 1);

return codus;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `UpdateVendedor`
--

DROP PROCEDURE IF EXISTS `UpdateVendedor`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=``@`` PROCEDURE `UpdateVendedor`(in idus int, in nous varchar(45), in ps varchar(45), in nomb varchar(45), in di varchar(45),
in te varchar(20),in dn varchar(10))
begin
update usuario set nomusr=nous, psw=ps where idusuario=idus;
update datosusuarios set nom=nomb, dire=di, tel=te,dni=dn where idusuario=idus;
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of view `comensale`
--

DROP TABLE IF EXISTS `comensale`;
DROP VIEW IF EXISTS `comensale`;
CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `comensale` AS select `p`.`idpago` AS `idpago`,`t`.`descritipcom` AS `descritipcom`,`f`.`codfacu` AS `codfacu`,concat(`ta`.`categor`,' - ',`ta`.`monto`) AS `concat(categor,' - ',ta.monto)`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`p`.`fechapag` AS `fechapag`,`tc`.`descricomproba` AS `descricomproba`,`p`.`numcomp` AS `numcomp`,`p`.`monto` AS `monto`,`t`.`idtipocomensal` AS `idtipocomensal` from ((((((`consumidores` `c` join `alumnos` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) where ((`c`.`idcomensales` = `a`.`idalumnos`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 1) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`a`.`idfacultad` = `f`.`idfacultad`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and `p`.`idpago` in (select `comedorbd`.`cronogramaconsumo`.`idpago` AS `idpago` from `cronogramaconsumo`)) union select `p`.`idpago` AS `idpago`,`t`.`descritipcom` AS `descritipcom`,'No Asignado' AS `No Asignado`,concat(`ta`.`categor`,' - ',`ta`.`monto`) AS `concat(categor,' - ',ta.monto)`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`p`.`fechapag` AS `fechapag`,`tc`.`descricomproba` AS `descricomproba`,`p`.`numcomp` AS `numcomp`,`p`.`monto` AS `monto`,`t`.`idtipocomensal` AS `idtipocomensal` from ((((((`consumidores` `c` join `docentes` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) where ((`c`.`idcomensales` = `a`.`iddocentes`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 2) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and `p`.`idpago` in (select `comedorbd`.`cronogramaconsumo`.`idpago` AS `idpago` from `cronogramaconsumo`)) union select `p`.`idpago` AS `idpago`,`t`.`descritipcom` AS `descritipcom`,'No Asignado' AS `No Asignado`,concat(`ta`.`categor`,' - ',`ta`.`monto`) AS `concat(categor,' - ',ta.monto)`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`p`.`fechapag` AS `fechapag`,`tc`.`descricomproba` AS `descricomproba`,`p`.`numcomp` AS `numcomp`,`p`.`monto` AS `monto`,`t`.`idtipocomensal` AS `idtipocomensal` from ((((((`consumidores` `c` join `administrativos` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) where ((`c`.`idcomensales` = `a`.`idadministrativos`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 3) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and `p`.`idpago` in (select `comedorbd`.`cronogramaconsumo`.`idpago` AS `idpago` from `cronogramaconsumo`));

--
-- Definition of view `crono_comensale`
--

DROP TABLE IF EXISTS `crono_comensale`;
DROP VIEW IF EXISTS `crono_comensale`;
CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `crono_comensale` AS select `c`.`idconsumidores` AS `idconsumidores`,`p`.`idpago` AS `idpago`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`cr`.`fechaconsumo` AS `fechaconsumo`,`cr`.`estadocrono` AS `estadocrono`,`cr`.`idcronogramaconsumo` AS `idcronogramaconsumo`,`t`.`descritipcom` AS `descritipcom`,`t`.`idtipocomensal` AS `idtipocomensal`,`f`.`codfacu` AS `codfacu`,concat(`ta`.`categor`,' - ',`ta`.`monto`) AS `cat` from (((((((`consumidores` `c` join `alumnos` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) join `cronogramaconsumo` `cr`) where ((`c`.`idcomensales` = `a`.`idalumnos`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 1) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`a`.`idfacultad` = `f`.`idfacultad`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`p`.`idpago` = `cr`.`idpago`)) union select `c`.`idconsumidores` AS `idconsumidores`,`p`.`idpago` AS `idpago`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`cr`.`fechaconsumo` AS `fechaconsumo`,`cr`.`estadocrono` AS `estadocrono`,`cr`.`idcronogramaconsumo` AS `idcronogramaconsumo`,`t`.`descritipcom` AS `descritipcom`,`t`.`idtipocomensal` AS `idtipocomensal`,'No Asignado' AS `No Asignado`,concat(`ta`.`categor`,' - ',`ta`.`monto`) AS `cat` from (((((((`consumidores` `c` join `docentes` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) join `cronogramaconsumo` `cr`) where ((`c`.`idcomensales` = `a`.`iddocentes`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 2) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`p`.`idpago` = `cr`.`idpago`)) union select `c`.`idconsumidores` AS `idconsumidores`,`p`.`idpago` AS `idpago`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`cr`.`fechaconsumo` AS `fechaconsumo`,`cr`.`estadocrono` AS `estadocrono`,`cr`.`idcronogramaconsumo` AS `idcronogramaconsumo`,`t`.`descritipcom` AS `descritipcom`,`t`.`idtipocomensal` AS `idtipocomensal`,'No Asignado' AS `No Asignado`,concat(`ta`.`categor`,' - ',`ta`.`monto`) AS `cat` from (((((((`consumidores` `c` join `administrativos` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) join `cronogramaconsumo` `cr`) where ((`c`.`idcomensales` = `a`.`idadministrativos`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 3) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`p`.`idpago` = `cr`.`idpago`));

--
-- Definition of view `inscritos`
--

DROP TABLE IF EXISTS `inscritos`;
DROP VIEW IF EXISTS `inscritos`;
CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `inscritos` AS select `c`.`idconsumidores` AS `idconsumidores`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,if((`a`.`genero` = '0'),'Masculino','Femenino') AS `gene`,if((`c`.`estado` = 0),'Activo','InActivo') AS `est`,`f`.`descrifacu` AS `descrifacu`,`t`.`monto` AS `monto`,`t`.`categor` AS `categor`,`s`.`descrisemestre` AS `descrisemestre`,`tc`.`descritipcom` AS `descritipcom`,`f`.`codfacu` AS `codfacu` from (((((`alumnos` `a` join `consumidores` `c`) join `facultad` `f`) join `tarifas` `t`) join `semestre` `s`) join `tipocomensal` `tc`) where ((`a`.`idfacultad` = `f`.`idfacultad`) and (`a`.`idalumnos` = `c`.`idcomensales`) and (`c`.`idtipocomensal` = `tc`.`idtipocomensal`) and (`c`.`idtarifas` = `t`.`idtarifas`) and (`c`.`idsemestre` = `s`.`idsemestre`) and (`tc`.`idtipocomensal` = '1') and (`s`.`estadosem` = '0')) union select `c`.`idconsumidores` AS `idconsumidores`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,if((`a`.`genero` = '0'),'Masculino','Femenino') AS `if(genero='0','Masculino','Femenino')`,if((`c`.`estado` = 0),'Activo','InActivo') AS `if(c.estado=0,'Activo','InActivo')`,'No Asignado' AS `No Asignado`,`t`.`monto` AS `monto`,`t`.`categor` AS `categor`,`s`.`descrisemestre` AS `descrisemestre`,`tc`.`descritipcom` AS `descritipcom`,'Sin Asignar' AS `Sin Asignar` from ((((`docentes` `a` join `consumidores` `c`) join `tarifas` `t`) join `semestre` `s`) join `tipocomensal` `tc`) where ((`a`.`iddocentes` = `c`.`idcomensales`) and (`c`.`idtipocomensal` = `tc`.`idtipocomensal`) and (`c`.`idtarifas` = `t`.`idtarifas`) and (`c`.`idsemestre` = `s`.`idsemestre`) and (`tc`.`idtipocomensal` = '2') and (`s`.`estadosem` = '0')) union select `c`.`idconsumidores` AS `idconsumidores`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,if((`a`.`genero` = '0'),'Masculino','Femenino') AS `if(genero='0','Masculino','Femenino')`,if((`c`.`estado` = 0),'Activo','InActivo') AS `if(c.estado=0,'Activo','InActivo')`,'No Asignado' AS `No Asignado`,`t`.`monto` AS `monto`,`t`.`categor` AS `categor`,`s`.`descrisemestre` AS `descrisemestre`,`tc`.`descritipcom` AS `descritipcom`,'Sin Asignar' AS `Sin Asignar` from (((((`administrativos` `a` join `consumidores` `c`) join `facultad` `f`) join `tarifas` `t`) join `semestre` `s`) join `tipocomensal` `tc`) where ((`a`.`idadministrativos` = `c`.`idcomensales`) and (`c`.`idtipocomensal` = `tc`.`idtipocomensal`) and (`c`.`idtarifas` = `t`.`idtarifas`) and (`c`.`idsemestre` = `s`.`idsemestre`) and (`tc`.`idtipocomensal` = '3') and (`s`.`estadosem` = '0'));

--
-- Definition of view `vat_asistencia`
--

DROP TABLE IF EXISTS `vat_asistencia`;
DROP VIEW IF EXISTS `vat_asistencia`;
CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `vat_asistencia` AS select `asi`.`idasistencia` AS `idasistencia`,`t`.`descritipcom` AS `descritipcom`,`f`.`codfacu` AS `codfacu`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`asi`.`hora` AS `hora`,`asi`.`fecha` AS `fecha`,`tur`.`descripturno` AS `descripturno`,`cr`.`idcronogramaconsumo` AS `idcronogramaconsumo` from (((((((((`consumidores` `c` join `alumnos` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) join `cronogramaconsumo` `cr`) join `asistencia` `asi`) join `turno` `tur`) where ((`c`.`idcomensales` = `a`.`idalumnos`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 1) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`a`.`idfacultad` = `f`.`idfacultad`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`p`.`idpago` = `cr`.`idpago`) and (`cr`.`idcronogramaconsumo` = `asi`.`idcronogramaconsumo`) and (`asi`.`idturno` = `tur`.`idturno`) and `p`.`idpago` in (select `comedorbd`.`cronogramaconsumo`.`idpago` AS `idpago` from `cronogramaconsumo` where (`comedorbd`.`cronogramaconsumo`.`estadocrono` = 0))) union select `asi`.`idasistencia` AS `idasistencia`,`t`.`descritipcom` AS `descritipcom`,'No Asignado' AS `No Asignado`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`asi`.`hora` AS `hora`,`asi`.`fecha` AS `fecha`,`tur`.`descripturno` AS `descripturno`,`cr`.`idcronogramaconsumo` AS `idcronogramaconsumo` from (((((((((`consumidores` `c` join `docentes` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) join `cronogramaconsumo` `cr`) join `asistencia` `asi`) join `turno` `tur`) where ((`c`.`idcomensales` = `a`.`iddocentes`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 2) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`p`.`idpago` = `cr`.`idpago`) and (`cr`.`idcronogramaconsumo` = `asi`.`idcronogramaconsumo`) and (`asi`.`idturno` = `tur`.`idturno`) and `p`.`idpago` in (select `comedorbd`.`cronogramaconsumo`.`idpago` AS `idpago` from `cronogramaconsumo` where (`comedorbd`.`cronogramaconsumo`.`estadocrono` = 0))) union select `asi`.`idasistencia` AS `idasistencia`,`t`.`descritipcom` AS `descritipcom`,'No Asignado' AS `No Asignado`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`asi`.`hora` AS `hora`,`asi`.`fecha` AS `fecha`,`tur`.`descripturno` AS `descripturno`,`cr`.`idcronogramaconsumo` AS `idcronogramaconsumo` from (((((((((`consumidores` `c` join `administrativos` `a`) join `pago` `p`) join `tipocomensal` `t`) join `tarifas` `ta`) join `facultad` `f`) join `tipocomprobante` `tc`) join `cronogramaconsumo` `cr`) join `asistencia` `asi`) join `turno` `tur`) where ((`c`.`idcomensales` = `a`.`idadministrativos`) and (`c`.`idtipocomensal` = `t`.`idtipocomensal`) and (`c`.`idtipocomensal` = 3) and (`c`.`idconsumidores` = `p`.`idconsumidores`) and (`c`.`idtarifas` = `ta`.`idtarifas`) and (`p`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`p`.`idpago` = `cr`.`idpago`) and (`cr`.`idcronogramaconsumo` = `asi`.`idcronogramaconsumo`) and (`asi`.`idturno` = `tur`.`idturno`) and `p`.`idpago` in (select `comedorbd`.`cronogramaconsumo`.`idpago` AS `idpago` from `cronogramaconsumo` where (`comedorbd`.`cronogramaconsumo`.`estadocrono` = 0)));

--
-- Definition of view `vta_licencias`
--

DROP TABLE IF EXISTS `vta_licencias`;
DROP VIEW IF EXISTS `vta_licencias`;
CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `vta_licencias` AS select `l`.`idlicencias` AS `idlicencias`,`t`.`descritipcom` AS `descritipcom`,`f`.`codfacu` AS `codfacu`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`c`.`fechaconsumo` AS `fechaconsumo`,`tc`.`descricomproba` AS `descricomproba`,`l`.`numdoc` AS `numdoc`,`l`.`motivo` AS `motivo`,`l`.`nufecha` AS `nufecha`,`t`.`idtipocomensal` AS `idtipocomensal` from (((((((`cronogramaconsumo` `c` join `licencias` `l`) join `pago` `p`) join `consumidores` `co`) join `tipocomensal` `t`) join `alumnos` `a`) join `facultad` `f`) join `tipocomprobante` `tc`) where ((`l`.`idcronogramaconsumo` = `c`.`idcronogramaconsumo`) and (`c`.`idpago` = `p`.`idpago`) and (`p`.`idconsumidores` = `co`.`idconsumidores`) and (`co`.`idtipocomensal` = `t`.`idtipocomensal`) and (`co`.`idcomensales` = `a`.`idalumnos`) and (`a`.`idfacultad` = `f`.`idfacultad`) and (`l`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`t`.`idtipocomensal` = 1)) union select `l`.`idlicencias` AS `idlicencias`,`t`.`descritipcom` AS `descritipcom`,'No Asignado' AS `No Asignado`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`c`.`fechaconsumo` AS `fechaconsumo`,`tc`.`descricomproba` AS `descricomproba`,`l`.`numdoc` AS `numdoc`,`l`.`motivo` AS `motivo`,`l`.`nufecha` AS `nufecha`,`t`.`idtipocomensal` AS `idtipocomensal` from ((((((`cronogramaconsumo` `c` join `licencias` `l`) join `pago` `p`) join `consumidores` `co`) join `tipocomensal` `t`) join `docentes` `a`) join `tipocomprobante` `tc`) where ((`l`.`idcronogramaconsumo` = `c`.`idcronogramaconsumo`) and (`c`.`idpago` = `p`.`idpago`) and (`p`.`idconsumidores` = `co`.`idconsumidores`) and (`co`.`idtipocomensal` = `t`.`idtipocomensal`) and (`co`.`idcomensales` = `a`.`iddocentes`) and (`l`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`t`.`idtipocomensal` = 2)) union select `l`.`idlicencias` AS `idlicencias`,`t`.`descritipcom` AS `descritipcom`,'No Asignado' AS `No Asignado`,`a`.`dni` AS `dni`,`a`.`nombres` AS `nombres`,`c`.`fechaconsumo` AS `fechaconsumo`,`tc`.`descricomproba` AS `descricomproba`,`l`.`numdoc` AS `numdoc`,`l`.`motivo` AS `motivo`,`l`.`nufecha` AS `nufecha`,`t`.`idtipocomensal` AS `idtipocomensal` from ((((((`cronogramaconsumo` `c` join `licencias` `l`) join `pago` `p`) join `consumidores` `co`) join `tipocomensal` `t`) join `administrativos` `a`) join `tipocomprobante` `tc`) where ((`l`.`idcronogramaconsumo` = `c`.`idcronogramaconsumo`) and (`c`.`idpago` = `p`.`idpago`) and (`p`.`idconsumidores` = `co`.`idconsumidores`) and (`co`.`idtipocomensal` = `t`.`idtipocomensal`) and (`co`.`idcomensales` = `a`.`idadministrativos`) and (`l`.`idtipocomprobante` = `tc`.`idtipocomprobante`) and (`t`.`idtipocomensal` = 3));

--
-- Definition of view `vtapermisosusuarios`
--

DROP TABLE IF EXISTS `vtapermisosusuarios`;
DROP VIEW IF EXISTS `vtapermisosusuarios`;
CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `vtapermisosusuarios` AS select `ps`.`idPermisos` AS `idpermisos`,`ps`.`Descripcion` AS `descripcion`,`p`.`idPermisosUsuario` AS `idPermisosUsuario`,`p`.`idusuario` AS `idusuario` from (`permisosusuario` `p` join `permisos` `ps`) where (`p`.`idPermisos` = `ps`.`idPermisos`);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
