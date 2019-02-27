package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.model.RangerVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-type.xml")
public class PropertyEditorTest {
	private Logger logger = LoggerFactory.getLogger(PropertyEditorTest.class);
	
	@Resource(name="rangerVo")
	private RangerVo rangerVo;
	
	
	@Test
	public void testRnagrVo() {
		/***Given***/
		

		/***When***/
			String userId = rangerVo.getUserId();
			Date birth = rangerVo.getBirth();
			logger.debug("birth {}:",birth);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthStr = sdf.format(birth);

		/***Then***/
		assertNotNull(rangerVo);
		assertEquals(userId, "brown");
		assertEquals("2018-08-08", birthStr);
	}

}
