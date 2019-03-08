package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public class UserDaoImplTest extends LogicTestConfig {
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	
	@Before
	public void setup() {
		userDao.deleteUser("test");
	}
	
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void gertAllUserTest() {
		/*** Given ***/
		
		/*** When ***/
		
		List<UserVo> list = userDao.gertAllUser();
		
		for(UserVo userVo : list)
			System.out.println(userVo);
		
		
		/*** Then ***/
		assertNotNull(list);
		//assertEquals(5, list.size());
		
	}
	
	
	@Test
	public void 특정사용자_조회쿼리() {
		/***Given***/
		
		/***When***/
		
		UserVo user  = userDao.selectUser("cony");
		
		System.out.println(user.getUserId());

		/*** Then ***/
		assertNotNull(user);
	}
	
	/**
	 * Method : testselectUserPagingList
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :사용자 페이징 리스트 조회 테스트
	 */
	
	@Test
	public void testselectUserPagingList() {
		/***Given***/
		PageVo pageVo = new PageVo(1,10);

		/***When***/
		List<UserVo> userList = userDao.selectUserPagingList(pageVo);
		
		/***Then***/
		//assertNotNull(userList);
		assertEquals(10,userList.size());
	}
	
	/**
	 * Method : testGetUserCnt
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :전체 사용자 수 조회
	 */
	@Test
	public void testGetUserCnt() {
		/***Given***/
		

		/***When***/
		int userCnt = userDao.getUserCnt();
		
		/***Then***/
		assertEquals(105, userCnt);
	}
	@Test
	public void testPagination() {
		/***Given***/
		int userCnt = 105;
		int userSize = 10;

		/***When***/
		int lastPage =  userCnt/userSize +  (userCnt%userSize > 0? 1 : 0);
		
		/***Then***/
			assertEquals(11,lastPage);	
	}
	@Test
	public void testPagination2() {
		/***Given***/
		int userCnt = 110;
		int useSize = 10;
		
		/***When***/
		int lastPage =  userCnt/useSize +  (userCnt%useSize > 0? 1 : 0);
		
		/***Then***/
		assertEquals(11,lastPage);	
	}
	@Test
	public void testPagination3() {
		/***Given***/
		int userCnt = 0;
		int pageSize = 10;
		
		/***When***/
		//(totalCount + (pageSize - 1)) / pageSize;
		
		int lastPage =  userCnt/pageSize +  (userCnt%pageSize > 0? 1 : 0);
		
		int lastPage1 = (userCnt +(pageSize-1)) / pageSize;
		
		/***Then***/
		assertEquals(0,lastPage1);	
	}
	@Test
	public void testPagination4() {
		/***Given***/
		int userCnt = 4;
		int useSize = 10;
		
		/***When***/
		int lastPage =  userCnt/useSize +  (userCnt%useSize > 0? 1 : 0);
		
		/***Then***/
		assertEquals(1,lastPage);	
	}
	
	@Test
	public void testinsertUser() {
		/***Given***/
		UserVo vo = new UserVo();
		vo.setUserId("tes123");
		vo.setUserNm("테스트");
		vo.setAlias("별명");
		vo.setAddr1("대전 중구 대흥로 76");
		vo.setAddr2("2층 ddit");
		vo.setZipcode("34942");
		vo.setPass("testpass");
		vo.setFilename("testsdfa");
		vo.setRealFilename("dfsdfs");
		
		

		/***When***/
		 int userCnt= userDao.insertUser(vo);
		
		/***Then***/
		assertEquals(1, userCnt);
	}

	@Test
	public void 유저업데이트() {
		
		/***Given***/
		UserVo vo = new UserVo();
		vo.setUserId("brown");
		vo.setUserNm("테스트");
		vo.setAlias("별명");
		vo.setAddr1("대전 중구 대흥로 76");
		vo.setAddr2("2층 ddit");
		vo.setZipcode("34942");
		vo.setPass("testpass");

		/***When***/
		 int userCnt= userDao.updateUser(vo);
		
		/***Then***/
			assertEquals(1, userCnt);
	}

}
