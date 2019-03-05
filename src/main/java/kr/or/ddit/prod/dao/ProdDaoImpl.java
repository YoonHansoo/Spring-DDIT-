package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.ProdVo;

@Repository("prodDao")
public class ProdDaoImpl implements IProdDao {

	@Override
	public List<ProdVo> selectProdInfo(String lprod_gu) {
			SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
				SqlSession openSession = sqlSessionFactory.openSession();
				
				List<ProdVo> prodlist = openSession.selectList("prod.selectProdInfo", lprod_gu);
				openSession.close();
				return prodlist;
	}

}
