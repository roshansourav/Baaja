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

public class Information 
{
	JFrame infoFrame;
	Image imageBaaja, imageLogo,imageClose;
	ImageIcon iconBaaja, iconLogo, iconClose;
	JButton btnLogo, btnClose;
	
	MoveMouseListener mmlInfoObj;
	
	public void infoframe() //Info
	{
		infoFrame=new JFrame("Info");
		infoFrame.setSize(700,500);
		infoFrame.setUndecorated(true);
		infoFrame.setOpacity(0.9f);
		infoFrame.setLocationRelativeTo(null);
		infoFrame.setLayout(null);
		infoFrame.getContentPane().setBackground(new Color(176,224,230));
		
		iconBaaja  = new ImageIcon("src/assets/PNGBaaja.png");
		imageBaaja = iconBaaja.getImage();
		iconBaaja.setImage(imageBaaja);
		infoFrame.setIconImage(imageBaaja);
		
		JPanel pnlInfoHdr=new JPanel();
		pnlInfoHdr.setBackground(Color.black);
		pnlInfoHdr.setLayout(null);
		pnlInfoHdr.setBounds(0, 0, 700,60);
		infoFrame.getContentPane().add(pnlInfoHdr);
		
		JLabel lblInfo=new JLabel("About Baaja",SwingConstants.CENTER);
		lblInfo.setBounds(10,5,650,40);
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setFont(new Font("Times New Roman",Font.BOLD,24));
		pnlInfoHdr.add(lblInfo);
		
		////////////////////////////icon_Logo starts here ////////////////////////////////////
		iconLogo = new ImageIcon("src/assets/PNGBaaja.png");
		imageLogo = iconLogo .getImage();
		imageLogo  = imageLogo .getScaledInstance(39,39, Image.SCALE_SMOOTH);
		iconLogo .setImage(imageLogo );
		//////////////////////////// icon_Logo closed here /////////////////////////////////////
		
		btnLogo = new JButton();
		btnLogo.setBounds(5, 5, 40, 40);
		btnLogo.setFont(new Font("Times New Roman",Font.BOLD,9));
		btnLogo.setFocusPainted(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setContentAreaFilled(false);
		btnLogo.setBackground(Color.black);
		btnLogo.setIcon(iconLogo);
		btnLogo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
		
			}          
		});
		
		pnlInfoHdr.add(btnLogo);
		
		
		btnClose = new JButton();
		btnClose.setBounds(650, 10, 40, 40);
		btnClose.setFont(new Font("Times New Roman",Font.BOLD,9));
		btnClose.setFocusPainted(false);
		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setToolTipText("Close");
		btnClose.setBackground(Color.black);
		btnClose.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
					infoFrame.dispose();
		    }          
		});
		
		////////////////////////////icon_close starts here ////////////////////////////////////
		iconClose  = new ImageIcon("src/assets/PNGClose.png");
		imageClose = iconClose.getImage();
		imageClose = imageClose.getScaledInstance(39,39, Image.SCALE_SMOOTH);
		iconClose.setImage(imageClose);
		//////////////////////////// icon_close closed here /////////////////////////////////////
		
		btnClose.setIcon(iconClose);
		pnlInfoHdr.add(btnClose);
		
		
		
		
		ImageIcon iconLogo  = new ImageIcon("src/assets/LogoBaajaPNG.png");
		 Image imageLogo = iconLogo.getImage();
		 imageLogo = imageLogo.getScaledInstance(380,150, Image.SCALE_DEFAULT);
		 iconLogo.setImage(imageLogo);
		
		JLabel lblLogo=new JLabel(iconLogo);
		lblLogo.setBounds(20,80,380,150);
		infoFrame.getContentPane().add(lblLogo);
		
		JLabel lblInfo1=new JLabel();
		lblInfo1.setBounds(200,200,450,300);
		lblInfo1.setText("<html>Baaja v1.0 is designed & developed by :-<br><br>-> Roshan Kumar<br><br> <br><br>Created on 13th may 2020</html>");
		lblInfo1.setFont(new Font("Times New Roman",Font.BOLD,14));
		infoFrame.getContentPane().add(lblInfo1);
		
		infoFrame.repaint();
		
		
		mmlInfoObj = new MoveMouseListener(pnlInfoHdr);
	    pnlInfoHdr.addMouseListener(mmlInfoObj);
	    pnlInfoHdr.addMouseMotionListener(mmlInfoObj);
	    
	    infoFrame.setVisible(true);
		
	}//info frame method closed
}
