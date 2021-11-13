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
		UI t = new UI("����ؼ�", null);
		t.CreatUI();
	}
}

class UI {

	String name;
	//�ؼ���
	String keyWord;
	//��������
	FileIndexer fileIndex=new FileIndexer(".\\index");
	Map<String, String> searchPaths;
	JFrame frame;
	JMenuBar mb = new JMenuBar();
	//"�ļ�"�˵�
	FgMenu mFile=new FgMenu("�ļ�(F)",KeyEvent.VK_F);
	FgMenu mColor=new FgMenu("��ɫ(P)",KeyEvent.VK_P);
	FgMenu mFont=new FgMenu("����(Z)",KeyEvent.VK_Z);
	JMenuItem miHelp=new JMenuItem("����(H)",KeyEvent.VK_H);
	JMenuItem miColor1=new JMenuItem("������ɫ(T)",KeyEvent.VK_T);
	JMenuItem miColor2=new JMenuItem("������ɫ(K)",KeyEvent.VK_K);
	JMenuItem miFont=new JMenuItem("����(J)",KeyEvent.VK_J);
	//������ť
	JButton index = new JButton("�½�����");
	//������ť
	JButton SearchButton = new JButton("����");
	//������ť
	JButton highLight = new JButton("����");
	//������
	JTextField SearchText = new JTextField(100);
	//ѡ���
	JCheckBox txt = new JCheckBox("TXT");
	JCheckBox word = new JCheckBox("WORD");
	JCheckBox pdf = new JCheckBox("PDF");
	JCheckBox ppt = new JCheckBox("PPT");
	JCheckBox excel = new JCheckBox("EXCEL");
	JCheckBox all = new JCheckBox("All");
	//�ı���
	JTextArea ta=new JTextArea();
	//����
	Highlighter TextLighter = ta.getHighlighter();
	DefaultHighlighter.DefaultHighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
	boolean isHighlighted=true;
	//highLightLenth �Ǹ������ı����±�ͳ���
	Map<Integer, Integer> highLightLenth=new HashMap<>();
	//��¼�±�
	Vector<Integer> highLightIndex=new Vector<>();
	//�����
	Box topBox = Box.createHorizontalBox();
	//��������Ϣ����
	private Vector title = new Vector(2);
	//�洢����
	private Vector<Vector> data = new Vector<>();
	
	UI() {

	}
	
	UI(String _name, String res) {
		this.name = _name;
		this.frame = new JFrame(this.name);
		ta.setText(res);
	}
	
	void CreatUI() {
		
		//��Ӵ�������(JScrollPane)���ı��༭��JTextArea
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
		//��"�ļ�"�˵���ӵ��˵�����
		mb.add(mFile); 
		//��ɫ����
		mb.add(mColor); 
		//�������
		mb.add(mFont);
		
		//�����¼���Ӧ
		miHelp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				help();
			}
		});
		
		//������ɫ�¼���Ӧ
		miColor1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultColorSelectionModel Model=new DefaultColorSelectionModel();
			    JColorChooser chooser=new JColorChooser(Model);
			    Color color=chooser.showDialog(null,"������ɫѡ��",Color.orange);
			    p = new DefaultHighlighter.DefaultHighlightPainter(color);
			    TextLighter.removeAllHighlights();
			    setHighlight(keyWord);
			    isHighlighted=true;
			}
		});
		
		//������ɫ�¼���Ӧ
		miColor2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fontcolor();
			}
		});
		
		
		//�����¼���Ӧ
		miFont.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				font();
			}
		});
		return mb;
	}
 	
	private void SetCheckBox() {
		//������ѡ�����ӵ���¼���Ӧ
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

	    //���ѡ���
		topBox.add(Box.createHorizontalStrut(10));
		topBox.add(all);
		topBox.add(txt);
		topBox.add(word);
		topBox.add(pdf);
		topBox.add(ppt);
		topBox.add(excel);
		
		//���������Ͱ�ť
		topBox.add(Box.createHorizontalStrut(10));
	    topBox.add(SearchText);
	    topBox.add(Box.createHorizontalStrut(10));
	    SearchButton.setMnemonic(KeyEvent.VK_ENTER);//���ÿ�ݼ�
	    topBox.add(SearchButton);
	    topBox.add(Box.createHorizontalStrut(5));
	    topBox.add(index);
	    topBox.add(Box.createHorizontalStrut(5));
		topBox.add(highLight);
		topBox.add(Box.createHorizontalStrut(10));
		
		//ȫ�����óɿɼ�
		all.setSelected(true);
	    txt.setSelected(true);
	    word.setSelected(true);
	    pdf.setSelected(true);
	    ppt.setSelected(true);
	    excel.setSelected(true);
	    
	    //������ť�¼���Ӧ
	    SearchButton.addActionListener(new ActionListener() {

	  		public void actionPerformed(ActionEvent e) {
	  			
	  			//����ı�������
	  			ta.setText("");
	  			keyWord= SearchText.getText();
	  			
	  			if (keyWord.trim().isEmpty()) {
	  		        JOptionPane.showMessageDialog(frame, "����������!", "����",JOptionPane.ERROR_MESSAGE);
	  		        return;
	  		    }
	  			else {
	  				try {
	  					fileIndex.readFileContent(keyWord);
	  					searchPaths=fileIndex.getSearchPaths();
	  					if(searchPaths.size()==0){
	  						JOptionPane.showMessageDialog(frame, "δ���ҵ���Ϣ", "��ʾ",JOptionPane.ERROR_MESSAGE);
	  						return;
	  					}
	  					search();
	  				} catch (Exception ioException) {
	  		            ioException.printStackTrace();
	  				}
	  			}
	  			if(data.isEmpty()){
	  		        JOptionPane.showMessageDialog(frame, "δ�ܲ��ҵ�����", "��ʾ",JOptionPane.ERROR_MESSAGE);
	  		    }
	  		}
	  	});
	    
	    //������ť��Ӧ
	    index.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser fd=new JFileChooser();
	    	      fd.setCurrentDirectory(new File("C:\\Users\\kbdnzzzzz\\Desktop\\test"));
	    	      fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	      fd.showOpenDialog(null);
	    	      File f=fd.getSelectedFile();
	    	      //��ȡ���ļ����µ������ļ�
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
	    
	    //�رմ򿪸���
	    highLight.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(isHighlighted){
	    	        //�Ƴ�����
	    	        TextLighter.removeAllHighlights();
	    	        isHighlighted=false;
	    	      }
	    	      else {
	    	        //��Ӹ���
	    	        setHighlight(keyWord);
	    	        isHighlighted=true;
	    	      }
	    	}
	    });
	    
	    //���������Ϸ�
	    frame.add(topBox, BorderLayout.NORTH);
	}
	
	private void help() {
		final JDialog helpDialog = new JDialog(frame,"����˵��",false);
		Container con = helpDialog.getContentPane();
		JTextArea taa=new JTextArea("����˺�����ʲôҲû�����¡�\n��ɧ������:huangyimiao666@gmail.com");//�ı���
		con.add(taa);
		helpDialog.setSize(300,300);//���ô�С
		helpDialog.setLocationRelativeTo(null);//����
		helpDialog.setResizable(false);//�û����ɵ���
		helpDialog.setVisible(true);
	}
	
	//������ɫ
	private void fontcolor() {
		JColorChooser chooser=new JColorChooser();
		Color color=chooser.showDialog(frame, "ʰȡ��ɫ", Color.orange);
		ta.setForeground(color);
	}
	
	//���ؼ��ָ���
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
	
	
	//���� �ؼ���
	private void search() {
		final JDialog searchDialog = new JDialog(frame,"�������",false);
		Container con = searchDialog.getContentPane();
		
		title.clear();
		title.add("�ļ���");
	    title.add("·��"); 
	    
	    DefaultTableModel model = new DefaultTableModel(data, title){
	    	@Override
	      	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };//���ò��ɱ༭
	    
		JTable SearchContent = new JTable(model);
	    TableColumn column = SearchContent.getColumnModel().getColumn(0);
	    column.setMinWidth(150);
	    column.setMaxWidth(800);
	    //������������ӹ�����
	    JScrollPane jsp = new JScrollPane(SearchContent);
	    //���ñ��߶�
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
	          if(e.getClickCount()==2){//˫��
	        	  if(col==0){
	        		  //����ļ����ƣ����ļ�
	        		  openFile((String) path);
	        	  }
	        	  else if(col==1){
	        		  //����ļ�·��
	        		  openDir((String) path);
	        	  }
	          }
	          else if(e.getClickCount()==1){//����
	        	  highLightIndex.clear();
	        	  highLightLenth.clear();
	        	  ta.setText(searchPaths.get(path));
	        	  //�Ƴ�����
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
	    
	    searchDialog.setSize(400,500);//���ô�С
	    searchDialog.setLocationRelativeTo(null);//����
	    searchDialog.setVisible(true);
	}
	
	//�ж�ѡ����Ƿ�ѡ��
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

	//��ȡ�ļ�����
	static String getFileName(String path){
	    if(path.contains("/")) {
	    	return path.substring(path.lastIndexOf("/")+1);
	    }
	    else if(path.contains("\\")) {
	    	return path.substring(path.lastIndexOf("\\")+1);
	    }
	    return path;
	}
	
	//���ļ�
	public static void openFile(String path){
	    try {
	        java.awt.Desktop.getDesktop().open(new File(path));
	    }catch (IOException e) {
	        e.printStackTrace();
	      }
	}
	 
	//��·��
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
		
		final JDialog fontDialog = new JDialog(frame,"��������",false);
		Container con = fontDialog.getContentPane();
		
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel fontLabel = new JLabel("����(F)��");
		fontLabel.setPreferredSize(new Dimension(100,20));//����һ��Dimension���������ʼ��Ϊָ����Ⱥ͸߶�
		JLabel styleLabel = new JLabel("����(Y)��");
		styleLabel.setPreferredSize(new Dimension(100,20));
		JLabel sizeLabel = new JLabel("��С(S)��");
		sizeLabel.setPreferredSize(new Dimension(100,20));
		
		final JLabel sample = new JLabel("��������");
		final JTextField fontText = new JTextField(10);
		fontText.setPreferredSize(new Dimension(200,20));
		final JTextField styleText = new JTextField(5);
		styleText.setPreferredSize(new Dimension(200,20));
		final JTextField sizeText = new JTextField(3);
		sizeText.setPreferredSize(new Dimension(200,20));
		
		final int style[]={Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
		
		JButton okButton = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");
		
		Font currentFont = ta.getFont();
		fontText.setText(currentFont.getFontName());
		fontText.selectAll();
		
		if(currentFont.getStyle() == Font.PLAIN)
			styleText.setText("����");
		else if(currentFont.getStyle() == Font.BOLD)
			styleText.setText("����");
		else if(currentFont.getStyle() == Font.ITALIC)
			styleText.setText("б��");
		else if(currentFont.getStyle() == (Font.BOLD+Font.ITALIC))
			styleText.setText("��б��");
		styleText.selectAll();
		
		String str = String.valueOf(currentFont.getSize());
		sizeText.setText(str);
		sizeText.selectAll();
		
		final JList fontList,styleList,sizeList;
		
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();//ɨ�豾�ػ���
		
		final String fontName[]=ge.getAvailableFontFamilyNames();
		fontList=new JList(fontName);
		fontList.setFixedCellWidth(90);
		fontList.setFixedCellHeight(20);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//һ��ֻ��ѡ��һ��
		
		final String fontStyle[]={"����","����","б��","��б��"};
		styleList=new JList(fontStyle);
		styleList.setFixedCellWidth(70);
		styleList.setFixedCellHeight(20);
		styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//һ��ֻ��ѡ��һ��
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
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//һ��ֻ��ѡ��һ��
		
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
		samplePanel.setBorder(BorderFactory.createTitledBorder("ʾ��"));
		samplePanel.add(sample);
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		
		panel1.add(fontText);
		panel1.add(styleText);
		panel1.add(sizeText);
		panel1.add(okButton);
		
		panel2.add(new JScrollPane(fontList));//��JList��ΪJScrollPane���ӿ���ͼ
		panel2.add(new JScrollPane(styleList));
		panel2.add(new JScrollPane(sizeList));
		
		panel3.add(okButton);
		panel3.add(cancel);
		
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		con.add(samplePanel);
		
		fontDialog.setSize(300,450);//���ô�С
		fontDialog.setLocationRelativeTo(null);//����
		fontDialog.setResizable(false);//�û����ɵ���
		fontDialog.setVisible(true);
	}

	//�����½��߳�
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
	        	System.out.println("�������");
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

