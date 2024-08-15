INSERT INTO usuario (id, apellido, email, nombre, password, username)
VALUES (1, 'postgres', 'postgres@postgres.cl', 'postgres', '$2a$10$SLcJGBnMfhzSrYogTPQF3eGh34BKPREmfeEy/lVPwrkiqIUudJ/Ga', 'postgres');

INSERT INTO roles (id, nombre)
VALUES
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN'),
    (3, 'ROLE_MANAGER'),
    (4, 'ROLE_SUPPORT'),
    (5, 'ROLE_OPERATOR');

INSERT INTO usuario_roles (usuario_id, rol_id)
VALUES (1, 2);