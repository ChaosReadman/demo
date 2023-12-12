INSERT INTO account (user_name, password, nick_name) VALUES('user1', '$2a$10$TQaN9zqUVjcS0v/5WImVeOd9EVoCaChMeC8G4zS7Br4aCP2L1A9Q.','Stella');
INSERT INTO account (user_name, password, nick_name) VALUES('user2', '$2a$10$ulDzGxLX9/n1xT880oxkfu0wnL7p06i.ar0fKmd4A.WhIrs/Olb/u','John');

INSERT INTO information (message) values(STRINGDECODE('奥です
今日もよろしくお願いします
一応、トップページにお知らせと掲示板の内容を表示する様にしてみました
')
);

INSERT INTO message_board (user_name, message) values ('Stella','こんにちは');
INSERT INTO message_board (user_name, message) values ('John','こん!');
INSERT INTO message_board (user_name, message) values ('Stella','こないだの試合なんだけど・・・');
INSERT INTO message_board (user_name, message) values ('John','ん？');
INSERT INTO message_board (user_name, message) values ('Stella','すみませんでした！');
INSERT INTO message_board (user_name, message) values ('John','気にすんな！');


