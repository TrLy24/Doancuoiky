-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ql_quaythuoc
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ban_thuoc`
--

DROP TABLE IF EXISTS `ban_thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban_thuoc` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ma_don_thuoc` varchar(10) NOT NULL,
  `ma_thuoc` varchar(10) NOT NULL,
  `so_luong_ban` int(11) NOT NULL,
  `ma_nhan_vien` varchar(10) NOT NULL,
  `tong_tien` int(11) NOT NULL,
  `ngay_ban_thuoc` date DEFAULT NULL,
  `in_hoa_don` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban_thuoc`
--

LOCK TABLES `ban_thuoc` WRITE;
/*!40000 ALTER TABLE `ban_thuoc` DISABLE KEYS */;
INSERT INTO `ban_thuoc` VALUES (0000000110,'0001','0002',1,'0002',20000,'2024-06-09',1),(0000000113,'0002','0004',2,'0002',100000,'2024-06-09',1),(0000000114,'0003','0006',2,'0002',30000,'2024-06-09',1),(0000000115,'0004','0007',1,'0002',27000,'2024-06-09',0),(0000000116,'0005','0008',3,'0002',144000,'2024-06-09',0),(0000000117,'0001','0007',1,'0003',27000,'2024-06-09',1),(0000000118,'0001','0008',5,'0003',240000,'2024-06-09',1),(0000000119,'0003','0018',1,'0003',45000,'2024-06-09',1),(0000000120,'0004','0015',1,'0003',32000,'2024-06-09',0),(0000000121,'0005','0001',1,'0003',30000,'2024-06-09',0),(0000000122,'0001','0005',1,'0004',5000,'2024-06-09',1),(0000000123,'0002','0004',2,'0004',100000,'2024-06-09',1),(0000000124,'0003','0013',2,'0004',40000,'2024-06-09',1),(0000000125,'0004','0010',1,'0004',12000,'2024-06-09',0),(0000000126,'0005','0014',3,'0004',45000,'2024-06-09',0),(0000000127,'0002','0002',3,'0003',60000,'2024-06-09',1),(0000000128,'0006','0006',2,'0002',30000,'2024-06-09',0),(0000000129,'0007','0009',2,'0002',108000,'2024-06-09',1),(0000000130,'0006','0007',5,'0003',135000,'2024-06-09',0),(0000000131,'0007','0008',2,'0002',96000,'2024-06-11',0),(0000000132,'0007','0008',2,'0003',96000,'2024-06-11',0),(0000000133,'0008','0004',5,'0003',250000,'2024-06-11',0),(0000000134,'0006','0004',1,'0004',50000,'2024-06-11',0),(0000000135,'0007','0008',1,'0004',48000,'2024-06-11',0),(0000000136,'0007','0018',2,'0002',90000,'2024-06-12',0),(0000000137,'0008','0016',3,'0002',120000,'2024-06-12',0),(0000000138,'0009','0013',3,'0003',60000,'2024-06-12',0),(0000000139,'0008','0014',5,'0004',75000,'2024-06-12',0),(0000000140,'0009','0010',1,'0004',12000,'2024-06-12',0),(0000000141,'0010','0003',6,'0004',60000,'2024-06-12',0),(0000000142,'0010','0006',8,'0003',120000,'2024-06-12',0),(0000000143,'0009','0006',10,'0002',150000,'2024-06-12',0),(0000000144,'0010','0009',6,'0002',324000,'2024-06-13',0),(0000000145,'0011','0013',9,'0002',180000,'2024-06-13',0),(0000000146,'0012','0014',7,'0002',105000,'2024-06-13',0),(0000000147,'0011','0007',10,'0003',270000,'2024-06-13',0),(0000000148,'0012','0004',2,'0003',100000,'2024-06-13',0),(0000000149,'0011','0018',6,'0004',270000,'2024-06-13',0),(0000000150,'0012','0017',5,'0004',115000,'2024-06-13',0),(0000000151,'0011','0009',2,'0002',108000,'2024-06-13',0),(0000000152,'0013','0007',4,'0002',108000,'2024-06-14',0),(0000000153,'0012','0006',1,'0002',15000,'2024-06-14',0),(0000000154,'0013','0006',2,'0003',30000,'2024-06-14',0),(0000000155,'0012','0008',2,'0003',96000,'2024-06-14',0),(0000000156,'0013','0009',3,'0003',162000,'2024-06-14',0),(0000000157,'0014','0004',3,'0002',150000,'2024-06-15',0),(0000000158,'0014','0016',2,'0003',80000,'2024-06-15',0),(0000000159,'0014','0010',3,'0003',36000,'2024-06-15',0),(0000000160,'0013','0016',2,'0004',80000,'2024-06-15',0),(0000000161,'0013','0001',2,'0004',60000,'2024-06-15',0),(0000000162,'0013','0010',1,'0004',12000,'2024-06-15',0),(0000000163,'0014','0005',5,'0003',25000,'2024-06-15',0),(0000000164,'0015','0015',2,'0003',64000,'2024-06-15',1),(0000000165,'0015','0017',3,'0002',69000,'2024-06-19',1),(0000000166,'0015','0010',6,'0002',72000,'2024-06-19',1),(0000000167,'0016','0017',2,'0002',46000,'2024-06-19',0),(0000000168,'0015','0008',10,'0003',480000,'2024-06-19',0),(0000000169,'0015','0006',8,'0003',120000,'2024-06-19',0),(0000000170,'0014','0010',4,'0004',48000,'2024-06-19',0),(0000000171,'0015','0010',5,'0004',60000,'2024-06-19',0),(0000000172,'0017','0007',9,'0002',243000,'2024-06-21',0),(0000000173,'0018','0009',4,'0002',216000,'2024-06-21',0),(0000000174,'0018','0006',2,'0002',30000,'2024-06-21',0),(0000000175,'0016','0018',10,'0003',450000,'2024-06-21',0),(0000000176,'0016','0017',2,'0003',46000,'2024-06-21',0),(0000000177,'0016','0015',10,'0003',320000,'2024-06-21',0),(0000000178,'0016','0007',10,'0004',270000,'2024-06-21',0),(0000000179,'0017','0013',10,'0004',200000,'2024-06-21',0),(0000000180,'0017','0016',5,'0004',200000,'2024-06-21',0),(0000000181,'0019','0009',10,'0002',540000,'2024-06-22',0),(0000000182,'0017','0014',10,'0003',150000,'2024-06-22',0),(0000000183,'0018','0011',10,'0004',60000,'2024-06-22',0);
/*!40000 ALTER TABLE `ban_thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) NOT NULL,
  `ten_nhan_vien` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `so_dien_thoai` char(10) DEFAULT NULL,
  `dia_chi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` varchar(45) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT 0,
  `ma_nhan_vien` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
INSERT INTO `nhan_vien` VALUES (15,'$2a$10$NjMIumxjV2xcxY9S40Uoq.DipUWoIzJeZZX3Em1gfI4BxKr29SE1.','admin','2445436','Quảng Ngãi','2024-04-04','Nữ',1,'0001'),(17,'$2a$10$7nFm/V0TgPFwF5N/RNW25Oz3TrsBAALmq6bVj81glSd48g2aw1uZ.','Loc','0345678983','Đà Nẵng','2024-05-07','Nam',0,'0002'),(18,'$2a$10$/IA/Emqetxfo9BPqb03bJOFWjGtNit.ZywUVuZD9NPJXOAubPapuK','Ly','0362436734','Quảng Ngãi','2005-06-24','Nữ',0,'0003'),(20,'$2a$10$VNMzI9G.34UgucD3CVId2eboQdIC.JCYHPR1DYQK712nZb0wpUNum','Linh','0394876543','Quảng Trị','2000-05-17','Nam',0,'0004'),(21,'$2a$10$zBg6GyNLDKdakMOhyb6Yu.SPzQWFxdIHRLmjGbLfFwqqfXJWjBNGi','Hồng','0384768374','Quảng Nam','2003-05-15','Nữ',0,'0005'),(24,'$2a$10$OTeHCvmKEqgykrYITocgk./oz91k/FbWCOoHJdfZ0bvwEWol4zrTG','Nam','0398765432','Huế','1996-05-22','Nam',0,'0006'),(25,'$2a$10$d2dUKW/Y.ZYxvNkciQ74HObBzljxvwX.TsuISC5Y5K7oHptM7tonq','Thanh','0345678910','TP. HCM','2001-05-15','Nữ',0,'0007'),(26,'$2a$10$nc8ic7u8ocb/NnlCYlC0hOfTDPVWy7UwZFGf4nRjXxItgO1GJXROu','Nga','0987654321','Khánh Hòa','1999-04-26','Nữ',0,'0008'),(28,'$2a$10$6K9b1va3S.AH8HA9NfNfC.oEJnMQz1A1WfBrNV/201aWghgIjfAvO','Huy','0985746382','Hà Nội','2006-05-17','Nam',0,'0009'),(29,'$2a$10$elA1O7s0hlNZJy7RYFgG1Ohalt5.l1JyKVcXcuhTY4C/XM1xyW43a','Quân','0384728543','Phú Thọ','2004-05-07','Nam',0,'0010');
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,1,'Admin'),(2,0,'User');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuoc`
--

DROP TABLE IF EXISTS `thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_thuoc` varchar(10) NOT NULL,
  `ten_thuoc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ngay_san_xuat` datetime NOT NULL,
  `han_dung` datetime NOT NULL,
  `don_gia` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `tong_gia_nhap` int(11) NOT NULL,
  `nha_cung_cap` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `don_vi_tinh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuoc`
--

LOCK TABLES `thuoc` WRITE;
/*!40000 ALTER TABLE `thuoc` DISABLE KEYS */;
INSERT INTO `thuoc` VALUES (25,'0001','Paracetamol','2023-05-11 00:00:00','2025-06-07 00:00:00',30000,500,3000000,'Nhà Cung Cấp 2','Vỉ'),(26,'0002','Dexamethasone','2024-05-16 00:00:00','2025-05-16 00:00:00',20000,200,4000000,'Nhà Cung Cấp 1','Hộp'),(27,'0003','Amlodipin','2024-04-01 00:00:00','2026-05-14 00:00:00',10000,400,400000,'Nhà Cung Cấp 3','Hộp'),(28,'0004','Prospan','2024-02-05 00:00:00','2026-06-05 00:00:00',50000,550,2500000,'Nhà Cung Cấp 1','Hộp'),(29,'0005','Alpha Choay','2023-05-12 00:00:00','2024-05-30 00:00:00',5000,250,1250000,'Nhà Cung Cấp 3','Vỉ'),(30,'0006','Tiffy','2024-05-02 00:00:00','2025-05-30 00:00:00',15000,650,975000,'Nhà Cung Cấp 2','Vỉ'),(31,'0007','Nitromint','2024-05-01 00:00:00','2025-05-23 00:00:00',27000,380,1026000,'Nhà Cung Cấp 1','Hộp'),(32,'0008','Sorbitol','2024-03-05 00:00:00','2026-05-22 00:00:00',48000,100,4800000,'Nhà Cung Cấp 3','Hộp'),(33,'0009','Panadol Extra','2024-04-01 00:00:00','2025-06-07 00:00:00',54000,800,4320000,'Nhà Cung Cấp 2','Hộp'),(36,'0010','Amoxicillin','2024-01-01 00:00:00','2025-11-29 00:00:00',12000,150,1800000,'Nhà Cung Cấp 3','Vỉ'),(37,'0011','Pennicillin','2023-12-12 00:00:00','2026-05-08 00:00:00',6000,850,480000,'Nhà Cung Cấp 1','Viên'),(39,'0012','Ambroxol','2024-04-16 00:00:00','2024-06-01 00:00:00',6000,180,108000,'Nhà Cung Cấp 3','Viên'),(42,'0013','Becberin','2024-05-28 00:00:00','2025-09-10 00:00:00',20000,200,2000000,'Nhà Cung Cấp 3','Hộp'),(43,'0014','Oxamniquine','2024-01-08 00:00:00','2025-07-03 00:00:00',15000,500,750000,'Nhà Cung Cấp 2','Viên'),(44,'0015','Tylenol','2024-06-10 00:00:00','2024-10-30 00:00:00',32000,600,1920000,'Nhà Cung Cấp 1','Vỉ'),(45,'0016','Cefixim','2023-12-11 00:00:00','2024-06-14 00:00:00',40000,130,5200000,'Nhà Cung Cấp 2','Hộp'),(46,'0017','Pefloxacin','2024-04-16 00:00:00','2026-06-18 00:00:00',23000,450,1035000,'Nhà Cung Cấp 1','Hộp'),(47,'0018','Biseptol','2024-04-01 00:00:00','2026-06-25 00:00:00',45000,170,7650000,'Nhà Cung Cấp 3','Hộp');
/*!40000 ALTER TABLE `thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ql_quaythuoc'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-22 16:53:36
