insert into role values(1, 'admin');
insert into role values(2, 'approved user');
insert into role values(3, 'pending user');
insert into role values(4, 'blocked user');
insert into role values(5, 'declined user');

--//parola pentru userii de mai jos este "parola"- apare asa pentru ca trebuie adaugata direct criptata daca se face prin insert


insert into user values(1, 0, false,'achim.marian@gmail.com', true, 'Achim','Marian','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','achim12', 1);
insert into user values(2, 0, false,'calin.avram@gmail.com', true, 'Calin','Avram','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','calin.a', 1);
insert into user values(3, 0, false,'bucur.daniel@gmail.com', true, 'Bucur','Daniel','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','bucurDan', 1);
insert into user values(4, 0, false,'chifor.maria@gmail.com', true, 'Chifor','Maria','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','mariaC', 1);
insert into user values(5, 0, false,'corvin.bernard@gmail.com', true, 'Corvin','Bernard','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','corvin.matei', 2);
insert into user values(6, 0, false,'brad.mirel@gmail.com', true, 'Brad','Mirel','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','brad.din.padure', 2);
insert into user values(7, 0, false,'barbu.george@gmail.com', true, 'Barbu','George','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','barbu.g', 2);
insert into user values(8, 0, false,'lazar.matei@gmail.com', true, 'Lazar','Matei','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','lazarM', 2);
insert into user values(9, 0, false,'nicoara.mircea@gmail.com', true, 'Nicoara','Mircea','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','mircea.the.great', 3);
insert into user values(10, 0, false,'miron.ivan@gmail.com', true, 'Miron','Ivan','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','miner.developer', 3);
insert into user values(11, 0, false,'sever.sabin@gmail.com', true, 'Sever','Sabin','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','cuza.voievod', 3);
insert into user values(12, 0, false,'petru.silviu@gmail.com', true, 'Petru','Sivliu','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','petru.silviu', 3);
insert into user values(13, 0, false,'toma.marcu@gmail.com', true, 'Toma','Marcu','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','toma.credinta', 4);
insert into user values(14, 0, false,'vladimir.alexandru@gmail.com', true, 'Vladimir','Alexandru','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','alex.est', 4);
insert into user values(15, 0, false,'vlaicu.voda@gmail.com', true, 'Vlaicu','Mircea','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','vlaicu.voda', 4);
insert into user values(16, 0, false,'lascar.horatiu@gmail.com', true, 'Lascar','Horatiu','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','lascar02', 4);
insert into user values(17, 0, false,'bandila.stefan@gmail.com', true, 'Toma','Marcu','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','vali.rock', 5);
insert into user values(18, 0, false,'stroe.alexandru@gmail.com', true, 'Vladimir','Alexandru','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','stroe.eseu', 5);
insert into user values(19, 0, false,'stefan.antonio@gmail.com', true, 'Vlaicu','Mircea','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','fane.moto', 5);
insert into user values(20, 0, false,'ilie.cristian@gmail.com', true, 'Lascar','Horatiu','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','ilieC', 5);
insert into user values(21, 0, false,'george.popescu@gmail.com', true, 'Achim','Marian','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','popesco', 1);
insert into user values(22, 0, false,'petru.maria90@gmail.com', true, 'Petru','Maria','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','maria.petru', 2);
/*
insert into user values(22, 0, false,'zoe.ionesco@gmail.com', true, 'Zoe','Ionesco','$2a$04$pMF92/IeIwj7qmUEHYxFb.RrzoIh5i/jPsPXl7QV8JxVhQqUtKfry','zoe.farfuridi', 2);
*/

--insert into user values(5, 0, false,'cristos@gmail.com', true, 'data','lastname','parola','user4', 5);


--insert into post (body, create_date, number_of_comments, title, topic, user_id, id) values (?, ?, ?, ?, ?, ?, ?)

insert into post values(1, 'I am writing a Rest service to save data into postgres. This program uses Spring boot, Hibernate and maven for dependencies. Those are just my beginnings with spring so I need some help where it causes the error. Here are my logs:
given json request
{ "articleId": "1" }
My Controller class:', '2020-01-01', true, 3, 'Sprin JPA Repository throws bean creation error?', 2);

insert into post values (2, 'Exapmele : I have payment object with 3 request formats
1. request 1 card payment: validate card number, exp date and amount
2. token payment: token , exp date
3. validate card : card number, exp date
==> How it is possible from single payment object', '2018-08-01', true, 2, 'How to do sring validations on particular fields for each request?', 1);
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
}```', '2020-01-01', true, 4, 'How to parse belowJSON in springboot for Java?', 2);
insert into post values (4, 'I know how to create multiple instances of a bean class. However, now I have a series of classes, say:' ||
                            'class AppleInModule {
  @Autowired RedisTemplate template; // <-- just any *external* dependencies
  @Autowired OrangeInModule orange; // <-- dependencies to *internal* classes of this package
  @Value("${the_module.apple.some_config_value}") String configValue; // <-- application.yml config values
}
class OrangeInModule {
  ... // similar
}', '2019-01-01', true, 2, 'Multiple instances of a *series* of beans', 2);
insert into post values (5, 'Is it possible to call a constructor from another (within the same class, not from a subclass)? If yes how? And what could be the best way to call another constructor (if there are several ways to do it)?', '2019-01-01', true, 2, 'How do I call one constructor from another in Java in Java in java', 2);
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
}', '2019-01-01', true, 1, 'How to avoid constructor code redudancy in Java?', 2);
/*id-ul postarii / continut / data postarii / aprobat / numar de comm/ titlu/ id-ul user-ului care a postat*/

insert into post values(7, 'like this have historical exchange rate data for multiple currencies and exchange rate for over 3 years, as shown in above table we have start date and enddate for each currency rate in a range of 1 month ,what i need is to basically split it into each day ,so basically need exchange rate daily ,for ex: for 1st record i need 30 rows which should say from_currency as EUR and To_currency as GBP and exchange rate as 33.5 and new date column should be increment date starting from 2018-03-31 to 2018-04-30 .'
, '2020-01-02'
, true
, 0
, 'Split a single row into multiple rows based on date range in sql server'
, 2);

insert into post values(8
,'I have added 2 colour variations:
    Blue
    Green
I added different "short description" for all the colour variations.
Short description currently appears as CRX-00025. Short description for variations was CRX-00026 and CRX-00027.
How can I have short description change when colour variations are selected. Information added to Description column changes and appears right under the qty selector.'

, '2017-03-09'
, true
, 0
, 'Change Short description along with variation'
, 2);

insert into post values(9
, 'So I bought a site template from a very known site, bought myself a domain and after changing all the things I put it on my hosting and changed the e-mail adress(the author said its the only thing you gotta change) so I did and after that this is the only problem im having. Like I have no idea whats going on. I have zero skill in php and I thought I could ask you guys for some help. I just spent like 6 hours trying to figure it out and nothing, im literally too stupid. Please help a man', '2018-01-02'
, true
, 0
, 'Contact form sends no data via e-mail'
, 3);
insert into post values(10
,'We are doing load time weaving of this aspect.
Now while running unit tests for getContract method, the aspect code also gets executed and it throws an NPE at return statement from logAndProceed.
All I want is to disable aspect weaving for testing getContract method only. Is there a good solution for it?'
, '2020-05-12'
, false
, 0
, 'Disable certain aspects during ant tests'
, 4);
insert into post values(11
,'I have a navigation menu that triggers popups as a section with different options. Here is the dilemma though, and I see this with lots of sites that have popups or drop down menus.
When you scroll inside the pop up, safari obviously does not recognize this as body scrolling, so there is no way for the browser toolbars on the top and bottom to move out of the way as normal. Is there any way to deal with this?'
, '2019-08-04'
, true
, 0
, 'How to fix popup scrolling issues with safari browser toolbar?'
, 5);
insert into post values(12
, 'Ive got Drivers and LicenseCodes in my database where many drivers can have many license codes.
Its also the case that many different geographical regions (States in the US, for example), have multiple drivers license codes so I need identify the region for each license code.
My boss has suggested I set up a joining table that simply lists foreign keys. The POCO model for this class would look something like this (I use virtual properties because I;m lazy):'
, '2018-11-10'
, true
, 0
, 'Joining table in Entity Framework?'
, 7);
insert into post values(13
, 'I am generating code using openapi-generator-maven-plugin. The code is getting generated succesfully. But the spring boot is not loading the generated code in the context. I am not sure why, because I have done following things.'
, '2020-05-02'
, true
, 1
, 'SpringBoot not loading controllers which are generated using OpenAPI 3.0'
, 4);
insert into post values(14
, 'I was doing the assignment of "advanced machine learning and signal processing" in Coursera. I get the encountered with this error "Py4JavaEror". This is the first assignment of this course. It was said to be done in IBM Watson studio but doing it in Ibm Watson studio to too complex and I did in google Colab. Here is my code:'
, '2020-01-21'
, true
, 0
, 'How can i get the solution for this error in my code?'
, 3);
insert into post values(15
, 'Is there any higher method that can catch and handle the exception occurs in the main method?
How can we deal with it?
Here is an example given by Introduction to Java Programming (in Chapter 12)'
, '2020-05-12'
, false
, 0
, 'How to handle the exceptions after throwing them in main method?'
, 5);
insert into post values(16
, 'i am using mvvm and i would like my button to appear and disappear when needed. I was using the CS but now with MVVM I am getting an exception System.ArgumentNullException: Value cannot be null. on the second line. I have set Width of the button as default 0 and then its supposed to animate back once its needed.'
, '2018-01-21'
, false
, 0
, 'Animation of button Width'
, 2);
insert into post values(17
, 'kind of stuck here with react-select. Trying to add up to 5-6 selected options to another group. Id be looking to have two groups (default and selected). Once someones select the option from the dropdown menu then his choice would appear also in "selected" group.
Ive been trying to sort it out with formatGroupLabel & react-select-nested. Unfortunately, Im just stuck here.'
, '2017-08-07'
, true
, 0
, 'React-select adding selected options to group'
, 4);
insert into post values(18
,'Im using google sign in API for my application and for every request made to the server, I validate that the user is logged in, by sending the token (which I store in session storage) in a post method where the server checks its valid. However, I also want to do this before a page renders, so a non-logged in user doesnt see the page.
Ive read that I should put a script in the tag to validate the user, but is this the best way to do it?
Im using Express.js and was wondering if there was a way to do it through this on specific pages, without the need to run a script contained in every HTML file.'
, '2016-01-09'
, true
, 0
, 'Auto redirect for non-logged in users - Node.js'
, 6);
insert into post values(19, 'like this have historical exchange rate data for multiple currencies and exchange rate for over 3 years, as shown in above table we have start date and enddate for each currency rate in a range of 1 month ,what i need is to basically split it into each day ,so basically need exchange rate daily ,for ex: for 1st record i need 30 rows which should say from_currency as EUR and To_currency as GBP and exchange rate as 33.5 and new date column should be increment date starting from 2018-03-31 to 2018-04-30 .'
, '2020-03-08'
, true
, 0
, 'Split a single row into multiple rows based on date range in sql server'
, 3);
/*insert into post values(20
,'Im trying to upload a set of images to Firebase. The upload itself works, the pictures get added. However, Id like to get an URL of the pictures.
I figured that I could store them in an Array, but whatever I do, only one picture gets stored in the URL.
I want to A, upload a set of images (max 3) or B, upload only one image. Therefore, I set the target files in a state, and use that state to check if there are one or more images. Then I loop through the upload for x amount of times, and I push the response into ...URL. No matter what I do, I end up with only one image URL in firebase.
Going to try to clarify a bit more: I want to upload up to three images to Firebase (this works, the images gets uploaded into the image-bucket.
What I want, is to get the download URL of the images (I want to use the image URL in another component. Essentially, I want to create an image gallery or an image carousel with the images.'
,'2018-07-12'
,true
, 0
, 'How to upload more than one picture to Firebase and download the URL'
, 3);*/


insert into topic values(1,'Java ');
insert into topic values(2,'Spring Boot ');
insert into topic values(3,'SQL ' );
insert into topic values(4,'Tomcat ');
insert into topic values(5,'JPA ');
insert into topic values(6,'Google Cloud ');
insert into topic values(7,'Hibernate ');
insert into topic values(8,'MongoDB ');
insert into topic values(9,'HTML ');
insert into topic values(10,'CSS ');
insert into topic values(11,'JavaScript ');
insert into topic values(12,'Bootstrap ');
insert into topic values(13,'React ');
insert into topic values(14,'Angular ');
insert into topic values(15,'JQuery ');
insert into topic values(16,'Other ');

--post id, apoi topic id
insert into post_topic values(1,1);
insert into post_topic values(1,2);
insert into post_topic values(1,5);

insert into post_topic values(2,3);
insert into post_topic values(2,10);
insert into post_topic values(2,14);

insert into post_topic values(3,8);
insert into post_topic values(3,1);
insert into post_topic values(3,12);

insert into post_topic values(4,10);
insert into post_topic values(4,15);
insert into post_topic values(4,9);

insert into post_topic values(5,7);
insert into post_topic values(5,12);
insert into post_topic values(5,6);

insert into post_topic values(6,8);
insert into post_topic values(6,14);
insert into post_topic values(6,2);

insert into post_topic values(7,2);
insert into post_topic values(7,3);
insert into post_topic values(7,4);

insert into post_topic values(8,1);
insert into post_topic values(8,4);
insert into post_topic values(8,8);

insert into post_topic values(9,8);
insert into post_topic values(9,10);
insert into post_topic values(9,4);

insert into post_topic values(10,4);
insert into post_topic values(10,5);
insert into post_topic values(10,12);

insert into post_topic values(11,4);
insert into post_topic values(11,8);
insert into post_topic values(11,12);

insert into post_topic values(12,2);
insert into post_topic values(12,7);
insert into post_topic values(12,12);

insert into post_topic values(13,5);
insert into post_topic values(13,7);
insert into post_topic values(13,2);

insert into post_topic values(14,8);
insert into post_topic values(14,10);
insert into post_topic values(14,4);

insert into post_topic values(15,5);
insert into post_topic values(15,12);
insert into post_topic values(15,16);

insert into post_topic values(16,4);
insert into post_topic values(16,5);
insert into post_topic values(16,10);

insert into post_topic values(17,7);
insert into post_topic values(17,2);
insert into post_topic values(17,9);

insert into post_topic values(18,7);
insert into post_topic values(18,5);
insert into post_topic values(18,14);

insert into post_topic values(19,14);
insert into post_topic values(19,11);
insert into post_topic values(19,4);

/*insert into post_topic values(20,15);
insert into post_topic values(20,1);
insert into post_topic values(20,2);*/


--create table comment (id bigint not null, approved_comment boolean, body varchar(255), create_date timestamp not null, vote_count bigint, post_id bigint, user_id bigint, primary key (id))
--toat sunt comentarii la postarea 1!!!
insert into comment values (1, 'It depends on attribute scope of bean tag. If scope="singleton" which is by default then each time you will get single instance and if scope="prototype" then you will get different instances. And it doesn''t depend on autowire', '2020-01-01', true, true,-2, 1, 3);
insert into comment values (2, 'By using @Autowired you instruct classloader to associate any class instance available in container to associate with the callee. You need to make sure that you have made an entry to instantiate the bean in your config file like <bean id="test" class="xxx.Test" />. Also you might want to check scopes too.
If you have multiple instances of same class define with different names then with @Autowired you need to provide specific name that you want to use with the help of qualifier.', '2019-01-01', true,  false, 10, 1, 4);
insert into comment values (3,  'Yes, Spring does some magic. Check the Spring Docs:
This is where the magic comes in: All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child method checks the container first for any cached (scoped) beans before it calls the parent method and creates a new instance.
This means that the calls to @Bean methods are proxied via CGLIB and therefore the cached version of the bean is returned (a new one is not created).','2020-01-01', true, false, 1, 13, 2);
insert into comment values (4,  'Using this(args). The preferred pattern is to work from the smallest constructor to the largest.', '2020-01-01', true, false, 0, 2, 1);

insert into comment values (5, 'It depends on attribute scope of bean tag. If scope="singleton" which is by default then each time you will get single instance and if scope="prototype" then you will get different instances. And it doesn''t depend on autowire', '2020-01-01', true, false,-2, 2, 3);
insert into comment values (6, 'By using @Autowired you instruct classloader to associate any class instance available in container to associate with the callee. You need to make sure that you have made an entry to instantiate the bean in your config file like <bean id="test" class="xxx.Test" />. Also you might want to check scopes too.
If you have multiple instances of same class define with different names then with @Autowired you need to provide specific name that you want to use with the help of qualifier.', '2020-01-01', false,  false, 10, 2, 4);
insert into comment values (7,  'Yes, Spring does some magic. Check the Spring Docs:
This is where the magic comes in: All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child method checks the container first for any cached (scoped) beans before it calls the parent method and creates a new instance.
This means that the calls to @Bean methods are proxied via CGLIB and therefore the cached version of the bean is returned (a new one is not created).','2020-01-01', true, false, 6969, 3, 2);
insert into comment values (8,  'Using this(args). The preferred pattern is to work from the smallest constructor to the largest.', '2020-01-01', true, false, 0, 3, 1);

insert into comment values (9, 'It depends on attribute scope of bean tag. If scope="singleton" which is by default then each time you will get single instance and if scope="prototype" then you will get different instances. And it doesn''t depend on autowire', '2020-01-01', true, false,-2, 3, 3);
insert into comment values (10, 'By using @Autowired you instruct classloader to associate any class instance available in container to associate with the callee. You need to make sure that you have made an entry to instantiate the bean in your config file like <bean id="test" class="xxx.Test" />. Also you might want to check scopes too.
If you have multiple instances of same class define with different names then with @Autowired you need to provide specific name that you want to use with the help of qualifier.', '2020-01-01', true,  false, 10, 3, 4);
insert into comment values (11,  'Yes, Spring does some magic. Check the Spring Docs:
This is where the magic comes in: All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child method checks the container first for any cached (scoped) beans before it calls the parent method and creates a new instance.
This means that the calls to @Bean methods are proxied via CGLIB and therefore the cached version of the bean is returned (a new one is not created).','2020-01-01', true, false, 6969, 4, 2);
insert into comment values (12,  'Using this(args). The preferred pattern is to work from the smallest constructor to the largest.', '2020-01-01', true, false, 0, 4, 1);

insert into comment values (13, 'It depends on attribute scope of bean tag. If scope="singleton" which is by default then each time you will get single instance and if scope="prototype" then you will get different instances. And it doesn''t depend on autowire', '2020-01-01', true, false,-2, 1, 3);
insert into comment values (14, 'By using @Autowired you instruct classloader to associate any class instance available in container to associate with the callee. You need to make sure that you have made an entry to instantiate the bean in your config file like <bean id="test" class="xxx.Test" />. Also you might want to check scopes too.
If you have multiple instances of same class define with different names then with @Autowired you need to provide specific name that you want to use with the help of qualifier.', '2020-01-01', true,  false, 10, 5, 4);
insert into comment values (15,  'Yes, Spring does some magic. Check the Spring Docs:
This is where the magic comes in: All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child method checks the container first for any cached (scoped) beans before it calls the parent method and creates a new instance.
This means that the calls to @Bean methods are proxied via CGLIB and therefore the cached version of the bean is returned (a new one is not created).','2020-01-01', true, false, 6969, 5, 2);
insert into comment values (16,  'Using this(args). The preferred pattern is to work from the smallest constructor to the largest.', '2020-01-01', true, false, 0, 6, 1);

--//inserare notificari
--Hibernate: create table notification (notification_id bigint not null, date timestamp, notification_type integer not null,
-- sender_name varchar(255), topics varchar(255), post_id bigint not null, primary key (notification_id))


insert into notification values(1,'2017-01-01',1,'George','SQL',2);
insert into notification values(2,'2018-01-01',2,'Andrei','Java',2);
insert into notification values(3,'2017-03-05',1,'Costel','Java, Spring Boot',1);
insert into notification values(4,'2020-06-02',2,'Iulian','Java',1);
insert into notification values(5,'2019-06-02',1,'Costel','SQL',1);
insert into notification values(6,'2019-06-02',1,'Iulian',null,3);
insert into notification values(7,'2016-06-02',2,'Andrei','Oracle',3);
insert into notification values(8,'2016-06-02',2,'Andrei','Oracle',3);
insert into notification values(9,'2017-01-01',2,'George','SQL',4);
insert into notification values(10,'2018-01-01',1,'Andrei','Java',2);
insert into notification values(11,'2017-03-05',2,'Costel','Java, Spring Boot',1);
insert into notification values(12,'2020-06-02',1,'Iulian','Java',1);
insert into notification values(13,'2019-06-02',2,'Costel','SQL',1);
insert into notification values(14,'2019-06-02',1,'Iulian',null,3);
insert into notification values(15,'2016-06-02',2,'Andrei','Oracle',3);
insert into notification values(16,'2017-01-01',2,'George','SQL',4);
insert into notification values(17,'2018-01-01',1,'Andrei','Java',2);
insert into notification values(18,'2017-03-05',2,'Costel','Java, Spring Boot',1);
insert into notification values(19,'2020-06-02',1,'Iulian','Java',1);
insert into notification values(20,'2019-06-02',2,'Costel','SQL',1);
insert into notification values(21,'2019-06-02',1,'Iulian',null,3);
insert into notification values(22,'2016-06-02',2,'Andrei','Oracle',3);
insert into notification values(23,'2017-01-01',2,'George','SQL',2);
insert into notification values(24,'2018-01-01',1,'Andrei','Java',2);
insert into notification values(25,'2017-03-05',2,'Costel','Java, Spring Boot',1);
insert into notification values(26,'2020-06-02',1,'Iulian','Java',1);
insert into notification values(27,'2019-06-02',1,'Costel','SQL',1);
insert into notification values(28,'2019-06-02',2,'Iulian',null,3);
insert into notification values(29,'2016-06-02',1,'Andrei','Oracle',3);
insert into notification values(30,'2017-01-01',2,'George','SQL',4);
insert into notification values(31,'2018-01-01',1,'Andrei','Java',2);
insert into notification values(32,'2017-03-05',2,'Costel','Java, Spring Boot',1);
insert into notification values(33,'2020-06-02',1,'Iulian','Java',1);
insert into notification values(34,'2019-06-02',2,'Costel','SQL',1);
insert into notification values(35,'2019-06-02',1,'Iulian',null,3);
insert into notification values(36,'2016-06-02',2,'Andrei','Oracle',3);
insert into notification values(37,'2017-01-01',2,'George','SQL',4);
insert into notification values(38,'2018-01-01',1,'Andrei','Java',2);
insert into notification values(39,'2017-03-05',1,'Costel','Java, Spring Boot',1);
insert into notification values(40,'2020-06-02',2,'Iulian','Java',1);
insert into notification values(41,'2019-06-02',2,'Costel','SQL',1);
insert into notification values(42,'2019-06-02',1,'Iulian',null,3);
insert into notification values(43,'2016-06-02',2,'Andrei','Oracle',3);

--// id-ul notificarii | id-ul user-ului
insert  into notification_user values ( 1,1);
insert  into notification_user values ( 2,1 );
insert  into notification_user values ( 3,1 );
insert  into notification_user values ( 4,2 );
insert  into notification_user values ( 5,2 );
insert  into notification_user values ( 6,3 );
insert  into notification_user values ( 7,3 );
insert  into notification_user values ( 8,4 );
insert  into notification_user values ( 9,4 );
insert  into notification_user values ( 10,5 );
insert  into notification_user values ( 11,5 );
insert  into notification_user values ( 12,6 );
insert  into notification_user values ( 13,6 );
insert  into notification_user values ( 14,7 );
insert  into notification_user values ( 15,7 );
insert  into notification_user values ( 16,8 );
insert  into notification_user values ( 17,8 );
insert  into notification_user values ( 18,9 );
insert  into notification_user values ( 19,9 );
insert  into notification_user values ( 20,10 );
insert  into notification_user values ( 21,10 );
insert  into notification_user values ( 22,11 );
insert  into notification_user values ( 23,11 );
insert  into notification_user values ( 24,12 );
insert  into notification_user values ( 25,13 );
insert  into notification_user values ( 26,14 );
insert  into notification_user values ( 27,14 );
insert  into notification_user values ( 28,15 );
insert  into notification_user values ( 29,15 );
insert  into notification_user values ( 30,16 );
insert  into notification_user values ( 31,16 );
insert  into notification_user values ( 32,17 );
insert  into notification_user values ( 33,17 );
insert  into notification_user values ( 34,18 );
insert  into notification_user values ( 35,18 );
insert  into notification_user values ( 36,19 );
insert  into notification_user values ( 37,19 );
insert  into notification_user values ( 38,20 );
insert  into notification_user values ( 39,20 );
insert  into notification_user values ( 40,15 );
insert  into notification_user values ( 41,16 );
insert  into notification_user values ( 42,17 );
insert  into notification_user values ( 43,18 );


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





