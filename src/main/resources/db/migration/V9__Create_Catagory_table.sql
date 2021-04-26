create table category
(
    id bigint auto_increment,
    category_name varchar(25) not null,
    gmt_create bigint not null,
    is_deleted int default 0 not null,
    constraint category_pk
        primary key (id)
);
