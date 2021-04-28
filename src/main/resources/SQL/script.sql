-- script for create tables [user, comment, game, lot]
create database LeverXProject;

use LeverXProject;

create table user
(
    id            int auto_increment primary key,
    login         varchar(30),
    hash_password varchar(60),
    first_name    varchar(30),
    last_name     varchar(30),
    email         varchar(30),
    created_at    datetime,
    role          varchar(15),
    status        varchar(15)
);

create table comment
(
    id         int auto_increment primary key,
    message    varchar(256),
    trader_id  int,
    author_id  int,
    created_at datetime,
    updated_at datetime,
    status     varchar(15)
);

create table game
(
    id   int auto_increment primary key,
    name varchar(30)
);

create table lot
(
    id        int auto_increment primary key,
    trader_id int,
    game_id   int,
    cost      double
);

insert into user (login, hash_password, first_name, last_name, email, created_at, role, status)
    value ('admin',
           '$2a$10$YNVOFgqMYiOEiv7EIdHwn.vuYVegNWc0bRWQj22NitXfv9Mi/BH9a',
           'Mikhail',
           'Pashkevich',
           'pashkevich.m.a@mail.ru',
           '2021-04-08 14:27:00',
           'ADMIN',
           'CREATED');

