import org.junit.Assert;
import org.junit.Test;

public class BacktrackingSolverTest {

    @Test
    public void checkIfBacktrackingSolverGeneratedCorrectBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);

        final int boardSideSize = 9;
        for (int i = 0 ; i < boardSideSize * boardSideSize; i++) {
            for (int j = 0 ; j < boardSideSize * boardSideSize; j++) {
                if (i != j) {
                    if ((sudokuBoard.getField(i).getXAxis() == sudokuBoard.getField(j).getXAxis()) ||
                            (sudokuBoard.getField(i).getYAxis() == sudokuBoard.getField(j).getYAxis()) ||
                            (sudokuBoard.getField(i).getSector() == sudokuBoard.getField(j).getSector())) {
                        Assert.assertFalse(sudokuBoard.getField(i).getValue() == sudokuBoard.getField(j).getValue());
                    }
                }
            }
        }
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
            if (sudokuBoard1.getField(i).getValue() == sudokuBoard2.getField(i).getValue()) {
                counter++;
            }
        }
        
        Assert.assertFalse(counter == 81);
    }
}
