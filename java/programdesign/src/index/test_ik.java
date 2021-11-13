package index;
import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import java.io.StringReader;

public class test_ik {
    //��������
    public static String testData="���ķִ�(Chinese Word Segmentation) ָ���ǽ�һ�����������зֳ�һ" +
            "һ�������Ĵʡ��ִʾ��ǽ������������а���һ���Ĺ淶������ϳɴ����еĹ��̡�";
    
    public static Analyzer getIKAnalyzer(){
        return  new IKAnalyzer();
    }
    public static void TestIKAnalyzer()throws Exception{
    	Analyzer analyzer =getIKAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", new StringReader(testData));
        tokenStream.reset();
        while (tokenStream.incrementToken()){
        	CharTermAttribute c = tokenStream.addAttribute(CharTermAttribute.class);
        	System.out.println(c);
        	
        }

    }
    public static void main(String[] args) throws Exception{
    	TestIKAnalyzer();
    }
}
