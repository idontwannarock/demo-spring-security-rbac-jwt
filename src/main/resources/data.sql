DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO users (username, password, enabled) VALUES
    ('John', '', 1),
    ('Marry', '', 1);

DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS roles(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

DROP TABLE IF EXISTS privileges;

CREATE TABLE IF NOT EXISTS privileges(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO privileges (name) VALUES
    ('CREATE_USER'),
    ('RETRIEVE_USER'),
    ('UPDATE_USER'),
    ('DELETE_USER'),
    ('CREATE_ROLE'),
    ('RETRIEVE_ROLE'),
    ('UPDATE_ROLE'),
    ('DELETE_ROLE'),
    ('CREATE_PRIVILEGE'),
    ('RETRIEVE_PRIVILEGE'),
    ('UPDATE_PRIVILEGE'),
    ('DELETE_PRIVILEGE'),
    ('CREATE_ROLE_MEMBER'),
    ('RETRIEVE_ROLE_MEMBER'),
    ('UPDATE_ROLE_MEMBER'),
    ('DELETE_ROLE_MEMBER'),
    ('CREATE_ROLE_PRIVILEGE'),
    ('RETRIEVE_ROLE_PRIVILEGE'),
    ('UPDATE_ROLE_PRIVILEGE'),
    ('DELETE_ROLE_PRIVILEGE');

DROP TABLE IF EXISTS role_members;

CREATE TABLE IF NOT EXISTS role_members(
    `roles_id` bigint(20) NOT NULL,
    `members_id` bigint(20) NOT NULL,
    KEY `fk_role_members_users` (`members_id`),
    KEY `fk_role_members_roles` (`roles_id`),
    CONSTRAINT `fk_role_members_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `fk_role_members_users` FOREIGN KEY (`members_id`) REFERENCES `users` (`id`)
);

INSERT INTO role_members (roles_id, members_id) VALUES
    (1, 1),
    (2, 2);

DROP TABLE IF EXISTS role_privileges;

CREATE TABLE IF NOT EXISTS role_privileges(
    `roles_id` bigint(20) NOT NULL,
    `privileges_id` bigint(20) NOT NULL,
    KEY `fk_role_privileges_privileges` (`privileges_id`),
    KEY `fk_role_privileges_roles` (`roles_id`),
    CONSTRAINT `fk_role_privileges_privileges` FOREIGN KEY (`privileges_id`) REFERENCES `privileges` (`id`),
    CONSTRAINT `fk_role_privileges_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
);

INSERT INTO role_privileges (roles_id, privileges_id) VALUES
    (1, 2),
    (1, 6),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9),
    (2, 10),
    (2, 11),
    (2, 12),
    (2, 13),
    (2, 14),
    (2, 15),
    (2, 16),
    (2, 17),
    (2, 18),
    (2, 19),
    (2, 20);