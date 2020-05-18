insert into role values(1, 'admin');
insert into role values(2, 'approved user');
insert into role values(3, 'pending user');
insert into role values(4, 'blocked user');
insert into role values(5, 'declined user');

--//parola pentru userii de mai jos este "parola"- apare asa pentru ca trebuie adaugata direct criptata daca se face prin insert

insert into user values(1, 0, false,'george.popescu.db@gmail.com', true, 'deliric','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','Popica', 1);
insert into user values(2, 0, false,'george.pop@gmail.com', true, 'gigi','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 2);
insert into user values(3, 0, false,'george@gmail.com', true, 'coco','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 3);
insert into user values(4, 0, false,'cristos@gmail.com', true, 'data','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 4);
insert into user values(5, 0, false,'cristos@gmail.com', true, 'data','lastname','parola','user4', 5)






