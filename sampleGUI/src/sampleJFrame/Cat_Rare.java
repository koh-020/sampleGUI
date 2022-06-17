package sampleJFrame;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Cat_Rare extends Animal{

	//設定
	protected void prepareImageAndScoreAndVoice() {
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("cat_nezumi_oikakeru.png"));
		MediaTracker tracker = new MediaTracker(this);
		Image smallCatImage = image.getImage().getScaledInstance((int) (image.getIconWidth() * 0.5), -1,
				Image.SCALE_SMOOTH);
		tracker.addImage(smallCatImage, 1);
		ImageIcon catImage = new ImageIcon(smallCatImage);
		super.image = catImage.getImage();
		super.score = 10;
		super.voiceKey = "ニャー";
		super.escapeTime = 200;
		super.direction = "right";
	};
}
