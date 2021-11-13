import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class A_H {

 public static void main(String[] args) {
  ArrayList<Student> list=new ArrayList<Student>();
  
  HashMap<Integer,Student> map = new HashMap<>();
  for(int i=0;i<10000000;++i) {
   Student s =new Student(i,"","");
   list.add(s);
  }
 
  for(int i=0;i<10000000;++i) {
   Student s =new Student(i,"","");
   map.put(i, s);
  }
  Student s1 =new Student(1,"","");
  Student s2 =new Student(100,"","");
  Student s3 =new Student(5000,"","");
  Student s4 =new Student(50000,"","");
  Student s5 =new Student(9000000,"","");
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
  for(Student tmp:list){
   if(tmp.num==1)
    System.out.println(tmp.num);
   }
  for(Student tmp:list){
   if(tmp.num==100)
    System.out.println(tmp.num);
   }
  for(Student tmp:list){
   if(tmp.num==5000)
    System.out.println(tmp.num);
   }
  for(Student tmp:list){
   if(tmp.num==50000)
    System.out.println(tmp.num);
   }
  for(Student tmp:list){
   if(tmp.num==9000000)
    System.out.println(tmp.num);
   }
  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  System.out.println(df1.format(new Date()));// new Date()为获取当前系统时间
   map.get(1).out();
   map.get(100).out();
   map.get(5000).out();
   map.get(50000).out();
   map.get(9000000).out();
  SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  System.out.println(df2.format(new Date()));// new Date()为获取当前系统时间
  
 }

}
class Student{
 public String last_name;
 public String first_name;
 public long num;
 public Student(long num,String last_name,String first_name) {
        this.num = num;
        this.last_name = last_name;
        this.first_name = first_name; 
    }
 public void out() {
  System.out.println(num);
 }

}
