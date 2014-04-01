
public class Problem {
	String title;
	String teacher;
	String startData;
	String endData;
	String classRoom;
	String manager;
	
	
	public static Problem FromCSV(String value){
		String[] data = value.split(",");
		
		Problem obj = new Problem();
		obj.title = data[0];
		obj.teacher = data[1];
		obj.startData = data[2];
		obj.endData = data[3];
		obj.classRoom = data[4];
		obj.manager = data[5];
		
		
		return obj;
		
		
	}


	@Override
  public String toString() {
	  return title + "," + teacher + ","
	      + startData + "," + endData + "," + classRoom
	      + "," + manager;
  }
	
	
	
}
