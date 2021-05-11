package entity;

/**
 * table name:  glxtpaike
 * author name: ºÚÃ¨
 * create time: 2021-04-25 00:36:04
 */ 
public class Glxtpaike{

	private Integer paikeId;
	private String paikeWeeks;
	private Integer paikeJieshu;
	private Integer paikeWeekDay;
	private String paikeCourseName;
	private String paikeTeacher;
	private Integer paikeLabId;
	private Integer paikeClassId;

	public Glxtpaike(){}
	public Glxtpaike(String paikeWeeks, Integer paikeJieshu, Integer paikeWeekDay, String paikeCourseName, String paikeTeacher, Integer paikeLabId, Integer paikeClassId) {
		this.paikeWeeks = paikeWeeks;
		this.paikeJieshu = paikeJieshu;
		this.paikeWeekDay = paikeWeekDay;
		this.paikeCourseName = paikeCourseName;
		this.paikeTeacher = paikeTeacher;
		this.paikeLabId = paikeLabId;
		this.paikeClassId = paikeClassId;
	}

	public Glxtpaike(Integer paikeId, String paikeWeeks, Integer paikeJieshu, Integer paikeWeekDay, String paikeCourseName, String paikeTeacher, Integer paikeLabId, Integer paikeClassId) {
		this.paikeId = paikeId;
		this.paikeWeeks = paikeWeeks;
		this.paikeJieshu = paikeJieshu;
		this.paikeWeekDay = paikeWeekDay;
		this.paikeCourseName = paikeCourseName;
		this.paikeTeacher = paikeTeacher;
		this.paikeLabId = paikeLabId;
		this.paikeClassId = paikeClassId;
	}

	public void setPaikeId(Integer paikeId){
		this.paikeId=paikeId;
	}
	public Integer getPaikeId(){
		return paikeId;
	}
	public void setPaikeWeeks(String paikeWeeks){
		this.paikeWeeks=paikeWeeks;
	}
	public String getPaikeWeeks(){
		return paikeWeeks;
	}
	public void setPaikeJieshu(Integer paikeJieshu){
		this.paikeJieshu=paikeJieshu;
	}
	public Integer getPaikeJieshu(){
		return paikeJieshu;
	}
	public void setPaikeWeekDay(Integer paikeWeekDay){
		this.paikeWeekDay=paikeWeekDay;
	}
	public Integer getPaikeWeekDay(){
		return paikeWeekDay;
	}
	public void setPaikeCourseName(String paikeCourseName){
		this.paikeCourseName=paikeCourseName;
	}
	public String getPaikeCourseName(){
		return paikeCourseName;
	}
	public void setPaikeTeacher(String paikeTeacher){
		this.paikeTeacher=paikeTeacher;
	}
	public String getPaikeTeacher(){
		return paikeTeacher;
	}
	public void setPaikeLabId(Integer paikeLabId){
		this.paikeLabId=paikeLabId;
	}
	public Integer getPaikeLabId(){
		return paikeLabId;
	}
	public void setPaikeClassId(Integer paikeClassId){
		this.paikeClassId=paikeClassId;
	}
	public Integer getPaikeClassId(){
		return paikeClassId;
	}
}

