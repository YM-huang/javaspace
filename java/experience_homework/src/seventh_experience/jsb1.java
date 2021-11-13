package seventh_experience;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.io.*;
import javax.swing.undo.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.datatransfer.*;
 
 
public class jsb1 extends JFrame implements ActionListener,DocumentListener{//�˵�
	JMenu file,edit,format,view;
	JPopupMenu popupmenu;
	JMenuItem fileNew,fileOpen,fileSave,fileSaveAs,fileExit;
	JMenuItem pmundo,pmcut,pmcopy,pmpaste,pmDelete,pmSelectAll;
	JMenuItem editUndo,editCut,editCopy,editPaste,editDelete,editFind,editFindnext,editReplace,editGoto,editSelectall;
	JCheckBoxMenuItem LineWrap;
	JMenuItem formatFont;
	JCheckBoxMenuItem viewStatus;
	JTextArea editArea;
	JLabel statusLabel;
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	Clipboard clipBoard=toolkit.getSystemClipboard();
	protected UndoManager undo=new UndoManager();
	protected UndoableEditListener undoHandler=new UndoHandler();

	String oldValue;//��ű༭��ԭ�������ݣ����ڱȽ��ı��Ƿ��иĶ�
	boolean isNewFile=true;//�Ƿ����ļ�(δ�������)
	File name;//��ǰ�ļ���
	//���캯����ʼ
	public jsb1(){	
		super("���±�");
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		//�����˵���
		JMenuBar menuBar=new JMenuBar();
		//�����ļ��˵����˵��ע���¼�����
		file=new JMenu("�ļ�(F)");
		file.setMnemonic('F');
		//���ÿ�ݼ�
		fileNew=new JMenuItem("�½�(N)");
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		fileNew.addActionListener(this);
 
		fileOpen=new JMenuItem("��(O)...");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileOpen.addActionListener(this);
 
		fileSave=new JMenuItem("����(S)");
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileSave.addActionListener(this);
 
		fileSaveAs=new JMenuItem("���Ϊ(A)...");
		fileSaveAs.addActionListener(this);
		
		fileExit=new JMenuItem("�˳�(X)");
		fileExit.addActionListener(this);
 
		//�����༭�˵����˵��ע���¼�����
		edit=new JMenu("�༭(E)");
		edit.setMnemonic('E');
		//���ÿ�ݼ�
		edit.addMenuListener(new MenuListener(){	
			public void menuCanceled(MenuEvent e){
			checkMenuItemEnabled();
			}
			public void menuDeselected(MenuEvent e){
				checkMenuItemEnabled();
			}
			public void menuSelected(MenuEvent e){	
				checkMenuItemEnabled();
			}
		});
 
		editUndo=new JMenuItem("����(U)");
		editUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
		editUndo.addActionListener(this);
		editUndo.setEnabled(false);
 
		editCut=new JMenuItem("����(T)");
		editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		editCut.addActionListener(this);
 
		editCopy=new JMenuItem("����(C)");
		editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		editCopy.addActionListener(this);
 
		editPaste=new JMenuItem("ճ��(P)");
		editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		editPaste.addActionListener(this);
 
		editDelete=new JMenuItem("ɾ��(D)");
		editDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		editDelete.addActionListener(this);
 
		editFind=new JMenuItem("����(F)...");
		editFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
		editFind.addActionListener(this);
 
		editFindnext=new JMenuItem("������һ��(N)");
		editFindnext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
		editFindnext.addActionListener(this);
 
		editReplace = new JMenuItem("�滻(R)...",'R'); 
		editReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK)); 
		editReplace.addActionListener(this);
 
		editGoto = new JMenuItem("ת��(G)...",'G'); 
		editGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK)); 
		editGoto.addActionListener(this);
 
		editSelectall = new JMenuItem("ȫѡ",'A'); 
		editSelectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK)); 
		editSelectall.addActionListener(this);
 
		//������ʽ�˵����˵��ע���¼�����
		format=new JMenu("��ʽ(O)");
		format.setMnemonic('O');
 
		LineWrap=new JCheckBoxMenuItem("�Զ�����(W)");
		LineWrap.setMnemonic('W');
		LineWrap.setState(true);
		LineWrap.addActionListener(this);
 
		formatFont=new JMenuItem("����(F)...");
		formatFont.addActionListener(this);
 
		//�����鿴�˵����˵��ע���¼�����
		view=new JMenu("�鿴(V)");
		view.setMnemonic('V');
 
		viewStatus=new JCheckBoxMenuItem("״̬��(S)");
		viewStatus.setMnemonic('S');
		viewStatus.setState(true);
		viewStatus.addActionListener(this);
 
		
		//��˵������"�ļ�"�˵����˵���
		menuBar.add(file); 
		file.add(fileNew); 
		file.add(fileOpen); 
		file.add(fileSave); 
		file.add(fileSaveAs); 
		file.addSeparator();		
		file.addSeparator();		
		file.add(fileExit); 
 
		//��˵������"�༭"�˵����˵��� 
		menuBar.add(edit); 
		edit.add(editUndo);  
		edit.addSeparator();		
		edit.add(editCut); 
		edit.add(editCopy); 
		edit.add(editPaste); 
		edit.add(editDelete); 
		edit.addSeparator(); 		
		edit.add(editFind); 
		edit.add(editFindnext); 
		edit.add(editReplace);
		edit.addSeparator();  		
		edit.add(editSelectall); 
 
		//��˵������"��ʽ"�˵����˵���		
		menuBar.add(format); 
		format.add(LineWrap); 
		format.add(formatFont);
 
		//��˵������"�鿴"�˵����˵��� 
		menuBar.add(view); 
		view.add(viewStatus);
		//�򴰿���Ӳ˵���				
		this.setJMenuBar(menuBar);
		//�����ı��༭������ӹ�����
		editArea=new JTextArea(20,50);
		JScrollPane scroller=new JScrollPane(editArea);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroller,BorderLayout.CENTER);//�򴰿�����ı��༭��
		editArea.setWrapStyleWord(true);//���õ�����һ�в�������ʱ����
		editArea.setLineWrap(true);//�����ı��༭���Զ�����Ĭ��Ϊtrue,����"�Զ�����"
		//�򴰿�����ı��༭��
		oldValue=editArea.getText();//��ȡԭ�ı��༭��������
 
		//�༭��ע���¼�����(�볷�������й�)
		editArea.getDocument().addUndoableEditListener(undoHandler);
		editArea.getDocument().addDocumentListener(this);
 
		//�����Ҽ������˵�
		popupmenu=new JPopupMenu();
		pmundo=new JMenuItem("����(U)");
		pmcut=new JMenuItem("����(T)");
		pmcopy=new JMenuItem("����(C)");
		pmpaste=new JMenuItem("ճ��(P)");
		pmDelete=new JMenuItem("ɾ��(D)");
		pmSelectAll=new JMenuItem("ȫѡ(A)");
 
		pmundo.setEnabled(false);
 
		//���Ҽ��˵���Ӳ˵���ͷָ���
		popupmenu.add(pmundo);
		popupmenu.addSeparator();
		popupmenu.add(pmcut);
		popupmenu.add(pmcopy);
		popupmenu.add(pmpaste);
		popupmenu.add(pmDelete);
		popupmenu.addSeparator();
		popupmenu.add(pmSelectAll);
 
		//�ı��༭��ע���Ҽ��˵��¼�
		pmundo.addActionListener(this);
		pmcut.addActionListener(this);
		pmcopy.addActionListener(this);
		pmpaste.addActionListener(this);
		pmDelete.addActionListener(this);
		pmSelectAll.addActionListener(this);
 
		//�ı��༭��ע���Ҽ��˵��¼�
		editArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
			if(e.isPopupTrigger()){
				popupmenu.show(e.getComponent(),e.getX(),e.getY());//����������ߵ�����ռ��е�λ�� X��Y ��ʾ�����˵�
				}
				checkMenuItemEnabled();//���ü��У����ƣ�ճ����ɾ���ȹ��ܵĿ�����
				editArea.requestFocus();//�༭����ȡ����
			}
			public void mouseReleased(MouseEvent e){
				if(e.isPopupTrigger()){
					popupmenu.show(e.getComponent(),e.getX(),e.getY());//����������ߵ�����ռ��е�λ�� X��Y ��ʾ�����˵�
				}
				checkMenuItemEnabled();//���ü��У����ƣ�ճ����ɾ���ȹ��ܵĿ�����
				editArea.requestFocus();//�༭����ȡ����
			}
		});//�ı��༭��ע���Ҽ��˵��¼�����

		//���������״̬��
		statusLabel=new JLabel("����F1��ȡ����");
		this.add(statusLabel,BorderLayout.SOUTH);//�򴰿����״̬����ǩ

		//���ô�������Ļ�ϵ�λ�á���С�Ϳɼ��� 
		this.setLocation(100,100);
		this.setSize(650,550);
		this.setVisible(true);
		//��Ӵ��ڼ�����
		addWindowListener(new WindowAdapter(){	
			public void windowClosing(WindowEvent e){	
			exitWindowChoose();
			}
		});
 
		checkMenuItemEnabled();
		editArea.requestFocus();
	}
	
	//���ò˵���Ŀ����ԣ����У����ƣ�ճ����ɾ������
	public void checkMenuItemEnabled(){
		String selectText=editArea.getSelectedText();
		if(selectText==null){
			editCut.setEnabled(false);
			pmcut.setEnabled(false);
			editCopy.setEnabled(false);
			pmcopy.setEnabled(false);
			editDelete.setEnabled(false);
			pmDelete.setEnabled(false);
		}
		else{
			editCut.setEnabled(true);
			pmcut.setEnabled(true); 
			editCopy.setEnabled(true);
			pmcopy.setEnabled(true);
			editDelete.setEnabled(true);
			pmDelete.setEnabled(true);
		}
		//ճ�����ܿ������ж�
		Transferable contents=clipBoard.getContents(this);
		if(contents==null){
			editPaste.setEnabled(false);
			pmpaste.setEnabled(false);
		}
		else{
			editPaste.setEnabled(true);
			pmpaste.setEnabled(true);	
		}
	}
 
	//�رմ���ʱ����
	public void exitWindowChoose(){
		editArea.requestFocus();
		String currentValue=editArea.getText();
		if(currentValue.equals(oldValue)==true){
			System.exit(0);
		}
		else{
			int exitChoose=JOptionPane.showConfirmDialog(this,"�����ļ���δ���棬�Ƿ񱣴棿","�˳���ʾ",JOptionPane.YES_NO_CANCEL_OPTION);
			if(exitChoose==JOptionPane.YES_OPTION){	
				if(isNewFile){	
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileChooser.setApproveButtonText("ȷ��");
					fileChooser.setDialogTitle("���Ϊ");
					
					int result=fileChooser.showSaveDialog(this);
					
					if(result==JFileChooser.CANCEL_OPTION){
						statusLabel.setText("����δ�����ļ�");
						return;
					}					
	
					File saveFileName=fileChooser.getSelectedFile();
					if(saveFileName==null||saveFileName.getName().equals("")){
						JOptionPane.showMessageDialog(this,"�ļ�������","�ļ�������",JOptionPane.ERROR_MESSAGE);
					}
					else {
						try{
						    FileWriter fw=new FileWriter(saveFileName);
							BufferedWriter bfw=new BufferedWriter(fw);
							bfw.write(editArea.getText(),0,editArea.getText().length());
							bfw.flush();
							fw.close();
							
							isNewFile=false;
							name=saveFileName;
							oldValue=editArea.getText();
							
							this.setTitle(saveFileName.getName()+"  - ���±�");
							statusLabel.setText("����ǰ���ļ�:"+saveFileName.getAbsoluteFile());
							
						}							
						catch(IOException ioException){					
						}				
					}
				}
				else{
					
					try{
						FileWriter fw=new FileWriter(name);
						BufferedWriter bfw=new BufferedWriter(fw);
						bfw.write(editArea.getText(),0,editArea.getText().length());
						bfw.flush();
						fw.close();
						
					}							
					catch(IOException ioException){					
					}
				}
				System.exit(0);
				
			}
			else if(exitChoose==JOptionPane.NO_OPTION){
				System.exit(0);
			}
			else{
				return;
			}
		}
	}
 
	//���ҷ���
	public void find(){
		final JDialog findDialog=new JDialog(this,"����",false);
		Container con=findDialog.getContentPane();//���ش˶Ի����contentPane����	
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel findContentLabel=new JLabel("��������(N)��");
		final JTextField findText=new JTextField(15);
		JButton findNextButton=new JButton("������һ��(F)��");
		final JCheckBox matchCheckBox=new JCheckBox("���ִ�Сд(C)");
		ButtonGroup bGroup=new ButtonGroup();
		final JRadioButton upButton=new JRadioButton("����(U)");
		final JRadioButton downButton=new JRadioButton("����(U)");
		downButton.setSelected(true);
		bGroup.add(upButton);
		bGroup.add(downButton);
		JButton cancel=new JButton("ȡ��");
		//ȡ����ť�¼�����
		cancel.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{	findDialog.dispose();
			}
		});
		//"������һ��"��ť����
		findNextButton.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
			//"���ִ�Сд(C)"��JCheckBox�Ƿ�ѡ��
				int k=0,m=0;
				final String str1,str2,str3,str4,strA,strB;
				str1=editArea.getText();
				str2=findText.getText();
				str3=str1.toUpperCase();
				str4=str2.toUpperCase();
				if(matchCheckBox.isSelected()){
					strA=str1;
					strB=str2;
				}
				else{
					strA=str3;
					strB=str4;
				}
				if(upButton.isSelected()){
					if(editArea.getSelectedText()==null)
						k=strA.lastIndexOf(strB,editArea.getCaretPosition()-1);
					else
						k=strA.lastIndexOf(strB, editArea.getCaretPosition()-findText.getText().length()-1);	
					if(k>-1){	
						editArea.setCaretPosition(k);
						editArea.select(k,k+strB.length());
					}
					else{
						JOptionPane.showMessageDialog(null,"�Ҳ��������ҵ����ݣ�","����",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(downButton.isSelected()){	
					if(editArea.getSelectedText()==null)
						k=strA.indexOf(strB,editArea.getCaretPosition()+1);
					else
						k=strA.indexOf(strB, editArea.getCaretPosition()-findText.getText().length()+1);	
					if(k>-1){	
						editArea.setCaretPosition(k);
						editArea.select(k,k+strB.length());
					}
					else{	
						JOptionPane.showMessageDialog(null,"�Ҳ��������ҵ����ݣ�","����",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		//����"����"�Ի���Ľ���
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel directionPanel=new JPanel();
		directionPanel.setBorder(BorderFactory.createTitledBorder("����"));
		//����directionPanel����ı߿�;
		directionPanel.add(upButton);
		directionPanel.add(downButton);
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(findNextButton);
		panel1.add(cancel);
		panel2.add(findContentLabel);
		panel2.add(findText);
		panel2.add(panel1);
		panel3.add(matchCheckBox);
		panel3.add(directionPanel);
		con.add(panel2);
		con.add(panel3);
		findDialog.setSize(410,180);
		findDialog.setResizable(false);//���ɵ�����С
		findDialog.setLocation(230,280);
		findDialog.setVisible(true);
	}
	
	//�滻����
	public void replace(){
		final JDialog replaceDialog=new JDialog(this,"�滻",false);//falseʱ������������ͬʱ���ڼ���״̬(����ģʽ)
		Container con=replaceDialog.getContentPane();//���ش˶Ի����contentPane����
		con.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel findContentLabel=new JLabel("��������(N)��");
		final JTextField findText=new JTextField(15);
		JButton findNextButton=new JButton("������һ��(F):");
		JLabel replaceLabel=new JLabel("�滻Ϊ(P)��");
		final JTextField replaceText=new JTextField(15);
		JButton replaceButton=new JButton("�滻(R)");
		JButton replaceAllButton=new JButton("ȫ���滻(A)");
		JButton cancel=new JButton("ȡ��");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
			replaceDialog.dispose();
			}
		});
		final JCheckBox matchCheckBox=new JCheckBox("���ִ�Сд(C)");
		ButtonGroup bGroup=new ButtonGroup();
		final JRadioButton upButton=new JRadioButton("����(U)");
		final JRadioButton downButton=new JRadioButton("����(U)");
		downButton.setSelected(true);
		bGroup.add(upButton);
		bGroup.add(downButton);
		
		//"������һ��"��ť����
		findNextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			//"���ִ�Сд(C)"��JCheckBox�Ƿ�ѡ��
				int k=0,m=0;
				final String str1,str2,str3,str4,strA,strB;
				str1=editArea.getText();
				str2=findText.getText();
				str3=str1.toUpperCase();
				str4=str2.toUpperCase();
				if(matchCheckBox.isSelected()){
					strA=str1;
					strB=str2;
				}
				else{
					strA=str3;
					strB=str4;
				}
				if(upButton.isSelected()){	
					if(editArea.getSelectedText()==null)
						k=strA.lastIndexOf(strB,editArea.getCaretPosition()-1);
					else
						k=strA.lastIndexOf(strB, editArea.getCaretPosition()-findText.getText().length()-1);	
					if(k>-1){	
						editArea.setCaretPosition(k);
						editArea.select(k,k+strB.length());
					}
					else{	
						JOptionPane.showMessageDialog(null,"�Ҳ��������ҵ����ݣ�","����",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(downButton.isSelected()){	
					if(editArea.getSelectedText()==null)
						k=strA.indexOf(strB,editArea.getCaretPosition()+1);
					else
						k=strA.indexOf(strB, editArea.getCaretPosition()-findText.getText().length()+1);	
					if(k>-1){
						editArea.setCaretPosition(k);
						editArea.select(k,k+strB.length());
					}
					else{
						JOptionPane.showMessageDialog(null,"�Ҳ��������ҵ����ݣ�","����",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		//"�滻"��ť����
		replaceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			if(replaceText.getText().length()==0 && editArea.getSelectedText()!=null) 
					editArea.replaceSelection(""); 
				if(replaceText.getText().length()>0 && editArea.getSelectedText()!=null) 
					editArea.replaceSelection(replaceText.getText());
			}
		});
		
		//"ȫ���滻"��ť����
		replaceAllButton.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
			    editArea.setCaretPosition(0);	//�����ŵ��༭����ͷ	
				int k=0,m=0,replaceCount=0;
				if(findText.getText().length()==0){
					JOptionPane.showMessageDialog(replaceDialog,"����д��������!","��ʾ",JOptionPane.WARNING_MESSAGE);
					findText.requestFocus(true);
					return;
				}
				while(k>-1){
					//"���ִ�Сд(C)"��JCheckBox�Ƿ�ѡ��
					final String str1,str2,str3,str4,strA,strB;
					str1=editArea.getText();
					str2=findText.getText();
					str3=str1.toUpperCase();
					str4=str2.toUpperCase();
					if(matchCheckBox.isSelected()){
						strA=str1;
						strB=str2;
					}
					else{
						strA=str3;
						strB=str4;
					}
					if(upButton.isSelected()){
						if(editArea.getSelectedText()==null)
							k=strA.lastIndexOf(strB,editArea.getCaretPosition()-1);
						else
							k=strA.lastIndexOf(strB, editArea.getCaretPosition()-findText.getText().length()-1);	
						if(k>-1){	
							editArea.setCaretPosition(k);
							editArea.select(k,k+strB.length());
						}
						else{
							if(replaceCount==0){
							JOptionPane.showMessageDialog(replaceDialog, "�Ҳ��������ҵ�����!", "���±�",JOptionPane.INFORMATION_MESSAGE); 
							}
							else{
								JOptionPane.showMessageDialog(replaceDialog,"�ɹ��滻"+replaceCount+"��ƥ������!","�滻�ɹ�",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					else if(downButton.isSelected()){	
						if(editArea.getSelectedText()==null)
							k=strA.indexOf(strB,editArea.getCaretPosition()+1);
						else
							k=strA.indexOf(strB, editArea.getCaretPosition()-findText.getText().length()+1);	
						if(k>-1){	
							editArea.setCaretPosition(k);
							editArea.select(k,k+strB.length());
						}
						else{
							if(replaceCount==0){
							JOptionPane.showMessageDialog(replaceDialog, "�Ҳ��������ҵ�����!", "���±�",JOptionPane.INFORMATION_MESSAGE); 
							}
							else{
								JOptionPane.showMessageDialog(replaceDialog,"�ɹ��滻"+replaceCount+"��ƥ������!","�滻�ɹ�",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					if(replaceText.getText().length()==0 && editArea.getSelectedText()!= null){
						editArea.replaceSelection("");
						replaceCount++;
					} 
					
					if(replaceText.getText().length()>0 && editArea.getSelectedText()!= null){	
						editArea.replaceSelection(replaceText.getText()); 
						replaceCount++;
					}
				}//whileѭ������
			}
		});
		
		//����"�滻"�Ի���Ľ���
		JPanel directionPanel=new JPanel();
		directionPanel.setBorder(BorderFactory.createTitledBorder("����"));
		directionPanel.add(upButton);
		directionPanel.add(downButton);
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		panel4.setLayout(new GridLayout(2,1));
		panel1.add(findContentLabel);
		panel1.add(findText);
		panel1.add(findNextButton);
		panel4.add(replaceButton);
		panel4.add(replaceAllButton);
		panel2.add(replaceLabel);
		panel2.add(replaceText);
		panel2.add(panel4);
		panel3.add(matchCheckBox);
		panel3.add(directionPanel);
		panel3.add(cancel);
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		replaceDialog.setSize(420,220);
		replaceDialog.setResizable(false);//���ɵ�����С
		replaceDialog.setLocation(230,280);
		replaceDialog.setVisible(true);
	}
	//"����"����
	public void font(){
		final JDialog fontDialog=new JDialog(this,"��������",false);
		Container con=fontDialog.getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel fontLabel=new JLabel("����(F)��");
		fontLabel.setPreferredSize(new Dimension(100,20));//����һ��Dimension���������ʼ��Ϊָ����Ⱥ͸߶�
		JLabel styleLabel=new JLabel("����(Y)��");
		styleLabel.setPreferredSize(new Dimension(100,20));
		JLabel sizeLabel=new JLabel("��С(S)��");
		sizeLabel.setPreferredSize(new Dimension(100,20));
		final JLabel sample=new JLabel("���±�");
		final JTextField fontText=new JTextField(9);
		fontText.setPreferredSize(new Dimension(200,20));
		final JTextField styleText=new JTextField(8);
		styleText.setPreferredSize(new Dimension(200,20));
		final int style[]={Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
		final JTextField sizeText=new JTextField(5);
		sizeText.setPreferredSize(new Dimension(200,20));
		JButton okButton=new JButton("ȷ��");
		JButton cancel=new JButton("ȡ��");
		cancel.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{	fontDialog.dispose();	
			}
		});
		Font currentFont=editArea.getFont();
		fontText.setText(currentFont.getFontName());
		fontText.selectAll();
		
		if(currentFont.getStyle()==Font.PLAIN)
			styleText.setText("����");
		else if(currentFont.getStyle()==Font.BOLD)
			styleText.setText("����");
		else if(currentFont.getStyle()==Font.ITALIC)
			styleText.setText("б��");
		else if(currentFont.getStyle()==(Font.BOLD+Font.ITALIC))
			styleText.setText("��б��");
		styleText.selectAll();
		String str=String.valueOf(currentFont.getSize());
		sizeText.setText(str);
		sizeText.selectAll();
		final JList fontList,styleList,sizeList;
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		final String fontName[]=ge.getAvailableFontFamilyNames();
		fontList=new JList(fontName);
		fontList.setFixedCellWidth(86);
		fontList.setFixedCellHeight(20);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final String fontStyle[]={"����","����","б��","��б��"};
		styleList=new JList(fontStyle);
		styleList.setFixedCellWidth(86);
		styleList.setFixedCellHeight(20);
		styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(currentFont.getStyle()==Font.PLAIN)
			styleList.setSelectedIndex(0);
		else if(currentFont.getStyle()==Font.BOLD)
			styleList.setSelectedIndex(1);
		else if(currentFont.getStyle()==Font.ITALIC)
			styleList.setSelectedIndex(2);
		else if(currentFont.getStyle()==(Font.BOLD+Font.ITALIC))
			styleList.setSelectedIndex(3);
		final String fontSize[]={"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};
		sizeList=new JList(fontSize);
		sizeList.setFixedCellWidth(43);
		sizeList.setFixedCellHeight(20);
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.addListSelectionListener(new ListSelectionListener(){	
			public void valueChanged(ListSelectionEvent event){
			    fontText.setText(fontName[fontList.getSelectedIndex()]);
				fontText.selectAll();
				Font sampleFont1=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				sample.setFont(sampleFont1);
			}
		});
		styleList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
			    int s=style[styleList.getSelectedIndex()];
				styleText.setText(fontStyle[s]);
				styleText.selectAll();
				Font sampleFont2=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				sample.setFont(sampleFont2);
			}
		});
		sizeList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
			    sizeText.setText(fontSize[sizeList.getSelectedIndex()]);
				sizeText.selectAll();	
				Font sampleFont3=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				sample.setFont(sampleFont3);
			}
		});
		okButton.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
			Font okFont=new Font(fontText.getText(),style[styleList.getSelectedIndex()],Integer.parseInt(sizeText.getText()));
				editArea.setFont(okFont);
				fontDialog.dispose();
			}
		});
		JPanel samplePanel=new JPanel();
		samplePanel.setBorder(BorderFactory.createTitledBorder("ʾ��"));
		samplePanel.add(sample);
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		panel2.add(fontText);
		panel2.add(styleText);
		panel2.add(sizeText);
		panel2.add(okButton);
		panel3.add(new JScrollPane(fontList));//��JList��ΪJScrollPane���ӿ���ͼ
		panel3.add(new JScrollPane(styleList));
		panel3.add(new JScrollPane(sizeList));
		panel3.add(cancel);
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		con.add(samplePanel);
		fontDialog.setSize(350,340);
		fontDialog.setLocation(200,200);
		fontDialog.setResizable(false);
		fontDialog.setVisible(true);
	}
 
 
	public void actionPerformed(ActionEvent e){
		//�½�
		if(e.getSource()==fileNew){
			editArea.requestFocus();
			String currentValue=editArea.getText();
			boolean isTextChange=(currentValue.equals(oldValue))?false:true;
			if(isTextChange){	
				int saveChoose=JOptionPane.showConfirmDialog(this,"�����ļ���δ���棬�Ƿ񱣴棿","��ʾ",JOptionPane.YES_NO_CANCEL_OPTION);
				if(saveChoose==JOptionPane.YES_OPTION){	
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setDialogTitle("���Ϊ");
					int result=fileChooser.showSaveDialog(this);
					if(result==JFileChooser.CANCEL_OPTION){	
						statusLabel.setText("��δѡ���κ��ļ�");
						return;
					}
					File saveFileName=fileChooser.getSelectedFile();
					if(saveFileName==null || saveFileName.getName().equals("")){	
						JOptionPane.showMessageDialog(this,"�ļ�������","�ļ�������",JOptionPane.ERROR_MESSAGE);
					}
					else {
						try{
						    FileWriter fw=new FileWriter(saveFileName);
							BufferedWriter bfw=new BufferedWriter(fw);
							bfw.write(editArea.getText(),0,editArea.getText().length());
							bfw.flush();//ˢ�¸����Ļ���
							bfw.close();
							isNewFile=false;
							name=saveFileName;
							oldValue=editArea.getText();
							this.setTitle(saveFileName.getName()+" - ���±�");
							statusLabel.setText("��ǰ���ļ���"+saveFileName.getAbsoluteFile());
						}
						catch (IOException ioException){
						}
					}
				}
				else if(saveChoose==JOptionPane.NO_OPTION){	
					editArea.replaceRange("",0,editArea.getText().length());
					statusLabel.setText(" �½��ļ�");
					this.setTitle("�ޱ��� - ���±�");
					isNewFile=true;
					undo.discardAllEdits();	//�������е�"����"����
					editUndo.setEnabled(false);
					oldValue=editArea.getText();
				}
				else if(saveChoose==JOptionPane.CANCEL_OPTION){	
					return;
				}
			}
			else{	
				editArea.replaceRange("",0,editArea.getText().length());
				statusLabel.setText(" �½��ļ�");
				this.setTitle("�ޱ��� - ���±�");
				isNewFile=true;
				undo.discardAllEdits();//�������е�"����"����
				editUndo.setEnabled(false);
				oldValue=editArea.getText();
			}
		}
		//��
		else if(e.getSource()==fileOpen){
			editArea.requestFocus();
			String currentValue=editArea.getText();
			boolean isTextChange=(currentValue.equals(oldValue))?false:true;
			if(isTextChange){
				int saveChoose=JOptionPane.showConfirmDialog(this,"�����ļ���δ���棬�Ƿ񱣴棿","��ʾ",JOptionPane.YES_NO_CANCEL_OPTION);
				if(saveChoose==JOptionPane.YES_OPTION){
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setDialogTitle("���Ϊ");
					int result=fileChooser.showSaveDialog(this);
					if(result==JFileChooser.CANCEL_OPTION){
						statusLabel.setText("��δѡ���κ��ļ�");
						return;
					}
					File saveFileName=fileChooser.getSelectedFile();
					if(saveFileName==null || saveFileName.getName().equals("")){
						JOptionPane.showMessageDialog(this,"�ļ�������","�ļ�������",JOptionPane.ERROR_MESSAGE);
					}
					else {
						try{
						    FileWriter fw=new FileWriter(saveFileName);
							BufferedWriter bfw=new BufferedWriter(fw);
							bfw.write(editArea.getText(),0,editArea.getText().length());
							bfw.flush();//ˢ�¸����Ļ���
							bfw.close();
							isNewFile=false;
							name=saveFileName;
							oldValue=editArea.getText();
							this.setTitle(saveFileName.getName()+" - ���±�");
							statusLabel.setText("��ǰ���ļ���"+saveFileName.getAbsoluteFile());
						}
						catch (IOException ioException){
						}
					}
				}
				else if(saveChoose==JOptionPane.NO_OPTION){	
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setDialogTitle("���ļ�");
					int result=fileChooser.showOpenDialog(this);
					if(result==JFileChooser.CANCEL_OPTION){	
						statusLabel.setText("��δѡ���κ��ļ�");
						return;
					}
					File fileName=fileChooser.getSelectedFile();
					if(fileName==null || fileName.getName().equals("")){
						JOptionPane.showMessageDialog(this,"������ļ���","������ļ���",JOptionPane.ERROR_MESSAGE);
					}
					else{
						try{
					    	FileReader fr=new FileReader(fileName);
							BufferedReader bfr=new BufferedReader(fr);
							editArea.setText("");
							while((str=bfr.readLine())!=null){
								editArea.append(str);
							}
							this.setTitle(fileName.getName()+" - ���±�");
							statusLabel.setText(" ��ǰ���ļ���"+fileName.getAbsoluteFile());
							fr.close();
							isNewFile=false;
							name=fileName;
							oldValue=editArea.getText();
						}
						catch (IOException ioException){
						}
					}
				}
				else{
					return;
				}
			}
			else{
				String str=null;
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
				fileChooser.setDialogTitle("���ļ�");
				int result=fileChooser.showOpenDialog(this);
				if(result==JFileChooser.CANCEL_OPTION){	
					statusLabel.setText(" ��δѡ���κ��ļ� ");
					return;
				}
				File fileName=fileChooser.getSelectedFile();
				if(fileName==null || fileName.getName().equals("")){	
					JOptionPane.showMessageDialog(this,"�ļ�������","�ļ�������",JOptionPane.ERROR_MESSAGE);
				}
				else{
					try{
					FileReader fr=new FileReader(fileName);
						BufferedReader bfr=new BufferedReader(fr);
						editArea.setText("");
						while((str=bfr.readLine())!=null){
							editArea.append(str);
						}
						this.setTitle(fileName.getName()+" - ���±�");
						statusLabel.setText(" ��ǰ���ļ���"+fileName.getAbsoluteFile());
						fr.close();
						isNewFile=false;
						name=fileName;
						oldValue=editArea.getText();
					}
					catch (IOException ioException){
					}
				}
			}
		}
		//����
		else if(e.getSource()==fileSave){	
			editArea.requestFocus();
			if(isNewFile){
				String str=null;
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				fileChooser.setDialogTitle("����");
				int result=fileChooser.showSaveDialog(this);
				if(result==JFileChooser.CANCEL_OPTION){
					statusLabel.setText("��δѡ���κ��ļ�");
					return;
				}
				File saveFileName=fileChooser.getSelectedFile();
				if(saveFileName==null || saveFileName.getName().equals("")){
					JOptionPane.showMessageDialog(this,"�ļ�������","�ļ�������",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try{	
					    FileWriter fw=new FileWriter(saveFileName);
						BufferedWriter bfw=new BufferedWriter(fw);
						bfw.write(editArea.getText(),0,editArea.getText().length());
						bfw.flush();//ˢ�¸����Ļ���
						bfw.close();
						isNewFile=false;
						name=saveFileName;
						oldValue=editArea.getText();
						this.setTitle(saveFileName.getName()+" - ���±�");
						statusLabel.setText("��ǰ���ļ���"+saveFileName.getAbsoluteFile());
					}
					catch (IOException ioException){
					}
				}
			}
			else{
				try{
				    FileWriter fw=new FileWriter(name);
					BufferedWriter bfw=new BufferedWriter(fw);
					bfw.write(editArea.getText(),0,editArea.getText().length());
					bfw.flush();
					fw.close();
				}							
				catch(IOException ioException){					
				}
			}
		}
		//���Ϊ
		else if(e.getSource()==fileSaveAs)
		{	editArea.requestFocus();
			String str=null;
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("���Ϊ");
			int result=fileChooser.showSaveDialog(this);
			if(result==JFileChooser.CANCEL_OPTION){	
				statusLabel.setText("����δѡ���κ��ļ�");
				return;
			}				
			File saveFileName=fileChooser.getSelectedFile();
			if(saveFileName==null||saveFileName.getName().equals("")){	
				JOptionPane.showMessageDialog(this,"�ļ�������","�ļ�������",JOptionPane.ERROR_MESSAGE);
			}	
			else {
				try{
				    FileWriter fw=new FileWriter(saveFileName);
					BufferedWriter bfw=new BufferedWriter(fw);
					bfw.write(editArea.getText(),0,editArea.getText().length());
					bfw.flush();
					fw.close();
					oldValue=editArea.getText();
					this.setTitle(saveFileName.getName()+"  - ���±�");
					statusLabel.setText("����ǰ���ļ�:"+saveFileName.getAbsoluteFile());
				}						
				catch(IOException ioException){					
				}				
			}
		}
		
		//�˳�
		else if(e.getSource()==fileExit){
			int exitChoose=JOptionPane.showConfirmDialog(this,"ȷ��Ҫ�˳���?","�˳���ʾ",JOptionPane.OK_CANCEL_OPTION);
			if(exitChoose==JOptionPane.OK_OPTION){	
				System.exit(0);
			}
			else{
				return;
			}
		}
		//����
		else if(e.getSource()==editUndo || e.getSource()==pmundo){
			editArea.requestFocus();
			if(undo.canUndo()){
				try{
					undo.undo();
				}
				catch (CannotUndoException ex){
					System.out.println("Unable to undo:" + ex);
				}
			}
			if(!undo.canUndo()){
				editUndo.setEnabled(false);
				}
		}
		//����
		else if(e.getSource()==editCut || e.getSource()==pmcut){
			editArea.requestFocus();
			String text=editArea.getSelectedText();
			StringSelection selection=new StringSelection(text);
			clipBoard.setContents(selection,null);
			editArea.replaceRange("",editArea.getSelectionStart(),editArea.getSelectionEnd());
			checkMenuItemEnabled();
		}
		//����
		else if(e.getSource()==editCopy || e.getSource()==pmcopy){
			editArea.requestFocus();
			String text=editArea.getSelectedText();
			StringSelection selection=new StringSelection(text);
			clipBoard.setContents(selection,null);
			checkMenuItemEnabled();
		}
		//ճ��
		else if(e.getSource()==editPaste || e.getSource()==pmpaste){
			editArea.requestFocus();
			Transferable contents=clipBoard.getContents(this);
			if(contents==null)return;
			String text="";
			try{
				text=(String)contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (Exception exception){
			}
			editArea.replaceRange(text,editArea.getSelectionStart(),editArea.getSelectionEnd());
			checkMenuItemEnabled();
		}
		//ɾ��
		else if(e.getSource()==editDelete || e.getSource()==pmDelete){
			editArea.requestFocus();
			editArea.replaceRange("",editArea.getSelectionStart(),editArea.getSelectionEnd());
			checkMenuItemEnabled();	
		}
		//����
		else if(e.getSource()==editFind){	
			editArea.requestFocus();
			find();
		}
		//������һ��
		else if(e.getSource()==editFindnext){
			editArea.requestFocus();
			find();
		}
		//�滻
		else if(e.getSource()==editReplace){
			editArea.requestFocus();
			replace();
		}
		
		//ȫѡ
		else if(e.getSource()==editSelectall || e.getSource()==pmSelectAll){
			editArea.selectAll();
		}
		//�Զ�����(����ǰ������)
		else if(e.getSource()==LineWrap){
			if(LineWrap.getState())
				editArea.setLineWrap(true);
			else 
				editArea.setLineWrap(false);
		}
		//��������
		else if(e.getSource()==formatFont){	
			editArea.requestFocus();
			font();
		}//�������ý���
		//����״̬���ɼ���
		else if(e.getSource()==viewStatus){
			if(viewStatus.getState())
				statusLabel.setVisible(true);
			else 
				statusLabel.setVisible(false);
		}
		
	}
	public void removeUpdate(DocumentEvent e){	
		editUndo.setEnabled(true);
	}
	public void insertUpdate(DocumentEvent e){
		editUndo.setEnabled(true);
	}
	public void changedUpdate(DocumentEvent e){	
		editUndo.setEnabled(true);
	}
 
	
	class UndoHandler implements UndoableEditListener{	
		public void undoableEditHappened(UndoableEditEvent uee){
			undo.addEdit(uee.getEdit());
		}
	}
 
	
	public static void main(String args[]){
		jsb1 notepad=new jsb1();
		notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ʹ�� System exit �����˳�Ӧ�ó���
	}
}
