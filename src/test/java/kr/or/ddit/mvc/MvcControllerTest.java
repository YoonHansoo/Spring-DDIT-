package kr.or.ddit.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.RequestBuilder;

import kr.or.ddit.test.WebTestConfig;

public class MvcControllerTest extends WebTestConfig {

	/**
	 * Method : testView
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :파일 업로드 테스트 뷰 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testView() throws Exception {
		/***Given***/
		mockMvc.perform(get("/mvc/view")).andExpect(status().isOk())
										 .andExpect(view().name("mvc/view"));
		

		/***When***/

		/***Then***/
	}

	
	/**
	 * Method : testFileupload
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :파일 업로드 테스트
	 * @throws Exception 
	 */
	@Test
	public void testFileupload() throws Exception{
		File profileFile = new File("C:\\Users\\Hansoo\\Desktop\\윤한수\\배경화면\\cat.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "cat.jpg", "image/jpg",fis);
		
		mockMvc.perform(fileUpload("/mvc/fileupload")
			             .file(file)
			             .param("userId", "brown"))
						 .andExpect(status().isOk())
						 .andExpect(view().name("mvc/view"))
		;
		}
	
}
