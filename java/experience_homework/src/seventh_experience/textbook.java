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

	static FgMenu mFile=new FgMenu("文件(F)",KeyEvent.VK_F);//"文件"菜单
	static JMenuItem miNew=new JMenuItem("新建(N)",KeyEvent.VK_N),
					 miOpen=new JMenuItem("打开(O)...",KeyEvent.VK_O),
					 miSave=new JMenuItem("保存(S)",KeyEvent.VK_S),
					 miFont=new JMenuItem("字体与颜色(F)...",KeyEvent.VK_F),
					 miQuit=new JMenuItem("退出(X)",KeyEvent.VK_X);

	//图片
	static ImageIcon icon1=new ImageIcon(zoom("open.png", 0.1));
	static ImageIcon icon2=new ImageIcon(zoom("new.png", 0.1));
	static ImageIcon icon3=new ImageIcon(zoom("save.png", 0.2));
	
	JTextArea ta=new JTextArea();//文本框
	
	//图片缩放（地址，比例）
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
	
	//加工具条
	private void addToolBar(){
		//工具条
		Container c=getContentPane();
		c.add(BorderLayout.NORTH, mtb);
						
		mtb.setLayout(new FlowLayout(FlowLayout.LEFT));
		FgButton[] btn={new FgButton(new ImageIcon (zoom("new.png", 0.1)),"新建文件"),
						new FgButton(new ImageIcon (zoom("open.png", 0.1)),"打开文件"),
						new FgButton(new ImageIcon (zoom("save.png", 0.15)),"保存文件")};
				
		for(int i=0;i<btn.length;i++){			
			btn[i].setBorder(BorderFactory.createEmptyBorder());
			mtb.add(btn[i]);
			btn[i].addActionListener(new JButtonHandler());//第③步，注册监听者
		}
		//设置不可浮动
		mtb.setFloatable(false);
	}

	//构造函数
	public textbook(String sTitle) {
		super(sTitle);
		//②：添加组件。
		addMenus();
		//添加带滚动条(JScrollPane)的文本编辑框JTextArea
		JScrollPane sp=new JScrollPane(ta);
		add(sp);
		addToolBar();
		setSize(800, 600);//设置好宽高
		setLocationRelativeTo(null);//窗体居中显示
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addMenus(){
		//快捷键
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK| InputEvent.SHIFT_DOWN_MASK));

		//设置图标
		miOpen.setIcon(icon1);
		miNew.setIcon(icon2);
		miSave.setIcon(icon3);
		
		setJMenuBar(mb);
		mFile.add(miNew);//新建
		mFile.add(miOpen);//打开
		mFile.add(miSave);//保存
		mFile.addSeparator();//分割条
		mFile.add(miFont);//字体与颜色菜单
		mFile.addSeparator();//分割条
		mFile.add(miQuit);//退出		
		mb.add(mFile); //将"文件"菜单添加到菜单栏上
		
	}
	
	public static void main(String[] args) {

		textbook frm=new textbook("记事本");
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
	//第④步，监听者如何监听
		public void actionPerformed(ActionEvent e){    	
			JOptionPane.showMessageDialog(null,
					"你点击了按钮\""+e.getActionCommand()+"\"",
					"提示",
					JOptionPane.INFORMATION_MESSAGE);
		}
}
