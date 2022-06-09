package sample05;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

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
		//猫生成1
		cat01 = new CatLabel();
		vitalizeCat(cat01);
		
		//猫生成2
		cat02 = new CatLabel();
		vitalizeCat(cat02);
	}
	
	//猫にリスナーをつけてからパネルに貼るメソッド
	public void vitalizeCat(CatLabel c) {
		new DDListener(c);
		this.add(c);
	}
	
	//内部クラス（ドラッグアンドドロップ用）
	private class DDListener extends MouseAdapter{
		private int dx;
		private int dy;
		private CatLabel cat;
		
		//コンストラクタ
		DDListener(CatLabel c) {
			//引数としてadd咲のコンポーネントを指定しておく
			cat = c;
			cat.addMouseListener(this);
			cat.addMouseMotionListener(this);
		}
		
		public void mousePressed(MouseEvent e) {
			//抑えたところからラベルの左上の差を取っておく(ドラッグ開始点)
			
			dx = e.getXOnScreen() - cat.getX();
			dy = e.getYOnScreen() - cat.getY();
		}
		
		public void mouseDragged(MouseEvent e) {
			//マウスの座標からラベルの左上の座標を取得する(ドラッグ終了点)
			int x = e.getXOnScreen() - dx;
			int y = e.getYOnScreen() - dy;
			if(x < Main.mainWindow.mainPanel.getWidth() - cat.getWidth()-5 && x > 5) {
				cat.x = x;
			}
			if(y < Main.mainWindow.mainPanel.getHeight() - cat.getHeight()-5 && y > 5) {
				cat.y = y;
			}
			cat.setLocation(cat.x, cat.y);
		}
		
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2) {
				//ダブルクリック
				Main.mainWindow.mainPanel.remove(e.getComponent());
				repaint();
			}
		}
	}
}