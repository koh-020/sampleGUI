package sampleJFrame;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Cat_SSR extends Animal{
	private static final long serialVersionUID = 1L;
	//コンストラクタのオーバーライド
	Cat_SSR() {
		super();
		super.xVelocity = 0;
		super.yVelocity = 0;
	}
	
	//設定
	protected void prepareImageAndScoreAndVoice() {
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("./images/kotowaza_neko_koban.png"));
		MediaTracker tracker = new MediaTracker(this);
		Image smallCatImage = image.getImage().getScaledInstance((int) (image.getIconWidth() * 0.5), -1,
				Image.SCALE_SMOOTH);
		tracker.addImage(smallCatImage, 1);
		ImageIcon catImage = new ImageIcon(smallCatImage);
		super.image = catImage.getImage();
		super.score = 300;
		super.voiceKey = "ニャー";
		super.escapeTime = 150;
		super.direction = "right";
	};
}
