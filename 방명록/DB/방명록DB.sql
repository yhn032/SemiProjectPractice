/*

--일련번호 관리 객체 
create sequence seq_visit_idx
	
	
	
--테이블 생성 
create table visit
(
	idx		int, 						--일련번호
	name	varchar2(100) not null,		--이름
	content varchar2(100) not null,		--내용
	pwd		varchar2(100) not null,		--비밀번호
	ip		varchar2(100) not null,		--아이피
	regdate	date						--작성일자
)	
	
--기본키
alter table visit 
add constraint pk_visit_idx primary key(idx)
	
--sample data 
insert into visit values(seq_visit_idx.nextVal, '일길동', '내가1등이다', '1234', '192.168.0.15', sysdate);
insert into visit values(seq_visit_idx.nextVal, '이길동', '아쉽네;;1등놓쳤네..', '1234', '192.168.0.15', sysdate);
	
	
--조회 (최근 작성된 순서대로)
select * from visit order by idx desc
	
	
commit
	
	
	
*/