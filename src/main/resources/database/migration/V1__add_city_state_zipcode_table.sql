create table if not exists zipcode(
    id bigserial primary key,
    created_at timestamptz not null,
    updated_at timestamptz null,
    area_name varchar(255) null,
    zipcode varchar(255) not null,
    active boolean not null
);

create table if not exists state(
    id bigserial primary key,
    created_at timestamptz not null,
    updated_at timestamptz null,
    name varchar(255) not null,
    code varchar(255) not null unique,
    active boolean not null
);

create table if not exists city(
    id bigserial primary key,
    created_at timestamptz not null,
    updated_at timestamptz null,
    name varchar(255) not null,
    code varchar(255) not null unique,
    state_id bigint not null,
    active boolean not null,

    CONSTRAINT fk_city_state
        FOREIGN KEY (state_id)
        REFERENCES state (id)
        ON DELETE CASCADE
);