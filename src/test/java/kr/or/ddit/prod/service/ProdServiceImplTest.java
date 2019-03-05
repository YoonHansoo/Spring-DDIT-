package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;

public class ProdServiceImplTest extends LogicTestConfig {

	@Resource(name="prodService")
	IProdService prodService;
	
		
	/**
	 * Method : getAllLprod
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : Service getAllLprod 리스트 조회 
	 */
	@Test
	public void getAllLprod() {
		/***Given***/
		String lprod_gu ="P101";
		
		/***When***/
		List<ProdVo> prodList = prodService.selectProdInfo(lprod_gu);

		
		/***Then***/
		assertEquals(6, prodList.size());
	}

}
