package sampleJFrame;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Cat_UltraRare extends Animal{

	//設定
	protected void prepareImageAndScoreAndVoice() {
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("./images/jitensya_neko.png"));
		MediaTracker tracker = new MediaTracker(this);
		Image smallCatImage = image.getImage().getScaledInstance((int) (image.getIconWidth() * 0.5), -1,
				Image.SCALE_SMOOTH);
		tracker.addImage(smallCatImage, 1);
		ImageIcon catImage = new ImageIcon(smallCatImage);
		super.image = catImage.getImage();
		super.score = 100;
		super.voiceKey = "暴走";
		super.escapeTime = 150;
		super.direction = "left";
	};
}
