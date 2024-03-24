create table users (
    username varchar(20) primary key,
    password varchar(20) not null
);

create table files (
    id bigint primary key,
    filename varchar(40) not null,
    content bytea not null,
    owner varchar(20) not null,
    foreign key(owner) references  users(username)
);

create table tokens (
    authtoken varchar primary key,
    "user" varchar(20) not null,
    foreign key("user") references  users(username)
);
