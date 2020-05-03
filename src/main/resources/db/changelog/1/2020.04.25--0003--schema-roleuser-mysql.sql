CREATE TABLE `user_role` (
                             `user` bigint(20) NOT NULL,
                             `role` bigint(20) NOT NULL,
                             `user_key` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`user`,`role`),
                             KEY `user_role_role_2` (`role`),
                             CONSTRAINT `user_role_role_2` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
                             CONSTRAINT `user_role_user_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;