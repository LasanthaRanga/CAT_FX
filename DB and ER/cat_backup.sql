/*
Navicat MySQL Data Transfer

Source Server         : Localhost Mysql
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : cat

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-01-24 11:28:52
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
  `Sub_Nature_idSub_Nature` int(11) NOT NULL,
  `User_idUser` int(11) NOT NULL DEFAULT '0' COMMENT 'RO Catagary Eke User\nApplication Eka aragena awa kena',
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('1', '1', '1', '2', '1', '2', '2018-01-22', '2018', '1', '45000', null, 'asfd', 'asfd', 'asdf', 'asf', null, '4500', 'asfsdf', '1', '1', '1', '1');
INSERT INTO `application` VALUES ('2', '1', '1', '2', '1', '2', '2018-01-22', '2018', '1', '23', null, 'asdf', 'asdf', 'asdf', 'asdf', null, '23', 'asdfsadf asdf sdfsd sdf ', '1', '1', '1', '1');
INSERT INTO `application` VALUES ('3', '1', '1', '2', '1', '2', '2018-01-22', '2018', '1', '2323', null, 'asfd', 'asf', 'asfda', 'asdf', null, '2323', 'asdfasdf asdf fs asdf', '1', '1', '1', '0');
INSERT INTO `application` VALUES ('4', '1', '1', '2', '1', '2', '2018-01-22', '2018', '1', '233', null, 'asdfa', 'asdfasdf', 'asdf', 'asdfafd', null, '2323', 'asfdasdffaf', '1', '1', '1', '0');
INSERT INTO `application` VALUES ('5', '1', '1', '2', '1', '2', '2018-01-22', '2018', '1', '55', null, 'sdgs', 'sdgsdg', 'sdgsdg', 'sdgsdg', null, '555', 'sdgsdgdg', '1', '1', '1', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apprualstatues
-- ----------------------------
INSERT INTO `apprualstatues` VALUES ('1', '4', '1', '0', 'asdfsadf', '2018-01-22', '1', '1');
INSERT INTO `apprualstatues` VALUES ('2', '4', '1', '0', 'asdfsfd', '2018-01-22', '1', '2');
INSERT INTO `apprualstatues` VALUES ('3', '4', '2', '0', 'asfdasfd', '2018-01-22', '1', '1');
INSERT INTO `apprualstatues` VALUES ('4', '4', '3', '0', 'asfasfgt', '2018-01-22', '1', '1');
INSERT INTO `apprualstatues` VALUES ('5', '4', '4', '0', 'asfasftt', '2018-01-22', '1', '1');
INSERT INTO `apprualstatues` VALUES ('6', '3', '4', '1', 'approve', '2018-01-23', '1', '2');
INSERT INTO `apprualstatues` VALUES ('7', '4', '4', '1', 'Ela eLa ', '2018-01-23', '1', '3');
INSERT INTO `apprualstatues` VALUES ('8', '2', '5', '0', 'asfasfdf', '2018-01-22', '1', '1');
INSERT INTO `apprualstatues` VALUES ('9', '3', '4', '2', 'මොකක් හරි ', '2018-01-23', '1', '2');

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
INSERT INTO `bank_info` VALUES ('1', '1', '1', '1', '1', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catagory
-- ----------------------------
INSERT INTO `catagory` VALUES ('1', 'Trade Licens', null, null, '1', '1');
INSERT INTO `catagory` VALUES ('2', 'Trade Type', null, null, '1', '1');
INSERT INTO `catagory` VALUES ('3', 'Trade Nature', null, null, '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '1', 'asfd', 'asdf', 'sadf', 'sadf', 'sdfasdf', 'asdf', 'Kurunegala', '1', '1');
INSERT INTO `contact` VALUES ('2', '2', 'asdf', 'asdf', 'asdf', 'asdf', 'asdf', 'asdf', 'Kurunegala', '1', '1');
INSERT INTO `contact` VALUES ('3', '3', 'sdf', 'asdf', 'sdf', 'sdf', 'sadf', 'asdf', 'Kurunegala', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '2', '12345', 'asdf asdf ', null, '2018-01-22', '1', '1');
INSERT INTO `customer` VALUES ('2', '2', '23456', 'asdf asdf ', null, '2018-01-22', '1', '1');
INSERT INTO `customer` VALUES ('3', '2', '34567', 'asdf', null, '2018-01-22', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'd1', null, '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('1', '1', 'admin', 'admin', '1', '1');
INSERT INTO `login` VALUES ('2', '2', 'asdf', 'asdf', '1', '1');
INSERT INTO `login` VALUES ('3', '3', 'b', 'b', '1', '1');
INSERT INTO `login` VALUES ('4', '4', 'f', 'f', '1', '1');
INSERT INTO `login` VALUES ('5', '5', 'normal', 'normal', '1', '1');
INSERT INTO `login` VALUES ('6', '6', 'r1', 'r1', '1', '1');
INSERT INTO `login` VALUES ('7', '7', 'r2', 'r2', '1', '1');
INSERT INTO `login` VALUES ('8', '8', 'r3', 'r3', '1', '1');
INSERT INTO `login` VALUES ('9', '9', 'Lasa', 'Lasa', '1', '1');
INSERT INTO `login` VALUES ('10', '10', 'supem', 'supem', '1', '1');
INSERT INTO `login` VALUES ('11', '11', 'sdfg', 'sdfg', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of otheritiscat
-- ----------------------------
INSERT INTO `otheritiscat` VALUES ('1', 'RO', '', '1', '1');
INSERT INTO `otheritiscat` VALUES ('2', 'PHI', '', '1', '1');
INSERT INTO `otheritiscat` VALUES ('3', 'MOH', '', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES ('1', '1', '1', '1', '1');

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
INSERT INTO `program_subtitle` VALUES ('1', '1', '1', '1', '1', '1');

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
INSERT INTO `program_title` VALUES ('1', '1', '1', '1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of street
-- ----------------------------
INSERT INTO `street` VALUES ('1', '1', 'asdfasfd', null, '1', '1');
INSERT INTO `street` VALUES ('2', '2', 'asdfasdfsadf', null, '1', '1');
INSERT INTO `street` VALUES ('3', '2', 'asdfsdf', null, '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_nature
-- ----------------------------
INSERT INTO `sub_nature` VALUES ('1', '2', 'asdf', 'sdf', '1', '1');
INSERT INTO `sub_nature` VALUES ('2', '2', 'asdf', 'sadf', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_nature
-- ----------------------------
INSERT INTO `trade_nature` VALUES ('1', '1', '12', 'adfsff', '1', '1');
INSERT INTO `trade_nature` VALUES ('2', '1', '12', 'adfs', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_type
-- ----------------------------
INSERT INTO `trade_type` VALUES ('1', '1', 'asdfasf', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'super', '000000000', '000000000', '2018-01-01', 'xx', 'xx', '1', '1');
INSERT INTO `user` VALUES ('2', 'asdff', 'asdf', 'asdf', '2018-01-22', 'What was your childhood nickname?', 'asdf', '1', '1');
INSERT INTO `user` VALUES ('3', 'asdffff', 'asdf', 'asdf', '2018-01-22', 'What was your childhood nickname?', 'asdf', '1', '1');
INSERT INTO `user` VALUES ('4', 'asdfasdf', 'asdfasdf', 'asdfasdf', '2018-01-22', 'What was your childhood nickname?', 'asdf', '1', '1');
INSERT INTO `user` VALUES ('5', 'normal', 'normal', 'normal', '2018-01-23', 'What was your childhood nickname?', 'normal', '1', '1');
INSERT INTO `user` VALUES ('6', 'ro1111', 'asdfasf', 'asdfsdf', '2018-01-23', 'What was your childhood nickname?', 'asdfsfd', '1', '1');
INSERT INTO `user` VALUES ('7', 'ro222', 'asdfasf', 'asdfsdf', '2018-01-23', 'What was your childhood nickname?', 'asdfsfd', '1', '1');
INSERT INTO `user` VALUES ('8', 'ro333', 'asdfasf', 'asdfsdf', '2018-01-23', 'What was your childhood nickname?', 'asdfsfd', '1', '1');
INSERT INTO `user` VALUES ('9', 'Lasantha', '883554469V', '0788749390', '2018-01-24', 'What was your childhood nickname?', 'Rembo', '1', '1');
INSERT INTO `user` VALUES ('10', 'supmepa', 'nic', 'mobile', '2018-01-24', 'What is the middle name of your oldest child?', 'what', '1', '1');
INSERT INTO `user` VALUES ('11', 'sdfg', 'sdfg', 'sdfg', '2018-01-24', 'What was your favorite sport in high school?', 'sdfg', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_has_catagory
-- ----------------------------
INSERT INTO `user_has_catagory` VALUES ('1', '2', '1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_has_department
-- ----------------------------
INSERT INTO `user_has_department` VALUES ('1', '2', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_has_otheritiscat
-- ----------------------------
INSERT INTO `user_has_otheritiscat` VALUES ('1', '2', '1', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('2', '3', '2', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('3', '4', '3', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('4', '2', '2', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('5', '4', '1', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('6', '4', '2', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('7', '4', '3', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('8', '6', '1', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('9', '7', '1', '1');
INSERT INTO `user_has_otheritiscat` VALUES ('10', '8', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('1', null, '2018-01-22 13:41:38', '1', '1', '1');
INSERT INTO `user_log` VALUES ('2', null, '2018-01-22 13:44:23', '2', '1', '1');
INSERT INTO `user_log` VALUES ('3', null, '2018-01-22 13:46:34', '2', '1', '1');
INSERT INTO `user_log` VALUES ('4', null, '2018-01-22 13:50:25', '2', '1', '1');
INSERT INTO `user_log` VALUES ('5', null, '2018-01-22 13:52:49', '2', '1', '1');
INSERT INTO `user_log` VALUES ('6', null, '2018-01-22 14:07:09', '2', '1', '1');
INSERT INTO `user_log` VALUES ('7', null, '2018-01-22 14:10:40', '2', '1', '1');
INSERT INTO `user_log` VALUES ('8', null, '2018-01-22 14:20:47', '2', '1', '1');
INSERT INTO `user_log` VALUES ('9', null, '2018-01-22 14:27:02', '2', '1', '1');
INSERT INTO `user_log` VALUES ('10', null, '2018-01-22 14:28:07', '2', '1', '1');
INSERT INTO `user_log` VALUES ('11', null, '2018-01-22 15:03:49', '2', '1', '1');
INSERT INTO `user_log` VALUES ('12', null, '2018-01-22 15:06:23', '2', '1', '1');
INSERT INTO `user_log` VALUES ('13', null, '2018-01-22 15:20:47', '2', '1', '1');
INSERT INTO `user_log` VALUES ('14', null, '2018-01-22 15:22:16', '2', '1', '1');
INSERT INTO `user_log` VALUES ('15', null, '2018-01-22 15:25:22', '2', '1', '1');
INSERT INTO `user_log` VALUES ('16', null, '2018-01-22 15:26:46', '2', '1', '1');
INSERT INTO `user_log` VALUES ('17', null, '2018-01-22 15:29:33', '2', '1', '1');
INSERT INTO `user_log` VALUES ('18', null, '2018-01-22 15:31:36', '1', '1', '1');
INSERT INTO `user_log` VALUES ('19', null, '2018-01-22 15:33:44', '1', '1', '1');
INSERT INTO `user_log` VALUES ('20', null, '2018-01-22 15:35:14', '1', '1', '1');
INSERT INTO `user_log` VALUES ('21', null, '2018-01-22 15:36:22', '4', '1', '1');
INSERT INTO `user_log` VALUES ('22', null, '2018-01-22 15:37:55', '4', '1', '1');
INSERT INTO `user_log` VALUES ('23', null, '2018-01-22 15:40:17', '4', '1', '1');
INSERT INTO `user_log` VALUES ('24', null, '2018-01-22 15:45:12', '4', '1', '1');
INSERT INTO `user_log` VALUES ('25', null, '2018-01-22 15:50:02', '4', '1', '1');
INSERT INTO `user_log` VALUES ('26', null, '2018-01-22 15:53:08', '4', '1', '1');
INSERT INTO `user_log` VALUES ('27', null, '2018-01-22 16:00:24', '4', '1', '1');
INSERT INTO `user_log` VALUES ('28', null, '2018-01-22 16:54:42', '2', '1', '1');
INSERT INTO `user_log` VALUES ('29', null, '2018-01-22 16:57:40', '2', '1', '1');
INSERT INTO `user_log` VALUES ('30', null, '2018-01-22 17:02:14', '2', '1', '1');
INSERT INTO `user_log` VALUES ('31', null, '2018-01-22 17:20:25', '2', '1', '1');
INSERT INTO `user_log` VALUES ('32', null, '2018-01-22 17:24:07', '2', '1', '1');
INSERT INTO `user_log` VALUES ('33', null, '2018-01-22 17:28:14', '2', '1', '1');
INSERT INTO `user_log` VALUES ('34', null, '2018-01-22 17:32:08', '2', '1', '1');
INSERT INTO `user_log` VALUES ('36', null, '2018-01-23 03:22:36', '3', '1', '1');
INSERT INTO `user_log` VALUES ('37', null, '2018-01-23 03:24:07', '3', '1', '1');
INSERT INTO `user_log` VALUES ('38', null, '2018-01-23 03:24:51', '2', '1', '1');
INSERT INTO `user_log` VALUES ('39', null, '2018-01-23 03:25:23', '4', '1', '1');
INSERT INTO `user_log` VALUES ('40', null, '2018-01-23 03:25:48', '1', '1', '1');
INSERT INTO `user_log` VALUES ('41', null, '2018-01-23 04:01:49', '3', '1', '1');
INSERT INTO `user_log` VALUES ('42', null, '2018-01-23 04:02:29', '1', '1', '1');
INSERT INTO `user_log` VALUES ('43', null, '2018-01-23 04:03:32', '2', '1', '1');
INSERT INTO `user_log` VALUES ('44', null, '2018-01-23 05:03:01', '3', '1', '1');
INSERT INTO `user_log` VALUES ('46', null, '2018-01-23 05:05:58', '1', '1', '1');
INSERT INTO `user_log` VALUES ('47', null, '2018-01-23 05:07:05', '5', '1', '1');
INSERT INTO `user_log` VALUES ('53', null, '2018-01-23 10:06:28', '2', '1', '1');
INSERT INTO `user_log` VALUES ('60', null, '2018-01-23 10:53:42', '3', '1', '1');
INSERT INTO `user_log` VALUES ('64', null, '2018-01-23 11:18:45', '4', '1', '1');
INSERT INTO `user_log` VALUES ('82', null, '2018-01-23 12:26:55', '4', '1', '1');
INSERT INTO `user_log` VALUES ('85', null, '2018-01-23 12:36:58', '3', '1', '1');
INSERT INTO `user_log` VALUES ('94', null, '2018-01-23 14:00:05', '5', '1', '1');
INSERT INTO `user_log` VALUES ('95', null, '2018-01-23 14:04:30', '3', '1', '1');
INSERT INTO `user_log` VALUES ('98', null, '2018-01-23 14:12:57', '1', '1', '1');
INSERT INTO `user_log` VALUES ('100', null, '2018-01-23 14:16:06', '1', '1', '1');
INSERT INTO `user_log` VALUES ('101', null, '2018-01-23 14:17:30', '1', '1', '1');
INSERT INTO `user_log` VALUES ('103', null, '2018-01-23 14:20:06', '5', '1', '1');
INSERT INTO `user_log` VALUES ('104', null, '2018-01-23 14:25:08', '5', '1', '1');
INSERT INTO `user_log` VALUES ('106', null, '2018-01-24 01:50:07', '1', '1', '1');
INSERT INTO `user_log` VALUES ('107', null, '2018-01-24 10:45:40', '1', '1', '1');
INSERT INTO `user_log` VALUES ('108', null, '2018-01-24 10:55:58', '1', '1', '1');
INSERT INTO `user_log` VALUES ('109', null, '2018-01-24 11:04:22', '1', '1', '1');
INSERT INTO `user_log` VALUES ('110', null, '2018-01-24 11:06:25', '1', '1', '1');
INSERT INTO `user_log` VALUES ('111', null, '2018-01-24 11:09:39', '1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vort
-- ----------------------------
INSERT INTO `vort` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ward
-- ----------------------------
INSERT INTO `ward` VALUES ('1', null, 'ward', null, '1', '1');
INSERT INTO `ward` VALUES ('2', null, 'ward2', null, '1', '1');
INSERT INTO `ward` VALUES ('3', null, 'wared3', null, '1', '1');
