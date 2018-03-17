import org.junit.Assert;
import org.junit.Test;

public class SudokuBoardTest {
    
    @Test
    public void checkIfCheckFunctionWorksOnCorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        
        Assert.assertTrue(sudokuBoard.checkBoard());
    }
    
    @Test
    public void checkIfCheckFunctionWorksOnIncorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        
        int existingNumber = sudokuBoard.get(0);
        sudokuBoard.set(1, existingNumber);
        
        Assert.assertFalse(sudokuBoard.checkBoard());
    }
}
