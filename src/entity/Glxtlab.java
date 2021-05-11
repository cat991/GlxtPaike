package entity;

/**
 * table name:  glxtlab
 * author name: ºÚÃ¨
 * create time: 2021-04-25 00:36:04
 */ 
public class Glxtlab{

	private Integer labId;
	private String labName;
	private String labType;
	private String labDesc;

	public Glxtlab(){}
	public Glxtlab(String labName, String labType, String labDesc) {
		this.labName = labName;
		this.labType = labType;
		this.labDesc = labDesc;
	}

	public Glxtlab(Integer labId, String labName, String labType, String labDesc) {
		this.labId = labId;
		this.labName = labName;
		this.labType = labType;
		this.labDesc = labDesc;
	}

	public void setLabId(Integer labId){
		this.labId=labId;
	}
	public Integer getLabId(){
		return labId;
	}
	public void setLabName(String labName){
		this.labName=labName;
	}
	public String getLabName(){
		return labName;
	}
	public void setLabType(String labType){
		this.labType=labType;
	}
	public String getLabType(){
		return labType;
	}
	public void setLabDesc(String labDesc){
		this.labDesc=labDesc;
	}
	public String getLabDesc(){
		return labDesc;
	}
}

