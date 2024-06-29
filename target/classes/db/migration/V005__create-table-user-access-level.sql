create table user_access_level (
    access_level_id bigint not null,
    user_id bigint not null,
    primary key (access_level_id, user_id)
) engine=InnoDB;

alter table user_access_level add constraint fk_user_access_level_access_level foreign key (access_level_id) references access_level (id);
alter table user_access_level add constraint fk_user_access_level_user foreign key (user_id) references _user (id);