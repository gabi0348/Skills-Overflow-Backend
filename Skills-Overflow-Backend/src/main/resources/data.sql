insert into role values(1, 'user');
insert into role values(2, 'admin');


insert into user values(1,'email@yahoo.com','firstname','lastname','$2a$04$PipDu4cjlZ01Inm68ENFEuHWu9haIXLKeN9N1i4uzivhU5QQOHPv6','username');
insert into user values(2,'email2@yahoo.com','firstnam2e','lastname2','parola2','username2');

insert into role_user values(1, 1);
insert into role_user values(1, 2);
insert into role_user values(2, 1);
insert into role_user values(2, 2);

--insert into user values(1, 'george.popescu.db@gmail.com',false,'deliric','lastname','parola','username');
--insert into user values(2, 'email2@yahoo.com',false,'3sudEst','lastname2','parola2','username2');
--
--insert into role_user values(1, 1);
--insert into role_user values(1, 2);
--insert into role_user values(2, 1);
--insert into role_user values(2, 2);


