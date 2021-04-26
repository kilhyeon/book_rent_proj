select * from memberinfo;
select * from bookinfo;
select * from grade;
select * from category;

-- 회원조회
select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo;

-- 책 조회
select bookNo, bookName, bookCateNo, rentState from bookinfo;

-- 대여목록 조회
select rentNo, memNo, bookNo, rentDate, returnDate, lateDate from rent;

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



insert into memberinfo(memName, memBirth, memTel, memCp, memAddr, memGradeNo) 
values('홍길동', '20000101', '010-1234-1234', '010-1234-1234', '대구', 2);


insert into bookinfo(bookNo, bookName, bookCateNo) values (555, 555, 1);



-- 대여정보 뷰 정렬
select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, 
rentDate, returnDate, lateDate 
from vw_rent_mb order by rentNo;


-- 도서 대여 정보 입력
select * from memberinfo;
select * from bookinfo;
select * from rent;

insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (12011, 40010, now(), DATE_ADD(NOW(), INTERVAL 3 DAY), 0);

update bookinfo set rentState = 1 where bookNo = 40010;


insert into rent (memNo, bookNo, rentDate, returnDate, lateDate) values (12009, 40009, now(), DATE_ADD(NOW(), INTERVAL 3 DAY), 0);


-- 회원 대여내역 조회
select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, rentDate, returnDate, lateDate
from vw_rent_mb where memNo = 12004 order by rentNo;
