package sample04;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//定数フィールド
	Color backgroundColor = Color.black;
	//コンポーネント
	CatLabel cat01;
	CatLabel cat02;

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
		//猫生成01
		cat01 = new CatLabel();
		vitalizeCat(cat01);
		
		//猫生成02
		cat02 = new CatLabel();
		vitalizeCat(cat02);
	}
	
	//猫にリスナーをつけてからパネルに貼るメソッド
	public void vitalizeCat(CatLabel c) {
		CatActionListener catListener = new CatActionListener(c);
		c.timer = new Timer(10, catListener);
		this.add(c);
		c.timer.start();
	}
	
	//内部クラス（パネル内で猫を走らせる）
	private class CatActionListener implements ActionListener{
		//貼り付け先を保持
		private CatLabel cat;
		
		public CatActionListener(CatLabel c) {
			cat = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cat.x > Main.mainWindow.mainPanel.getWidth() - cat.getWidth() || cat.x <0) {
				cat.xVelocity = cat.xVelocity*(-1);
			}
			cat.x = cat.x + cat.xVelocity;
			
			if(cat.y > Main.mainWindow.mainPanel.getHeight() - cat.getHeight() || cat.y < 0) {
				cat.yVelocity = cat.yVelocity*(-1);
			}
			cat.y = cat.y + cat.yVelocity;
			cat.setLocation(cat.x, cat.y);
			cat.repaint();
		}
	}
}