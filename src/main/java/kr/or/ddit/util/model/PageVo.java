package kr.or.ddit.util.model;

public class PageVo {
	private int page; //페이지 번호
	private int pageSize; //페이지당 사이즈
	public int getPage() {
		return page;
	}
	
	public PageVo() {
		super();
	}
	
	public PageVo(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
