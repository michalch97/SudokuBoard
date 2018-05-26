import java.io.IOException;

public class SudokuBoardDaoFactory {
    public static Dao<SudokuBoard> getFileDao(final String fileName) throws IOException {
        return new FileSudokuBoardDao(fileName);
    }
}
