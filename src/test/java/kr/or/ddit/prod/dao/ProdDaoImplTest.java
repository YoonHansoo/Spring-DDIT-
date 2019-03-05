package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;

public class ProdDaoImplTest extends LogicTestConfig {

	@Resource(name="prodDao")
	IProdDao proddao;
	
	/**
	 * Method : getAllLprod
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : Service selectProdInfo 리스트 조회
	 */
	@Test
	public void selectProdInfo() {
		/*** Given ***/
		/*** When ***/
		
		List<ProdVo> list = proddao.selectProdInfo("P101");
		
		for(ProdVo ProdVo : list)
			System.out.println(ProdVo);
		
		
		/*** Then ***/
		assertNotNull(list);
		assertEquals(6, list.size());
		
	}

}
