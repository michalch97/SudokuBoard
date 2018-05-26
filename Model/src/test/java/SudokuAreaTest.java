import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuAreaTest {

    @Test
    public void checkIfCreatingAreasWorks() {
        final int[] EXPECTED_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SudokuBoard sudokuBoard = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i, i + 1);
        }

        List<SudokuField> fields = sudokuBoard.getRow(1).getFields();

        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(fields.get(i).getFieldValue() == EXPECTED_VALUES[i]);
        }


        sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(i * 9, i + 1);
        }

        fields = sudokuBoard.getColumn(1).getFields();

        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(fields.get(i).getFieldValue() == EXPECTED_VALUES[i]);
        }


        sudokuBoard = new SudokuBoard();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                sudokuBoard.set(i, j, (i - 1) * 3 + j);
            }
        }

        fields = sudokuBoard.getBox(1, 1).getFields();

        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(fields.get(i).getFieldValue() == EXPECTED_VALUES[i]);
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

    @Test
    public void checkEqualsForSudokuBox() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuBox sudokuBox1 = SudokuBox.createSudokuBox(areaFields, 1, 1);
        SudokuBox sudokuBox2 = SudokuBox.createSudokuBox(areaFields, 1, 1);
        Assert.assertEquals(sudokuBox1, sudokuBox2);
    }

    @Test
    public void checkFalseEqualsForSudokuBox() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuBox sudokuBox1 = SudokuBox.createSudokuBox(areaFields, 4, 4);
        Collections.shuffle(areaFields);
        SudokuBox sudokuBox2 = SudokuBox.createSudokuBox(areaFields, 1, 1);
        Assert.assertNotEquals(sudokuBox1, sudokuBox2);
    }

    @Test
    public void checkEqualsForSudokuColumn() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuColumn sudokuColumn1 = SudokuColumn.createSudokuColumn(areaFields, 1);
        SudokuColumn sudokuColumn2 = SudokuColumn.createSudokuColumn(areaFields, 1);
        Assert.assertEquals(sudokuColumn1, sudokuColumn2);
    }

    @Test
    public void checkFalseEqualsForSudokuColumn() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuColumn sudokuColumn1 = SudokuColumn.createSudokuColumn(areaFields, 1);
        Collections.shuffle(areaFields);
        SudokuColumn sudokuColumn2 = SudokuColumn.createSudokuColumn(areaFields, 2);
        Assert.assertNotEquals(sudokuColumn1, sudokuColumn2);
    }

    @Test
    public void checkEqualsForSudokuRow() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuRow sudokuRow1 = SudokuRow.createSudokuRow(areaFields, 1);
        SudokuRow sudokuRow2 = SudokuRow.createSudokuRow(areaFields, 1);
        Assert.assertEquals(sudokuRow1, sudokuRow2);
    }

    @Test
    public void checkFalseEqualsForSudokuRow() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuRow sudokuRow1 = SudokuRow.createSudokuRow(areaFields, 1);
        Collections.shuffle(areaFields);
        SudokuRow sudokuRow2 = SudokuRow.createSudokuRow(areaFields, 2);
        Assert.assertNotEquals(sudokuRow1, sudokuRow2);
    }

    @Test
    public void checkHashcodeForSudokuBox() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuBox sudokuBox1 = SudokuBox.createSudokuBox(areaFields, 1, 1);
        SudokuBox sudokuBox2 = SudokuBox.createSudokuBox(areaFields, 1, 1);
        Assert.assertEquals(sudokuBox1.hashCode(), sudokuBox2.hashCode());
    }

    @Test
    public void checkFalseHashcodeForSudokuBox() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuBox sudokuBox1 = SudokuBox.createSudokuBox(areaFields, 4, 4);
        Collections.shuffle(areaFields);
        SudokuBox sudokuBox2 = SudokuBox.createSudokuBox(areaFields, 1, 1);
        Assert.assertNotEquals(sudokuBox1.hashCode(), sudokuBox2.hashCode());
    }

    @Test
    public void checkHashcodeForSudokuColumn() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuColumn sudokuColumn1 = SudokuColumn.createSudokuColumn(areaFields, 1);
        SudokuColumn sudokuColumn2 = SudokuColumn.createSudokuColumn(areaFields, 1);
        Assert.assertEquals(sudokuColumn1.hashCode(), sudokuColumn2.hashCode());
    }

    @Test
    public void checkFalseHashcodeForSudokuColumn() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuColumn sudokuColumn1 = SudokuColumn.createSudokuColumn(areaFields, 1);
        Collections.shuffle(areaFields);
        SudokuColumn sudokuColumn2 = SudokuColumn.createSudokuColumn(areaFields, 2);
        Assert.assertNotEquals(sudokuColumn1.hashCode(), sudokuColumn2.hashCode());
    }

    @Test
    public void checkHashcodeForSudokuRow() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuRow sudokuRow1 = SudokuRow.createSudokuRow(areaFields, 1);
        SudokuRow sudokuRow2 = SudokuRow.createSudokuRow(areaFields, 1);
        Assert.assertEquals(sudokuRow1.hashCode(), sudokuRow2.hashCode());
    }

    @Test
    public void checkFalseHashcodeForSudokuRow() {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[81]);
        for (int i = 1; i <= 81; i++) {
            areaFields.set(i - 1, new SudokuField(i));
        }
        SudokuRow sudokuRow1 = SudokuRow.createSudokuRow(areaFields, 1);
        Collections.shuffle(areaFields);
        SudokuRow sudokuRow2 = SudokuRow.createSudokuRow(areaFields, 2);
        Assert.assertNotEquals(sudokuRow1.hashCode(), sudokuRow2.hashCode());
    }
}
