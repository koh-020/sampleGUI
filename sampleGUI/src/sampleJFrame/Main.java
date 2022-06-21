package sampleJFrame;

import javax.swing.UIManager;

public class Main {
	static MainWindow mainWindow;
	public static void main(String[] args) {
		//Buttonのフォーカスに関する設定
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		mainWindow = new MainWindow();
		mainWindow.preparePanels();
		mainWindow.prepareComponents();
		mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
		mainWindow.setVisible(true);
	}
}
 