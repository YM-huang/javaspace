package forth_homework;

import java.util.*;
public class arraylist_hashmap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Student> list = new ArrayList<Student>();
		HashMap<Integer,Student> map = new HashMap<>();
		for(int i = 0;i<=10000000;i++)
		{
			list.add(new Student(i,"",""));
		}
		for(int i=0;i<10000000;++i) 
		{
			Student s =new Student(i,"","");
			map.put(i, s);
		}

		long start = System.currentTimeMillis();
		for(Student tmp:list)
		{
			   if(tmp.getID()==1)
				   	System.out.println(tmp);
			   if(tmp.getID()==100)
				    System.out.println(tmp);
			   if(tmp.getID()==5000)
				    System.out.println(tmp);
			   if(tmp.getID()==50000)
				    System.out.println(tmp);
			   if(tmp.getID()==9000000)
				    System.out.println(tmp);
		}

		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms with list");
		start = System.currentTimeMillis();
		System.out.println(map.get(1));
		System.out.println(map.get(100));
		System.out.println(map.get(5000));
		System.out.println(map.get(50000));
		System.out.println(map.get(9000000));
		
		end = System.currentTimeMillis();
		System.out.println(end - start + "ms with map");
	}
}
class Student {
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

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
}