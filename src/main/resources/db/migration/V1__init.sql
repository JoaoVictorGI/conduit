create table if not exists usr
(
    id         uuid        not null primary key,
    email      text unique not null,
    username   text        not null,
    password   text        not null,
    bio        text,
    image      text,
    created_at timestamp with time zone,
    updated_at timestamp with time zone
);