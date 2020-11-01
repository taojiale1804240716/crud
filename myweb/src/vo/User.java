package vo;

public class User {
	  private String userName;
      private String password;
      private String chrName;
      private String role;
     
	  private String provincial;
      private String city;
      private String email;
     
     
     public User() {
  		super();
  		
  	}
      
	public User(String chrName,String userName, String provincial, String email) {
		super();
		this.userName = userName;
		this.chrName = chrName;
		this.provincial = provincial;
		this.email = email;
	}
//	public User(String userName, String password, String chrName, String role) {
//		super();
//		this.userName = userName;
//		this.password = password;
//		this.chrName = chrName;
//		this.role = role;
//	}
	public User(String role,String userName, String password, String chrName,String provincial,String city,String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.chrName = chrName;
		this.role = role;
		this.provincial=provincial;
		this.city=city;
		this.email=email;
	}
	public User(String userName, String password, String chrName,String provincial,String city,String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.chrName = chrName;
		this.provincial=provincial;
		this.city=city;
		this.email=email;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", chrName=" + chrName + ", role=" + role
				+ "]";
	}
	 public void setRole(String role) {
			this.role = role;
		}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getChrName() {
		return chrName;
	}
	public void setChrName(String chrName) {
		this.chrName = chrName;
	}
	public String getRole() {
		return role;
	}
	public void setprovincial(String provincial) {
		this.provincial = provincial;
	}
	public String getprovincial() {
		return provincial;
	}
	public void setcity(String city) {
		this.city = city;
	}
	public String getcity() {
		return city;
	}
	public void setemial(String emial) {
		this.email =emial;
	}
	public String getemial() {
		return email;
	}
}
