package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodDao {

	/**
	 * Method : getAllLprod
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 lprod 수 조회
	 */
	
	public List<LprodVo> getAllLprod();
	
	/**
	 * Method : getUserCnt
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 lprod 수 조회
	 */
	int getLprodCnt();
	
	List<LprodVo> selectLprodPagingList(PageVo pageVo);
}
