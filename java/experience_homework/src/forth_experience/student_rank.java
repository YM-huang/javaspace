package forth_experience;
import java.util.ArrayList;  
import java.util.Collections;
import java.util.Scanner;

public class student_rank {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);

		ArrayList<Student> list = new ArrayList<Student>();

		while(cin.hasNext())
		{
			list.add(new Student(cin.nextLong(),cin.next(),cin.next()));
		}
		Collections.sort(list);
//		System.out.println(list);
		for(int j = 0; j <list.size();j++) 
		{
			System.out.print(list.get(j)+"\n");
		}
		
	}

}

class Student implements Comparable{
	private String surname; 
	private String givenname;
    private long ID;  

    public Student(long ID, String surname ,String givenname){  
        this.surname = surname; 
        this.givenname = givenname; 
        this.ID = ID;  
    }   
  
    public String toString(){  
        return this.ID + " " + this.surname +" "+this.givenname;  
    }
    
    public int compareTo(Object o){  
        Student s = (Student)(o);  
        return (int)this.ID - (int)s.ID;  
    } 
}
