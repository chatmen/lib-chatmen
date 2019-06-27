drop table if exists user;
create table user
(
`uid`             bigint(20)   unsigned AUTO_INCREMENT NOT NULL PRIMARY KEY,
`name`            varchar(255) CHARACTER SET utf8 DEFAULT NULL,
`email`           varchar(255) CHARACTER SET ascii DEFAULT NULL,
`phoneNumber`     varchar(32)  CHARACTER SET ascii DEFAULT NULL,
`updated_at`      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
`created_at`      timestamp default CURRENT_TIMESTAMP not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (name, email, phoneNumber)
VALUES
('fuma','hoge1@gmail.com', '08012345678'),
('hoge','hoge2@gmail.com', '08022223333'),
('hage','hoge3@gmail.com', '08033334444');
