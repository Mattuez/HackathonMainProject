create table _user (
    id bigint not null auto_increment,
    cpf varchar(255) not null,
    email varchar(255) not null,
    full_name varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
) engine=InnoDB;

alter table _user add constraint user_cpf_unique unique (cpf);
alter table _user add constraint user_email_unique unique (email);