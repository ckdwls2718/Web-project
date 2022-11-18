create table pizza_user(
	idx number(8) primary key,
	name varchar2(30),
	phone varchar2(15) not null,
	addr varchar2(100) not null
);
create sequence pizza_user_idx
start with 1
increment by 1
nocache;

insert into pizza_user(idx,name,phone,addr)
values(pizza_user_idx.nextval,'홍길동','1111-1111',
'서울시 강남구 삼성동 100번지');

insert into pizza_user(idx,name,phone,addr)
values(pizza_user_idx.nextval,'이창진,'222-2222',
'완도군 금일읍');
commit;
