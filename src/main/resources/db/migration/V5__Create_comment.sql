create table comment
(
    id bigint auto_increment,
    parent_id bigint not null,
    type int not null comment '区分评论一级二级',
    commentator int not null comment '评论人id',
    gmt_create bigint not null,
    gmt_modified bigint not null,
    like_count bigint default 0 null,
    constraint comment_pk
        primary key (id)
);

