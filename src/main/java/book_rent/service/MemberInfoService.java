package book_rent.service;

import java.util.List;

import book_rent.dao.MemberInfoDao;
import book_rent.dao.impl.MemberInfoDaoImpl;
import book_rent.dto.MemberInfo;

public class MemberInfoService {
	private MemberInfoDao memDao = MemberInfoDaoImpl.getInstance();
	
	public List<MemberInfo> showMemberList() {
		return memDao.selectMemberInfoByAll();
	}
	
}