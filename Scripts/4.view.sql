select * from bookinfo;
select * from category;

drop view vw_bookinfo_cate;

select * from memberinfo;
select * from grade;



-- 도서정보 구분이름
create view vw_bookinfo_cate as select b.bookNo, b.bookName, c.bookCateNo, c.bookCateName, b.rentState 
from bookinfo b join category c on b.bookCateNo = c.bookCateNo;

select * from vw_bookinfo_cate;


-- 회원정보 등급이름
create view vw_meminfo_grade as select m.memNo, m.memName, m.memBirth, m.memTel, m.memCp, m.memAddr, g.memGradeNo, g.memGradeName
from memberinfo m join grade g on m.memGradeNo = g.memGradeNo;

select * from vw_meminfo_grade;

-- 렌트 정보 회원 도서 정보

select * from memberinfo;
select * from bookinfo;
select * from rent;

create view vw_rent_mb as select r.rentNo, m.memNo, m.memName, m.memGradeNo, m.memGradeName, b.bookNo, b.bookName, b.bookCateNo, b.bookCateName,
r.rentDate, r.returnDate, r.lateDate 
from rent r join vw_bookinfo_cate b on r.bookNo = b.bookNo join vw_meminfo_grade m on r.memNo = m.memNo;

drop view vw_rent_mb;

select rentNo, memNo, memName, memGradeNo, memGradeName, bookNo, bookName, bookCateNo, bookCateName, 
rentDate, returnDate, lateDate 
from vw_rent_mb order by rentNo;






