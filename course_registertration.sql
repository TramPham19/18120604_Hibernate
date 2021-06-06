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

# BEGIN ;
# INSERT INTO `teacher`(`ID`, `CMND`, `password`, `fullname`, `email`, `gender`) VALUES (1,'341990000','341990000','Nguyễn Văn Tài','a@gmail.com','Nam');
# INSERT INTO `teacher`(`ID`, `CMND`, `password`, `fullname`, `email`, `gender`) VALUES (2,'341990001','341990001','Trần Thị Mai','b@gmail.com','Nữ');
# INSERT INTO `teacher`(`ID`, `CMND`, `password`, `fullname`, `email`, `gender`) VALUES (3,'341990003','341990002','Phan Văn Duy','c@gmail.com','Nam');
# COMMIT;
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


-- ----------------------------
-- Table structure for courseRegistration session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `semesterName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           `year` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                           `dateBegin` date,
                           `dateEnd` date,
                           PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
                          `ID` int(11) NOT NULL AUTO_INCREMENT,
                          `semesterName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                          `year` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                          `courseID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                          `coursetName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                          `credits` int NOT NULL,
                          `teacherName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                          `roomName` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
                          `dayOfWeek` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                          `timeOfDay` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                          `slotMax` int NOT NULL,
                          PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for joincourse
-- ----------------------------
DROP TABLE IF EXISTS `joincourse`;
CREATE TABLE `joincourse` (
                          `ID` int(11) NOT NULL AUTO_INCREMENT,
                          `MSSV` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
                          `ID_course` int(11) not null ,
                          PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
