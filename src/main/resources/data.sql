insert into users(id, birthdate, name) values
 ( 1, sysdate(), 'Andrey' ),
 ( 2, sysdate(), 'Tolik' ),
 ( 3, sysdate(), 'Bolik' );

insert into posts(id, description, user_id)
values
       (nextval('hibernate_sequence'), 'some description1', 1),
       (nextval('hibernate_sequence'), 'some description2', 1),
       (nextval('hibernate_sequence'), 'some description3', 1),
       (nextval('hibernate_sequence'), 'some description4', 1),
       (nextval('hibernate_sequence'), 'some description1', 2),
       (nextval('hibernate_sequence'), 'some description2', 2),
       (nextval('hibernate_sequence'), 'some description3', 2),
       (nextval('hibernate_sequence'), 'some description1', 3);
