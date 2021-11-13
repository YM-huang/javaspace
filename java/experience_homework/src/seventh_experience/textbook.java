package seventh_experience;
import java.awt.event.*;
import java.awt.BorderLayout;
//import java.awt.Container.*;
//import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class textbook extends JFrame{

	static JMenuBar mb = new JMenuBar();
	static JToolBar mtb = new JToolBar();

	static FgMenu mFile=new FgMenu("�ļ�(F)",KeyEvent.VK_F);//"�ļ�"�˵�
	static JMenuItem miNew=new JMenuItem("�½�(N)",KeyEvent.VK_N),
					 miOpen=new JMenuItem("��(O)...",KeyEvent.VK_O),
					 miSave=new JMenuItem("����(S)",KeyEvent.VK_S),
					 miFont=new JMenuItem("��������ɫ(F)...",KeyEvent.VK_F),
					 miQuit=new JMenuItem("�˳�(X)",KeyEvent.VK_X);

	//ͼƬ
	static ImageIcon icon1=new ImageIcon(zoom("open.png", 0.1));
	static ImageIcon icon2=new ImageIcon(zoom("new.png", 0.1));
	static ImageIcon icon3=new ImageIcon(zoom("save.png", 0.2));
	
	JTextArea ta=new JTextArea();//�ı���
	
	//ͼƬ���ţ���ַ��������
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
	
	//�ӹ�����
	private void addToolBar(){
		//������
		Container c=getContentPane();
		c.add(BorderLayout.NORTH, mtb);
						
		mtb.setLayout(new FlowLayout(FlowLayout.LEFT));
		FgButton[] btn={new FgButton(new ImageIcon (zoom("new.png", 0.1)),"�½��ļ�"),
						new FgButton(new ImageIcon (zoom("open.png", 0.1)),"���ļ�"),
						new FgButton(new ImageIcon (zoom("save.png", 0.15)),"�����ļ�")};
				
		for(int i=0;i<btn.length;i++){			
			btn[i].setBorder(BorderFactory.createEmptyBorder());
			mtb.add(btn[i]);
			btn[i].addActionListener(new JButtonHandler());//�ڢ۲���ע�������
		}
		//���ò��ɸ���
		mtb.setFloatable(false);
	}

	//���캯��
	public textbook(String sTitle) {
		super(sTitle);
		//�ڣ���������
		addMenus();
		//��Ӵ�������(JScrollPane)���ı��༭��JTextArea
		JScrollPane sp=new JScrollPane(ta);
		add(sp);
		addToolBar();
		setSize(800, 600);//���úÿ��
		setLocationRelativeTo(null);//���������ʾ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addMenus(){
		//��ݼ�
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));

		//����ͼ��
		miOpen.setIcon(icon1);
		miNew.setIcon(icon2);
		miSave.setIcon(icon3);
		
		setJMenuBar(mb);
		mFile.add(miNew);//�½�
		mFile.add(miOpen);//��
		mFile.add(miSave);//����
		mFile.addSeparator();//�ָ���
		mFile.add(miFont);//��������ɫ�˵�
		mFile.addSeparator();//�ָ���
		mFile.add(miQuit);//�˳�		
		mb.add(mFile); //��"�ļ�"�˵���ӵ��˵�����
		
	}
	
	public static void main(String[] args) {

		textbook frm=new textbook("���±�");
		frm.setVisible(true);


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

class JButtonHandler implements ActionListener{
	//�ڢܲ�����������μ���
		public void actionPerformed(ActionEvent e){    	
			JOptionPane.showMessageDialog(null,
					"�����˰�ť\""+e.getActionCommand()+"\"",
					"��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		}
}
