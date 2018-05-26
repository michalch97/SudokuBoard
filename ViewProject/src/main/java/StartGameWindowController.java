import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartGameWindowController implements Initializable {
    @FXML
    private ComboBox<DifficultyLevel> difficultyComboBox;
    @FXML
    private Button startGameButton;

    private StartGameWindow gameStarter;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @FXML
    public void initialize() {
        ObservableList<DifficultyLevel> difficultyLevels = FXCollections.observableArrayList(DifficultyLevel.values());
        difficultyComboBox.setItems(difficultyLevels);
        difficultyComboBox.getSelectionModel().select(0);
    }

    @FXML
    public void onStartButtonAction() {
        log.info("start button clicked");
        DifficultyLevel chosenDifficultyLevel = difficultyComboBox.getValue();

        if (gameStarter != null) {
            try {
                gameStarter.startNewGame(chosenDifficultyLevel);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initialize();
    }

    public void setOnStartButtonClickedEvent(final StartGameWindow gameStarter) {
        this.gameStarter = gameStarter;
    }
}
