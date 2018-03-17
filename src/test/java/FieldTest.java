import org.junit.Assert;
import org.junit.Test;

public class FieldTest {
    @Test
    public void checkIfFieldGivesCorrectValues() {
        Field field = new Field(16, 1);
        
        Assert.assertTrue(field.getValue() == 1);
        Assert.assertTrue(field.getSector() == 3);
        Assert.assertTrue(field.getXAxis() == 8);
        Assert.assertTrue(field.getYAxis() == 2);
    }
}
