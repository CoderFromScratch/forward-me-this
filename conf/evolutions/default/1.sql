# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table email_address (
  id_email_address              bigint auto_increment not null,
  email_address                 varchar(255),
  email_confirmed               tinyint(1) default 0 not null,
  constraint pk_email_address primary key (id_email_address)
);

create table pending_email (
  id_pending_email              bigint auto_increment not null,
  request_json                  longtext not null,
  email_address_id_email_address bigint,
  constraint pk_pending_email primary key (id_pending_email)
);

create index ix_pending_email_email_address_id_email_address on pending_email (email_address_id_email_address);
alter table pending_email add constraint fk_pending_email_email_address_id_email_address foreign key (email_address_id_email_address) references email_address (id_email_address) on delete restrict on update restrict;


# --- !Downs

alter table pending_email drop foreign key fk_pending_email_email_address_id_email_address;
drop index ix_pending_email_email_address_id_email_address on pending_email;

drop table if exists email_address;

drop table if exists pending_email;

