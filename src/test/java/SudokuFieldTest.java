import org.junit.Assert;
import org.junit.Test;

public class SudokuFieldTest {
    @Test
    public void checkIfFieldGivesCorrectValues() {
        SudokuField field = new SudokuField(1);
        
        Assert.assertTrue(field.getFieldValue() == 1);
    }
}
