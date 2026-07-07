INSERT INTO id_sequence (
    sequence_id,
    entity_name,
    code,
    start_value,
    current_value,
    is_active,
    created_by,
    created_on,
    modified_by,
    modified_on
)
VALUES
('IDS00000001', 'Employee', 'EMP', 1, 0, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW()),
('IDS00000002', 'Hospital', 'HOP', 1, 0, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW()),
('IDS00000003', 'Donation Plan', 'DOP', 1, 0, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW()),
('IDS00000004', 'Donation Request', 'DOR', 1, 0, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW()),
('IDS00000005', 'Donation Transaction', 'DTR', 1, 0, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW()),
('IDS00000006', 'Role', 'ROL', 1, 3, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW()),
('IDS00000007', 'Application User', 'USR', 1, 0, TRUE, 'SYSTEM', NOW(),'SYSTEM',NOW());


INSERT 
	INTO 
    role_table (role_id, role_name, is_active, created_by, created_on)
VALUES
	('ROL00000001', 'ADMIN', TRUE, 'SYSTEM', NOW()),
	('ROL00000002', 'EMPLOYEE', TRUE, 'SYSTEM', NOW()),
	('ROL00000003', 'PAYROLL_ADMIN', TRUE, 'SYSTEM', NOW());
    




INSERT INTO employee_table (
    employee_id,
    employee_number,
    employee_name,
    employee_email,
    employee_phone_number,
    basic_salary,
    is_active,
    created_by,
    created_on,
    modified_by,
    modified_on
) VALUES
('EMP00000001', 'CBG100001', 'Rahul Sharma', 'rahul.sharma@example.com', '+919999900001', 45000.00, TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('EMP00000002', 'CBG100002', 'Priya Nair', 'priya.nair@example.com', '+919999900002', 62000.00, TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('EMP00000003', 'CBG100003', 'Arjun Kumar', 'arjun.kumar@example.com', '+919999900003', 38000.00, TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('EMP00000004', 'CBG100004', 'Sneha Reddy', 'sneha.reddy@example.com', '+919999900004', 75000.00, TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('EMP00000005', 'CBG100005', 'Vikram Singh', 'vikram.singh@example.com', '+919999900005', 55000.00, TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW());


INSERT INTO hospital_table (
    hospital_id,
    hospital_name,
    hospital_description,
    is_active,
    created_by,
    created_on,
    modified_by,
    modified_on
)
VALUES
('HOP00000001', 'HopeCare Hospital',
'Providing medical assistance to underprivileged families.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('HOP00000002', 'LifeBridge Medical Center',
'Supporting critical healthcare treatments and patient care.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('HOP00000003', 'Sunrise Community Hospital',
'Focused on maternal, child, and rural healthcare services.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('HOP00000004', 'Unity Health Foundation',
'Delivering affordable healthcare and emergency medical support.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('HOP00000005', 'Green Valley Hospital',
'Dedicated to improving community health through charitable initiatives.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW());

select * from hospital_table;


INSERT INTO donation_plan_table (
    donation_plan_id,
    hospital_id,
    donation_name,
    donation_description,
    is_active,
    created_by,
    created_on,
    modified_by,
    modified_on
)
VALUES
('DOP00000001', 'HOP00000001', 'Children''s Education Support',
'Provides financial assistance for children requiring long-term medical treatment and education support.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000002', 'HOP00000001', 'Cancer Treatment Assistance',
'Supports patients undergoing cancer treatment through charitable contributions.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000003', 'HOP00000002', 'Cardiac Care Program',
'Helps patients receive life-saving cardiac treatments and surgeries.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000004', 'HOP00000002', 'Dialysis Support Initiative',
'Provides dialysis treatment support for financially challenged patients.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000005', 'HOP00000003', 'Maternal Care Program',
'Supports healthcare services for expecting mothers and newborns.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000006', 'HOP00000003', 'Child Nutrition Initiative',
'Provides nutrition and healthcare assistance for children.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000007', 'HOP00000004', 'Emergency Relief Fund',
'Provides emergency medical financial assistance to patients.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000008', 'HOP00000004', 'Rural Healthcare Outreach',
'Supports healthcare camps and treatment in rural communities.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000009', 'HOP00000005', 'Senior Citizen Wellness',
'Provides healthcare support for elderly patients.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW()),

('DOP00000010', 'HOP00000005', 'Organ Transplant Support',
'Provides financial assistance for organ transplant patients.',
TRUE, 'SYSTEM', NOW(), 'SYSTEM', NOW());


select * from donation_plan_table;


-- update id sequence table after every insert

select * from id_sequence;

UPDATE id_sequence
SET current_value = 10,
    modified_by = 'SYSTEM',
    modified_on = NOW()
WHERE code = 'DOP';

INSERT INTO id_sequence (
    sequence_id,
    entity_name,
    code,
    start_value,
    current_value,
    is_active,
    created_by,
    created_on,
    modified_by,
    modified_on
)
VALUES (
    'IDS00000008',
    'Payroll Run',
    'PRL',
    1,
    2,
    TRUE,
    'SYSTEM',
    NOW(),
    'SYSTEM',
    NOW()
);


INSERT INTO payroll_run_table (
    payroll_run_id,
    payroll_month,
    payroll_year,
    run_status,
    processed_on,
    processed_by,
    remarks,
    is_active,
    created_by,
    created_on,
    modified_by,
    modified_on
)
VALUES
(
    'PRL00000001',
    7,
    2026,
    'COMPLETED',
    '2026-07-06 18:43:07',
    'SYSTEM',
    'July 2026 payroll processed successfully.',
    TRUE,
    'SYSTEM',
    '2026-07-06 18:43:07',
    'SYSTEM',
    '2026-07-06 18:43:07'
),
(
    'PRL00000002',
    8,
    2026,
    'COMPLETED',
    '2026-08-06 18:43:07',
    'SYSTEM',
    'August 2026 payroll processed successfully.',
    TRUE,
    'SYSTEM',
    '2026-08-06 18:43:07',
    'SYSTEM',
    '2026-08-06 18:43:07'
);

