package index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class getfilelist {

//		private static void getFilelist(File file)throws IOException{
//			File[] fs = file.listFiles();
//			try {
//				for(File f:fs){
//					if(f.isDirectory())	//����Ŀ¼����ݹ��ӡ��Ŀ¼�µ��ļ�
//						getFilelist(f);
//					if(f.isFile())		//�����ļ���ֱ�Ӵ�ӡ
//						System.out.println(f);
//				}
//			}catch(NullPointerException e){//������
//				System.out.println("Cannot read "+file.getCanonicalPath());
//			}
//		}
	
		String path;
		ArrayList<String> files=new ArrayList<String>();
		public getfilelist(String Path)throws IOException{
			path=Path.trim();
			files.add(path);
			getFileList(new File(path));
		}
  
		public ArrayList<String> getFiles(){
			return files;
		}
  
		private void getFileList(File f) throws IOException {
			String[] file_list=f.list();
			try{
				for(int i=0;i<file_list.length;i++){
					File cf=new File(f.getPath(),file_list[i]);
					if(cf.isDirectory()) {
						getFileList(cf);
					}
					if(cf.isFile()){
						files.add(cf.getCanonicalPath());
					}
				}
			}catch(NullPointerException e){//������
				System.out.println("Cannot read "+f.getCanonicalPath());
			}
		}
		
		public static void main(String[] args) {
			try {
//				String path = "D:\\javappt\\lib\\IKAnalyzer2012_u6";		//Ҫ������·��
//				File file = new File(path);		//��ȡ��file����
//				getFilelist(file);
				getfilelist f=new getfilelist("D:\\download\\eclipse-java-2020-09-R-win32-x86_64\\eclipse\\eclipse-workspace\\programdesign\\lib");
		 		  ArrayList<String> myfiles=f.getFiles();
		 		  for(String s:myfiles){
		 			  System.out.println(s.substring(s.lastIndexOf("\\")+1));
		 		  }
				
			}catch (IOException e) {
		 		  e.printStackTrace();
		 	}
		}

}
