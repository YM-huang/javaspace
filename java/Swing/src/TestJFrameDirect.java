import java.awt.event.*;
import javax.swing.*;
public class TestJFrameDirect {

	static JMenuBar mb = new JMenuBar();
//	static FgMenu mFile = new FgMenu("�ļ�(F)",KeyEvent.VK_F);
	static FgMenu mFile=new FgMenu("�ļ�(F)",KeyEvent.VK_F);//"�ļ�"�˵�
	static JMenuItem miNew=new JMenuItem("�½�(N)",KeyEvent.VK_N),
	miOpen=new JMenuItem("��(O)...",KeyEvent.VK_O),
	miSave=new JMenuItem("����(S)",KeyEvent.VK_S),
	miFont=new JMenuItem("��������ɫ(F)...",KeyEvent.VK_F),
	miQuit=new JMenuItem("�˳�(X)",KeyEvent.VK_X);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�٣��������ڶ��󣬴��ڱ���ͨ�����췽�����ݽ�ȥ
		JFrame frm=new JFrame("�����ҵĵ�һ������Ӧ�ó���");
		//�ڣ����������������ֱ�����Ӳ˵�
		frm.setJMenuBar(mb);
		mFile.add(miNew);//�½�
		mFile.add(miOpen);//��
		mFile.add(miSave);//����
		mFile.addSeparator();//�ָ���
		mFile.add(miFont);//��������ɫ�˵�
		mFile.addSeparator();//�ָ���
		mFile.add(miQuit);//�˳�		
		mb.add(mFile); //��"�ļ�"�˵����ӵ��˵�����
		//�ۣ����ô���λ�úʹ�С
		frm.setBounds(10, 10, 800, 400);
		//����close��ť�Ĳ���
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�ܣ���ʾ����
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