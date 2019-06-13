INSERT INTO users (username, password, isEnabled, isTokenExpired) VALUES
    ('John', '', true, true),
    ('Marie', '', true, true);

INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

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

INSERT INTO role_members (roleId, memberId) VALUES
    (1, 1),
    (2, 2);

INSERT INTO role_privileges (roleId, privilegeId) VALUES
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