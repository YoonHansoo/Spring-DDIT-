package kr.or.ddit.user.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserDaoTest extends LogicTestConfig {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Test
	public void testgetAllUser() {
		/***Given***/
		

		/***When***/
		List<UserVo> userList = userDao.gertAllUser();

		/***Then***/
		assertTrue(userList.size() > 100);
		
	}

}
