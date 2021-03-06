package sampleJFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.Timer;

public abstract class Animal extends JLabel {
	private static final long serialVersionUID = 1L;
	Image image; // 100x100の画像を指定
	int score; // 捕獲時の得点
	String voiceKey; // 捕獲時の鳴き声key
	Timer timer = null; // その個体の動きの制御用
	int escapeTime = 500; // 時間経過で消える用
	// 座標と速度
	int x;
	int y;
	int xVelocity;
	int yVelocity;
	String direction;

	// コンストラクタ
	public Animal() {
		x = new java.util.Random().nextInt(680);
		y = new java.util.Random().nextInt(300);
		do {
			xVelocity = -5 + new java.util.Random().nextInt(10);
			yVelocity = -5 + new java.util.Random().nextInt(10);
		} while (xVelocity == 0 || yVelocity == 0);
		this.prepareImageAndScoreAndVoice();
		this.setBounds(x, y, image.getWidth(null), image.getHeight(null));
		this.setLocation(x, y);
	}

	// メソッド
	// 設定（子クラスで必ず設定）
	protected abstract void prepareImageAndScoreAndVoice();

	// 画像の描画（反転の処理を含む）
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		if (direction == "left") {
			if (xVelocity <= 0) {
				g2D.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
			} else {
				g2D.drawImage(image, image.getWidth(null), 0, -image.getWidth(null), image.getHeight(null), null);
			}
		} else if (direction == "right") {
			if (xVelocity <= 0) {
				g2D.drawImage(image, image.getWidth(null), 0, -image.getWidth(null), image.getHeight(null), null);
			} else {
				g2D.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
			}
		}
		
	}

}
