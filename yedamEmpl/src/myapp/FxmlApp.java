package myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("viewPackage/Board.fxml"));
		// 컨테이너들은 모두 Parent의 상위클래스 어떤 유형(Root, AnchorPane...)이든 다 받을수 있음
		
		// 컨테이너를 Scene의 매개값으로 넣어줌
		Scene scene = new Scene(root);

		// Stage의 매개값으로 Scene 달아줌
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
