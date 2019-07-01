drop table if exists tweet;
create table tweet
(
'id'              bigint(20)   unsigned AUTO_INCREMENT NOT NULL PRIMARY KEY,
'uid'             bigint(20)   unsigned  DEFAULT NULL,
'text'            varchar(255) CHARACTER SET utf8  DEFAULT NULL,
'favoriteNumber'  INT(16)      DEFAULT '0',
'reTweetNumber'   INT(16)      DEFAULT '0',
'updated_at'      timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
'created_at'      timestamp    default CURRENT_TIMESTAMP not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tweet (uid, text, favoriteNumber, reTweetNumber)
VALUES
('1', 'テストだよ', '1', '2'),
('2', 'できるかな', '3', '4'),
('3', 'できたー', '5', '6');
