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
		// start 메소드에 컨트롤(label,button) 만들기
		Label label = new Label();
		label.setText("Hello, JavaFX"); // 라벨에 보여질 문구
		label.setFont(new Font(50)); // 폰트 클래스의 인스턴스가 와야함
		
		Button button = new Button();
		button.setText("확인");
		button.setOnAction(new EventHandler<ActionEvent>() {// 버튼누르면 이벤트실행 메소드
		@Override
		public void handle(ActionEvent arg0) {
			Platform.exit();
			}
		});
		// 컨테이너(VBox)
		VBox root = new VBox();
		root.setPrefWidth(350);
		root.setPrefHeight(150);
		root.setAlignment(Pos.CENTER); // Pos 열거형 타입으로 지정되어있음
		root.setSpacing(20);
		
		root.getChildren().add(label);
		root.getChildren().add(button); // label과 button을 담겠다

		// 컨테이너를 Scene의 매개값으로 넣어줌
		Scene scene = new Scene(root);
		
		//Stage의 매개값으로 Scene 달아줌
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		Application.launch(args); // Application.launch(메인메소드 매개변수);
	}

}
