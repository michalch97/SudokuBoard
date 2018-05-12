import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuFieldTest {
    @Test
    public void checkIfFieldGivesCorrectValues() {
        SudokuField field = new SudokuField(1);

        Assert.assertTrue(field.getFieldValue() == 1);
    }

    @Test
    public void checkEqualsForSudokuField() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);

        Assert.assertEquals(sudokuField1, sudokuField2);
    }

    @Test
    public void checkFalseEqualsForSudokuField() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(2);

        Assert.assertNotEquals(sudokuField1, sudokuField2);
    }

    @Test
    public void checkHashcodeForSudokuField() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);

        Assert.assertEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }

    @Test
    public void checkFalseHashcodeForSudokuField() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(2);

        Assert.assertNotEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }
    
    @Test
    public void checkCloneForSudokuField() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = null;
        try {
            sudokuField2 = (SudokuField) sudokuField1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(sudokuField2);
        Assert.assertEquals(sudokuField1, sudokuField2);
        Assert.assertNotSame(sudokuField1, sudokuField2);
    }
    
    @Test
    public void checkComparableForSudokuField() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        SudokuField sudokuField3 = new SudokuField(2);

        Assert.assertEquals(0, sudokuField1.compareTo(sudokuField2));
        Assert.assertEquals(-1, sudokuField1.compareTo(sudokuField3));
        Assert.assertEquals(1, sudokuField3.compareTo(sudokuField1));
    }
}
