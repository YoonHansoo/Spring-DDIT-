package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig {

	
	
	/**
	 * Method : testUserController
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :사용자 전체 조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserController() throws Exception {
		
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList= (List<UserVo>) mav.getModel().get("userList");
		
		/***Then***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size()>100);
		
		
	}
	
	@Test
	public void testuserPagingList() throws Exception{
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		List<UserVo> userList=  (List<UserVo>) mav.getModel().get("userList");
		//Map<String, Object> map=  (Map<String, Object>) mav.getModel().get("resultMap");
		int pageSize = (int) mav.getModel().get("pageSize");
		int page = (int) mav.getModel().get("page");
		
		assertEquals(10, pageSize);
		assertEquals(1, page);
		assertEquals(10, userList.size());
	}
	
	@Test
	public void testUser() throws Exception{
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModel().get("userVo");
		
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertNotNull(userVo);
		assertEquals("brown", userVo.getUserId());
	}
	
/*	@Test
	public void testProfileImg(){
		
	}*/

}
