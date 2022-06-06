package sampleJFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuBar extends JPanel{
	private static final long serialVersionUID = 1L;
	//コンポーネント
	JButton homeButton;
	JLabel homeLabel;
	HomeButtonListener homeButtonListener;
	
	//コンストラクタ
	public MenuBar() {
		//パネルサイズと貼り付け位置の設定は不要（BorderLayoutが自動で画面サイズに合わせてくれる）
		this.setPreferredSize(new Dimension(100, 400)); //幅は自動調整されるがこの命令は必要
		this.setBackground(Color.red);
		this.setLayout(null);
	}
	
	//メソッド
	//コンストラクタが呼ばれた後に呼び出す
	public void prepareComponents() {
		//ホームボタン
		homeButton = new JButton();
		homeButton.setBounds(5, 5, 80, 30);
		homeButton.setText("HOME");
		homeButton.setFocusable(false);
		homeButtonListener = new HomeButtonListener();
		homeButton.addActionListener(homeButtonListener);
		
		//ラベル
		homeLabel = new JLabel("←click this button or press 'h' to home");
		homeLabel.setBounds(100, 5, 250, 30);
		homeLabel.setBorder(BorderFactory.createEtchedBorder(3, Color.black, Color.white));
		
		//設置
		this.add(homeButton);
		this.add(homeLabel);
	}
	
	//内部クラス(ホームボタン用リスナー)
	private class HomeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
		}
	}
	
}