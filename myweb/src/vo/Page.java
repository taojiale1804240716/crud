package vo;

public class Page {
     private String sort;
     private String sortOrder;
     private int pageSize;
 	 private int pageNumber;
	@Override
	public String toString() {
		return "Page [sort=" + sort + ", sortOrder=" + sortOrder + ", pageSize=" + pageSize + ", pageNumber="
				+ pageNumber + "]";
	}
	public Page(){
		super();
	}
	public Page(String sort, String sortOrder, int pageSize, int pageNumber) {
		super();
		this.sort = sort;
		this.sortOrder = sortOrder;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
    
}
