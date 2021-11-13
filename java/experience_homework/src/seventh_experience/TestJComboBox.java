package seventh_experience;
import java.awt.*;
import javax.swing.*;
public class TestJComboBox extends JFrame{
	//�������С������	
	JComboBox cbxFont=new JComboBox();
	JComboBox cbxFontSize=new JComboBox();//�����С	
	TestJComboBox(String sTitle){
		super(sTitle);
		
		Container c = getContentPane( );
		c.setLayout(new FlowLayout(FlowLayout.LEFT));

		c.add(new JLabel("�������ƣ�"));
		c.add(cbxFont);		
		c.add(new JLabel("�����С��"));
		c.add(cbxFontSize);

		//��ʼ���������С������
		InitFonts();

		setSize(300,120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void InitFonts(){
		//���ϵͳ����������		
		GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		String[]  fontList=ge.getAvailableFontFamilyNames();
		int i;
			
		//�����������
		for(i=0;i<fontList.length;i++)
			cbxFont.addItem(String.valueOf(i)+"  |  "+fontList[i]);
			
		cbxFont.setSelectedIndex(146);//ѡ��indexΪ146����
		//��������С
		for(i=9;i<=72;i++)
			cbxFontSize.addItem(new Integer(i).toString());
		cbxFontSize.setSelectedIndex(3);//ѡ��indexΪ3����
	}	
	public static void main(String args[]){
		TestJComboBox frm=new TestJComboBox("JFrame with JComboBox");
		frm.setVisible(true);
	}
}
