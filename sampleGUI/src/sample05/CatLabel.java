package sample05;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CatLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	 // フィールド
	Image catImage;
	int x;
	int y;
	//コンストラクタ
	CatLabel(){
		catImage = new ImageIcon(getClass().getClassLoader().getResource("./images/cat_fish_run.png")).getImage();
		this.setSize(catImage.getWidth(null), catImage.getHeight(null));
		//初期値
		x = new java.util.Random().nextInt(Main.mainWindow.mainPanel.getWidth() - this.getWidth());
		y = new java.util.Random().nextInt(Main.mainWindow.mainPanel.getHeight() - this.getHeight());
		//位置設定
		this.setLocation(x, y);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//ネコの画像を使う
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(catImage, 0, 0, catImage.getWidth(null), catImage.getHeight(null), null);
	}
}