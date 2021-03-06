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
	    	//打开索引库
	    	directory=FSDirectory.open(Paths.get(path));
	    	//创建一个分词器对象
	    	analyzer=new IKAnalyzer();
	    	//创建写索引的对象配置
	    	indexWriterConfig=new IndexWriterConfig(analyzer);
	    	indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
	    	//创建写索引对象
	    	indexWriter=new IndexWriter(directory, indexWriterConfig);
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	public void addData(String index, String content) throws IOException{
	    document=new Document();
//	    document.add(new TextField("fileNme",fileNme, Field.Store.YES));//分词
//	    document.add(new StringField("path",path, Field.Store.YES));//不分词
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
	    // 索引目录对象
	    directory = FSDirectory.open(Paths.get(path));
	    // 索引读取工具
	    reader = DirectoryReader.open(directory);
	    // 索引搜索工具
	    indexSearcher = new IndexSearcher(reader);

	    // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
	    // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
	    TopDocs topDocs = indexSearcher.search(query,1000000);
	    // 获取总条数
	    // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
	    ScoreDoc[] scoreDocs = topDocs.scoreDocs;

	    for (ScoreDoc scoreDoc : scoreDocs) {
	      // 取出文档编号
	      int docID = scoreDoc.doc;
	      // 根据编号去找文档
	      Document doc = reader.document(docID);
	      searchPaths.put(doc.get("index"),doc.get("content"));
	    }
	}
	
	public void readFileContent(String item)throws IOException{
	    //使用分词器
	    Analyzer analyzer = new IKAnalyzer();
	    // 索引目录对象
	    directory = FSDirectory.open(Paths.get(path));
	    // 索引读取工具
	    reader = DirectoryReader.open(directory);
	    // 索引搜索工具
	    indexSearcher = new IndexSearcher(reader);
	    // 创建查询解析器,两个参数：默认要查询的字段的名称，分词器
	    QueryParser parser = new QueryParser("content", analyzer);
	    // 创建查询对象
	    Query query = null;
	    // parse解析输入（分词）
	    try {
	    	query = parser.parse(item);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
	    // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
	    TopDocs topDocs = indexSearcher.search(query, 100000);
	    
	    // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
	    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
	    // 遍历topN结果的scoreDocs,取出文档id对应的文档信息
	    for (ScoreDoc scoreDoc : scoreDocs) {
	    	// 取出文档编号
	    	int docID = scoreDoc.doc;
	    	// 根据编号去找文档
	    	Document doc = reader.document(docID);
	    	//System.out.println(pa);
	    	searchPaths.put(doc.get("index"),doc.get("content"));
	    }
	}
	
	void readFileIndex(String item) throws IOException{
		//使用分词器
	    Analyzer analyzer = new IKAnalyzer();
		// 索引目录对象
	    directory = FSDirectory.open(Paths.get(path));
	    // 索引读取工具
	    reader = DirectoryReader.open(directory);
	    // 索引搜索工具
	    indexSearcher = new IndexSearcher(reader);
	    // 创建查询解析器,两个参数：默认要查询的字段的名称，分词器
	    QueryParser parser = new QueryParser("index", analyzer);
	    // 创建查询对象
	    Query query = null;
	    try {
	    	query = parser.parse(item);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
	    // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
	    TopDocs topDocs = indexSearcher.search(query, 10);
	    // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
	    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
	    for (ScoreDoc scoreDoc : scoreDocs) {
	    	// 取出文档编号
	    	int docID = scoreDoc.doc;
	    	// 根据编号去找文档
	    	Document doc = reader.document(docID);
	    	System.out.println("index: " + doc.get("index"));
	    	System.out.println("text: " + doc.get("text"));
	    	// 取出文档得分
	     	System.out.println("得分： " + scoreDoc.score);
	    }
	}
	
	public static void main(String[] args) {
	    try {
	    	FileIndexer fileIndex=new FileIndexer(".\\index");
//	    	建立索引，建立完注释测试
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
