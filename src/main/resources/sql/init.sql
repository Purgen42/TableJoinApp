drop table if exists STICKS;
drop table if exists FIR_TREES;

create table FIR_TREES
(
    ID   INT not null,
    NAME VARCHAR
);

create unique index FIR_TREES_ID_UINDEX
    on FIR_TREES (ID);

alter table FIR_TREES
    add constraint FIR_TREES_PK
        primary key (ID);

create table STICKS
(
    ID          INT not null,
    NAME        VARCHAR,
    FIR_TREE_ID INT not null,
    constraint STICKS_PK
        primary key (ID),
    constraint STICKS_FIR_TREES_ID_FK
        foreign key (FIR_TREE_ID) references FIR_TREES (ID)
);

create unique index STICKS_ID_UINDEX
    on STICKS (ID);

insert into FIR_TREES (ID, NAME)
values (1, 'Ёлка1'),
       (2, 'Ёлка2');


insert into STICKS (ID, NAME, FIR_TREE_ID)
values (1, 'Палка1', 1),
       (2, 'Палка2', 1),
       (3, 'Палка3', 1),
       (4, 'Палка4', 2),
       (5, 'Палка5', 2);
