package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {SpringJavaConfig.class}) 
public class SpringJavaConfigTest {
	private Logger logger = LoggerFactory.getLogger(SpringJavaConfigTest.class);
	
	//@Autowired
	@Resource(name="")   //name=""이면 타입으로 찾아간다.
	private IRangerDao rangerDao;
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	
	@Test
	public void testRangerDao() {
	
		/***Given***/
		
		

		/***When***/
		logger.debug("ranger : {}" , rangerDao.getRangers());
		
		
		
		/***Then***/
	}
	
	@Test
	public void testgRangerService() {
		/***Given***/
		
		

		/***When***/
		logger.debug("ranger: {}", rangerService.getRangers());
		
		/***Then***/
		assertNotNull(rangerService);
	}

	/**
	 * Method : setRangerDaoEquals
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :rangerService 스프링 빈에 주입된 rangerDao 객체가 rangerDao 스프링 빈과 일치하는 지 테스트
	 */
	@Test
	public void setRangerDaoEquals() {
		/***Given***/
		

		/***When***/
		IRangerDao rangerServiceDao = rangerService.getRangerDao();
		
		
		/***Then***/
		assertEquals(rangerDao, rangerServiceDao);
		
	}
}
