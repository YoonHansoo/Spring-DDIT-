package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodServiceImplTest extends LogicTestConfig {

	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	/**
	 * Method : getAllLprod
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :lprodService getAllLprod
	 */
	@Test
	public void getAllLprod()  {
		
		/***Given***/
		
		

		/***When***/
		List<LprodVo> lprodList = lprodService.getAllLprod();

		/***Then***/
		assertEquals(12, lprodList.size());
	}
	
	
	/**
	 * Method : selectLprodPagingList
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : lprodService 페이징한  lprod 리스트 조회
	 */
	@Test
	public void selectLprodPagingList()  {
		
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(10);
		
		/***When***/
		 Map<String, Object> selectLprodPagingList = lprodService.selectLprodPagingList(pageVo);
		 List<LprodVo> lprodList = (List<LprodVo>) selectLprodPagingList.get("lprodList");
		/***Then***/
		assertEquals(10, lprodList.size());
	}
}