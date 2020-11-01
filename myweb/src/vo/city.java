package vo;

public class city {
      private int cid;//城市编号
      private String city;//城市名称
      private int pid;//省份编号
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public city(int cid, String city, int pid) {
		super();
		this.cid = cid;
		this.city = city;
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "city [cid=" + cid + ", city=" + city + ", pid=" + pid + "]";
	}
	
}
