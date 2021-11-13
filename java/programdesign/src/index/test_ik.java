package index;
import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import java.io.StringReader;

public class test_ik {
    //测试数据
    public static String testData="中文分词(Chinese Word Segmentation) 指的是将一个汉字序列切分成一" +
            "一个单独的词。分词就是将连续的字序列按照一定的规范重新组合成词序列的过程。";
    
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
