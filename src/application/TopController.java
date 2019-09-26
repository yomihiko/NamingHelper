package application;

import static application.Define.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタ
		//コンボボックスの配列を初期化
		ChoiceBox<?>[] selecters = {word1Select,word2Select,partsSelect};

		try(DBConnect db = new DBConnect()){
			ArrayList<WordSet> words = db.SelectWords();
			ArrayList<WordSet> parts = db.SelectParts();
			//データベースからワードセットを取得
			word1Select.getItems().addAll(words);
			word2Select.getItems().addAll(words);
			partsSelect.getItems().addAll(parts);
			word1Select.setValue(words.get(ZERO));
			word2Select.setValue(words.get(ZERO));
			partsSelect.setValue(parts.get(ZERO));
		}catch (Exception e) {
			// TODO: handle exception
			Alert alt = new Alert(AlertType.ERROR,E001,ButtonType.CLOSE);
			alt.showAndWait();
		}
		copyBtn.disableProperty().bind(Bindings.createBooleanBinding(
				() -> resultForm.getText().isEmpty(),//結果フォームが空白の時はボタンを押せない
				resultForm.textProperty()));
		Arrays.stream(selecters).forEach(s -> s.disableProperty()//コンボボックスに値がないときは無効化
				.set(s.getItems().size() == ZERO));
		Arrays.stream(selecters).forEach(s -> s.setOnAction(event -> {
			resultForm.setText(generate());//コンボボックスの値が変更されると結果フォームに反映する
		}));
		copyBtn.setOnAction(event -> {//クリップボードにコピーボタンが押された時
			Clipboard cb = Clipboard.getSystemClipboard();
			Map<DataFormat,Object> tmpMap = new HashMap<>();
			tmpMap.put(DataFormat.PLAIN_TEXT,resultForm.getText());
			cb.setContent(tmpMap);
			Alert alt = new Alert(AlertType.INFORMATION,resultForm.getText() + I001,ButtonType.OK);
			//コピーができたアラートを表示
			alt.setTitle(I002);
			alt.setHeaderText(I003);;
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
		return String.join(NOTHING, tmpSts).trim();
	}
	/**
	 * キーの先頭を大文字にする
	 * @param sets 先頭を大文字にしたいWordSetを任意数
	 * @return 先頭が大文字になったキーの配列
	 */
	private String[] firstUpper(WordSet ...sets) {
		sets = Arrays.stream(sets).filter(s -> s.getKey().length() > ONE).toArray(WordSet[]::new);
	    return Arrays.stream(sets)
	    		.map(s -> s.getKey().substring(ZERO, ONE).toUpperCase() + s.getKey().substring(ONE).toLowerCase())
	    		.toArray(String[]::new);
	}

}
