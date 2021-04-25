package book_rent.service;

import java.util.List;

import book_rent.dao.MemGradeDao;
import book_rent.dao.impl.MemGradeDaoImpl;
import book_rent.dto.MemGrade;

public class MemGradeService {
	private MemGradeDao gradeDao = MemGradeDaoImpl.getInstance();

	public List<MemGrade> showMemGradeByAll() {
		return gradeDao.selectMemGradeByAll();
	}

	public void addGrade(MemGrade grade) {
		gradeDao.insertMemGrade(grade);
	}

	public void modifyGrade(MemGrade grade) {
		gradeDao.updateMemGrade(grade);
	}

	public void removeGrade(MemGrade grade) {
		gradeDao.deleteMemGrade(grade);
	}
}
