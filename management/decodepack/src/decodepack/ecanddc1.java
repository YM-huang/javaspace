package decodepack;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ecanddc1 implements ecanddcbehavior {
	/**
	 * ���ļ����ݼ���
	 * ʹ�����ķ�ʽ��a.txt���ܸ��Ƴ�һ��b.txt�ŵ�ͬһ���ļ�����
	*/
	 public void encrypt(String file, String destFile) throws Exception {
		 FileInputStream in = null;
		 FileOutputStream out = null;
		 try {
			  in = new FileInputStream(file);
			  out = new FileOutputStream(destFile);
			  int data = 0;
			  while ((data=in.read())!=-1){
				  //����ȡ�����ֽ������һ�����������
				  out.write(data^1234);
			  }
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally {
			  //��finally�йرտ�������
			  if (in!=null){
				  try {
					  in.close();
				  } catch (IOException e) {
					  e.printStackTrace();
				  }
			  }
			  if (out!=null){
				  try {
					  out.close();
				  } catch (IOException e) {
					  e.printStackTrace();
				  }
			  }
			  File filet = new File(file);
			  filet.delete();
		 }
	 }
	 
	 /**
	  * ���ļ����ݽ���
	  * ��ʹ�����ķ�ʽ���ܸ��Ƴ���b.txt���ܵ�c.txt�ŵ�ͬһ���ļ�����
	  */
	  public void decrypt(String file, String dest) throws Exception {
		  FileInputStream in = null;
		  FileOutputStream out = null;
		  try {
			   in = new FileInputStream(file);
			   out = new FileOutputStream(dest);
			   int data = 0;
			   while ((data=in.read())!=-1){
				   //����ȡ�����ֽ������һ�����������
				   out.write(data^1234);
			   }
		  }catch (Exception e){
			  e.printStackTrace();
		  }finally {
			   //��finally�йرտ�������
			   if (in!=null){
				   try {
					   in.close();
				   } catch (IOException e) {
					   e.printStackTrace();
				   }
			   }
			   if (out!=null){
				   try {
					   out.close();
				   } catch (IOException e) {
					   e.printStackTrace();
				   }	
			   }
		  }
	  }
	  
	  public static void main(String[] args) throws Exception {   
		  ecanddc1 td = new ecanddc1();   
		    td.encrypt("e:/����/cehi.txt", "e:/����/r����.txt"); //����   
		    td.decrypt("e:/����/r����.txt", "e:/����/r1.txt"); //����   
		      
	  }
    
}
