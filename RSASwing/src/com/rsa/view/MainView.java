package com.rsa.view;
import java.util.*;
import java.applet.*;
import java.awt.*;
import javax.swing.*;

import com.rsa.bussiness.RSACoder;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MainView extends Applet implements ActionListener {
	JTextArea PUBLIC_AREA;
	JTextArea PRIVATE_AREA;
	JTextField PUBLIC_KEY; 
	JTextField PRIVATE_KEY;
	JTextField FilePath;
	private String default_filepath="E:";
	JButton b1, b2;

	public void init() {
		setSize(1400, 600);
		PUBLIC_KEY=new JTextField("",10);
		PUBLIC_KEY.setBackground(Color.LIGHT_GRAY);
		PRIVATE_KEY=new JTextField("",10);
		PRIVATE_KEY.setBackground(Color.ORANGE);
		
		PUBLIC_AREA = new JTextArea("", 20, 20);
		PUBLIC_AREA.setBackground(Color.LIGHT_GRAY);

		PRIVATE_AREA = new JTextArea("", 20, 20);
		PRIVATE_AREA.setBackground(Color.ORANGE);
		
		FilePath = new JTextField("",20);
		FilePath.setBackground(Color.PINK);
		FilePath.setText(default_filepath);
		b1 = new JButton("生成");
		b2 = new JButton("重新开始");
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		
		
		add(PUBLIC_KEY);
		add(PRIVATE_KEY);
		add(FilePath);
		add(b1);
		add(b2);
		add(PUBLIC_AREA);
		add(PRIVATE_AREA);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			File public_delename = new File(FilePath.getText()+"\\rsa_server_public_key_2048.pem"); 
			File private_delename = new File(FilePath.getText()+"\\rsa_merchant_private_key_2048.pem"); // 相对路径，如果没有则要建立一个新的output。txt文件  
			if(public_delename.exists()){
				public_delename.delete();
			}
			if(private_delename.exists()){
				private_delename.delete();
			}
			
			
//			PUBLIC_KEY.getText();
//			PRIVATE_KEY.getText();
			
//			StringTokenizer tokens = new StringTokenizer(s);
//			textF.setText("" + sum);
			Map<String, Object> keyMap;
			try {
				keyMap = RSACoder.initKey(PUBLIC_KEY.getText(),PRIVATE_KEY.getText());
				String publicKey = RSACoder.getPublicKey(keyMap);
				String privateKey = RSACoder.getPrivateKey(keyMap);
				PUBLIC_AREA.setText(publicKey);
				PRIVATE_AREA.setText(privateKey);
			    File public_writename = new File(FilePath.getText()+"\\rsa_server_public_key_2048.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
			    public_writename.createNewFile(); // 创建新文件  
			  
			    BufferedWriter public_out = new BufferedWriter(new FileWriter(public_writename));  
			    public_out.write("-----BEGIN PUBLIC KEY-----\r\n"); 
			    public_out.write(publicKey);
			    public_out.write("-----END PUBLIC KEY-----\r\n"); 
			    public_out.flush(); 
			    public_out.close(); 
			    public_writename.renameTo(new File(FilePath.getText()+"\\rsa_server_public_key_2048.pem"));
			    
			    
			    
			    
			    File private_writename = new File(FilePath.getText()+"\\rsa_merchant_private_key_2048.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
			    private_writename.createNewFile(); // 创建新文件  
			    BufferedWriter privatge_out = new BufferedWriter(new FileWriter(private_writename));  
			    privatge_out.write("-----BEGIN PRIVATE KEY-----\r\n"); 
			    privatge_out.write(privateKey);
			    privatge_out.write("-----BEGIN PRIVATE KEY-----\r\n"); 
			    privatge_out.flush(); 
			    privatge_out.close(); 
			    private_writename.renameTo(new File(FilePath.getText()+"\\rsa_merchant_private_key_2048.pem"));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == b2) {
			PUBLIC_KEY.setText(null);
			PRIVATE_KEY.setText(null);
			PUBLIC_AREA.setText(null);
			PRIVATE_AREA.setText(null);
			FilePath.setText(default_filepath);
		}
	}
}