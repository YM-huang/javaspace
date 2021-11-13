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
	JTextArea ta=new JTextArea();//�ı���
	
	JMenuBar mb = new JMenuBar();
	JToolBar mtb = new JToolBar();

	FgMenu mFile=new FgMenu("�ļ�(F)",KeyEvent.VK_F);//"�ļ�"�˵�
	JMenuItem miNew=new JMenuItem("�½�(N)",KeyEvent.VK_N),
					 miOpen=new JMenuItem("��(O)...",KeyEvent.VK_O),
					 miSave=new JMenuItem("����(S)",KeyEvent.VK_S),
					 miFont=new JMenuItem("����(F)",KeyEvent.VK_F),
					 miColor=new JMenuItem("��ɫ(P)",KeyEvent.VK_P),
					 miQuit=new JMenuItem("�˳�(X)",KeyEvent.VK_X);
	
	static ImageIcon icon1=new ImageIcon(zoom("open.png", 0.1));
	static ImageIcon icon2=new ImageIcon(zoom("new.png", 0.1));
	static ImageIcon icon3=new ImageIcon(zoom("save.png", 0.2));
	
	public static Image zoom(String srcPath, double d) {

		BufferedImage src;
		Image image = null;
		try {
			src = ImageIO.read(new File(srcPath));
			int width = src.getWidth(); // Դͼ��
			int height = src.getHeight(); // Դͼ��

			// ��ȡһ��������ԭ��scale��ͼ��ʵ��
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
		//��Ӵ�������(JScrollPane)���ı��༭��JTextArea
		JScrollPane sp=new JScrollPane(ta);
		frame.add(sp);
		frame.setJMenuBar(addMenuBar());
		addToolBar();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		//���ó���رյ����ͣ���ֹ�ر�
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		//���ùرճ���
		frame.addWindowListener( new WindowAdapter() {//���ùرյ���Ӧ
			public void windowClosing( WindowEvent e ) {
				closeApplication();
			}
		});
		frame.setVisible(true);
	}
	
	private JToolBar addToolBar(){
		
//		mtb.removeAll();//��Ҫ���óɾ�̬�ͺ��ˡ�
		//������
		Container c=frame.getContentPane();
		c.add(BorderLayout.NORTH, mtb);
						
		mtb.setLayout(new FlowLayout(FlowLayout.LEFT));
		FgButton[] btn={new FgButton(new ImageIcon (zoom("new.png", 0.1)),"�½��ļ�"),
						new FgButton(new ImageIcon (zoom("open.png", 0.1)),"���ļ�"),
						new FgButton(new ImageIcon (zoom("save.png", 0.15)),"�����ļ�")};
				
		for(int i=0;i<btn.length;i++){			
			btn[i].setBorder(BorderFactory.createEmptyBorder());
			mtb.add(btn[i]);
		}
		
		//�½�
		btn[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Text t = new Text("new Document", null);
				t.CreatText();
			}
		});
		
		//��
		btn[1].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OpenItem();
			}
		});
		
		//����
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveItem();
			}
		});
		
		//���ò��ɸ���
		mtb.setFloatable(false);
		
		return mtb;

	}
	
	private JMenuBar addMenuBar() {
		
		//��ݼ�
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));
		
		//����ͼ��
		miOpen.setIcon(icon1);
		miNew.setIcon(icon2);
		miSave.setIcon(icon3);
		
		frame.setJMenuBar(mb);
		mFile.add(miNew);//�½�
		mFile.add(miOpen);//��
		mFile.add(miSave);//����
		mFile.addSeparator();//�ָ���
		mFile.add(miFont);//����˵�
		mFile.add(miColor);//��ɫ�˵�
		mFile.addSeparator();//�ָ���
		mFile.add(miQuit);//�˳�		
		mb.add(mFile); //��"�ļ�"�˵���ӵ��˵�����
		
		//�½��¼���Ӧ
		miNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Text t = new Text("new Document", null);
				t.CreatText();
			}
		});
		
		//���¼���Ӧ
		miOpen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OpenItem();
			}
		});
		
		//�����¼���Ӧ
		miSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				SaveFile(WorkPath, WorkName, tArea.getText());
				SaveItem();
			}
		});
		
		//�����¼���Ӧ
		miFont.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				font();
			}
		});
		
		//��ɫ�¼���Ӧ
		miColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				color();
			}
		});
		
		//�˳�
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
		
		
		if(f.exists()) {//��ѡ�������ļ�----ѯ���Ƿ�Ҫ����
			int i = JOptionPane.showConfirmDialog(null, "���ļ��Ѿ����ڣ�ȷ��Ҫ������");
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
			JOptionPane.showMessageDialog(null, "�ļ��������"+e1.getMessage());
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
		if(!((String)ta.getText()).trim().equals(""))//���ֻ�пո�Ҳ���
		{	
			int answer=JOptionPane.showConfirmDialog( null,
												  "�����ж�����������",
												  "�˳�����",
												  JOptionPane.YES_NO_CANCEL_OPTION,
												  JOptionPane.QUESTION_MESSAGE,
												  null);
			if( answer == JOptionPane.YES_OPTION )//ѡ����
			{
				SaveItem();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ֻ�رյ�ǰ����
			}
			else if(answer == JOptionPane.NO_OPTION)
			{
//				System.exit(0);//�˳���������
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ֻ�رյ�ǰ����
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
		
		final JDialog fontDialog = new JDialog(frame,"��������",false);
		Container con = fontDialog.getContentPane();
		
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel fontLabel = new JLabel("����(F)��");
		fontLabel.setPreferredSize(new Dimension(100,20));//����һ��Dimension���������ʼ��Ϊָ����Ⱥ͸߶�
		JLabel styleLabel = new JLabel("����(Y)��");
		styleLabel.setPreferredSize(new Dimension(100,20));
		JLabel sizeLabel = new JLabel("��С(S)��");
		sizeLabel.setPreferredSize(new Dimension(100,20));
		
		final JLabel sample = new JLabel("���±�");
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
	
	private void color() {
		JColorChooser chooser=new JColorChooser();
		Color color=chooser.showDialog(frame, "ʰȡ��ɫ", Color.orange);
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