package seventh_experience;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class NoteBook {
	public static void main(String[] args) {
		Text t = new Text("DocumentFile", null);
		t.CreatText();
	}
}

class Text {
	final static String WorkPath = "D:\\File\\";
	final static String WorkName = "NewDocumentFile.txt";
	final static String HelpPage = "/*\r\n" + " * @author YancyKahn\r\n" + " * \r\n" + " * @version 2.0\r\n" + " * \r\n"
			+ " * @date 2018/4/9\r\n" + " * \r\n" + " * @email 2205776318@qq.com\r\n" + " * \r\n"
			+ " * @blog https://my.csdn.net/qq_37753409\r\n" + " */";
	String name;
	JFrame frame;
	JTextArea tArea = new JTextArea();

	Text(String _name, String res) {
		this.name = _name;
		this.frame = new JFrame(this.name);
		tArea.setText(res);
	}

	Text() {

	}

	void CreatText() {
		/*
		 * Jframe properties
		 */
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jsPane = new JScrollPane(tArea);
		frame.add(jsPane);
		frame.setJMenuBar(addMenuBar());
		frame.setVisible(true);
	}

	private JMenuBar addMenuBar() {
		JMenuBar menubar = new JMenuBar();
		/*
		 * 创建一级菜单
		 */
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");
		JMenu helpMenu = new JMenu("help");

		/*
		 * add
		 */
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(viewMenu);
		menubar.add(helpMenu);

		/*
		 * fileMenu
		 */
		JMenuItem newMenuItem = new JMenuItem("new");
		JMenuItem openMenuItem = new JMenuItem("open");
		JMenuItem saveAsMenuItem = new JMenuItem("save as");
		JMenuItem saveMenuItem = new JMenuItem("save");
		JMenuItem exitMenuItem = new JMenuItem("exit");
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator(); // separator
		fileMenu.add(exitMenuItem);

		/*
		 * editMenu
		 */
		JMenuItem copyMenuItem = new JMenuItem("copy");
		JMenuItem pasteMenuItem = new JMenuItem("paste");
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);

		/*
		 * viewMenu
		 */
		JMenuItem statisticsMenuItem = new JMenuItem("statistics");
		viewMenu.add(statisticsMenuItem);

		/*
		 * helpMenu
		 */
		JMenuItem ViewHelpMenuItem = new JMenuItem("view help");
		JMenuItem AboutHelpMenuItem = new JMenuItem("about help");
		helpMenu.add(ViewHelpMenuItem);
		helpMenu.addSeparator();
		helpMenu.add(AboutHelpMenuItem);

		/*
		 * ActionListener
		 */

		// fileMenu
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Text t = new Text("new Document", null);
				t.CreatText();
			}
		});
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OpenItem();
			}
		});
		saveAsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SaveFile(WorkPath, WorkName, tArea.getText());
			}
		});
		saveAsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SaveItem();
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});

		// editMenu
		copyMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setSysClipboardText(tArea.getText());
			}
		});

		pasteMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = getSysClipboardText();
				tArea.setText(s);
			}
		});

		// viewMenu
		statisticsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = tArea.getText();
				int alphaCount = 0;
				int digitCount = 0;
				int otherCount = 0;
				for (int i = 0; i < s.length(); ++i) {
					if (Character.isDigit(s.charAt(i)))
						digitCount++;
					else if (Character.isAlphabetic(s.charAt(i)))
						alphaCount++;
					else
						otherCount++;
				}

				JFrame sframe = new JFrame("Statistics");
				sframe.setSize(300, 150);
				// sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sframe.setLayout(new GridLayout(3, 1));
				sframe.setLocationRelativeTo(null);
				JLabel alpha = new JLabel("   Alpha: " + alphaCount);
				JLabel digit = new JLabel("   Digit: " + digitCount);
				JLabel other = new JLabel("   Other: " + otherCount);
				sframe.add(alpha);
				sframe.add(digit);
				sframe.add(other);

				sframe.addWindowListener(new WindowAdapter() {
					@SuppressWarnings("unused")
					public void WindowClosing(WindowEvent e) {
						sframe.dispose();
					}
				});
				sframe.setVisible(true);
			}
		});

		// helpMenu
		ViewHelpMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Text t = new Text("Help page", HelpPage);
				t.CreatText();
			}
		});
		return menubar;
	}

	/*
	 * save item
	 */

	private void SaveItem() {
		JFrame iframe = new JFrame("Save");
		// iframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iframe.setLocationRelativeTo(null);

		/*
		 * panel
		 */
		JPanel panelTop = new JPanel();
		JPanel panelMid = new JPanel();
		JPanel panelBut = new JPanel();
		JTextField textFilePath = new JTextField(10);
		JTextField textFileName = new JTextField(10);
		JButton SaveButton = new JButton("Save");
		iframe.setSize(300, 200);
		Box vbox = Box.createVerticalBox();
		panelTop.add(new JLabel("file path"));
		panelTop.add(textFilePath);
		panelMid.add(new JLabel("file name"));
		panelMid.add(textFileName);
		panelBut.add(SaveButton);

		vbox.add(panelTop);
		vbox.add(panelMid);
		vbox.add(panelBut);
		iframe.add(vbox);
		/*
		 * saveButton ActionListener
		 */

		SaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SaveFile(textFilePath.getText(), textFileName.getText(), tArea.getText());
				iframe.dispose();
			}
		});

		iframe.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unused")
			public void WindowClosing(WindowEvent e) {
				iframe.dispose();
			}
		});
		iframe.setVisible(true);
	}

	/*
	 * open item
	 */

	private void OpenItem() {
		JFrame oframe = new JFrame("open");
		oframe.setLocationRelativeTo(null);
		// oframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * panel
		 */
		JPanel panelTop = new JPanel();
		JPanel panelBut = new JPanel();
		JTextField textFilePathName = new JTextField(10);
		JButton OpenButton = new JButton("Open");
		oframe.setSize(300, 150);
		Box vbox = Box.createVerticalBox();
		panelTop.add(new JLabel("file path and name"));
		panelTop.add(textFilePathName);
		panelBut.add(OpenButton);

		vbox.add(panelTop);
		vbox.add(panelBut);
		oframe.add(vbox);

		/*
		 * button ActionListener
		 */

		OpenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s = ReadFile(textFilePathName.getText());
				// System.out.println(s);
				tArea.setText(s);
				oframe.dispose();
			}
		});

		oframe.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unused")
			public void WindowClosing(WindowEvent e) {
				oframe.dispose();
			}
		});
		oframe.setVisible(true);

	}

	/*
	 * save file
	 */
	private void SaveFile(String savePath, String saveName, String res) {
		File file = new File(savePath + saveName);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(res);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * read file
	 */
	private String ReadFile(String openPathName) {
		String res = "";
		File file = new File(openPathName);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = br.readLine();
			if (line != null)
				res += line;
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * get system clipboard text
	 */
	private String getSysClipboardText() {
		String res = "";
		Clipboard sysclip = Toolkit.getDefaultToolkit().getSystemClipboard();

		// 获取剪切板的内容
		Transferable clipif = sysclip.getContents(null);

		if (clipif != null) {
			// 检查为文本类型
			if (clipif.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					res = (String) clipif.getTransferData(DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return res;
	}

	/*
	 * set system clipboard text
	 */

	private void setSysClipboardText(String writeFile) {
		Clipboard sysclip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable clipif = sysclip.getContents(writeFile);
		sysclip.setContents(clipif, null);
	}
}