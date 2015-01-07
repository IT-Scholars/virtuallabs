CREATE OR REPLACE FUNCTION "set_update_ts" () RETURNS trigger AS $$
   BEGIN
       NEW.update_ts = NOW();
       RETURN NEW;
   END;
$$LANGUAGE 'plpgsql' IMMUTABLE CALLED ON NULL INPUT SECURITY INVOKER;

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role(
       role_title VARCHAR(100) PRIMARY KEY,
       update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "user_role" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
INSERT INTO user_role(role_title) VALUES('STUDENT');
INSERT INTO user_role(role_title) VALUES('MENTOR');
INSERT INTO user_role(role_title) VALUES('ADMIN');

DROP TABLE IF EXISTS user_resource;
CREATE TABLE user_resource(
       resource_title VARCHAR(100) PRIMARY KEY,
       update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "user_resource" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
INSERT INTO user_resource(resource_title) VALUES('VIRTUAL LAB');
INSERT INTO user_resource(resource_title) VALUES('MENTORING');
INSERT INTO user_resource(resource_title) VALUES('CERTIFICATE');
INSERT INTO user_resource(resource_title) VALUES('PHYSICAL');

DROP TABLE IF EXISTS role_resource;
CREATE TABLE role_resource (
       user_role VARCHAR(100) NOT NULL REFERENCES user_role(role_title), 
       resource VARCHAR(100) NOT NULL REFERENCES user_resource(resource_title),
       update_ts TIMESTAMP DEFAULT NOW(),
       PRIMARY KEY(user_role, resource)
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "role_resource" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
INSERT INTO role_resource(user_role, resource) VALUES( 'STUDENT', 'VIRTUAL LAB');
INSERT INTO role_resource(user_role, resource) VALUES( 'STUDENT', 'MENTORING');
INSERT INTO role_resource(user_role, resource) VALUES( 'STUDENT', 'CERTIFICATE');
INSERT INTO role_resource(user_role, resource) VALUES( 'MENTOR', 'MENTORING');
INSERT INTO role_resource(user_role, resource) VALUES( 'ADMIN', 'PHYSICAL');

DROP TABLE IF EXISTS user_profile;
CREATE TABLE user_profile (
       username VARCHAR(50) NOT NULL PRIMARY KEY, 
       password VARCHAR(50),
       first_name VARCHAR(50),
       last_name VARCHAR(50),
       email_address VARCHAR(100),
       user_role VARCHAR(100) NOT NULL REFERENCES user_role(role_title),
       time_zone_id VARCHAR(50),
       contact_info VARCHAR(200),
       update_ts TIMESTAMP DEFAULT NOW() 
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "user_profile" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
INSERT INTO user_profile (username, password, user_role, time_zone_id) 
	VALUES ('admin', 'admin', 'ADMIN', 'US/Eastern');
INSERT INTO user_profile (username, password, user_role, time_zone_id) 
	VALUES('ALL_STUDENTS','','STUDENT','');
-- INSERT INTO user_profile (username, password, user_role, time_zone_id) 
--	VALUES ('student', 'student', 'STUDENT', 'US/Eastern');
--INSERT INTO user_profile (username, password, user_role, time_zone_id) 
--	VALUES ('student2', 'student2', 'STUDENT', 'US/Eastern');
--INSERT INTO user_profile (username, password, user_role, time_zone_id) 
--	VALUES ('mentor', 'mentor', 'MENTOR', 'US/Eastern');
--INSERT INTO user_profile (username, password, user_role, time_zone_id) 
--	VALUES ('mentor2', 'mentor2', 'MENTOR', 'US/Eastern');

DROP TABLE IF EXISTS user_course;
CREATE TABLE user_course(
       course 	VARCHAR(200) NOT NULL,
	   username VARCHAR(50) REFERENCES user_profile(username) NOT NULL,
       update_ts TIMESTAMP DEFAULT NOW(),
	   PRIMARY KEY(course, username)
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "user_course" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
--INSERT INTO user_course VALUES ('All Courses', 'mentor');
--INSERT INTO user_course VALUES ('Kaseya 1', 'admin');
--INSERT INTO user_course VALUES ('Kaseya 1', 'student');
--INSERT INTO user_course VALUES ('Kaseya 1', 'student2');
--INSERT INTO user_course VALUES ('Kaseya 1', 'mentor');
--INSERT INTO user_course VALUES ('Kaseya 1', 'mentor2');
--INSERT INTO user_course VALUES ('Kaseya 2', 'admin');
--INSERT INTO user_course VALUES ('Kaseya 2', 'student');
--INSERT INTO user_course VALUES ('Kaseya 2', 'student2');
--INSERT INTO user_course VALUES ('Kaseya 2', 'mentor');
--INSERT INTO user_course VALUES ('Kaseya 2', 'mentor2');

DROP TABLE IF EXISTS appointments;
CREATE TABLE appointments (
        sch_id CHAR(36) PRIMARY KEY,
        quota_id CHAR(36),   
        affiliation_id CHAR(36) NOT NULL,   
	    username VARCHAR(50) NOT NULL REFERENCES user_profile(username),
		course VARCHAR(200) NOT NULL,
		resource_type VARCHAR(100) NOT NULL,
 	    active BOOLEAN DEFAULT 'TRUE',
        update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "appointments" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();

DROP TABLE IF EXISTS user_ve_instances;
CREATE TABLE user_ve_instances(
	    id serial PRIMARY KEY,
	    username VARCHAR(50) NOT NULL REFERENCES user_profile(username),
	    ve_ins_username VARCHAR(50),
		ve_id CHAR(36) NOT NULL,
		course VARCHAR(200) NOT NULL,
		resource_type VARCHAR(100) NOT NULL,
	    active BOOLEAN DEFAULT 'TRUE',
        update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "user_ve_instances" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();

DROP TABLE IF EXISTS ve_types;
CREATE TABLE ve_types(
	    id serial PRIMARY KEY,
		ve_type VARCHAR(200) NOT NULL,
		course VARCHAR(200) NOT NULL,
		resource_type VARCHAR(100) NOT NULL,
        active BOOLEAN DEFAULT 'TRUE',
        update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "ve_types" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();

DROP TABLE IF EXISTS promo;
CREATE TABLE promo(
	    id serial PRIMARY KEY,
		promo_code VARCHAR(50) NOT NULL,
		description VARCHAR(255),
        active BOOLEAN DEFAULT 'TRUE',
        update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "promo" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
INSERT INTO promo (id, promo_code, description, active) VALUES (1, 'FIU-SP12' , 'FIU - Spring 2012', 't');
INSERT INTO promo (id, promo_code, description, active) VALUES (2, 'ITS-FW'   , 'IT Scholars - Fundamentals Workshop', 't');
INSERT INTO promo (id, promo_code, description, active) VALUES (3, 'ITS-KSDW' , 'IT Scholars - Kaseya Service Desk Workshop', 't');
INSERT INTO promo (id, promo_code, description, active) VALUES (4, 'ITS-FW-63', 'IT Scholars - Fundamentals Workshop - 6.3', 't');

DROP TABLE IF EXISTS course;
CREATE TABLE course(
	    id INT NOT NULL,
		full_name VARCHAR(255) NOT NULL,
		short_name VARCHAR(45) NOT NULL,
		promo_code VARCHAR(50),
        update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "course" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();

DROP TABLE IF EXISTS ve_free_ports_and_macs;
CREATE TABLE ve_free_ports_and_macs(
	    ve_first_free_port INT NOT NULL,
		ve_first_free_mac INT NOT NULL,
        active BOOLEAN DEFAULT 'TRUE',
        update_ts TIMESTAMP DEFAULT NOW()
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "ve_free_ports_and_macs" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();

--INSERT INTO ve_types (ve_type, course, resource_type) VALUES ('ve_sample.xml','Kaseya 1', 'VIRTUAL LAB');
--INSERT INTO ve_types (ve_type, course, resource_type) VALUES ('ve_sample.xml','Kaseya 1', 'MENTORING');
--INSERT INTO ve_types (ve_type, course, resource_type) VALUES ('ve_sample.xml','Kaseya 1', 'CERTIFICATE');
--INSERT INTO ve_types (ve_type, course, resource_type) VALUES ('ve_sample.xml','Kaseya 2', 'VIRTUAL LAB');
--INSERT INTO ve_types (ve_type, course, resource_type) VALUES ('ve_sample.xml','Kaseya 2', 'MENTORING');
--INSERT INTO ve_types (ve_type, course, resource_type) VALUES ('ve_sample.xml','Kaseya 2', 'CERTIFICATE');

DROP TABLE IF EXISTS vm_ins_task;
CREATE TABLE vm_ins_task(
	id serial PRIMARY KEY,
	mac_address CHAR(17) NOT NULL,
	task VARCHAR(50) NOT NULL,
	execution_time TIMESTAMP NOT NULL,
	active BOOLEAN DEFAULT 'TRUE',
	update_ts TIMESTAMP DEFAULT NOW()
	);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "vm_ins_task" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
   
DROP TABLE IF EXISTS vm_ins_sync_user_task;
CREATE TABLE vm_ins_sync_user_task(
	task_id INT NOT NULL,
	username VARCHAR(50), 
	password VARCHAR(50),
	active BOOLEAN DEFAULT 'TRUE',
	update_ts TIMESTAMP DEFAULT NOW()
	);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
	ON "vm_ins_sync_user_task" FOR EACH ROW
	EXECUTE PROCEDURE "set_update_ts"();
   
DROP TABLE IF EXISTS htpasswd_host;
CREATE TABLE htpasswd_host(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	ssh_port INTEGER NOT NULL DEFAULT 22,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	active BOOLEAN DEFAULT 'TRUE',
	update_ts TIMESTAMP DEFAULT NOW()
	);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "htpasswd_host" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();
INSERT INTO 
	htpasswd_host(name,ssh_port,username,password,active) 
	VALUES('wolf.cis.fiu.edu',22,'sadjadi','lei9864la','TRUE');	
	
DROP TABLE IF EXISTS cached_password;
CREATE TABLE cached_password (
       username VARCHAR(50) NOT NULL PRIMARY KEY, 
       password VARCHAR(50),
       update_ts TIMESTAMP DEFAULT NOW() 
);
CREATE TRIGGER "trg_set_update_ts" BEFORE INSERT OR UPDATE
   ON "cached_password" FOR EACH ROW
   EXECUTE PROCEDURE "set_update_ts"();


   