package fileTextGet;

import com.spire.doc.Document;

public class readWord {
  public static String getText(String path){
    Document doc = new Document();
    doc.loadFromFile(path);
    //��ȡ�ı�����ΪString
    String result = doc.getText();
    return result;
  }

  public static void main(String[] args) {
    //System.out.println(getText("src/main/resources/20162017ѧ�꣨��������ʵ������Ծ��ο��𰸣� new.docx"));
    System.out.println(getText("Hym��TM�Ǹ���¿.docx"));
  }
}
