
public class SudokuColumn extends SudokuArea {

    private SudokuColumn(final SudokuField[] areaFields) {
        super(areaFields);
        
    }
    
    public static SudokuColumn createSudokuColumn(final SudokuField[] boardFields, int x) {
        SudokuField[] areaFields = new SudokuField[AREA_SIZE];
        
        for (int i = 0; i < AREA_SIZE; i++) {
            areaFields[i] = boardFields[(x - 1) + 9 * i];
        }
        
        return new SudokuColumn(areaFields);
    }
}
