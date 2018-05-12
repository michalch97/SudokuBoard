import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        primaryStage.setMinWidth(420);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }

    public void startNewGame(final DifficultyLevel chosenDifficulty) {
        sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        sudokuSolver.solve(sudokuBoard);
        try {
            SudokuBoard boardToSolve = sudokuBoard.clone();
            boardToSolve.prepareBoardToGame(chosenDifficulty);
            sudokuBoard.draw();
            BoardWindow window = new BoardWindow(this, boardToSolve);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStartGameWindowEvents() {
        startGameController.setOnStartButtonClickedEvent(this);
    }

    public void checkTheCorrectness(final List<Integer> fields) {
        List<SudokuField> board = Arrays.asList(new SudokuField[81]);
        for (int i = 0; i < board.size(); i++) {
            board.set(i, new SudokuField(fields.get(i)));
        }
        SudokuBoard boardAfterGame = new SudokuBoard(board);
        String teqxt = sudokuBoard.equals(boardAfterGame) ? "GOOD!" : "BAD!";
        System.out.println(teqxt);
    }
}
