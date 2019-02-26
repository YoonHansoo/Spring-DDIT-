package kr.or.ddit.ioc;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {
	
	private Logger logger = LoggerFactory.getLogger(CollectionBeanTest.class);
	
	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	/**
	 * Method : testPlaceholder
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :property placeholder 테스트
	 */
	@Test
	public void testPlaceholder() {
		/***Given***/
		
		logger.debug("classname :{}",dbProperties.getDriverClassName());
		logger.debug("url :{}",dbProperties.getUrl());
		logger.debug("username :{}",dbProperties.getUsername());
		logger.debug("password :{}",dbProperties.getPassword());

		/***When***/

		/***Then***/
	}

}
