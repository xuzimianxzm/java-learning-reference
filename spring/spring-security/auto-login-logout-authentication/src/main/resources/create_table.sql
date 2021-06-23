create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);

CREATE TABLE users
(
    id       int(20)     NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password varchar(60) NOT NULL,
    enable   int         NOT NULL DEFAULT 1 COMMENT '用户是否可用',
    roles    text COMMENT '用户角色，多个角色之间用逗号隔开',
    PRIMARY KEY (id)
);

insert into users(username, password, roles)
values ('admin', '123', 'ROLE_ADMIN,ROLE_USER');
insert into users(username, password, roles)
values ('user', '123', 'ROLE_USER');