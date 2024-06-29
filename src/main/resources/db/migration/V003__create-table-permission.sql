create table permission (
    id bigint not null auto_increment, description varchar(255),
    name varchar(255) not null, primary key (id)
) engine=InnoDB;