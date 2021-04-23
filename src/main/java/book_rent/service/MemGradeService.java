package book_rent.service;

import java.util.List;

import book_rent.dao.MemGradeDao;
import book_rent.dao.impl.MemGradeDaoImpl;
import book_rent.dto.MemGrade;

public class MemGradeService {
	private MemGradeDao gradeDao = MemGradeDaoImpl.getInstance();
	
	public List<MemGrade> showMemGradeByAll(){
		return gradeDao.selectMemGradeByAll();
	}
}
