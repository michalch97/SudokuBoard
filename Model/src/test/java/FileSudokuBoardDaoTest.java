import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileSudokuBoardDaoTest {
    private static final List<Integer> CORRECT_SUDOKU_BOARD_VALUES =
            Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2,
                    6, 7, 2, 1, 9, 5, 3, 4, 8,
                    1, 9, 8, 3, 4, 2, 5, 6, 7,
                    8, 5, 9, 7, 6, 1, 4, 2, 3,
                    4, 2, 6, 8, 5, 3, 7, 9, 1,
                    7, 1, 3, 9, 2, 4, 8, 5, 6,
                    9, 6, 1, 5, 3, 7, 2, 8, 4,
                    2, 8, 7, 4, 1, 9, 6, 3, 5,
                    3, 4, 5, 2, 8, 6, 1, 7, 9);

    private SudokuBoard sudokuBoardExemplary;

    @Before
    public void setupSudokuBoard() {
        sudokuBoardExemplary = new SudokuBoard();

        for (int i = 0; i < 81; i++) {
            sudokuBoardExemplary.set(i, CORRECT_SUDOKU_BOARD_VALUES.get(i));
        }
    }

    @Test
    public void checkIfSudokuBoardSuccessfullySerializes() {
        final String filePath = "target/sudokuSer.ser";

        try (FileSudokuBoardDao sudokuDao = new FileSudokuBoardDao(filePath)) {
            sudokuDao.write(sudokuBoardExemplary);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        File saveFile = new File(filePath);
        Assert.assertTrue(saveFile.exists());
        Assert.assertTrue(saveFile.length() > 1000);
    }

    @Test
    public void checkIfSudokuBoardSuccessfullyDeserializes() {
        try (FileSudokuBoardDao sudokuDao = new FileSudokuBoardDao("testData/sudokuSerialized.ser")) {
            SudokuBoard loadedSudokuBoard;
            loadedSudokuBoard = sudokuDao.read();

            Assert.assertTrue(loadedSudokuBoard.equals(sudokuBoardExemplary));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
