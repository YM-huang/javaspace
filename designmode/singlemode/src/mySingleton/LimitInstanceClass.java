package mySingleton;

import java.io.FileInputStream;
import java.io.IOException;

public class LimitInstanceClass 
{ 
	static int i;//�����õ�InstanceLimit.cfgָ���Ķ������
	int id=0;//�������ı��
	boolean isBusy;//����ñ�����ֵΪtrue����ʾ�ö������ڱ�ʹ�ã�����ö������
	String accessMessage=new String();
	
	static {
	try
	{
		FileInputStream f=new FileInputStream("D:\\download\\eclipse-java-2020-09-R-win32-x86_64\\eclipse\\eclipse-workspace\\singlemode\\src\\mySingleton\\instanceLimit.cfg");
		i=f.read()-48;
	
		System.out.println("�ܲ���"+i+"������");
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
