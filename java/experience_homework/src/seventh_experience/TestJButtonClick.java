package seventh_experience;
import java.awt.event.* ;//第①步，引入事件包
import java.awt.*;
import javax.swing.*;
////第②步，即声明implements某个监听器接口
//public class TestJButtonClick implements ActionListener{//事件监听者与事件源属于同一个类 
public class TestJButtonClick{
////	public TestJButtonClick(){
////		JFrame f = new JFrame("单击按钮事件");
////		Container c=f.getContentPane();
////		JButton b = new JButton("Press Me!");  
////		b.addActionListener(this);//第③步，注册监听者
////		c.add(b, "Center");
////          
////		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		f.setSize(800,400);
////		f.setVisible(true);
////	}
////	public void actionPerformed(ActionEvent e){//第④步，监听者如何监听
////		//e.getActionCommand()方法返回事件源的名称
////		JOptionPane.showMessageDialog(null,
////		              "你点击了按钮\""+e.getActionCommand()+"\"",
////		              "提示",
////                      JOptionPane.INFORMATION_MESSAGE);
////	}
	public static void main(String args[ ]){
		new TestJButtonClick();
	}
}
//public class TestJButtonClick{//事件监听者与事件源不属于同一类 
//	TestJButtonClick(){
//		JFrame f = new JFrame("事件监听者与事件源所在的类分离");
//		Container c=f.getContentPane();
//		JButton b = new JButton("Press Me!");  
//        
//		b.addActionListener(new JButtonHandler());//第③步，注册监听者
//		c.add(b, "Center");
//        
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(200,100);
//		f.setVisible(true);
//	}
//	public static void main(String args[ ]){
//		new TestJButtonClick();
//	}
//}
//
//
//
////第②步，单独定义一个类声明implements某个监听器接口
////class JButtonHandler implements ActionListener{
//////第④步，监听者如何监听
////	public void actionPerformed(ActionEvent e){    	
////		JOptionPane.showMessageDialog(null,
////				"你点击了按钮\""+e.getActionCommand()+"\"",
////				"提示",
////				JOptionPane.INFORMATION_MESSAGE);
////	}
//}
//
//
//
//
