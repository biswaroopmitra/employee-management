create table if not exists department(
    id bigserial primary key,
    created_at timestamptz not null,
    updated_at timestamptz null,
    name varchar(255) not null,
    code varchar(255) not null unique,
    active boolean not null
);

create table if not exists designation(
    id bigserial primary key,
    created_at timestamptz not null,
    updated_at timestamptz null,
    name varchar(255) not null,
    code varchar(255) not null unique,
    active boolean not null
);

create table if not exists employee(
    id bigserial primary key,
    created_at timestamptz not null,
    updated_at timestamptz null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    employee_code varchar(255) not null unique,
    email varchar(255) not null unique,
    mobile_number varchar(255) not null unique,
    date_of_birth date not null,
    date_of_joining_org date not null,
    salary numeric(10, 2),
    employee_status varchar(255) not null,
    reporting_manager_id bigint null,
    designation_id bigint not null,
    department_id bigint not null,
    city_id bigint null,
    zipcode_id bigint null,
    active boolean not null,

    CONSTRAINT check_employee_status
        CHECK (employee_status IN ('PROBATION', 'CONTRACT', 'PERMANENT', 'NOTICE_PERIOD', 'EXIT')),

    CONSTRAINT fk_employee_reporting_manager
          FOREIGN KEY (reporting_manager_id)
          REFERENCES employee (id)
          ON DELETE set null,

    CONSTRAINT fk_employee_designation
            FOREIGN KEY (designation_id)
            REFERENCES designation (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_employee_department
                FOREIGN KEY (department_id)
                REFERENCES department (id)
                ON DELETE CASCADE,

    CONSTRAINT fk_employee_city
                FOREIGN KEY (city_id)
                REFERENCES city (id)
                ON DELETE CASCADE,

    CONSTRAINT fk_employee_zipcode
                FOREIGN KEY (zipcode_id)
                REFERENCES zipcode (id)
                ON DELETE CASCADE
);