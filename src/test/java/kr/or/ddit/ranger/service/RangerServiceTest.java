package kr.or.ddit.ranger.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.test.LogicTestConfig;

		 			  
public class RangerServiceTest extends LogicTestConfig {

	@Resource(name="rangerService")
	private IRangerService ramgerService;
	
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;

		/***When***/
		String ranger = ramgerService.getRanger(index);

		/***Then***/
		assertEquals("brown", ranger);
	}
	
	@Test
	public void testGetRanger_overFlowIndex() {
		/***Given***/
		int index = 4;

		/***When***/
		String ranger = ramgerService.getRanger(index);

		/***Then***/
		assertEquals("james", ranger);
	}
	
	@Test
	public void testGetRanger() {
		/***Given***/
		int index = 2;

		/***When***/
		String ranger = ramgerService.getRanger(index);

		/***Then***/
		assertEquals("sally", ranger);
	}
}
