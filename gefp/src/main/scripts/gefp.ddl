
    create table cell (
        id int4 not null,
        plan_id int4,
        runway_id int4,
        stage_id int4,
        primary key (id)
    );

    create table checkpoints (
        id int4 not null,
        description varchar(255),
        ischecked boolean not null,
        cell_id int4,
        primary key (id)
    );

    create table department (
        id int4 not null,
        name varchar(255),
        currentPlan_id int4,
        primary key (id)
    );

    create table flightplan (
        id int4 not null,
        name varchar(255),
        publisheddate timestamp,
        department_id int4,
        primary key (id)
    );

    create table roles (
        id int4 not null,
        rolename varchar(255),
        primary key (id)
    );

    create table runway (
        id int4 not null,
        description varchar(255),
        plan_id int4,
        primary key (id)
    );

    create table stage (
        id int4 not null,
        position int4,
        sname varchar(255),
        plan_id int4,
        primary key (id)
    );

    create table users (
        id int4 not null,
        cin varchar(255) not null,
        email varchar(255) not null,
        enabled boolean not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        major_id int4,
        plan_id int4,
        primary key (id)
    );

    create table users_checkpoints (
        user_id int4 not null,
        checkpoints_id int4 not null
    );

    create table users_roles (
        user_id int4 not null,
        roles_id int4 not null,
        primary key (user_id, roles_id)
    );

    alter table users 
        add constraint UK_ka6m8ghsr7vna1ti6lftwww8o unique (cin);

    alter table users 
        add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

    alter table users 
        add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

    alter table cell 
        add constraint FK_5709j5ibsgsvgsgmsywuolwsp 
        foreign key (plan_id) 
        references flightplan;

    alter table cell 
        add constraint FK_9y92mmkrji884v27sj99txci2 
        foreign key (runway_id) 
        references runway;

    alter table cell 
        add constraint FK_k59ltkw6rs6v4xgm4a0aqwc6d 
        foreign key (stage_id) 
        references stage;

    alter table checkpoints 
        add constraint FK_rrcdicx35xhsc6677jvgmrmp9 
        foreign key (cell_id) 
        references cell;

    alter table department 
        add constraint FK_nhxn01f9ds28j9r2ggytm2awo 
        foreign key (currentPlan_id) 
        references flightplan;

    alter table flightplan 
        add constraint FK_oucp4wdyd2f049jlswgjeyoka 
        foreign key (department_id) 
        references department;

    alter table runway 
        add constraint FK_2myowg0wb36kv47fkqfgyhaqf 
        foreign key (plan_id) 
        references flightplan;

    alter table stage 
        add constraint FK_k6pwv821ovp7d177rjmcvy2m5 
        foreign key (plan_id) 
        references flightplan;

    alter table users 
        add constraint FK_q37jte7r1ptl16arimkk23y1h 
        foreign key (major_id) 
        references department;

    alter table users 
        add constraint FK_km7rd8sgwa1qls24gkxoh2b2i 
        foreign key (plan_id) 
        references flightplan;

    alter table users_checkpoints 
        add constraint FK_o9v83tlotmys5ftkny0umger1 
        foreign key (checkpoints_id) 
        references checkpoints;

    alter table users_checkpoints 
        add constraint FK_osk518wbds95frvuiksp2h62d 
        foreign key (user_id) 
        references users;

    alter table users_roles 
        add constraint FK_60loxav507l5mreo05v0im1lq 
        foreign key (roles_id) 
        references roles;

    alter table users_roles 
        add constraint FK_1hjw31qvltj7v3wb5o31jsrd8 
        foreign key (user_id) 
        references users;

    create sequence hibernate_sequence;
