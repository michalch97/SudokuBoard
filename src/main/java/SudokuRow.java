import java.util.Arrays;
import java.util.List;

public class SudokuRow extends SudokuArea {

    private SudokuRow(final List<SudokuField> areaFields) {
        super(areaFields);
    }

    public static SudokuRow createSudokuRow(final List<SudokuField> boardFields, int y) {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[AREA_SIZE]);

        for (int i = 0; i < AREA_SIZE; i++) {
            areaFields.set(i, boardFields.get(((y - 1) * 9 + i)));
        }

        return new SudokuRow(areaFields);
    }
}
