import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartGameWindow extends Application {
    private static final String startGameWindowFXML = "StartGameWindow.fxml";

    private Stage primaryStage;
    private StartGameWindowController startGameController;
    private SudokuBoard sudokuBoard;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URL url = getClass().getClassLoader().getResource(startGameWindowFXML);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        startGameController = loader.getController();
        initStartGameWindowEvents();

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(220);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }

    public void startNewGame(final DifficultyLevel chosenDifficulty) throws Throwable {
        sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        sudokuSolver.solve(sudokuBoard);
        try {
            sudokuBoard.prepareBoardToGame(chosenDifficulty);
            sudokuBoard.draw();
            BoardWindow window = new BoardWindow(this, sudokuBoard);
        } catch (IOException e) {
            Throwable te = new SudokuException("Start new game error");
            te.initCause(e);
            throw te;
        }
    }

    private void initStartGameWindowEvents() {
        startGameController.setOnStartButtonClickedEvent(this);
    }
}
