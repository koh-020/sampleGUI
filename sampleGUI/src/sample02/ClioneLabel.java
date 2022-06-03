package sample02;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ClioneLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	
//	フィールド
	Image clioneImage;
	
//	コンストラクタ
	ClioneLabel() {
//		ClioneImage = new ImageIcon(getClass().getClassLoader().getResource("fish_clione.png"));
		clioneImage  = new ImageIcon("C:\\Users\\ko-he-\\Desktop\\DMM\\java\\GUI\\sampleGUI\\src\\images/fish_clione.png").getImage();
		this.setBounds(100, 100, clioneImage.getWidth(null), clioneImage.getHeight(null));
	}
	public void paint(Graphics g) {
		super.paint(g);
//		クリオネの画像を使う
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(clioneImage, 0, 0, clioneImage.getWidth(null), clioneImage.getHeight(null), null);
	}
	

	
}
