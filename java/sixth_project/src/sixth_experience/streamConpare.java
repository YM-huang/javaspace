package sixth_experience;
import java.io.*;
public class streamConpare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String File1 = "./list1.txt";//DataOutputStream
			String File2 = "./list2.txt";//OutputStreamWriter
			DataOutputStream dos  = new DataOutputStream(new  FileOutputStream(File1));
			OutputStreamWriter osw  = new OutputStreamWriter(new  FileOutputStream(File2));
			for(int i = 0 ; i <=50000000 ; i++)
			{
				dos.writeDouble(i);
				dos.flush();
		
				osw.write(i);
				osw.flush();
			}
			DataInputStream dis  = new DataInputStream(new  FileInputStream(File1));
			InputStreamReader osr  = new InputStreamReader(new  FileInputStream(File2));
			
			long start1 = System.currentTimeMillis();
			for(int i = 0 ; i <=50000000 ; i++)
			{
				dis.readDouble();
			}
			long end1 = System.currentTimeMillis();
			System.out.println(end1 - start1 + "ms with DataInputStream");
			
			long start2 = System.currentTimeMillis();
			for(int i = 0 ; i <=50000000 ; i++)
			{
				osr.read();
			}
			long end2 = System.currentTimeMillis();
			System.out.println(end2 - start2 + "ms with InputStreamReader");
			
			dos.close();
			osw.close();
			dis.close();
			osr.close();
			
		}catch(IOException ie) {
			System.out.println(ie);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
