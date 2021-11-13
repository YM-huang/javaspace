import java.awt.Color; 
import java.awt.Container;
import javax.swing.*;
public class TestJPanel extends JFrame{
	public TestJPanel(String sTitle) {
		super(sTitle);
		setSize(400,300);//设置大小
		//获取窗口面板;
		Container c=getContentPane();
		c.setBackground(Color.YELLOW); //窗口背景红色
		c.setLayout(null);//取消布局器
		JPanel pan=new JPanel();
		pan.setBackground(Color.GREEN); // pan背景黄色
		pan.setSize(200,100);
		add(pan);//用add方法把面板pan添加到窗口中
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]) {
		TestJPanel frm = new TestJPanel("JFrame with JPanel");
		frm.setVisible(true);
	}
}