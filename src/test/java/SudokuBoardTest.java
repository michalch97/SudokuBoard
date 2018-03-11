import org.junit.Assert;
import org.junit.Test;

public class SudokuBoardTest {

    @Test
    public void checkIfBoardIsCorrect() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        Field[] field = sudokuBoard.getBoard();
        for (Field f1 : field) {
            for (Field f2 : field) {
                if (f1 != f2) {
                    if ((f1.getxAxis() == f2.getxAxis()) || (f1.getyAxis() == f2.getyAxis()) || (f1.getSector() == f2.getSector())) {
                        Assert.assertFalse(1 == 0);
                    }
                }
            }
        }
    }

    @Test
    public void checkIfTheBoardsAreTheSame() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        Field[] field1 = sudokuBoard.getBoard();
        sudokuBoard.fillBoard();
        Field[] field2 = sudokuBoard.getBoard();
        int counter = 0;
        for (int i = 0; i < 81; i++) {
            if (field1[i].getValue() == field2[i].getValue()) {
                counter++;
            }
        }
        Assert.assertFalse(counter == 81);
    }
}
