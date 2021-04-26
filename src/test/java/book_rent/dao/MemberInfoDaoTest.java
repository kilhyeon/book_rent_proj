package book_rent.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import book_rent.dao.impl.MemberInfoDaoImpl;
import book_rent.dto.MemberInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberInfoDaoTest {

	private static MemberInfoDao dao = MemberInfoDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectMemberInfoByAll() {
		System.out.printf("%s()%n", "testSelectMemberInfoByAll");
		List<MemberInfo> memberinfoList = dao.selectMemberInfoByAll();
		Assert.assertNotNull(memberinfoList);
		for (MemberInfo m : memberinfoList) {
			System.out.println(memberinfoList);
		}
	}

	@Test
	public void test05SelectMemberInfoByNo() {
		System.out.printf("%s()%n", "testSelectMemberInfoByNo");
		MemberInfo member = new MemberInfo(12002);
		List<MemberInfo> searchMemberList = dao.selectMemberInfoByNo(member);
		Assert.assertNotNull(searchMemberList);
		System.out.println(searchMemberList);
		searchMemberList.stream().forEach(System.out::println);
	}
	
	@Test
	public void test06SelectMemberInfoByGrade() {
		System.out.printf("%s()%n", "testSelectMemberInfoByGrade");
//		MemGradeNo member = new MemGradeNo(1);
		MemberInfo member = new MemberInfo(1);

		List<MemberInfo> searchMemberList = dao.selectMemberInfoByGrade(member);
		Assert.assertNotNull(searchMemberList);
		System.out.println(searchMemberList);
		searchMemberList.stream().forEach(System.out::println);
	}
	

//	@Test
//	public void test01InsertEmployee() {
//		System.out.printf("%s()%n", "testInsertEmployee");
//		MemberInfo newMember = new MemberInfo(19998, "홍길동", "20010101", "010-1234-1234", "010-1234-1234", "대구", 1);
//		int res = dao.insertMember(newMember);
//		Assert.assertEquals(1, res);
//
//	}
//
//	@Test
//	public void test02UpdateEmployee() {
//		System.out.printf("%s()%n", "testInsertEmployee");
//		MemberInfo newMember = new MemberInfo(19998, "홍길동2", "20010101", "010-1234-1234", "010-1234-1234", "대구",
//				1);
//		int res = dao.updateMember(newMember);
//		Assert.assertEquals(1, res);
//	}

//	@Test
	public void test03DeleteEmployee() {
		System.out.printf("%s()%n", "testDeleteEmployee()");
		MemberInfo delMember = new MemberInfo(19998);
		int res = dao.deleteMember(delMember);
		Assert.assertEquals(1, res);
	}

}
