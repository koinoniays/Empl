package myapp.viewPackage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.BoardVO;
import common.InputBoardVO;
import common.InputDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class BoardController implements Initializable {
	@FXML
	TableView<BoardVO> tableView;
	@FXML
	TextField boardNo, title;
	@FXML
	TextArea contents;
	@FXML
	ComboBox<String> publicity;
	@FXML
	DatePicker exitDate;
	@FXML
	Button updateBtn, deleteBtn, addBtn;

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			// setOnAction의 매개값으로 EventHandler인터페이스를 구현하는 익명구현객체 람다식으로 표현 가능
			@Override
			public void handle(ActionEvent e) {
				deleteBtnAction(e);
			}

		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addBtnAction(e);
			}
		});

		ObservableList<BoardVO> list = InputDAO.boardList();

		tableView.setPrefWidth(450);
		tableView.setLayoutX(30);

		TableColumn<BoardVO, Integer> tcBoardNo = (TableColumn<BoardVO, Integer>) tableView.getColumns().get(0);
		tcBoardNo.setCellValueFactory(new PropertyValueFactory<BoardVO, Integer>("boardNo"));

		TableColumn<BoardVO, String> tcTitle = (TableColumn<BoardVO, String>) tableView.getColumns().get(1);
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<BoardVO, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BoardVO, String> param) {
				return param.getValue().titleProperty();
			}
		});
		tcBoardNo.setPrefWidth(70);

		TableColumn<BoardVO, String> tcPub = new TableColumn<BoardVO, String>("공개");
		tcPub.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("publicity"));
		tableView.getColumns().add(tcPub);

		TableColumn<BoardVO, String> tcDate = new TableColumn<BoardVO, String>("날짜");
		tcDate.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("exitDate"));
		tableView.getColumns().add(tcDate);

		TableColumn<BoardVO, String> tcCon = new TableColumn<BoardVO, String>("내용");
		tcCon.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("contents"));
		tableView.getColumns().add(tcCon);

		tableView.setItems(list);

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BoardVO>() {

			@Override
			public void changed(ObservableValue<? extends BoardVO> arg0, BoardVO oldVal, BoardVO newVal) {
//				System.out.println(newVal.getBoardNo());
				boardNo.setText(String.valueOf(newVal.getBoardNo()));
				title.setText(newVal.getTitle());
				publicity.setValue(newVal.getPublicity());
				contents.setText(newVal.getContents());
				exitDate.setValue(LocalDate.parse(newVal.getExitDate()));
			}

		});
	} // end of initialize

	public void updateBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		vo.setContents(contents.getText());
		vo.setExitDate(exitDate.getValue().toString());
		vo.setPublicity(publicity.getValue());
		InputDAO.updateBoard(vo);
	}

	public void deleteBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		InputDAO.deleteBoard(vo);
	}

	public void addBtnAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);

		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardAdd.fxml"));
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.show();

			Button btnReg = (Button) ap.lookup("#btnReg");
			btnReg.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) ap.lookup("#txtTitle");
					InputBoardVO vo = new InputBoardVO();
					vo.setTitle(txtTitle.getText());
					
					TextArea txtContent = (TextArea) ap.lookup("#txtContent");
					vo.setContents(txtContent.getText());
					
					PasswordField txtPassword = (PasswordField) ap.lookup("#txtPassword");
					vo.setPasswd(txtPassword.getText());
					
					ComboBox<String> comboPublic = (ComboBox<String>) ap.lookup("#comboPublic");
					vo.setPublicity(comboPublic.getValue());
					
					DatePicker dateExit = (DatePicker) ap.lookup("#dateExit");
					vo.setExitDate(dateExit.getValue().toString());
					
					
					InputDAO.insertBoard(vo);


				}

			});

		} catch (IOException e1) {

			e1.printStackTrace();
		}

	}

}
