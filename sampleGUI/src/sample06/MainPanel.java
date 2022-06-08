package sample06;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//定数フィールド
	Color backgroundColor = Color.lightGray;
	//コンポーネント
	JLayeredPane layeredPane;
	JButton sortButton;
	JLabel redLabel;
	JLabel yellowLabel;
	JLabel greenLabel;
	JLabel blueLabel;
	JLabel blackLabel;
	
	//リスナー
	MyButtonListener myButtonListener;

	//コンストラクタ
	MainPanel() {
		//パネルサイズと貼り付け位置の設定は不要
		this.setLayout(null);  //レイアウトの設定
		this.setBackground(backgroundColor);  //背景の色
		//　その他追加設定をここに追記
	}
	
	//メソッド
	public void prepareComponents() {
		//以下コンポーネントに関する命令（以下は一例）
		//ボタンの設定
		sortButton = new JButton();
		sortButton.setBounds(200, 10, 100, 30);
		sortButton.setText("初期化");
		sortButton.setFocusable(false);
		myButtonListener = new MyButtonListener();
		sortButton.addActionListener(myButtonListener);
		this.add(sortButton);
		
		//レイヤードペインを追加
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 800, 560);
		this.add(layeredPane);
		
		//5色のラベルを作成
		redLabel = new JLabel();
		yellowLabel = new JLabel();
		greenLabel = new JLabel();
		blueLabel = new JLabel();
		blackLabel = new JLabel();
		
		redLabel.setBackground(Color.red);
		yellowLabel.setBackground(Color.yellow);
		greenLabel.setBackground(Color.green);
		blueLabel.setBackground(Color.blue);
		blackLabel.setBackground(Color.black);
		
		redLabel.setOpaque(true);
		yellowLabel.setOpaque(true);
		greenLabel.setOpaque(true);
		blueLabel.setOpaque(true);
		blackLabel.setOpaque(true);
		
		redLabel.setBounds(10, 10, 100, 100);
		yellowLabel.setBounds(20, 20, 100, 100);
		greenLabel.setBounds(30, 30, 100, 100);
		blueLabel.setBounds(40, 40, 100, 100);
		blackLabel.setBounds(50, 50, 100, 100);
		
		new DDListener(redLabel);      //コンストラクタで自動的に引数にaddする
		new DDListener(yellowLabel);
		new DDListener(greenLabel);
		new DDListener(blueLabel);
		new DDListener(blackLabel);
		
		//レイヤードペインに張り付ける（数字が大きいほど前にでる）
		this.layeredPane.add(redLabel, 500);
		this.layeredPane.add(yellowLabel, 400);
		this.layeredPane.add(greenLabel, 300);
		this.layeredPane.add(blueLabel, 200);
		this.layeredPane.add(blackLabel, 100);
	}
	
	//初期化用メソッド
	public void sort() {
		//位置・並び順を初期化
		redLabel.setLocation(10, 10);
		yellowLabel.setLocation(20, 20);
		greenLabel.setLocation(30, 30);
		blueLabel.setLocation(40, 40);
		blackLabel.setLocation(50, 50);
		
		this.layeredPane.moveToBack(redLabel);
		this.layeredPane.moveToBack(yellowLabel);
		this.layeredPane.moveToBack(greenLabel);
		this.layeredPane.moveToBack(blueLabel);
		this.layeredPane.moveToBack(blackLabel);
	}
	
	//内部クラス（ドラッグアンドドロップ用）
	private class DDListener extends MouseAdapter {
		private int dx;
		private int dy;
		private JLabel comp;
		
		//コンストラクタ
		DDListener(JLabel c) {
			//引数としてadd咲のコンポーネントを指定しておく
			comp = c;
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
		}
		
		public void mousePressed(MouseEvent e) {
			//抑えたところからラベルの左上の差を取っておく
			dx = e.getXOnScreen() - comp.getX();
			dy = e.getYOnScreen() - comp.getY();
		}
		
		public void mouseDragged(MouseEvent e) {
			//マウスの座標からラベルの座標を取得する
			int x = e.getXOnScreen() - dx;
			int y = e.getYOnScreen() - dy;
			comp.setLocation(x, y);
		}
		
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2) {
				//ダブルクリックで一番前に
				layeredPane.moveToFront(e.getComponent());
			}
		}
	}
	
	//内部クラス（ボタンを監視）
	private class MyButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == sortButton) {
				sort();
			}
		}
 	}
}