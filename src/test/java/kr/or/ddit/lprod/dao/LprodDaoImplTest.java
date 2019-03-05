package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodDaoImplTest extends LogicTestConfig {

	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	/**
	 * Method : getAllLprod
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :DAo getAllLprod
	 */
	@Test
	public void getAllLprod()  {
		
		/***Given***/
		
		

		/***When***/
		List<LprodVo> lprodList = lprodDao.getAllLprod();

		/***Then***/
		assertEquals(12, lprodList.size());
	}
	
	
	/**
	 * Method : selectLprodPagingList
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :페이징한  lprod 리스트 조회
	 */
	@Test
	public void selectLprodPagingList()  {
		
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(10);
		
		/***When***/
		List<LprodVo> lprodList = lprodDao.selectLprodPagingList(pageVo);
		
		/***Then***/
		assertEquals(10, lprodList.size());
	}
	
	

}
