INSERT INTO t_question_type(type_code, type_name, created_by, created_at, is_active, version_num) VALUES
	('PG', 'Pilihan Ganda', 1, '2023-06-23 09:00:00', true, 1),
	('ESSA', 'Essay', 1, '2023-06-23 09:00:00', true, 1);
	
INSERT INTO t_user_role(role_name, role_code, created_by, created_at, is_active, version_num) VALUES
	('Super Admin', 'SPA', 1, '2023-06-23 09:00:00', true, 1),
	('Human Resource', 'HR', 1, '2023-06-23 09:00:00', true, 1),
	('Reviewer', 'REV', 1, '2023-06-23 09:00:00', true, 1),
	('Candidate', 'CAN', 1, '2023-06-23 09:00:00', true, 1);
	
INSERT INTO t_progress_status(status, progress_code, created_by, created_at, is_active, version_num) VALUES
	('On Progress', 'ONP', 1, '2023-06-23 09:00:00', true, 1),
	('Submitted', 'SUBM', 1, '2023-06-23 09:00:00', true, 1);
	
INSERT INTO t_acceptance_status(status, acceptance_code, created_by, created_at, is_active, version_num) VALUES
	('Needs Review', 'NR', 1, '2023-06-23 09:00:00', true, 1),
	('Rejected', 'REJ', 1, '2023-06-23 09:00:00', true, 1),
	('Considered', 'CND', 1, '2023-06-23 09:00:00', true, 1),
	('Recommended', 'RCD', 1, '2023-06-23 09:00:00', true, 1 );
	
INSERT INTO t_file_type(type_name, type_code, created_by, created_at, is_active, version_num) VALUES
	('Curiculum Vitae', 'CV', 1, '2023-06-23 09:00:00', true, 1),
	('Ijazah', 'DIP', 1, '2023-06-23 09:00:00', true, 1),
	('Transkrip Nilai', 'TN', 1, '2023-06-23 09:00:00', true, 1),
	('Kartu Keluarga', 'KK', 1, '2023-06-23 09:00:00', true, 1);
	
INSERT INTO t_file(ext, file, created_by, created_at, is_active, version_num) VALUES
	('.jpg', 'examplefile1', 1, '2023-06-23 09:00:00', true, 1);
	
INSERT INTO t_profile(user_name, user_phone, user_address, file_id, created_by, created_at, is_active, version_num) VALUES
	('Muhammad Anggi', '081234567890', 'Jl. Massutakarya No.1', 1, 1, '2023-06-24 07:00:00', true, 1 );

INSERT INTO t_user(user_email, user_password, role_id, profile_id, created_by, created_at, is_active, version_num) VALUES
	('muhammadanggi@gmail.com','muhammadanggi', 1, 1, 1, '2023-06-24 07:00:00', true, 1);
	
