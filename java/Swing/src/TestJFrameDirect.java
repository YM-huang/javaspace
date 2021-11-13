import java.awt.event.*;
import javax.swing.*;
public class TestJFrameDirect {

	static JMenuBar mb = new JMenuBar();
//	static FgMenu mFile = new FgMenu("文件(F)",KeyEvent.VK_F);
	static FgMenu mFile=new FgMenu("文件(F)",KeyEvent.VK_F);//"文件"菜单
	static JMenuItem miNew=new JMenuItem("新建(N)",KeyEvent.VK_N),
	miOpen=new JMenuItem("打开(O)...",KeyEvent.VK_O),
	miSave=new JMenuItem("保存(S)",KeyEvent.VK_S),
	miFont=new JMenuItem("字体与颜色(F)...",KeyEvent.VK_F),
	miQuit=new JMenuItem("退出(X)",KeyEvent.VK_X);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//①：创建窗口对象，窗口标题通过构造方法传递进去
		JFrame frm=new JFrame("这是我的第一个窗口应用程序");
		//②：添加组件。本例中直接添加菜单
		frm.setJMenuBar(mb);
		mFile.add(miNew);//新建
		mFile.add(miOpen);//打开
		mFile.add(miSave);//保存
		mFile.addSeparator();//分割条
		mFile.add(miFont);//字体与颜色菜单
		mFile.addSeparator();//分割条
		mFile.add(miQuit);//退出		
		mb.add(mFile); //将"文件"菜单添加到菜单栏上
		//③：设置窗口位置和大小
		frm.setBounds(10, 10, 800, 400);
		//设置close按钮的操作
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
