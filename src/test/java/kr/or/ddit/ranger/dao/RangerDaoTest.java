package kr.or.ddit.ranger.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.test.LogicTestConfig;

public class RangerDaoTest extends LogicTestConfig {

	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	
	
//데이터 초기화 하는 법
	@Before
	public void setUpup(){
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource("kr/or/ddit./config/db/init.sql"));
		rdp.setContinueOnError(false);
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	
	
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;

		/***When***/
		String ranger = rangerDao.getRanger(index);

		/***Then***/
		assertEquals("brown", ranger);
	}
	
	@Test
	public void testGetRanger_overFlowIndex() {
		/***Given***/
		int index = 4;

		/***When***/
		String ranger = rangerDao.getRanger(index);

		/***Then***/
		assertEquals("james", ranger);
	}
	
	@Test
	public void testGetRanger() {
		/***Given***/
		int index = 2;

		/***When***/
		String ranger = rangerDao.getRanger(index);

		/***Then***/
		assertEquals("sally", ranger);
	}
	
	/**
	 * Method : testGetRangerDb
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : 전체 레인저스 조회(db)
	 */
	@Test
	public void testGetRangerDb(){
		/***Given***/
		

		/***When***/
		List<Map<String, String>> rangerList = rangerDao.getRangerDb();
		
		/***Then***/
		assertEquals(5, rangerList.size());
	}
	
	@Test
	public void testGetRangerId(){
		/***Given***/
		

		/***When***/
		Map<String, String> ranger = rangerDao.getRanger("brown");
		
		/***Then***/
		assertNotNull(ranger);
		assertEquals("브라운", ranger.get("NAME"));
	}
	
	@Test
	public void testInsertRanger(){
		/***Given***/
		Map<String, String> ranger = new HashMap<>();
		ranger.put("id", "ryon");
		ranger.put("name", "라이언");

		/***When***/
		int insertCnt = rangerDao.insertRanger(ranger);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void testDeleteRanger(){
		/***Given***/
		rangerDao.DeleteRangerDept("brown");

		/***When***/
		int deleteCnt = rangerDao.deleteRanger("brown");

		/***Then***/
		assertEquals(1, deleteCnt);
	}
}
