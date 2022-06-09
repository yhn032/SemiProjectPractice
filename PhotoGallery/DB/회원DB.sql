/*

--일련번호 관리 객체
create sequence seq_member_m_idx

--테이블 생성 
create table memember
(
	m_idx     int,							--일련번호
	m_name    varchar2(100) not null,		--이름
	m_id	  varchar2(100) not null,		--아이디
	m_pwd	  varchar2(100) not null,		--비밀번호
	m_zipcode varchar2(100) not null,		--우편번호
	m_addr	  varchar2(100) not null,		--주소
	m_grade   varchar2(100) default '일반', --회원등급(일반 or 관리자)
	m_ip	  varchar2(100),				--IP
	m_regdate date							--등록날짜
)


--기본키 
alter table memember
add constraint pk_member_m_idx primary key(m_idx)

--아이디 유니크 제약 
alter table memember 
add constraint unique_member_m_id unique(m_id)

--회원등급의 체크 제약 
alter table memember 
add constraint ck_member_m_grade check(m_grade in ('일반', '관리자'));

select * from memember

--Sample Data
insert into memember values(seq_member_m_idx.nextVal,
							 '일길동', 
							 'one', 
							 '1234', 
							 '12345', 
							 '서울시 마포구 노고산동', 
							 '일반',
							 '192.168.0.15',
							 sysdate
							);
							
insert into memember values(seq_member_m_idx.nextVal,
							 '홍관리', 
							 'admin', 
							 '1234', 
							 '12345', 
							 '서울시 마포구 노고산동', 
							 '관리자',
							 '192.168.0.15',
							 sysdate
							);

--JDBC insert용					
insert into memember values(seq_member_m_idx.nextVal,?,?,?,?,?,?,?,sysdate);
							
commit
*/