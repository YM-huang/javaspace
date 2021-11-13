package index;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class FileIndexer {
	
	String path;
	Map<String, String> searchPaths=new HashMap<>();
	
	private Document document;
	private FSDirectory directory;
	private Analyzer analyzer;
	private IndexWriterConfig indexWriterConfig;
	private IndexWriter indexWriter;
	private IndexSearcher indexSearcher;
	private IndexReader reader;
	
	public FileIndexer(String Path){
		path=Path;
	    document=new Document();
	    try {
	    	//��������
	    	directory=FSDirectory.open(Paths.get(path));
	    	//����һ���ִ�������
	    	analyzer=new IKAnalyzer();
	    	//����д�����Ķ�������
	    	indexWriterConfig=new IndexWriterConfig(analyzer);
	    	indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
	    	//����д��������
	    	indexWriter=new IndexWriter(directory, indexWriterConfig);
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	public void addData(String index, String content) throws IOException{
	    document=new Document();
//	    document.add(new TextField("fileNme",fileNme, Field.Store.YES));//�ִ�
//	    document.add(new StringField("path",path, Field.Store.YES));//���ִ�
	    document.add(new TextField("index",index, Field.Store.YES));
	    document.add(new TextField("content",content, Field.Store.YES));
	    indexWriter.addDocument(document);
	}
	
	public void fileClose()throws IOException{
	    indexWriter.close();
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
	      searchPaths.put(doc.get("index"),doc.get("content"));
	    }
	}
	
	public void readFileContent(String item)throws IOException{
	    //ʹ�÷ִ���
	    Analyzer analyzer = new IKAnalyzer();
	    // ����Ŀ¼����
	    directory = FSDirectory.open(Paths.get(path));
	    // ������ȡ����
	    reader = DirectoryReader.open(directory);
	    // ������������
	    indexSearcher = new IndexSearcher(reader);
	    // ������ѯ������,����������Ĭ��Ҫ��ѯ���ֶε����ƣ��ִ���
	    QueryParser parser = new QueryParser("content", analyzer);
	    // ������ѯ����
	    Query query = null;
	    // parse�������루�ִʣ�
	    try {
	    	query = parser.parse(item);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    // ��������,������������ѯ��������Ҫ��ѯ�����������
	    // ���صĽ���� ����ƥ��������÷�ǰN�����ĵ���Ϣ��������ѯ������������Ϣ�����з����������ĵ��ı����Ϣ����
	    TopDocs topDocs = indexSearcher.search(query, 100000);
	    
	    // ��ȡ�÷��ĵ�����ScoreDoc������.SocreDoc�а������ĵ��ı�š��ĵ��ĵ÷�
	    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
	    // ����topN�����scoreDocs,ȡ���ĵ�id��Ӧ���ĵ���Ϣ
	    for (ScoreDoc scoreDoc : scoreDocs) {
	    	// ȡ���ĵ����
	    	int docID = scoreDoc.doc;
	    	// ���ݱ��ȥ���ĵ�
	    	Document doc = reader.document(docID);
	    	//System.out.println(pa);
	    	searchPaths.put(doc.get("index"),doc.get("content"));
	    }
	}
	
	void readFileIndex(String item) throws IOException{
		//ʹ�÷ִ���
	    Analyzer analyzer = new IKAnalyzer();
		// ����Ŀ¼����
	    directory = FSDirectory.open(Paths.get(path));
	    // ������ȡ����
	    reader = DirectoryReader.open(directory);
	    // ������������
	    indexSearcher = new IndexSearcher(reader);
	    // ������ѯ������,����������Ĭ��Ҫ��ѯ���ֶε����ƣ��ִ���
	    QueryParser parser = new QueryParser("index", analyzer);
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
	    // ��ȡ�÷��ĵ�����ScoreDoc������.SocreDoc�а������ĵ��ı�š��ĵ��ĵ÷�
	    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
	    for (ScoreDoc scoreDoc : scoreDocs) {
	    	// ȡ���ĵ����
	    	int docID = scoreDoc.doc;
	    	// ���ݱ��ȥ���ĵ�
	    	Document doc = reader.document(docID);
	    	System.out.println("index: " + doc.get("index"));
	    	System.out.println("text: " + doc.get("text"));
	    	// ȡ���ĵ��÷�
	     	System.out.println("�÷֣� " + scoreDoc.score);
	    }
	}
	
	public static void main(String[] args) {
	    try {
	    	FileIndexer fileIndex=new FileIndexer(".\\index");
//	    	����������������ע�Ͳ���
//	      	getfilelist fileList=new getfilelist("C:\\Users\\kbdnzzzzz\\Desktop\\test");
//	      	ArrayList<String> myfiles=fileList.getFiles();
//	      	for(String s:myfiles){
//	        	fileIndex.addData(s, GetText.read(s) );
//	      	}
//	      	fileIndex.fileClose();
	    	fileIndex.readFileContent(".doc");
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
