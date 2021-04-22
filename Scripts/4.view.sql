select * from bookinfo;
select * from category;

drop view vw_bookinfo_cate;

-- 도서정보 구분이름
create view vw_bookinfo_cate as select b.bookNo, b.bookName, c.bookCateNo, c.bookCateName, b.rentState 
from bookinfo b join category c on b.bookCateNo = c.bookCateNo;

select * from vw_bookinfo_cate;