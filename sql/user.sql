create table `user`
(
`uid`             bigint unsigned auto_increment primary key,
`name`            varchar(80)                         not null,
`email`           varchar(80)                         not null,
`phoneNumber`     varchar(255)                        not null,
`updated_at`      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
`created_at`      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
