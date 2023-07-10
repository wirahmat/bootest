-- Table Topic
CREATE TABLE t_question_topic(
	id serial,
	topic_name varchar(50) NOT NULL,
	topic_code varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_question_topic ADD CONSTRAINT topic_pk
	PRIMARY KEY(id);

-- Table Type
CREATE TABLE t_question_type(
	id serial,
	type_name varchar(15) NOT NULL,
	type_code varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_question_type ADD CONSTRAINT type_pk
	PRIMARY KEY(id);

-- Table Package
CREATE TABLE t_question_package(
	id serial,
	package_name varchar(25) NOT NULL,
	package_code varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_question_package ADD CONSTRAINT package_pk
	PRIMARY KEY(id);

-- Table Question
CREATE TABLE t_question(
	id serial,
	question text NOT NULL,
	topic_id int NOT NULL,
	type_id int NOT NULL,
	package_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_question ADD CONSTRAINT question_pk
	PRIMARY KEY(id);

ALTER TABLE t_question ADD CONSTRAINT topic_question_fk
	FOREIGN KEY(topic_id)
	REFERENCES t_question_topic(id);

ALTER TABLE t_question ADD CONSTRAINT type_question_fk
	FOREIGN KEY(type_id)
	REFERENCES t_question_type(id);

ALTER TABLE t_question ADD CONSTRAINT package_question_fk
	FOREIGN KEY(package_id)
	REFERENCES t_question_package(id);

-- Table Question Option
CREATE TABLE t_question_option(
	id serial,
	question_id int NOT NULL,
	option_data text NOT NULL,
	is_answer boolean NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_question_option ADD CONSTRAINT question_option_pk
	PRIMARY KEY(id);

ALTER TABLE t_question_option ADD CONSTRAINT question_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

-- Table Role
CREATE TABLE t_user_role(
	id serial,
	role_name varchar(20) NOT NULL,
	role_code varchar(4) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_user_role ADD CONSTRAINT user_role_pk
	PRIMARY KEY(id);

-- Table File Type
CREATE TABLE t_file_type(
	id serial,
	type_name varchar(20) NOT NULL,
	type_code varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_file_type ADD CONSTRAINT file_type_pk
	PRIMARY KEY(id);

-- Table File
CREATE TABLE t_file(
	id serial,
	ext varchar(5) NOT NULL,
	file text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

-- Table Progress Status
CREATE TABLE t_progress_status(
	id serial,
	status varchar(15) NOT NULL,
	progress_code varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_progress_status ADD CONSTRAINT progress_status_pk
	PRIMARY KEY(id);

-- Table Acceptance Status
CREATE TABLE t_acceptance_status(
	id serial,
	status varchar(30) NOT NULL,
	acceptance_code varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_acceptance_status ADD CONSTRAINT acceptance_status_pk
	PRIMARY KEY(id);

-- Table Profile
CREATE TABLE t_profile(
	id serial,
	user_name varchar(50) NOT NULL,
	user_phone varchar(50) NOT NULL,
	user_address varchar(50) NOT NULL,
	file_id int ,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_profile ADD CONSTRAINT profile_pk
	PRIMARY KEY(id);

ALTER TABLE t_profile ADD CONSTRAINT file_profile_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

-- Table User
CREATE TABLE t_user(
	id serial,
	user_email varchar(50) NOT NULL,
	user_password text NOT NULL,
	role_id int NOT NULL,
	profile_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL, 
	version_num int NOT NULL
);

ALTER TABLE t_user ADD CONSTRAINT user_pk
	PRIMARY KEY(id);

ALTER TABLE t_user ADD CONSTRAINT role_user_fk
	FOREIGN KEY(role_id)
	REFERENCES t_user_role(id);

ALTER TABLE t_user ADD CONSTRAINT profile_user_fk
	FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);

-- Table Candidate File
CREATE TABLE t_candidate_file(
	id serial,
	file_id int NOT NULL,
	candidate_id int NOT NULL,
	file_type_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_candidate_file ADD CONSTRAINT candidate_file_pk
	PRIMARY KEY(id);

ALTER TABLE t_candidate_file ADD CONSTRAINT candidate_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_user(id);

ALTER TABLE t_candidate_file ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_candidate_file ADD CONSTRAINT file_type_fk
	FOREIGN KEY(file_type_id)
	REFERENCES t_file_type(id);

-- Table Review
CREATE TABLE t_review(
	id serial,
	progress_status_id int,
	acceptance_status_id int,
	candidate_id int NOT NULL,
	reviewer_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_review ADD CONSTRAINT review_pk
	PRIMARY KEY(id);

ALTER TABLE t_review ADD CONSTRAINT progress_review_fk
	FOREIGN KEY(progress_status_id)
	REFERENCES t_progress_status(id);

ALTER TABLE t_review ADD CONSTRAINT acceptance_review_fk
	FOREIGN KEY(acceptance_status_id)
	REFERENCES t_acceptance_status(id);

ALTER TABLE t_review ADD CONSTRAINT candidate_review_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_user(id);

ALTER TABLE t_review ADD CONSTRAINT reviewer_review_fk
	FOREIGN KEY(reviewer_id)
	REFERENCES t_user(id);

-- Table Candidate Assign
CREATE TABLE t_candidate_assign(
	id serial,
	candidate_id int NOT NULL,
	question_type_id int NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_candidate_assign ADD CONSTRAINT candidate_assign_pk
	PRIMARY KEY(id);

ALTER TABLE t_candidate_assign ADD CONSTRAINT user_assign_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_user(id);

ALTER TABLE t_candidate_assign ADD CONSTRAINT acceptance_review_fk
	FOREIGN KEY(question_type_id)
	REFERENCES t_question_type(id);

--Table Answer
CREATE TABLE t_answer_candidate(
	id serial,
	question_id int NOT NULL,
	candidate_id int NOT NULL,
	answer_essay text,
	question_option_id int,
	candidate_assign_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_answer_candidate ADD CONSTRAINT answer_candidate_pk
	PRIMARY KEY(id);

ALTER TABLE t_answer_candidate ADD CONSTRAINT option_answer_fk
	FOREIGN KEY(question_option_id)
	REFERENCES t_question_option (id);

ALTER TABLE t_answer_candidate ADD CONSTRAINT candidate_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_user(id);

ALTER TABLE t_answer_candidate ADD CONSTRAINT question_answer_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

ALTER TABLE t_answer_candidate ADD CONSTRAINT assign_answer_fk
	FOREIGN KEY(candidate_assign_id)
	REFERENCES t_candidate_assign(id);

--Table Review Detail
CREATE TABLE t_review_detail(
	id serial,
	grade numeric (4,1),
	notes text,
	review_id int NOT NULL,
	candidate_assign_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_review_detail ADD CONSTRAINT review_detail_pk
	PRIMARY KEY(id);

ALTER TABLE t_review_detail ADD CONSTRAINT review_fk
	FOREIGN KEY(review_id)
	REFERENCES t_review(id);

ALTER TABLE t_review_detail ADD CONSTRAINT assign_review_detail_fk
	FOREIGN KEY(candidate_assign_id)
	REFERENCES t_candidate_assign(id);

-- Table Candidate Question
CREATE TABLE t_question_candidate(
	id serial,
	candidate_id int NOT NULL,
	question_id int NOT NULL,
	hr_id int NOT NULL,
	candidate_assign_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version_num int NOT NULL
);

ALTER TABLE t_question_candidate ADD CONSTRAINT question_candidate_pk
	PRIMARY KEY(id);

ALTER TABLE t_question_candidate ADD CONSTRAINT question_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

ALTER TABLE t_question_candidate ADD CONSTRAINT review_fk
	FOREIGN KEY(candidate_assign_id)
	REFERENCES t_candidate_assign(id);

ALTER TABLE t_question_candidate ADD CONSTRAINT candidate_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_user(id);

ALTER TABLE t_question_candidate ADD CONSTRAINT hr_fk
	FOREIGN KEY(hr_id)
	REFERENCES t_user(id);