package sample03;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//定数フィールド
	Color backgroundColor = Color.green;
	Door doorcheck = Door.CLOSE;
	//コンポーネント
	JLabel doorLabel;
	ImageIcon doorOpenImage;
	ImageIcon doorCloseImage;
	JPanel buttonPanel;
	JButton changeButton;
	JButton endButton;
	
	
	//リスナー
	MyButtonListener myButtonListener;
	
	public enum Door {
		OPEN,
		CLOSE,
	}
	
	

	//コンストラクタ
	MainPanel() {
		//パネルサイズと貼り付け位置の設定は不要
		this.setLayout(new BorderLayout());  //レイアウトの設定
		this.setBackground(backgroundColor);  //背景の色
		//　その他追加設定をここに追記
	}
	
	//メソッド
	//コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		//以下コンポーネントに関する命令（以下は一例）
		changeButton = new JButton("扉を開ける");
		endButton = new JButton("終了");
//		doorCloseImage = new ImageIcon(getClass().getClassLoader().getResource("nikukyu.png"));
		ImageIcon closeIcon = new ImageIcon("C:\\Users\\ko-he-\\Desktop\\DMM\\java\\GUI\\sampleGUI\\src\\images/door_close.png");
		ImageIcon openIcon = new ImageIcon("C:\\Users\\ko-he-\\Desktop\\DMM\\java\\GUI\\sampleGUI\\src\\images/door_open.png");
		
		MediaTracker tracker = new MediaTracker(this);
//		画像サイズの変更
		Image smallCloseImage = closeIcon.getImage().getScaledInstance((int) (closeIcon.getIconWidth() * 0.5), -1, Image.SCALE_SMOOTH );
		Image smallOpenImage = openIcon.getImage().getScaledInstance((int) (openIcon.getIconWidth() * 0.5), -1, Image.SCALE_SMOOTH );
//		MediaTrackerで処理の終了を待つ
		tracker.addImage(smallCloseImage,1);
		tracker.addImage(smallOpenImage,2);
//		処理完了後の画像を取得
		doorCloseImage = new ImageIcon(smallCloseImage);
		doorOpenImage = new ImageIcon(smallOpenImage);
		
		doorLabel = new JLabel(doorCloseImage);      //ラベル生成
		
		//ボタンの設定
		changeButton.setFocusable(false); //カーソルなし
		endButton.setFocusable(false); //カーソルなし
		
//		ボタンをパネルに貼り付け
		buttonPanel = new JPanel();
		buttonPanel.add(changeButton);
		buttonPanel.add(endButton);
		
//		ボタンにリスナーを付ける
		myButtonListener = new MyButtonListener();
		changeButton.addActionListener(myButtonListener);
		endButton.addActionListener(myButtonListener);
		
//		ドアの画像とボタンパネルをメインパネルに貼り付ける
		this.add(doorLabel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}	
//		内部クラス(ボタンを監視)
		class MyButtonListener implements ActionListener {
			int check = 0;
			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == endButton) {
					System.exit(0);
				}
				if(event.getSource() == changeButton) {
					if(doorcheck == Door.CLOSE) {
						doorLabel.setIcon(doorOpenImage);
						doorcheck = Door.OPEN;
						changeButton.setText("扉を閉める");
						check += 1;
					} else {
						doorLabel.setIcon(doorCloseImage);
						doorcheck = Door.CLOSE;
						changeButton.setText("扉を開ける");
						if (check >= 10) {
							changeButton.setEnabled(false);
							changeButton.setText("扉はもう開きません");
						}
					}
				}
			}
		}
	
}