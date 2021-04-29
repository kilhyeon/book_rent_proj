-- 도서정보 구분이름
create view vw_bookinfo_cate as select b.bookNo, b.bookName, b.bookCountTotal, b.bookCount, c.bookCateNo, c.bookCateName, b.rentState, b.bookRecord
from bookinfo b join category c on b.bookCateNo = c.bookCateNo;



-- 회원정보 등급이름
create view vw_meminfo_grade as select m.memNo, m.memName, m.memBirth, m.memTel, m.memCp, m.memAddr, g.memGradeNo, g.memGradeName, g.memGradeRentCount, m.memRentCount, m.memRecord
from memberinfo m join grade g on m.memGradeNo = g.memGradeNo;


-- 렌트 정보 회원 도서 정보

create view vw_rent_mb as select r.rentNo, m.memNo, m.memName, m.memGradeNo, m.memGradeName, m.memGradeRentCount, m.memRentCount, b.bookNo, b.bookName, b.bookCount, b.bookCateNo, b.bookCateName,
r.rentDate, r.returnDate, r.lateDate 
from rent r join vw_bookinfo_cate b on r.bookNo = b.bookNo join vw_meminfo_grade m on r.memNo = m.memNo;


/*
select * from vw_bookinfo_cate;
select * from vw_meminfo_grade;
select * from vw_rent_mb;
*/


/*-- 테스트
update bookinfo set bookName = 40010, bookCount = ?, bookCateNo = ? where bookNo = ? */


