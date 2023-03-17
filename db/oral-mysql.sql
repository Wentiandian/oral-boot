/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : oral

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 17/03/2023 16:42:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doc_dept
-- ----------------------------
DROP TABLE IF EXISTS `doc_dept`;
CREATE TABLE `doc_dept`  (
  `doc_dept_id` int(0) NOT NULL AUTO_INCREMENT,
  `doc_id` int(0) NOT NULL,
  `dept_id` int(0) NOT NULL,
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`doc_dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医生与科室与i应关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doc_dept
-- ----------------------------
INSERT INTO `doc_dept` VALUES (1, 2, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for drug_pre
-- ----------------------------
DROP TABLE IF EXISTS `drug_pre`;
CREATE TABLE `drug_pre`  (
  `drug_pre_id` int(0) NOT NULL AUTO_INCREMENT,
  `prescription_id` int(0) NULL DEFAULT NULL,
  `drug_id` int(0) NULL DEFAULT NULL,
  `drug_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`drug_pre_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '药号单对应的药品关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_pre
-- ----------------------------
INSERT INTO `drug_pre` VALUES (7, 7, 3, 1);
INSERT INTO `drug_pre` VALUES (8, 7, 1, 2);

-- ----------------------------
-- Table structure for ele_records
-- ----------------------------
DROP TABLE IF EXISTS `ele_records`;
CREATE TABLE `ele_records`  (
  `ele_records_id` int(0) NOT NULL AUTO_INCREMENT,
  `patient_id` int(0) NOT NULL COMMENT '病人ID',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生',
  `nurse_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '护士',
  `is_referral` int(0) NULL DEFAULT NULL COMMENT '是否复诊',
  `treatment_num` int(0) NULL DEFAULT NULL COMMENT '第几次就诊',
  `treatment_time` datetime(0) NULL DEFAULT NULL COMMENT '就诊时间',
  `treatment_description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊断描述',
  `treatment_method` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '治疗方案',
  `treatment_during` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '治疗过程',
  `doc_orders` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医嘱',
  `prescription_id` int(0) NULL DEFAULT NULL COMMENT '药物单id',
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者名称',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '治疗科室ID',
  `is_gh_booking` int(0) NULL DEFAULT NULL COMMENT '0:挂号  1：预约',
  PRIMARY KEY (`ele_records_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电子病历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ele_records
-- ----------------------------
INSERT INTO `ele_records` VALUES (7, 3, 'ys', 'ys', 0, 1, '2023-03-29 00:00:00', 'nejrkirsughrsghsh', 'jrskgnrgnruighrghrigh', 'nfsjfjfisfjjeifj', 'sefbhfrhjbbgsghsr', 7, 1, '2023-03-12 00:02:52', NULL, NULL, 'ccc', 1, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT,
  `patient_id` int(0) NULL DEFAULT NULL COMMENT '患者ID',
  `prescription_id` int(0) NULL DEFAULT NULL COMMENT '药物单ID',
  `sum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总价',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '生成订单时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '0：未付款  1：已付款',
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '缴费订单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 1, '155.00', '2023-03-11 16:39:59', 0, 1, '2023-03-11 16:39:59', NULL, NULL);
INSERT INTO `orders` VALUES (2, 3, 7, '81.00', '2023-03-12 10:32:51', 0, 1, '2023-03-12 10:32:51', NULL, NULL);

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `patient_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL COMMENT '1:男  0: 女',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL COMMENT '1:正常  0：禁用',
  PRIMARY KEY (`patient_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '患者信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('1', 'aaa', 1, '12', '156489648', NULL, '1', '2023-02-27 16:05:25', NULL, NULL, 1);
INSERT INTO `patient` VALUES ('2', 'bbb', 0, '13', '1954652865', NULL, '2', '2023-02-28 10:01:54', NULL, NULL, 1);
INSERT INTO `patient` VALUES ('202303160025320561', 'www', 1, '23', '18219135363', 'asffggggg', '202303160025320561', '2023-03-16 00:25:32', NULL, NULL, 1);
INSERT INTO `patient` VALUES ('202303160041530746', 'kkk', 0, '23', '19254802938', '12326@063.com', '202303160041530746', '2023-03-16 00:41:53', NULL, NULL, 1);
INSERT INTO `patient` VALUES ('3', 'ccc', 1, '15', '44558885674', NULL, '3', '2023-02-28 15:11:19', NULL, NULL, 1);
INSERT INTO `patient` VALUES ('7', 'www', 1, '23', '2144233223', 'fefsgrgdgtd', NULL, '2023-03-14 21:47:13', NULL, NULL, 1);
INSERT INTO `patient` VALUES ('8', 'ttt', 0, '45', '1564984968', 'awfsgrdgdg', NULL, '2023-03-14 21:48:18', NULL, NULL, 1);

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `prescription_id` int(0) NOT NULL AUTO_INCREMENT,
  `hospital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `patient_id` int(0) NULL DEFAULT NULL COMMENT '病人ID',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '科室ID',
  `prescription_time` datetime(0) NULL DEFAULT NULL COMMENT '开处方时间',
  `use_method` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用方法',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生',
  `pharmacist` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药剂师',
  `estimator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计价员',
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '0：未计价  1：已计价',
  `sum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总价',
  `flag` int(0) NULL DEFAULT NULL COMMENT '用来标记获取药物单ID   0：未获取到   1：已获取到',
  PRIMARY KEY (`prescription_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '药号单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES (7, '人民医院', 3, 1, '2023-03-12 00:02:52', 'grsgbnrsgnnknklrgnk', 'ys', 'ys', 'ys', 1, '2023-03-12 00:02:52', 1, '2023-03-12 10:32:51', 1, '81.00', 1);

-- ----------------------------
-- Table structure for sys_booking
-- ----------------------------
DROP TABLE IF EXISTS `sys_booking`;
CREATE TABLE `sys_booking`  (
  `booking_id` int(0) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept_id` int(0) NOT NULL,
  `doc_id` int(0) NOT NULL,
  `booking_time` datetime(0) NOT NULL,
  `booking_num` int(0) NULL DEFAULT NULL,
  `create_user_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '0:已就诊 1：未就诊 2：已过期',
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`booking_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '患者预约信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_booking
-- ----------------------------
INSERT INTO `sys_booking` VALUES (2, '3', 1, 2, '2023-03-01 17:09:07', 1, NULL, NULL, 1, '2023-03-01 17:09:13', 2, 'ccc');
INSERT INTO `sys_booking` VALUES (10, '202303160025320561', 1, 2, '2023-03-16 00:26:26', NULL, '202303160025320561', '2023-03-16 00:26:30', NULL, NULL, 2, 'www');
INSERT INTO `sys_booking` VALUES (11, '202303160041530746', 6, 11, '2023-03-16 00:41:04', NULL, '202303160041530746', '2023-03-16 00:41:07', NULL, NULL, 2, 'kkk');
INSERT INTO `sys_booking` VALUES (12, '202303160025320561', 6, 2, '2023-03-17 16:25:32', NULL, '202303160025320561', '2023-03-17 16:25:42', NULL, NULL, 2, 'www');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('015a7dcf-e6d4-410c-88de-027ad4fdac0e', 'wdenm', '2023-01-12 14:15:21');
INSERT INTO `sys_captcha` VALUES ('04c9839f-4a04-485c-879d-bd769ff41264', '4f757', '2023-02-26 21:51:24');
INSERT INTO `sys_captcha` VALUES ('088afa50-a767-425d-847c-f3fce0e10055', 'd4x26', '2023-01-09 13:27:31');
INSERT INTO `sys_captcha` VALUES ('08d2648a-5900-45f5-8a14-57ebdbaeb4d3', '2wwa5', '2023-01-12 14:34:35');
INSERT INTO `sys_captcha` VALUES ('0918a29d-fd2b-4a21-886b-9b729a728bb0', '2pyma', '2023-02-26 23:06:58');
INSERT INTO `sys_captcha` VALUES ('0b655d69-5da9-464f-8529-b1e53db3775c', 'g3xy3', '2023-01-06 16:42:10');
INSERT INTO `sys_captcha` VALUES ('0d4f226f-b036-4a88-8892-381434873c3f', 'ea235', '2023-02-27 13:12:54');
INSERT INTO `sys_captcha` VALUES ('0e572992-b80d-4de8-831b-5b2f4db3bb2e', 'pmeg3', '2023-01-06 17:14:25');
INSERT INTO `sys_captcha` VALUES ('13fed396-9fd4-4ab4-814e-0ce13c521240', '6wm47', '2023-02-27 12:30:02');
INSERT INTO `sys_captcha` VALUES ('14b80fa5-de00-4204-83b5-ba122cb45ea9', 'g367a', '2023-01-12 14:58:30');
INSERT INTO `sys_captcha` VALUES ('179f8fad-1a9c-430d-8c5a-f079a886a579', '3746y', '2023-02-26 19:17:13');
INSERT INTO `sys_captcha` VALUES ('191314f2-6cb1-4d68-8678-e1d1eaf8d443', '5emfg', '2023-03-02 22:51:36');
INSERT INTO `sys_captcha` VALUES ('1ae021a3-10e5-4ecf-85a8-e9a50cd31b16', '27aww', '2023-01-06 17:17:59');
INSERT INTO `sys_captcha` VALUES ('1b6a4e44-bdd5-4514-8549-78fee1b6b533', '7bbnf', '2023-01-06 17:12:55');
INSERT INTO `sys_captcha` VALUES ('1c276e2e-a4fe-48a0-8b94-91c4e4a3c2e0', 'neen4', '2023-01-12 14:39:36');
INSERT INTO `sys_captcha` VALUES ('20b071d0-42d2-4a5b-8ff0-83fadbf06dc5', 'mwamd', '2023-02-27 13:21:32');
INSERT INTO `sys_captcha` VALUES ('2284c596-e20a-4655-8640-ad8222a37725', 'cmpbf', '2023-01-06 17:11:35');
INSERT INTO `sys_captcha` VALUES ('232fd295-0dfb-4e21-8b4a-6fc982f31df2', 'na42f', '2023-02-27 13:57:57');
INSERT INTO `sys_captcha` VALUES ('245c7f3b-6f9f-4757-84b1-28fdf79779ce', '7xby4', '2023-01-06 17:14:26');
INSERT INTO `sys_captcha` VALUES ('2617dc60-d214-462a-883f-a297453ba4aa', '2fn77', '2023-02-26 21:58:46');
INSERT INTO `sys_captcha` VALUES ('26cd451e-a574-41d1-8fe9-b1dbb2b1f8ba', 'm256c', '2023-01-09 17:02:30');
INSERT INTO `sys_captcha` VALUES ('2a0436ca-5058-4b85-8eae-877262b6f863', 'pyem6', '2023-01-06 17:12:51');
INSERT INTO `sys_captcha` VALUES ('2a75f0b9-6e47-4f59-82e9-89f8db7ae5b5', 'ac6cn', '2023-01-06 17:06:31');
INSERT INTO `sys_captcha` VALUES ('2f130b02-bfb2-4cd3-8bcb-8adee9cce32e', '2npax', '2023-01-06 17:10:18');
INSERT INTO `sys_captcha` VALUES ('316292c2-161e-425b-8477-ec937d2f2288', 'm8c62', '2023-02-26 19:24:33');
INSERT INTO `sys_captcha` VALUES ('3206cec2-108c-4e26-8f61-7a2c72841ffb', 'mb3pn', '2023-02-26 19:17:19');
INSERT INTO `sys_captcha` VALUES ('328e60c2-54df-487b-8ea6-ddf103df73a0', 'aaag3', '2023-01-06 17:21:16');
INSERT INTO `sys_captcha` VALUES ('3c775eff-26cf-41d9-8551-8ca7c06b1822', 'wn676', '2023-02-26 19:35:02');
INSERT INTO `sys_captcha` VALUES ('3f13c451-a06d-44a9-85bc-274361ef77c1', 'gx3cf', '2023-02-27 13:07:32');
INSERT INTO `sys_captcha` VALUES ('40c7b43e-e269-4e0a-8e21-e71160e3c874', 'bbn22', '2023-01-12 14:59:33');
INSERT INTO `sys_captcha` VALUES ('42e4495f-65fd-4d1c-88be-125b10c13957', '385dp', '2023-01-12 15:00:16');
INSERT INTO `sys_captcha` VALUES ('4453e86a-5c68-430c-805e-07132c683ecc', '4332x', '2023-01-06 17:14:23');
INSERT INTO `sys_captcha` VALUES ('456b75f8-ce61-4692-8a7e-69aeeeb9fb98', 'a5awp', '2023-01-11 23:00:30');
INSERT INTO `sys_captcha` VALUES ('4c16fb57-feb5-4797-8539-f38986ec2e6a', 'x7gef', '2023-01-06 17:21:07');
INSERT INTO `sys_captcha` VALUES ('51734712-5280-4df6-8762-851dd0051958', '8x8de', '2023-02-26 21:55:59');
INSERT INTO `sys_captcha` VALUES ('518e6204-0f7e-41af-83c1-ffa07903e8b0', 'c6m5p', '2023-01-12 13:40:30');
INSERT INTO `sys_captcha` VALUES ('52107198-af97-43dd-810e-2a9169ebaf64', '2bnn3', '2023-02-26 22:01:17');
INSERT INTO `sys_captcha` VALUES ('52d4a31b-9cad-4b06-8de3-8cbcf26902a7', 'pe4dp', '2023-02-27 13:00:18');
INSERT INTO `sys_captcha` VALUES ('52ea90b7-a052-4a8c-8ee5-f9844b8e4ad6', 'm637e', '2023-01-06 17:21:11');
INSERT INTO `sys_captcha` VALUES ('543f2a63-6c33-45f0-806f-b0085bd45040', 'e7y7p', '2023-01-06 17:21:16');
INSERT INTO `sys_captcha` VALUES ('54cb0954-314e-44ae-8494-3ad2e6051681', 'w52mg', '2023-01-12 14:47:42');
INSERT INTO `sys_captcha` VALUES ('5e309448-ea56-44aa-8ca9-358a302a6263', '2afwy', '2023-02-27 13:21:11');
INSERT INTO `sys_captcha` VALUES ('614a37b0-c52a-4199-8bc2-57504ed2322a', 'y4cn4', '2023-01-06 17:18:04');
INSERT INTO `sys_captcha` VALUES ('6b402ec5-4144-465d-824c-155df1cf48cf', '82ed3', '2023-02-26 22:57:51');
INSERT INTO `sys_captcha` VALUES ('6d29575a-4e5c-4989-818f-a0ebefbe6c6d', '6n7e5', '2023-01-06 17:21:30');
INSERT INTO `sys_captcha` VALUES ('6ed64ae4-6776-40a7-8148-0bdaae6304f6', 'nn788', '2023-02-27 13:21:20');
INSERT INTO `sys_captcha` VALUES ('6f06b921-0081-46aa-88be-e8613ecb3a6d', 'e7ba4', '2023-01-06 17:21:19');
INSERT INTO `sys_captcha` VALUES ('6f7d9036-33d5-4e4f-8c21-814443f2525e', '24825', '2023-01-12 15:00:14');
INSERT INTO `sys_captcha` VALUES ('70a33dc5-b84e-4474-83c3-c1ab787b5f91', '33x46', '2023-02-27 14:10:03');
INSERT INTO `sys_captcha` VALUES ('73dc48b7-a6c5-46bf-8cca-3f9567e12d81', 'ngbn5', '2023-02-26 19:24:39');
INSERT INTO `sys_captcha` VALUES ('776db702-33ff-445f-832f-32d39d5abfa5', 'fwef5', '2023-01-06 17:20:43');
INSERT INTO `sys_captcha` VALUES ('7cf8a196-e713-4d01-846d-01a393af7948', 'w3f32', '2023-01-12 14:24:04');
INSERT INTO `sys_captcha` VALUES ('85ee20de-f861-46ad-88ab-fb31af345b5e', '3cwg4', '2023-01-06 17:21:17');
INSERT INTO `sys_captcha` VALUES ('8a0159d2-de50-4f80-8e89-d6c81f6b8ceb', 'gppw2', '2023-02-27 13:57:04');
INSERT INTO `sys_captcha` VALUES ('8ff9827c-560f-4d7b-8ef1-2cebdc0bb82d', '75e37', '2023-01-06 17:21:20');
INSERT INTO `sys_captcha` VALUES ('90fe5c8d-6785-444f-8e5b-f82cf69d18b0', '3nb4w', '2023-01-09 13:27:41');
INSERT INTO `sys_captcha` VALUES ('92996169-aabd-4adc-8169-d9ef5e355bda', '5dcf5', '2023-01-12 14:53:52');
INSERT INTO `sys_captcha` VALUES ('962d49b7-ae4a-4462-886d-575900179792', 'geygx', '2023-03-01 19:55:15');
INSERT INTO `sys_captcha` VALUES ('97a9c716-c3ba-43de-85cc-4f9ba8c4143f', 'pgnfw', '2023-02-26 23:15:49');
INSERT INTO `sys_captcha` VALUES ('995723a3-012a-4787-8a27-7f026344a1e7', '3w8xg', '2023-02-26 22:00:43');
INSERT INTO `sys_captcha` VALUES ('9cc001fe-791d-4d63-80f4-cfe8702273ea', 'y548a', '2023-01-06 17:21:21');
INSERT INTO `sys_captcha` VALUES ('a0a20564-e644-4d81-8d76-72c08283d7b1', 'xn22c', '2023-02-27 12:56:35');
INSERT INTO `sys_captcha` VALUES ('a0c47d74-6de4-441b-82db-010115576cb6', '4c2y2', '2023-01-12 14:51:43');
INSERT INTO `sys_captcha` VALUES ('a1c2db09-9a49-45b2-8670-73c9674684f0', 'mn3g5', '2023-01-11 14:58:42');
INSERT INTO `sys_captcha` VALUES ('a8753e26-f142-470b-81ba-18022c491032', '3mxfc', '2023-01-06 17:06:36');
INSERT INTO `sys_captcha` VALUES ('ab89937d-6ab5-4335-8c49-cfc348547da7', 'dpb2d', '2023-01-06 17:06:43');
INSERT INTO `sys_captcha` VALUES ('abda4cb8-427e-4c3d-8cc0-b15448eb40be', 'f3ggx', '2023-02-26 19:17:24');
INSERT INTO `sys_captcha` VALUES ('acd6ff0b-d495-4b9b-8791-9ece767e415b', '7n264', '2023-02-27 13:57:09');
INSERT INTO `sys_captcha` VALUES ('b18690e3-bfeb-4167-8a7b-a8cf2c3afd6d', '24p4n', '2023-02-26 19:17:33');
INSERT INTO `sys_captcha` VALUES ('b194812c-59ad-49c2-8aaa-123c527f0411', '5ame8', '2023-02-26 23:05:01');
INSERT INTO `sys_captcha` VALUES ('b3c3dd10-20a0-42cd-8a5b-f5048f757b3e', 'p5n5f', '2023-01-06 17:18:06');
INSERT INTO `sys_captcha` VALUES ('b4527b8c-47e0-4668-8a34-46c53ca69998', 'b47gn', '2023-01-06 17:11:38');
INSERT INTO `sys_captcha` VALUES ('b6f72c32-5508-4479-87e1-9766b1378c80', 'm3wf7', '2023-02-27 13:18:37');
INSERT INTO `sys_captcha` VALUES ('bad56dde-a89d-4290-85bd-5cec90ddfbd4', 'awdp8', '2023-01-06 17:12:54');
INSERT INTO `sys_captcha` VALUES ('bb4177b0-d0d0-4765-88e5-70bd7b1e5f83', '35fae', '2023-02-26 19:17:18');
INSERT INTO `sys_captcha` VALUES ('bb4a2dd9-9427-445a-8f84-4b0fa3b93519', 'xpxa2', '2023-02-27 13:05:21');
INSERT INTO `sys_captcha` VALUES ('c251acda-7667-4adc-8f44-28021501730f', 'g7ydx', '2023-02-26 19:07:49');
INSERT INTO `sys_captcha` VALUES ('c4d25a0a-6ddf-470d-847c-6e80e0325ad0', '66w52', '2023-01-06 17:13:23');
INSERT INTO `sys_captcha` VALUES ('c58f5ba1-6265-45d4-823f-9807d7ef9e1b', 'wn2pd', '2023-01-06 17:21:24');
INSERT INTO `sys_captcha` VALUES ('c7110fb9-46e4-424e-854f-d668635bcbdf', 'n8wpb', '2023-01-06 17:10:28');
INSERT INTO `sys_captcha` VALUES ('c8a55ed3-acb0-426a-8670-2d296932c0c7', '54aem', '2023-02-27 13:59:28');
INSERT INTO `sys_captcha` VALUES ('cd508f06-d379-47b4-817c-ff94d6d41e60', 'ee234', '2023-03-01 19:48:11');
INSERT INTO `sys_captcha` VALUES ('ce9025e5-eb55-4631-8323-c4333947a222', '2xyfb', '2023-01-06 17:21:23');
INSERT INTO `sys_captcha` VALUES ('d20cffd5-3cf4-42f7-8a69-0f26ae5ae62a', 'bd6dg', '2023-01-12 13:36:11');
INSERT INTO `sys_captcha` VALUES ('d744f3b4-c6c8-4fa2-8e86-1c16bc780743', 'wx3fd', '2023-01-06 17:12:57');
INSERT INTO `sys_captcha` VALUES ('d9a77744-6976-41ac-8f73-3d76873b8a9c', '5ywnc', '2023-01-10 13:54:58');
INSERT INTO `sys_captcha` VALUES ('d9d5d011-0308-4a33-894f-f494ef02b933', 'fad6e', '2023-02-27 14:10:59');
INSERT INTO `sys_captcha` VALUES ('def09d6d-fc53-4e09-85b4-285110798de0', 'd32y6', '2023-01-06 17:14:27');
INSERT INTO `sys_captcha` VALUES ('e12544a7-7f4e-44d9-8d91-5f245788b955', 'b2dpe', '2023-01-11 14:58:48');
INSERT INTO `sys_captcha` VALUES ('e1ecb3f9-3177-43e5-81b7-c796233edf6f', '563cm', '2023-01-06 17:20:57');
INSERT INTO `sys_captcha` VALUES ('e52b0d62-433e-497e-8ade-3fc60edadb30', '47c5d', '2023-01-06 17:12:58');
INSERT INTO `sys_captcha` VALUES ('e8ff9862-d657-482f-8fb2-bbc69163e37d', 'acxge', '2023-03-01 22:28:14');
INSERT INTO `sys_captcha` VALUES ('e9b8030d-1eba-4c00-8cf0-b6381ea70cc9', 'b8f5n', '2023-01-12 14:24:44');
INSERT INTO `sys_captcha` VALUES ('f2280695-56f5-47c6-8f77-9b008dc33a2a', 'xnea2', '2023-02-27 10:40:03');
INSERT INTO `sys_captcha` VALUES ('f6bfe435-3ac0-420f-8536-4abcd8463887', '3xmnx', '2023-02-26 23:05:15');
INSERT INTO `sys_captcha` VALUES ('fbb0484a-afb6-420d-8ccd-1806619eecd8', 'nm7xy', '2023-01-12 14:16:21');
INSERT INTO `sys_captcha` VALUES ('fe015d9d-eb00-4258-808b-b06d915c18b3', '2gbbb', '2023-01-06 17:18:07');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(0) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '科室名称',
  `doc_num` int(0) NOT NULL COMMENT '医生人数',
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `dept_room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室房间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0：已禁用 1：已启用',
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室主任',
  `residual_num` int(0) NULL DEFAULT NULL COMMENT '剩余可预约人数',
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '科室信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '牙周科', 15, 1, '2023-03-03 14:12:57', 1, '2023-03-04 12:58:13', 'B5-301', '1', 'ys', 50, '牙周科');
INSERT INTO `sys_dept` VALUES (6, '牙龈科', 122, 1, '2023-03-04 17:42:00', NULL, NULL, 'B5-306', '1', 'ys', 23, '1311515');
INSERT INTO `sys_dept` VALUES (7, '口腔科', 10, 1, '2023-03-04 18:05:53', NULL, NULL, 'B5-402', '1', 'ys', 16, NULL);

-- ----------------------------
-- Table structure for sys_drug
-- ----------------------------
DROP TABLE IF EXISTS `sys_drug`;
CREATE TABLE `sys_drug`  (
  `drug_id` int(0) NOT NULL AUTO_INCREMENT,
  `drug_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `drug_dosage_form` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剂型',
  `drug_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `inventory` int(0) NULL DEFAULT NULL COMMENT '库存',
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单价',
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药品描述（备注）',
  `image_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `usage_dosage` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用法用量',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存放地址',
  `life_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保质期',
  `vendor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `inbound_time` datetime(0) NULL DEFAULT NULL COMMENT '入库日期',
  `outbound_time` datetime(0) NULL DEFAULT NULL COMMENT '出库日期',
  `inbound_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库批次',
  `outbound_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库批次',
  `status` int(0) NULL DEFAULT NULL COMMENT '0:未入库  1：已入库',
  PRIMARY KEY (`drug_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '药品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_drug
-- ----------------------------
INSERT INTO `sys_drug` VALUES (1, '三九感冒灵颗粒', '冲剂', '500g/一盒（5袋）', 200, 1, '2023-03-08 17:56:03', 1, '2023-03-09 23:46:50', '15.50', 'qedq', '977fa193-4e85-4d0e-99c3-b55191aed7ae.png', '每次1粒，每天3次', 'B5-301', '24', '王老吉集团', '2023-03-29 15:53:20', '2023-03-07 15:53:42', '1232', '1232', 1);
INSERT INTO `sys_drug` VALUES (2, '葡萄糖酸钙锌口服溶液', '口服液', '10毫升*20支', 1000, 1, '2023-03-10 00:08:54', 1, '2023-03-10 00:09:04', '29.00', 'awdadwa', '14d6a6e0-f8e6-49e2-bd1d-06e2a2db3a6c.jpg', '每次1粒，每天3次', 'B5-301', '24', '王老吉集团', '2023-03-11 15:53:38', '2023-03-07 15:53:42', '1232', '1232', 1);
INSERT INTO `sys_drug` VALUES (3, '九九九强力枇杷露', '口服液', '500毫升/1支', 10, 1, '2023-03-08 22:43:09', 1, '2023-03-09 23:43:21', '50.00', '止咳糖浆', '64ace7d6-724a-4b66-b6b2-514d018307c4.png', '每次1粒，每天3次', 'B5-301', '24', '王老吉集团', '2023-03-25 15:53:28', '2023-03-07 15:53:42', '1232', '1232', 1);
INSERT INTO `sys_drug` VALUES (7, 'awddwww', '冲剂', '1231', 26, 1, '2023-03-09 23:56:03', 1, '2023-03-09 20:48:19', '131.11', '31211', NULL, '每次1粒，每天3次', 'B5-301', '24', 'wasdawd', '2023-03-18 15:53:31', '2023-03-07 15:53:42', '235', '1232', 0);
INSERT INTO `sys_drug` VALUES (8, 'adwfgrg', '口服液', 'awdad', 51, 1, '2023-03-09 23:56:45', 1, '2023-03-09 20:48:01', '153.32', 'wadwad', NULL, '每次1粒，每天3次', 'B5-301', '24', 'adda', '2023-03-18 15:53:34', '2023-03-07 15:53:42', '2365', '1232', 0);
INSERT INTO `sys_drug` VALUES (9, 'awdad', '冲剂', 'adwad', 9, 1, '2023-03-09 15:10:18', 1, '2023-03-09 23:51:09', '53.32', 'awdawwd', 'efe4f6b9-c7d9-4857-ac31-49b0fe515908.png', '每次1粒，每天3次', 'B5-301', '24', '王老吉集团', '2023-03-11 15:53:38', '2023-03-07 15:53:42', '1232', '1232', 1);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `file_id` int(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_id` int(0) NULL DEFAULT NULL,
  `file_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `size` double NULL DEFAULT NULL,
  `status` int(0) NOT NULL COMMENT '1:图片文件  0：文档',
  `file_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (31, 'blob:http://localhost:8081/7417c295-5f8d-4cef-9405-e253c3bcb520', 4, 'ffa7cb7c-24ed-45bb-bd23-94ca1305e5a0.jpg', 1215200, 1, '2023-03-04 14:11:38');
INSERT INTO `sys_file` VALUES (32, 'blob:http://localhost:8081/265a44fd-89fa-4432-89a5-5611eb17e254', 1, '9d03ea6f-f919-44ad-bc7b-29abfca8fb47.jpg', 937407, 1, '2023-03-24 14:19:20');
INSERT INTO `sys_file` VALUES (33, 'blob:http://localhost:8081/dc2b2bd0-4126-4a75-b90d-3386ae9a3994', 4, 'b9199cbf-2247-4914-8175-f1bf11d9b51c.jpg', 1125321, 1, '2023-03-08 14:19:14');
INSERT INTO `sys_file` VALUES (39, NULL, 1, '140797dd-d1d9-45f8-ace1-ed0d0e7c7f2f.txt', 0, 0, '2023-03-08 16:21:52');
INSERT INTO `sys_file` VALUES (47, NULL, 1, 'c0081790-45b2-4535-8f8a-587d3d3236ce.txt', 0, 0, '2023-03-11 16:48:43');
INSERT INTO `sys_file` VALUES (48, NULL, 1, '19f63e20-6491-4f85-90c9-e970019da81e.txt', 0, 0, '2023-03-11 16:48:43');
INSERT INTO `sys_file` VALUES (49, NULL, 3, 'c0081790-45b2-4535-8f8a-587d3d3236ce.txt', 0, 0, '2023-03-11 16:49:16');
INSERT INTO `sys_file` VALUES (50, NULL, 3, '19f63e20-6491-4f85-90c9-e970019da81e.txt', 0, 0, '2023-03-11 16:49:16');
INSERT INTO `sys_file` VALUES (51, NULL, 3, 'c56a4062-1797-40bc-9f32-cac4ffba722c.txt', 0, 0, '2023-03-11 16:49:16');
INSERT INTO `sys_file` VALUES (52, NULL, 3, 'c0da834a-6874-4072-8733-5c4648c2d352.txt', 0, 0, '2023-03-11 16:49:16');

-- ----------------------------
-- Table structure for sys_gh
-- ----------------------------
DROP TABLE IF EXISTS `sys_gh`;
CREATE TABLE `sys_gh`  (
  `gh_id` int(0) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gh_time` datetime(0) NOT NULL,
  `status` int(0) NULL DEFAULT NULL COMMENT '0:已就诊  1：未就诊 2: 已过期',
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`gh_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '患者挂号信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gh
-- ----------------------------
INSERT INTO `sys_gh` VALUES (1, '1', '2023-02-27 16:06:13', 2, 'aaa', NULL, NULL, '1', '2023-02-28 21:47:08');
INSERT INTO `sys_gh` VALUES (2, '2', '2023-02-28 10:01:15', 2, 'bbb', NULL, NULL, NULL, NULL);
INSERT INTO `sys_gh` VALUES (11, '202303160025320561', '2023-03-16 00:26:06', 2, 'www', '202303160025320561', '2023-03-16 00:26:06', '1', '2023-03-16 00:41:12');
INSERT INTO `sys_gh` VALUES (12, '202303160041530746', '2023-03-16 00:40:43', 2, 'kkk', '202303160041530746', '2023-03-16 00:40:43', NULL, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(0) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '修改密码', 'itw.oralboot.modules.sys.controller.SysUserController.password()', '[{\"password\":\"admin\",\"newPassword\":\"123456\"}]', 50, '0:0:0:0:0:0:0:1', '2023-02-26 17:46:15');
INSERT INTO `sys_log` VALUES (2, 'admin', '保存用户', 'itw.oralboot.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"admin1\",\"password\":\"643db73c2ee07e40990619465e8c248764d7f2c0b27afde4ce825a9c0a055961\",\"salt\":\"a28Ruat3T9dQlIknMe2j\",\"email\":\"123131@13.com\",\"mobile\":\"15656648963\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Feb 26, 2023 11:52:42 PM\"}]', 32, '0:0:0:0:0:0:0:1', '2023-02-26 23:52:42');
INSERT INTO `sys_log` VALUES (3, 'admin', '保存用户', 'itw.oralboot.modules.sys.controller.SysUserController.save()', '[{\"userId\":6,\"username\":\"wadwad\",\"password\":\"868817c8b1b8e828d51fe21ebcc15214f8b8bf99d576f383f423c14025d89fec\",\"salt\":\"u1stzsVS3mecdR08w097\",\"email\":\"5464465@qq.com\",\"mobile\":\"123456789\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Feb 27, 2023 4:09:22 PM\"}]', 324, '0:0:0:0:0:0:0:1', '2023-02-27 16:09:23');
INSERT INTO `sys_log` VALUES (4, 'admin', '删除用户', 'itw.oralboot.modules.sys.controller.SysUserController.delete()', '[[6]]', 20, '0:0:0:0:0:0:0:1', '2023-02-27 16:09:32');
INSERT INTO `sys_log` VALUES (5, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"awsda\",\"type\":0,\"icon\":\"bianji\",\"orderNum\":0,\"list\":[]}]', 18, '0:0:0:0:0:0:0:1', '2023-03-10 11:27:45');
INSERT INTO `sys_log` VALUES (6, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"/ksgl\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 13, '0:0:0:0:0:0:0:1', '2023-03-10 11:28:46');
INSERT INTO `sys_log` VALUES (7, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 17, '0:0:0:0:0:0:0:1', '2023-03-10 11:29:36');
INSERT INTO `sys_log` VALUES (8, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"/ksgl/ksgl\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 12, '0:0:0:0:0:0:0:1', '2023-03-10 11:30:00');
INSERT INTO `sys_log` VALUES (9, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 11:31:40');
INSERT INTO `sys_log` VALUES (10, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 11:36:50');
INSERT INTO `sys_log` VALUES (11, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 11:39:40');
INSERT INTO `sys_log` VALUES (12, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"job/schedule\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 11:40:58');
INSERT INTO `sys_log` VALUES (13, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"科室管理\",\"url\":\"sys/ksgl\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 11:42:46');
INSERT INTO `sys_log` VALUES (14, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[31]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 11:53:45');
INSERT INTO `sys_log` VALUES (15, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[31]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 11:53:52');
INSERT INTO `sys_log` VALUES (16, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[32]', 68, '0:0:0:0:0:0:0:1', '2023-03-10 11:54:07');
INSERT INTO `sys_log` VALUES (17, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[31]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 11:54:22');
INSERT INTO `sys_log` VALUES (18, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[31]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 11:54:42');
INSERT INTO `sys_log` VALUES (19, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[5]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 11:54:58');
INSERT INTO `sys_log` VALUES (20, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":33,\"parentId\":0,\"name\":\"dwdada\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 12:03:14');
INSERT INTO `sys_log` VALUES (21, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[33]', 12, '0:0:0:0:0:0:0:1', '2023-03-10 12:03:20');
INSERT INTO `sys_log` VALUES (22, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":34,\"parentId\":0,\"name\":\"dawad\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 12:03:32');
INSERT INTO `sys_log` VALUES (23, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":34,\"parentId\":0,\"name\":\"dawad\",\"url\":\"adwwad\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 12:03:50');
INSERT INTO `sys_log` VALUES (24, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[34]', 14, '0:0:0:0:0:0:0:1', '2023-03-10 12:03:55');
INSERT INTO `sys_log` VALUES (25, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":35,\"parentId\":0,\"name\":\"awdawd\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 4, '0:0:0:0:0:0:0:1', '2023-03-10 12:04:23');
INSERT INTO `sys_log` VALUES (26, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":35,\"parentId\":0,\"name\":\"awdawd\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 3, '0:0:0:0:0:0:0:1', '2023-03-10 12:04:29');
INSERT INTO `sys_log` VALUES (27, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":35,\"parentId\":0,\"name\":\"awdawd\",\"url\":\"sys/user\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 12:04:49');
INSERT INTO `sys_log` VALUES (28, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[35]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 12:05:17');
INSERT INTO `sys_log` VALUES (29, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":36,\"parentId\":0,\"name\":\"abc\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"config\",\"orderNum\":0,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 13:47:34');
INSERT INTO `sys_log` VALUES (30, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":36,\"parentId\":0,\"name\":\"abc\",\"url\":\"abc/abc\",\"perms\":\"\",\"type\":1,\"icon\":\"config\",\"orderNum\":0,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 13:47:48');
INSERT INTO `sys_log` VALUES (31, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":36,\"parentId\":0,\"name\":\"abc\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":1,\"icon\":\"config\",\"orderNum\":0,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 13:50:18');
INSERT INTO `sys_log` VALUES (32, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":36,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":1,\"icon\":\"config\",\"orderNum\":1,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 13:57:24');
INSERT INTO `sys_log` VALUES (33, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[36]', 15, '0:0:0:0:0:0:0:1', '2023-03-10 13:59:15');
INSERT INTO `sys_log` VALUES (34, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":37,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 13:59:38');
INSERT INTO `sys_log` VALUES (35, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":37,\"parentId\":37,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 12, '0:0:0:0:0:0:0:1', '2023-03-10 14:00:08');
INSERT INTO `sys_log` VALUES (36, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[37]', 4, '0:0:0:0:0:0:0:1', '2023-03-10 14:02:24');
INSERT INTO `sys_log` VALUES (37, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[37]', 2, '0:0:0:0:0:0:0:1', '2023-03-10 14:02:48');
INSERT INTO `sys_log` VALUES (38, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[37]', 1, '0:0:0:0:0:0:0:1', '2023-03-10 14:03:06');
INSERT INTO `sys_log` VALUES (39, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":37,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 14:03:41');
INSERT INTO `sys_log` VALUES (40, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":37,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"role\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:05:04');
INSERT INTO `sys_log` VALUES (41, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":37,\"parentId\":37,\"name\":\"科室管理1\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"role\",\"orderNum\":1,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 14:05:54');
INSERT INTO `sys_log` VALUES (42, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":37,\"parentId\":0,\"name\":\"科室管理1\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"role\",\"orderNum\":1,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 14:06:19');
INSERT INTO `sys_log` VALUES (43, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":37,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"dangdifill\",\"orderNum\":1,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 14:07:47');
INSERT INTO `sys_log` VALUES (44, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[37]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 14:08:30');
INSERT INTO `sys_log` VALUES (45, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":38,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 14:08:42');
INSERT INTO `sys_log` VALUES (46, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":39,\"parentId\":38,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 13, '0:0:0:0:0:0:0:1', '2023-03-10 14:08:55');
INSERT INTO `sys_log` VALUES (47, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":40,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 14:18:45');
INSERT INTO `sys_log` VALUES (48, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":41,\"parentId\":40,\"name\":\"挂号管理\",\"url\":\"ghyy/gh\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:19:23');
INSERT INTO `sys_log` VALUES (49, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":42,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 14:27:20');
INSERT INTO `sys_log` VALUES (50, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":43,\"parentId\":42,\"name\":\"挂号管理\",\"url\":\"ghyy/gh\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:27:55');
INSERT INTO `sys_log` VALUES (51, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":44,\"parentId\":42,\"name\":\"预约管理\",\"url\":\"ghyy/yy\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":2,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:28:26');
INSERT INTO `sys_log` VALUES (52, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":38,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":2,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 14:29:03');
INSERT INTO `sys_log` VALUES (53, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":45,\"parentId\":0,\"name\":\"电子病历\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":3,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 14:29:35');
INSERT INTO `sys_log` VALUES (54, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":46,\"parentId\":45,\"name\":\"病历管理\",\"url\":\"dzbl/blgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 8, '0:0:0:0:0:0:0:1', '2023-03-10 14:30:30');
INSERT INTO `sys_log` VALUES (55, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":47,\"parentId\":45,\"name\":\"病历文档管理\",\"url\":\"dzbl/blwdgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":2,\"list\":[]}]', 8, '0:0:0:0:0:0:0:1', '2023-03-10 14:31:24');
INSERT INTO `sys_log` VALUES (56, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":48,\"parentId\":0,\"name\":\"药物管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":4,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:32:04');
INSERT INTO `sys_log` VALUES (57, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":49,\"parentId\":0,\"name\":\"药品分类\",\"url\":\"ywgl/ypfl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 14:32:39');
INSERT INTO `sys_log` VALUES (58, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[49]', 17, '0:0:0:0:0:0:0:1', '2023-03-10 14:32:51');
INSERT INTO `sys_log` VALUES (59, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":50,\"parentId\":48,\"name\":\"药品分类\",\"url\":\"ywgl/ypfl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 8, '0:0:0:0:0:0:0:1', '2023-03-10 14:33:12');
INSERT INTO `sys_log` VALUES (60, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":46,\"parentId\":45,\"name\":\"病历管理\",\"url\":\"dzbl/blgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 8, '0:0:0:0:0:0:0:1', '2023-03-10 14:33:41');
INSERT INTO `sys_log` VALUES (61, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":47,\"parentId\":45,\"name\":\"病历文档管理\",\"url\":\"dzbl/blwdgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:33:51');
INSERT INTO `sys_log` VALUES (62, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":51,\"parentId\":48,\"name\":\"药品出入库\",\"url\":\"ywgl/ypcrk\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:34:45');
INSERT INTO `sys_log` VALUES (63, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":52,\"parentId\":48,\"name\":\"药物单（处方单）\",\"url\":\"ywgl/ywd\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":2,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:35:22');
INSERT INTO `sys_log` VALUES (64, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":53,\"parentId\":0,\"name\":\"缴费管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":5,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 14:35:49');
INSERT INTO `sys_log` VALUES (65, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":54,\"parentId\":53,\"name\":\"缴费订单\",\"url\":\"jfgl/jfdd\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 11, '0:0:0:0:0:0:0:1', '2023-03-10 14:36:18');
INSERT INTO `sys_log` VALUES (66, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":55,\"parentId\":0,\"name\":\"人员管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":6,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 14:36:48');
INSERT INTO `sys_log` VALUES (67, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":56,\"parentId\":55,\"name\":\"患者管理\",\"url\":\"rygl/hzgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:37:21');
INSERT INTO `sys_log` VALUES (68, 'admin', '保存菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":57,\"parentId\":55,\"name\":\"医护管理\",\"url\":\"rygl/yhgl\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 11, '0:0:0:0:0:0:0:1', '2023-03-10 14:37:45');
INSERT INTO `sys_log` VALUES (69, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":42,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"ghyy\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 14:48:17');
INSERT INTO `sys_log` VALUES (70, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":42,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"ghyy/yy\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:48:56');
INSERT INTO `sys_log` VALUES (71, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":42,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:49:43');
INSERT INTO `sys_log` VALUES (72, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":45,\"parentId\":0,\"name\":\"电子病历\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":3,\"list\":[]}]', 2, '0:0:0:0:0:0:0:1', '2023-03-10 14:51:07');
INSERT INTO `sys_log` VALUES (73, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":38,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":2,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 14:51:18');
INSERT INTO `sys_log` VALUES (74, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":45,\"parentId\":0,\"name\":\"电子病历\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":3,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 14:51:40');
INSERT INTO `sys_log` VALUES (75, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":48,\"parentId\":0,\"name\":\"药物管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":4,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 14:51:47');
INSERT INTO `sys_log` VALUES (76, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":53,\"parentId\":0,\"name\":\"缴费管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":5,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:51:55');
INSERT INTO `sys_log` VALUES (77, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":55,\"parentId\":0,\"name\":\"人员管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":6,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 14:52:03');
INSERT INTO `sys_log` VALUES (78, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[5]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 15:04:50');
INSERT INTO `sys_log` VALUES (79, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[7]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 15:06:01');
INSERT INTO `sys_log` VALUES (80, 'admin', '删除菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.delete()', '[27]', 0, '0:0:0:0:0:0:0:1', '2023-03-10 15:07:17');
INSERT INTO `sys_log` VALUES (81, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":14,\"parentId\":29,\"name\":\"日志列表\",\"perms\":\"sys:schedule:log\",\"type\":2,\"orderNum\":0,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:10:42');
INSERT INTO `sys_log` VALUES (82, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":42,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"shoucangfill\",\"orderNum\":1,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 15:12:32');
INSERT INTO `sys_log` VALUES (83, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":43,\"parentId\":42,\"name\":\"挂号管理\",\"url\":\"ghyy/gh\",\"perms\":\"\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":1,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:12:58');
INSERT INTO `sys_log` VALUES (84, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":44,\"parentId\":42,\"name\":\"预约管理\",\"url\":\"ghyy/yy\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":2,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:13:07');
INSERT INTO `sys_log` VALUES (85, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":38,\"parentId\":0,\"name\":\"科室管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"shouye\",\"orderNum\":2,\"list\":[]}]', 8, '0:0:0:0:0:0:0:1', '2023-03-10 15:13:33');
INSERT INTO `sys_log` VALUES (86, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":39,\"parentId\":38,\"name\":\"科室管理\",\"url\":\"ksgl/ksgl\",\"perms\":\"\",\"type\":1,\"icon\":\"job\",\"orderNum\":0,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 15:13:53');
INSERT INTO `sys_log` VALUES (87, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":45,\"parentId\":0,\"name\":\"电子病历\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"editor\",\"orderNum\":3,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:14:15');
INSERT INTO `sys_log` VALUES (88, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":46,\"parentId\":45,\"name\":\"病历管理\",\"url\":\"dzbl/blgl\",\"perms\":\"\",\"type\":1,\"icon\":\"config\",\"orderNum\":0,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 15:14:28');
INSERT INTO `sys_log` VALUES (89, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":47,\"parentId\":45,\"name\":\"病历文档管理\",\"url\":\"dzbl/blwdgl\",\"perms\":\"\",\"type\":1,\"icon\":\"oss\",\"orderNum\":1,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 15:14:39');
INSERT INTO `sys_log` VALUES (90, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":48,\"parentId\":0,\"name\":\"药物管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"geren\",\"orderNum\":4,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:15:15');
INSERT INTO `sys_log` VALUES (91, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":50,\"parentId\":48,\"name\":\"药品分类\",\"url\":\"ywgl/ypfl\",\"perms\":\"\",\"type\":1,\"icon\":\"zhedie\",\"orderNum\":0,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 15:15:34');
INSERT INTO `sys_log` VALUES (92, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":51,\"parentId\":48,\"name\":\"药品出入库\",\"url\":\"ywgl/ypcrk\",\"perms\":\"\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":1,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 15:15:49');
INSERT INTO `sys_log` VALUES (93, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":52,\"parentId\":48,\"name\":\"药物单（处方单）\",\"url\":\"ywgl/ywd\",\"perms\":\"\",\"type\":1,\"icon\":\"log\",\"orderNum\":2,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2023-03-10 15:16:11');
INSERT INTO `sys_log` VALUES (94, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":53,\"parentId\":0,\"name\":\"缴费管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"bianji\",\"orderNum\":5,\"list\":[]}]', 4, '0:0:0:0:0:0:0:1', '2023-03-10 15:16:25');
INSERT INTO `sys_log` VALUES (95, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":54,\"parentId\":53,\"name\":\"缴费订单\",\"url\":\"jfgl/jfdd\",\"perms\":\"\",\"type\":1,\"icon\":\"sousuo\",\"orderNum\":0,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:16:36');
INSERT INTO `sys_log` VALUES (96, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":55,\"parentId\":0,\"name\":\"人员管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"geren\",\"orderNum\":6,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:16:51');
INSERT INTO `sys_log` VALUES (97, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":48,\"parentId\":0,\"name\":\"药物管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"xiangqu\",\"orderNum\":4,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:16:59');
INSERT INTO `sys_log` VALUES (98, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":56,\"parentId\":55,\"name\":\"患者管理\",\"url\":\"rygl/hzgl\",\"perms\":\"\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":0,\"list\":[]}]', 5, '0:0:0:0:0:0:0:1', '2023-03-10 15:17:28');
INSERT INTO `sys_log` VALUES (99, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":57,\"parentId\":55,\"name\":\"医护管理\",\"url\":\"rygl/yhgl\",\"perms\":\"\",\"type\":1,\"icon\":\"pinglun\",\"orderNum\":1,\"list\":[]}]', 6, '0:0:0:0:0:0:0:1', '2023-03-10 15:17:38');
INSERT INTO `sys_log` VALUES (100, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":42,\"parentId\":0,\"name\":\"挂号预约\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"duanxin\",\"orderNum\":1,\"list\":[]}]', 10, '0:0:0:0:0:0:0:1', '2023-03-10 15:17:46');
INSERT INTO `sys_log` VALUES (101, 'admin', '修改菜单', 'itw.oralboot.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":7,\"list\":[]}]', 9, '0:0:0:0:0:0:0:1', '2023-03-10 15:18:44');
INSERT INTO `sys_log` VALUES (102, 'admin', '保存角色', 'itw.oralboot.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"医生\",\"remark\":\"牙周科\",\"createUserId\":1,\"menuIdList\":[42,43,44,38,39,45,46,47,-666666],\"createTime\":\"Mar 12, 2023 3:56:49 PM\"}]', 45, '0:0:0:0:0:0:0:1', '2023-03-12 15:56:50');
INSERT INTO `sys_log` VALUES (103, 'admin', '保存角色', 'itw.oralboot.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"药剂师\",\"remark\":\"药剂师\",\"createUserId\":1,\"menuIdList\":[48,50,51,52,-666666],\"createTime\":\"Mar 12, 2023 5:05:31 PM\"}]', 109, '0:0:0:0:0:0:0:1', '2023-03-12 17:05:31');
INSERT INTO `sys_log` VALUES (104, 'admin', '修改角色', 'itw.oralboot.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"医生\",\"remark\":\"牙周科\",\"createUserId\":1,\"menuIdList\":[42,43,44,38,39,45,46,47,56,-666666,55]}]', 61, '0:0:0:0:0:0:0:1', '2023-03-12 17:05:43');
INSERT INTO `sys_log` VALUES (105, 'admin', '保存用户', 'itw.oralboot.modules.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"yj\",\"password\":\"b09b39d6bf194ee7f369f080fb655f18d60cd6ae3b4e48f3c36db4c3f374cfbf\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[1,2],\"createUserId\":1,\"createTime\":\"Mar 12, 2023 7:11:15 PM\"}]', 206, '0:0:0:0:0:0:0:1', '2023-03-12 19:11:15');
INSERT INTO `sys_log` VALUES (106, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":10,\"username\":\"yj\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"name\":\"yj\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[1,2],\"createUserId\":1}]', 29, '0:0:0:0:0:0:0:1', '2023-03-12 19:13:54');
INSERT INTO `sys_log` VALUES (107, 'admin', '保存用户', 'itw.oralboot.modules.sys.controller.SysUserController.save()', '[{\"userId\":11,\"username\":\"wtd\",\"password\":\"9673f273404b4ef455fcd0a049e29766640a21ed334e7245909ab005badc07af\",\"salt\":\"xTDVZIBcCS4G0Dfl99tC\",\"name\":\"wtd\",\"flag\":\"1\",\"email\":\"2357996956@qq.com\",\"mobile\":\"18219135363\",\"status\":1,\"roleIdList\":[1,2],\"createUserId\":1,\"createTime\":\"Mar 12, 2023 7:15:27 PM\"}]', 19, '0:0:0:0:0:0:0:1', '2023-03-12 19:15:27');
INSERT INTO `sys_log` VALUES (108, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":10,\"username\":\"yj\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"name\":\"yj\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 10, '0:0:0:0:0:0:0:1', '2023-03-12 19:18:34');
INSERT INTO `sys_log` VALUES (109, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":10,\"username\":\"yj\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"name\":\"yj\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1}]', 15, '0:0:0:0:0:0:0:1', '2023-03-12 19:18:51');
INSERT INTO `sys_log` VALUES (110, 'admin', '保存角色', 'itw.oralboot.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"管理员\",\"remark\":\"超级管理员\",\"createUserId\":1,\"menuIdList\":[42,43,44,38,39,45,46,47,48,50,51,52,53,54,55,56,57,1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,29,14,-666666],\"createTime\":\"Mar 12, 2023 7:23:01 PM\"}]', 64, '0:0:0:0:0:0:0:1', '2023-03-12 19:23:01');
INSERT INTO `sys_log` VALUES (111, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":11,\"username\":\"wtd\",\"salt\":\"xTDVZIBcCS4G0Dfl99tC\",\"name\":\"wtd\",\"flag\":\"1\",\"email\":\"2357996956@qq.com\",\"mobile\":\"18219135363\",\"status\":1,\"roleIdList\":[1,2,3],\"createUserId\":1}]', 109, '0:0:0:0:0:0:0:1', '2023-03-12 19:28:43');
INSERT INTO `sys_log` VALUES (112, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":10,\"username\":\"yj\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"name\":\"yj\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[2,1],\"createUserId\":1}]', 19, '0:0:0:0:0:0:0:1', '2023-03-12 19:29:00');
INSERT INTO `sys_log` VALUES (113, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":10,\"username\":\"yj\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"name\":\"yj\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1}]', 12, '0:0:0:0:0:0:0:1', '2023-03-12 19:29:23');
INSERT INTO `sys_log` VALUES (114, 'admin', '修改用户', 'itw.oralboot.modules.sys.controller.SysUserController.update()', '[{\"userId\":10,\"username\":\"yj\",\"salt\":\"rYiWHPZ3GAdCqVqshZG5\",\"name\":\"yj\",\"flag\":\"0\",\"email\":\"12345@163.com\",\"mobile\":\"15646235956\",\"status\":1,\"roleIdList\":[2,1],\"createUserId\":1}]', 11, '0:0:0:0:0:0:0:1', '2023-03-12 19:32:38');
INSERT INTO `sys_log` VALUES (115, 'wtd', '保存角色', 'itw.oralboot.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"管理员\",\"remark\":\"超级管理员\",\"createUserId\":11,\"menuIdList\":[42,43,44,38,39,45,46,47,48,50,51,52,53,54,55,56,57,1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,29,14,-666666],\"createTime\":\"Mar 12, 2023 7:34:47 PM\"}]', 72, '0:0:0:0:0:0:0:1', '2023-03-12 19:34:48');
INSERT INTO `sys_log` VALUES (116, 'wtd', '保存用户', 'itw.oralboot.modules.sys.controller.SysUserController.save()', '[{\"userId\":12,\"username\":\"wtd1\",\"password\":\"56d512320b756b57279a5544ccd8734c4c20b19f6cf069f03ce8832b0e0e4116\",\"salt\":\"uXK2ra7DHnoPv6RwRU1o\",\"name\":\"wtd1\",\"flag\":\"0\",\"email\":\"W535410086@163.com\",\"mobile\":\"19854802938\",\"status\":1,\"roleIdList\":[4],\"createUserId\":11,\"createTime\":\"Mar 12, 2023 7:35:26 PM\"}]', 19, '0:0:0:0:0:0:0:1', '2023-03-12 19:35:26');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 7);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu` VALUES (14, 29, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu` VALUES (38, 0, '科室管理', '', '', 0, 'shouye', 2);
INSERT INTO `sys_menu` VALUES (39, 38, '科室管理', 'ksgl/ksgl', '', 1, 'job', 0);
INSERT INTO `sys_menu` VALUES (42, 0, '挂号预约', '', '', 0, 'duanxin', 1);
INSERT INTO `sys_menu` VALUES (43, 42, '挂号管理', 'ghyy/gh', '', 1, 'dangdifill', 1);
INSERT INTO `sys_menu` VALUES (44, 42, '预约管理', 'ghyy/yy', '', 1, 'daohang', 2);
INSERT INTO `sys_menu` VALUES (45, 0, '电子病历', '', '', 0, 'editor', 3);
INSERT INTO `sys_menu` VALUES (46, 45, '病历管理', 'dzbl/blgl', '', 1, 'config', 0);
INSERT INTO `sys_menu` VALUES (47, 45, '病历文档管理', 'dzbl/blwdgl', '', 1, 'oss', 1);
INSERT INTO `sys_menu` VALUES (48, 0, '药物管理', '', '', 0, 'xiangqu', 4);
INSERT INTO `sys_menu` VALUES (50, 48, '药品分类', 'ywgl/ypfl', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (51, 48, '药品出入库', 'ywgl/ypcrk', '', 1, 'mudedi', 1);
INSERT INTO `sys_menu` VALUES (52, 48, '药物单（处方单）', 'ywgl/ywd', '', 1, 'log', 2);
INSERT INTO `sys_menu` VALUES (53, 0, '缴费管理', '', '', 0, 'bianji', 5);
INSERT INTO `sys_menu` VALUES (54, 53, '缴费订单', 'jfgl/jfdd', '', 1, 'sousuo', 0);
INSERT INTO `sys_menu` VALUES (55, 0, '人员管理', '', '', 0, 'geren', 6);
INSERT INTO `sys_menu` VALUES (56, 55, '患者管理', 'rygl/hzgl', '', 1, 'mudedi', 0);
INSERT INTO `sys_menu` VALUES (57, 55, '医护管理', 'rygl/yhgl', '', 1, 'pinglun', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '医生', '牙周科', '1', '2023-03-12 15:56:50');
INSERT INTO `sys_role` VALUES (2, '药剂师', '药剂师', '1', '2023-03-12 17:05:31');
INSERT INTO `sys_role` VALUES (3, '管理员', '超级管理员', '1', '2023-03-12 19:23:01');
INSERT INTO `sys_role` VALUES (4, '管理员', '超级管理员', '11', '2023-03-12 19:34:48');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (10, 2, 48);
INSERT INTO `sys_role_menu` VALUES (11, 2, 50);
INSERT INTO `sys_role_menu` VALUES (12, 2, 51);
INSERT INTO `sys_role_menu` VALUES (13, 2, 52);
INSERT INTO `sys_role_menu` VALUES (14, 2, -666666);
INSERT INTO `sys_role_menu` VALUES (15, 1, 42);
INSERT INTO `sys_role_menu` VALUES (16, 1, 43);
INSERT INTO `sys_role_menu` VALUES (17, 1, 44);
INSERT INTO `sys_role_menu` VALUES (18, 1, 38);
INSERT INTO `sys_role_menu` VALUES (19, 1, 39);
INSERT INTO `sys_role_menu` VALUES (20, 1, 45);
INSERT INTO `sys_role_menu` VALUES (21, 1, 46);
INSERT INTO `sys_role_menu` VALUES (22, 1, 47);
INSERT INTO `sys_role_menu` VALUES (23, 1, 56);
INSERT INTO `sys_role_menu` VALUES (24, 1, -666666);
INSERT INTO `sys_role_menu` VALUES (25, 1, 55);
INSERT INTO `sys_role_menu` VALUES (26, 3, 42);
INSERT INTO `sys_role_menu` VALUES (27, 3, 43);
INSERT INTO `sys_role_menu` VALUES (28, 3, 44);
INSERT INTO `sys_role_menu` VALUES (29, 3, 38);
INSERT INTO `sys_role_menu` VALUES (30, 3, 39);
INSERT INTO `sys_role_menu` VALUES (31, 3, 45);
INSERT INTO `sys_role_menu` VALUES (32, 3, 46);
INSERT INTO `sys_role_menu` VALUES (33, 3, 47);
INSERT INTO `sys_role_menu` VALUES (34, 3, 48);
INSERT INTO `sys_role_menu` VALUES (35, 3, 50);
INSERT INTO `sys_role_menu` VALUES (36, 3, 51);
INSERT INTO `sys_role_menu` VALUES (37, 3, 52);
INSERT INTO `sys_role_menu` VALUES (38, 3, 53);
INSERT INTO `sys_role_menu` VALUES (39, 3, 54);
INSERT INTO `sys_role_menu` VALUES (40, 3, 55);
INSERT INTO `sys_role_menu` VALUES (41, 3, 56);
INSERT INTO `sys_role_menu` VALUES (42, 3, 57);
INSERT INTO `sys_role_menu` VALUES (43, 3, 1);
INSERT INTO `sys_role_menu` VALUES (44, 3, 2);
INSERT INTO `sys_role_menu` VALUES (45, 3, 15);
INSERT INTO `sys_role_menu` VALUES (46, 3, 16);
INSERT INTO `sys_role_menu` VALUES (47, 3, 17);
INSERT INTO `sys_role_menu` VALUES (48, 3, 18);
INSERT INTO `sys_role_menu` VALUES (49, 3, 3);
INSERT INTO `sys_role_menu` VALUES (50, 3, 19);
INSERT INTO `sys_role_menu` VALUES (51, 3, 20);
INSERT INTO `sys_role_menu` VALUES (52, 3, 21);
INSERT INTO `sys_role_menu` VALUES (53, 3, 22);
INSERT INTO `sys_role_menu` VALUES (54, 3, 4);
INSERT INTO `sys_role_menu` VALUES (55, 3, 23);
INSERT INTO `sys_role_menu` VALUES (56, 3, 24);
INSERT INTO `sys_role_menu` VALUES (57, 3, 25);
INSERT INTO `sys_role_menu` VALUES (58, 3, 26);
INSERT INTO `sys_role_menu` VALUES (59, 3, 29);
INSERT INTO `sys_role_menu` VALUES (60, 3, 14);
INSERT INTO `sys_role_menu` VALUES (61, 3, -666666);
INSERT INTO `sys_role_menu` VALUES (62, 4, 42);
INSERT INTO `sys_role_menu` VALUES (63, 4, 43);
INSERT INTO `sys_role_menu` VALUES (64, 4, 44);
INSERT INTO `sys_role_menu` VALUES (65, 4, 38);
INSERT INTO `sys_role_menu` VALUES (66, 4, 39);
INSERT INTO `sys_role_menu` VALUES (67, 4, 45);
INSERT INTO `sys_role_menu` VALUES (68, 4, 46);
INSERT INTO `sys_role_menu` VALUES (69, 4, 47);
INSERT INTO `sys_role_menu` VALUES (70, 4, 48);
INSERT INTO `sys_role_menu` VALUES (71, 4, 50);
INSERT INTO `sys_role_menu` VALUES (72, 4, 51);
INSERT INTO `sys_role_menu` VALUES (73, 4, 52);
INSERT INTO `sys_role_menu` VALUES (74, 4, 53);
INSERT INTO `sys_role_menu` VALUES (75, 4, 54);
INSERT INTO `sys_role_menu` VALUES (76, 4, 55);
INSERT INTO `sys_role_menu` VALUES (77, 4, 56);
INSERT INTO `sys_role_menu` VALUES (78, 4, 57);
INSERT INTO `sys_role_menu` VALUES (79, 4, 1);
INSERT INTO `sys_role_menu` VALUES (80, 4, 2);
INSERT INTO `sys_role_menu` VALUES (81, 4, 15);
INSERT INTO `sys_role_menu` VALUES (82, 4, 16);
INSERT INTO `sys_role_menu` VALUES (83, 4, 17);
INSERT INTO `sys_role_menu` VALUES (84, 4, 18);
INSERT INTO `sys_role_menu` VALUES (85, 4, 3);
INSERT INTO `sys_role_menu` VALUES (86, 4, 19);
INSERT INTO `sys_role_menu` VALUES (87, 4, 20);
INSERT INTO `sys_role_menu` VALUES (88, 4, 21);
INSERT INTO `sys_role_menu` VALUES (89, 4, 22);
INSERT INTO `sys_role_menu` VALUES (90, 4, 4);
INSERT INTO `sys_role_menu` VALUES (91, 4, 23);
INSERT INTO `sys_role_menu` VALUES (92, 4, 24);
INSERT INTO `sys_role_menu` VALUES (93, 4, 25);
INSERT INTO `sys_role_menu` VALUES (94, 4, 26);
INSERT INTO `sys_role_menu` VALUES (95, 4, 29);
INSERT INTO `sys_role_menu` VALUES (96, 4, 14);
INSERT INTO `sys_role_menu` VALUES (97, 4, -666666);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL COMMENT '1:男  0：女',
  `modify_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者ID',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1:超级管理员 0：医护人员',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11', 'admin', 1, NULL, NULL, '1');
INSERT INTO `sys_user` VALUES (2, 'ys', 'cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '12356458995', 1, 1, '2023-02-28 23:41:29', 'ys', 1, NULL, NULL, '0');
INSERT INTO `sys_user` VALUES (10, 'yj', 'b09b39d6bf194ee7f369f080fb655f18d60cd6ae3b4e48f3c36db4c3f374cfbf', 'rYiWHPZ3GAdCqVqshZG5', '12345@163.com', '15646235956', 1, 1, '2023-03-12 19:11:15', 'yj', NULL, NULL, NULL, '0');
INSERT INTO `sys_user` VALUES (11, 'wtd', '9673f273404b4ef455fcd0a049e29766640a21ed334e7245909ab005badc07af', 'xTDVZIBcCS4G0Dfl99tC', '2357996956@qq.com', '18219135363', 1, 1, '2023-03-12 19:15:27', 'wtd', NULL, NULL, NULL, '1');
INSERT INTO `sys_user` VALUES (12, 'wtd1', '56d512320b756b57279a5544ccd8734c4c20b19f6cf069f03ce8832b0e0e4116', 'uXK2ra7DHnoPv6RwRU1o', 'W535410086@163.com', '19854802938', 1, 11, '2023-03-12 19:35:26', 'wtd1', NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 2, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (11, 11, 1);
INSERT INTO `sys_user_role` VALUES (12, 11, 2);
INSERT INTO `sys_user_role` VALUES (13, 11, 3);
INSERT INTO `sys_user_role` VALUES (17, 10, 2);
INSERT INTO `sys_user_role` VALUES (18, 10, 1);
INSERT INTO `sys_user_role` VALUES (19, 12, 4);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(0) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 'd67b4bd3471f6749c54544ddaf347aa0', '2023-03-18 04:21:28', '2023-03-17 16:21:28');
INSERT INTO `sys_user_token` VALUES (2, 'b663eaba017b59ff42f78b4dc1d8ff46', '2023-03-13 07:33:03', '2023-03-12 19:33:03');
INSERT INTO `sys_user_token` VALUES (10, 'e3bfa15e02312cbbf4cbda05767bb8db', '2023-03-13 07:33:28', '2023-03-12 19:33:28');
INSERT INTO `sys_user_token` VALUES (11, 'f2df90097cb02983da1c5e00ee279b34', '2023-03-13 07:36:05', '2023-03-12 19:36:05');

SET FOREIGN_KEY_CHECKS = 1;
