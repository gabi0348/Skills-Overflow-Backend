insert into role values(1, 'admin');
insert into role values(2, 'approved user');
insert into role values(3, 'pending user');
insert into role values(4, 'blocked user');

//parola pentru userii de mai jos este "parola"- apare asa pentru ca trebuie adaugata direct criptata daca se face prin insert

insert into user values(1, false,'george.popescu.db@gmail.com', true, 'deliric','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','Popica', 1);
insert into user values(2, false,'george.pop@gmail.com', true, 'gigi','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 2);
insert into user values(3, false,'george@gmail.com', true, 'coco','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 3);
insert into user values(4, false,'cristos@gmail.com', true, 'data','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 4);



//inserare postari

insert into post values ( 1,'dasdasda asdahakfhak afkdfhksd dssdsdsds',null,4,'oare e bine?','java', 2);
insert into post values ( 2,'dasdasda asdahakfhak afkdfhksd dssdsdsds',null,4,'oare e bine?','java',2);

//inserare notificari

insert into notification values(1,null,true,'notification type',2,2);
insert into notification values(2,null,true,'notification type',2,3);
insert into notification values(3,null,true,'notification type',2,2);
insert into notification values(4,null,true,'notification type',2,2);