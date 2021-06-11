SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `CMND` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
                           `fullname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
                           `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                           `gender` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

BEGIN ;
INSERT INTO `teacher`(`ID`, `CMND`, `password`, `fullname`, `email`, `gender`) VALUES (1,'341990000','341990000','Nguyễn Văn Tài','a@gmail.com','Nam');
INSERT INTO `teacher`(`ID`, `CMND`, `password`, `fullname`, `email`, `gender`) VALUES (2,'341990001','123','Trần Thị Mai','b@gmail.com','Nữ');
INSERT INTO `teacher`(`ID`, `CMND`, `password`, `fullname`, `email`, `gender`) VALUES (3,'341990002','341990002','Phan Văn Duy','c@gmail.com','Nam');
COMMIT;
-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `className` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           `MSSV` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
                           `fullname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
                           `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                           `gender` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `student`
    ADD FOREIGN KEY (`className`) REFERENCES `class`(`className`);

BEGIN ;
INSERT INTO `student` VALUES (1,'18CTT1','20120001','123','Nguyễn Văn Thanh','a@gmail.com','Nam');
INSERT INTO `student` VALUES (2,'18CTT2','20120002','123','Trần Thị Lan','b@gmail.com','Nữ');
INSERT INTO `student` VALUES (3,'18CTT3','20120003','123','Phan Văn Hưng','c@gmail.com','Nam');
INSERT INTO `student` VALUES (4,'19CTT1','20120004','123','LeLush','le@gmail.com','Nam');
INSERT INTO `student` VALUES (5,'19CTT2','20120005','20120005','Trần Thị Ngọc','1@gmail.com','Nữ');
INSERT INTO `student` VALUES (6,'19CTT3','20120006','20120006','Vũ Văn Minh','2@gmail.com','Nam');
INSERT INTO `student` VALUES (7,'20CTT1','20120007','20120007','Nguyễn Văn Thành','3@gmail.com','Nam');
INSERT INTO `student` VALUES (8,'20CTT2','20120008','20120008','Nguyễn Ánh Ngọc','4@gmail.com','Nữ');
INSERT INTO `student` VALUES (9,'18CTT3','20120009','20120009','Phan Văn Hùng','5@gmail.com','Nam');
INSERT INTO `student` VALUES (10,'18CTT1','20120010','20120010','Nguyễn Văn Tuấn','6@gmail.com','Nam');
INSERT INTO `student` VALUES (11,'19CTT2','20120011','20120011','Trần Thị Thu','7@gmail.com','Nữ');
INSERT INTO `student` VALUES (12,'20CTT3','20120012','20120012','Phan Văn Vũ','8@gmail.com','Nam');
COMMIT;
-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `subjectID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           `subjectName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                           `credits` int NOT NULL,
                           PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

BEGIN;
INSERT INTO `subject` VALUES (1,'CSC00001','Nhập môn Công nghệ thông tin 1',4);
INSERT INTO `subject` VALUES (2,'CSC10002','Kỹ thuật phần mềm',4);
INSERT INTO `subject` VALUES (3,'CSC10003','Phương pháp lập trình hướng đối tượng',4);
INSERT INTO `subject` VALUES (4,'CSC10104','Quy hoạch tuyến tính',4);
INSERT INTO `subject` VALUES (5,'CSC13005','Phân tích và quản lý yêu cầu phần mềm',4);
INSERT INTO `subject` VALUES (6,'CSC13010','Thiết kế phần mềm',4);
INSERT INTO `subject` VALUES (7,'CSC13102','Lập trình ứng dụng Java',4);
INSERT INTO `subject` VALUES (8,'MTH00081','Vi tích phân 1B',3);
INSERT INTO `subject` VALUES (9,'MTH00004','Vi tích phân 2B',3);
INSERT INTO `subject` VALUES (10,'CSC10004','Cấu trúc dữ liệu và giải thuật',4);
COMMIT;

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester` (
                            `ID` int(11) NOT NULL AUTO_INCREMENT,
                            `semesterName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                            `year` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                            `dateBegin` date,
                            `dateEnd` date,
                            `type` int ,
                            PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

BEGIN;
INSERT INTO `semester` VALUES (1,'HK1','2019-2020','2019-1-1','2019-5-4',0);
INSERT INTO `semester` VALUES (2,'HK2','2019-2020','2019-5-5','2019-12-30',0);
INSERT INTO `semester` VALUES (3,'HK2','2020-2021','2021-1-1','2021-12-30',1);
COMMIT;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
                         `ID` int(11) NOT NULL AUTO_INCREMENT,
                         `className` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                         `countStudent` int ,
                         `countMen` int ,
                         `countWomen` int ,
                         PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

BEGIN;
INSERT INTO `class` VALUES (1,'18CTT1',0,0,0);
INSERT INTO `class` VALUES (2,'18CTT2',0,0,0);
INSERT INTO `class` VALUES (3,'18CTT3',0,0,0);
INSERT INTO `class` VALUES (4,'19CTT1',0,0,0);
INSERT INTO `class` VALUES (5,'19CTT2',0,0,0);
INSERT INTO `class` VALUES (6,'19CTT3',0,0,0);
INSERT INTO `class` VALUES (7,'20CTT1',0,0,0);
INSERT INTO `class` VALUES (8,'20CTT2',0,0,0);
INSERT INTO `class` VALUES (9,'20CTT3',0,0,0);
COMMIT;

-- ----------------------------
-- Table structure for courseRegistration session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `IdSemester` int(11) NOT NULL,
                           `dateBegin` date,
                           `dateEnd` date,
                           PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

BEGIN;
INSERT INTO `session` VALUES (1,3,'2020-1-1','2020-1-14');
INSERT INTO `session` VALUES (2,3,'2020-2-1','2020-2-14');
INSERT INTO `session` VALUES (3,3,'2021-3-1','2021-12-30');
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
                          `ID` int(11) NOT NULL AUTO_INCREMENT,
                          `IdSemester` int(11) NOT NULL,
                          `IdSubject` int(11) NOT NULL,
                          `teacherName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                          `roomName` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
                          `dayOfWeek` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                          `timeOfDay` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                          `slotMax` int NOT NULL,
                          PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `course`
ADD FOREIGN KEY (`IdSemester`) REFERENCES `semester`(`ID`);
ALTER TABLE `course`
ADD FOREIGN KEY (`IdSubject`) REFERENCES `subject`(`ID`);

BEGIN;
INSERT INTO `course` VALUES (1,3,1,'Thái Hùng Văn','A101','Thứ 2','7:30 – 9:30',50);
INSERT INTO `course` VALUES (2,3,2,'Nguyễn Phạm Phương Nam','A102','Thứ 3','9:30 – 11:30',50);
INSERT INTO `course` VALUES (3,3,3,'Lê Viết Long','A103','Thứ 4','11:30 - 15:30',50);
INSERT INTO `course` VALUES (4,3,4,'Trần Quang Hiển','B101','Thứ 5','7:30 – 9:30',50);
INSERT INTO `course` VALUES (5,3,5,'Trần Phước Quốc Hiền','B102','Thứ 6','9:30 – 11:30',50);
INSERT INTO `course` VALUES (6,3,6,'Lê Viết Long','B103','Thứ 7','15:30 – 17:30',100);
INSERT INTO `course` VALUES (7,3,7,'Trần Thái Sơn','C101','Thứ 2','7:30 – 9:30',100);
INSERT INTO `course` VALUES (8,3,8,'Trần Minh Triết','C102','Thứ 3','11:30 - 15:30',100);
INSERT INTO `course` VALUES (9,3,9,'Thái Lê Vinh','C103','Thứ 4','15:30 – 17:30',100);
INSERT INTO `course` VALUES (10,3,7,'Nguyễn Vinh Tiệp','D101','Thứ 5','7:30 – 9:30',100);
INSERT INTO `course` VALUES (11,3,6,'Nguyễn Việt Thành','D102','Thứ 6','9:30 – 11:30',100);
INSERT INTO `course` VALUES (12,3,5,'Thái Hùng Văn','D103','Thứ 7','15:30 – 17:30',100);
COMMIT;

-- ----------------------------
-- Table structure for joincourse
-- ----------------------------
DROP TABLE IF EXISTS `joincourse`;
CREATE TABLE `joincourse` (
                          `ID` int(11) NOT NULL AUTO_INCREMENT,
                          `ID_student` int(11) not null ,
                          `ID_course` int(11) not null ,
                          `dayRegistration` DATETIME,
                          PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
ALTER TABLE `joincourse`
ADD FOREIGN KEY (`ID_student`) REFERENCES `student`(`ID`);
ALTER TABLE `joincourse`
ADD FOREIGN KEY (`ID_course`) REFERENCES `course`(`ID`);

BEGIN;
INSERT INTO `joincourse` VALUES (1,1,1,'2021-6-10');
INSERT INTO `joincourse` VALUES (2,1,2,'2021-6-10');
INSERT INTO `joincourse` VALUES (3,1,3,'2021-6-10');
INSERT INTO `joincourse` VALUES (4,2,3,'2021-6-10');
INSERT INTO `joincourse` VALUES (5,3,3,'2021-6-10');
INSERT INTO `joincourse` VALUES (6,3,2,'2021-6-10');
INSERT INTO `joincourse` VALUES (7,4,1,'2021-6-10');
INSERT INTO `joincourse` VALUES (8,4,2,'2021-6-10');
INSERT INTO `joincourse` VALUES (9,4,3,'2021-6-10');
INSERT INTO `joincourse` VALUES (10,5,4,'2021-6-10');
INSERT INTO `joincourse` VALUES (11,5,5,'2021-6-10');
INSERT INTO `joincourse` VALUES (12,5,6,'2021-6-10');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
