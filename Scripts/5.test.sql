select * from memberinfo;
select * from bookinfo;
select * from grade;
select * from category;
select * from rent;

-- 회원조회
select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo;

-- 책 조회
select bookNo, bookName, bookCateNo, rentState from bookinfo;

-- 대여목록 조회
select rentNo, memNo, bookNo, rentDate, returnDate, lateDate from rent;
update rentinfo set returndate = now() where rentno = ? and returndate is null;


select bookCateNo, bookCateName from category where bookCateNo = 1;
select bookCateNo, bookCateName from category where bookCateName = '수학';

select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo where memGrade = '일반회원';

select bookCateNo, bookCateName from category;
select bookCateNo, bookCateName from category;

select memGradeNo, memGradeName from grade;

select memGradeNo, memGradeName from grade where memGradeNo = 1;
select memGradeNo, memGradeName from grade where memGradeName = '일반회원';

insert into category values(5, '소설');
update category set bookCateName = '시' where bookCateNo = 5;
delete from category where bookCateNo = 5;

insert into grade  values(3, '그냥회원');
update grade set memGradeName = '모름회원' where memGradeNo = 3;
delete from grade where memGradeNo = 3;

select bookNo, bookName, bookCateNo, rentState from bookinfo;
update bookinfo set rentState = 2 where bookNo = 123123;



insert into memberinfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo) 
values(120020, '이순신', '20000101', '010-1234-1234', '010-1234-1234', '대구', 2);


insert into bookinfo(bookNo, bookName, bookCateNo) values (555, 555, 1);

-- 회원검색
select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memNo = 12001 order by memNo;

-- 대여정보 뷰 정렬
select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, 
rentDate, returnDate, lateDate 
from vw_rent_mb order by rentNo;

select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount from vw_meminfo_grade where memNo = 12001;

-- 도서 대여 정보 입력
select * from memberinfo;
select * from bookinfo;
select * from rent;

insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (12011, 40010, now(), DATE_ADD(NOW(), INTERVAL 3 DAY), 0);

update bookinfo set rentState = 1 where bookNo = 40010;
update bookinfo set bookCount = bookCount-1, rentState = 1 where bookNo = 40008;
update bookinfo set rentState = if (bookCount > 0, rentState = 0, rentState =1) where bookNo = 40008;
update bookinfo set rentState = rentState-1 where bookNo = 40008;
insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (12009, 40009, now(), DATE_ADD(NOW(), INTERVAL 3 DAY), 0);


-- 대여내역 조회
select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, rentDate, returnDate, lateDate
from vw_rent_mb where memNo = 12001 order by rentNo;

select * from rent;

update rent set returnDate = now() where rentNo = 7 and returnDate is null;

select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, 
rentDate, returnDate, lateDate from vw_rent_mb where memNo = 12001 and returnDate is null order by rentNo;

delete from rent where memNo = 12004;

select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, rentDate, returnDate, lateDate
from vw_rent_mb where memNo = 12001 and returnDate = null order by rentNo;


-- 도서 반납 (대여내역삭제)
select * from vw_rent_mb;

update bookinfo set rentState = 0 where bookNo = 40010;

delete from rent where rentNo = 6;

delete from category where bookCateNo = 5;
select * from bookinfo;
select * from rent;
select * from memberinfo;


update bookinfo set rentState = if (bookCount >= bookCountTotal, 1, 0) where bookNo = 40001;
update bookinfo set bookCount = 4 where bookNo = 40001;


-- 도서추가
insert into bookinfo(bookNo, bookName, bookCountTotal, bookCount, bookCateNo) values (40044, 'book', 5, bookCountTotal, 1);
select bookNo, bookName, bookCountTotal, bookCount, bookCateNo, bookCateName, rentState from vw_bookinfo_cate order by bookNo;



-- 대여 제약조건
insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (12002, 40002, now(), null, 0)
from dual where exists(select * from bookinfo where bookCount = 0);



-- 회원 등급별 대여 회수
update memberinfo set memRentCount = memRentCount+1 where memNo = 12001;


-- 대여횟수 증가
select * from memberinfo;
select * from bookinfo;

update memberinfo set memRecord = memRecord+1 where memNo = 12001;
update bookinfo set bookRecord = bookRecord+1 where bookNo = 40001;

select memNo, memName, memGradeNo, memRecord from memberinfo order by memRecord desc, memNo limit 10;
select bookNo, bookName, bookCateNo, bookRecord from bookinfo b2 order by bookRecord desc, bookNo limit 10;



select memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeName, memGradeRentCount, memRentCount
from vw_meminfo_grade where memCp = '01097415821' order by memNo;


select * from rent;

insert into rent values(1, 12001, 40001, 20210425, null, 0);

update rent set lateDate = if(ReturnDate - rentDate > 3, (ReturnDate - rentDate) -3, 0) where rentNo = 5;














