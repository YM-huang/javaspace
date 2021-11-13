package fifth_homework;

import java.util.*;

public class test{
    public static void main(String[] args) {
    	Scanner cin =new Scanner(System.in);
		List a=new ArrayList<>();
    	while(cin.hasNext()) {
    		a.add(cin.nextInt());
  
    	}
    	int listmin=(int)a.get(0);
    	int listmax=(int)a.get(0);
    	int listmin_index=0;
    	int listmax_index=0;
    	int temp;
    	  for (int i=0; i<a.size(); i++) {
              int s = (int)a.get(i);
              System.out.println(s);
              if(s>listmax) {
            	  listmax=s;
            	  listmax_index=i;
              }
              if(s<listmin) {
            	  listmin=s;
            	  listmin_index=i;
              }
          }
    	 temp=(int)a.get(0);
     	 a.set(0, listmax);
     	 a.set(listmax_index, temp);
    	 temp=(int)a.get(a.size()-1);
     	 a.set(a.size()-1, listmin);
     	 a.set(listmin_index, temp);
    	 for (int i=0; i<a.size(); i++) {
              System.out.println(a.get(i));
         }
    	 cin.close();
      }
}