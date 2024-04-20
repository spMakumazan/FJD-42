create table users (
    username varchar(20) primary key,
    password varchar(255) not null
);

create table files (
    id bigserial primary key,
    filename varchar(40) not null,
    content bytea not null,
    owner_username varchar(20) not null,
    unique(filename),
    foreign key(owner_username) references  users(username)
);

create table tokens (
    authtoken varchar(255) primary key,
    user_username varchar(20) not null,
    foreign key(user_username) references  users(username)
);
