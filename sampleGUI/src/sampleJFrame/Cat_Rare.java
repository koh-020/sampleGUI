package sampleJFrame;

import javax.swing.ImageIcon;

public class Cat_Rare extends Animal{

	//設定
	protected void prepareImageAndScoreAndVoice() {
		super.image = new ImageIcon(getClass().getClassLoader().getResource("./images/cat_nezumi.png")).getImage();
		super.score = 10;
		super.voiceKey = "ニャー";
		super.escapeTime = 200;
	};
}