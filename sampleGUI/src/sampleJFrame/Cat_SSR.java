package sampleJFrame;

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
		super.image = new ImageIcon(getClass().getClassLoader().getResource("./images/kotowaza_neko_koban.png")).getImage();
		super.score = 300;
		super.voiceKey = "ニャー";
		super.escapeTime = 150;
	};
}