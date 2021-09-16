create table dog(
id number primary kiy, --상품아이디(my sql에서는 auto_icrement로 자동 1증가)
kind varchar2(12) not null,--개 품종
price number not null, --개 가격
image varchar2(20) not null, -- 개 이미지
country varchar2(12) not null, --원산지
height number, --평균 개 신장
weight number, --평균 개 몸무게
content varchar2(400), --개 설명
readcount number --조회수
);
select * from dog
CREATE TABLE dog(
	id NUMBER PRIMARY KEY,
	kind VARCHAR2(12) NOT NULL,
	price NUMBER NOT NULL,
	image VARCHAR2(20) NOT NULL,
	country VARCHAR2(12) NOT NULL,
	height NUMBER,
	weight NUMBER,
	content VARCHAR2(400),
	readcount NUMBER
);
drop table dog
--sql은 시켄스가 있다
create SEQUENCE dog_seq;

insert into dog value(dog_seq.nextval,'푸들',1000,'pu.jpg','프랑스',1,20,'털많다.'0);
insert into dog value(dog_seq.nextval,'불독',2000,'bul.jpg','독일',1,20,'못생겼다.'0);
insert into dog value(dog_seq.nextval,'진도개',3000,'jin.jpg','대한민국',1,20,'최고다.'0);
insert into dog value(dog_seq.nextval,'허스키',4000,'h.jpg','북극',1,20,'멋지다.'0);

INSERT INTO dog VALUES(dog_seq.nextval,'푸들',1000,'pu.jpg','프랑스',1,20,'털많다',0);
INSERT INTO dog VALUES(dog_seq.nextval,'불독',2000,'bul.jpg','독일',1,20,'못생겼다',0);
INSERT INTO dog VALUES(dog_seq.nextval,'진도개',3000,'jin.jpg','대한민국',1,20,'최고다',0);
INSERT INTO dog VALUES(dog_seq.nextval,'허스키',4000,'h.jpg','북극',1,20,'멋지다',0);
commit

select * from dog;