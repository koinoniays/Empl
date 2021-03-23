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
		// �����̳ʵ��� ��� Parent�� ����Ŭ���� � ����(Root, AnchorPane...)�̵� �� ������ ����
		
		// �����̳ʸ� Scene�� �Ű������� �־���
		Scene scene = new Scene(root);

		// Stage�� �Ű������� Scene �޾���
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}