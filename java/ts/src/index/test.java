package index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

public class test {
  public static void main(String[] args) throws IOException {
    Analyzer analyzer=new IKAnalyzer(); //ʹ��IK�ִ���������
    TokenStream tokenStream=analyzer.tokenStream("fileName", "ɵ");
    tokenStream.reset();
    OffsetAttribute offsetAttribute=tokenStream.addAttribute(OffsetAttribute.class);
    CharTermAttribute charTermAttribute=tokenStream.addAttribute(CharTermAttribute.class);
    while(tokenStream.incrementToken()) {
      System.out.println("��ʼ������"+offsetAttribute.startOffset()+"----------------��������:"+offsetAttribute.endOffset());
      System.out.println(charTermAttribute);
      System.out.println("------------------");
    }
  }
}