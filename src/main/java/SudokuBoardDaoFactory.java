import java.io.IOException;

public class SudokuBoardDaoFactory {
    public static Dao<SudokuBoard> getFileDao(String fileName) throws IOException {
        return new FileSudokuBoardDao(fileName);
    }
}
