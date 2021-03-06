package sampleJFrame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow  extends JFrame{
	private static final long serialVersionUID = 1L;
//	フィールド
	ScreenMode screenMode = ScreenMode.TITLE;
	
//	定数
	final int WIDTH = 1000;
	final int HEIGHT = 800;
	
//	レイアウト
	CardLayout layout = new CardLayout();
	
//	コンポ―ネント
	TitlePanel titlePanel;
	GamePanel gamePanel;
	
//	コンストラクタ
	MainWindow() {
//		ウィンドウ左下のアイコンとタイトル
		this.setTitle("sampleGUI");
//		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("nikukyu.png"));
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("nikukyu.png"));
		this.setIconImage(icon.getImage());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);						//ウィンドウのXを押すとプログラムを終了させる(デフォルトのままでは非表示になるだけ)
		this.setResizable(false);																//画面サイズの変更を禁止
		this.getContentPane().setBackground(Color.green);					//背景の色
//		this.getContentPane().setBackground(new Color(0xF6F6F6));		//細かく設定する場合はこちら
		this.setLayout(layout);																//"紙芝居"のように設定
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));				//サイズ調整
		this.pack();																				//自動サイズ調整(これが無いと変なサイズになる)
		this.setLocationRelativeTo(null);													//起動時のスクリーンの位置を中央に(packより後で呼ぶ)
//		this.setLocation(450, 450):														//細かく設定する場合はこちら
		
	}
	
//	メソッド
	
//	コンストラクタが呼ばれた後メインメソッドから最初に手動で呼び出す
	public void preparePanels() {
//		パネルの準備
		titlePanel = new TitlePanel();
		this.add(titlePanel, "タイトル画面");
		gamePanel = new GamePanel();
		this.add(gamePanel, "ゲーム画面");
		this.pack();
	}
	
//	preparePanels()が呼ばれた後メインメソッドからさらに手動で呼び出す
	public void prepareComponents() {
		titlePanel.prepareComponents();
		gamePanel.prepareComponents();
	}
	
//	スクリーンモードを切り替える
//	メインメソッドから手動で呼び出す
	public void setFrontScreenAndFocus(ScreenMode s) {
		screenMode = s;
		switch(screenMode) {
		case TITLE:
			layout.show(this.getContentPane(),  "タイトル画面");
			titlePanel.requestFocus();
			break;
		case GAME:
			layout.show(this.getContentPane(), "ゲーム画面");
			gamePanel.requestFocus();
			gamePanel.resetGame();
//			gamePanel.timerStart();
			gamePanel.soundStart();
			break;
		}
	}
	
}
