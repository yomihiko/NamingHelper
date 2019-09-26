package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TopController implements Initializable{

    @FXML
    private GridPane mainPane;

    @FXML
    private HBox titleBox;

    @FXML
    private Label titleLabel;

    @FXML
    private Label word1Label;

    @FXML
    private Label word2Label;

    @FXML
    private Label partsLabel;

    @FXML
    private ChoiceBox<WordSet> word1Select;

    @FXML
    private ChoiceBox<WordSet> word2Select;

    @FXML
    private ChoiceBox<WordSet> partsSelect;

    @FXML
    private HBox resultBox;

    @FXML
    private TextField resultForm;

    @FXML
    private Button copyBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタ
		ArrayList<WordSet> li = new ArrayList<>();
		li.add(new WordSet("hubuki", "吹雪"));

		word1Select.getItems().addAll(li);
		word1Select.setValue(new WordSet("hatuyuki", "初雪"));
		copyBtn.disableProperty().bind(Bindings.createBooleanBinding(
				() -> resultForm.getText().isEmpty(),
				resultForm.textProperty()));
		word1Select.setOnAction(event -> {

		});

	}
	private String generate() {
		String w1 = word1Select.getValue().getKey().toUpperCase(Locale.US);
	}

}
