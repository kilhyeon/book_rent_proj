-- 도서관리
DROP SCHEMA IF EXISTS book_rent;

-- 도서관리
CREATE SCHEMA book_rent;

-- 회원정보
CREATE TABLE book_rent.member_info (
	memNo    VARCHAR(5)  NOT NULL COMMENT '회원번호', -- 회원번호
	memName  VARCHAR(10) NULL     COMMENT '이름', -- 이름
	memBirth VARCHAR(8)  NULL     COMMENT '생년월일', -- 생년월일
	memTel   VARCHAR(13) NULL     COMMENT '전화번호', -- 전화번호
	memCp    VARCHAR(13) NULL     COMMENT '휴대전화', -- 휴대전화
	memAddr  VARCHAR(20) NULL     COMMENT '주소' -- 주소
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE book_rent.member_info
	ADD CONSTRAINT PK_member_info -- 회원정보 기본키
		PRIMARY KEY (
			memNo -- 회원번호
		);

-- 도서정보
CREATE TABLE book_rent.book_info (
	bookNo   VARCHAR(5)  NOT NULL COMMENT '도서번호', -- 도서번호
	bookName VARCHAR(40) NULL     COMMENT '도서제목', -- 도서제목
	bookRent VARCHAR(4)  NULL     COMMENT '대출여부' -- 대출여부
)
COMMENT '도서정보';

-- 도서정보
ALTER TABLE book_rent.book_info
	ADD CONSTRAINT PK_book_info -- 도서정보 기본키
		PRIMARY KEY (
			bookNo -- 도서번호
		);

-- 대출내역
CREATE TABLE book_rent.rent (
	rentNo     VARCHAR(5)  NOT NULL COMMENT '대출번호', -- 대출번호
	memNo      VARCHAR(5)  NOT NULL COMMENT '회원번호', -- 회원번호
	memName    VARCHAR(4)  NULL     COMMENT '회원이름', -- 회원이름
	memBirth   VARCHAR(8)  NULL     COMMENT '생년월일', -- 생년월일
	bookNo     VARCHAR(5)  NOT NULL COMMENT '도서번호', -- 도서번호
	bookName   VARCHAR(40) NULL     COMMENT '도서제목', -- 도서제목
	rentDate   DATE        NULL     COMMENT '도서대여일', -- 도서대여일
	returnDate DATE        NULL     COMMENT '도서반납일' -- 도서반납일
)
COMMENT '대출내역';

-- 대출내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT PK_rent -- 대출내역 기본키
		PRIMARY KEY (
			rentNo -- 대출번호
		);

-- 대출내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT FK_member_info_TO_rent -- 회원정보 -> 대출내역
		FOREIGN KEY (
			memNo -- 회원번호
		)
		REFERENCES book_rent.member_info ( -- 회원정보
			memNo -- 회원번호
		);

-- 대출내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT FK_book_info_TO_rent -- 도서정보 -> 대출내역
		FOREIGN KEY (
			bookNo -- 도서번호
		)
		REFERENCES book_rent.book_info ( -- 도서정보
			bookNo -- 도서번호
		);