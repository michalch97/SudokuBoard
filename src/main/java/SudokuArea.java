import java.util.List;

public abstract class SudokuArea {
    protected static final int AREA_SIZE = 9;
    protected List<SudokuField> containedFields;

    public SudokuArea(final List<SudokuField> fields) {
        containedFields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < AREA_SIZE; i++) {
            for (int j = 0; j < AREA_SIZE; j++) {
                if (containedFields.get(i) == null || containedFields.get(j) == null) {
                    continue;
                }

                if (i != j && containedFields.get(i).getFieldValue() == containedFields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<SudokuField> getFields() {
        return containedFields;
    }
}
