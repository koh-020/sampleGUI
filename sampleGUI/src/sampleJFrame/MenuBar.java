package sampleJFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MenuBar extends JPanel {
	private static final long serialVersionUID = 1L;
	// コンポーネント
	JButton homeButton;
	JProgressBar timeLimitBar;
	JLabel homeLabel;
	JLabel scoreLabel;
	HomeButtonListener homeButtonListener;

	// コンストラクタ
	public MenuBar() {
		// パネルサイズと貼り付け位置の設定は不要（BorderLayoutが自動で画面サイズに合わせてくれる）
		this.setPreferredSize(new Dimension(100, 40)); // 幅は自動調整されるがこの命令は必要
		this.setBackground(new Color(224, 255, 255));
		this.setLayout(null);
	}

	// メソッド
	// コンストラクタが呼ばれた後に呼び出す
	public void prepareComponents(int timeMax, int score) {

		// ホームボタン
		homeButton = new JButton();
		homeButton.setBounds(5, 5, 80, 30);
		homeButton.setText("HOME");
//		homeButton.setFocusable(false);
		homeButtonListener = new HomeButtonListener();
		homeButton.addActionListener(homeButtonListener);

		// パネル内にプログレスバーを表示（制限時間用）
		timeLimitBar = new JProgressBar(0, 100);
		timeLimitBar.setBounds(400, 5, 300, 30);
		timeLimitBar.setForeground(Color.red);
		timeLimitBar.setUI(new BasicProgressBarUI() {
			@Override
			protected Color getSelectionForeground() {
				return Color.black;
			}

			@Override
			protected Color getSelectionBackground() {
				return Color.gray;
			}
		});
//		System.out.println("情報" + timeLimitBar.getString());
		timeLimitBar.setFont(new Font("MV Boli", Font.BOLD, 25));
		timeLimitBar.setStringPainted(false);

		// スコアを表示
		scoreLabel = new JLabel();
		scoreLabel.setText("SCORE : " + score);
		scoreLabel.setBounds(800, 5, 300, 30);

		// ラベル
		homeLabel = new JLabel("←click this button or press 'h' to home");
		homeLabel.setBounds(100, 5, 250, 30);
//		homeLabel.setBorder(BorderFactory.createEtchedBorder(3, Color.black, Color.white));

		// 設置
		this.add(homeButton);
		this.add(timeLimitBar);
		this.add(homeLabel);
		this.add(scoreLabel);
	}

	// 制限時間を表示
	public void actionPerformed(int time, int max) {
//		timeLimitBar.setString(Integer.toString(time));
		timeLimitBar.setValue(time * 100 / max);
	}

	// 内部クラス(ホームボタン用リスナー)
	private class HomeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.mainWindow.gamePanel.backToTitleDialogue();
//			Main.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
		}
	}

}
