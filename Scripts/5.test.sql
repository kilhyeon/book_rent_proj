select * from memberinfo;
select * from bookinfo;
select * from grade;
select * from category;

-- 회원조회
select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo;

-- 책 조회
select bookNo, bookName, cateNo, rentState from bookinfo;

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

