import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuBoardTest {
    private static final List<Integer> CORRECT_SUDOKU_BOARD_VALUES = Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2,
            6, 7, 2, 1, 9, 5, 3, 4, 8,
            1, 9, 8, 3, 4, 2, 5, 6, 7,
            8, 5, 9, 7, 6, 1, 4, 2, 3,
            4, 2, 6, 8, 5, 3, 7, 9, 1,
            7, 1, 3, 9, 2, 4, 8, 5, 6,
            9, 6, 1, 5, 3, 7, 2, 8, 4,
            2, 8, 7, 4, 1, 9, 6, 3, 5,
            3, 4, 5, 2, 8, 6, 1, 7, 9);

    @Test
    public void checkIfCheckFunctionWorksOnCorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();

        for (int i = 0; i < 81; i++) {
            sudokuBoard.set(i, CORRECT_SUDOKU_BOARD_VALUES.get(i));
        }

        Assert.assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    public void checkIfCheckFunctionWorksOnIncorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();

        for (int i = 0; i < 81; i++) {
            sudokuBoard.set(i, CORRECT_SUDOKU_BOARD_VALUES.get(i));
        }

        int existingNumber = sudokuBoard.get(0);
        sudokuBoard.set(1, existingNumber);

        Assert.assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    public void checkFalseEqualsForSudokuBoard() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);
        sudokuSolver.solve(sudokuBoard2);

        Assert.assertNotEquals(sudokuBoard1, sudokuBoard2);
    }

    @Test
    public void checkEqualsForSudokuBoard() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);
        sudokuBoard2 = sudokuBoard1;

        Assert.assertEquals(sudokuBoard1, sudokuBoard2);
    }

    @Test
    public void checkFalseHashcodeForSudokuBoard() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);
        sudokuSolver.solve(sudokuBoard2);

        Assert.assertNotEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());
    }

    @Test
    public void checkHashcodeForSudokuBoard() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);
        sudokuBoard2 = sudokuBoard1;

        Assert.assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());
    }
    
    @Test
    public void checkCloneForSudokuBoard() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = null;
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);

        try {
            sudokuBoard2 = (SudokuBoard) sudokuBoard1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(sudokuBoard2);
        Assert.assertEquals(sudokuBoard1, sudokuBoard2);
        Assert.assertNotSame(sudokuBoard1, sudokuBoard2);
        
        if (sudokuBoard2.get(0) != 5) {
            sudokuBoard2.set(0, 5);
        } else {
            sudokuBoard2.set(0, 6);
        }
        Assert.assertNotEquals(sudokuBoard1, sudokuBoard2);
    }
}
