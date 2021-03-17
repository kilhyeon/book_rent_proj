package book_rent.dao;

import java.util.List;

import book_rent.dto.MemberInfo;

public interface MemberInfoDao {

	List<MemberInfo> selectMemberInfoByAll();
	
	int insertEmployee(MemberInfo memberinfo);
	int updateEmployee(MemberInfo memberinfo);
	int deleteEmployee(MemberInfo memberinfo);
}
