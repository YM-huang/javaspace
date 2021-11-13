package fileTextGet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class readPDF {
  public static String getText(String path) {
    /**
     * @param path pdf�ļ���·��
     * @return pdf�е��ı���Ϣ����������pdf�����ʽ�����⣬�����ı���˳����ܻ���ҡ�
     */
    String result = "";
    try {
      PDDocument document = PDDocument.load(new File(path));
      PDFTextStripper stripper = new PDFTextStripper();
      stripper.setSortByPosition(true);
      for (int p = 1; p <= document.getNumberOfPages(); ++p) {
        // Set the page interval to extract. If you don't, then all pages would be extracted.
        stripper.setStartPage(p);
        stripper.setEndPage(p);
        // let the magic happen
        String text = stripper.getText(document);
        result += text;
      }
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static void main(String[] args) {
    String path="09ͼ���û�������.pdf";
    String s=getText(path);
    System.out.println(s);
  }
}
