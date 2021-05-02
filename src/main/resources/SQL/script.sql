create database LeverXProject;

use LeverXProject;

create table user
(
    id            int auto_increment primary key,
    login         varchar(30),
    hash_password varchar(60),
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

insert into user (login, hash_password, email,
                  created_at, role, status)

values ('anonymous', '', '',
        '2021-01-01 00:00:00', 'ANONYMOUS', 'CREATED'),

       ('admin', '$2a$10$YNVOFgqMYiOEiv7EIdHwn.vuYVegNWc0bRWQj22NitXfv9Mi/BH9a', 'admin@mail.ru',
        '2021-01-01 00:00:00', 'ADMIN', 'CREATED'),

       ('user', '$2a$10$tlZ3BW8QADim3dNJpUfhTeZ0z5HxxpYdsTkfobbdZjT14KKcpPQaC', 'user@mail.ru',
        '2021-01-01 00:00:00', 'USER', 'CREATED'),

       ('user1', '$2a$10$pdy6WWViO0UlWJdaMrCmpeunEHInaPaRF7QRqksyzAp7aUpNJ39GC', 'user1@mail.ru',
        '2021-01-01 00:00:00', 'USER', 'CREATED'),

       ('user2', '$2a$10$lRHNrcE81lH30Q9/1emOze4qNUp3onUOaEE.Axm7kQpfWB4PeCjZi', 'user2@mail.ru',
        '2021-01-01 00:00:00', 'USER', 'CREATED'),

       ('trader', '$2a$10$7uIuueUbXyIQGDJbTh/cUefOhAMlNtcxluSXASsFuN.utA0Lnlpbm', 'trader@mail.ru',
        '2021-01-01 00:00:00', 'TRADER', 'CREATED'),

       ('trader1', '$2a$10$T8SfieE80hZwJoPdQfok8.V9mhHJVnNg.mMVw1zNz3IRQl8aJgAkW', 'trader1@mail.ru',
        '2021-01-01 00:00:00', 'TRADER', 'CREATED'),

       ('trader2', '$2a$10$Yjdv9QeCGEyi5Go.FH7ufuTgTx5JlzAlv/6EOPidnkx8hbFueP/rG', 'trader2@mail.ru',
        '2021-01-01 00:00:00', 'TRADER', 'CREATED');

drop table lot;
drop table user;