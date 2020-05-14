package dev.roshan.baaja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ExitFrame 
{
	JFrame exitFrame;
	ImageIcon iconShakey, iconClose, iconOK, iconCancel;
	Image imageClose, imageOK, imageCancel;
	JPanel pnlexitHdr;
	JButton btnCloseExit, btnOK, btnCancel;
	JLabel lblConfirm;
	
	public ExitFrame()
	{
		////////////////////////////icon_close starts here ////////////////////////////////////
		iconClose  = new ImageIcon("src/assets/PNGClose.png");
		imageClose = iconClose.getImage();
		imageClose = imageClose.getScaledInstance(39,39, Image.SCALE_SMOOTH);
		iconClose.setImage(imageClose);
		//////////////////////////// icon_close closed here /////////////////////////////////////
		
	}//constructor closed here
	
	
	public void exitframe()
	{
		exitFrame=new JFrame("Exit Baaja");
		exitFrame.setSize(300,150);
		exitFrame.setUndecorated(true);
		exitFrame.setOpacity(0.9f);
		exitFrame.setLocationRelativeTo(null);
		exitFrame.setLayout(null);
		exitFrame.getContentPane().setBackground(new Color(38, 38, 38));
		
		iconShakey  = new ImageIcon("src/asstes/shakey.png");
		Image imageShakey = iconShakey.getImage();
		iconShakey.setImage(imageShakey);
		exitFrame.setIconImage(imageShakey);
    	exitFrame.setVisible(true);
	    	
	    pnlexitHdr=new JPanel();
		pnlexitHdr.setBackground(Color.black);
		pnlexitHdr.setLayout(null);
		pnlexitHdr.setBounds(0, 0, 300,50);
	    
	    btnCloseExit = new JButton();
		btnCloseExit.setBounds(255, 5, 40, 40);
		btnCloseExit.setFocusPainted(false);
		btnCloseExit.setBorderPainted(false);
		btnCloseExit.setContentAreaFilled(false);
		btnCloseExit.setToolTipText("Cancel");
		btnCloseExit.setBackground(Color.black);
		btnCloseExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
				exitFrame.dispose();
		    }          

		});
		btnCloseExit.setIcon(iconClose);
		
		lblConfirm=new JLabel("Confirm Exit");
		lblConfirm.setBounds(10,5,235,40);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setForeground(Color.WHITE);
		lblConfirm.setFont(new Font("Times New Roman",Font.BOLD,18));
		pnlexitHdr.add(lblConfirm);
		
		
		iconOK  = new ImageIcon("src/assets/btnOK.png");//Billing_button image
	    imageOK = iconOK.getImage();
	    imageOK = imageOK.getScaledInstance(98,38, Image.SCALE_SMOOTH);
	    iconOK.setImage(imageOK);
		
		btnOK=new JButton(iconOK);
		btnOK.setBounds(30,80,100,40);
		btnOK.setBackground(new Color(38, 38, 38));
		btnOK.setFocusPainted(false);
		btnOK.setBorderPainted(false);
		btnOK.setContentAreaFilled(false);
		btnOK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
				System.exit(0);
		    }          

		});
		
		
		iconCancel  = new ImageIcon("src/assets/btnCancel.png");//Billing_button image
	    imageCancel = iconCancel.getImage();
	    imageCancel = imageCancel.getScaledInstance(98,38, Image.SCALE_SMOOTH);
	    iconCancel.setImage(imageCancel);
		
		btnCancel=new JButton(iconCancel);
		btnCancel.setBounds(170,80,100,40);
		btnCancel.setBackground(new Color(38, 38, 38));
		btnCancel.setFocusPainted(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
				exitFrame.dispose();
		    }          

		});
	    
		exitFrame.getContentPane().add(pnlexitHdr);
	    pnlexitHdr.add(btnCloseExit);
	    exitFrame.add(btnOK);
	    exitFrame.add(btnCancel);
	    exitFrame.repaint();
	    
	    MoveMouseListener mml2 = new MoveMouseListener(pnlexitHdr);
	    pnlexitHdr.addMouseListener(mml2);
	    pnlexitHdr.addMouseMotionListener(mml2);
	}
}
