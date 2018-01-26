/*
Navicat MySQL Data Transfer

Source Server         : Localhost Mysql
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-01-26 08:27:14
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of street
-- ----------------------------

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
