package sampleJFrame;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CatPot extends JLabel{
	private static final long serialVersionUID = 1L;
	//フィールド
	ImageIcon image;
	ImageIcon cupImage;
	
	//コンストラクタ
	public CatPot() {
		image = new ImageIcon(getClass().getClassLoader().getResource("cup.png"));
		MediaTracker tracker = new MediaTracker(this);
		Image smallCupImage = image.getImage().getScaledInstance((int) (image.getIconWidth() * 0.2), -1, Image.SCALE_SMOOTH);
		tracker.addImage(smallCupImage, 1);
		cupImage = new ImageIcon(smallCupImage);
		this.setSize(500, 500);
		this.setIcon(cupImage);
		this.setText("DROP HERE↓");
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(TOP);
		
	}
}
