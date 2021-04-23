select * from bookinfo;
select * from category;

drop view vw_bookinfo_cate;

select * from memberinfo;
select * from grade;



-- 도서정보 구분이름
create view vw_bookinfo_cate as select b.bookNo, b.bookName, c.bookCateNo, c.bookCateName, b.rentState 
from bookinfo b join category c on b.bookCateNo = c.bookCateNo;

select * from vw_bookinfo_cate;


-- 멤버정보 등급이름
create view vw_meminfo_grade as select m.memNo, m.memName, m.memBirth, m.memTel, m.memCp, m.memAddr, g.memGradeNo, g.memGradeName
from memberinfo m join grade g on m.memGradeNo = g.memGradeNo;

select * from vw_meminfo_grade;
