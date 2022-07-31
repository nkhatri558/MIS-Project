/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.4.11-MariaDB : Database - mis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mis` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mis`;

/*Table structure for table `academic_information_board` */

DROP TABLE IF EXISTS `academic_information_board`;

CREATE TABLE `academic_information_board` (
  `academic_information_board_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `passing_year` varchar(255) DEFAULT NULL,
  `board` varchar(255) DEFAULT NULL,
  `marks` double(16,4) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `academic_type` enum('matriculation','intermediate') DEFAULT NULL,
  `marksheet_image` varchar(255) DEFAULT NULL,
  `certificate_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`academic_information_board_id`),
  KEY `personal_information_id` (`student_id`),
  CONSTRAINT `academic_information_board_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `academic_information_board` */

insert  into `academic_information_board`(`academic_information_board_id`,`student_id`,`passing_year`,`board`,`marks`,`subject`,`academic_type`,`marksheet_image`,`certificate_image`) values (14,1,'123','123',90.0000,'123','intermediate','uploads\\academicDocuments\\intermediate\\F16SW49_marksheetImage.png','uploads\\academicDocuments\\intermediate\\F16SW49_certificateImage.png'),(15,1,'123','123',90.0000,'123','matriculation','uploads\\academicDocuments\\matriculation\\F16SW49_marksheetImage.jpg','uploads\\academicDocuments\\matriculation\\F16SW49_certificateImage.jpg');

/*Table structure for table `academic_information_graduate` */

DROP TABLE IF EXISTS `academic_information_graduate`;

CREATE TABLE `academic_information_graduate` (
  `academic_information_graduate_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `passing_year` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  `cgpa` double(16,4) DEFAULT NULL,
  `degree_program` varchar(255) DEFAULT NULL,
  `graduate_type` enum('bachelors','masters') DEFAULT NULL,
  `marksheet_image` varchar(255) DEFAULT NULL,
  `certificate_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`academic_information_graduate_id`),
  KEY `personal_information_id` (`student_id`),
  CONSTRAINT `academic_information_graduate_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `academic_information_graduate` */

insert  into `academic_information_graduate`(`academic_information_graduate_id`,`student_id`,`passing_year`,`university`,`cgpa`,`degree_program`,`graduate_type`,`marksheet_image`,`certificate_image`) values (8,1,'2020','123',90.0000,'123','masters','uploads\\academicDocuments\\graduate\\F16SW49_marksheetImage.jpg','uploads\\academicDocuments\\graduate\\F16SW49_certificateImage.jpg');

/*Table structure for table `batch` */

DROP TABLE IF EXISTS `batch`;

CREATE TABLE `batch` (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `batch` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `faculty_id` (`faculty_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `department` */

insert  into `department`(`department_id`,`department_name`,`faculty_id`) values (29,'Software Engineering',7);

/*Table structure for table `department_faculty` */

DROP TABLE IF EXISTS `department_faculty`;

CREATE TABLE `department_faculty` (
  `department_faculty_id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`department_faculty_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `department_faculty_ibfk_1` FOREIGN KEY (`department_faculty_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `department_faculty` */

/*Table structure for table `documents` */

DROP TABLE IF EXISTS `documents`;

CREATE TABLE `documents` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `matriculation_marksheet` blob DEFAULT NULL,
  `matriculation_certificate` blob DEFAULT NULL,
  `intermediate_marksheet` blob DEFAULT NULL,
  `intermediate_certificate` blob DEFAULT NULL,
  `graduate_transcript` blob DEFAULT NULL,
  `graduate_pass_certificate` blob DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  KEY `personal_information_id` (`student_id`),
  CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `documents` */

/*Table structure for table `experience` */

DROP TABLE IF EXISTS `experience`;

CREATE TABLE `experience` (
  `experience_id` int(11) NOT NULL AUTO_INCREMENT,
  `personal_information_id` int(11) NOT NULL,
  `company` varchar(255) NOT NULL,
  `position_held` varchar(255) NOT NULL,
  `from` date NOT NULL,
  `to` date NOT NULL,
  `nature_of_work` varchar(255) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  PRIMARY KEY (`experience_id`),
  KEY `personal_information_id` (`personal_information_id`),
  CONSTRAINT `experience_ibfk_1` FOREIGN KEY (`personal_information_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `experience` */

/*Table structure for table `faculty` */

DROP TABLE IF EXISTS `faculty`;

CREATE TABLE `faculty` (
  `faculty_id` int(11) NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `faculty` */

insert  into `faculty`(`faculty_id`,`faculty_name`) values (1,'Faculty Of Science'),(7,'Faculty of Computer Engineerings'),(8,'Faculty of Computer Engineerings'),(9,'Faculty of Computer Engineerings');

/*Table structure for table `fees` */

DROP TABLE IF EXISTS `fees`;

CREATE TABLE `fees` (
  `fees_id` int(11) NOT NULL AUTO_INCREMENT,
  `fees_title` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `announcement_date` varchar(255) DEFAULT NULL,
  `due_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fees_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `fees` */

insert  into `fees`(`fees_id`,`fees_title`,`amount`,`announcement_date`,`due_date`) values (3,'Test',10,'2020-08-07T17:36','2020-08-07T17:36'),(4,'TestUpdate',10,'2020-08-07T18:44','2020-08-07T18:44'),(5,'Test',10,'2020-08-08T13:36','2020-08-08T13:36');

/*Table structure for table `seminar` */

DROP TABLE IF EXISTS `seminar`;

CREATE TABLE `seminar` (
  `seminar_id` int(11) NOT NULL AUTO_INCREMENT,
  `seminar_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seminar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `seminar` */

insert  into `seminar`(`seminar_id`,`seminar_title`) values (1,'SW Lectures');

/*Table structure for table `seminar_thesis` */

DROP TABLE IF EXISTS `seminar_thesis`;

CREATE TABLE `seminar_thesis` (
  `seminar_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `seminar_id` int(11) DEFAULT NULL,
  `thesis_id` int(11) DEFAULT NULL,
  `date_applied` date DEFAULT NULL,
  `date_conducted` date DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `comments` varchar(1000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seminar_group_id`),
  KEY `seminar_id` (`seminar_id`),
  KEY `thesis_id` (`thesis_id`),
  CONSTRAINT `seminar_thesis_ibfk_2` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`thesis_id`),
  CONSTRAINT `seminar_thesis_ibfk_3` FOREIGN KEY (`seminar_id`) REFERENCES `seminar` (`seminar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `seminar_thesis` */

insert  into `seminar_thesis`(`seminar_group_id`,`seminar_id`,`thesis_id`,`date_applied`,`date_conducted`,`remarks`,`comments`,`status`) values (1,1,1,'2020-08-19','2020-08-27','Amazing ','Very Good','Approved');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `roll_number` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `fathers_name` varchar(255) DEFAULT NULL,
  `legal_id` varchar(255) DEFAULT NULL,
  `legal_id_no` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `place_of_issue_of_legal_id` varchar(255) DEFAULT NULL,
  `date_of_issue_of_legal_id` date DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `domicile` varchar(255) DEFAULT NULL,
  `country_of_birth` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `current_address` varchar(255) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `batch` varchar(255) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `supervisor_id` int(11) DEFAULT NULL,
  `program` varchar(255) DEFAULT NULL,
  `field_program` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `timing` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `nationality` (`nationality`),
  KEY `domicile_id` (`domicile`),
  KEY `country_of_birth` (`country_of_birth`),
  KEY `blood_group_id` (`blood_group`),
  KEY `religion_id` (`religion`),
  KEY `supervisor_id` (`supervisor_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `student_ibfk_6` FOREIGN KEY (`supervisor_id`) REFERENCES `department_faculty` (`department_faculty_id`),
  CONSTRAINT `student_ibfk_7` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`student_id`,`roll_number`,`full_name`,`fathers_name`,`legal_id`,`legal_id_no`,`nationality`,`place_of_issue_of_legal_id`,`date_of_issue_of_legal_id`,`mobile`,`dob`,`email`,`domicile`,`country_of_birth`,`blood_group`,`religion`,`address`,`current_address`,`semester`,`department_id`,`batch`,`admission_date`,`supervisor_id`,`program`,`field_program`,`shift`,`timing`,`profile_image`) values (1,'F16SW49','Nand Lal Khatri','Lal Chand','123','123','123','123','2020-08-27','123123123','2020-08-27','nand@gmail.com','123','Pakistan','123','123','Sonara Mohalla','123',1,29,'19','2020-08-27',NULL,'aasdf','asdf','asdf','123','uploads\\profileImages\\F16SW49_profileImage.jpg'),(4,'F16SW491`','Jawad Nabi','Ghulam Nabi','CNIC','123123123123','Pakistan','Sujawal','1990-01-01','0123123123','1990-01-01','jawadzour@gmail.com','Sujawal','Pakistan','O+','Islam','asdf adf','asdfa asdf',2,29,'19','2020-01-01',NULL,'Masters','Software Engineering','asdf','asdf',NULL);

/*Table structure for table `student_fees` */

DROP TABLE IF EXISTS `student_fees`;

CREATE TABLE `student_fees` (
  `student_fees_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `fees_id` int(11) DEFAULT NULL,
  `date_paid` varchar(255) DEFAULT NULL,
  `amount_paid` int(11) DEFAULT NULL,
  `late_fees` int(11) DEFAULT NULL,
  `challan_no` int(11) DEFAULT NULL,
  `challan_image` blob DEFAULT NULL,
  PRIMARY KEY (`student_fees_id`),
  KEY `student_id` (`student_id`),
  KEY `fees_id` (`fees_id`),
  CONSTRAINT `student_fees_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `student_fees_ibfk_2` FOREIGN KEY (`fees_id`) REFERENCES `fees` (`fees_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `student_fees` */

insert  into `student_fees`(`student_fees_id`,`student_id`,`fees_id`,`date_paid`,`amount_paid`,`late_fees`,`challan_no`,`challan_image`) values (2,1,3,'2020-08-07',12,12,12,NULL);

/*Table structure for table `thesis` */

DROP TABLE IF EXISTS `thesis`;

CREATE TABLE `thesis` (
  `thesis_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `thesis_title` varchar(255) DEFAULT NULL,
  `thesis_exam_date` varchar(255) DEFAULT NULL,
  `final_results` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`thesis_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `thesis_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `thesis` */

insert  into `thesis`(`thesis_id`,`student_id`,`thesis_title`,`thesis_exam_date`,`final_results`,`remarks`) values (1,1,'MIS',NULL,NULL,NULL);

/*Table structure for table `thesis_documents` */

DROP TABLE IF EXISTS `thesis_documents`;

CREATE TABLE `thesis_documents` (
  `thesis_document_id` int(11) NOT NULL AUTO_INCREMENT,
  `thesis_document_title` varchar(255) DEFAULT NULL,
  `thesis_id` int(11) DEFAULT NULL,
  `thesis_document` blob DEFAULT NULL,
  PRIMARY KEY (`thesis_document_id`),
  KEY `thesis_id` (`thesis_id`),
  CONSTRAINT `thesis_documents_ibfk_1` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`thesis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `thesis_documents` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`full_name`,`email`,`password`,`role`,`student_id`) values (2,'Nand Lal','nkhatri558@gmail.com','123','admin',NULL),(3,'Jawad Nabi','jawadzour786@gmail.com','2233','user',NULL),(4,'Raheem','raheem@gmail.com','123','student',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
