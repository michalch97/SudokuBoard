import java.util.Arrays;
import java.util.List;

public class SudokuColumn extends SudokuArea {

    private SudokuColumn(final List<SudokuField> areaFields) {
        super(areaFields);

    }

    public static SudokuColumn createSudokuColumn(final List<SudokuField> boardFields, int x) {
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[AREA_SIZE]);

        for (int i = 0; i < AREA_SIZE; i++) {
            areaFields.set(i, boardFields.get((x - 1) + 9 * i));
        }

        return new SudokuColumn(areaFields);
    }
}
