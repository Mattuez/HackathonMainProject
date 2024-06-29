SET foreign_key_checks = 0;

DELETE
FROM access_level;
DELETE
FROM access_level_permission;
DELETE
FROM _user;
DELETE
FROM user_access_level;
DELETE
FROM permission;
DELETE
FROM _transaction;

ALTER TABLE access_level
    AUTO_INCREMENT = 1;
ALTER TABLE access_level_permission
    AUTO_INCREMENT = 1;
ALTER TABLE permission
    AUTO_INCREMENT = 1;
ALTER TABLE _user
    AUTO_INCREMENT = 1;
ALTER TABLE user_access_level
    AUTO_INCREMENT = 1;
ALTER TABLE _transaction
    AUTO_INCREMENT = 1;

insert into permission(id, name, description)
VALUES (1, 'LER', 'Pode acessar os recursos de consulta'),
(2, 'ESCREVER', 'Pode acessar os recursos de escrita e edição'),
(3, 'DELETAR', 'Pode acessar os recursos de exclusão');

insert into access_level(id, name) VALUES (1, 'administrador');

INSERT INTO access_level_permission (access_level_id, permission_id)
SELECT 1, id
FROM permission;

insert into _user (id, email, password, cpf, full_name)
values (1, 'matheuslins45@gmail.com', '$2a$12$FKxpA76BhGYxHLyMGcvzg.7.j99D7FfxL3N61bJkEdc3mVfff3k.u', '328.010.520-00', 'Matheus Medeiros'),
(2, 'exemplo@gmail.com', '$2a$12$jNzLAaz8fEzjwXLFm7Ajou2D.7zhiO7UqEDRI78MTCVM.yswLDBNC', '154.366.280-36', 'example example');

insert into user_access_level (user_id, access_level_id)
values (1, 1);