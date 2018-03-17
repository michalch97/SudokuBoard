import org.junit.Assert;
import org.junit.Test;

public class SudokuBoardTest {
    
    @Test
    public void testCheckFunction() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        
        Assert.assertTrue(sudokuBoard.checkBoard());
    }
}
