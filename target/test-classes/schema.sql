-- create table currency(id  number identity ,name varchar ,code varchar ,title varchar ,coin_info number);
-- create table coin_info(id  number identity ,title varchar ,description varchar ,canonical varchar ,lang varchar, coin_coin_id number ,link_link_id number );
-- create table faq( id number identity ,question varchar ,answer varchar ,COIN_INFO_COIN_ID number );
-- create table link(id number identity ,path varchar , redirect_user varchar ,redirect_code varchar ,deleted boolean,coin_info number );
-- create table meta(id number identity ,meta_key varchar ,type varchar ,value varchar ,coin_info_coin_id number );
-- create table tag(id number  identity ,title varchar ,coin_info varchar );
create schema CE;
create schema AUD;
create table CE.currency
(
    id        number identity,
    name      varchar,
    code      varchar,
    title     varchar,
    coin_info number,
    version   number
);
create table CE.coin_info
(
    id          number identity,
    title       varchar,
    description varchar,
    lang        varchar,
    link_id     number,
    version     number
);

create table aud.revision_info
(
    id        number identity,
    date      date,
    ip        varchar,
    user_id   number,
    user_name varchar
)