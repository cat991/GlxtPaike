package entity;

/**
 * table name:  glxtclass
 * author name: ºÚÃ¨
 * create time: 2021-04-25 00:36:04
 */
public class Glxtclass{

	public Glxtclass() {
	}

	public Glxtclass(Integer classId, String className, String classMaster, Integer classNum) {
		this.classId = classId;
		this.className = className;
		this.classMaster = classMaster;
		this.classNum = classNum;
	}

	@Override
	public String toString() {
		return "Glxtclass{" +
				"classId=" + classId +
				", className='" + className + '\'' +
				", classMaster='" + classMaster + '\'' +
				", classNum=" + classNum +
				'}';
	}

	private Integer classId;
	private String className;
	private String classMaster;
	private Integer classNum;

	public void setClassId(Integer classId){
		this.classId=classId;
	}
	public Integer getClassId(){
		return classId;
	}
	public void setClassName(String className){
		this.className=className;
	}
	public String getClassName(){
		return className;
	}
	public void setClassMaster(String classMaster){
		this.classMaster=classMaster;
	}
	public String getClassMaster(){
		return classMaster;
	}
	public void setClassNum(Integer classNum){
		this.classNum=classNum;
	}
	public Integer getClassNum(){
		return classNum;
	}
}

