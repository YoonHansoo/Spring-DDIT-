package kr.or.ddit.ranger.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ranger.dao.IRangerDao;

@Service("rangerService")
public class RangerServiceImpl implements IRangerService {

	@Resource(name = "rangerDao") // 클래스이름(RangerServiceImpl)의 앞글자만 소문자로 바꿔서 쓰면 자동으로 됨(Default값)  ""일 때도 디폴트가 먹는 것가? 아니면 타입으로 찾아가는건가?
	IRangerDao rangerD;

	public RangerServiceImpl() {

	}

	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerD = rangerDao;
	}

	public void setRangerD(IRangerDao rangerDao) {
		this.rangerD = rangerDao;
	}

	@Override
	public List<String> getRangers() {
		return rangerD.getRangers();

	}
	

	@Override
	public String getRanger(int listindex) {
		return rangerD.getRanger(listindex);
	}

	@Override
	public IRangerDao getRangerDao() {
		return this.rangerD;
	}
}
