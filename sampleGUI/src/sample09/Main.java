package sample09;

import javax.swing.UIManager;
public class Main {
	static MainWindow mainWindow;
	public static void main(String[] args) {
		//Buttonのフォーカスに関する設定
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		
		mainWindow = new MainWindow();	//ウィンドウのみを生成
		mainWindow.preparePanels();     //ペインに直接貼るパネルのみを生成
		mainWindow.prepareComponents(); //その他のコンポーネントを生成
		mainWindow.setFrontScreenAndFocus(ScreenMode.MAIN); //起動後最初に表示するべき画面を手前に持ってきてそれに注目させる
		mainWindow.setVisible(true);    //最後にウィンドウを可視化
	}
}