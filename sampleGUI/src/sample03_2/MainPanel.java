package sample03_2;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//定数フィールド
	Color backgroundColor = Color.green;
	//コンポーネント
	JLabel label;
	JButton button;
	
//	リスナー
	MyButtonListener myButtonListener;

	//コンストラクタ
	MainPanel() {
		//パネルサイズと貼り付け位置の設定は不要
		this.setLayout(null);  //レイアウトの設定
//		this.setBackground(backgroundColor);  //背景の色なし
		//　その他追加設定をここに追記
	}
	
	//メソッド
	public void prepareComponents() {
		//以下コンポーネントに関する命令（以下は一例）
		//ネコの画像
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("./images/nikukyu.png"));
		
		//見えないネコのラベル
		label = new JLabel();      //ラベル生成
		label.setIcon(icon);  //ラベルに文字を記入
		label.setBounds(30, 220, 200, 200);  //位置とサイズを指定
		label.setVisible(false);
		
		//ボタンとリスナー
		button = new JButton();
		button.setBounds(10, 10, 200, 200); //形と位置を決定
		myButtonListener = new MyButtonListener();
		button.addActionListener(myButtonListener);
		
		//ボタンのカスタマイズいろいろ
		button.setText("I'm a Button!!");
		button.setFocusable(false);
		button.setIcon(icon);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setFont(new Font("Comic Sans", Font.BOLD, 25));
		button.setBackground(Color.blue);
		button.setBorder(BorderFactory.createEtchedBorder());
		
		//取り付け
		this.add(label);
		this.add(button);
	}
	
	//内部クラス(ボタンを監視)
	private class MyButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button) {
				button.setEnabled(false);
				label.setVisible(true);
			}
		}
		
	}
}