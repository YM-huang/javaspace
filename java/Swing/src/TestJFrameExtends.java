import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class TestJFrameExtends extends JFrame{
//以下为成员变量（对象）的定义
//此处定义菜单(与TestJFrameDirect.java中一样)
	static JMenuBar mb = new JMenuBar();
//	static FgMenu mFile = new FgMenu("文件(F)",KeyEvent.VK_F);
	static FgMenu mFile=new FgMenu("文件(F)",KeyEvent.VK_F);//"文件"菜单
	static JMenuItem miNew=new JMenuItem("新建(N)",KeyEvent.VK_N),
	miOpen=new JMenuItem("打开(O)...",KeyEvent.VK_O),
	miSave=new JMenuItem("保存(S)",KeyEvent.VK_S),
	miFont=new JMenuItem("字体与颜色(F)...",KeyEvent.VK_F),
	miQuit=new JMenuItem("退出(X)",KeyEvent.VK_X);
	
	JTextArea ta=new JTextArea();//文本框
	
	TestJFrameExtends(String sTitle){
		super(sTitle);
		//②：添加组件。本例直接添加菜单与JTextArea
		addMenus();
		//添加带滚动条(JScrollPane)的文本编辑框JTextArea
		JScrollPane sp=new JScrollPane(ta);
		add(sp);
		//③：设置窗口大小
		setSize(400, 300);
		//设置close按钮的操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//使窗口在显示屏居中显示
		centerWindow();
		//改变窗口图标
		Toolkit tk=getToolkit(); //得到一个Toolkit对象 
		Image icon=tk.getImage("new.png"); //获取图标
		setIconImage(icon);
	}
	
	private void addMenus(){
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
//窗口居中
	public void centerWindow(){
		//获得显示屏桌面窗口的大小
		Toolkit tk=getToolkit();
		Dimension dm=tk.getScreenSize();
		//让窗口居中显示
		setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
	}
	
	public static void main(String args[]){
		//①：创建窗口对象
		TestJFrameExtends frm=new TestJFrameExtends ("这是我的另一个java窗口程序");
		//④：显示窗口
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
