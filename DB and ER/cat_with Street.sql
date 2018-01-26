/*
Navicat MySQL Data Transfer

Source Server         : Localhost Mysql
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-01-26 08:39:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aplication_payment
-- ----------------------------
DROP TABLE IF EXISTS `aplication_payment`;
CREATE TABLE `aplication_payment` (
  `idAplication_Payment` int(11) NOT NULL AUTO_INCREMENT,
  `Application_idApplication` int(11) NOT NULL,
  `Payment_idPayment` int(11) NOT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAplication_Payment`),
  KEY `fk_Aplication_Payment_Application1_idx` (`Application_idApplication`),
  KEY `fk_Aplication_Payment_Payment1_idx` (`Payment_idPayment`),
  CONSTRAINT `fk_Aplication_Payment_Application1` FOREIGN KEY (`Application_idApplication`) REFERENCES `application` (`idApplication`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aplication_Payment_Payment1` FOREIGN KEY (`Payment_idPayment`) REFERENCES `payment` (`idPayment`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aplication_payment
-- ----------------------------

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `idApplication` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_idCustomer` int(11) NOT NULL,
  `Trade_Type_idTrade_Type` int(11) NOT NULL,
  `Trade_Nature_idTrade_Nature` int(11) NOT NULL,
  `Sub_Nature_idSub_Nature` int(11) DEFAULT NULL,
  `User_idUser` int(11) DEFAULT '0' COMMENT 'RO Catagary Eke User\nApplication Eka aragena awa kena',
  `application_date` date DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `allocation` double DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `trade_name` varchar(45) DEFAULT NULL,
  `trade_address1` varchar(45) DEFAULT NULL,
  `trade_address2` varchar(45) DEFAULT NULL,
  `trade_address3` varchar(45) DEFAULT NULL,
  `trade_nature` varchar(45) DEFAULT NULL,
  `tax_amount` double DEFAULT NULL,
  `discription` varchar(255) DEFAULT NULL,
  `statues` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  `User_Log_idUser_Log` int(11) NOT NULL,
  `approveTo_Paymant` int(11) DEFAULT NULL,
  PRIMARY KEY (`idApplication`),
  KEY `fk_Application_Trade_Nature1_idx` (`Trade_Nature_idTrade_Nature`),
  KEY `fk_Application_Customer1_idx` (`Customer_idCustomer`),
  KEY `fk_Application_Sub_Nature1_idx` (`Sub_Nature_idSub_Nature`),
  KEY `fk_Application_Trade_Type1_idx` (`Trade_Type_idTrade_Type`),
  KEY `fk_Application_User1_idx` (`User_idUser`),
  KEY `fk_Application_User_Log1_idx` (`User_Log_idUser_Log`),
  CONSTRAINT `fk_Application_Customer1` FOREIGN KEY (`Customer_idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Application_Sub_Nature1` FOREIGN KEY (`Sub_Nature_idSub_Nature`) REFERENCES `sub_nature` (`idSub_Nature`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Application_Trade_Nature1` FOREIGN KEY (`Trade_Nature_idTrade_Nature`) REFERENCES `trade_nature` (`idTrade_Nature`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Application_Trade_Type1` FOREIGN KEY (`Trade_Type_idTrade_Type`) REFERENCES `trade_type` (`idTrade_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Application_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Application_User_Log1` FOREIGN KEY (`User_Log_idUser_Log`) REFERENCES `user_log` (`idUser_Log`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------

-- ----------------------------
-- Table structure for apprualstatues
-- ----------------------------
DROP TABLE IF EXISTS `apprualstatues`;
CREATE TABLE `apprualstatues` (
  `idApprualStatues` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) NOT NULL,
  `Application_idApplication` int(11) NOT NULL,
  `statues` int(11) DEFAULT NULL,
  `description` text,
  `date` date DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  `idOtheritisCat` int(11) DEFAULT NULL,
  PRIMARY KEY (`idApprualStatues`),
  KEY `fk_ApprualStatues_Application1_idx` (`Application_idApplication`),
  KEY `fk_ApprualStatues_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_ApprualStatues_Application1` FOREIGN KEY (`Application_idApplication`) REFERENCES `application` (`idApplication`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ApprualStatues_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apprualstatues
-- ----------------------------

-- ----------------------------
-- Table structure for assessment
-- ----------------------------
DROP TABLE IF EXISTS `assessment`;
CREATE TABLE `assessment` (
  `idAssessment` int(11) NOT NULL AUTO_INCREMENT,
  `Street_idStreet` int(11) NOT NULL,
  `assessment_no` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAssessment`),
  KEY `fk_Assessment_Street1_idx` (`Street_idStreet`),
  CONSTRAINT `fk_Assessment_Street1` FOREIGN KEY (`Street_idStreet`) REFERENCES `street` (`idStreet`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assessment
-- ----------------------------

-- ----------------------------
-- Table structure for bank_info
-- ----------------------------
DROP TABLE IF EXISTS `bank_info`;
CREATE TABLE `bank_info` (
  `idBank_Info` int(11) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(45) DEFAULT NULL,
  `acount_no` varchar(45) DEFAULT NULL,
  `acount_name` varchar(45) DEFAULT NULL,
  `current_ballance` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBank_Info`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_info
-- ----------------------------
INSERT INTO `bank_info` VALUES ('1', '100', '100', '100', '0', '1', '1');

-- ----------------------------
-- Table structure for cash_flow
-- ----------------------------
DROP TABLE IF EXISTS `cash_flow`;
CREATE TABLE `cash_flow` (
  `idCash_Flow` int(11) NOT NULL AUTO_INCREMENT,
  `Vort_idVort` int(11) NOT NULL,
  `Bank_Info_idBank_Info` int(11) NOT NULL,
  `recipt_no` varchar(45) DEFAULT NULL,
  `cash` double DEFAULT NULL,
  `cheque` double DEFAULT NULL,
  `cheque_no` varchar(45) DEFAULT NULL,
  `application_no` varchar(45) DEFAULT NULL,
  `totale` double DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `mont` int(11) DEFAULT NULL,
  `cf_date` date DEFAULT NULL,
  PRIMARY KEY (`idCash_Flow`),
  KEY `fk_Cash_Flow_Vort1_idx` (`Vort_idVort`),
  KEY `fk_Cash_Flow_Bank_Info1_idx` (`Bank_Info_idBank_Info`),
  CONSTRAINT `fk_Cash_Flow_Bank_Info1` FOREIGN KEY (`Bank_Info_idBank_Info`) REFERENCES `bank_info` (`idBank_Info`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cash_Flow_Vort1` FOREIGN KEY (`Vort_idVort`) REFERENCES `vort` (`idVort`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cash_flow
-- ----------------------------

-- ----------------------------
-- Table structure for catagory
-- ----------------------------
DROP TABLE IF EXISTS `catagory`;
CREATE TABLE `catagory` (
  `idCatagory` int(11) NOT NULL AUTO_INCREMENT,
  `catagory_name` varchar(45) DEFAULT NULL COMMENT 'Catagory kiyanne application\njala\ntax\nshop rental\ntread license\n',
  `catagory_no` varchar(45) DEFAULT NULL,
  `Catagorycol` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCatagory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catagory
-- ----------------------------

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `idContact` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_idCustomer` int(11) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address1` varchar(45) DEFAULT NULL,
  `address2` varchar(45) DEFAULT NULL,
  `address3` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `statues` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idContact`),
  KEY `fk_Contact_Customer_idx` (`Customer_idCustomer`),
  CONSTRAINT `fk_Contact_Customer` FOREIGN KEY (`Customer_idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `idCustomer` int(11) NOT NULL AUTO_INCREMENT,
  `User_Log_idUser_Log` int(11) NOT NULL,
  `nic` varchar(45) DEFAULT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `Customercol` varchar(45) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `statues` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  KEY `fk_Customer_User_Log1_idx` (`User_Log_idUser_Log`),
  CONSTRAINT `fk_Customer_User_Log1` FOREIGN KEY (`User_Log_idUser_Log`) REFERENCES `user_log` (`idUser_Log`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for customer_has_assessment
-- ----------------------------
DROP TABLE IF EXISTS `customer_has_assessment`;
CREATE TABLE `customer_has_assessment` (
  `idCustomer_has_Assessmentcol` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_idCustomer` int(11) NOT NULL,
  `Assessment_idAssessment` int(11) NOT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCustomer_has_Assessmentcol`),
  KEY `fk_Customer_has_Assessment_Assessment1_idx` (`Assessment_idAssessment`),
  KEY `fk_Customer_has_Assessment_Customer1_idx` (`Customer_idCustomer`),
  CONSTRAINT `fk_Customer_has_Assessment_Assessment1` FOREIGN KEY (`Assessment_idAssessment`) REFERENCES `assessment` (`idAssessment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_has_Assessment_Customer1` FOREIGN KEY (`Customer_idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_has_assessment
-- ----------------------------

-- ----------------------------
-- Table structure for customer_has_trade_license
-- ----------------------------
DROP TABLE IF EXISTS `customer_has_trade_license`;
CREATE TABLE `customer_has_trade_license` (
  `idCustomer_has_Trade_Licensecol` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_idCustomer` int(11) NOT NULL,
  `Trade_License_idTrade_License` int(11) NOT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCustomer_has_Trade_Licensecol`),
  KEY `fk_Customer_has_Trade_License_Trade_License1_idx` (`Trade_License_idTrade_License`),
  KEY `fk_Customer_has_Trade_License_Customer1_idx` (`Customer_idCustomer`),
  CONSTRAINT `fk_Customer_has_Trade_License_Customer1` FOREIGN KEY (`Customer_idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_has_Trade_License_Trade_License1` FOREIGN KEY (`Trade_License_idTrade_License`) REFERENCES `trade_license` (`idTrade_License`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_has_trade_license
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `idDepartment` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `statues` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDepartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for interest
-- ----------------------------
DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
  `idInterest` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `statues` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInterest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interest
-- ----------------------------

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `idLogin` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) NOT NULL,
  `uname` varchar(45) DEFAULT NULL,
  `pword` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLogin`),
  KEY `fk_Login_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_Login_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('1', '1', 'admin', 'admin', '1', '1');

-- ----------------------------
-- Table structure for otheritiscat
-- ----------------------------
DROP TABLE IF EXISTS `otheritiscat`;
CREATE TABLE `otheritiscat` (
  `idOtheritisCat` int(11) NOT NULL AUTO_INCREMENT,
  `catname` varchar(45) DEFAULT NULL,
  `disignation` varchar(45) DEFAULT NULL,
  `statues` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOtheritisCat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of otheritiscat
-- ----------------------------

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `idPayment` int(11) NOT NULL AUTO_INCREMENT,
  `Vort_idVort` int(11) NOT NULL,
  `application_no` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `receipt_no` varchar(45) DEFAULT NULL,
  `tax_amount` double DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `nbt` double DEFAULT NULL,
  `spamp` double DEFAULT NULL,
  `totale_amount` double DEFAULT NULL,
  `CASH CHEACK` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  `User_Log_idUser_Log` int(11) NOT NULL,
  PRIMARY KEY (`idPayment`),
  KEY `fk_Payment_Vort1_idx` (`Vort_idVort`),
  KEY `fk_Payment_User_Log1_idx` (`User_Log_idUser_Log`),
  CONSTRAINT `fk_Payment_User_Log1` FOREIGN KEY (`User_Log_idUser_Log`) REFERENCES `user_log` (`idUser_Log`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_Vort1` FOREIGN KEY (`Vort_idVort`) REFERENCES `vort` (`idVort`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for privilage
-- ----------------------------
DROP TABLE IF EXISTS `privilage`;
CREATE TABLE `privilage` (
  `idPrivilage` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) NOT NULL,
  `componant` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `Privilagecol` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPrivilage`),
  KEY `fk_Privilage_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_Privilage_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilage
-- ----------------------------

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `idProgram` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `Programcol` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProgram`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES ('1', 'පොදු පරිපාලන හා කර්යමන්ඩල සේවා', null, '1', '1');
INSERT INTO `program` VALUES ('2', 'සෞඛ්‍ය සේවා', null, '1', '1');

-- ----------------------------
-- Table structure for program_subtitle
-- ----------------------------
DROP TABLE IF EXISTS `program_subtitle`;
CREATE TABLE `program_subtitle` (
  `idProgram_SubTitle` int(11) NOT NULL AUTO_INCREMENT,
  `Program_Title_idProgram_Title` int(11) NOT NULL,
  `sub_title_no` varchar(45) DEFAULT NULL,
  `sub_title_name` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProgram_SubTitle`),
  KEY `fk_Program_SubTitle_Program_Title1_idx` (`Program_Title_idProgram_Title`),
  CONSTRAINT `fk_Program_SubTitle_Program_Title1` FOREIGN KEY (`Program_Title_idProgram_Title`) REFERENCES `program_title` (`idProgram_Title`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program_subtitle
-- ----------------------------
INSERT INTO `program_subtitle` VALUES ('1', '1', null, 'වෙල‍්ඳ බලපත්‍ර', '1', '1');

-- ----------------------------
-- Table structure for program_title
-- ----------------------------
DROP TABLE IF EXISTS `program_title`;
CREATE TABLE `program_title` (
  `idProgram_Title` int(11) NOT NULL AUTO_INCREMENT,
  `Program_idProgram` int(11) NOT NULL,
  `title_no` varchar(45) DEFAULT NULL,
  `title_name` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `int` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProgram_Title`),
  KEY `fk_Program_Title_Program1_idx` (`Program_idProgram`),
  CONSTRAINT `fk_Program_Title_Program1` FOREIGN KEY (`Program_idProgram`) REFERENCES `program` (`idProgram`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program_title
-- ----------------------------
INSERT INTO `program_title` VALUES ('1', '1', null, 'බලපත්‍ර ගාස්තු', '1', '1');

-- ----------------------------
-- Table structure for street
-- ----------------------------
DROP TABLE IF EXISTS `street`;
CREATE TABLE `street` (
  `idStreet` int(11) NOT NULL AUTO_INCREMENT,
  `Ward_idWard` int(11) NOT NULL,
  `street_name` varchar(45) DEFAULT NULL,
  `strret_no` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStreet`),
  KEY `fk_Street_Ward1_idx` (`Ward_idWard`),
  CONSTRAINT `fk_Street_Ward1` FOREIGN KEY (`Ward_idWard`) REFERENCES `ward` (`idWard`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of street
-- ----------------------------
INSERT INTO `street` VALUES ('1', '1', 'GANGODA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('2', '1', 'PUTTLUM ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('3', '1', 'PUTTLUM ROAD 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('4', '1', 'SUMANGALA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('5', '1', 'WILGODA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('6', '1', 'WILGODA ROAD 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('7', '1', 'WILGODA ROAD 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('8', '1', 'WILGODA ROAD 3 RD LANE', null, '1', '1');
INSERT INTO `street` VALUES ('9', '1', 'ARIYASINGHALA', null, '1', '1');
INSERT INTO `street` VALUES ('10', '1', 'ASIRI ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('11', '1', 'ATHTHADASSI', null, '1', '1');
INSERT INTO `street` VALUES ('12', '1', 'BODIYA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('13', '1', 'MADURA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('14', '1', 'SAMA MAWATHA [WILGODA 1ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('15', '1', 'SALIYA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('16', '1', 'SUMANGALA MAWATHA 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('17', '1', 'SUMANGALA MAWATHA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('18', '1', 'WIJAYA MAWATHA 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('19', '1', 'UDYANA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('20', '1', 'OTHU KUMBURA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('21', '2', 'WELAGEDARA STREET', null, '1', '1');
INSERT INTO `street` VALUES ('22', '2', 'PUTTLUM ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('23', '2', 'SQUARE LANE', null, '1', '1');
INSERT INTO `street` VALUES ('24', '2', 'WELANGOLLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('25', '2', 'WELANGOLLA RD.2 LAN.[MAHAMEGA]', null, '1', '1');
INSERT INTO `street` VALUES ('26', '2', 'HINDU DEVALA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('27', '2', 'PRASANNA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('28', '2', 'LAKE ROUND ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('29', '2', 'SOUTH LAKE ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('30', '2', 'WELANGOLLA RD.3 LAN.(MANGA.MW)', null, '1', '1');
INSERT INTO `street` VALUES ('31', '2', 'WELANGOLLA RD.1 LAN.(SAMADI.M)', null, '1', '1');
INSERT INTO `street` VALUES ('32', '2', 'WELANGOLLA RD.4 TH LA.(UYAN.M)', null, '1', '1');
INSERT INTO `street` VALUES ('33', '3', 'BAUDDALOKA ROAD 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('34', '3', 'JAYA PATHIRANA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('35', '3', 'BAUDDALOKA ROAD 3 RD LANE', null, '1', '1');
INSERT INTO `street` VALUES ('36', '3', 'BAUDDALOKA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('37', '3', 'SENANAYAKE MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('38', '3', 'COLOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('39', '3', 'MIHINDU MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('40', '3', 'NEGOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('41', '3', 'ST. ANNES STREET', null, '1', '1');
INSERT INTO `street` VALUES ('42', '3', 'PUTTLUM ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('43', '3', 'BAUDDALOKA ROAD 2ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('44', '4', 'COLOMBO RD:1 LANE(NOYAL SEA:M)', null, '1', '1');
INSERT INTO `street` VALUES ('45', '4', 'GALWALA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('46', '4', 'ILUPPUGEDERA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('47', '4', 'SAMAGI MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('48', '4', 'NEGOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('49', '4', 'NEGO.RD 1 ST LANE[WAGIRAGNANA]', null, '1', '1');
INSERT INTO `street` VALUES ('50', '4', 'PUTTALAM ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('51', '4', 'SUMANGALA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('52', '4', 'WILGODA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('53', '4', 'DHARMAYATHANA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('54', '4', 'GAJABA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('55', '4', 'ILUPPUGEDARA ROAD (1 ST LANE)', null, '1', '1');
INSERT INTO `street` VALUES ('56', '4', 'SEPALIKA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('57', '4', 'SAPUMAL MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('58', '4', 'UPUL MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('59', '5', 'COLOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('60', '5', 'NISSANKA MAWATHA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('61', '5', 'NISSANKA MAWATHA 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('62', '5', 'NISSANKA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('63', '5', 'MALKADUWAWA CIRCULAR ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('64', '5', 'SENASUMA MAWATHA [NGD 4TH L]', null, '1', '1');
INSERT INTO `street` VALUES ('65', '5', 'MUNAMALE MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('66', '5', 'MAHASEN MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('67', '5', 'MALKADUWAWA UYAN PEDESA MW', null, '1', '1');
INSERT INTO `street` VALUES ('68', '5', 'THAABRU MAWATHA [NGD 2ND LANE]', null, '1', '1');
INSERT INTO `street` VALUES ('69', '5', 'NEGOMBO ROAD 3 RD LANE', null, '1', '1');
INSERT INTO `street` VALUES ('70', '5', 'NEGOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('71', '5', 'MUHANDIRAM WEERATHUNGA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('72', '5', 'HERALIYAWALA ROAD [NGD 3RD L]', null, '1', '1');
INSERT INTO `street` VALUES ('73', '5', 'BANDARANAYAKA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('74', '5', 'NANDANA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('75', '5', 'NISSANKA MAWATHA 5 LANE', null, '1', '1');
INSERT INTO `street` VALUES ('76', '5', 'NISSANKA MAWATHA 3 RD LANE', null, '1', '1');
INSERT INTO `street` VALUES ('77', '5', 'WAHARA NAVAGANGODA 1ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('78', '5', 'PUBUDU MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('79', '5', 'PUBUDU MAWATHA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('80', '5', 'PUBUDU MAWATHA 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('81', '5', 'WIJAYABA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('82', '5', 'PEHEKAMHALA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('83', '5', 'RAJASINGHE MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('84', '5', 'PANAMBALANA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('85', '5', 'THALGODAPITIYA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('86', '5', 'WEHARA NAWAGANGODA 2ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('87', '5', 'BAUDDALOKA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('88', '6', 'AMUNUWATTA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('89', '6', 'COLOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('90', '6', 'HABAGE LANE', null, '1', '1');
INSERT INTO `street` VALUES ('91', '6', 'HENAMULLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('92', '6', 'JAYANTHIPURA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('93', '6', 'KANDAUDAWATTA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('94', '6', 'RAJAPHILLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('95', '6', 'COLOMBO ROAD 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('96', '6', 'UDAWALPOLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('97', '6', 'WILBAWA DEVALA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('98', '6', 'HABAGE 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('99', '6', 'HABAGE 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('100', '6', 'UDAWALPOLA 3 RD LANE', null, '1', '1');
INSERT INTO `street` VALUES ('101', '6', 'UDAWALPOLA 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('102', '6', 'UDAWALPOLA I ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('103', '6', 'AMUNAWATTHA PEDESA', null, '1', '1');
INSERT INTO `street` VALUES ('104', '6', 'JAYANTHIPURA BALADAKSA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('105', '6', 'SOUTH ROUND ROAD 4TH LANE', null, '1', '1');
INSERT INTO `street` VALUES ('106', '6', 'WAHARAWATHTHA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('107', '7', 'UDAWALPOLA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('108', '7', 'SUMANADASA MAWATHA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('109', '7', 'HABAGE 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('110', '7', 'SUMANADASA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('111', '7', 'KANDY ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('112', '7', 'PIRIVENA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('113', '7', 'REST HOUSE ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('114', '7', 'UDAWALPOLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('115', '7', 'RUWAN MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('116', '7', 'SUMANADASA MW 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('117', '7', 'HABAGE 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('118', '7', 'REST HOUSE 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('119', '7', 'UDAWALPOLA HOUSING SCHEME', null, '1', '1');
INSERT INTO `street` VALUES ('120', '7', 'RAJAPIHILLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('121', '7', 'RUWAN BODI MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('130', '8', 'WELAGEDARA STREET', null, '1', '1');
INSERT INTO `street` VALUES ('131', '8', 'BODHIRAJA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('132', '8', 'COLOMBO ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('133', '8', 'KANDY ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('134', '8', 'KUMARATHUNGA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('135', '8', 'MALIYADEVA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('136', '8', 'MOSQUE ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('137', '8', 'PERAKUMBA STREET', null, '1', '1');
INSERT INTO `street` VALUES ('138', '8', 'PUTTLUM ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('139', '8', 'RAJAPIHILLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('140', '8', 'SALPILA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('141', '8', 'THOSATHIRATHNA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('142', '8', 'SAMAJASALAWA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('143', '8', 'DUMBULLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('144', '9', 'DAMBULLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('145', '9', 'ANANDA WEERASINGHE MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('146', '9', 'MITHURU MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('147', '9', 'GETTUWANA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('148', '9', 'KANDY ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('149', '9', 'MALIGAWA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('150', '9', 'PIRIVENA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('151', '9', 'UDAWALPOLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('152', '9', 'WATHHIMI ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('153', '9', 'ANANDA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('154', '9', 'ABEYSEKARA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('155', '9', 'RUWAN MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('156', '10', 'ANDAGALA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('157', '10', 'BAMUNUGEDARA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('158', '10', 'BAMUNUGEDARA ROAD 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('159', '10', 'DAMBULLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('160', '10', 'MALIGAWA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('161', '10', 'LAKE ROUND ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('162', '10', 'LAKE ROUND ROAD 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('163', '10', 'NORTH LAKE ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('164', '10', 'MEDAWAWA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('165', '10', 'WATHHIMI ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('166', '10', 'WELANGOLLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('167', '10', 'WELLAWA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('168', '10', 'SURATHISSA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('169', '10', 'DHARMAPALA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('170', '10', 'GEMUNU MAWATHA (L.S)', null, '1', '1');
INSERT INTO `street` VALUES ('171', '10', 'PARAKUM MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('172', '10', 'SINGHA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('173', '10', 'SRI JOHAN KOTHALAWALA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('174', '10', 'SOUTH LAKE ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('175', '10', 'LAKE ROUND ROAD 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('176', '10', 'UYANWATTA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('177', '10', 'SAHIRA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('178', '10', 'WELAGEDARA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('179', '11', 'DAMBULLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('180', '11', 'PURANAPPU MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('181', '11', 'GETTUWANA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('182', '11', 'LUMBINI MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('183', '11', 'GETTUWANA ROAD 3 RD LANE', null, '1', '1');
INSERT INTO `street` VALUES ('184', '11', 'GETTUWANA ROAD 6 TH LANE', null, '1', '1');
INSERT INTO `street` VALUES ('185', '11', 'KANDY ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('186', '11', 'KOTAKANDA LANE', null, '1', '1');
INSERT INTO `street` VALUES ('187', '11', 'PAHALA GETTUWANA', null, '1', '1');
INSERT INTO `street` VALUES ('188', '11', 'BUWANEKABA MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('189', '11', 'SUBA MAWATHA(GETTUWANA 2 LANE)', null, '1', '1');
INSERT INTO `street` VALUES ('190', '11', 'RUWAN BODHI MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('191', '12', 'PAHALA GETTUWANA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('192', '12', 'HOROWWA LANE', null, '1', '1');
INSERT INTO `street` VALUES ('193', '12', 'KANDY ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('194', '12', 'MOHOMED RIYAL MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('195', '12', 'RAILWAY STATION ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('196', '12', 'UDAYAR MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('197', '12', 'THELIYAGONNA', null, '1', '1');
INSERT INTO `street` VALUES ('198', '12', 'UDAWALPOLA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('199', '12', 'WENDARUWEWA ROAD', null, '1', '1');
INSERT INTO `street` VALUES ('200', '12', 'KETH MAWATHA', null, '1', '1');
INSERT INTO `street` VALUES ('201', '12', 'THELIYAGONNA 2 ND LANE', null, '1', '1');
INSERT INTO `street` VALUES ('202', '12', 'UDAYAR MAWATHA 1 ST LANE', null, '1', '1');
INSERT INTO `street` VALUES ('203', '12', 'UDAYAR MAWATHA 2 ND LANE', null, '1', '1');

-- ----------------------------
-- Table structure for streetsss
-- ----------------------------
DROP TABLE IF EXISTS `streetsss`;
CREATE TABLE `streetsss` (
  `id` int(11) NOT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `ward_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ward_id` (`ward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of streetsss
-- ----------------------------
INSERT INTO `streetsss` VALUES ('1', 'GANGODA ROAD', '1');
INSERT INTO `streetsss` VALUES ('2', 'PUTTLUM ROAD', '1');
INSERT INTO `streetsss` VALUES ('3', 'PUTTLUM ROAD 1 ST LANE', '1');
INSERT INTO `streetsss` VALUES ('4', 'SUMANGALA MAWATHA', '1');
INSERT INTO `streetsss` VALUES ('5', 'WILGODA ROAD', '1');
INSERT INTO `streetsss` VALUES ('6', 'WILGODA ROAD 1 ST LANE', '1');
INSERT INTO `streetsss` VALUES ('7', 'WILGODA ROAD 2 ND LANE', '1');
INSERT INTO `streetsss` VALUES ('8', 'WILGODA ROAD 3 RD LANE', '1');
INSERT INTO `streetsss` VALUES ('9', 'ARIYASINGHALA', '1');
INSERT INTO `streetsss` VALUES ('10', 'ASIRI ROAD', '1');
INSERT INTO `streetsss` VALUES ('11', 'ATHTHADASSI', '1');
INSERT INTO `streetsss` VALUES ('12', 'BODIYA ROAD', '1');
INSERT INTO `streetsss` VALUES ('13', 'MADURA MAWATHA', '1');
INSERT INTO `streetsss` VALUES ('14', 'SAMA MAWATHA [WILGODA 1ST LANE', '1');
INSERT INTO `streetsss` VALUES ('15', 'SALIYA MAWATHA', '1');
INSERT INTO `streetsss` VALUES ('16', 'SUMANGALA MAWATHA 2 ND LANE', '1');
INSERT INTO `streetsss` VALUES ('17', 'SUMANGALA MAWATHA 1 ST LANE', '1');
INSERT INTO `streetsss` VALUES ('18', 'WIJAYA MAWATHA 2 ND LANE', '1');
INSERT INTO `streetsss` VALUES ('19', 'UDYANA MAWATHA', '1');
INSERT INTO `streetsss` VALUES ('20', 'OTHU KUMBURA MAWATHA', '1');
INSERT INTO `streetsss` VALUES ('21', 'WELAGEDARA STREET', '2');
INSERT INTO `streetsss` VALUES ('22', 'PUTTLUM ROAD', '2');
INSERT INTO `streetsss` VALUES ('23', 'SQUARE LANE', '2');
INSERT INTO `streetsss` VALUES ('24', 'WELANGOLLA ROAD', '2');
INSERT INTO `streetsss` VALUES ('25', 'WELANGOLLA RD.2 LAN.[MAHAMEGA]', '2');
INSERT INTO `streetsss` VALUES ('26', 'HINDU DEVALA ROAD', '2');
INSERT INTO `streetsss` VALUES ('27', 'PRASANNA MAWATHA', '2');
INSERT INTO `streetsss` VALUES ('28', 'LAKE ROUND ROAD', '2');
INSERT INTO `streetsss` VALUES ('29', 'SOUTH LAKE ROAD', '2');
INSERT INTO `streetsss` VALUES ('30', 'WELANGOLLA RD.3 LAN.(MANGA.MW)', '2');
INSERT INTO `streetsss` VALUES ('31', 'WELANGOLLA RD.1 LAN.(SAMADI.M)', '2');
INSERT INTO `streetsss` VALUES ('32', 'WELANGOLLA RD.4 TH LA.(UYAN.M)', '2');
INSERT INTO `streetsss` VALUES ('33', 'BAUDDALOKA ROAD 1 ST LANE', '3');
INSERT INTO `streetsss` VALUES ('34', 'JAYA PATHIRANA MAWATHA', '3');
INSERT INTO `streetsss` VALUES ('35', 'BAUDDALOKA ROAD 3 RD LANE', '3');
INSERT INTO `streetsss` VALUES ('36', 'BAUDDALOKA MAWATHA', '3');
INSERT INTO `streetsss` VALUES ('37', 'SENANAYAKE MAWATHA', '3');
INSERT INTO `streetsss` VALUES ('38', 'COLOMBO ROAD', '3');
INSERT INTO `streetsss` VALUES ('39', 'MIHINDU MAWATHA', '3');
INSERT INTO `streetsss` VALUES ('40', 'NEGOMBO ROAD', '3');
INSERT INTO `streetsss` VALUES ('41', 'ST. ANNES STREET', '3');
INSERT INTO `streetsss` VALUES ('42', 'PUTTLUM ROAD', '3');
INSERT INTO `streetsss` VALUES ('43', 'BAUDDALOKA ROAD 2ND LANE', '3');
INSERT INTO `streetsss` VALUES ('44', 'COLOMBO RD:1 LANE(NOYAL SEA:M)', '4');
INSERT INTO `streetsss` VALUES ('45', 'GALWALA ROAD', '4');
INSERT INTO `streetsss` VALUES ('46', 'ILUPPUGEDERA ROAD', '4');
INSERT INTO `streetsss` VALUES ('47', 'SAMAGI MAWATHA', '4');
INSERT INTO `streetsss` VALUES ('48', 'NEGOMBO ROAD', '4');
INSERT INTO `streetsss` VALUES ('49', 'NEGO.RD 1 ST LANE[WAGIRAGNANA]', '4');
INSERT INTO `streetsss` VALUES ('50', 'PUTTALAM ROAD', '4');
INSERT INTO `streetsss` VALUES ('51', 'SUMANGALA MAWATHA', '4');
INSERT INTO `streetsss` VALUES ('52', 'WILGODA ROAD', '4');
INSERT INTO `streetsss` VALUES ('53', 'DHARMAYATHANA ROAD', '4');
INSERT INTO `streetsss` VALUES ('54', 'GAJABA ROAD', '4');
INSERT INTO `streetsss` VALUES ('55', 'ILUPPUGEDARA ROAD (1 ST LANE)', '4');
INSERT INTO `streetsss` VALUES ('56', 'SEPALIKA MAWATHA', '4');
INSERT INTO `streetsss` VALUES ('57', 'SAPUMAL MAWATHA', '4');
INSERT INTO `streetsss` VALUES ('58', 'UPUL MAWATHA', '4');
INSERT INTO `streetsss` VALUES ('59', 'COLOMBO ROAD', '5');
INSERT INTO `streetsss` VALUES ('60', 'NISSANKA MAWATHA 1 ST LANE', '5');
INSERT INTO `streetsss` VALUES ('61', 'NISSANKA MAWATHA 2 ND LANE', '5');
INSERT INTO `streetsss` VALUES ('62', 'NISSANKA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('63', 'MALKADUWAWA CIRCULAR ROAD', '5');
INSERT INTO `streetsss` VALUES ('64', 'SENASUMA MAWATHA [NGD 4TH L]', '5');
INSERT INTO `streetsss` VALUES ('65', 'MUNAMALE MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('66', 'MAHASEN MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('67', 'MALKADUWAWA UYAN PEDESA MW', '5');
INSERT INTO `streetsss` VALUES ('68', 'THAABRU MAWATHA [NGD 2ND LANE]', '5');
INSERT INTO `streetsss` VALUES ('69', 'NEGOMBO ROAD 3 RD LANE', '5');
INSERT INTO `streetsss` VALUES ('70', 'NEGOMBO ROAD', '5');
INSERT INTO `streetsss` VALUES ('71', 'MUHANDIRAM WEERATHUNGA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('72', 'HERALIYAWALA ROAD [NGD 3RD L]', '5');
INSERT INTO `streetsss` VALUES ('73', 'BANDARANAYAKA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('74', 'NANDANA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('75', 'NISSANKA MAWATHA 5 LANE', '5');
INSERT INTO `streetsss` VALUES ('76', 'NISSANKA MAWATHA 3 RD LANE', '5');
INSERT INTO `streetsss` VALUES ('77', 'WAHARA NAVAGANGODA 1ST LANE', '5');
INSERT INTO `streetsss` VALUES ('78', 'PUBUDU MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('79', 'PUBUDU MAWATHA 1 ST LANE', '5');
INSERT INTO `streetsss` VALUES ('80', 'PUBUDU MAWATHA 2 ND LANE', '5');
INSERT INTO `streetsss` VALUES ('81', 'WIJAYABA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('82', 'PEHEKAMHALA ROAD', '5');
INSERT INTO `streetsss` VALUES ('83', 'RAJASINGHE MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('84', 'PANAMBALANA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('85', 'THALGODAPITIYA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('86', 'WEHARA NAWAGANGODA 2ND LANE', '5');
INSERT INTO `streetsss` VALUES ('87', 'BAUDDALOKA MAWATHA', '5');
INSERT INTO `streetsss` VALUES ('88', 'AMUNUWATTA ROAD', '6');
INSERT INTO `streetsss` VALUES ('89', 'COLOMBO ROAD', '6');
INSERT INTO `streetsss` VALUES ('90', 'HABAGE LANE', '6');
INSERT INTO `streetsss` VALUES ('91', 'HENAMULLA ROAD', '6');
INSERT INTO `streetsss` VALUES ('92', 'JAYANTHIPURA ROAD', '6');
INSERT INTO `streetsss` VALUES ('93', 'KANDAUDAWATTA ROAD', '6');
INSERT INTO `streetsss` VALUES ('94', 'RAJAPHILLA ROAD', '6');
INSERT INTO `streetsss` VALUES ('95', 'COLOMBO ROAD 2 ND LANE', '6');
INSERT INTO `streetsss` VALUES ('96', 'UDAWALPOLA ROAD', '6');
INSERT INTO `streetsss` VALUES ('97', 'WILBAWA DEVALA ROAD', '6');
INSERT INTO `streetsss` VALUES ('98', 'HABAGE 2 ND LANE', '6');
INSERT INTO `streetsss` VALUES ('99', 'HABAGE 1 ST LANE', '6');
INSERT INTO `streetsss` VALUES ('100', 'UDAWALPOLA 3 RD LANE', '6');
INSERT INTO `streetsss` VALUES ('101', 'UDAWALPOLA 2 ND LANE', '6');
INSERT INTO `streetsss` VALUES ('102', 'UDAWALPOLA I ST LANE', '6');
INSERT INTO `streetsss` VALUES ('103', 'AMUNAWATTHA PEDESA', '6');
INSERT INTO `streetsss` VALUES ('104', 'JAYANTHIPURA BALADAKSA MAWATHA', '6');
INSERT INTO `streetsss` VALUES ('105', 'SOUTH ROUND ROAD 4TH LANE', '6');
INSERT INTO `streetsss` VALUES ('106', 'WAHARAWATHTHA ROAD', '6');
INSERT INTO `streetsss` VALUES ('107', 'UDAWALPOLA 1 ST LANE', '7');
INSERT INTO `streetsss` VALUES ('108', 'SUMANADASA MAWATHA 1 ST LANE', '7');
INSERT INTO `streetsss` VALUES ('109', 'HABAGE 2 ND LANE', '7');
INSERT INTO `streetsss` VALUES ('110', 'SUMANADASA MAWATHA', '7');
INSERT INTO `streetsss` VALUES ('111', 'KANDY ROAD', '7');
INSERT INTO `streetsss` VALUES ('112', 'PIRIVENA ROAD', '7');
INSERT INTO `streetsss` VALUES ('113', 'REST HOUSE ROAD', '7');
INSERT INTO `streetsss` VALUES ('114', 'UDAWALPOLA ROAD', '7');
INSERT INTO `streetsss` VALUES ('115', 'RUWAN MAWATHA', '7');
INSERT INTO `streetsss` VALUES ('116', 'SUMANADASA MW 2 ND LANE', '7');
INSERT INTO `streetsss` VALUES ('117', 'HABAGE 1 ST LANE', '7');
INSERT INTO `streetsss` VALUES ('118', 'REST HOUSE 1 ST LANE', '7');
INSERT INTO `streetsss` VALUES ('119', 'UDAWALPOLA HOUSING SCHEME', '7');
INSERT INTO `streetsss` VALUES ('120', 'RAJAPIHILLA ROAD', '7');
INSERT INTO `streetsss` VALUES ('121', 'RUWAN BODI MAWATHA', '7');
INSERT INTO `streetsss` VALUES ('130', 'WELAGEDARA STREET', '8');
INSERT INTO `streetsss` VALUES ('131', 'BODHIRAJA MAWATHA', '8');
INSERT INTO `streetsss` VALUES ('132', 'COLOMBO ROAD', '8');
INSERT INTO `streetsss` VALUES ('133', 'KANDY ROAD', '8');
INSERT INTO `streetsss` VALUES ('134', 'KUMARATHUNGA MAWATHA', '8');
INSERT INTO `streetsss` VALUES ('135', 'MALIYADEVA MAWATHA', '8');
INSERT INTO `streetsss` VALUES ('136', 'MOSQUE ROAD', '8');
INSERT INTO `streetsss` VALUES ('137', 'PERAKUMBA STREET', '8');
INSERT INTO `streetsss` VALUES ('138', 'PUTTLUM ROAD', '8');
INSERT INTO `streetsss` VALUES ('139', 'RAJAPIHILLA ROAD', '8');
INSERT INTO `streetsss` VALUES ('140', 'SALPILA ROAD', '8');
INSERT INTO `streetsss` VALUES ('141', 'THOSATHIRATHNA MAWATHA', '8');
INSERT INTO `streetsss` VALUES ('142', 'SAMAJASALAWA ROAD', '8');
INSERT INTO `streetsss` VALUES ('143', 'DUMBULLA ROAD', '8');
INSERT INTO `streetsss` VALUES ('144', 'DAMBULLA ROAD', '9');
INSERT INTO `streetsss` VALUES ('145', 'ANANDA WEERASINGHE MAWATHA', '9');
INSERT INTO `streetsss` VALUES ('146', 'MITHURU MAWATHA', '9');
INSERT INTO `streetsss` VALUES ('147', 'GETTUWANA ROAD', '9');
INSERT INTO `streetsss` VALUES ('148', 'KANDY ROAD', '9');
INSERT INTO `streetsss` VALUES ('149', 'MALIGAWA ROAD', '9');
INSERT INTO `streetsss` VALUES ('150', 'PIRIVENA ROAD', '9');
INSERT INTO `streetsss` VALUES ('151', 'UDAWALPOLA ROAD', '9');
INSERT INTO `streetsss` VALUES ('152', 'WATHHIMI ROAD', '9');
INSERT INTO `streetsss` VALUES ('153', 'ANANDA MAWATHA', '9');
INSERT INTO `streetsss` VALUES ('154', 'ABEYSEKARA MAWATHA', '9');
INSERT INTO `streetsss` VALUES ('155', 'RUWAN MAWATHA', '9');
INSERT INTO `streetsss` VALUES ('156', 'ANDAGALA ROAD', '10');
INSERT INTO `streetsss` VALUES ('157', 'BAMUNUGEDARA ROAD', '10');
INSERT INTO `streetsss` VALUES ('158', 'BAMUNUGEDARA ROAD 2 ND LANE', '10');
INSERT INTO `streetsss` VALUES ('159', 'DAMBULLA ROAD', '10');
INSERT INTO `streetsss` VALUES ('160', 'MALIGAWA ROAD', '10');
INSERT INTO `streetsss` VALUES ('161', 'LAKE ROUND ROAD', '10');
INSERT INTO `streetsss` VALUES ('162', 'LAKE ROUND ROAD 2 ND LANE', '10');
INSERT INTO `streetsss` VALUES ('163', 'NORTH LAKE ROAD', '10');
INSERT INTO `streetsss` VALUES ('164', 'MEDAWAWA ROAD', '10');
INSERT INTO `streetsss` VALUES ('165', 'WATHHIMI ROAD', '10');
INSERT INTO `streetsss` VALUES ('166', 'WELANGOLLA ROAD', '10');
INSERT INTO `streetsss` VALUES ('167', 'WELLAWA ROAD', '10');
INSERT INTO `streetsss` VALUES ('168', 'SURATHISSA ROAD', '10');
INSERT INTO `streetsss` VALUES ('169', 'DHARMAPALA MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('170', 'GEMUNU MAWATHA (L.S)', '10');
INSERT INTO `streetsss` VALUES ('171', 'PARAKUM MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('172', 'SINGHA MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('173', 'SRI JOHAN KOTHALAWALA MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('174', 'SOUTH LAKE ROAD', '10');
INSERT INTO `streetsss` VALUES ('175', 'LAKE ROUND ROAD 1 ST LANE', '10');
INSERT INTO `streetsss` VALUES ('176', 'UYANWATTA MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('177', 'SAHIRA MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('178', 'WELAGEDARA MAWATHA', '10');
INSERT INTO `streetsss` VALUES ('179', 'DAMBULLA ROAD', '11');
INSERT INTO `streetsss` VALUES ('180', 'PURANAPPU MAWATHA', '11');
INSERT INTO `streetsss` VALUES ('181', 'GETTUWANA ROAD', '11');
INSERT INTO `streetsss` VALUES ('182', 'LUMBINI MAWATHA', '11');
INSERT INTO `streetsss` VALUES ('183', 'GETTUWANA ROAD 3 RD LANE', '11');
INSERT INTO `streetsss` VALUES ('184', 'GETTUWANA ROAD 6 TH LANE', '11');
INSERT INTO `streetsss` VALUES ('185', 'KANDY ROAD', '11');
INSERT INTO `streetsss` VALUES ('186', 'KOTAKANDA LANE', '11');
INSERT INTO `streetsss` VALUES ('187', 'PAHALA GETTUWANA', '11');
INSERT INTO `streetsss` VALUES ('188', 'BUWANEKABA MAWATHA', '11');
INSERT INTO `streetsss` VALUES ('189', 'SUBA MAWATHA(GETTUWANA 2 LANE)', '11');
INSERT INTO `streetsss` VALUES ('190', 'RUWAN BODHI MAWATHA', '11');
INSERT INTO `streetsss` VALUES ('191', 'PAHALA GETTUWANA 1 ST LANE', '12');
INSERT INTO `streetsss` VALUES ('192', 'HOROWWA LANE', '12');
INSERT INTO `streetsss` VALUES ('193', 'KANDY ROAD', '12');
INSERT INTO `streetsss` VALUES ('194', 'MOHOMED RIYAL MAWATHA', '12');
INSERT INTO `streetsss` VALUES ('195', 'RAILWAY STATION ROAD', '12');
INSERT INTO `streetsss` VALUES ('196', 'UDAYAR MAWATHA', '12');
INSERT INTO `streetsss` VALUES ('197', 'THELIYAGONNA', '12');
INSERT INTO `streetsss` VALUES ('198', 'UDAWALPOLA ROAD', '12');
INSERT INTO `streetsss` VALUES ('199', 'WENDARUWEWA ROAD', '12');
INSERT INTO `streetsss` VALUES ('200', 'KETH MAWATHA', '12');
INSERT INTO `streetsss` VALUES ('201', 'THELIYAGONNA 2 ND LANE', '12');
INSERT INTO `streetsss` VALUES ('202', 'UDAYAR MAWATHA 1 ST LANE', '12');
INSERT INTO `streetsss` VALUES ('203', 'UDAYAR MAWATHA 2 ND LANE', '12');

-- ----------------------------
-- Table structure for sub_nature
-- ----------------------------
DROP TABLE IF EXISTS `sub_nature`;
CREATE TABLE `sub_nature` (
  `idSub_Nature` int(11) NOT NULL AUTO_INCREMENT,
  `Trade_Nature_idTrade_Nature` int(11) NOT NULL,
  `sub_nature_no` varchar(45) DEFAULT NULL,
  `sub_nature` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSub_Nature`),
  KEY `fk_Sub_Nature_Trade_Nature1_idx` (`Trade_Nature_idTrade_Nature`),
  CONSTRAINT `fk_Sub_Nature_Trade_Nature1` FOREIGN KEY (`Trade_Nature_idTrade_Nature`) REFERENCES `trade_nature` (`idTrade_Nature`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_nature
-- ----------------------------

-- ----------------------------
-- Table structure for tax_rate
-- ----------------------------
DROP TABLE IF EXISTS `tax_rate`;
CREATE TABLE `tax_rate` (
  `idTax_Rate` int(11) NOT NULL AUTO_INCREMENT,
  `min` double DEFAULT NULL,
  `max` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `taxType` varchar(45) DEFAULT NULL,
  `taxTypeNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTax_Rate`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tax_rate
-- ----------------------------
INSERT INTO `tax_rate` VALUES ('1', '0', '6000', '0', '1', 'TradeLicens', '1');
INSERT INTO `tax_rate` VALUES ('2', '6001', '12000', '90', '1', 'TradeLicens', '1');
INSERT INTO `tax_rate` VALUES ('3', '12001', '18750', '180', '1', 'TradeLicens', '1');
INSERT INTO `tax_rate` VALUES ('4', '18750', '75000', '360', '1', 'TradeLicens', '1');
INSERT INTO `tax_rate` VALUES ('5', '75001', '15000', '1200', '1', 'TradeLicens', '1');
INSERT INTO `tax_rate` VALUES ('6', '150001', '1.7976931348623157e308', '5000', '1', 'TradeLicens', '1');

-- ----------------------------
-- Table structure for tax_value
-- ----------------------------
DROP TABLE IF EXISTS `tax_value`;
CREATE TABLE `tax_value` (
  `idTax_Value` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idTax_Value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tax_value
-- ----------------------------

-- ----------------------------
-- Table structure for trade_license
-- ----------------------------
DROP TABLE IF EXISTS `trade_license`;
CREATE TABLE `trade_license` (
  `idTrade_License` int(11) NOT NULL AUTO_INCREMENT,
  `Payment_idPayment` int(11) NOT NULL,
  `licen_no` varchar(45) DEFAULT NULL,
  `trade_license_date` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  `Application_idApplication` int(11) NOT NULL,
  PRIMARY KEY (`idTrade_License`),
  KEY `fk_Trade_License_Payment1_idx` (`Payment_idPayment`),
  KEY `fk_Trade_License_Application1_idx` (`Application_idApplication`),
  CONSTRAINT `fk_Trade_License_Application1` FOREIGN KEY (`Application_idApplication`) REFERENCES `application` (`idApplication`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trade_License_Payment1` FOREIGN KEY (`Payment_idPayment`) REFERENCES `payment` (`idPayment`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_license
-- ----------------------------

-- ----------------------------
-- Table structure for trade_nature
-- ----------------------------
DROP TABLE IF EXISTS `trade_nature`;
CREATE TABLE `trade_nature` (
  `idTrade_Nature` int(11) NOT NULL AUTO_INCREMENT,
  `Trade_Type_idTrade_Type` int(11) NOT NULL,
  `nature_no` varchar(45) DEFAULT NULL,
  `nature` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTrade_Nature`),
  KEY `fk_Trade_Nature_Trade_Type1_idx` (`Trade_Type_idTrade_Type`),
  CONSTRAINT `fk_Trade_Nature_Trade_Type1` FOREIGN KEY (`Trade_Type_idTrade_Type`) REFERENCES `trade_type` (`idTrade_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_nature
-- ----------------------------

-- ----------------------------
-- Table structure for trade_type
-- ----------------------------
DROP TABLE IF EXISTS `trade_type`;
CREATE TABLE `trade_type` (
  `idTrade_Type` int(11) NOT NULL AUTO_INCREMENT,
  `Vort_idVort` int(11) NOT NULL,
  `type_name` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTrade_Type`),
  KEY `fk_Trade_Type_Vort1_idx` (`Vort_idVort`),
  CONSTRAINT `fk_Trade_Type_Vort1` FOREIGN KEY (`Vort_idVort`) REFERENCES `vort` (`idVort`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_type
-- ----------------------------
INSERT INTO `trade_type` VALUES ('1', '1', 'Trade License ', '1', '1');
INSERT INTO `trade_type` VALUES ('2', '2', 'Business Tax', '1', '1');
INSERT INTO `trade_type` VALUES ('3', '3', 'Industrial Tax', '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) DEFAULT NULL,
  `nic` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `sq` varchar(255) DEFAULT NULL,
  `sqa` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'super', '000000000', '000000000', '2018-01-01', 'xx', 'xx', '1', '1');

-- ----------------------------
-- Table structure for user_has_catagory
-- ----------------------------
DROP TABLE IF EXISTS `user_has_catagory`;
CREATE TABLE `user_has_catagory` (
  `idUser_has_Catagory` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) NOT NULL,
  `Catagory_idCatagory` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser_has_Catagory`),
  KEY `fk_User_has_Catagory_Catagory1_idx` (`Catagory_idCatagory`),
  KEY `fk_User_has_Catagory_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_User_has_Catagory_Catagory1` FOREIGN KEY (`Catagory_idCatagory`) REFERENCES `catagory` (`idCatagory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Catagory_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_has_catagory
-- ----------------------------

-- ----------------------------
-- Table structure for user_has_department
-- ----------------------------
DROP TABLE IF EXISTS `user_has_department`;
CREATE TABLE `user_has_department` (
  `idUser_has_Department` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) NOT NULL,
  `Department_idDepartment` int(11) NOT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser_has_Department`),
  KEY `fk_User_has_Department_Department1_idx` (`Department_idDepartment`),
  KEY `fk_User_has_Department_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_User_has_Department_Department1` FOREIGN KEY (`Department_idDepartment`) REFERENCES `department` (`idDepartment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Department_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_has_department
-- ----------------------------

-- ----------------------------
-- Table structure for user_has_otheritiscat
-- ----------------------------
DROP TABLE IF EXISTS `user_has_otheritiscat`;
CREATE TABLE `user_has_otheritiscat` (
  `idUser_has_OtheritisCat` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) NOT NULL,
  `OtheritisCat_idOtheritisCat` int(11) NOT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser_has_OtheritisCat`),
  KEY `fk_User_has_OtheritisCat_OtheritisCat1_idx` (`OtheritisCat_idOtheritisCat`),
  KEY `fk_User_has_OtheritisCat_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_User_has_OtheritisCat_OtheritisCat1` FOREIGN KEY (`OtheritisCat_idOtheritisCat`) REFERENCES `otheritiscat` (`idOtheritisCat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_OtheritisCat_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_has_otheritiscat
-- ----------------------------

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `idUser_Log` int(11) NOT NULL AUTO_INCREMENT,
  `out_tyme` datetime DEFAULT NULL,
  `in_tyme` datetime DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser_Log`),
  KEY `fk_User_Log_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_User_Log_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_log
-- ----------------------------

-- ----------------------------
-- Table structure for vort
-- ----------------------------
DROP TABLE IF EXISTS `vort`;
CREATE TABLE `vort` (
  `idVort` int(11) NOT NULL AUTO_INCREMENT,
  `Program_SubTitle_idProgram_SubTitle` int(11) NOT NULL,
  `vote_no` varchar(45) DEFAULT NULL,
  `vote_name` varchar(45) DEFAULT NULL,
  `vote_current_balance` double DEFAULT NULL,
  `vote_alocation` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  `Bank_Info_idBank_Info` int(11) NOT NULL,
  PRIMARY KEY (`idVort`),
  KEY `fk_Vort_Program_SubTitle1_idx` (`Program_SubTitle_idProgram_SubTitle`),
  KEY `fk_Vort_Bank_Info1_idx` (`Bank_Info_idBank_Info`),
  CONSTRAINT `fk_Vort_Bank_Info1` FOREIGN KEY (`Bank_Info_idBank_Info`) REFERENCES `bank_info` (`idBank_Info`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vort_Program_SubTitle1` FOREIGN KEY (`Program_SubTitle_idProgram_SubTitle`) REFERENCES `program_subtitle` (`idProgram_SubTitle`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vort
-- ----------------------------
INSERT INTO `vort` VALUES ('1', '1', '10333', 'වෙලඳ බලපත්‍ර', '0', '100000', '1', '1', '1');
INSERT INTO `vort` VALUES ('2', '1', '10333(I)', 'ව්‍යාපාර බදු', '0', '100000', '1', '1', '1');
INSERT INTO `vort` VALUES ('3', '1', '10333(II)', 'කර්මාන්ත බදු', '0', '100000', '1', '1', '1');

-- ----------------------------
-- Table structure for ward
-- ----------------------------
DROP TABLE IF EXISTS `ward`;
CREATE TABLE `ward` (
  `idWard` int(11) NOT NULL AUTO_INCREMENT,
  `ward_no` varchar(45) DEFAULT NULL,
  `ward_name` varchar(45) DEFAULT NULL,
  `Wardcol` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `syn` int(11) DEFAULT NULL,
  PRIMARY KEY (`idWard`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ward
-- ----------------------------
INSERT INTO `ward` VALUES ('1', '', 'WARD 01', null, '1', '1');
INSERT INTO `ward` VALUES ('2', null, 'WARD 02', null, '1', '1');
INSERT INTO `ward` VALUES ('3', null, 'WARD 03', null, '1', '1');
INSERT INTO `ward` VALUES ('4', null, 'WARD 04', null, '1', '1');
INSERT INTO `ward` VALUES ('5', null, 'WARD 05', null, '1', '1');
INSERT INTO `ward` VALUES ('6', null, 'WARD 06', null, '1', '1');
INSERT INTO `ward` VALUES ('7', null, 'WARD 07', null, '1', '1');
INSERT INTO `ward` VALUES ('8', null, 'WARD 08', null, '1', '1');
INSERT INTO `ward` VALUES ('9', null, 'WARD 09', null, '1', '1');
INSERT INTO `ward` VALUES ('10', null, 'WARD 10', null, '1', '1');
INSERT INTO `ward` VALUES ('11', null, 'WARD 11', null, '1', '1');
INSERT INTO `ward` VALUES ('12', null, 'WARD 12', null, '1', '1');
