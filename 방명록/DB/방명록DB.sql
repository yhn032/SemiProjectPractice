/*

--�Ϸù�ȣ ���� ��ü 
create sequence seq_visit_idx
	
	
	
--���̺� ���� 
create table visit
(
	idx		int, 						--�Ϸù�ȣ
	name	varchar2(100) not null,		--�̸�
	content varchar2(100) not null,		--����
	pwd		varchar2(100) not null,		--��й�ȣ
	ip		varchar2(100) not null,		--������
	regdate	date						--�ۼ�����
)	
	
--�⺻Ű
alter table visit 
add constraint pk_visit_idx primary key(idx)
	
--sample data 
insert into visit values(seq_visit_idx.nextVal, '�ϱ浿', '����1���̴�', '1234', '192.168.0.15', sysdate);
insert into visit values(seq_visit_idx.nextVal, '�̱浿', '�ƽ���;;1����Ƴ�..', '1234', '192.168.0.15', sysdate);
	
	
--��ȸ (�ֱ� �ۼ��� �������)
select * from visit order by idx desc
	
	
commit
	
	
	
*/