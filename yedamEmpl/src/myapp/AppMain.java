package myapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// start �޼ҵ忡 ��Ʈ��(label,button) �����
		Label label = new Label();
		label.setText("Hello, JavaFX"); // �󺧿� ������ ����
		label.setFont(new Font(50)); // ��Ʈ Ŭ������ �ν��Ͻ��� �;���
		
		Button button = new Button();
		button.setText("Ȯ��");
		button.setOnAction(new EventHandler<ActionEvent>() {// ��ư������ �̺�Ʈ���� �޼ҵ�
		@Override
		public void handle(ActionEvent arg0) {
			Platform.exit();
			}
		});
		// �����̳�(VBox)
		VBox root = new VBox();
		root.setPrefWidth(350);
		root.setPrefHeight(150);
		root.setAlignment(Pos.CENTER); // Pos ������ Ÿ������ �����Ǿ�����
		root.setSpacing(20);
		
		root.getChildren().add(label);
		root.getChildren().add(button); // label�� button�� ��ڴ�

		// �����̳ʸ� Scene�� �Ű������� �־���
		Scene scene = new Scene(root);
		
		//Stage�� �Ű������� Scene �޾���
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		Application.launch(args); // Application.launch(���θ޼ҵ� �Ű�����);
	}

}