-- 계정 권한 부여
grant all
  on book_rent.*
  to 'user_book_rent'@'localhost' identified by 'rootroot';

-- file 권환(backup, load) -- root만 권한 부여 가능
grant File
   on *.*
   to 'user_book_rent'@'localhost';