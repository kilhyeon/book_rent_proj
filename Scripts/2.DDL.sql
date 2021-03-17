-- 도서관리
DROP SCHEMA IF EXISTS book_rent;

-- 도서관리
CREATE SCHEMA book_rent;

-- 회원정보
CREATE TABLE book_rent.memberInfo (
	memNo    VARCHAR(5)   NOT NULL COMMENT '회원번호', -- 회원번호
	memName  VARCHAR(10)  NULL     COMMENT '회원이름', -- 회원이름
	memBirth VARCHAR(8)   NULL     COMMENT '생년월일', -- 생년월일
	memTel   VARCHAR(13)  NULL     COMMENT '전화번호', -- 전화번호
	memCp    VARCHAR(13)  NULL     COMMENT '휴대전화', -- 휴대전화
	memAddr  VARCHAR(256) NULL     COMMENT '주소', -- 주소
	memGrade VARCHAR(5)   NULL     COMMENT '회원등급' -- 회원등급
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE book_rent.memberInfo
	ADD CONSTRAINT PK_memberInfo -- 회원정보 기본키
		PRIMARY KEY (
			memNo -- 회원번호
		);

-- 도서정보
CREATE TABLE book_rent.bookInfo (
	bookNo    VARCHAR(5)  NOT NULL COMMENT '도서번호', -- 도서번호
	bookName  VARCHAR(40) NULL     COMMENT '도서제목', -- 도서제목
	cateNo    VARCHAR(5)  NULL     COMMENT '구분번호', -- 구분번호
	rentState VARCHAR(5)  NULL     COMMENT '대출상태' -- 대출상태
)
COMMENT '도서정보';

-- 도서정보
ALTER TABLE book_rent.bookInfo
	ADD CONSTRAINT PK_bookInfo -- 도서정보 기본키
		PRIMARY KEY (
			bookNo -- 도서번호
		);

-- 대출내역
CREATE TABLE book_rent.rent (
	rentNo     VARCHAR(5) NOT NULL COMMENT '대출번호', -- 대출번호
	memNo      VARCHAR(5) NOT NULL COMMENT '회원번호', -- 회원번호
	bookNo     VARCHAR(5) NOT NULL COMMENT '도서번호', -- 도서번호
	rentDate   DATE       NULL     COMMENT '도서대여일', -- 도서대여일
	returnDate DATE       NULL     COMMENT '도서반납일', -- 도서반납일
	lateDate   INT        NULL     COMMENT '연체일자', -- 연체일자
	rentState  VARCHAR(5) NULL     COMMENT '대출상태' -- 대출상태
)
COMMENT '대출내역';

-- 대출내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT PK_rent -- 대출내역 기본키
		PRIMARY KEY (
			rentNo -- 대출번호
		);

-- 도서구분
CREATE TABLE book_rent.bookCategory (
	cateNo   VARCHAR(5)   NOT NULL COMMENT '구분번호', -- 구분번호
	cateName VARCHAR(256) NULL     COMMENT '구분이름' -- 구분이름
)
COMMENT '도서구분';

-- 도서구분
ALTER TABLE book_rent.bookCategory
	ADD CONSTRAINT PK_bookCategory -- 도서구분 기본키
		PRIMARY KEY (
			cateNo -- 구분번호
		);

-- 대출내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT FK_memberInfo_TO_rent -- 회원정보 -> 대출내역
		FOREIGN KEY (
			memNo -- 회원번호
		)
		REFERENCES book_rent.memberInfo ( -- 회원정보
			memNo -- 회원번호
		);

-- 대출내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT FK_bookInfo_TO_rent -- 도서정보 -> 대출내역
		FOREIGN KEY (
			bookNo -- 도서번호
		)
		REFERENCES book_rent.bookInfo ( -- 도서정보
			bookNo -- 도서번호
		);