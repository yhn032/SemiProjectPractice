/*

--일련번호 관리 객체 시퀀스 생성
create sequence seq_photo_p_idx

--테이블 생성
create table photo 
(
	p_idx 					int,						--일련번호
	p_subjet				varchar2(1000) not null,	--사진제목
	p_content				varchar2(2000) not null,	--사진설명
	p_filename				varchar2(1000) not null,	--파일명
	p_ip					varchar2(100)  not null,	--아이피
	p_regdate				date,						--등록일자
	m_idx					int							--등록사용자 m_idx
)

--기본키
alter table photo 
add constraint pk_photo_p_idx primary key(p_idx);
	
--참조키
alter table photo
add constraint fk_photo_m_idx foreign key(m_idx) references memember(m_idx)

select * from photo

--sample data
insert into photo values(seq_photo_p_idx.nextVal, '일깅동', '길동이는<br>신났어요', '신남.jpg', '192.168.0.9', sysdate, 1);

delete from photo where p_idx = 5
*/