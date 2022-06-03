package sample02;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//定数フィールド
	Color backgroundColor = Color.green;
	//コンポーネント
	ClioneLabel clioneLabel;

//	リスナー
	MyKeyListener myKeyListener;
	
	//コンストラクタ
	MainPanel() {
		//パネルサイズと貼り付け位置の設定は不要
		this.setLayout(null);  //レイアウトの設定
		this.setBackground(backgroundColor);  //背景の色
		//　その他追加設定をここに追記
	}
	
	//メソッド
	public void prepareComponents() {
		//以下コンポーネントに関する命令（以下は一例）
		clioneLabel = new ClioneLabel();      //ラベル生成
		this.add(clioneLabel);  //ラベルをこのパネルに貼る
		myKeyListener = new MyKeyListener(this);
	}
	
//	内部クラス（クリオネの動きを制御する）
	private class MyKeyListener implements KeyListener {
		
//		貼り付け先を保持
		MainPanel panel;
		
//		コンストラクタ
		MyKeyListener(MainPanel p) {
			super();
			panel = p;
			p.addKeyListener(this);
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
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP: //上を押した場合
					if (clioneLabel.getY() > 0) {
						clioneLabel.setLocation(clioneLabel.getX(), clioneLabel.getY()-5);
					}
					break;
				case KeyEvent.VK_DOWN: //下を押した場合
					if (clioneLabel.getY() + clioneLabel.clioneImage.getHeight(null) < panel.getHeight()) {
						clioneLabel.setLocation(clioneLabel.getX(), clioneLabel.getY()+5);
					}
					break;
				case KeyEvent.VK_LEFT: //左を押した場合
					if (clioneLabel.getX() > 0) {
						clioneLabel.setLocation(clioneLabel.getX()-5, clioneLabel.getY());
					}
					break;
				case KeyEvent.VK_RIGHT: //⇒が押された場合
					if (clioneLabel.getX() + clioneLabel.clioneImage.getWidth(null) < panel.getWidth()) {
						clioneLabel.setLocation(clioneLabel.getX()+5, clioneLabel.getY());
					}
					break;
				case KeyEvent.VK_ENTER: //Enterを押した場合
					if (backgroundColor == Color.green) {
						backgroundColor = Color.blue;
						panel.setBackground(backgroundColor);
					} else if (backgroundColor == Color.blue) {
						backgroundColor = Color.green;
						panel.setBackground(backgroundColor);
					}
					break;
			}
		}
		
	}
}