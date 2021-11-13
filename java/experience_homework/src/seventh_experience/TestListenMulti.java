package seventh_experience;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;//����������
public class TestListenMulti extends JFrame implements ActionListener{
	JTextField txtNumber;
	JButton btnInc,btnDec;
	public void initComponents(){
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		//��ӵ��п�
		txtNumber = new JTextField("0",20);
		c.add(txtNumber);
		//btnInc��ť
		btnInc = new JButton("��");
		c.add(btnInc);
		btnInc.addActionListener(this);
		//btnDec��ť
		btnDec= new JButton("��");
		c.add(btnDec);
		btnDec.addActionListener(this);
//		catch (Exception e){}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	TestListenMulti(String sTitle){
		super(sTitle);
		initComponents();
	}
	public void actionPerformed(ActionEvent e){
		int oldNum = Integer.parseInt(txtNumber.getText());
		int newNum = oldNum;
		if(e.getSource()==btnInc)//�����btnInc��ť
			newNum++;
		else if (e.getSource()==btnDec)//�����btnDec��ť
			newNum--;
		
		txtNumber.setText(String.valueOf(newNum));
	}
	public static void main(String args[]) {
		TestListenMulti f = new TestListenMulti ("�����������¼�");
		f.setVisible(true);
	}
}
