package application;

import static application.Define.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
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

    private ChoiceBox<?>[] selecters;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタ
		ArrayList<WordSet> li = new ArrayList<>();
		//コンボボックスの配列を初期化
		selecters = List.of(word1Select,word2Select,partsSelect).toArray(ChoiceBox<?>[]::new);

		li.add(new WordSet("emp", "社員"));
		li.add(new WordSet("gomi","ゴミ"));
		li.add(new WordSet("", "空白"));
		word1Select.getItems().addAll(li);
		word1Select.setValue(li.get(0));
		word2Select.getItems().addAll(li);
		word2Select.setValue(li.get(0));
		partsSelect.getItems().addAll(li);
		partsSelect.setValue(li.get(0));
		copyBtn.disableProperty().bind(Bindings.createBooleanBinding(
				() -> resultForm.getText().isEmpty(),//結果フォームが空白の時はボタンを押せない
				resultForm.textProperty()));
		Arrays.stream(selecters).forEach(s -> s.setOnAction(event -> {
			resultForm.setText(generate());//コンボボックスの値が変更されると結果フォームに反映する
		}));
		copyBtn.setOnAction(event -> {//クリップボードにコピーボタンが押された時
			Clipboard cb = Clipboard.getSystemClipboard();
			cb.setContent(Map.of(DataFormat.PLAIN_TEXT,resultForm.getText()));
			Alert alt = new Alert(AlertType.INFORMATION,I001,ButtonType.OK);
			alt.setTitle(I002);
			alt.setHeaderText(I002);;
			alt.showAndWait();
		});

	}
	/**
	 * 変数名を生成する
	 * @return 生成した変数名
	 */
	private String generate() {
		String[] tmpSts = firstUpper(word1Select.getValue(),word2Select.getValue(),partsSelect.getValue());
		if(tmpSts.length > ZERO) {
			tmpSts[ZERO] = tmpSts[ZERO].substring(ZERO,ONE).toLowerCase() + tmpSts[ZERO].substring(ONE);
		}
		return String.join("", tmpSts);
	}
	/**
	 * キーの先頭を大文字にする
	 * @param sets 先頭を大文字にしたいWordSetを任意数
	 * @return 先頭が大文字になったキーの配列
	 */
	private String[] firstUpper(WordSet ...sets) {
		sets = Arrays.stream(sets).filter(s -> s.getKey().length() > 1).toArray(WordSet[]::new);
	    return Arrays.stream(sets)
	    		.map(s -> s.getKey().substring(ZERO, ONE).toUpperCase() + s.getKey().substring(ONE).toLowerCase())
	    		.toArray(String[]::new);
	}

}
