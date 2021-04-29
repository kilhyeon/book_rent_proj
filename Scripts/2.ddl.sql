-- 도서관리
DROP SCHEMA IF EXISTS book_rent;

-- 도서관리
CREATE SCHEMA book_rent;

-- 회원정보
CREATE TABLE book_rent.memberInfo (
	memNo        INT          NOT NULL COMMENT '회원번호', -- 회원번호
	memName      VARCHAR(10)  NULL     COMMENT '회원이름', -- 회원이름
	memBirth     VARCHAR(8)   NULL     COMMENT '생년월일', -- 생년월일
	memTel       VARCHAR(13)  NULL     COMMENT '전화번호', -- 전화번호
	memCp        VARCHAR(13)  NULL     COMMENT '휴대전화', -- 휴대전화
	memAddr      VARCHAR(256) NULL     COMMENT '주소', -- 주소
	memGradeNo   INT          NULL     COMMENT '회원등급', -- 회원등급
	memRentCount INT          NULL     DEFAULT 0 COMMENT '대여권수' -- 대여권수
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
	bookNo     INT         NOT NULL COMMENT '도서번호', -- 도서번호
	bookName   VARCHAR(40) NULL     COMMENT '도서제목', -- 도서제목
	bookCateNo INT         NULL     COMMENT '도서구분', -- 도서구분
	bookCount  INT         NULL     COMMENT '도서보유권수', -- 도서보유권수
	rentState  INT         NULL     DEFAULT 0 COMMENT '대출여부' -- 대출여부
)
COMMENT '도서정보';

-- 도서정보
ALTER TABLE book_rent.bookInfo
	ADD CONSTRAINT PK_bookInfo -- 도서정보 기본키
		PRIMARY KEY (
			bookNo -- 도서번호
		);

-- 대여내역
CREATE TABLE book_rent.rent (
	rentNo     INT  NOT NULL COMMENT '대여번호', -- 대여번호
	memNo      INT  NOT NULL COMMENT '회원번호', -- 회원번호
	bookNo     INT  NOT NULL COMMENT '도서번호', -- 도서번호
	rentDate   DATE NULL     COMMENT '도서대여일', -- 도서대여일
	returnDate DATE NULL     COMMENT '도서반납일', -- 도서반납일
	lateDate   INT  NULL     COMMENT '연체일자' -- 연체일자
)
COMMENT '대여내역';

-- 대여내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT PK_rent -- 대여내역 기본키
		PRIMARY KEY (
			rentNo -- 대여번호
		);

ALTER TABLE book_rent.rent
	MODIFY COLUMN rentNo INT NOT NULL AUTO_INCREMENT COMMENT '대여번호';

-- 도서구분
CREATE TABLE book_rent.category (
	bookCateNo   INT         NOT NULL COMMENT '도서구분', -- 도서구분
	bookCateName VARCHAR(10) NULL     COMMENT '도서구분이름' -- 도서구분이름
)
COMMENT '도서구분';

-- 도서구분
ALTER TABLE book_rent.category
	ADD CONSTRAINT PK_category -- 도서구분 기본키
		PRIMARY KEY (
			bookCateNo -- 도서구분
		);

-- 회원등급
CREATE TABLE book_rent.grade (
	memGradeNo        INT         NOT NULL COMMENT '회원등급', -- 회원등급
	memGradeName      VARCHAR(10) NULL     COMMENT '회원등급이름', -- 회원등급이름
	memGradeRentCount INT         NULL     COMMENT '등급대여권수' -- 등급대여권수
)
COMMENT '회원등급';

-- 회원등급
ALTER TABLE book_rent.grade
	ADD CONSTRAINT PK_grade -- 회원등급 기본키
		PRIMARY KEY (
			memGradeNo -- 회원등급
		);

-- 회원정보
ALTER TABLE book_rent.memberInfo
	ADD CONSTRAINT FK_grade_TO_memberInfo -- 회원등급 -> 회원정보
		FOREIGN KEY (
			memGradeNo -- 회원등급
		)
		REFERENCES book_rent.grade ( -- 회원등급
			memGradeNo -- 회원등급
		);

-- 도서정보
ALTER TABLE book_rent.bookInfo
	ADD CONSTRAINT FK_category_TO_bookInfo -- 도서구분 -> 도서정보
		FOREIGN KEY (
			bookCateNo -- 도서구분
		)
		REFERENCES book_rent.category ( -- 도서구분
			bookCateNo -- 도서구분
		);

-- 대여내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT FK_memberInfo_TO_rent -- 회원정보 -> 대여내역
		FOREIGN KEY (
			memNo -- 회원번호
		)
		REFERENCES book_rent.memberInfo ( -- 회원정보
			memNo -- 회원번호
		);

-- 대여내역
ALTER TABLE book_rent.rent
	ADD CONSTRAINT FK_bookInfo_TO_rent -- 도서정보 -> 대여내역
		FOREIGN KEY (
			bookNo -- 도서번호
		)
		REFERENCES book_rent.bookInfo ( -- 도서정보
			bookNo -- 도서번호
		);