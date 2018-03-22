import org.junit.Assert;
import org.junit.Test;

public class SudokuBoardTest {
    private static final int[] CORRECT_SUDOKU_BOARD_VALUES = { 5, 3, 4, 6, 7, 8, 9, 1, 2,
                                                               6, 7, 2, 1, 9, 5, 3, 4, 8,
                                                               1, 9, 8, 3, 4, 2, 5, 6, 7,
                                                               8, 5, 9, 7, 6, 1, 4, 2, 3,
                                                               4, 2, 6, 8, 5, 3, 7, 9, 1,
                                                               7, 1, 3, 9, 2, 4, 8, 5, 6,
                                                               9, 6, 1, 5, 3, 7, 2, 8, 4,
                                                               2, 8, 7, 4, 1, 9, 6, 3, 5,
                                                               3, 4, 5, 2, 8, 6, 1, 7, 9};
    
    @Test
    public void checkIfCheckFunctionWorksOnCorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();

        for (int i = 0; i < 81; i++) {
            sudokuBoard.set(i, CORRECT_SUDOKU_BOARD_VALUES[i]);
        }
        
        Assert.assertTrue(sudokuBoard.checkBoard());
    }
    
    @Test
    public void checkIfCheckFunctionWorksOnIncorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        
        for (int i = 0; i < 81; i++) {
            sudokuBoard.set(i, CORRECT_SUDOKU_BOARD_VALUES[i]);
        }
        
        int existingNumber = sudokuBoard.get(0);
        sudokuBoard.set(1, existingNumber);
        
        Assert.assertFalse(sudokuBoard.checkBoard());
    }
}
