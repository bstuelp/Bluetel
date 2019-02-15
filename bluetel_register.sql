CREATE TABLE `register` (
  `readID` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `registerID` varchar(45) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`readID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `register` VALUES (1,1,'ANYTIME','1',1),(2,1,'NIGHT','2',2),(3,1,'NIGHT','3',3),(4,2,'ANYTIME','1',1),(5,2,'NIGHT','2',2),(6,2,'NIGHT','2',2),(7,2,'NIGHT','2',2),(8,2,'NIGHT','3',3),(9,2,'ANYTIME','1',1),(10,2,'NIGHT','2',2),(11,2,'NIGHT','2',2),(12,2,'NIGHT','2',2),(13,2,'NIGHT','2',2),(14,2,'NIGHT','3',3),(15,2,'ANYTIME','1',1),(16,2,'NIGHT','2',2),(17,2,'NIGHT','2',2),(18,2,'NIGHT','2',2),(19,2,'NIGHT','2',2),(20,2,'NIGHT','3',3),(21,3,'ANYTIME','1',1),(22,3,'NIGHT','2',2),(23,3,'NIGHT','2',2),(24,3,'NIGHT','2',2),(25,3,'NIGHT','2',2),(26,3,'NIGHT','3',3),(27,4,'ANYTIME','1',1),(28,4,'NIGHT','3',3),(29,5,'ANYTIME','1',1),(30,5,'NIGHT','3',3);