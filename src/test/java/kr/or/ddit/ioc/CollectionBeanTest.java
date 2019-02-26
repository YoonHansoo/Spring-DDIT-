package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-collection.xml")
public class CollectionBeanTest {
	private Logger logger = LoggerFactory.getLogger(CollectionBeanTest.class);
	
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	 * Method : testCollectionBean
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :스프링 설정파일을 통해 컬렉션 객체를 생성하고, 주입받는다.
	 * 				list,set,map,properties
	 */
	@Test
	public void testCollectionBean() {
		/***Given***/
		
		

		/***When***/
		
		List<String> list = collectionBean.getList();
		Set<String> set = collectionBean.getSet();
		Map<String,String> map = collectionBean.getMap();
		Properties properties = collectionBean.getProperties();
		for(String str : list) {
			logger.debug("list : {}", str);
		}
		
		for(String str : set) {
			logger.debug("set : {}", str);
		}
		
		for(Entry<String, String> entry : map.entrySet()) {
			logger.debug("entry : {} {}", entry.getKey(), entry.getValue());
		}
		
		for(Object entryprop : properties.keySet()) {
			logger.debug("entryprop : {}", (String)entryprop);
		}
		

		/***Then***/
		assertNotNull(list);
		assertEquals(3, list.size());
		assertEquals(3, set.size());
		assertEquals(3, map.size());
		assertEquals(3, properties.size());
	}

}
