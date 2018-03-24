
public abstract class SudokuArea {
    protected static final int AREA_SIZE = 9;
    protected SudokuField[] containedFields;
    
    public SudokuArea(final SudokuField[] fields) {
        containedFields = fields;
    }
    
    public boolean verify() {
        for (int i = 0; i < AREA_SIZE; i++) {
            for (int j = 0; j < AREA_SIZE; j++) {
                if (containedFields[i] == null || containedFields[j] == null) {
                    continue;
                }
                
                if (i != j && containedFields[i].getFieldValue() == containedFields[j].getFieldValue()) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public SudokuField[] getFields() {
        return containedFields;
    }
}
