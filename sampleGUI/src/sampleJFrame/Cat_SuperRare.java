package sampleJFrame;

import javax.swing.ImageIcon;

public class Cat_SuperRare extends Animal{
	//設定
	protected void prepareImageAndScoreAndVoice() {
		super.image = new ImageIcon(getClass().getClassLoader().getResource("./images/petcart_cat.png")).getImage();
		super.score = 50;
		super.voiceKey = "ニャー";
		super.escapeTime = 200;
	};
}
