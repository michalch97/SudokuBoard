
public class SudokuBox extends SudokuArea {

    private SudokuBox(final SudokuField[] areaFields) {
        super(areaFields);
    }
    
    public static SudokuBox createSudokuBox(final SudokuField[] boardFields, int x, int y) {
        final int sectorSideLength = 3;
        SudokuField[] areaFields = new SudokuField[AREA_SIZE];
        
        x = x - 1;
        y = y - 1;
        
        for (int i = 0; i < sectorSideLength; i++) { // remove from available in the same sector
            for (int j = 0; j < sectorSideLength; j++) {
                areaFields[i * sectorSideLength + j] = boardFields[(((x / 3) * 3 + i) + ((y / 3) * 3 * 9 + j * 9))];
            }
        }
        
        return new SudokuBox(areaFields);
    }
}
