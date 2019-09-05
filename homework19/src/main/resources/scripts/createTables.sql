create table GOOD (id integer not null, price double not null, title varchar(255) not null, primary key (id));
create table Order_Good (order_id integer not null, good_id integer not null);
create table SHOP_ORDER (id integer not null, total_price double not null, user_id integer not null, primary key (id));
create table USER (id integer not null, login varchar(255) not null, password varchar(255) not null, primary key (id));
alter table GOOD drop constraint if exists UK_e2n0jd553jkjfm7gx4cfxjmfy;
alter table GOOD add constraint UK_e2n0jd553jkjfm7gx4cfxjmfy unique (title);
alter table USER drop constraint if exists UK_slockai06wyhy7i5c8vnd2o31;
alter table USER add constraint UK_slockai06wyhy7i5c8vnd2o31 unique (login);
create sequence hibernate_sequence start with 1 increment by 1;
alter table Order_Good add constraint FKnrd8tgg7qfupi0dn28ef7d38t foreign key (good_id) references GOOD;
alter table Order_Good add constraint FKtepb8xswf8n2q7cst8bxjd6eq foreign key (order_id) references SHOP_ORDER;
alter table SHOP_ORDER add constraint FKdu5mo3a2m9117qitvji4e3ey6 foreign key (user_id) references USER;