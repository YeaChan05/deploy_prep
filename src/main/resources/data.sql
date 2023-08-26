-- Authority 테이블에 데이터 추가
insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

-- Member 데이터 추가
insert into member ( member_name, password, email)
values ('admin', '$2a$10$rzun9GI9GLEOucz05i73IOckCFBR6A/pRw9V4tHMfmzxIyM9HC/ge', 'admin@example.com');

-- Member-Authority 관계 데이터 추가
-- 아래에 있는 MEMBER_ID는 실제 Member 테이블의 member_id 값에 맞게 수정해야 합니다.
insert into member_authority (member_id, authority_name) values (1, 'ROLE_USER');
insert into member_authority (member_id, authority_name) values (1, 'ROLE_ADMIN');