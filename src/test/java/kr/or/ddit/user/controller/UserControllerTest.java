package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig {

	private static final String USER_INSERT_TEST_ID = "sallyTest";
	@Resource(name="userService")
	IUserService userSerivce;
	
	@Before
	public void initData(){
		userSerivce.deleteUser(USER_INSERT_TEST_ID);
	}
	
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

	@Test
	public void testuserForm() throws Exception{
		
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("user/userForm", viewName);
	}
	
	
	/**
	 * Method : testUserForm_post
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 요청 테스트
	 */
	@Test
	public void testUserForm_post_success() throws Exception{
	 /***Given***/
		File profileFile = new File("C:\\Users\\Hansoo\\Desktop\\윤한수\\배경화면\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png",fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("redirect:/user/userPagingList"))
				.andReturn();
		
	/***When***/
		HttpSession session = mvcResult.getRequest().getSession();
		String msg = (String) session.getAttribute("msg");
		
	/***Then***/
		assertEquals("정상 등록 되었습니다.", msg);
	}
	
	/**
	 * Method : testUserForm_post_fail_duplicateUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 :중복된 아이디 값 입력 테스트
	 */
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception{
	 /***Given***/
		File profileFile = new File("C:\\Users\\Hansoo\\Desktop\\윤한수\\배경화면\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png",fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", "sally")
				.param("userNm", "샐리중복테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("user/userForm"))
				.andReturn();
		
	/***When***/
		  ModelAndView mav = mvcResult.getModelAndView();
		  Map<String, Object> model = mav.getModel();
		  String msg = (String) model.get("msg");
		
	/***Then***/
		assertEquals("중복체크에 실패 했습니다.", msg);
	}
	
	
	/**
	 * Method : testUserForm_post_fail_insertError
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록(zipcode 사이즈 sql에러)  테스트
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
	 /***Given***/
		File profileFile = new File("C:\\Users\\Hansoo\\Desktop\\윤한수\\배경화면\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png",fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34942111111111111111111111111111111111111111111111111111111")
				.param("pass", "testpass"))
				.andExpect(view().name("user/userForm"))
				.andReturn();
		
	/***Then***/
	}
}
