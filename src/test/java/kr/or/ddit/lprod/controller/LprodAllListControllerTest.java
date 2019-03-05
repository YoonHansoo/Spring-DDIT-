package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.test.WebTestConfig;

public class LprodAllListControllerTest extends WebTestConfig {

	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	/**
	 * Method : testlprodList
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :lprod 조회창 테스트
	 * @throws Exception 
	 */
	@Test
	public void testlprodList() throws Exception {
		mockMvc.perform(get("/lprod/lprodList")).andExpect(status().isOk()).andExpect(view().name("lprod/lprodList"));
	}
	

}
