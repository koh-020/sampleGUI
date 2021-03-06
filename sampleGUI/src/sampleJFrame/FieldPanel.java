package sampleJFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FieldPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel gameLabel;
	Animal cat01;
	Animal cat02;
	JLayeredPane layeredPane;
	CatPot catPot;
	Animal animal;
	CatActionListener catListener;
	static Boolean playing = true;

	JLabel greenLabel;

	// コンストラクタ
	public FieldPanel() {
		this.setBackground(Color.orange);
		this.setLayout(null);
//		new DDListener();

	}

	// コンストラクタが呼び出された後に手動で呼び出す
	public void prepareComponents() {

		layeredPane = new JLayeredPane();

		layeredPane.setBounds(0, 0, 1000, 800);
		this.add(layeredPane);

		Animal cat01 = new Cat_Normal();
		vitalizeCat(cat01);
		Animal cat02 = new Cat_Normal();
		vitalizeCat(cat02);

		catPot = new CatPot();
		catPot.setLocation(400, 400);
		this.layeredPane.add(catPot, JLayeredPane.DEFAULT_LAYER);
		this.layeredPane.add(cat01, 500);
		this.layeredPane.add(cat02, 400);
		this.layeredPane.moveToBack(catPot);
	}

//	ネコにリスナーをつけてからパネルにはるメソッド
	public void vitalizeCat(Animal a) {
		catListener = new CatActionListener(a);
		a.timer = new Timer(10, catListener);
		new DDListener(a);
		this.add(a);
		a.timer.start();
	}

	// 動物をランダムに生成して走らせるメソッド
	public void generateAnimal() {
		Random r = new Random();
		int random = r.nextInt(1000);
		Animal a = null;
		if (random < 600) {
			a = new Cat_Normal();
		} else if (random < 600 + 200) {
			
			a = new Cat_Rare();
		} else if (random < 600 + 200 + 150) {
			a = new Cat_SuperRare();
		} else if (random < 600 + 200 + 150 + 40) {
			a = new Cat_SSR();
		} else {
			a = new Cat_UltraRare();
		}
		vitalizeCat(a);
		this.layeredPane.add(a);
		this.layeredPane.moveToFront(a);
	}
	
	//ネコの動きを止めるメソッド
	public static void stopAllAnimals() {
		FieldPanel.playing = false;
	}
	
	//ネコの動きを再開させるメソッド
	public static void startAllAnimals() {
		FieldPanel.playing = true;
		
	}

//	内部クラス（パネル内でネコを走らせる）
	private class CatActionListener implements ActionListener {
		
			
	
		private Animal animal;

		public CatActionListener(Animal a) {
			animal = a;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
 			if(FieldPanel.playing) {
				if(animal.x > Main.mainWindow.gamePanel.fieldPanel.getWidth() - animal.getWidth() || animal.x < 0) {
					animal.xVelocity = animal.xVelocity * (-1);
				}
				animal.x = animal.x + animal.xVelocity;
				if(animal.y > Main.mainWindow.gamePanel.fieldPanel.getHeight() - animal.getHeight() || animal.y < 0) {
					animal.yVelocity = animal.yVelocity * (-1);
				}
				animal.y = animal.y + animal.yVelocity;
				animal.setLocation(animal.x, animal.y);
				animal.repaint();
			}
		}

	}

	// 内部クラス（ドラッグアンドドロップ用）
	private class DDListener extends MouseAdapter {
		private int dx;
		private int dy;
		private Animal animal;

		// コンストラクタ
		DDListener(Animal a) {
			animal = a;
			animal.addMouseListener(this);
			animal.addMouseMotionListener(this);
		}

		public void mousePressed(MouseEvent e) {
			// 抑えたところからラベル左上の差を取っておく
			dx = e.getXOnScreen() - animal.getX();
			dy = e.getYOnScreen() - animal.getY();
		}

		// マウスをドラッグした時に移動できる
		public void mouseDragged(MouseEvent e) {
			int x = e.getXOnScreen() - dx;
			int y = e.getYOnScreen() - dy;
			if (x < Main.mainWindow.gamePanel.fieldPanel.getWidth() - animal.getWidth() - 5 && x > 5) {
				animal.x = x;
			}
			if (y < Main.mainWindow.gamePanel.fieldPanel.getHeight() - animal.getHeight() - 5 && y > 5) {
				animal.y = y;
			}
			animal.setLocation(animal.x, animal.y);
		}

		// カップの上でネコを離すと消えて得点が加算
		public void mouseReleased(MouseEvent e) {
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			int px = (int) catPot.getLocationOnScreen().getX();
			int py = (int) catPot.getLocationOnScreen().getY();
			int ph = catPot.getHeight();
			int pw = catPot.getWidth();
			if ((px < x) && (x < px + pw) && (py < y) && (y < py + ph)) {
				// 鳴き声を再生
//				MySpeaker.playSE(animal.voiceKey);
				// スコア計算と不可視化処理
				Main.mainWindow.gamePanel.score += animal.score;
				String str = "SCORE：" + Main.mainWindow.gamePanel.score;
				Main.mainWindow.gamePanel.menuBar.scoreLabel.setText(str);
				animal.setVisible(false);
				// 追加
				generateAnimal();
			} else {
				animal.timer.start();
			}

		}

	}

}
