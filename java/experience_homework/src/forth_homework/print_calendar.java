package forth_homework;

import java.util.Calendar;
public class print_calendar {

	public static void main(String[] args) {
		  Calendar c=Calendar.getInstance();
		  c.set(2020,9-1,27);
		  int i=1;
		  int t=2;
		  while(c.get(Calendar.MONTH)!=1)
		  {
			  int month=c.get(Calendar.MONTH)+1;
			  int date=c.get(Calendar.DATE);
			  int day=c.get(Calendar.DAY_OF_WEEK);
			  if(month==10&&date==10)
			  {
				  if(i<10)
				  {
					  System.out.print("0"+i+" ");
				  }
				  else
				  {
					  System.out.print(i+" ");
				  }
				  if(month>=1&&month<=9)
				  {
					  if(date>=1&&date<=9)
					  {  
						  System.out.print("0"+month+"��"+"0"+date+"��"+" ");
					  }
					  else 			
					  {  
						  System.out.print("0"+month+"��"+date+"��"+" ");
					  }
				  }
				  else
				  {
					  if(date>=1&&date<=9)
					  {  
						  System.out.print(month+"��"+"0"+date+"��"+" ");
					  }
					  else 			
					  {  
						  System.out.print(month+"��"+date+"��"+" ");
					  }
				  }
				  System.out.println("������(1,2)��1���ϻ��������Ŀ�");
			  }
			  if(day==2||day==4) 
			  {
				  if(i<10)
				  {
					  System.out.print("0"+i+" ");
				  }
				  else
				  {
					  System.out.print(i+" ");
				  }
				  if(month>=1&&month<=9)
				  {
					  if(date>=1&&date<=9)
					  {  
						  System.out.print("0"+month+"��"+"0"+date+"��"+" ");
					  }
					  else 			
					  {  
						  System.out.print("0"+month+"��"+date+"��"+" ");
					  }
				  }
				  else
				  {
					  if(date>=1&&date<=9)
					  {  
						  System.out.print(month+"��"+"0"+date+"��"+" ");
					  }
					  else 			
					  {  
						  System.out.print(month+"��"+date+"��"+" ");
					  }
				  }
				  if(month==10&&date==19 || month==10&&date==28 || month==11&&date==4 || month==11&&date==11 || month==11&&date==23 || month==12&&date==21 || month==1&&date==4)
				  {	
					  if(day==2) 
					  {		
						  System.out.println("����һ(6,7)��"+t+"���ϻ�");
						  t++;
					  }
					  else 
					  {
						  System.out.println("������(1,2)��"+t+"���ϻ�");
						  t++;
					  }
					  i++;
				  }
				  else
				  {
					  if(day==2) 
					  {		
						  System.out.println("����һ(6,7)�����Ͽ�");
					  }
					  else 
					  {
						  System.out.println("������(1,2)�����Ͽ�");
					  }
					  i++;
				  }
			  }
			  c.add(Calendar.DATE,1);
		  }
	}  
}
