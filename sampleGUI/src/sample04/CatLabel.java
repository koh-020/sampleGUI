package sample04;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class CatLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	//フィールド
	Image catImage;
	int x;
	int y;
	int xVelocity;
	int yVelocity;
	Timer timer = null;
	
	//コンストラクタ
	CatLabel(){
		//画像設定
		catImage = new ImageIcon(getClass().getClassLoader().getResource("./images/cat_fish_run.png")).getImage();
		//ラベルのサイズを設定
		this.setSize(catImage.getWidth(null), catImage.getHeight(null));
		Random randX = new Random();
		Random randY = new Random();
		Random randVx = new Random();
		Random randVy = new Random();
		//初期設定の座標と速さを決定
		x = randX.nextInt(Main.mainWindow.mainPanel.getWidth() - this.getWidth());
		y = randY.nextInt(Main.mainWindow.mainPanel.getHeight() - this.getHeight());
		xVelocity = -5 + randVx.nextInt(11); //-5以上5以下
		yVelocity = -5 + randVy.nextInt(11); //-5以上5以下
		//位置設定
		this.setLocation(x, y);
	}
	
	//メソッド
	public void paint(Graphics g) {
		super.paint(g);
		
		//画像の描画（左右反転を制御）
		Graphics2D g2D = (Graphics2D) g;
		if(xVelocity >= 0) {
			g2D.drawImage(catImage, 0, 0, catImage.getWidth(null), catImage.getHeight(null), null);
		} else {
			g2D.drawImage(catImage, catImage.getWidth(null), 0, -catImage.getWidth(null), catImage.getHeight(null), null);
		}
	}
}