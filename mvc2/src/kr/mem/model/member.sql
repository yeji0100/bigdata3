select * from EMPLOYEES;
-- ȸ������
create table tblMem(
	num number primary key, 
	name varchar2(20) not null,
	phone varchar2(20) not null,
	addr varchar2(50),
	lat number(16,12),
	lng number(16,12))
	
select * from tblMem

create sequence seq_num;
insert into tblMem values (seq_num.nextval, '���浿','010-111-1111', '���ֱ����� ���� ǳ�ϵ�', 35.1257699845615, 126.852047602507);

select * from tblMem
--Ŭ������ ��ü�� �̷�� �������(����)�� ��ȯ����
