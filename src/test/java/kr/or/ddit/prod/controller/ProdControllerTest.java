package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.test.WebTestConfig;

public class ProdControllerTest extends WebTestConfig {

	/**
	 * Method : testprod
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : prod 상세 조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void testprod() throws Exception {
		mockMvc.perform(get("/prod/prod").param("lprodGu", "P101")).andExpect(status().isOk()).andExpect(view().name("prod/prodList"));
	}

}
