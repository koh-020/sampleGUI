package sampleJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.Timer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// レイアウト
	BorderLayout layout = new BorderLayout();

	// フィールド
	public final int TIME_MAX = 6000;
	public int timeLeft = TIME_MAX;
	Timer timer;
	public int score = 0;

//	コンポーネント
	MenuBar menuBar;
	FieldPanel fieldPanel;

	// リスナー
	MyKeyListener myKeyListener;
	
	//スレッド
	Thread thread;
	
	Player playMP3 = null;

//	コンストラクタ
	public GamePanel() {
//		パネルサイズと貼り付け位置の設定は不要
		this.setLayout(layout); // レイアウトの設定
		this.setBackground(Color.yellow); // 背景色
		// パネル生成と設置
		menuBar = new MenuBar();
//		menuBar.setLayout(null);
		fieldPanel = new FieldPanel();
//		fieldPanel.setLayout(null);
		this.add(menuBar, BorderLayout.NORTH);
		this.add(fieldPanel, BorderLayout.CENTER);

	}

//	コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		menuBar.prepareComponents(TIME_MAX, score);
		fieldPanel.prepareComponents();

		// リスナーを設置
		myKeyListener = new MyKeyListener(this);
	}
	
	//タイマースタート
	public void timerStart() {
		timer = new Timer(1, taskPerformer);
		timer.start();
	}
	
	//ゲームリセット
	public void resetGame() {
		timeLeft = TIME_MAX;
		score = 0;
		fieldPanel.removeAll();
		fieldPanel.prepareComponents();
		this.menuBar.scoreLabel.setText("SCORE" + score);
	}

	// 内部クラス(hが押されたらタイトルへ)
	private class MyKeyListener implements KeyListener {
		// 貼り付け先を保持
		JPanel panel;

		// コンストラクタ
		MyKeyListener(JPanel p) {
			super();
			panel = p;
			panel.addKeyListener(this);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// do nothing
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// do nothing
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_H:
				Main.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
				Main.mainWindow.gamePanel.soundStop();
				break;
			}
		}

	}

	
	//タイマー用リスナー
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(timeLeft == 0) {
				timer.stop();
				Main.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
				Main.mainWindow.gamePanel.soundStop();
			}
			menuBar.actionPerformed(timeLeft, TIME_MAX);
			timeLeft--;
		}
	};
	

	
	//音楽再生メソッド
	public void soundStart() {
		try {
			MultiThread mt = new MultiThread();
			thread = new Thread(mt);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			thread.interrupt();
		}
	}
	
	//音楽停止用メソッド
	public void soundStop() {
		try {
			playMP3.close();
			thread.interrupt();
		} catch(Exception e) {
			System.out.println("エラーが発生しました。");
		}
	}
	
	//Thread用
	class MultiThread implements Runnable {
		@Override
		public void run() {
			try {
				URL url = this.getClass().getClassLoader().getResource("Smile_Today.mp3");
				playMP3 = new Player(url.openStream());
				playMP3.play();
			} catch(FileNotFoundException fe) {
				fe.printStackTrace();
			} catch (JavaLayerException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
