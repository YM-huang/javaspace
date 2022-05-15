package mySingleton;

import java.io.FileInputStream;
import java.io.IOException;

public class LimitInstanceClass 
{ 
	static int i;//用来得到InstanceLimit.cfg指定的对象个数
	int id=0;//保存对象的编号
	boolean isBusy;//如果该变量的值为true，表示该对象正在被使用，否则该对象空闲
	String accessMessage=new String();
	
	static {
	try
	{
		FileInputStream f=new FileInputStream("D:\\download\\eclipse-java-2020-09-R-win32-x86_64\\eclipse\\eclipse-workspace\\singlemode\\src\\mySingleton\\instanceLimit.cfg");
		i=f.read()-48;
	
		System.out.println("能产生"+i+"个对象");
		f.close();
	}
	catch(IOException e){
		e.printStackTrace();
		}
	}
	
	private static LimitInstanceClass instance[]=new  LimitInstanceClass[i];
	static {
		for(int m=0;m<i;m++){
			instance[m]=new LimitInstanceClass();
			}
		}
	
	private LimitInstanceClass() 
	{
		isBusy=false;
	} 

	public void release()
	{
		isBusy=false;
	}


	public String printAccessMessage(){
		System.out.println(accessMessage);
		return accessMessage;
	}

	public void writeAccessMessage(String message){
		accessMessage=message;
	}
	
	public static LimitInstanceClass getInstance() { 
		int x=0;
		for(x=0;x<i-1;x++){
			if(instance[x].isBusy==false){
				instance[x]=new LimitInstanceClass();
				instance[x].isBusy=true;
				break;
			}
		}
		return instance[x];
	} 
}
