insert into grade values
	(1, '일반회원', 3),
	(2, '특별회원', 5);

insert into category values
	(1, '수학'),
	(2, '컴퓨터'),
	(3, '전자'),
	(4, '통계');

insert into memberinfo values
	(12001, '홍길동', '19070405', '042-421-1739', '01097415821', '대전', 1, 0, 0),
	(12002, '김연수', '19850301', '064-446-8695', '010-4568-5581', '제주', 1, 0, 0),
	(12003, '김지원', '19860708', '053-548-7898', '010-9111-5556', '대구', 1, 0, 0),
	(12004, '문희원', '19800108', '052-221-1231', '010-7777-7777', '울산', 2, 0, 0),
	(12005, '유일한', '19790205', '051-111-2222', '010-4566-8886', '부산', 1, 0, 0),
	(12006, '김동수', '19811123', '02-668-8887', '010-1231-1211', '서울', 1, 0, 0),
	(12007, '배진태', '19820707', '044-500-7333', '010-7877-7777', '세종', 1, 0, 0),
	(12008, '류은수', '19830301', '062-233-1122', '010-7444-1474', '광주', 2, 0, 0),
	(12009, '김동철', '19870426', '061-877-4454', '010-8525-1235', '순천', 1, 0, 0),
	(12010, '최홍석', '19900405', '054-555-7897', '010-3214-6547', '포항', 1, 0, 0),
	(12011, '김동수', '19830108', '043-529-8457', '010-9566-4228', '제천', 2, 0, 0);

insert into bookinfo values
	(40001, 'The elements of statistical learning', 1, 3, 0, 0, 0),
	(40002, 'Computer vision : a modern approach', 2, 3, 0, 0, 0),
	(40003, 'MATLAM for engineers', 3, 3, 0, 0, 0),
	(40004, 'CUDA by example', 1, 3, 0, 0, 0),
	(40005, 'An introduction to 3D computer vision', 2, 3, 0, 0, 0),
	(40006, 'Numerical methods', 1, 3, 0, 0, 0),
	(40007, 'Image-based modeling', 2, 3, 0, 0, 0),
	(40008, 'Machine learning', 2, 3, 0, 0, 0),
	(40009, 'Probabilistic robotics', 4, 3, 0, 0, 0),
	(40010, 'Pattern recognition and machine learning', 2, 3, 0, 0, 0);
	
-- insert into rent values(1, 12001, 40001, 20210421, null, 0);
-- insert into rent values(2, 12002, 40002, 20210422, null, 0);
-- insert into rent values(3, 12003, 40003, 20210423, null, 0);