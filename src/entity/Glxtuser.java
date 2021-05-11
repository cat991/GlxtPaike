package entity;

/**
 * table name:  glxtuser
 * author name: 黑猫
 * create time: 2021-04-25 00:36:04
 */ 
public class Glxtuser{

	private Integer userId;
	private String userName;
	private String userPwd;
	private String userType;
	private String userStatus;

	public Glxtuser() {
	}

	public Glxtuser(String userName, String userPwd) {
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public Glxtuser(Integer userId, String userName, String userPwd, String userType, String userStatus) {
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "Glxtuser{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userPwd='" + userPwd + '\'' +
				", userType='" + userType + '\'' +
				", userStatus='" + userStatus + '\'' +
				'}';
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserPwd(String userPwd){
		this.userPwd=userPwd;
	}
	public String getUserPwd(){
		return userPwd;
	}
	public void setUserType(String userType){
		this.userType=userType;
	}
	public String getUserType(){
		return userType;
	}
	public void setUserStatus(String userStatus){
		this.userStatus=userStatus;
	}
	public String getUserStatus(){
		return userStatus;
	}
}

