import org.junit.Assert;
import org.junit.Test;

public class SudokuAreaTests {
    
    @Test
    public void checkIfCreatingAreasWorks() {
        final int [] EXPECTED_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9};
        SudokuBoard sudokuBoard = new SudokuBoard();
        
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i, i + 1);
        }
        
        SudokuField[] fields = sudokuBoard.getRow(1).getFields();
        
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(fields[i].getFieldValue() == EXPECTED_VALUES[i]);
        }
        
        
        sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i * 9, i + 1);
        }
        
        fields = sudokuBoard.getColumn(1).getFields();
        
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(fields[i].getFieldValue() == EXPECTED_VALUES[i]);
        }
        
        
        sudokuBoard = new SudokuBoard();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                sudokuBoard.set(i, j, (i - 1)*3 + j);
            }
        }
        
        fields = sudokuBoard.getBox(1, 1).getFields();
        
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(fields[i].getFieldValue() == EXPECTED_VALUES[i]);
        }
    }
    
    @Test
    public void checkIfVerifyWorksOnCorrectData() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i, i);
        }
        
        Assert.assertTrue(sudokuBoard.getRow(1).verify());
    }
    
    @Test
    public void checkIfVerifyWorksOnIncorrectData() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i, i + 1);
        }
        
        sudokuBoard.set(1, 1);
        
        Assert.assertFalse(sudokuBoard.getRow(1).verify());
    }
    
    @Test
    public void checkIfVerifyWorksOnPartialyEmptyCorrectData() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        
        sudokuBoard.set(0, 1);
        sudokuBoard.set(1, 2);
        
        Assert.assertTrue(sudokuBoard.getRow(1).verify());
    }
    
    @Test
    public void checkIfVerifyWorksOnPartialyEmptyIncorrectData() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        
        sudokuBoard.set(0, 1);
        sudokuBoard.set(1, 1);
        
        Assert.assertFalse(sudokuBoard.getRow(1).verify());
    }
}
