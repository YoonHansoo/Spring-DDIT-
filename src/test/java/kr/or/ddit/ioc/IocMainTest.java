package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context.xml")   //스프링 컨테이너 설정환경을 구축
public class IocMainTest {

	//rangerDao, rangerService
	
	//DI(Dependency Injection)
	
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao;
	 
	
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao2;							//같은 이름의 bean을 2번 주입 받는다.			 
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao3;
	
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype1;

//	@Resource(name="rangerDaoPrototype")
//	private IRangerDao rangerDaoPrototype2;
	
	@Test
	public void testRangerDao() {
		//기존 방법
		//ApplicationContext context = new ...
		//DL을 통해 스프링 컨테이너에 스프링 빈을 요청
		//IRangerDao rangerDao = context.getBean("rangerDao");
		
		//변경 방법
		//스프링 컨테이너 생성을 junit runner에게 위임
		//우리가 사용하고자 하는 객체를 DI(Dependency Injection)을 통해 주입받는다.
		//@Autowired(스프링), @Resource(java 표준)
		
		//테스트 대상
		//1.스프링빈이 정상적으로 생성되고, 주입이 문제가 없는지
		
		assertNotNull(rangerDao);
	}
	
	/**
	 * Method : testSpringSingletonBean
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : 같은 이름의 스프링 빈(Scope- singleton)을 두번 주입받았을 때 해당 객체가 동일한 객체인지 비교
	 * 
	 */
	@Test
	public void testSpringSingetonBean() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals(rangerDao, rangerDao2);
			//
	}
	
	
	/**
	 * Method : testSpringSingletonBean2
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 :같은 클래스로 선언된 서로다른 스프링 빈(Singleton)이 디자인 패턴의 정의대로 두 스프링 빈이 동일한지 테스트
	 */
	@Test
	public void testSpringSingletonBean2() {
		/***Given***/

		/***When***/

		/***Then***/
//		디자인 패턴에 의하면 같은 클래스로부터 하나의 인스턴스만 존재하게 하는 패턴이 싱글톤
//		springDaoSpringBean과 rangerDao 스프링 빈은 서로 같은 RangerDaoImpl
//		클래스로부터 생성된 객체
//		디자인 패턴의 정의에 의해서 두 개의 객체는 서로 같아야 한다.

//		스프링 bean scope에서 이야기하는 singleton은 스프링 이름 단위로 객체가 생성된다.
//		rangerDaoSpringBean과 rangerDao는 같은 클래스로부터 생성되었지만
//		spring bean 이름을 서로 다르게 선언 하였기 때문에 스프링 컨테이너 내에서는 서로 다른 객체로 인식
		
		assertNotEquals(rangerDao2, rangerDao3);
	}

	
	@Test
	public void testSpringSingletonBean3() {
		
		/***Given***/
		

		/***When***/

		/***Then***/
		//assertNotEquals(rangerDaoPrototype1,rangerDaoPrototype2);
	}
}
