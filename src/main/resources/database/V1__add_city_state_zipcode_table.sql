create table if not exists zipcode(
    id serial primary key,
    area_name varchar(255) null,
    zipcode varchar(255) not null,
    active boolean not null,
);

create table if not exists state(
    id serial primary key,
    name varchar(255) not null,
    code varchar(255) not null,
    active boolean not null,
);

create table if not exists city(
    id serial primary key,
    name varchar(255) not null,
    code varchar(255) not null,
    active boolean not null,

    CONSTRAINT fk_city_state
        FOREIGN KEY (state_id)
        REFERENCES state (id)
        ON DELETE CASCADE,
);