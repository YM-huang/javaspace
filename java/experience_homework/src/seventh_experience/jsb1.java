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
 
 
public class jsb1 extends JFrame implements ActionListener,DocumentListener{//菜单
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

	String oldValue;//存放编辑区原来的内容，用于比较文本是否有改动
	boolean isNewFile=true;//是否新文件(未保存过的)
	File name;//当前文件名
	//构造函数开始
	public jsb1(){	
		super("记事本");
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		//创建菜单条
		JMenuBar menuBar=new JMenuBar();
		//创建文件菜单及菜单项并注册事件监听
		file=new JMenu("文件(F)");
		file.setMnemonic('F');
		//设置快捷键
		fileNew=new JMenuItem("新建(N)");
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		fileNew.addActionListener(this);
 
		fileOpen=new JMenuItem("打开(O)...");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileOpen.addActionListener(this);
 
		fileSave=new JMenuItem("保存(S)");
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileSave.addActionListener(this);
 
		fileSaveAs=new JMenuItem("另存为(A)...");
		fileSaveAs.addActionListener(this);
		
		fileExit=new JMenuItem("退出(X)");
		fileExit.addActionListener(this);
 
		//创建编辑菜单及菜单项并注册事件监听
		edit=new JMenu("编辑(E)");
		edit.setMnemonic('E');
		//设置快捷键
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
 
		editUndo=new JMenuItem("撤销(U)");
		editUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
		editUndo.addActionListener(this);
		editUndo.setEnabled(false);
 
		editCut=new JMenuItem("剪切(T)");
		editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		editCut.addActionListener(this);
 
		editCopy=new JMenuItem("复制(C)");
		editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		editCopy.addActionListener(this);
 
		editPaste=new JMenuItem("粘贴(P)");
		editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		editPaste.addActionListener(this);
 
		editDelete=new JMenuItem("删除(D)");
		editDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		editDelete.addActionListener(this);
 
		editFind=new JMenuItem("查找(F)...");
		editFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
		editFind.addActionListener(this);
 
		editFindnext=new JMenuItem("查找下一个(N)");
		editFindnext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
		editFindnext.addActionListener(this);
 
		editReplace = new JMenuItem("替换(R)...",'R'); 
		editReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK)); 
		editReplace.addActionListener(this);
 
		editGoto = new JMenuItem("转到(G)...",'G'); 
		editGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK)); 
		editGoto.addActionListener(this);
 
		editSelectall = new JMenuItem("全选",'A'); 
		editSelectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK)); 
		editSelectall.addActionListener(this);
 
		//创建格式菜单及菜单项并注册事件监听
		format=new JMenu("格式(O)");
		format.setMnemonic('O');
 
		LineWrap=new JCheckBoxMenuItem("自动换行(W)");
		LineWrap.setMnemonic('W');
		LineWrap.setState(true);
		LineWrap.addActionListener(this);
 
		formatFont=new JMenuItem("字体(F)...");
		formatFont.addActionListener(this);
 
		//创建查看菜单及菜单项并注册事件监听
		view=new JMenu("查看(V)");
		view.setMnemonic('V');
 
		viewStatus=new JCheckBoxMenuItem("状态栏(S)");
		viewStatus.setMnemonic('S');
		viewStatus.setState(true);
		viewStatus.addActionListener(this);
 
		
		//向菜单条添加"文件"菜单及菜单项
		menuBar.add(file); 
		file.add(fileNew); 
		file.add(fileOpen); 
		file.add(fileSave); 
		file.add(fileSaveAs); 
		file.addSeparator();		
		file.addSeparator();		
		file.add(fileExit); 
 
		//向菜单条添加"编辑"菜单及菜单项 
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
 
		//向菜单条添加"格式"菜单及菜单项		
		menuBar.add(format); 
		format.add(LineWrap); 
		format.add(formatFont);
 
		//向菜单条添加"查看"菜单及菜单项 
		menuBar.add(view); 
		view.add(viewStatus);
		//向窗口添加菜单条				
		this.setJMenuBar(menuBar);
		//创建文本编辑区并添加滚动条
		editArea=new JTextArea(20,50);
		JScrollPane scroller=new JScrollPane(editArea);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroller,BorderLayout.CENTER);//向窗口添加文本编辑区
		editArea.setWrapStyleWord(true);//设置单词在一行不足容纳时换行
		editArea.setLineWrap(true);//设置文本编辑区自动换行默认为true,即会"自动换行"
		//向窗口添加文本编辑区
		oldValue=editArea.getText();//获取原文本编辑区的内容
 
		//编辑区注册事件监听(与撤销操作有关)
		editArea.getDocument().addUndoableEditListener(undoHandler);
		editArea.getDocument().addDocumentListener(this);
 
		//创建右键弹出菜单
		popupmenu=new JPopupMenu();
		pmundo=new JMenuItem("撤销(U)");
		pmcut=new JMenuItem("剪切(T)");
		pmcopy=new JMenuItem("复制(C)");
		pmpaste=new JMenuItem("粘帖(P)");
		pmDelete=new JMenuItem("删除(D)");
		pmSelectAll=new JMenuItem("全选(A)");
 
		pmundo.setEnabled(false);
 
		//向右键菜单添加菜单项和分隔符
		popupmenu.add(pmundo);
		popupmenu.addSeparator();
		popupmenu.add(pmcut);
		popupmenu.add(pmcopy);
		popupmenu.add(pmpaste);
		popupmenu.add(pmDelete);
		popupmenu.addSeparator();
		popupmenu.add(pmSelectAll);
 
		//文本编辑区注册右键菜单事件
		pmundo.addActionListener(this);
		pmcut.addActionListener(this);
		pmcopy.addActionListener(this);
		pmpaste.addActionListener(this);
		pmDelete.addActionListener(this);
		pmSelectAll.addActionListener(this);
 
		//文本编辑区注册右键菜单事件
		editArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
			if(e.isPopupTrigger()){
				popupmenu.show(e.getComponent(),e.getX(),e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单
				}
				checkMenuItemEnabled();//设置剪切，复制，粘帖，删除等功能的可用性
				editArea.requestFocus();//编辑区获取焦点
			}
			public void mouseReleased(MouseEvent e){
				if(e.isPopupTrigger()){
					popupmenu.show(e.getComponent(),e.getX(),e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单
				}
				checkMenuItemEnabled();//设置剪切，复制，粘帖，删除等功能的可用性
				editArea.requestFocus();//编辑区获取焦点
			}
		});//文本编辑区注册右键菜单事件结束

		//创建和添加状态栏
		statusLabel=new JLabel("　按F1获取帮助");
		this.add(statusLabel,BorderLayout.SOUTH);//向窗口添加状态栏标签

		//设置窗口在屏幕上的位置、大小和可见性 
		this.setLocation(100,100);
		this.setSize(650,550);
		this.setVisible(true);
		//添加窗口监听器
		addWindowListener(new WindowAdapter(){	
			public void windowClosing(WindowEvent e){	
			exitWindowChoose();
			}
		});
 
		checkMenuItemEnabled();
		editArea.requestFocus();
	}
	
	//设置菜单项的可用性：剪切，复制，粘帖，删除功能
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
		//粘帖功能可用性判断
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
 
	//关闭窗口时调用
	public void exitWindowChoose(){
		editArea.requestFocus();
		String currentValue=editArea.getText();
		if(currentValue.equals(oldValue)==true){
			System.exit(0);
		}
		else{
			int exitChoose=JOptionPane.showConfirmDialog(this,"您的文件尚未保存，是否保存？","退出提示",JOptionPane.YES_NO_CANCEL_OPTION);
			if(exitChoose==JOptionPane.YES_OPTION){	
				if(isNewFile){	
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileChooser.setApproveButtonText("确定");
					fileChooser.setDialogTitle("另存为");
					
					int result=fileChooser.showSaveDialog(this);
					
					if(result==JFileChooser.CANCEL_OPTION){
						statusLabel.setText("　您未保存文件");
						return;
					}					
	
					File saveFileName=fileChooser.getSelectedFile();
					if(saveFileName==null||saveFileName.getName().equals("")){
						JOptionPane.showMessageDialog(this,"文件名错误","文件名错误",JOptionPane.ERROR_MESSAGE);
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
							
							this.setTitle(saveFileName.getName()+"  - 记事本");
							statusLabel.setText("　当前打开文件:"+saveFileName.getAbsoluteFile());
							
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
 
	//查找方法
	public void find(){
		final JDialog findDialog=new JDialog(this,"查找",false);
		Container con=findDialog.getContentPane();//返回此对话框的contentPane对象	
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel findContentLabel=new JLabel("查找内容(N)：");
		final JTextField findText=new JTextField(15);
		JButton findNextButton=new JButton("查找下一个(F)：");
		final JCheckBox matchCheckBox=new JCheckBox("区分大小写(C)");
		ButtonGroup bGroup=new ButtonGroup();
		final JRadioButton upButton=new JRadioButton("向上(U)");
		final JRadioButton downButton=new JRadioButton("向下(U)");
		downButton.setSelected(true);
		bGroup.add(upButton);
		bGroup.add(downButton);
		JButton cancel=new JButton("取消");
		//取消按钮事件处理
		cancel.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{	findDialog.dispose();
			}
		});
		//"查找下一个"按钮监听
		findNextButton.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
			//"区分大小写(C)"的JCheckBox是否被选中
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
						JOptionPane.showMessageDialog(null,"找不到您查找的内容！","查找",JOptionPane.INFORMATION_MESSAGE);
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
						JOptionPane.showMessageDialog(null,"找不到您查找的内容！","查找",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		//创建"查找"对话框的界面
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel directionPanel=new JPanel();
		directionPanel.setBorder(BorderFactory.createTitledBorder("方向"));
		//设置directionPanel组件的边框;
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
		findDialog.setResizable(false);//不可调整大小
		findDialog.setLocation(230,280);
		findDialog.setVisible(true);
	}
	
	//替换方法
	public void replace(){
		final JDialog replaceDialog=new JDialog(this,"替换",false);//false时允许其他窗口同时处于激活状态(即无模式)
		Container con=replaceDialog.getContentPane();//返回此对话框的contentPane对象
		con.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel findContentLabel=new JLabel("查找内容(N)：");
		final JTextField findText=new JTextField(15);
		JButton findNextButton=new JButton("查找下一个(F):");
		JLabel replaceLabel=new JLabel("替换为(P)：");
		final JTextField replaceText=new JTextField(15);
		JButton replaceButton=new JButton("替换(R)");
		JButton replaceAllButton=new JButton("全部替换(A)");
		JButton cancel=new JButton("取消");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
			replaceDialog.dispose();
			}
		});
		final JCheckBox matchCheckBox=new JCheckBox("区分大小写(C)");
		ButtonGroup bGroup=new ButtonGroup();
		final JRadioButton upButton=new JRadioButton("向上(U)");
		final JRadioButton downButton=new JRadioButton("向下(U)");
		downButton.setSelected(true);
		bGroup.add(upButton);
		bGroup.add(downButton);
		
		//"查找下一个"按钮监听
		findNextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			//"区分大小写(C)"的JCheckBox是否被选中
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
						JOptionPane.showMessageDialog(null,"找不到您查找的内容！","查找",JOptionPane.INFORMATION_MESSAGE);
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
						JOptionPane.showMessageDialog(null,"找不到您查找的内容！","查找",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		//"替换"按钮监听
		replaceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			if(replaceText.getText().length()==0 && editArea.getSelectedText()!=null) 
					editArea.replaceSelection(""); 
				if(replaceText.getText().length()>0 && editArea.getSelectedText()!=null) 
					editArea.replaceSelection(replaceText.getText());
			}
		});
		
		//"全部替换"按钮监听
		replaceAllButton.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){
			    editArea.setCaretPosition(0);	//将光标放到编辑区开头	
				int k=0,m=0,replaceCount=0;
				if(findText.getText().length()==0){
					JOptionPane.showMessageDialog(replaceDialog,"请填写查找内容!","提示",JOptionPane.WARNING_MESSAGE);
					findText.requestFocus(true);
					return;
				}
				while(k>-1){
					//"区分大小写(C)"的JCheckBox是否被选中
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
							JOptionPane.showMessageDialog(replaceDialog, "找不到您查找的内容!", "记事本",JOptionPane.INFORMATION_MESSAGE); 
							}
							else{
								JOptionPane.showMessageDialog(replaceDialog,"成功替换"+replaceCount+"个匹配内容!","替换成功",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(replaceDialog, "找不到您查找的内容!", "记事本",JOptionPane.INFORMATION_MESSAGE); 
							}
							else{
								JOptionPane.showMessageDialog(replaceDialog,"成功替换"+replaceCount+"个匹配内容!","替换成功",JOptionPane.INFORMATION_MESSAGE);
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
				}//while循环结束
			}
		});
		
		//创建"替换"对话框的界面
		JPanel directionPanel=new JPanel();
		directionPanel.setBorder(BorderFactory.createTitledBorder("方向"));
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
		replaceDialog.setResizable(false);//不可调整大小
		replaceDialog.setLocation(230,280);
		replaceDialog.setVisible(true);
	}
	//"字体"方法
	public void font(){
		final JDialog fontDialog=new JDialog(this,"字体设置",false);
		Container con=fontDialog.getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel fontLabel=new JLabel("字体(F)：");
		fontLabel.setPreferredSize(new Dimension(100,20));//构造一个Dimension，并将其初始化为指定宽度和高度
		JLabel styleLabel=new JLabel("字形(Y)：");
		styleLabel.setPreferredSize(new Dimension(100,20));
		JLabel sizeLabel=new JLabel("大小(S)：");
		sizeLabel.setPreferredSize(new Dimension(100,20));
		final JLabel sample=new JLabel("记事本");
		final JTextField fontText=new JTextField(9);
		fontText.setPreferredSize(new Dimension(200,20));
		final JTextField styleText=new JTextField(8);
		styleText.setPreferredSize(new Dimension(200,20));
		final int style[]={Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
		final JTextField sizeText=new JTextField(5);
		sizeText.setPreferredSize(new Dimension(200,20));
		JButton okButton=new JButton("确定");
		JButton cancel=new JButton("取消");
		cancel.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{	fontDialog.dispose();	
			}
		});
		Font currentFont=editArea.getFont();
		fontText.setText(currentFont.getFontName());
		fontText.selectAll();
		
		if(currentFont.getStyle()==Font.PLAIN)
			styleText.setText("常规");
		else if(currentFont.getStyle()==Font.BOLD)
			styleText.setText("粗体");
		else if(currentFont.getStyle()==Font.ITALIC)
			styleText.setText("斜体");
		else if(currentFont.getStyle()==(Font.BOLD+Font.ITALIC))
			styleText.setText("粗斜体");
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
		final String fontStyle[]={"常规","粗体","斜体","粗斜体"};
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
		samplePanel.setBorder(BorderFactory.createTitledBorder("示例"));
		samplePanel.add(sample);
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		panel2.add(fontText);
		panel2.add(styleText);
		panel2.add(sizeText);
		panel2.add(okButton);
		panel3.add(new JScrollPane(fontList));//以JList作为JScrollPane的视口视图
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
		//新建
		if(e.getSource()==fileNew){
			editArea.requestFocus();
			String currentValue=editArea.getText();
			boolean isTextChange=(currentValue.equals(oldValue))?false:true;
			if(isTextChange){	
				int saveChoose=JOptionPane.showConfirmDialog(this,"您的文件尚未保存，是否保存？","提示",JOptionPane.YES_NO_CANCEL_OPTION);
				if(saveChoose==JOptionPane.YES_OPTION){	
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setDialogTitle("另存为");
					int result=fileChooser.showSaveDialog(this);
					if(result==JFileChooser.CANCEL_OPTION){	
						statusLabel.setText("您未选择任何文件");
						return;
					}
					File saveFileName=fileChooser.getSelectedFile();
					if(saveFileName==null || saveFileName.getName().equals("")){	
						JOptionPane.showMessageDialog(this,"文件名错误","文件名错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						try{
						    FileWriter fw=new FileWriter(saveFileName);
							BufferedWriter bfw=new BufferedWriter(fw);
							bfw.write(editArea.getText(),0,editArea.getText().length());
							bfw.flush();//刷新该流的缓冲
							bfw.close();
							isNewFile=false;
							name=saveFileName;
							oldValue=editArea.getText();
							this.setTitle(saveFileName.getName()+" - 记事本");
							statusLabel.setText("当前打开文件："+saveFileName.getAbsoluteFile());
						}
						catch (IOException ioException){
						}
					}
				}
				else if(saveChoose==JOptionPane.NO_OPTION){	
					editArea.replaceRange("",0,editArea.getText().length());
					statusLabel.setText(" 新建文件");
					this.setTitle("无标题 - 记事本");
					isNewFile=true;
					undo.discardAllEdits();	//撤消所有的"撤消"操作
					editUndo.setEnabled(false);
					oldValue=editArea.getText();
				}
				else if(saveChoose==JOptionPane.CANCEL_OPTION){	
					return;
				}
			}
			else{	
				editArea.replaceRange("",0,editArea.getText().length());
				statusLabel.setText(" 新建文件");
				this.setTitle("无标题 - 记事本");
				isNewFile=true;
				undo.discardAllEdits();//撤消所有的"撤消"操作
				editUndo.setEnabled(false);
				oldValue=editArea.getText();
			}
		}
		//打开
		else if(e.getSource()==fileOpen){
			editArea.requestFocus();
			String currentValue=editArea.getText();
			boolean isTextChange=(currentValue.equals(oldValue))?false:true;
			if(isTextChange){
				int saveChoose=JOptionPane.showConfirmDialog(this,"您的文件尚未保存，是否保存？","提示",JOptionPane.YES_NO_CANCEL_OPTION);
				if(saveChoose==JOptionPane.YES_OPTION){
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setDialogTitle("另存为");
					int result=fileChooser.showSaveDialog(this);
					if(result==JFileChooser.CANCEL_OPTION){
						statusLabel.setText("您未选择任何文件");
						return;
					}
					File saveFileName=fileChooser.getSelectedFile();
					if(saveFileName==null || saveFileName.getName().equals("")){
						JOptionPane.showMessageDialog(this,"文件名错误","文件名错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						try{
						    FileWriter fw=new FileWriter(saveFileName);
							BufferedWriter bfw=new BufferedWriter(fw);
							bfw.write(editArea.getText(),0,editArea.getText().length());
							bfw.flush();//刷新该流的缓冲
							bfw.close();
							isNewFile=false;
							name=saveFileName;
							oldValue=editArea.getText();
							this.setTitle(saveFileName.getName()+" - 记事本");
							statusLabel.setText("当前打开文件："+saveFileName.getAbsoluteFile());
						}
						catch (IOException ioException){
						}
					}
				}
				else if(saveChoose==JOptionPane.NO_OPTION){	
					String str=null;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setDialogTitle("打开文件");
					int result=fileChooser.showOpenDialog(this);
					if(result==JFileChooser.CANCEL_OPTION){	
						statusLabel.setText("您未选择任何文件");
						return;
					}
					File fileName=fileChooser.getSelectedFile();
					if(fileName==null || fileName.getName().equals("")){
						JOptionPane.showMessageDialog(this,"错误的文件名","错误的文件名",JOptionPane.ERROR_MESSAGE);
					}
					else{
						try{
					    	FileReader fr=new FileReader(fileName);
							BufferedReader bfr=new BufferedReader(fr);
							editArea.setText("");
							while((str=bfr.readLine())!=null){
								editArea.append(str);
							}
							this.setTitle(fileName.getName()+" - 记事本");
							statusLabel.setText(" 当前打开文件："+fileName.getAbsoluteFile());
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
			
				fileChooser.setDialogTitle("打开文件");
				int result=fileChooser.showOpenDialog(this);
				if(result==JFileChooser.CANCEL_OPTION){	
					statusLabel.setText(" 您未选择任何文件 ");
					return;
				}
				File fileName=fileChooser.getSelectedFile();
				if(fileName==null || fileName.getName().equals("")){	
					JOptionPane.showMessageDialog(this,"文件名错误","文件名错误",JOptionPane.ERROR_MESSAGE);
				}
				else{
					try{
					FileReader fr=new FileReader(fileName);
						BufferedReader bfr=new BufferedReader(fr);
						editArea.setText("");
						while((str=bfr.readLine())!=null){
							editArea.append(str);
						}
						this.setTitle(fileName.getName()+" - 记事本");
						statusLabel.setText(" 当前打开文件："+fileName.getAbsoluteFile());
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
		//保存
		else if(e.getSource()==fileSave){	
			editArea.requestFocus();
			if(isNewFile){
				String str=null;
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				fileChooser.setDialogTitle("保存");
				int result=fileChooser.showSaveDialog(this);
				if(result==JFileChooser.CANCEL_OPTION){
					statusLabel.setText("您未选择任何文件");
					return;
				}
				File saveFileName=fileChooser.getSelectedFile();
				if(saveFileName==null || saveFileName.getName().equals("")){
					JOptionPane.showMessageDialog(this,"文件名错误","文件名错误",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try{	
					    FileWriter fw=new FileWriter(saveFileName);
						BufferedWriter bfw=new BufferedWriter(fw);
						bfw.write(editArea.getText(),0,editArea.getText().length());
						bfw.flush();//刷新该流的缓冲
						bfw.close();
						isNewFile=false;
						name=saveFileName;
						oldValue=editArea.getText();
						this.setTitle(saveFileName.getName()+" - 记事本");
						statusLabel.setText("当前打开文件："+saveFileName.getAbsoluteFile());
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
		//另存为
		else if(e.getSource()==fileSaveAs)
		{	editArea.requestFocus();
			String str=null;
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("另存为");
			int result=fileChooser.showSaveDialog(this);
			if(result==JFileChooser.CANCEL_OPTION){	
				statusLabel.setText("　您未选择任何文件");
				return;
			}				
			File saveFileName=fileChooser.getSelectedFile();
			if(saveFileName==null||saveFileName.getName().equals("")){	
				JOptionPane.showMessageDialog(this,"文件名错误","文件名错误",JOptionPane.ERROR_MESSAGE);
			}	
			else {
				try{
				    FileWriter fw=new FileWriter(saveFileName);
					BufferedWriter bfw=new BufferedWriter(fw);
					bfw.write(editArea.getText(),0,editArea.getText().length());
					bfw.flush();
					fw.close();
					oldValue=editArea.getText();
					this.setTitle(saveFileName.getName()+"  - 记事本");
					statusLabel.setText("　当前打开文件:"+saveFileName.getAbsoluteFile());
				}						
				catch(IOException ioException){					
				}				
			}
		}
		
		//退出
		else if(e.getSource()==fileExit){
			int exitChoose=JOptionPane.showConfirmDialog(this,"确定要退出吗?","退出提示",JOptionPane.OK_CANCEL_OPTION);
			if(exitChoose==JOptionPane.OK_OPTION){	
				System.exit(0);
			}
			else{
				return;
			}
		}
		//撤销
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
		//剪切
		else if(e.getSource()==editCut || e.getSource()==pmcut){
			editArea.requestFocus();
			String text=editArea.getSelectedText();
			StringSelection selection=new StringSelection(text);
			clipBoard.setContents(selection,null);
			editArea.replaceRange("",editArea.getSelectionStart(),editArea.getSelectionEnd());
			checkMenuItemEnabled();
		}
		//复制
		else if(e.getSource()==editCopy || e.getSource()==pmcopy){
			editArea.requestFocus();
			String text=editArea.getSelectedText();
			StringSelection selection=new StringSelection(text);
			clipBoard.setContents(selection,null);
			checkMenuItemEnabled();
		}
		//粘帖
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
		//删除
		else if(e.getSource()==editDelete || e.getSource()==pmDelete){
			editArea.requestFocus();
			editArea.replaceRange("",editArea.getSelectionStart(),editArea.getSelectionEnd());
			checkMenuItemEnabled();	
		}
		//查找
		else if(e.getSource()==editFind){	
			editArea.requestFocus();
			find();
		}
		//查找下一个
		else if(e.getSource()==editFindnext){
			editArea.requestFocus();
			find();
		}
		//替换
		else if(e.getSource()==editReplace){
			editArea.requestFocus();
			replace();
		}
		
		//全选
		else if(e.getSource()==editSelectall || e.getSource()==pmSelectAll){
			editArea.selectAll();
		}
		//自动换行(已在前面设置)
		else if(e.getSource()==LineWrap){
			if(LineWrap.getState())
				editArea.setLineWrap(true);
			else 
				editArea.setLineWrap(false);
		}
		//字体设置
		else if(e.getSource()==formatFont){	
			editArea.requestFocus();
			font();
		}//字体设置结束
		//设置状态栏可见性
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
		notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使用 System exit 方法退出应用程序
	}
}
