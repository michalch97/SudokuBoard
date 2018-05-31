import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JdbcSudokuBoardDaoTest {
    @Test
    public void checkIfSudokuBoardSuccessfullySavedInDatabase() {
        try {
            SudokuBoard sudokuBoard = new SudokuBoard();
            SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
            sudokuSolver.solve(sudokuBoard);
            JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("test" + Integer.toString(sudokuBoard.hashCode()) + Integer.toString(sudokuSolver.hashCode()));
            jdbcSudokuBoardDao.write(sudokuBoard);
            Assert.assertTrue(jdbcSudokuBoardDao.checkIfExist("test" + Integer.toString(sudokuBoard.hashCode()) + Integer.toString(sudokuSolver.hashCode())));
            jdbcSudokuBoardDao.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void checkIfSudokuBoardSuccessfullyReadedFromDatabase() {try {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("test" + Integer.toString(sudokuBoard.hashCode()) + Integer.toString(sudokuSolver.hashCode()));
        jdbcSudokuBoardDao.write(sudokuBoard);
        SudokuBoard sudokuBoard2 = jdbcSudokuBoardDao.read();
        jdbcSudokuBoardDao.close();
        Assert.assertEquals(sudokuBoard,sudokuBoard2);
    } catch (Throwable throwable) {
        throwable.printStackTrace();
    }
    }
}
