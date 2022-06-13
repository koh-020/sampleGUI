package sample07;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//定数フィールド
	Color backgroundColor = Color.green;
	final int MAXHP = 243;
	final int DAMAGE = 43;
	//コンポーネント
	JPanel hpPanel;
	JPanel mainPanel;
	JPanel buttonPanel;
	
	JLabel mainLabel;
	ImageIcon heroIcon;
	ImageIcon bakuhaIcon;
	ImageIcon ohakaIcon;
	ImageIcon genkiIcon;
	
	JButton button;
	JLabel label;
	MyProgressBar hpBar;
	Timer checkHPcondition;
	
	//リスナー
	MyActionListener myActionListener; //ボタンと現在HPの監視
	
	
	
	

	//コンストラクタ
	MainPanel() {
		//パネルサイズと貼り付け位置の設定は不要
		this.setLayout(new BorderLayout());  //レイアウトの設定
		this.setBackground(backgroundColor);  //背景の色
		//　メインパネルに貼るパネルを生成
		hpPanel = new JPanel();
		mainPanel = new JPanel();
		buttonPanel = new JPanel();
		hpPanel.setBackground(Color.cyan);
		mainPanel.setBackground(Color.white);
		buttonPanel.setBackground(Color.black);
		this.add(hpPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	//メソッド
	public void prepareComponents() {
		//以下コンポーネントに関する命令（以下は一例）
		mainLabel = new JLabel();      //ラベル生成
		heroIcon = new ImageIcon(getClass().getClassLoader().getResource("./images/yuusya_game.png"));
		bakuhaIcon = new ImageIcon(getClass().getClassLoader().getResource("./images/bakuhatsu.png"));
		ohakaIcon = new ImageIcon(getClass().getClassLoader().getResource("./images/ohaka_seiyou.png"));
		genkiIcon = new ImageIcon(getClass().getClassLoader().getResource("./images/seikou_banzai_man.png"));
		mainLabel.setIcon(heroIcon);  //ラベルに画像を貼りつけ
		mainPanel.add(mainLabel);  //ラベルをこのパネルに貼る
		
		//ボタン
		button = new JButton();
		button.setText("勇者を攻撃(HPを" + DAMAGE + "減らす)");
		button.setFocusable(false);
		myActionListener = new MyActionListener();
		button.addActionListener(myActionListener);
		buttonPanel.add(button);
		
		//パネル内にプログレスバーを表示（制限時間用）
		hpBar = new MyProgressBar("HP", MAXHP);
		checkHPcondition = new Timer(5, myActionListener);
		checkHPcondition.start();
		hpPanel.add(hpBar);
	}
	
	//内部クラス（ボタンを監視）
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//ボタン用
			if (e.getSource() == button) {
				if(mainLabel.getIcon() == heroIcon) {
					hpBar.minus(DAMAGE);
					mainLabel.setIcon(bakuhaIcon);
					button.setEnabled(false);
					button.setText("勇者に" + DAMAGE + "のダメージ!");
				} else if (mainLabel.getIcon() == ohakaIcon) {
					hpBar.plus(MAXHP);
					mainLabel.setIcon(genkiIcon);
					button.setEnabled(false);
					button.setText("蘇生中...");
				}
			}
			
			//HPの状態を監視
			if(e.getSource() == checkHPcondition) {
				if(hpBar.mode == MyProgressBar.Mode.max) {
					mainLabel.setIcon(heroIcon);
					button.setEnabled(true);
					button.setText("勇者を攻撃(HPを" + DAMAGE + "減らす)");
					hpBar.mode = MyProgressBar.Mode.waiting;
					
				} else if (hpBar.mode == MyProgressBar.Mode.zero) {
					mainLabel.setIcon(ohakaIcon);
					button.setEnabled(true);
					button.setText("勇者を蘇生");
					hpBar.mode = MyProgressBar.Mode.waiting;
				} else if(hpBar.mode == MyProgressBar.Mode.middle) {
					mainLabel.setIcon(heroIcon);
					button.setEnabled(true);
					button.setText("勇者を攻撃(HPを" + DAMAGE + "減らす)");
					hpBar.mode = MyProgressBar.Mode.waiting;
				}
			}
		}
		
	}
}