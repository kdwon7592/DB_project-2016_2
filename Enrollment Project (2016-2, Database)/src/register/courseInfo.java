package register;

public class courseInfo {
	private String cname, room, stime, etime, day;
	private int cid, pid, credit;
	public courseInfo(){}
	public courseInfo(int cid, String cname, String room, String stime, String etime, int pid, int credit, String day){
		this.cid = cid;
		this.cname = cname;
		this.room = room;
		this.stime = stime;
		this.etime = etime;
		this.pid = pid;
		this.credit = credit;
		this.day = day;
	}
	public void setCid(int cid) {
        this.cid = cid;
   }
   public int getCid() {
        return cid;
   }
   public void setCnum(int credit) {
        this.credit = credit;
   }
   public int getCredit() {
        return credit;
   }
    public void setPid(int pid) {
       this.pid = pid;
    }
    public int getPid() {
       return pid;
    }
    public void setName(String cname){
    	this.cname = cname;
    }
    public String getName(){
    	return cname;
    }
    public void setRoom(String room){
    	this.room = room;
    }
    public String getRoom(){
    	return room;
    }
    public void setStime(String stime){
    	this.stime = stime;
    }
    public String getStime(){
    	return stime;
    }
    public void setEtime(String etime){
    	this.etime = etime;
    }
    public String getEtime(){
    	return etime;
    }
    public void setDay(String day){
    	this.day = day;
    }
    public String getDay(){
    	return day;
    }
}
