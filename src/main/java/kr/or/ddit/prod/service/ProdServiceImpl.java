package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVo;

@Service("prodService")
public class ProdServiceImpl implements IProdService  {

	@Resource(name="prodDao")
	private IProdDao prodDao;
	
	/**
	 * Method : selectProdInfo
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param lprod_gu
	 * @return
	 * Method 설명 :prod의 정보를 조회하는 메서드
	 */
	@Override
	public List<ProdVo>  selectProdInfo(String lprod_gu) {
		return prodDao.selectProdInfo(lprod_gu);
	}

}
