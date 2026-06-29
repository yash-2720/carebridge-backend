# CareBridge Database

## Database Name

carebridge

## Execution Order

1. Execute `01_create_database.sql`
2. Execute `02_create_tables.sql`
3. Execute `03_seed_data.sql`

## Seed Data Included

- Roles
- ID Sequences
- Employees
- Hospitals
- Donation Plans

## Notes

- Primary keys use custom business IDs.
- IDs are generated using the `id_sequence` table.
- Transactional tables (`application_user`, `donation_request`, `donation_transaction`) are populated through the Spring Boot application.