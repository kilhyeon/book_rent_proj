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

	public MemberInfo showMemberByNo(MemberInfo memberinfo) {
		return memDao.selectMemberByNo(memberinfo);
	}

	public void addMember(MemberInfo memberinfo) {
		memDao.insertMember(memberinfo);
	}

	public void modifyMember(MemberInfo memberinfo) {
		memDao.updateMember(memberinfo);
	}

	public void removeMember(MemberInfo memberinfo) {
		memDao.deleteMember(memberinfo);
	}
}