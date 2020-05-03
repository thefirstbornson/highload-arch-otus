CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `f_name` varchar(50) NOT NULL,
                        `l_name` varchar(50) NOT NULL,
                        `sex` varchar(50) NOT NULL,
                        `email` varchar(50) NOT NULL,
                        `interests` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `age` int(11) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;