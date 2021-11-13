package index;

import fileTextGet.getText;
import indexGet.fileList;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.swing.text.Highlighter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class fileIndex {
  String path,index="index",text="text";
  Map<String, String> searchPaths=new HashMap<>();
  private Document document;
  private Directory directory;
  private Analyzer analyzer;
  private IndexWriterConfig indexWriterConfig;
  private IndexWriter indexWriter;
  private IndexSearcher indexSearcher;
  private IndexReader reader;
  public fileIndex(String Path)  {
    path=Path;
    document=new Document();
    try {
      directory=FSDirectory.open(Paths.get(path));
      analyzer=new IKAnalyzer();
      indexWriterConfig=new IndexWriterConfig(analyzer);
      indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
      indexWriter=new IndexWriter(directory, indexWriterConfig);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void fileClose()throws IOException{
    indexWriter.close();
  }
  public void addData(String dataIndex, String dataText) throws IOException{
    document=new Document();
    document.add(new TextField(index,dataIndex, Field.Store.YES));
    document.add(new TextField(text,dataText, Field.Store.YES));
    indexWriter.addDocument(document);
  }
  void readFileIndex(String item) throws IOException{
    // ����Ŀ¼����
    directory = FSDirectory.open(Paths.get(path));
    // ������ȡ����
    reader = DirectoryReader.open(directory);
    // ������������
    indexSearcher = new IndexSearcher(reader);

    // ������ѯ������,����������Ĭ��Ҫ��ѯ���ֶε����ƣ��ִ���
    QueryParser parser = new QueryParser(index, new IKAnalyzer());
    // ������ѯ����
    Query query = null;
    try {
      query = parser.parse(item);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    // ��������,������������ѯ��������Ҫ��ѯ�����������
    // ���صĽ���� ����ƥ��������÷�ǰN�����ĵ���Ϣ��������ѯ������������Ϣ�����з����������ĵ��ı����Ϣ����
    TopDocs topDocs = indexSearcher.search(query, 10);
    // ��ȡ������
    System.out.println("�����������ҵ�" + topDocs.totalHits + "������");
    // ��ȡ�÷��ĵ�����ScoreDoc������.SocreDoc�а������ĵ��ı�š��ĵ��ĵ÷�
    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
    for (ScoreDoc scoreDoc : scoreDocs) {
      // ȡ���ĵ����
      int docID = scoreDoc.doc;
      // ���ݱ��ȥ���ĵ�
      Document doc = reader.document(docID);
      System.out.println(index+": " + doc.get(index));
      System.out.println(text+": " + doc.get(text));
      // ȡ���ĵ��÷�
      System.out.println("�÷֣� " + scoreDoc.score);
    }
  }
  public void readFileText(String item)throws IOException{
    Map<String, String> result = new HashMap<>();
    // ����Ŀ¼����
    directory = FSDirectory.open(Paths.get(path));
    // ������ȡ����
    reader = DirectoryReader.open(directory);
    // ������������
    indexSearcher = new IndexSearcher(reader);
    // ������ѯ������,����������Ĭ��Ҫ��ѯ���ֶε����ƣ��ִ���
    QueryParser parser = new QueryParser(text, new IKAnalyzer());
    // ������ѯ����
    Query query = null;
    try {
      query = parser.parse(item);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    // ��������,������������ѯ��������Ҫ��ѯ�����������
    // ���صĽ���� ����ƥ��������÷�ǰN�����ĵ���Ϣ��������ѯ������������Ϣ�����з����������ĵ��ı����Ϣ����
    TopDocs topDocs = indexSearcher.search(query, 100000);
    // ��ȡ������
    //System.out.println("�����������ҵ�" + topDocs.totalHits + "������");
    // ��ȡ�÷��ĵ�����ScoreDoc������.SocreDoc�а������ĵ��ı�š��ĵ��ĵ÷�
    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
    for (ScoreDoc scoreDoc : scoreDocs) {
      // ȡ���ĵ����
      int docID = scoreDoc.doc;
      // ���ݱ��ȥ���ĵ�
      Document doc = reader.document(docID);
      //System.out.println(pa);
      searchPaths.put(doc.get(index),doc.get(text));
    }
  }
  public  Map<String, String> getSearchPaths(){
    return searchPaths;
  }
  public void search(Query query) throws Exception {
    searchPaths=new HashMap<>();
    // ����Ŀ¼����
    directory = FSDirectory.open(Paths.get(path));
    // ������ȡ����
    reader = DirectoryReader.open(directory);
    // ������������
    indexSearcher = new IndexSearcher(reader);

    // ��������,������������ѯ��������Ҫ��ѯ�����������
    // ���صĽ���� ����ƥ��������÷�ǰN�����ĵ���Ϣ��������ѯ������������Ϣ�����з����������ĵ��ı����Ϣ����
    TopDocs topDocs = indexSearcher.search(query,1000000);
    // ��ȡ������
    // ��ȡ�÷��ĵ�����ScoreDoc������.SocreDoc�а������ĵ��ı�š��ĵ��ĵ÷�
    ScoreDoc[] scoreDocs = topDocs.scoreDocs;

    for (ScoreDoc scoreDoc : scoreDocs) {
      // ȡ���ĵ����
      int docID = scoreDoc.doc;
      // ���ݱ��ȥ���ĵ�
      Document doc = reader.document(docID);
      searchPaths.put(doc.get(index),doc.get(text));
    }
  }
  public void termQuery(String item) throws Exception {
    Query query=new TermQuery(new Term(text,item));
    search(query);
  }
  public void WildCardQuery(String item) throws Exception{
    Query query=new WildcardQuery(new Term(text,item));
    search(query);
  }
  public void FuzzyQuery(String item) throws Exception {
    Query query=new FuzzyQuery(new Term(text,item));
    search(query);
  }
  public static void main(String[] args) {
    try {
    	fileIndex fileIndex=new fileIndex(".\\index");
//	      fileList fileList=new fileList("C:\\Users\\kbdnzzzzz\\Desktop\\test");
//      ArrayList<String> myfiles=fileList.getFiles();
//      for(String s:myfiles){
//        fileIndex.addData(s, getText.read(s) );
//      }
//      fileIndex.fileClose();
      fileIndex.readFileText("��¿");
      Map<String, String> map=new HashMap<>();
      map=fileIndex.getSearchPaths();
      for(Map.Entry<String, String> entry :map.entrySet()){
        System.out.println(entry.getKey());
      }

    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
