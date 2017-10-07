insert into users(name, passport_number, email, phone_number, created_at, updated_at) values('user 1', 'PASS123', 'email@gmail.com', '+4915758832130', now(), now())
insert into users(name, passport_number, email, created_at, updated_at) values('user 2', 'PASS124', 'email2@gmail.com', now(), now())

insert into accounts(number, iban, account_type, balance, version, user_id, created_at, updated_at) values('123', 'DE01234123', 0, 1000, 0, 1, now(), now())
insert into accounts(number, iban, account_type, balance, version, user_id, created_at, updated_at) values('124', 'DE01234124', 0, 1000, 0, 2, now(), now())