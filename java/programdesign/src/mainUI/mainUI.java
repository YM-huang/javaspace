package mainUI;

import file_get_text.*;
import index.*;

import javax.swing.*;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*; 




public class mainUI {
	public static void main(String[] args) {
		UI t = new UI("好想回家", null);
		t.CreatUI();
	}
}

class UI {

	String name;
	//关键词
	String keyWord;
	//搜索内容
	FileIndexer fileIndex=new FileIndexer(".\\index");
	Map<String, String> searchPaths;
	JFrame frame;
	JMenuBar mb = new JMenuBar();
	//"文件"菜单
	FgMenu mFile=new FgMenu("文件(F)",KeyEvent.VK_F);
	FgMenu mColor=new FgMenu("颜色(P)",KeyEvent.VK_P);
	FgMenu mFont=new FgMenu("字体(Z)",KeyEvent.VK_Z);
	JMenuItem miHelp=new JMenuItem("帮助(H)",KeyEvent.VK_H);
	JMenuItem miColor1=new JMenuItem("高亮颜色(T)",KeyEvent.VK_T);
	JMenuItem miColor2=new JMenuItem("字体颜色(K)",KeyEvent.VK_K);
	JMenuItem miFont=new JMenuItem("字体(J)",KeyEvent.VK_J);
	//索引按钮
	JButton index = new JButton("新建索引");
	//搜索按钮
	JButton SearchButton = new JButton("查找");
	//高亮按钮
	JButton highLight = new JButton("高亮");
	//搜索框
	JTextField SearchText = new JTextField(100);
	//选择框
	JCheckBox txt = new JCheckBox("TXT");
	JCheckBox word = new JCheckBox("WORD");
	JCheckBox pdf = new JCheckBox("PDF");
	JCheckBox ppt = new JCheckBox("PPT");
	JCheckBox excel = new JCheckBox("EXCEL");
	JCheckBox all = new JCheckBox("All");
	//文本框
	JTextArea ta=new JTextArea();
	//高亮
	Highlighter TextLighter = ta.getHighlighter();
	DefaultHighlighter.DefaultHighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
	boolean isHighlighted=true;
	//highLightLenth 是高亮的文本的下标和长度
	Map<Integer, Integer> highLightLenth=new HashMap<>();
	//记录下标
	Vector<Integer> highLightIndex=new Vector<>();
	//组件框
	Box topBox = Box.createHorizontalBox();
	//定义行信息数据
	private Vector title = new Vector(2);
	//存储数据
	private Vector<Vector> data = new Vector<>();
	
	UI() {

	}
	
	UI(String _name, String res) {
		this.name = _name;
		this.frame = new JFrame(this.name);
		ta.setText(res);
	}
	
	void CreatUI() {
		
		//添加带滚动条(JScrollPane)的文本编辑框JTextArea
		JScrollPane sp=new JScrollPane(ta);
		frame.add(sp);
		SearchButton.setMnemonic(KeyEvent.VK_ENTER);
		addMenuBar();
		addTop();
		SetCheckBox();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private JMenuBar addMenuBar() {
		frame.setJMenuBar(mb);
		mFile.add(miHelp);
		mColor.add(miColor1);
		mColor.add(miColor2);
		mFont.add(miFont);
		//将"文件"菜单添加到菜单栏上
		mb.add(mFile); 
		//颜色加入
		mb.add(mColor); 
		//字体加入
		mb.add(mFont);
		
		//帮助事件响应
		miHelp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				help();
			}
		});
		
		//高亮颜色事件响应
		miColor1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultColorSelectionModel Model=new DefaultColorSelectionModel();
			    JColorChooser chooser=new JColorChooser(Model);
			    Color color=chooser.showDialog(null,"高亮颜色选择",Color.orange);
			    p = new DefaultHighlighter.DefaultHighlightPainter(color);
			    TextLighter.removeAllHighlights();
			    setHighlight(keyWord);
			    isHighlighted=true;
			}
		});
		
		//字体颜色事件响应
		miColor2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fontcolor();
			}
		});
		
		
		//字体事件响应
		miFont.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				font();
			}
		});
		return mb;
	}
 	
	private void SetCheckBox() {
		//给各个选择框添加点击事件响应
		all.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  if(!all.isSelected()){
		    		  txt.setSelected(false);
		    		  pdf.setSelected(false);
		    		  ppt.setSelected(false);
		    		  word.setSelected(false);
		    		  excel.setSelected(false);
		    	  }
		    	  else{
			        	txt.setSelected(true);
			        	pdf.setSelected(true);
			        	ppt.setSelected(true);
			        	word.setSelected(true);
			        	excel.setSelected(true);
		    	  }
		      }
		});
		
		txt.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  if(all.isSelected()){
		    		  all.setSelected(false);
		    		  txt.setSelected(true);
		    		  pdf.setSelected(false);
		    		  ppt.setSelected(false);
		    		  word.setSelected(false);
		    		  excel.setSelected(false);
		    	  }
		      }
		});
		
		pdf.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  if(all.isSelected()){
		    		  all.setSelected(false);
		    		  txt.setSelected(false);
		    		  pdf.setSelected(true);
		    		  ppt.setSelected(false);
		    		  word.setSelected(false);
		    		  excel.setSelected(false);
		    	  }
		      }
		});
		
		ppt.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  if(all.isSelected()){
		    		  all.setSelected(false);
		    		  txt.setSelected(false);
		    		  pdf.setSelected(false);
		    		  ppt.setSelected(true);
		    		  word.setSelected(false);
		    		  excel.setSelected(false);
		    	  }
		      }
		});
		
		word.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  if(all.isSelected()){
		    		  all.setSelected(false);
		    		  txt.setSelected(false);
		    		  pdf.setSelected(false);
		    		  ppt.setSelected(false);
		    		  word.setSelected(true);
		    		  excel.setSelected(false);
		    	  }
		      }
		});
		
		excel.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  if(all.isSelected()){
		    		  all.setSelected(false);
		    		  txt.setSelected(false);
		    		  pdf.setSelected(false);
		    		  ppt.setSelected(false);
		    		  word.setSelected(false);
		    		  excel.setSelected(true);
		    	  }
		      }
		});
		
	}
	
	private void addTop(){

	    //添加选择框
		topBox.add(Box.createHorizontalStrut(10));
		topBox.add(all);
		topBox.add(txt);
		topBox.add(word);
		topBox.add(pdf);
		topBox.add(ppt);
		topBox.add(excel);
		
		//添加搜索框和按钮
		topBox.add(Box.createHorizontalStrut(10));
	    topBox.add(SearchText);
	    topBox.add(Box.createHorizontalStrut(10));
	    SearchButton.setMnemonic(KeyEvent.VK_ENTER);//设置快捷键
	    topBox.add(SearchButton);
	    topBox.add(Box.createHorizontalStrut(5));
	    topBox.add(index);
	    topBox.add(Box.createHorizontalStrut(5));
		topBox.add(highLight);
		topBox.add(Box.createHorizontalStrut(10));
		
		//全部设置成可见
		all.setSelected(true);
	    txt.setSelected(true);
	    word.setSelected(true);
	    pdf.setSelected(true);
	    ppt.setSelected(true);
	    excel.setSelected(true);
	    
	    //搜索按钮事件响应
	    SearchButton.addActionListener(new ActionListener() {

	  		public void actionPerformed(ActionEvent e) {
	  			
	  			//清空文本框内容
	  			ta.setText("");
	  			keyWord= SearchText.getText();
	  			
	  			if (keyWord.trim().isEmpty()) {
	  		        JOptionPane.showMessageDialog(frame, "请输入内容!", "错误",JOptionPane.ERROR_MESSAGE);
	  		        return;
	  		    }
	  			else {
	  				try {
	  					fileIndex.readFileContent(keyWord);
	  					searchPaths=fileIndex.getSearchPaths();
	  					if(searchPaths.size()==0){
	  						JOptionPane.showMessageDialog(frame, "未查找到信息", "提示",JOptionPane.ERROR_MESSAGE);
	  						return;
	  					}
	  					search();
	  				} catch (Exception ioException) {
	  		            ioException.printStackTrace();
	  				}
	  			}
	  			if(data.isEmpty()){
	  		        JOptionPane.showMessageDialog(frame, "未能查找到内容", "提示",JOptionPane.ERROR_MESSAGE);
	  		    }
	  		}
	  	});
	    
	    //索引按钮响应
	    index.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser fd=new JFileChooser();
	    	      fd.setCurrentDirectory(new File("C:\\Users\\kbdnzzzzz\\Desktop\\test"));
	    	      fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	      fd.showOpenDialog(null);
	    	      File f=fd.getSelectedFile();
	    	      //获取此文件夹下的所有文件
	    	      getfilelist fl= null;
	    	      try {
	    	    	  	fl = new getfilelist(f.toString());
		  	      		ArrayList<String> myfiles=fl.getFiles();
		  	      		new Progress(myfiles).start();
	    	      } catch (IOException ioException) {
	    	    	  ioException.printStackTrace();
	    	      }
   
	    	}
	    });
	    
	    //关闭打开高亮
	    highLight.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(isHighlighted){
	    	        //移除高亮
	    	        TextLighter.removeAllHighlights();
	    	        isHighlighted=false;
	    	      }
	    	      else {
	    	        //添加高亮
	    	        setHighlight(keyWord);
	    	        isHighlighted=true;
	    	      }
	    	}
	    });
	    
	    //放置于最上方
	    frame.add(topBox, BorderLayout.NORTH);
	}
	
	private void help() {
		final JDialog helpDialog = new JDialog(frame,"帮助说明",false);
		Container con = helpDialog.getContentPane();
		JTextArea taa=new JTextArea("这个人很懒，什么也没有留下。\n聊骚发邮箱:huangyimiao666@gmail.com");//文本框
		con.add(taa);
		helpDialog.setSize(300,300);//设置大小
		helpDialog.setLocationRelativeTo(null);//居中
		helpDialog.setResizable(false);//用户不可调节
		helpDialog.setVisible(true);
	}
	
	//字体颜色
	private void fontcolor() {
		JColorChooser chooser=new JColorChooser();
		Color color=chooser.showDialog(frame, "拾取颜色", Color.orange);
		ta.setForeground(color);
	}
	
	//给关键字高亮
	private void setHighlight(String keys){
		highLightIndex.clear();
	    highLightLenth.clear();
	    try {
	    	String text = ta.getText().toLowerCase();
	        int lenth=text.length();
	        String key;
	        int keysLenth=keys.length();
	        for(int i=0;i<lenth-keysLenth-1;i++){
	        	key= text.substring(i,i+keysLenth);
	        	if(keys.equals(key)){
	        		highLightIndex.add(i);
	        		highLightLenth.put(i,key.length());
	        		TextLighter.addHighlight(i,i+keysLenth,p);
	        	}
	        }
	    }catch (BadLocationException e) {
	        e.printStackTrace();
	    }
	}
	
	
	//查找 关键字
	private void search() {
		final JDialog searchDialog = new JDialog(frame,"搜索结果",false);
		Container con = searchDialog.getContentPane();
		
		title.clear();
		title.add("文件名");
	    title.add("路径"); 
	    
	    DefaultTableModel model = new DefaultTableModel(data, title){
	    	@Override
	      	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };//设置不可编辑
	    
		JTable SearchContent = new JTable(model);
	    TableColumn column = SearchContent.getColumnModel().getColumn(0);
	    column.setMinWidth(150);
	    column.setMaxWidth(800);
	    //给搜索内容添加滚动条
	    JScrollPane jsp = new JScrollPane(SearchContent);
	    //设置表格高度
	    SearchContent.setRowHeight(25);
	    con.add(jsp);
	    
	    model.setRowCount(0);
	    
	    for(Map.Entry<String, String> entry :searchPaths.entrySet()){
            if(isSelected(entry.getKey())&&entry.getValue().contains(keyWord)){
            	Vector t = new Vector();
            	t.add(getFileName(entry.getKey()));
            	t.add(entry.getKey());
            	data.add(t);
            }
        }
	    
	    SearchContent.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	          int row = SearchContent.getSelectedRow();
	          int col = SearchContent.getSelectedColumn();
	          Object path=model.getValueAt(row,1);
	          if(e.getClickCount()==2){//双击
	        	  if(col==0){
	        		  //点击文件名称，打开文件
	        		  openFile((String) path);
	        	  }
	        	  else if(col==1){
	        		  //点击文件路径
	        		  openDir((String) path);
	        	  }
	          }
	          else if(e.getClickCount()==1){//单击
	        	  highLightIndex.clear();
	        	  highLightLenth.clear();
	        	  ta.setText(searchPaths.get(path));
	        	  //移除高亮
	        	  TextLighter.removeAllHighlights();
	        	  setHighlight(keyWord);
	        	  if(!isHighlighted) {
	        		  TextLighter.removeAllHighlights();
	        	  }
	        	  ta.setCaretPosition(highLightIndex.get(0));
	          }
	        }
	      });
	    
	    SearchContent.validate();
	    SearchContent.updateUI();
	    
	    searchDialog.setSize(400,500);//设置大小
	    searchDialog.setLocationRelativeTo(null);//居中
	    searchDialog.setVisible(true);
	}
	
	//判断选择框是否被选择
	private boolean isSelected(String path){
	    if(path.endsWith(".txt")) {
	    	return txt.isSelected();
	    }
	    else if(path.endsWith(".doc")||path.endsWith(".docx")){
	    	return word.isSelected();
	    }
	    else if(path.endsWith(".pdf")){
	    	return pdf.isSelected();
	    }
	    else if(path.endsWith(".pptx")||path.endsWith(".ppt")){
	    	return ppt.isSelected();
	    }
	    else if(path.endsWith(".xlsx")||path.endsWith(".xls")){
	    	return excel.isSelected();
	    }
	    else {
	    	return true;
	    }
	}

	//获取文件名称
	static String getFileName(String path){
	    if(path.contains("/")) {
	    	return path.substring(path.lastIndexOf("/")+1);
	    }
	    else if(path.contains("\\")) {
	    	return path.substring(path.lastIndexOf("\\")+1);
	    }
	    return path;
	}
	
	//打开文件
	public static void openFile(String path){
	    try {
	        java.awt.Desktop.getDesktop().open(new File(path));
	    }catch (IOException e) {
	        e.printStackTrace();
	      }
	}
	 
	//打开路径
	public static void openDir(String path){
	    try {
	    	if(!path.endsWith("\\")){
	    		path=path.substring(0,path.lastIndexOf("\\"));
	    	}
	    	else if(!path.endsWith("/")){
	    		path=path.substring(0,path.lastIndexOf("/")-1);
	    	}
	    	java.awt.Desktop.getDesktop().open(new File(path));
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	private void font() {
		
		final JDialog fontDialog = new JDialog(frame,"字体设置",false);
		Container con = fontDialog.getContentPane();
		
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel fontLabel = new JLabel("字体(F)：");
		fontLabel.setPreferredSize(new Dimension(100,20));//构造一个Dimension，并将其初始化为指定宽度和高度
		JLabel styleLabel = new JLabel("字形(Y)：");
		styleLabel.setPreferredSize(new Dimension(100,20));
		JLabel sizeLabel = new JLabel("大小(S)：");
		sizeLabel.setPreferredSize(new Dimension(100,20));
		
		final JLabel sample = new JLabel("测试样例");
		final JTextField fontText = new JTextField(10);
		fontText.setPreferredSize(new Dimension(200,20));
		final JTextField styleText = new JTextField(5);
		styleText.setPreferredSize(new Dimension(200,20));
		final JTextField sizeText = new JTextField(3);
		sizeText.setPreferredSize(new Dimension(200,20));
		
		final int style[]={Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
		
		JButton okButton = new JButton("确定");
		JButton cancel = new JButton("取消");
		
		Font currentFont = ta.getFont();
		fontText.setText(currentFont.getFontName());
		fontText.selectAll();
		
		if(currentFont.getStyle() == Font.PLAIN)
			styleText.setText("常规");
		else if(currentFont.getStyle() == Font.BOLD)
			styleText.setText("粗体");
		else if(currentFont.getStyle() == Font.ITALIC)
			styleText.setText("斜体");
		else if(currentFont.getStyle() == (Font.BOLD+Font.ITALIC))
			styleText.setText("粗斜体");
		styleText.selectAll();
		
		String str = String.valueOf(currentFont.getSize());
		sizeText.setText(str);
		sizeText.selectAll();
		
		final JList fontList,styleList,sizeList;
		
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();//扫描本地环境
		
		final String fontName[]=ge.getAvailableFontFamilyNames();
		fontList=new JList(fontName);
		fontList.setFixedCellWidth(90);
		fontList.setFixedCellHeight(20);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//一次只能选择一项
		
		final String fontStyle[]={"常规","粗体","斜体","粗斜体"};
		styleList=new JList(fontStyle);
		styleList.setFixedCellWidth(70);
		styleList.setFixedCellHeight(20);
		styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//一次只能选择一项
		if(currentFont.getStyle()==Font.PLAIN)
			styleList.setSelectedIndex(0);
		else if(currentFont.getStyle()==Font.BOLD)
			styleList.setSelectedIndex(1);
		else if(currentFont.getStyle()==Font.ITALIC)
			styleList.setSelectedIndex(2);
		else if(currentFont.getStyle()==(Font.BOLD+Font.ITALIC))
			styleList.setSelectedIndex(3);
		
		final String fontSize[]={"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};
		sizeList=new JList(fontSize);
		sizeList.setFixedCellWidth(40);
		sizeList.setFixedCellHeight(20);
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//一次只能选择一项
		
		fontList.addListSelectionListener(new ListSelectionListener(){	
			public void valueChanged(ListSelectionEvent event){
			    fontText.setText(fontName[fontList.getSelectedIndex()]);
				fontText.selectAll();
				Font sampleFont1=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				sample.setFont(sampleFont1);
			}
		});
		
		styleList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
			    int s=style[styleList.getSelectedIndex()];
				styleText.setText(fontStyle[s]);
				styleText.selectAll();
				Font sampleFont2=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				sample.setFont(sampleFont2);
			}
		});
		
		sizeList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
			    sizeText.setText(fontSize[sizeList.getSelectedIndex()]);
				sizeText.selectAll();	
				Font sampleFont3=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				sample.setFont(sampleFont3);
			}
		});
		
		okButton.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
			Font okFont=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				ta.setFont(okFont);
				fontDialog.dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{	fontDialog.dispose();	
			}
		});
		
		JPanel samplePanel=new JPanel();
		samplePanel.setBorder(BorderFactory.createTitledBorder("示例"));
		samplePanel.add(sample);
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		
		panel1.add(fontText);
		panel1.add(styleText);
		panel1.add(sizeText);
		panel1.add(okButton);
		
		panel2.add(new JScrollPane(fontList));//以JList作为JScrollPane的视口视图
		panel2.add(new JScrollPane(styleList));
		panel2.add(new JScrollPane(sizeList));
		
		panel3.add(okButton);
		panel3.add(cancel);
		
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		con.add(samplePanel);
		
		fontDialog.setSize(300,450);//设置大小
		fontDialog.setLocationRelativeTo(null);//居中
		fontDialog.setResizable(false);//用户不可调节
		fontDialog.setVisible(true);
	}

	//索引新建线程
	private class Progress extends Thread {

	    ArrayList<String> arrayList;
	    Progress(ArrayList<String> arr)
	    {
	        this.arrayList=arr;
	    }

	    @Override
	    public void run() {
	        try {
	        	for(String s:arrayList){
  	      			fileIndex.addData(s, GetText.read(s) );
  	      			System.out.println(s);
  	      		}
	        	System.out.println("索引完成");
  	      		fileIndex.fileClose();
	        }catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
	    }
	}
}

class FgMenu extends JMenu{
	public FgMenu(String label){
		super(label);
	}
	public FgMenu(String label,int nAccelerator){
		super(label);
		setMnemonic(nAccelerator); 
	}
	
}

