package book_rent.dao;

import static org.junit.Assert.fail;

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
	}

	@Test
	public void testSelectMemberInfoByAll() {
		System.out.printf("%s()%n", "testSelectMemberInfoByAll");
		List<MemberInfo> memberinfoList = dao.selectMemberInfoByAll();
		Assert.assertNotNull(memberinfoList);
		for (MemberInfo m : memberinfoList) {
			System.out.println(m);
		}
	}

	@Test
	public void testInsertEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
