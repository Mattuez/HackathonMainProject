create table _transaction (
    id bigint not null auto_increment,
    user_id bigint not null,
    value bigint not null,
    primary key (id)
) engine=InnoDB;

alter table _transaction add constraint fk_transaction_user foreign key (user_id) references _user (id);