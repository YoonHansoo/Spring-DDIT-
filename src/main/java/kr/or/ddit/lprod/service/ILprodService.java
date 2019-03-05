package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodService {
	List<LprodVo> getAllLprod();
	
	int getLprodCnt();
	
	Map<String,Object> selectLprodPagingList(PageVo pageVo);
	
}
