package chatui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class In implements Runnable {
	private Socket socket;
	private JTextArea area;
	public In(Socket socket,JTextArea area) {
		this.socket=socket;
		this.area=area;
	}
	
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true) {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

				area.setText(br.readLine());

		} }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
