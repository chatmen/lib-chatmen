-- drop table if exists tweet;
-- create table tweet
-- (
-- `id`              bigint(20)   unsigned AUTO_INCREMENT NOT NULL PRIMARY KEY,
-- `uid`             bigint(20)   unsigned  DEFAULT NULL,
-- `text`            varchar(255) CHARACTER SET utf8  DEFAULT NULL,
-- `favorite_number` INT(16)      not null DEFAULT 0,
-- `retweet_number`  INT(16)      not null DEFAULT 0,
-- `updated_at`      timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
-- `created_at`      timestamp    default CURRENT_TIMESTAMP not null
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tweet (uid, text, favorite_number, reTweet_number)
VALUES
('4', 'いい一日ですね', '1', '2'),
('1', '今日もやるぞ', '3', '4'),
('1', '施策はベンディング', '30', '20'),
('3', 'tweetですよ', '5', '6');
