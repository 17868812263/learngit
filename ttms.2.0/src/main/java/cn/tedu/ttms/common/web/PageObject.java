package cn.tedu.ttms.common.web;

import java.io.Serializable;

/*
 * 借助此对象封装分页信息
 * 1）当前页
 * 2）记录数
 * 3）页数
 * 4）
*/
public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;
	
	//当前页
	private int pageCurrent=1;
	//记录数
	private int rowCount;
	//总页数
	private int pageCount;
	//每页显示的记录数
	private int pageSize=2;
	//取下页数据的起始记录
	private int startIndex;
	
	//总页数
	public int getPageCount(){
		int pages=rowCount/pageSize;
		//如果多出来而且不够每页记录数加一页
		if(rowCount%pageSize!=0){
			pages+=1;
		}
		return pages;
	}
	//根据当前页计算当前页记录的开始位置
	public int getStartIndex() {
		//即当前页之前一页乘以每页记录数
		return (pageCurrent-1)*pageSize;
	}
	
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "PageObject [pageCurrent=" + pageCurrent + "]";
	}
    
	

}














