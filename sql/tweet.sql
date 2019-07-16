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
('4', 'hoge1', '1', '2'),
('4', 'hoge2', '3', '4'),
('3', 'tweetだよ', '5', '6');
