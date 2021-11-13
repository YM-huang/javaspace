package chatroom;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;

public class ChatRoom extends JApplet implements Runnable, ActionListener {


	protected Socket s;
	protected ObjectInputStream i;
	protected ObjectOutputStream o;
	protected DataObject dO;
	protected JTextArea output;
	protected JTextField input;
	protected Thread listener;
	protected boolean first = true, kill=false;
	protected String name = "";
	protected JLabel initLabel, nameLabel;
	//JTextArea outputname;
	JScrollPane sp, lsp;
	private JList list;
	private DefaultListModel listModel;
//	 The LogOUT button
	protected Button lob;
	int count=0;



   	




	public void init(){

/*
   		String stringName = (String)JOptionPane.showInputDialog(
                this,
                "Please Enter Your Name",
                "Chatroom",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

*/
   		String host = "169.254.216.166"; //getParameter();
		String port = "9000"; //getParameter();

		JLabel initLabel = new JLabel(" Enter Your Name BELOW to login or : ");
		JLabel nameLabel = new JLabel(" Message here: ");

		Container cp = getContentPane();

		cp.setLayout(new BorderLayout());


		JPanel p1 = new JPanel();
		p1.setLayout (new BorderLayout ());
		p1.add(output = new JTextArea(20,30),BorderLayout.NORTH);
		JScrollPane sp = new JScrollPane(output);
		p1.add(sp);
    	output.setEditable (false);

    	JPanel p2 = new JPanel();
    	p2.add(nameLabel,BorderLayout.EAST);
	p2.add(input = new JTextField (30), BorderLayout.WEST);
    	input.addActionListener(this);


    	JPanel p3 = new JPanel();
    	p3.add(initLabel);

//    	The logout button
	p3.add(lob = new Button("Logout"));
	lob.setEnabled(true);
	lob.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				logout();

				lob.setEnabled(false);

			}
		});

    	listModel = new DefaultListModel();
   	listModel.addElement("        ");
    	JPanel p4 = new JPanel();
        //Create the list and put it in a scroll pane.
       	p4.add(list = new JList(listModel),BorderLayout.WEST);
       	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.setVisibleRowCount(17);
            JScrollPane lsp = new JScrollPane(list);
            p4.add(lsp);
           output.setEditable (false);

		p1.setBackground(new Color(255,255,204));
    		p2.setBackground(new Color(255,255,204));
    		p3.setBackground(new Color(255,255,204));
    		p4.setBackground(new Color(255,255,204));

    		p1.setBorder(BorderFactory.createMatteBorder(1,1,2,5,Color.blue));
    		p1.setBorder(BorderFactory.createTitledBorder(""));
    		p2.setBorder(BorderFactory.createTitledBorder(""));
		p3.setBorder(BorderFactory.createTitledBorder(""));
		p4.setBorder(BorderFactory.createTitledBorder(""));


        	cp.add(p1, BorderLayout.CENTER);
        	cp.add(p2, BorderLayout.SOUTH);
    	cp.add(p3 , BorderLayout.NORTH);
    	cp.add(p4,BorderLayout.WEST);



		dO=new DataObject();

		try{
    		s = new Socket (host, Integer.parseInt (port));
      		o = new ObjectOutputStream(s.getOutputStream());
		i = new ObjectInputStream(s.getInputStream());

    		}catch(Exception e){System.out.println("Stream creation problem.");}
      	dO.setName("New User");
		dO.setMessage("Entering");


		try{
			o.writeObject(dO);

			}catch(IOException e){};
		input.requestFocus ();
    		listener = new Thread (this);
    		listener.start ();


	}


	public void stop(){
		kill=true;
		listener = null;

	}

	public void run () {
     		if(kill){
          		return;
     		}
     		try {



      		while (true) {

      		System.out.println(count);
      		DataObject dN =(DataObject)i.readObject();
      		String line = dN.getName()+": "+dN.getMessage();
      	        output.append(line + "\n");
      	        if (dN.getConnect()){
      	        	listModel.addElement(dN.getName());
      	        }

      	      	System.out.println("you are: " + count);
          		//System.out.println(dO.getName());
          		//System.out.println(dO.getMessage());
      		}
      		}catch (IOException ex) {
      		ex.printStackTrace ();
    		} catch(ClassNotFoundException e){
			System.out.println("Can't find DataObject.");
    		} finally {

      		input.setVisible(false);
      		validate ();

      		try {

        			o.close ();
      		} catch (IOException ex) {
			  ex.printStackTrace ();
      		}

    		}

  	}

  	public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == input) {
    			DataObject d = new DataObject();
      		try {
				if (first){

					d.setName(input.getText());
					name=input.getText();
					d.setMessage("HAS ENTERED");
					d.setConnect(true);
					first=false;
				}

				else{
					d.setName(name);
					d.setMessage(input.getText());
					d.setConnect(false);
				}
            		o.writeObject(d);

      		} catch (IOException ex) {
        			ex.printStackTrace();
        			kill=true;
      		}
      		input.setText ("");

			return;
    		}
    		return;

  	}


  	//Logout
	public void logout()
	{
			try {
			if (s != null)
				s.close();
			} catch (IOException ign) {}
	}

}

