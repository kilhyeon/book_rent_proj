package book_rent.dao;

import java.util.List;

import book_rent.dto.MemberInfo;

public interface MemberInfoDao {

	List<MemberInfo> selectMemberInfoByAll();

	List<MemberInfo> selectMemberInfoByNo(MemberInfo memberinfo);

	List<MemberInfo> selectMemberInfoByName(MemberInfo memberinfo);

	List<MemberInfo> selectMemberInfoByBirth(MemberInfo memberinfo);

	List<MemberInfo> selectMemberInfoByCp(MemberInfo memberinfo);

	List<MemberInfo> selectMemberInfoByGrade(MemberInfo memberinfo);
	
	MemberInfo selectMeberInfoBymemNo(int memNo);

	int insertMember(MemberInfo memberinfo);

	int updateMember(MemberInfo memberinfo);

	int deleteMember(MemberInfo memberinfo);

}
