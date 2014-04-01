import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProblemControl {
	public ArrayList<Problem> problemList = new ArrayList<Problem>();
	
	public void add(Problem Problem){
		problemList.add(Problem);
	}
	
	
	public void delete(int no){
		problemList.remove(no);
	}
	
	
	public void update(int no, Problem p){
		problemList.set(no, p);
	}
	
	public void save(){
		try {
	    FileWriter out = new FileWriter("problem.data");
	    for(Problem p : problemList){
	    	out.write(p.toString() + "\n");
	    }
	    out.close();
    } catch (IOException e) {
	    e.printStackTrace();
    }
	}
	
	
	public void load(){
		FileReader in;
    try {
	    in = new FileReader("problem.data");
	    Scanner sc = new Scanner(in);
	    while(true){
	    	try{
	    		problemList.add(Problem.FromCSV(sc.nextLine()));
	    	}catch(Exception e){
	    		break;
	    	}
	    }
	    sc.close();
	    in.close();
    } catch (Exception e) {
	
	    e.printStackTrace();
    }
		
		
	}
	
	
	
	
}
