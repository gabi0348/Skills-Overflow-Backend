insert into role values(1, 'admin');
insert into role values(2, 'approved user');
insert into role values(3, 'pending user');
insert into role values(4, 'blocked user');
insert into role values(5, 'declined user');

--//parola pentru userii de mai jos este "parola"- apare asa pentru ca trebuie adaugata direct criptata daca se face prin insert

insert into user values(1, 0, false,'george.popescu@gmail.com', true, 'deliric','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','Popica', 1);
insert into user values(2, 0, false,'george.pop@gmail.com', true, 'gigi','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 2);
insert into user values(3, 0, false,'george@gmail.com', true, 'coco','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 2);
insert into user values(4, 0, false,'cristos@gmail.com', true, 'data','lastname','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','username', 2);
--insert into user values(5, 0, false,'cristos@gmail.com', true, 'data','lastname','parola','user4', 5);


--insert into post (body, create_date, number_of_comments, title, topic, user_id, id) values (?, ?, ?, ?, ?, ?, ?)

insert into post values(1, 'I am writing a Rest service to save data into postgres. This program uses Spring boot, Hibernate and maven for dependencies. Those are just my beginnings with spring so I need some help where it causes the error. Here are my logs:

given json request

{ "articleId": "1" }

My Controller class:', '2020-01-01', true, 4, 'Sprin JPA Repository throws bean creation error?', 1);
insert into post values (2, 'Exapmele : I have payment object with 3 request formats

1. request 1 card payment: validate card number, exp date and amount
2. token payment: token , exp date
3. validate card : card number, exp date
==> How it is possible from single payment object', '1970-01-01', false, 341141, 'How to do sring validations on particular fields for each request?', 1);
insert into post values (3, 'How to parse the response JSON and get summary and action each time and form a separate object with that.' ||
                            '{
  "issues": [
       {
         "fields": {
             "summary": "This is summary",
             "action": "Start"
                   }
       },
      {
         "fields": {
             "summary": "Second summary",
             "action": "Stop"
                   }
       }

}```', '2001-01-01', 0, false, 'How to parse belowJSON in springboot for Java?', 2);
insert into post values (4, 'I know how to create multiple instances of a bean class. However, now I have a series of classes, say:' ||
                            'class AppleInModule {
  @Autowired RedisTemplate template; // <-- just any *external* dependencies
  @Autowired OrangeInModule orange; // <-- dependencies to *internal* classes of this package
  @Value("${the_module.apple.some_config_value}") String configValue; // <-- application.yml config values
}

class OrangeInModule {
  ... // similar
}', '2999-01-01', true, 0, 'Multiple instances of a *series* of beans', 2);
insert into post values (5, 'Is it possible to call a constructor from another (within the same class, not from a subclass)? If yes how? And what could be the best way to call another constructor (if there are several ways to do it)?', '2999-01-01', true, 0, 'How do I call one constructor from another in Java in Java in java', 2);
insert into post values (6, 'I have the following class:

class Pair
{
    String car;
    Integer cdr;

    public Pair () {}
    public Pair (String car) { this.car = car; }
    public Pair (Integer cdr) { this.cdr = cdr; }

    public Pair (String car, Integer cdr)
    {
        this(car);
        this(cdr);
    }
}', '2999-01-01', true, 0, 'How to avoid constructor code redudancy in Java?', 2);
insert into post values(8, 'doc', '2001-01-02', true, 1, 'java java java', 2);
insert into post values(9, ' java java java ', '2121-01-01', true, -24, 'docx', 2);
insert into post values(10,'doc', '1111-01-01', true, 23423, 'docx', 2);
insert into post values(11,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(12,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(13,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(14,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(15,'doc', '1000-01-01', true, 0, 'docx', 2);
insert into post values(16,'doc', '2999-01-01', true, 42, 'docx', 2);
insert into post values(17,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(18,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(19,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(20,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(21,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(22,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(23,'doc', '2999-01-01', true, 0, 'docx', 2);
insert into post values(24,'doc', '2999-01-01', true, 0, 'docx', 1); --postarea adminului popescu


insert into topic values(1,'Java');
insert into topic values(2,'Spring Boot');
insert into topic values(3,'SQL');
insert into topic values(4,'Tomcat');
insert into topic values(5,'JPA');
insert into topic values(6,'Google Cloud');
insert into topic values(7,'Hibernate');
insert into topic values(8,'MongoDB');
insert into topic values(9,'HTML');
insert into topic values(10,'CSS');
insert into topic values(11,'JavaScript');
insert into topic values(12,'Bootstrap');
insert into topic values(13,'React');
insert into topic values(14,'Angular');
insert into topic values(15,'JQuery');
insert into topic values(16,'Other');

--post id, apoi topic id
insert into post_topic values(1,1);
insert into post_topic values(1,2);
insert into post_topic values(4,1);
insert into post_topic values(5,1);
insert into post_topic values(15,1);
insert into post_topic values(15,2);
insert into post_topic values(15,3);
insert into post_topic values(16,1);


--create table comment (id bigint not null, approved_comment boolean, body varchar(255), create_date timestamp not null, vote_count bigint, post_id bigint, user_id bigint, primary key (id))
--toat sunt comentarii la postarea 1!!!
insert into comment values (1, 'It depends on attribute scope of bean tag. If scope="singleton" which is by default then each time you will get single instance and if scope="prototype" then you will get different instances. And it doesn''t depend on autowire', '1974-01-01', true, false,-2, 1, 3);
insert into comment values (2, 'By using @Autowired you instruct classloader to associate any class instance available in container to associate with the callee. You need to make sure that you have made an entry to instantiate the bean in your config file like <bean id="test" class="xxx.Test" />. Also you might want to check scopes too.

If you have multiple instances of same class define with different names then with @Autowired you need to provide specific name that you want to use with the help of qualifier.', '1975-01-01', false,  true, 10, 1, 4);
insert into comment values (3,  'Yes, Spring does some magic. Check the Spring Docs:

This is where the magic comes in: All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child method checks the container first for any cached (scoped) beans before it calls the parent method and creates a new instance.

This means that the calls to @Bean methods are proxied via CGLIB and therefore the cached version of the bean is returned (a new one is not created).','1976-01-01', false, false, 6969, 1, 2);
insert into comment values (4,  'Using this(args). The preferred pattern is to work from the smallest constructor to the largest.', '1977-01-01', true, false, 0, 1, 1);

--//inserare notificari
--Hibernate: create table notification (notification_id bigint not null, date timestamp, notification_type integer not null,
-- sender_name varchar(255), topics varchar(255), post_id bigint not null, primary key (notification_id))


insert into notification values(1,'1977-01-01',2,'George','SQL',4);
insert into notification values(2,'2020-01-01',1,'Andrei','Java',2);
insert into notification values(3,'2020-03-05',3,'Costel','Java, Spring Boot',1);
insert into notification values(4,'2020-06-02',3,'Iulian','Java',3);
insert into notification values(5,'2020-06-02',3,'Costel','SQL',3);
insert into notification values(6,'2020-06-02',1,'Iulian',null,3);
insert into notification values(7,'2020-06-02',2,'Andrei','Oracle',3);


insert  into notification_user values ( 2,3 );
insert  into notification_user values ( 4,2 );
insert  into notification_user values ( 3,4 );
insert  into notification_user values ( 4,3 );
insert  into notification_user values ( 2,2 );
insert  into notification_user values ( 4,1 );
insert  into notification_user values ( 4,4 );
insert  into notification_user values ( 5,1 );
insert  into notification_user values ( 5,3 );

----Hibernate: create table topic (id bigint generated by default as identity, topic varchar(255), vote_count bigint, primary key (id))
--insert into topic values(1, 'Java', 0);
--insert into topic values(2, 'Springboot', 0);
--insert into topic values(3, 'React', 0);
--insert into topic values(4, 'Angular', 0);
--insert into topic values(5, 'Tomcat', 0);
--insert into topic values(6, 'Hibernate', 0);
--
----user si topicurile pe care le are
--insert into topic_user values (1, 1);
--insert into topic_user values (1, 2);
--insert into topic_user values (1, 3);
--insert into topic_user values (1, 4);
--insert into topic_user values (2, 1);
--
----post si topic
--insert into post_topic values(1, 1);
--insert into post_topic values(1, 2);
--insert into post_topic values(1, 3);
--insert into post_topic values(2, 1);






