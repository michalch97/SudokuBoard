import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.List;

public class SudokuBox extends SudokuArea {

    private SudokuBox(final List<SudokuField> areaFields) {
        super(areaFields);
    }

    public static SudokuBox createSudokuBox(final List<SudokuField> boardFields, int x, int y) {
        final int sectorSideLength = 3;
        List<SudokuField> areaFields = Arrays.asList(new SudokuField[AREA_SIZE]);

        x = x - 1;
        y = y - 1;

        for (int i = 0; i < sectorSideLength; i++) { // remove from available in the same sector
            for (int j = 0; j < sectorSideLength; j++) {
                areaFields.set(i * sectorSideLength + j, boardFields.get((((x / 3) * 3 + i) + ((y / 3) * 3 * 9 + j * 9))));
            }
        }

        return new SudokuBox(areaFields);
    }
}
