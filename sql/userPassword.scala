create table chatmen_user_password
(
  `uid`             bigint(20)   unsigned  NOT NULL PRIMARY KEY,
  `hash`            varchar(255) CHARACTER SET utf8 NOT NULL,
  `updated_at`      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  `created_at`      timestamp default CURRENT_TIMESTAMP not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
