package book_rent.dao;

import java.util.List;

import book_rent.dto.MemGrade;

public interface MemGradeDao {
	List<MemGrade> selectMemGradeByAll();
	List<MemGrade> selectMemGradeByNo(MemGrade grade);
	List<MemGrade> selectMemGradeByName(MemGrade grade);
	
	int insertMemGrade(MemGrade grade);
	int updateMemGrade(MemGrade grade);
	int deleteMemGrade(MemGrade grade);
}
