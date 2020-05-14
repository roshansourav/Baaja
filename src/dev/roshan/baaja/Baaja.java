package dev.roshan.baaja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Baaja
{
	JFrame frmBaaja;
	JPanel pnlHeader, pnlHeaderTrack, pnlBody, pnlBodyList;
	JLabel lblBaaja, lblLogo, lblAni;
	JButton btnClose, btnLogo, btnStop, btnMPrev, btnMPP, btnMNext;
	ImageIcon iconBaaja, iconClose, iconLogo, iconStop, iconPrev, iconPlay, iconPause, iconStatic, iconNext;
	Image imageClose, imageBaaja, imageLogo, imageStop, imagePrev, imagePlay, imagePause, imageStatic, imageNext;
	ImageIcon iconAni0, iconAni1, iconAni2, iconAni3, iconAni4, iconAni5, iconAni6;
	Image imageAni0, imageAni1, imageAni2, imageAni3, imageAni4, imageAni5, imageAni6;
	
	DefaultListModel<String> listModel;
	JList<String> list;
	 JScrollPane scrollPane;
	
	long pauseLoc, songLength;
	int playstatus=0,filepathresponse,trackNo=0;
	//play status 0 for stop , 1 for playing, 2 for paused
	public Player player;
	FileInputStream fis1;
	File[] selectedFile;
	BufferedInputStream bis1;
	JFileChooser fcPath=new JFileChooser();
	String strPath="",strPathNew;
	FileNameExtensionFilter filter ;
	
	MoveMouseListener mml1, mml2, mml3, mml4;
	ExitFrame exitFrameObj;
	
	MarqueeLabel lblCurrentSong;
	
	int width = 600, height = 410;
	
	
	public Baaja()
	{
				
		frmBaaja = new JFrame();
		frmBaaja.setSize(width, height);
		frmBaaja.setLocationRelativeTo(null);
		frmBaaja.setUndecorated(true);
		frmBaaja.setLayout(null);
		frmBaaja.setOpacity(0.9f);
		
		iconBaaja  = new ImageIcon("src/assets/PNGBaaja.png");
		imageBaaja = iconBaaja.getImage();
		iconBaaja.setImage(imageBaaja);
		frmBaaja.setIconImage(imageBaaja);
		
		pnlHeader=new JPanel();
		pnlHeader.setBackground(Color.black);
		pnlHeader.setBounds(0, 0, width,50);
		pnlHeader.setLayout(null);
		frmBaaja.getContentPane().add(pnlHeader);
		
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
				Information objInformation = new Information();
				objInformation.infoframe();
		    }          
		});
		
		pnlHeader.add(btnLogo);
	    
		
		lblBaaja=new JLabel("Baaja");
		lblBaaja.setBounds(50,5,width-50,40);
		lblBaaja.setHorizontalAlignment(SwingConstants.LEFT);
		lblBaaja.setForeground(Color.ORANGE);
		lblBaaja.setFont(new Font("Times New Roman",Font.BOLD,28));
		pnlHeader.add(lblBaaja);
		
		mml1 = new MoveMouseListener(pnlHeader);
		pnlHeader.addMouseListener(mml1);
		pnlHeader.addMouseMotionListener(mml1);
		
		btnClose = new JButton();
		btnClose.setBounds(width-45, 5, 40, 40);
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
					exitFrameObj = new ExitFrame();
					exitFrameObj.exitframe();
		    }          
		});
		
		////////////////////////////icon_close starts here ////////////////////////////////////
		iconClose  = new ImageIcon("src/assets/PNGClose.png");
		imageClose = iconClose.getImage();
		imageClose = imageClose.getScaledInstance(39,39, Image.SCALE_SMOOTH);
		iconClose.setImage(imageClose);
		//////////////////////////// icon_close closed here /////////////////////////////////////
		
		btnClose.setIcon(iconClose);
		pnlHeader.add(btnClose);
		
		pnlHeaderTrack = new JPanel();
		pnlHeaderTrack.setBackground(Color.black);
		pnlHeaderTrack.setBounds(0, 52, width,30);
		pnlHeaderTrack.setLayout(null);
		frmBaaja.getContentPane().add(pnlHeaderTrack);
		
		mml4 = new MoveMouseListener(pnlHeaderTrack);
		pnlHeaderTrack.addMouseListener(mml4);
		pnlHeaderTrack.addMouseMotionListener(mml4);
		
		
		////////////////////////////////////keyListener starts here  /////////////////////////////
		frmBaaja.setFocusable(false);
		frmBaaja.addKeyListener( new KeyListener() 
		{	
			@Override
			public void keyTyped( KeyEvent evt ){}
			
			@Override
			public void keyPressed( KeyEvent evt )
			{
				System.out.println("KEY "+evt.getKeyCode()+" pressed");
			}
			
			
			@Override
			public void keyReleased( KeyEvent evt ) 
			{
				System.out.println("KEY "+evt.getKeyCode()+" released");
			
			if(evt.getKeyCode() == KeyEvent.VK_N)
			{
				nextTrack();
			}
			else if(evt.getKeyCode() == KeyEvent.VK_SPACE)
			{
				System.out.println("KEY "+evt.getKeyCode()+" released");
				playPauseTrack();
			}
			else if(evt.getKeyCode() == KeyEvent.VK_P)
			{
				prevTrack();
			}
			else if(evt.getKeyCode() == KeyEvent.VK_S)
			{
				stopPlayer();
			}
			else {}
			
			}//keyReleased() closed here
		
		} );
		//////////////////////////////////////  keyListener ends here  ////////////////////////////
		
		funBaajaDecoration();
			
		
	}//constructor closed here
	
	
	public void init()
	{
		frmBaaja.setVisible(true);
	}//init()_method closed here
	
	
	public void stopPlayer()
	{
		try
		{
			playstatus=0;
			strPath="";
			lblCurrentSong.setText("Hit the PLAY button");
			btnMPP.setIcon(iconPlay);
			lblAni.setIcon(iconStatic);
			player.close();
			trackNo=0;
			btnMPP.setToolTipText("Select MP3 files");
			listModel.removeAllElements();
		}catch(Exception e) {}
	}//stopPlayer()_method closed here

	
	public void playSong(String path)
	{
		try 
		{
			fis1=new FileInputStream(path);
			bis1=new BufferedInputStream(fis1);
			player=new Player(bis1);
			songLength=fis1.available();			
			playstatus=1;
			btnMPP.setIcon(iconPause);
			setVisual();
			lblCurrentSong.setText(selectedFile[trackNo].getName());
			strPathNew=path+"";
			btnMPP.setToolTipText("Pause");
			
		}
		catch (FileNotFoundException  | JavaLayerException ex) 
		{
			playstatus=0;
			btnMPP.setIcon(iconPlay);
			lblAni.setIcon(iconLogo);
			lblCurrentSong.setText("");
			btnMPP.setToolTipText("Select MP3 files");
			
		} 
		catch (IOException e) 
		{}
		new Thread()
		{
			public void run()
			{
				try
				{
					player.play();
					
					if(player.isComplete())
					{
						btnMNext.doClick();
					}
				}
				catch (JavaLayerException e) 
				{
					strPath="";
					playstatus=0;
					lblCurrentSong.setText("");
					btnMPP.setIcon(iconPlay);
					lblAni.setIcon(iconLogo);
				}
			}
		}.start();
		
	}//plays method closed here
	
	
	public void pausePlayer()
	{
		if(player != null)
		{
			try 
			{
				pauseLoc=fis1.available();
				player.close();
				playstatus=2;
				btnMPP.setToolTipText("Resume");
			}
			catch (IOException e) 
			{}
		}
	}//pause method closed here
	
	
	public void resumePlayer()
	{
		
		try 
		{
			
			fis1=new FileInputStream(strPathNew);
			bis1=new BufferedInputStream(fis1);
			player=new Player(bis1);
			songLength=fis1.available();
			playstatus=1;
			fis1.skip(songLength-pauseLoc);
			btnMPP.setIcon(iconPause);
			setVisual();
			lblCurrentSong.setText(selectedFile[trackNo].getName());
			btnMPP.setToolTipText("Pause");
		}
		catch (FileNotFoundException  | JavaLayerException ex) 
		{
			playstatus=0;
			btnMPP.setIcon(iconPlay);
			lblAni.setIcon(iconLogo);
			lblCurrentSong.setText("");
			JOptionPane.showMessageDialog(null,ex);
			btnMPP.setToolTipText("Select MP3 files");
			stopPlayer();
		} 
		catch (IOException e) 
		{}
		new Thread()
		{
			public void run()
			{
				try
				{
					player.play();
					if(player.isComplete())
					{
						btnMNext.doClick();
					}
				}
				catch (JavaLayerException e) 
				{
					JOptionPane.showMessageDialog(null,e);
					strPath="";
					playstatus=0;
					lblCurrentSong.setText("");
					btnMPP.setIcon(iconPlay);
					lblAni.setIcon(iconLogo);
				}
			}
		}.start();
		
	}//resume method ended here
	
	
	public void prevTrack()
	{
		try
		{
			if(trackNo==0)
				trackNo=selectedFile.length;
		
			player.close();
			trackNo--;
		}
		catch(Exception e2) {}
		
		if(trackNo == selectedFile.length-1 && selectedFile.length-1 == 0)
		{
			jumpTrack(selectedFile.length-1);
		}
		else
		{
			try
			{
				list.setSelectedIndex(trackNo);
			}catch(Exception e) {}
		}
	}//prevTrack()_method closed here
	
	
	public void playPauseTrack()
	{
		if(playstatus==0)
		{	
			fcPath.setFileFilter(filter);
			fcPath.setMultiSelectionEnabled(true);
			fcPath.setCurrentDirectory(new File(System.getProperty("user.home")));
			filepathresponse = fcPath.showOpenDialog(pnlBody);
			if (filepathresponse == JFileChooser.APPROVE_OPTION) 
			{
				// user selects a file
				selectedFile = null;
				selectedFile = fcPath.getSelectedFiles();
				strPath=selectedFile[0].getAbsolutePath();
				trackNo = 0;
				strPath=strPath.replace("\\", "\\\\");
				
				for(int i=0; i<selectedFile.length; i++)
				{
					listModel.addElement(selectedFile[i].getName());
				}
				
				playstatus = 1;
				list.setSelectedIndex(0);
				
			}	
		}
		
		else if(playstatus==1)
		{
			btnMPP.setIcon(iconPlay);
			lblAni.setIcon(iconStatic);
			playstatus=2;
			pausePlayer();
			
		}
		
		else
		{
			resumePlayer();
		}
	}//playPauseTrack()_method closed here
	
	
	public void nextTrack()
	{
		try
		{
			if(trackNo==selectedFile.length-1)
				trackNo=-1;
			
			player.close();
			trackNo++;
		}
		catch(Exception e2){}
		
		if(trackNo == 0 && selectedFile.length-1 == 0)
		{
			jumpTrack(trackNo);
		}
		else
		{
			try
			{
				list.setSelectedIndex(trackNo);
			}catch(Exception e) {}
		}
	}//nextTrack()_method closed here
	
	
	public void setVisual()
	{
		int result;
		result = trackNo % 7;
		
		switch(result)
		{
			case 0:
				lblAni.setIcon(iconAni0);
				break;
			
			case 1:
				lblAni.setIcon(iconAni1);
				break;
				
			case 2:
				lblAni.setIcon(iconAni2);
				break;
				
			case 3:
				lblAni.setIcon(iconAni3);
				break;
				
			case 4:
				lblAni.setIcon(iconAni4);
				break;
				
			case 5:
				lblAni.setIcon(iconAni5);
				break;
				
			case 6:
				lblAni.setIcon(iconAni6);
				break;
				
			default:
				lblAni.setIcon(iconAni0);
				break;
		}			
	}
	
	
	public void jumpTrack(int index)
	{
		try
		{	
			player.close();
			trackNo = index;
			strPath=selectedFile[trackNo].getAbsolutePath();
			strPath=strPath.replace("\\", "\\\\");
		}
		catch(Exception e2){}
		if(filepathresponse==0 && playstatus!=0)
			playSong(strPath);
	}//jumpTrack()_method closed here
	
	
	public void funBaajaDecoration()
	{
		pnlBody = new JPanel();
		pnlBody.setBackground(Color.black);
		pnlBody.setBounds(0, 84, width-250,height-84);
		pnlBody.setLayout(null);
		frmBaaja.getContentPane().add(pnlBody);
		
		mml2 = new MoveMouseListener(pnlBody);
		pnlBody.addMouseListener(mml2);
		pnlBody.addMouseMotionListener(mml2);
		
			
		
		lblCurrentSong  = new MarqueeLabel("Hit the PLAY button", MarqueeLabel.RIGHT_TO_LEFT, 20);  
		lblCurrentSong.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblCurrentSong.setForeground(new Color(127,255,0));
		lblCurrentSong.setBounds(05,05,width,20);
		lblCurrentSong.setBackground(Color.red);
		pnlHeaderTrack.add(lblCurrentSong);
		
		
	    
	    iconStop  = new ImageIcon("src/assets/PNGStop.png");
	    imageStop = iconStop.getImage();
	    imageStop = imageStop.getScaledInstance(39,39, Image.SCALE_SMOOTH);
	    iconStop.setImage(imageStop);
	    
	
	    btnStop = new JButton(iconStop);
		btnStop.setBounds(20, 270, 42, 42);
		btnStop.setFont(new Font("Times New Roman",Font.BOLD,9));
		btnStop.setBackground(Color.BLACK);
		btnStop.setFocusPainted(false);
		btnStop.setBorderPainted(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setToolTipText("Stop");
		btnStop.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
				stopPlayer();
		    }          

		});
		pnlBody.add(btnStop);
		
		iconPrev  = new ImageIcon("src/assets/PNGPrevious.png");
	    imagePrev = iconPrev.getImage();
	    imagePrev = imagePrev.getScaledInstance(39,39, Image.SCALE_SMOOTH);
	    iconPrev.setImage(imagePrev);
	    
		btnMPrev = new JButton(iconPrev);
		btnMPrev.setBounds(120, 270, 42, 42);
		btnMPrev.setFont(new Font("Times New Roman",Font.BOLD,9));
		btnMPrev.setBackground(Color.BLACK);
		btnMPrev.setFocusPainted(false);
		btnMPrev.setBorderPainted(false);
		btnMPrev.setContentAreaFilled(false);
		btnMPrev.setToolTipText("Previous");
		btnMPrev.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
				prevTrack();
		    }          

		}); //button previous-song ended here
		pnlBody.add(btnMPrev);
		
		
		
		iconPlay  = new ImageIcon("src/assets/PNGPlay.png");
	    imagePlay = iconPlay.getImage();
	    imagePlay = imagePlay.getScaledInstance(59,59, Image.SCALE_SMOOTH);
	    iconPlay.setImage(imagePlay);
	    
	    
	    iconPause  = new ImageIcon("src/assets/PNGPause.png");
	    imagePause = iconPause.getImage();
	    imagePause = imagePause.getScaledInstance(59,59, Image.SCALE_SMOOTH);
	    iconPause.setImage(imagePause);
		
		btnMPP = new JButton(iconPlay);
		btnMPP.setBounds(190, 260, 63, 63);
		btnMPP.setFont(new Font("Times New Roman",Font.BOLD,9));
		btnMPP.setBackground(Color.BLACK);
		btnMPP.setFocusPainted(false);
		btnMPP.setBorderPainted(false);
		btnMPP.setContentAreaFilled(false);
		
		btnMPP.setToolTipText("Select MP3 files");
		btnMPP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {
				playPauseTrack();
		    }          
		});//button play-pause action ended here
		pnlBody.add(btnMPP);
		

		iconNext  = new ImageIcon("src/assets/PNGNext.png");
	    imageNext = iconNext.getImage();
	    imageNext = imageNext.getScaledInstance(39,39, Image.SCALE_SMOOTH);
	    iconNext.setImage(imageNext);
		
		btnMNext = new JButton(iconNext);
		btnMNext.setBounds(280, 270, 42, 42);
		btnMNext.setFont(new Font("Times New Roman",Font.BOLD,9));
		btnMNext.setBackground(Color.BLACK);
		btnMNext.setFocusPainted(false);
		btnMNext.setBorderPainted(false);
		btnMNext.setContentAreaFilled(false);
		btnMNext.setToolTipText("Next");
		btnMNext.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
		    {	
				nextTrack();
		    }          
		});
		pnlBody.add(btnMNext);
		
		////////////////////////////////  Music Visualizer effect starts here ///////////////////////
		
		iconAni0  = new ImageIcon("src/assets/animated0.gif");
	    imageAni0 = iconAni0.getImage();
	    imageAni0 = imageAni0.getScaledInstance(350,260, Image.SCALE_DEFAULT);
	    iconAni0.setImage(imageAni0);
	    
	    iconAni1  = new ImageIcon("src/assets/animated1.gif");
	    imageAni1 = iconAni1.getImage();
	    imageAni1 = imageAni1.getScaledInstance(350,260, Image.SCALE_DEFAULT);
	    iconAni1.setImage(imageAni1);
	    
	    iconAni2  = new ImageIcon("src/assets/animated2.gif");
	    imageAni2 = iconAni2.getImage();
	    imageAni2 = imageAni2.getScaledInstance(260,260, Image.SCALE_DEFAULT);
	    iconAni2.setImage(imageAni2);
	    
	    iconAni3  = new ImageIcon("src/assets/animated3.gif");
	    imageAni3 = iconAni3.getImage();
	    imageAni3 = imageAni3.getScaledInstance(260,260, Image.SCALE_DEFAULT);
	    iconAni3.setImage(imageAni3);
	    
	    iconAni4  = new ImageIcon("src/assets/animated4.gif");
	    imageAni4 = iconAni4.getImage();
	    imageAni4 = imageAni4.getScaledInstance(260,260, Image.SCALE_DEFAULT);
	    iconAni4.setImage(imageAni4);
		
		iconAni5  = new ImageIcon("src/assets/animated5.gif");
	    imageAni5 = iconAni5.getImage();
	    imageAni5 = imageAni5.getScaledInstance(260,260, Image.SCALE_DEFAULT);
	    iconAni5.setImage(imageAni5);
	    
	    iconAni6  = new ImageIcon("src/assets/animated6.gif");
	    imageAni6 = iconAni6.getImage();
	    imageAni6 = imageAni6.getScaledInstance(260,260, Image.SCALE_DEFAULT);
	    iconAni6.setImage(imageAni6);
	    
	    
	    iconStatic  = new ImageIcon("src/assets/LogoBaajaPNG.png");
	    imageStatic = iconStatic.getImage();
	    imageStatic = imageStatic.getScaledInstance(330,200, Image.SCALE_DEFAULT);
	    iconStatic.setImage(imageStatic);
		
		lblAni=new JLabel(iconStatic);
		lblAni.setBounds(5,0,350,260);
		pnlBody.add(lblAni);
		
		//////////////////////////////// Music Visualizer effect ends here ///////////////////////
		
		/////////////////////////////////////// pnlBodyList starts here //////////////////////////////
		
		pnlBodyList = new JPanel();
		pnlBodyList.setBounds(353, 84, 247,height-84);
		pnlBodyList.setBackground(Color.black);
		
		TitledBorder bdrNetwork = new TitledBorder("Tracks");
		bdrNetwork.setTitleColor(new Color(0,255,255));
	    bdrNetwork.setTitleJustification(TitledBorder.CENTER);
	    bdrNetwork.setTitlePosition(TitledBorder.TOP);
	    pnlBodyList.setBorder(bdrNetwork);
		
	    mml3 = new MoveMouseListener(pnlBodyList);
	    pnlBodyList.addMouseListener(mml3);
	    pnlBodyList.addMouseMotionListener(mml3);
		
		frmBaaja.getContentPane().add(pnlBodyList);
		
		////////////////////////////////////// pnlBodyList ends here  /////////////////////////////
		
		funList();
		
		
		
		filter = new FileNameExtensionFilter("MP3 File","mp3");
		
	}//decoration()_method closed here

	
	void funList()
	{
		listModel = new DefaultListModel<>(); 
         
        list = new JList<>(listModel);  
        list.setSize(195,height-55);
	    list.setBackground(Color.black);
	    list.setForeground(new Color(102, 255, 102));
	    
	    ListSelectionListener listSelectionListener = new ListSelectionListener() 
	    {
	        public void valueChanged(ListSelectionEvent listSelectionEvent) 
	        {
	        	if (!listSelectionEvent.getValueIsAdjusting()) 
	        	{//This line prevents double events
	        		jumpTrack(list.getSelectedIndex());
	        		list.setSelectionBackground(new Color(255, 128, 0));
	            }
	        	
	        }
	    };
	    list.addListSelectionListener(listSelectionListener);
          
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(240,height-110));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        scrollPane.setBorder(blackline);
        
        scrollPane.getViewport().getView().setBackground(Color.black);
        scrollPane.getViewport().getView().setForeground(Color.YELLOW);
        
        
        pnlBodyList.add(scrollPane);  
	}
	
}
