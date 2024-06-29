insert into permission(id, name, description) VALUES (1, 'LER', 'Pode acessar os recursos de consulta'), (2, 'ESCREVER', 'Pode acessar os recursos de escrita e edição'), (3, 'DELETAR', 'Pode acessar os recursos de exclusão');

insert into access_level(id, name) VALUES (1, 'administrador'), (2, 'convidado');

insert into access_level_permission(access_level_id, permission_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1);

insert into _user (id, email, password) values (1, 'matheuslins45@gmail.com', '$2a$12$FKxpA76BhGYxHLyMGcvzg.7.j99D7FfxL3N61bJkEdc3mVfff3k.u'), (2, 'exemplo@gmail.com', '$2a$12$jNzLAaz8fEzjwXLFm7Ajou2D.7zhiO7UqEDRI78MTCVM.yswLDBNC');

insert into user_access_level (user_id, access_level_id) values (1, 1), (2, 2);
