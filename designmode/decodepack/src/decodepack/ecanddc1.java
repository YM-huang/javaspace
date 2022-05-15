package decodepack;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ecanddc1 implements ecanddcbehavior {
	/**
	 * 将文件内容加密
	 * 使用异或的方式将a.txt加密复制出一个b.txt放到同一个文件夹下
	*/
	 public void encrypt(String file, String destFile) throws Exception {
		 FileInputStream in = null;
		 FileOutputStream out = null;
		 try {
			  in = new FileInputStream(file);
			  out = new FileOutputStream(destFile);
			  int data = 0;
			  while ((data=in.read())!=-1){
				  //将读取到的字节异或上一个数加密输出
				  out.write(data^1234);
			  }
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally {
			  //在finally中关闭开启的流
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
	  * 将文件内容解密
	  * 将使用异或的方式加密复制出的b.txt解密到c.txt放到同一个文件夹下
	  */
	  public void decrypt(String file, String dest) throws Exception {
		  FileInputStream in = null;
		  FileOutputStream out = null;
		  try {
			   in = new FileInputStream(file);
			   out = new FileOutputStream(dest);
			   int data = 0;
			   while ((data=in.read())!=-1){
				   //将读取到的字节异或上一个数加密输出
				   out.write(data^1234);
			   }
		  }catch (Exception e){
			  e.printStackTrace();
		  }finally {
			   //在finally中关闭开启的流
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
		    td.encrypt("e:/测试/cehi.txt", "e:/测试/r加密.txt"); //加密   
		    td.decrypt("e:/测试/r加密.txt", "e:/测试/r1.txt"); //解密   
		      
	  }
    
}
