import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BoardWindow {
    private static final String boardWindowFXML = "BoardWindow.fxml";
    
    private Stage boardStage;
    private StartGameWindow parent;
    private BoardWindowController boardWindowController;
    
    public BoardWindow(final StartGameWindow parent, final SudokuBoard board) throws IOException {
        this.parent = parent;
        
        URL url = getClass().getClassLoader().getResource(boardWindowFXML);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        boardWindowController = loader.getController();
        boardWindowController.setSudokuBoard(board);
        initBoardWindowEvents();
        
        boardStage = new Stage();
        boardStage.setTitle("Plansza sudoku");
        boardStage.setScene(scene);
        boardStage.initOwner(parent.getStage());
        boardStage.initModality(Modality.APPLICATION_MODAL);
        boardStage.setMinWidth(330);
        boardStage.setMinHeight(430);
        boardStage.show();
    }
    
    private void initBoardWindowEvents() {
        boardWindowController.setBoardWindows(this);
    }

    public void checkTheCorrectness(final List<Integer> fields) {
        parent.checkTheCorrectness(fields);
    }
}
