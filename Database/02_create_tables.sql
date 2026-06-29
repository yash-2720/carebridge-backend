CREATE TABLE role_table (
    role_id VARCHAR(20),
    role_name VARCHAR(50) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,
    CONSTRAINT pk_role_table PRIMARY KEY (role_id)
);


CREATE TABLE employee_table(
    employee_id VARCHAR(20),
    employee_number VARCHAR(20) NOT NULL UNIQUE,
    employee_name VARCHAR(150) NOT NULL,
    employee_email VARCHAR(150) NOT NULL UNIQUE,
    employee_phone_number VARCHAR(15) NOT NULL UNIQUE,
    basic_salary DECIMAL(10,2) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,
    CONSTRAINT pk_employee_table PRIMARY KEY (employee_id)
);


CREATE TABLE hospital_table(
    hospital_id VARCHAR(20),
    hospital_name VARCHAR(150) NOT NULL,
    hospital_description VARCHAR(500) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,
    CONSTRAINT pk_hospital_table PRIMARY KEY (hospital_id)
);

CREATE TABLE id_sequence(
	sequence_id VARCHAR(20),
    entity_name VARCHAR(50) NOT NULL,
    code VARCHAR(5) NOT NULL UNIQUE,
    start_value BIGINT NOT NULL,
    current_value BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,
    CONSTRAINT pk_id_sequence PRIMARY KEY (sequence_id)
);
    
CREATE TABLE application_user_table(
    user_id VARCHAR(20),
    employee_id VARCHAR(20) NOT NULL UNIQUE,
    role_id VARCHAR(20) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    last_login TIMESTAMP,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,

    CONSTRAINT pk_application_user_table
        PRIMARY KEY (user_id),

    CONSTRAINT fk_application_user_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee_table(employee_id),

    CONSTRAINT fk_application_user_role
        FOREIGN KEY (role_id)
        REFERENCES role_table(role_id)
);
    

CREATE TABLE donation_plan_table(
    donation_plan_id VARCHAR(20),
    hospital_id VARCHAR(20) NOT NULL,
    donation_name VARCHAR(150) NOT NULL,
    donation_description VARCHAR(500) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,

    CONSTRAINT pk_donation_plan_table
        PRIMARY KEY (donation_plan_id),

    CONSTRAINT fk_donation_plan_hospital
        FOREIGN KEY (hospital_id)
        REFERENCES hospital_table(hospital_id)
);


CREATE TABLE donation_request_table(
    donation_request_id VARCHAR(20),
    employee_id VARCHAR(20) NOT NULL,
    donation_plan_id VARCHAR(20) NOT NULL,
    donation_type VARCHAR(20) NOT NULL,
    donation_amount DECIMAL(10,2) NOT NULL,
    donation_start_date DATE NOT NULL,
    donation_end_date DATE,
    donation_status VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,

    CONSTRAINT pk_donation_request_table
        PRIMARY KEY (donation_request_id),

    CONSTRAINT fk_donation_request_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee_table(employee_id),

    CONSTRAINT fk_donation_request_donation_plan
        FOREIGN KEY (donation_plan_id)
        REFERENCES donation_plan_table(donation_plan_id)
);


CREATE TABLE donation_transaction_table(
    donation_transaction_id VARCHAR(20),
    donation_request_id VARCHAR(20) NOT NULL,
    payroll_period VARCHAR(20) NOT NULL,
    deducted_amount DECIMAL(10,2) NOT NULL,
    transaction_status VARCHAR(50) NOT NULL,
    processed_on TIMESTAMP,
    remarks VARCHAR(100),
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_by VARCHAR(50),
    modified_on TIMESTAMP,

    CONSTRAINT pk_donation_transaction_table
        PRIMARY KEY (donation_transaction_id),

    CONSTRAINT fk_donation_transaction_donation_request
        FOREIGN KEY (donation_request_id)
        REFERENCES donation_request_table(donation_request_id)
);
