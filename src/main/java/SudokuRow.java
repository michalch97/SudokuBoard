
public class SudokuRow extends SudokuArea {
    
    private SudokuRow(final SudokuField[] areaFields) {
        super(areaFields);
    }
    
    public static SudokuRow createSudokuRow(final SudokuField[] boardFields, int y) {
        SudokuField[] areaFields = new SudokuField[AREA_SIZE];
        
        for (int i = 0; i < AREA_SIZE; i++) {
            areaFields[i] = boardFields[((y - 1) * 9 + i)];
        }
        
        return new SudokuRow(areaFields);
    }
}
