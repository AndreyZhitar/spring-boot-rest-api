drop table if exists users CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table users (
    id bigint not null,
    birthdate timestamp not null,
    name varchar(255),
    primary key (id)
);

select nextval('hibernate_sequence');



drop table if exists posts CASCADE;
drop table if exists users CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table posts (
    id bigint not null,
    description varchar(255),
    user_id bigint,
    primary key (id)
);
create table users (
    id bigint not null,
    birthdate timestamp not null,
    name varchar(255),
    primary key (id)
);
alter table posts add constraint users_user_id_fk foreign key (user_id) references users;