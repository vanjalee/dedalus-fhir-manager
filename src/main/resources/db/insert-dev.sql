---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------- Test Data -----------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

LOCK TABLES `human_name` WRITE;
/*!40000 ALTER TABLE `human_name` DISABLE KEYS */;
INSERT INTO `human_name` VALUES ('ebaaa241-2336-4a61-b2c0-42123d217318','2021-06-14 08:26:14.207709','Newman');
/*!40000 ALTER TABLE `human_name` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `human_name_given` WRITE;
/*!40000 ALTER TABLE `human_name_given` DISABLE KEYS */;
INSERT INTO `human_name_given` VALUES ('ebaaa241-2336-4a61-b2c0-42123d217318','Simon'),('ebaaa241-2336-4a61-b2c0-42123d217318','Paul');
/*!40000 ALTER TABLE `human_name_given` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('883c4093-9745-40d9-bd8a-53b8c96ec275','2021-06-14 08:26:14.186911','1998-03-17',NULL,'http://hapi.fhir.org/baseR4/Patient/1854776','MALE');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `patient_human_name` WRITE;
/*!40000 ALTER TABLE `patient_human_name` DISABLE KEYS */;
INSERT INTO `patient_human_name` VALUES ('883c4093-9745-40d9-bd8a-53b8c96ec275','ebaaa241-2336-4a61-b2c0-42123d217318');
/*!40000 ALTER TABLE `patient_human_name` ENABLE KEYS */;
UNLOCK TABLES;