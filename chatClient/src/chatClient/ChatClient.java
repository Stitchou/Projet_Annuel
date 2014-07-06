package chatClient;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



class ChatClient extends JFrame implements Runnable, ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Socket soc;    
    TextField tf;
    TextField tf2;
    TextField tf3;
    TextArea ta;
    TextArea ta2;
    TextArea ta3;
    Button btnSend,btnClose;
    Button btnSend2,btnClose2;
    Button btnSend3,btnClose3;
    String sendTo;
    String LoginName;
    Thread t=null;
    DataOutputStream dout;
    DataInputStream din;
	CardLayout cl = new CardLayout();
	JPanel jpc = new JPanel();
    private ButtonGroup bg; 
    private ButtonGroup bgc; 
    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;
    private JRadioButton r4;
    private JRadioButton n;
    private JRadioButton oui;
    private JRadioButton non;
	JButton jbLeft = new JButton("<-- ");
	JButton jbRight = new JButton(" -->");
	Container ct;
	JPanel card = null;
    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JPanel jpBoutton = new JPanel();
    JPanel jpBoutton2 = new JPanel();
    JPanel jpBoutton3 = new JPanel();
    JPanel jpRadio = new JPanel();
    JPanel nav = new JPanel();
    
    String event_id;
    
    ChatClient(String LoginName,String chatwith) throws Exception {
        super(LoginName);
        this.LoginName = LoginName;
        sendTo = chatwith;
        tf = new TextField(50);
        tf2 = new TextField(50);
        tf3 = new TextField(50);
        ta = new TextArea(50,50);
        ta2 = new TextArea(50,50);
        ta3 = new TextArea(50,50);

        btnSend = new Button("Send");
        btnSend2 = new Button("Send");
        btnSend3 = new Button("Send");

        btnClose = new Button("Close");
        btnClose2 = new Button("Close");
        btnClose3 = new Button("Close");
        
        soc = new Socket("127.0.0.1",4444);

        din = new DataInputStream(soc.getInputStream()); 
        dout = new DataOutputStream(soc.getOutputStream());        
        dout.writeUTF(LoginName);

        t = new Thread(this);
        t.start();
    }

    void setup() {

		setLocationRelativeTo(null);
		setSize(600, 400);
		ct = getContentPane();

        
		jpc.setLayout(cl);
        jp1.setLayout(new GridLayout(2,1));
        jp2.setLayout(new GridLayout(2,1));
        jp3.setLayout(new GridLayout(3,2));
        jp.setLayout(new BorderLayout());
        nav.setLayout(new BorderLayout());
        jpBoutton.setLayout(new FlowLayout());
        jpRadio.setLayout(new FlowLayout());
        
		nav.add(jbLeft, BorderLayout.WEST);
		nav.add(jbRight, BorderLayout.EAST);

        bg = new ButtonGroup();
        bgc = new ButtonGroup();
        r1 = new JRadioButton("radio1");
        r2 = new JRadioButton("radio2");
        r3 = new JRadioButton("radio3");
        r4 = new JRadioButton("radio4");
        n = new JRadioButton("nothing");
        
        oui = new JRadioButton("oui");
        non = new JRadioButton("non");

        JLabel p = new JLabel("Voulez-vous confirmer l'evenement");
        
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);
        bg.add(n);
        
        bgc.add(oui);
        bgc.add(non);

        jpBoutton.add(btnSend);
        jpBoutton.add(btnClose);
        
        jpBoutton2.add(btnSend2);
        jpBoutton2.add(btnClose2);
        
        jpBoutton3.add(btnSend3);
        jpBoutton3.add(btnClose3);
        
        jpRadio.add(r1);
        jpRadio.add(r2);
        jpRadio.add(r3);
        jpRadio.add(r4);
        jpRadio.add(n);

        jp1.add(ta);
        jp1.add(tf);
        jp1.add(jpBoutton);
        jp1.add(jpRadio);
        
        jp2.add(ta2);
        jp2.add(tf2);
        jp2.add(jpBoutton2);

        jp3.add(ta3);
        //jp3.add(tf3);
        jp3.add(jpBoutton3);
        jp3.add(p);
        jp3.add(oui);
        jp3.add(non);
        
		jbLeft.addActionListener(this);
		jbRight.addActionListener(this);

		jp1.setName("ct1");
		jp2.setName("ct2");
		jp3.setName("ct3");
		
        jpc.add(jp1, "ct1");
        jpc.add(jp2, "ct2");
        jpc.add(jp3, "ct3");
        
        jp.add(jpc, BorderLayout.CENTER);
        jp.add(nav, BorderLayout.SOUTH);
        
        ct.add(jp);

		setVisible(true);

    }

    public boolean action(Event e,Object o) {
        if(e.arg.equals("Send")) {
            try {
            	String pseudo = LoginName.substring(0,LoginName.indexOf("&"));
                //dout.writeUTF(pseudo + "&"  + "CONFIRM" + "&" + tf.getText().toString());
            	String typeEvent = "1";
            	String latitude = "2";
            	String longitude = "6";
            	String compName = cardName();
            	String envoi = pseudo;
            	if(compName.equals("ct1")) { // EVENTS
            		envoi += "&" + "EVENTS";
                    if( tf.getText().toString().isEmpty() )
                    	envoi += "&" + latitude + "&" + longitude + "&" + typeEvent ;
                    else {
                    	envoi += "&" + tf.getText().toString();
    	                if( r1.isSelected() )
    	                    envoi += "&1";

    	                if( r2.isSelected() )
    	                    envoi += "&2";

    	                if( r3.isSelected() )
    	                    envoi += "&3";

    	                if( r4.isSelected() )
    	                    envoi += "&4";
                    }
            	} else if(compName.equals("ct2")) { // LOCATION
                	envoi += "&" + "LOCATION";
                    if( tf2.getText().toString().isEmpty() )
                    	envoi += "&3&3";
                    else {
                    	envoi += "&" + tf2.getText().toString();
                    }
            	} else if(compName.equals("ct3")) { // CONFIRM
            		if( !event_id.isEmpty() ){
	            		envoi += "&" + "CONFIRM";
		                if( oui.isSelected() )
		                    envoi += "&1";
	
		                if( non.isSelected() )
		                    envoi += "&0";
		                envoi += "&" + event_id;
            		}
            	}

                dout.writeUTF(envoi);
                ta.append("\n" + LoginName + " Says:" + tf.getText().toString());
                ta2.append("\n" + LoginName + " Says:" + tf2.getText().toString());
                ta3.append("\n" + LoginName + " Says:" + tf3.getText().toString());

                tf.setText("");
                tf2.setText("");
                tf3.setText("");
            } catch(Exception ex) {
            	System.out.println(ex.getMessage());
            }    

        } else if(e.arg.equals("Close")) {
            
        	try {
                String pseudo = LoginName.substring(0,LoginName.indexOf("&"));
                dout.writeUTF(pseudo + "&LOGOUT");
                System.exit(1);
            } catch(Exception ex) {
            	System.out.println(ex.getMessage());
            }
        }
        return super.action(e,o);
    }

    public static void main(String args[]) throws Exception {
        ChatClient Client1 = new ChatClient("red&md5","serv");
        Client1.setup();
        
    } 

    public void run() {        
        while(true) {
            try {
            	String recoit = din.readUTF();
            	String[] recep = recoit.split("&");
            	if (recep[1].equals("LOCATION"))
            		event_id = recep[recep.length - 1];
                ta.append( "\n" + sendTo + " Says :" + recoit);
                ta2.append( "\n" + sendTo + " Says :" + recoit);
                ta3.append( "\n" + sendTo + " Says :" + recoit);
                
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jbLeft))
			cl.previous(jpc);
		if(e.getSource().equals(jbRight))
			cl.next(jpc);
	}
	
	private String cardName(){
		for (Component comp : jpc.getComponents()) {
		    if (comp.isVisible() == true) {
		        card = (JPanel) comp;
		    }
		}
		return card.getName();
	}
}