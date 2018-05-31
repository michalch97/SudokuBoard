
import java.io.File;
import java.net.URL;
import java.util.*;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardWindowController implements Initializable {
    private static final String textFieldErrorCss = "TextFieldError.css";
    private static final String winIconName = "medal.png";

    @FXML
    private GridPane boardGridPane;
    @FXML
    private Button checkBoardButton;
    @FXML
    private Button saveBoardButton;
    @FXML
    private Button loadBoardButton;
    @FXML
    private Button saveBoardToDB;
    @FXML
    private Button loadBoardFromDB;

    private BoardWindow boardWindow;
    private Stage guiParent;
    private List<TextField> textFields = new ArrayList<TextField>();
    private List<GridPane> boxesOfFields = new ArrayList<GridPane>();
    private SudokuBoard currentSudokuBoard;
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @FXML
    public void initialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane boxGrid = new GridPane();
                boxGrid.setGridLinesVisible(true);

                boardGridPane.add(boxGrid, i, j);
                boxesOfFields.add(boxGrid);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                field.setTextFormatter(createFormater(field, textFields.size()));
                field.setAlignment(Pos.CENTER);
                field.setPrefSize(40, 40);

                boxesOfFields.get((j / 3) * 3 + i / 3).add(field, (j % 3) * 3, i % 3);
                textFields.add(field);
            }
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initialize();
    }

    public void setSudokuBoard(final SudokuBoard board) {
        if (board == null) {
            return;
        }

        currentSudokuBoard = board;
        StringConverter<Number> stringConverter = new StringConverter<Number>() {
            @Override
            public Number fromString(final String arg0) {
                if (arg0.isEmpty()) {
                    return 0;
                } else {
                    return Integer.parseInt(arg0);
                }
            }

            @Override
            public String toString(final Number arg0) {
                if (arg0.intValue() == 0) {
                    return "";
                } else {
                    return arg0.toString();
                }
            }
        };

        for (int i = 0; i < 81; i++) {
            SudokuField field = currentSudokuBoard.getField(i);

            if (field != null) {
                if (field.getIsUserProvidedField()) {
                    setFieldDisabled(textFields.get(i), false);
                } else {
                    setFieldDisabled(textFields.get(i), true);
                }
            } else {
                setFieldDisabled(textFields.get(i), false);
                currentSudokuBoard.set(i, 0, true);
                field = currentSudokuBoard.getField(i);
            }

            Bindings.bindBidirectional(textFields.get(i).textProperty(), field.getProperty(), stringConverter);
            textFields.get(i).textProperty().addListener((observable, oldValue, newValue) -> {
                checkTheCorrectness();
            });

        }
    }

    public void setParent(final BoardWindow parent) {
        boardWindow = parent;
    }

    public void setGuiParent(final Stage parent) {
        guiParent = parent;

        if (parent == null) {
            return;
        }
        String errorCss = getClass().getClassLoader().getResource(textFieldErrorCss).toString();
        Scene scene = guiParent.getScene();
        scene.getStylesheets().add(errorCss);
    }

    @FXML
    public void onCheckButtonAction() {
        if (currentSudokuBoard.checkBoard() && !currentSudokuBoard.checkIfContainsEmptyFields()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Koniec gry");
            alert.setHeaderText("Brawo!");
            alert.setContentText("Udało ci się rozwiązać sudoku!");

            Image winIcon = new Image(this.getClass().getResource(winIconName).toString());
            ImageView imageView = new ImageView(winIcon);
            alert.setGraphic(imageView);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(winIcon);

            alert.showAndWait();
            Stage currentStage = (Stage) boardGridPane.getScene().getWindow();
            currentStage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Niepoprawne rozwiązanie");
            alert.setHeaderText("To jeszcze nie koniec gry!");
            alert.setContentText("Plansza nie jest jeszcze poprawnie rozwiązana.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onSaveBoardButtonAction() throws Throwable {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File choosenFile = chooser.showSaveDialog(guiParent);

        if (choosenFile != null) {
            try {
                FileSudokuBoardDao serializator = new FileSudokuBoardDao(choosenFile);
                serializator.write(currentSudokuBoard);

                serializator.close();
                log.info("Serialized");
            } catch (Exception e) {
                Throwable te = new SudokuException("Serialization error");
                te.initCause(e);
                throw te;
            }
        }
    }

    @FXML
    public void onLoadBoardButtonAction() throws Throwable {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File choosenFile = chooser.showOpenDialog(guiParent);

        if (choosenFile != null) {
            try {
                FileSudokuBoardDao serializator = new FileSudokuBoardDao(choosenFile);
                SudokuBoard deserializedBoard = serializator.read();

                serializator.close();
                log.info("Deserialized");

                setSudokuBoard(deserializedBoard);
            } catch (Exception e) {
                Throwable te = new SudokuException("Serialization error");
                te.initCause(e);
                throw te;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void checkTheCorrectness() {
        final String errorCssClass = "textFieldError";

        for (TextField field : textFields) {
            field.getStyleClass().remove(errorCssClass);
        }

        if (!currentSudokuBoard.checkBoard()) {
            Set<Integer> errorIndexes = currentSudokuBoard.getIndexesOfUserIncorrectAnswers();
            for (int index : errorIndexes) {
                textFields.get(index).getStyleClass().add(errorCssClass);
            }
        }
    }

    private TextFormatter<String> createFormater(final TextField textField, int boardIndex) {
        TextFormatter<String> formatter = new TextFormatter<String>((Change change) -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }

            if (newText.length() > 1 || !newText.matches("\\d+")) {
                return null;
            } else if (Integer.parseInt(newText) < 1 || Integer.parseInt(newText) > 9) {
                return null;
            } else {
                return change;
            }
        });

        return formatter;
    }

    private void setFieldDisabled(final TextField field, boolean disabled) {
        if (disabled) {
            field.setDisable(true);
            field.setStyle("-fx-opacity: 1.0; -fx-background-color: rgba(230,230,230,0.5);");
        } else {
            field.setDisable(false);
            field.getStyleClass().clear();
            field.setStyle(null);
            field.getStyleClass().add("text-field");
            field.getStyleClass().add("text-input");
        }
    }

    public void onSavedBoardToDBButtonAction(final ActionEvent actionEvent) {
        String boardName = "";
        TextInputDialog dialog = new TextInputDialog("nazwa");
        dialog.setTitle("Zapisywanie tablicy");
        dialog.setHeaderText("Wybierz nazwę pod jaką ma być zapisana tablica");
        dialog.setContentText("Nazwa tablicy:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            boardName = result.get();
        }
        try {
            JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao(boardName);
            jdbcSudokuBoardDao.write(currentSudokuBoard);
            jdbcSudokuBoardDao.close();
            log.info("Saved in database");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void onLoadBoardFromDBButtonAction(final ActionEvent actionEvent) {
        String boardName = "";
        TextInputDialog dialog = new TextInputDialog("nazwa");
        dialog.setTitle("Wczytywanie tablicy");
        dialog.setHeaderText("Wybierz nazwę tablicy do wczytania");
        dialog.setContentText("Nazwa tablicy:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            boardName = result.get();
        }
        try {
            JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao(boardName);
            SudokuBoard sudokuBoard = jdbcSudokuBoardDao.read();
            jdbcSudokuBoardDao.close();
            setSudokuBoard(sudokuBoard);
            log.info("Loaded from database");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}