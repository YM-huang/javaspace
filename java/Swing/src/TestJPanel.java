import java.awt.Color; 
import java.awt.Container;
import javax.swing.*;
public class TestJPanel extends JFrame{
	public TestJPanel(String sTitle) {
		super(sTitle);
		setSize(400,300);//���ô�С
		//��ȡ�������;
		Container c=getContentPane();
		c.setBackground(Color.YELLOW); //���ڱ�����ɫ
		c.setLayout(null);//ȡ��������
		JPanel pan=new JPanel();
		pan.setBackground(Color.GREEN); // pan������ɫ
		pan.setSize(200,100);
		add(pan);//��add���������pan��ӵ�������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]) {
		TestJPanel frm = new TestJPanel("JFrame with JPanel");
		frm.setVisible(true);
	}
}