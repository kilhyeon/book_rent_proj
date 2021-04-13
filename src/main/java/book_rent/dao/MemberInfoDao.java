package book_rent.dao;

import java.util.List;

import book_rent.dto.MemberInfo;

public interface MemberInfoDao {

	List<MemberInfo> selectMemberInfoByAll();

	int insertMembere(MemberInfo memberinfo);

	int updateMember(MemberInfo memberinfo);

	int deleteMember(MemberInfo memberinfo);

}
