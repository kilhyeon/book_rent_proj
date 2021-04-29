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

	public List<MemberInfo> showMemberRank() {
		return memDao.selectMemberRank();
	}

	public List<MemberInfo> showMemberListByNo(MemberInfo memberinfo) {
		return memDao.selectMemberInfoByNo(memberinfo);
	}

	public List<MemberInfo> showMemberListByName(MemberInfo memberinfo) {
		return memDao.selectMemberInfoByName(memberinfo);
	}

	public List<MemberInfo> showMemberListByBirth(MemberInfo memberinfo) {
		return memDao.selectMemberInfoByBirth(memberinfo);
	}

	public List<MemberInfo> showMemberListByCp(MemberInfo memberinfo) {
		return memDao.selectMemberInfoByCp(memberinfo);
	}

	public List<MemberInfo> showMemberListByGrade(MemberInfo memberinfo) {
		return memDao.selectMemberInfoByGrade(memberinfo);

	}

	public MemberInfo showMemberByMemNo(int memNo) {
		return memDao.selectMeberInfoByMemNo(memNo);
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