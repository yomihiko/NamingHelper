package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TopController {

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
    private ChoiceBox<?> word1Select;

    @FXML
    private ChoiceBox<?> word2Select;

    @FXML
    private ChoiceBox<?> partsSelect;

    @FXML
    private HBox resultBox;

    @FXML
    private TextField resultForm;

    @FXML
    private Button copyBtn;

}
