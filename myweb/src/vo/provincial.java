package vo;

public class provincial {
     private int pid;
     private String Provincial;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getProvincial() {
		return Provincial;
	}
	//构造函数
	public provincial(int pid, String provincial) {
		super();
		this.pid = pid;
		this.Provincial= provincial;
	}
	@Override
	public String toString() {
		return "provincial [pid=" + pid + ", Provincial=" + Provincial + "]";
	}
	public void setProvincial(String provincial) {
		Provincial = provincial;
	}
     
     
}
