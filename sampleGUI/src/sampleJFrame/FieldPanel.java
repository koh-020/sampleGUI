package sampleJFrame;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FieldPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel gameLabel;
	
	//コンストラクタ
	public FieldPanel() {
		this.setBackground(Color.orange);
		this.setLayout(null);
	}
	
	//コンストラクタが呼び出された後に手動で呼び出す
	public void prepareComponents() {
		//コンポーネント
		gameLabel = new JLabel();
		gameLabel.setText("ゲーム画面");
		gameLabel.setBounds(0, 0, 100, 30);
		gameLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		this.add(gameLabel);
	}
}