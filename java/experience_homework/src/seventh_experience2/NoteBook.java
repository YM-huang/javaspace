package seventh_experience2;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Container.*;
import java.awt.Dimension;
import java.awt.*;
import java.awt.Color.*;
import javax.swing.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;

public class NoteBook {
	public static void main(String[] args) {
		Text t = new Text("DocumentFile", null);
		t.CreatText();
	}
}

class Text {

	String name;
	JFrame frame;
	JTextArea ta=new JTextArea();//文本框
	
	JMenuBar mb = new JMenuBar();
	JToolBar mtb = new JToolBar();

	FgMenu mFile=new FgMenu("文件(F)",KeyEvent.VK_F);//"文件"菜单
	JMenuItem miNew=new JMenuItem("新建(N)",KeyEvent.VK_N),
					 miOpen=new JMenuItem("打开(O)...",KeyEvent.VK_O),
					 miSave=new JMenuItem("保存(S)",KeyEvent.VK_S),
					 miFont=new JMenuItem("字体(F)",KeyEvent.VK_F),
					 miColor=new JMenuItem("颜色(P)",KeyEvent.VK_P),
					 miQuit=new JMenuItem("退出(X)",KeyEvent.VK_X);
	
	static ImageIcon icon1=new ImageIcon(zoom("open.png", 0.1));
	static ImageIcon icon2=new ImageIcon(zoom("new.png", 0.1));
	static ImageIcon icon3=new ImageIcon(zoom("save.png", 0.2));
	
	public static Image zoom(String srcPath, double d) {

		BufferedImage src;
		Image image = null;
		try {
			src = ImageIO.read(new File(srcPath));
			int width = src.getWidth(); // 源图宽
			int height = src.getHeight(); // 源图高

			// 获取一个宽、长是原来scale的图像实例
			image = src.getScaledInstance((int) (width * d),
					(int) (height * d), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	Text() {

	}
	
	Text(String _name, String res) {
		this.name = _name;
		this.frame = new JFrame(this.name);
		ta.setText(res);
	}
	
	void CreatText() {
		
//		JPanel jp = new JPanel();
//		jp.setBounds(5,40,1300,650);
//		jp.setLayout(new GridLayout());
		//添加带滚动条(JScrollPane)的文本编辑框JTextArea
		JScrollPane sp=new JScrollPane(ta);
		frame.add(sp);
		frame.setJMenuBar(addMenuBar());
		addToolBar();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		//设置程序关闭的类型，防止关闭
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		//设置关闭程序
		frame.addWindowListener( new WindowAdapter() {//设置关闭的响应
			public void windowClosing( WindowEvent e ) {
				closeApplication();
			}
		});
		frame.setVisible(true);
	}
	
	private JToolBar addToolBar(){
		
//		mtb.removeAll();//不要设置成静态就好了。
		//工具条
		Container c=frame.getContentPane();
		c.add(BorderLayout.NORTH, mtb);
						
		mtb.setLayout(new FlowLayout(FlowLayout.LEFT));
		FgButton[] btn={new FgButton(new ImageIcon (zoom("new.png", 0.1)),"新建文件"),
						new FgButton(new ImageIcon (zoom("open.png", 0.1)),"打开文件"),
						new FgButton(new ImageIcon (zoom("save.png", 0.15)),"保存文件")};
				
		for(int i=0;i<btn.length;i++){			
			btn[i].setBorder(BorderFactory.createEmptyBorder());
			mtb.add(btn[i]);
		}
		
		//新建
		btn[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Text t = new Text("new Document", null);
				t.CreatText();
			}
		});
		
		//打开
		btn[1].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OpenItem();
			}
		});
		
		//保存
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveItem();
			}
		});
		
		//设置不可浮动
		mtb.setFloatable(false);
		
		return mtb;

	}
	
	private JMenuBar addMenuBar() {
		
		//快捷键
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));
		
		//设置图标
		miOpen.setIcon(icon1);
		miNew.setIcon(icon2);
		miSave.setIcon(icon3);
		
		frame.setJMenuBar(mb);
		mFile.add(miNew);//新建
		mFile.add(miOpen);//打开
		mFile.add(miSave);//保存
		mFile.addSeparator();//分割条
		mFile.add(miFont);//字体菜单
		mFile.add(miColor);//颜色菜单
		mFile.addSeparator();//分割条
		mFile.add(miQuit);//退出		
		mb.add(mFile); //将"文件"菜单添加到菜单栏上
		
		//新建事件响应
		miNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Text t = new Text("new Document", null);
				t.CreatText();
			}
		});
		
		//打开事件响应
		miOpen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OpenItem();
			}
		});
		
		//保存事件响应
		miSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				SaveFile(WorkPath, WorkName, tArea.getText());
				SaveItem();
			}
		});
		
		//字体事件响应
		miFont.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				font();
			}
		});
		
		//颜色事件响应
		miColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				color();
			}
		});
		
		//退出
		miQuit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		return mb;
	}
	
	private void SaveItem() {
		JFileChooser fd=new JFileChooser();
		fd.showSaveDialog(null);
		File f=fd.getSelectedFile();
		
		
		if(f.exists()) {//若选择已有文件----询问是否要覆盖
			int i = JOptionPane.showConfirmDialog(null, "该文件已经存在，确定要覆盖吗？");
			if(i == JOptionPane.YES_OPTION);
			else return;
		}
		
		SaveFile(f, ta.getText());
		
	}
	
	private void OpenItem() {
		JFileChooser fd=new JFileChooser();
		fd.showOpenDialog(null);
		File f=fd.getSelectedFile();
		ReadFile(f);
//		FileReader fileReader = new FileReader(f);
		// System.out.println(s);
	}
	
	private void SaveFile(File f, String res) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(res);
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "文件保存出错"+e1.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ReadFile(File f) {

		try {

			BufferedReader bufReader=new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
			String str = "";
			int size = (int)f.length();
			int charRead = 0;
			char[] content = new char[size];
			
			while(bufReader.ready()) {
				charRead += bufReader.read(content, charRead, size - charRead);
			}
			
			bufReader.close();
			
			str = new String(content, 0, charRead);
			ta.setText(str);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void closeApplication() {
		Toolkit.getDefaultToolkit().beep();
		if(!((String)ta.getText()).trim().equals(""))//如果只有空格也算空
		{	
			int answer=JOptionPane.showConfirmDialog( null,
												  "里面有东西，保存吗？",
												  "退出程序",
												  JOptionPane.YES_NO_CANCEL_OPTION,
												  JOptionPane.QUESTION_MESSAGE,
												  null);
			if( answer == JOptionPane.YES_OPTION )//选择是
			{
				SaveItem();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭当前窗口
			}
			else if(answer == JOptionPane.NO_OPTION)
			{
//				System.exit(0);//退出整个程序
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭当前窗口
			}
			else if(answer == JOptionPane.CANCEL_OPTION)
			{
				return;
			}
		}
		else
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		final JLabel sample = new JLabel("记事本");
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
	
	private void color() {
		JColorChooser chooser=new JColorChooser();
		Color color=chooser.showDialog(frame, "拾取颜色", Color.orange);
		ta.setForeground(color);
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

class FgButton extends JButton{
	public FgButton(){
		super();
	}
	public FgButton(Icon icon){
		super(icon);
	}
	public FgButton(Icon icon,String strToolTipText){
		super(icon);
		setToolTipText(strToolTipText);
	}
	public FgButton(String text){
		super(text);
	}
	public FgButton(String text, Icon icon, String strToolTipText){
		super(text, icon);
		setToolTipText(strToolTipText);
	}
	
}