package register;

public class userInfo {
	private String pw,name,major;
	private int id;
	public userInfo(){}
	public userInfo(int id, String pw, String name, String major){
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.major = major;
	}
	public userInfo(int id, String name, String major){
		this.id = id;
		this.name = name;
		this.major = major;
	}
    public void setId(int id) {
          this.id = id;
    }
    public int getId() {
          return id;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getPw() {
        return pw;
    }
    public void setName(String name){
    	this.name = name;
    }
    public String getName(){
    	return name;
    }
    public void setMajor(String major){
    	this.major = major;
    }
    public String getMajor(){
    	return major;
    }
}
