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
}
