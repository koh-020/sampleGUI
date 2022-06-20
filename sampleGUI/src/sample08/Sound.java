package sample08;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound extends JFrame {
	private static final long serialVersionUID = 1L;
	
	final int WIDTH = 1000;
	final int HEIGHT = 800;
	
	//レイアウト
	CardLayout layout = new CardLayout();
	

	// コンポーネント
	JButton playButton;
	JButton stopButton;
	
	//スレッド
//	private Thread playThread;
	
//	SoundPlay soundPlay;
	
	//コンストラクタ
	Sound() {
		this.setTitle("SoundSample");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.cyan);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		
		//コンポーネント設置
		playButton = new JButton();
		playButton.setBounds(30, 30, 80, 80);
		playButton.setText("PLAY");
		stopButton = new JButton();
		stopButton.setBounds(200, 30, 80,80);
		stopButton.setText("STOP");
		ActionListener soundPlay = new SoundPlay();
		playButton.addActionListener(soundPlay);
		ActionListener soundStop = new SoundStop();
		stopButton.addActionListener(soundStop);
		this.add(playButton);
		this.add(stopButton);
		
	
	}
	
	//再生用リスナー
	private class SoundPlay implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			start();
		}
	}
	
	//停止用リスナー
	private class SoundStop implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stop();
		}
	}
	
	
	
	
	
	
//メソッド（音楽再生）
	private void start() {
		
	
		try
		{
			MultiThread mt = new MultiThread();
			Thread thread = new Thread(mt);
			thread.start();
//			playThread.start();
	
		}catch(
		Exception e)
		{
			System.out.println("エラーが発生、デバッグを推奨します。");
		}
	}
	
// メソッド（音楽停止）
	private void stop() {
		
		
		try
		{
			System.out.println("停止します");
			Thread.sleep(5000);
	
		}catch(
		Exception e)
		{
			System.out.println("エラーが発生、デバッグを推奨します。");
		}
	}
	
	//内部クラス（Thread用）
	
	class MultiThread implements Runnable {
		@Override
		public void run() {
			try {
				FileInputStream file = new FileInputStream("./sounds/Smile_Today.mp3");
				Player playMP3 = new Player(file);
				playMP3.play();
				System.out.println("Soundクラスを実行しました。");
				
			} catch(FileNotFoundException fe) {
				fe.printStackTrace();
			} catch(JavaLayerException je) {
				je.printStackTrace();
			}
			
			
			
		}

	}
	

}
