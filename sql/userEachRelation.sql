drop table if exists usereachrelation;
create table usereachrelation
(
`id`              bigint(20)   unsigned AUTO_INCREMENT NOT NULL PRIMARY KEY,
`fromid`          bigint(20)   unsigned  NOT NULL,
`targetid`         bigint(20)   unsigned NOT NULL,
`updated_at`      timestamp    default CURRENT_TIMESTAMP NOT NULL on update CURRENT_TIMESTAMP,
`created_at`      timestamp    default CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usereachrelation (fromid, targetid)
VALUES
(1, 2),
(2, 1),
(1,3);
