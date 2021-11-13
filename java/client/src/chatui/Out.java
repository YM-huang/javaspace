package chatui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextField;

public class Out implements Runnable{
	Scanner sc=new Scanner(System.in);
	private Socket socket;
	private String text;
	JTextField a2;
	public Out(Socket socket,JTextField a2) {
	this.socket=socket;
	this.a2=a2;
	}

	@Override
	public void run() {
		
		OutputStream out;
		try {
			out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			PrintWriter pw = new PrintWriter(osw, true);
			a2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					pw.println(a2.getText());
				}
			});
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}			
		
	}
	}
