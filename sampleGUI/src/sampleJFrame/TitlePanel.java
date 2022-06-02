package sampleJFrame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
public class TitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;
//	コンポーネント
	JLabel titleLabel;
	
//	コンストラクタ
	public TitlePanel() {
//		パネルサイズと貼り付け位置の設定は不要
		this.setLayout(null); //レイアウトの設定
		this.setBackground(Color.red); //背景色
	}
	
//	コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		titleLabel = new JLabel();
		titleLabel.setText("タイトル画面");
		titleLabel.setBounds(100, 0, 100, 30);
		this.add(titleLabel);
	}
}