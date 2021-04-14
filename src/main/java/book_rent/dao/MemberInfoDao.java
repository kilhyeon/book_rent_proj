package book_rent.dao;

import java.util.List;

import book_rent.dto.MemberInfo;

public interface MemberInfoDao {

	List<MemberInfo> selectMemberInfoByAll();
	
	MemberInfo selectMemberByNo(MemberInfo memberinfo);

	int insertMember(MemberInfo memberinfo);

	int updateMember(MemberInfo memberinfo);

	int deleteMember(MemberInfo memberinfo);

}
