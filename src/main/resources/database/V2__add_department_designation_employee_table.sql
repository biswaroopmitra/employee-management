create table if not exists department(
    id serial primary key,
    name varchar(255) not null,
    code varchar(255) not null,
    active boolean not null
);

create table if not exists designation(
    id serial primary key,
    name varchar(255) not null,
    code varchar(255) not null,
    active boolean not null
);

create table if not exists employee(
    id serial primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    employee_code varchar(255) not null,
    email varchar(255) not null,
    mobile_number varchar(255) not null,
    date_of_birth date not null,
    date_of_joining_org date not null,
    salary numeric(10, 2),
    employee_status varchar(255) not null,
    reporting_manager_id int null,
    designation_id int null,
    department_id int null,
    city_id int null,
    zipcode_id int null,
    active boolean not null,

    ADD CONSTRAINT check_employee_status
        CHECK (employee_status IN ('PROBATION', 'CONTRACT', 'PERMANENT', 'NOTICE_PERIOD', 'EXIT')),

    CONSTRAINT fk_employee_reporting_manager
          FOREIGN KEY (reporting_manager_id)
          REFERENCES reporting_manager (id)
          ON DELETE CASCADE set null,

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
                ON DELETE CASCADE,
);