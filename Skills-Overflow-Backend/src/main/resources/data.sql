insert into role values(1, 'admin');
insert into role values(2, 'approved user');
insert into role values(3, 'pending user');
insert into role values(4, 'blocked user');

//parola pentru userii de mai jos este "parola"- apare asa pentru ca trebuie adaugata direct criptata daca se face prin insert

insert into user values(1, false,'george.popescu.db@gmail.com', true, 'deliric','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','Popica', 1);
insert into user values(2, false,'george.pop@gmail.com', true, 'gigi','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 2);
insert into user values(3, false,'george@gmail.com', true, 'coco','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 3);
insert into user values(4, false,'cristos@gmail.com', true, 'data','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 4);


--insert into post (body, create_date, number_of_comments, title, topic, user_id, id) values (?, ?, ?, ?, ?, ?, ?)
insert into post values(1, 'ascult abi talent', '2020-01-01', 4, 'muzica', 'spring', 1);
insert into post values (2, 'ascult stravinsky', '1970-01-01', 0, 'muzicologie', 'spring', 1);
insert into post values (3, 'mananc chipsuri', '2001-01-01', 0,'mancarica', 'spring', 2);
insert into post values (4, 'mananc caviar', '2999-01-01', 0, 'caviar', 'java', 2);

--create table comment (id bigint not null, approved_comment boolean, body varchar(255), create_date timestamp not null, vote_count bigint, post_id bigint, user_id bigint, primary key (id))
insert into comment values (1, false, 'de ce abi', '1974-01-01', -2, 1, 3);
insert into comment values (2, false, 'abi e baza', '1975-01-01', 10, 1, 4);
insert into comment values (3, true, 'abi e rason d etre fratelo', '1976-01-01', 6969, 1, 2);
insert into comment values (4, false, 'asa e', '1977-01-01', 0, 1, 1);




