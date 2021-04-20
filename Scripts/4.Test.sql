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


select memNo, memName, memBirth, memTel, memCp, memAddr, memGrade from memberinfo where memGrade = '일반회원';