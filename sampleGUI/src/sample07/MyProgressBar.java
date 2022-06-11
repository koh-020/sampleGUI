package sample07;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MyProgressBar extends JProgressBar implements ActionListener{
	private static final long serialVersionUID = 1L;
		String name;
		int max;
		int now;
		int hold01;
		int hold02;
		Timer timer;
		Mode mode = Mode.waiting;
		
		public enum Mode {
			waiting, max, middle,zero, increasing, decreasing;
		}
		
		//コンストラクタ
		MyProgressBar(String str, int maximum) {
			name = str;
			max = maximum;
			now = max;
			
			//色の設定
			this.setForeground(Color.green);
			this.setBackground(Color.white);
			this.setUI(new BasicProgressBarUI() {
				@Override protected Color getSelectionForeground() {
					return Color.black; //文字の色（前）
				}
				@Override protected Color getSelectionBackground() {
					return Color.gray;
				}
			});
			
			//値と文字の設定
			this.setValue(100);
			this.updateText();
			this.setFont(new Font("MV Boli", Font.BOLD, 15));
			this.setStringPainted(true);
			timer = new Timer(10, this);
		}
		//文字列を更新するメソッド
		public void updateText() {
			this.setString(name + "：" + now + "/" + max);
		}
		
		//値を1減らすメソッド
		public void minusOne() {
			now -= 1;
			this.setValue(now*100/max); //あまりのある割り算
			this.updateText();
		}
		
		//値を1増やすメソッド
		public void plusOne() {
			now += 1;
			this.setValue(now*100/max); //あまりのある割り算
			this.updateText();
		}
		
		//値を減らすメソッド
		public void minus(int num) {
			hold01 = num;
			hold02 = now - num;
			this.mode = Mode.decreasing;
			this.timer.start();
		}
		
		//値を増やすメソッド
		public void plus(int num) {
			hold01 = num;
			hold02 = now + num;
			this.mode = Mode.increasing;
			this.timer.start();
		}
		
		//オーバーライド
		public void actionPerformed(ActionEvent e) {
			switch(this.mode) {
				case decreasing:
					if (this.now > this.hold02) {
						this.minusOne();
					} else {
						this.timer.stop();
						this.mode = Mode.middle;
					}
					
					if (this.now <= max/2) {
						this.setForeground(Color.yellow);
					}
					if (this.now <= max/4) {
						this.setForeground(Color.red);
					}
					if (this.now <= 0) {
						this.timer.stop();
						this.mode = Mode.zero;
					}
				break;
				case increasing:
					if(this.now < this.hold02) {
						this.plusOne();
					} else {
						this.timer.stop();
						this.mode = Mode.middle;
					}
					if(this.now >= max/4) {
						this.setForeground(Color.yellow);
					}
					if(this.now >= max/2) {
						this.setForeground(Color.green);
					}
					if(this.now >= this.max) {
						this.timer.stop();
						this.mode = Mode.max;
					}
				break;
				default:
					break;
			}
		}
		
}
