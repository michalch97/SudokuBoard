
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BoardWindowController implements Initializable {
    @FXML
    private GridPane boardGridPane;
    @FXML
    private Button checkBoardButton;
    private BoardWindow boardWindow;
    private List<TextField> textFields = new ArrayList<TextField>();

    @FXML
    public void initialize() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField(Integer.toString(j));
                field.setAlignment(Pos.CENTER);
                //field.setPrefSize(20, 20);
                boardGridPane.add(field, j, i);
                textFields.add(field);
            }
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initialize();
    }

    public void setSudokuBoard(final SudokuBoard board) {
        for (int i = 0; i < 81; i++) {
            String field = Integer.toString(board.get(i));
            if (!field.equals("0")) {
                textFields.get(i).setEditable(false);
            }
            textFields.get(i).setText(field.equals("0") ? "" : field);
        }
    }

    @FXML
    public void onCheckButtonAction() {
        List<Integer> fields = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            fields.add(i, Integer.parseInt(textFields.get(i).getText().equals(" ") ? "0" : textFields.get(i).getText()));
        }
        boardWindow.checkTheCorrectness(fields);
    }

    public void setBoardWindows(final BoardWindow boardWindow) {
        this.boardWindow = boardWindow;
    }
}