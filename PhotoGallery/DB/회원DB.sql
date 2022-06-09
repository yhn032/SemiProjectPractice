/*

--�Ϸù�ȣ ���� ��ü
create sequence seq_member_m_idx

--���̺� ���� 
create table memember
(
	m_idx     int,							--�Ϸù�ȣ
	m_name    varchar2(100) not null,		--�̸�
	m_id	  varchar2(100) not null,		--���̵�
	m_pwd	  varchar2(100) not null,		--��й�ȣ
	m_zipcode varchar2(100) not null,		--�����ȣ
	m_addr	  varchar2(100) not null,		--�ּ�
	m_grade   varchar2(100) default '�Ϲ�', --ȸ�����(�Ϲ� or ������)
	m_ip	  varchar2(100),				--IP
	m_regdate date							--��ϳ�¥
)


--�⺻Ű 
alter table memember
add constraint pk_member_m_idx primary key(m_idx)

--���̵� ����ũ ���� 
alter table memember 
add constraint unique_member_m_id unique(m_id)

--ȸ������� üũ ���� 
alter table memember 
add constraint ck_member_m_grade check(m_grade in ('�Ϲ�', '������'));

select * from memember

--Sample Data
insert into memember values(seq_member_m_idx.nextVal,
							 '�ϱ浿', 
							 'one', 
							 '1234', 
							 '12345', 
							 '����� ������ ���굿', 
							 '�Ϲ�',
							 '192.168.0.15',
							 sysdate
							);
							
insert into memember values(seq_member_m_idx.nextVal,
							 'ȫ����', 
							 'admin', 
							 '1234', 
							 '12345', 
							 '����� ������ ���굿', 
							 '������',
							 '192.168.0.15',
							 sysdate
							);

--JDBC insert��					
insert into memember values(seq_member_m_idx.nextVal,?,?,?,?,?,?,?,sysdate);
							
commit
*/