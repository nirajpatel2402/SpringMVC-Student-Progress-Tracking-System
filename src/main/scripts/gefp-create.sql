create sequence hibernate_sequence minvalue 100;

create table roles ( 
    id          integer primary key,
    rolename    varchar(255)
);  

create table flightplan (
    id              integer primary key,
    name            varchar(255),
    publisheddate   timestamp
);

create table department (
    id              integer primary key,
    name            varchar(255),
    currentPlan_id  integer references flightplan(id)
);

create table users (
    id          integer primary key,
    cin                     varchar(255) not null unique,
    username                varchar(255) not null unique,
    password                varchar(255) not null,
    email					varchar(255) not null unique,
    first_name              varchar(255) not null,
    last_name               varchar(255) not null,
    major_id    integer references department(id),
    plan_id     integer references flightplan(id),
    enabled     boolean not null default 't'
    );

create table runway (
    id          integer primary key,
    rposition		SERIAL,
    description varchar(255),
    plan_id     integer references flightplan(id)
);

create table stage (
    id          integer primary key,
    position		SERIAL,
    sname       varchar(255),
    plan_id     integer references flightplan(id)
);

create table cell (
    id          integer primary key,
    plan_id     integer references flightplan(id),
    runway_id   integer references runway(id),
    stage_id    integer references stage(id)
);

create table checkpoints (
    id          integer primary key,
    cposition	serial,
    description text,
    cell_id     integer references cell(id),
    ischecked 	boolean not null
);

create table cell_checkpoints (        
    cell_id         integer references cell(id),
    checkpoints_id  integer references checkpoints(id)
);

create table users_checkpoints (
    user_id         integer references users(id),
    checkpoints_id  integer references checkpoints(id)
);

create table users_roles (
    user_id         integer references users(id),
    roles_id        integer references roles(id)
);

           
    alter table flightplan 
        add department_id integer references department(id);

    alter table flightplan 
        add constraint flightplan_department_id 
        foreign key (department_id) 
        references department;

    alter table cell 
        add constraint cell_plan_id 
        foreign key (plan_id) 
        references flightplan;

    alter table cell 
        add constraint cell_runway_id 
        foreign key (runway_id) 
        references runway;

    alter table cell 
        add constraint cell_stage_id 
        foreign key (stage_id) 
        references stage;

    alter table checkpoints
        add constraint cell_checkpoints_cell_id
        foreign key (cell_id)
        references cell;

    alter table cell_checkpoints 
        add constraint cell_checkpoints_checkpoints_id 
        foreign key (checkpoints_id) 
        references checkpoints;

    alter table cell_checkpoints 
        add constraint cell_checkpoints_cell_id 
        foreign key (cell_id) 
        references cell;

    alter table department 
        add constraint department_currentPlan_id 
        foreign key (currentPlan_id) 
        references flightplan;

    alter table runway 
        add constraint runway_plan_id 
        foreign key (plan_id) 
        references flightplan;

    alter table stage 
        add constraint stage_plan_id 
        foreign key (plan_id) 
        references flightplan;

    alter table users 
        add constraint users_major_id
        foreign key (major_id) 
        references department;

    alter table users 
        add constraint users_plan_id 
        foreign key (plan_id) 
        references flightplan;

    alter table users_checkpoints 
        add constraint users_checkpoints_checkpoints_id 
        foreign key (checkpoints_id) 
        references checkpoints;

    alter table users_checkpoints 
        add constraint users_checkpoints_user_id 
        foreign key (user_id) 
        references users;

    alter table users_roles 
        add constraint users_roles_roles_id 
        foreign key (roles_id) 
        references roles;

    alter table users_roles 
        add constraint users_roles_user_id 
        foreign key (user_id) 
        references users;
        
insert into flightplan (id, name, publisheddate) values(1, 'Flightplan CS2015', null);
insert into flightplan (id, name, publisheddate) values(2, 'Flightplan EE2015', null);
insert into flightplan (id, name, publisheddate) values(3, 'Flightplan CS2015 v2', null);


insert into roles(id, rolename) values (1, 'administrator');
insert into roles(id, rolename) values (2, 'advisor');
insert into roles(id, rolename) values (3, 'student');

insert into department (id, name, currentplan_id) values (1, 'Computer Science', 1);
insert into department (id, name, currentplan_id) values (2, 'Electrical Engineering', 2);

update flightplan set department_id = 1 where id = 1;
update flightplan set department_id = 2 where id = 2;
update flightplan set department_id = 1 where id = 3;


insert into users (id,cin ,username, password,email, first_name, last_name, major_id, plan_id, enabled) values (1, '1001', 'cysun', 'abcd','cysun@calstatela.edu', 'chengyu', 'sun', null, null, 't');
insert into users (id,cin ,username, password,email, first_name, last_name, major_id, plan_id, enabled) values (2, '1002', 'tfox', 'abcd','tfox@calstatela.edu', 'tiger', 'fox', null, null, 't');
insert into users (id,cin ,username, password,email, first_name, last_name, major_id, plan_id, enabled) values (3, '9001','jdoe1','abcd','jdoe1@calstatela.edu', 'john', 'doe', 1, 1, 't');
insert into users (id,cin ,username, password,email, first_name, last_name, major_id, plan_id, enabled) values (4, '9002', 'jdoe2','abcd','jdoe2@calstatela.edu','john','doe2', 2, 2, 't');

insert into runway(id , description, plan_id) values (1, 'Academics', 1);
insert into runway(id , description, plan_id) values (2, 'Career Preparation', 1);
insert into runway(id , description, plan_id) values (3, 'Leadership Community Engagement', 1);


insert into runway(id , description, plan_id) values (4, 'runway1', 2);
insert into runway(id , description, plan_id) values (5, 'runway2', 2);
insert into runway(id , description, plan_id) values (6, 'runway3', 2);

insert into runway(id , description, plan_id) values (7, 'runway1', 3);
insert into runway(id , description, plan_id) values (8, 'runway2', 3);
insert into runway(id , description, plan_id) values (9, 'runway3', 3);

insert into stage(id, sname, plan_id) values (1, 'PreCollege preflight checklist',1);
insert into stage(id, sname, plan_id) values (2, 'Freshman takeoff',1);

insert into stage(id, sname, plan_id) values (3, 'stage1',2);
insert into stage(id, sname, plan_id) values (4, 'stage2',2);

insert into stage(id, sname, plan_id) values (5, 'stage1',3); 
insert into stage(id, sname, plan_id) values (6, 'stage2',3);


insert into cell(id, plan_id, runway_id, stage_id) values (1, 1, 1, 1);
insert into cell(id, plan_id, runway_id, stage_id) values (2, 1, 2, 1);
insert into cell(id, plan_id, runway_id, stage_id) values (3, 1, 3, 1);
insert into cell(id, plan_id, runway_id, stage_id) values (4, 1, 1, 2);
insert into cell(id, plan_id, runway_id, stage_id) values (5, 1, 2, 2);
insert into cell(id, plan_id, runway_id, stage_id) values (6, 1, 3, 2);

insert into cell(id, plan_id, runway_id, stage_id) values (7, 2, 4, 3);
insert into cell(id, plan_id, runway_id, stage_id) values (8, 2, 5, 3);
insert into cell(id, plan_id, runway_id, stage_id) values (9, 2, 6, 3);
insert into cell(id, plan_id, runway_id, stage_id) values (10, 2, 4, 4);
insert into cell(id, plan_id, runway_id, stage_id) values (11, 2, 5, 4);
insert into cell(id, plan_id, runway_id, stage_id) values (12, 2, 6, 4);

insert into cell(id, plan_id, runway_id, stage_id) values (13, 3, 7, 5);
insert into cell(id, plan_id, runway_id, stage_id) values (14, 3, 8, 5);
insert into cell(id, plan_id, runway_id, stage_id) values (15, 3, 9, 5);
insert into cell(id, plan_id, runway_id, stage_id) values (16, 3, 7, 6);
insert into cell(id, plan_id, runway_id, stage_id) values (17, 3, 8, 6);
insert into cell(id, plan_id, runway_id, stage_id) values (18, 3, 9, 6);

insert into checkpoints(id , description, cell_id, ischecked) values (1, 'Algebra before Yr1',1, false);
insert into checkpoints(id , description, cell_id, ischecked) values (2, 'Pre calculus',1, false);
insert into checkpoints(id , description, cell_id, ischecked) values (3, 'Math',1, false);
insert into checkpoints(id , description, cell_id, ischecked) values (4, 'Apply for financial aid and scholarships',1, false);
insert into checkpoints(id , description, cell_id, ischecked) values (5, 'Make a list of questions to ask during orientation and ask them',3, false);
insert into checkpoints(id , description, cell_id, ischecked) values (6, 'Math 206 Calc I',4, false);

insert into checkpoints(id , description, cell_id, ischecked) values (7, 'Take a personal assessment',5, false);
insert into checkpoints(id , description, cell_id, ischecked) values (8, 'Find out about ECST student organizations at ECST week',6, false);

insert into checkpoints(id , description, cell_id, ischecked) values (9, 'checkpoint 1',7, false);
insert into checkpoints(id , description, cell_id, ischecked) values (10, 'checkpoint 2',7, false);
insert into checkpoints(id , description, cell_id, ischecked) values (11, 'checkpoint 3',8, false);
insert into checkpoints(id , description, cell_id, ischecked) values (12, 'checkpoint 4',9, false);
insert into checkpoints(id , description, cell_id, ischecked) values (13, 'checkpoint 5',10, false);
insert into checkpoints(id , description, cell_id, ischecked) values (14, 'checkpoint 6',11, false);
insert into checkpoints(id , description, cell_id, ischecked) values (15, 'checkpoint 7',12, false);

insert into checkpoints(id , description, cell_id, ischecked) values (16, 'checkpoint 1',13, false);
insert into checkpoints(id , description, cell_id, ischecked) values (17, 'checkpoint 2',13, false);
insert into checkpoints(id , description, cell_id, ischecked) values (18, 'checkpoint 3',14, false);
insert into checkpoints(id , description, cell_id, ischecked) values (19, 'checkpoint 4',15, false);
insert into checkpoints(id , description, cell_id, ischecked) values (20, 'checkpoint 5',16, false);
insert into checkpoints(id , description, cell_id, ischecked) values (21, 'checkpoint 6',17, false);
insert into checkpoints(id , description, cell_id, ischecked) values (22, 'checkpoint 7',18, false);


insert into users_roles(user_id, roles_id) values (1, 1);
insert into users_roles(user_id, roles_id) values (2, 2);
insert into users_roles(user_id, roles_id) values (3, 3);
insert into users_roles(user_id, roles_id) values (4, 3);

insert into users_checkpoints(user_id, checkpoints_id) values (3, 3);
insert into users_checkpoints(user_id, checkpoints_id) values (3, 4);
insert into users_checkpoints(user_id, checkpoints_id) values (3, 6);
