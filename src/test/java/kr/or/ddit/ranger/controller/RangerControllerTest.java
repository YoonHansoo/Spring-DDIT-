package kr.or.ddit.ranger.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
	



public class RangerControllerTest extends WebTestConfig {
		
		
		
	/**
	 * Method : testGetRangers
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 전체 레인저스 조회 테스트
	 */
	@Test
	public void testGetRangers() throws Exception {
		
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRangers")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		Map<String, Object> model = mav.getModel();
		List<String> rangers = (List<String>) model.get("rangers");
		List<String> boardGb =(List<String>) model.get("boardGb");
		List<String> boardGb2 =(List<String>) model.get("boardGb2");
		
		/***Then***/
		assertEquals("ranger/rangerList", viewName);
		assertNotNull(rangers);
		assertEquals(5, rangers.size());
		assertNotNull(boardGb);
		assertNotNull(boardGb2);
		assertEquals(4, boardGb.size());
		
	}
	
	
	/**
	 * Method : testGetRanger
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : ListIndex에 해당하는 레인저 이름 조회
	 */
	@Test
	public void testGetRanger() throws Exception{
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRanger").param("listIndex", "2")).andReturn();
			
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		ModelMap modelMap = mav.getModelMap();
		String ranger = (String) modelMap.get("ranger");
		List<String> boardGb = (List<String>) modelMap.get("boardGb");
		
		
		/***Then***/
		assertEquals("ranger/ranger", viewName);
		assertEquals("sally", ranger);
		assertNotNull(boardGb);
		assertEquals(4, boardGb.size());
	}
	
	@Test
	public void testGetRangersMav() throws Exception{
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRangersMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("ranger/rangerList", viewName);
		assertEquals(6, ((List<String>)mav.getModel().get("rangers")).size());
	
	}

}
