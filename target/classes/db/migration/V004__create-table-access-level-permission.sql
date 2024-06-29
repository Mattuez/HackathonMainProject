create table access_level_permission (
    access_level_id bigint not null,
    permission_id bigint not null,
    primary key (access_level_id, permission_id)
) engine=InnoDB;

alter table access_level_permission add constraint fk_access_level_permission_permission foreign key (permission_id) references permission (id);
alter table access_level_permission add constraint fk_access_level_permission_access_level foreign key (access_level_id) references access_level (id);