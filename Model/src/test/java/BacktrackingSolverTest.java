import org.junit.Assert;
import org.junit.Test;

public class BacktrackingSolverTest {

    @Test
    public void checkIfBacktrackingSolverGeneratedCorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);

        Assert.assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    public void checkIfBacktrackingSolverGenerationIsNotRepetitive() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);
        sudokuSolver.solve(sudokuBoard2);

        int counter = 0;
        for (int i = 0; i < 81; i++) {
            if (sudokuBoard1.getField(i).getFieldValue() == sudokuBoard2.getField(i).getFieldValue()) {
                counter++;
            }
        }

        Assert.assertFalse(counter == 81);
    }

    @Test
    public void checkFalseHashcodeForBacktrackingSudokuSolver() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuSolver sudokuSolver2 = new BacktrackingSudokuSolver();
        sudokuSolver1.solve(sudokuBoard);
        sudokuSolver2.solve(sudokuBoard);

        Assert.assertNotEquals(sudokuSolver1.hashCode(), sudokuSolver2.hashCode());
    }

    @Test
    public void checkHashcodeForBacktrackingSudokuSolver() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuSolver sudokuSolver2;
        sudokuSolver1.solve(sudokuBoard);
        sudokuSolver2 = sudokuSolver1;

        Assert.assertEquals(sudokuSolver1.hashCode(), sudokuSolver2.hashCode());
    }

    @Test
    public void checkFalseEqualsForBacktrackingSudokuSolver() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuSolver sudokuSolver2 = new BacktrackingSudokuSolver();
        sudokuSolver1.solve(sudokuBoard);
        sudokuSolver2.solve(sudokuBoard);

        Assert.assertNotEquals(sudokuSolver1, sudokuSolver2);
    }

    @Test
    public void checkEqualsForBacktrackingSudokuSolver() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuSolver sudokuSolver2;
        sudokuSolver1.solve(sudokuBoard);
        sudokuSolver2 = sudokuSolver1;

        Assert.assertEquals(sudokuSolver1, sudokuSolver2);
    }
}
