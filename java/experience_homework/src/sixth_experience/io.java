package sixth_experience;
import java.io.*;
import java.util.Arrays;
public class io {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String srcFile = "./stulist.txt";//Դ�ļ�
			String desFile = "./stusort.txt";//Ŀ���ļ�
			LineNumberReader lnr = new LineNumberReader(new FileReader(srcFile));
			lnr.skip(Long.MAX_VALUE);
			System.out.println(lnr.getLineNumber() + 1);
			BufferedReader bufReader = new BufferedReader(new FileReader(srcFile));
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(desFile));
			String strLine[] = new String[lnr.getLineNumber() + 1];//��������ÿ�ζ�ȡ��һ��
			int t = 0;
			while(bufReader.ready())
			{
				strLine[t] = bufReader.readLine();
				t++;
			}
			Arrays.sort(strLine);//��������
			for(int i = 0;i <= lnr.getLineNumber();i++)
			{
				bufWriter.write(strLine[i]);
				bufWriter.newLine();
				bufWriter.flush();//ˢ�¸����Ļ��壬�������������Ŀ��
				System.out.println(strLine[i]);
			}
			lnr.close();
			bufReader.close();
			bufWriter.close();

		}catch(IOException ie) {
			System.out.println(ie);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
